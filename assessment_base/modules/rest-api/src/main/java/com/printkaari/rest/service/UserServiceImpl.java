package com.printkaari.rest.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prinktaakri.auth.util.AuthorizationUtil;
import com.printkaari.auth.service.SystemRoles;
import com.printkaari.data.dao.CustomerDao;
import com.printkaari.data.dao.EmployeeDao;
import com.printkaari.data.dao.UserDao;
import com.printkaari.data.dao.entity.Customer;
import com.printkaari.data.dao.entity.Employee;
import com.printkaari.data.dao.entity.User;
import com.printkaari.data.dto.UserDto;
import com.printkaari.data.exception.InstanceNotFoundException;
import com.printkaari.message.exception.MailNotSentException;
import com.printkaari.message.model.MailMessage;
import com.printkaari.message.service.MailService;
import com.printkaari.message.utils.ReadConfigurationFile;
import com.printkaari.rest.constant.CommonStatus;
import com.printkaari.rest.constant.ErrorCodes;
import com.printkaari.rest.constant.UserStatus;
import com.printkaari.rest.constant.UserTypes;
import com.printkaari.rest.exception.EmptyListException;
import com.printkaari.rest.exception.PasswordException;
import com.printkaari.rest.exception.SignUpException;
import com.printkaari.rest.form.ResetPasswordForm;
import com.printkaari.rest.form.SignUpStep1Form;
import com.printkaari.rest.utils.PasswordUtils;
import com.printkaari.rest.utils.RestClientUtils;
import com.printkaari.rest.utils.ValidationUtils;

@Service
public class UserServiceImpl implements UserService {
	private Logger		LOGGER	= LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao		userDao;
	
	@Autowired
	private CustomerDao custDao;
	
	@Autowired
	private EmployeeDao empDao;

	@Autowired
	private MailService	mailService;

	private void validateInitiateSignUpRequest(String email) throws SignUpException {
		try {
			User user = (User) userDao.getByCriteria(userDao.getFindByEmailCriteria(email));
			if (user != null) {
				UserStatus status = UserStatus.valueOf(user.getStatus());
				switch (status) {
				case SIGNUP_INITIATED:
					throw new SignUpException("User already initiated Sign Up Process!",
					        ErrorCodes.SIGNUP_ALREADY_INITIATED);
				case ACTIVE:
					throw new SignUpException("User already registered with this Email!",
					        ErrorCodes.SIGNUP_ALREADY_ACTIVE);
				case FORGET_PASSWORD_INITIATED:
					throw new SignUpException("User already registered with this Email!",
					        ErrorCodes.SIGNUP_ALREADY_ACTIVE);
				case ARCHIVED:
					throw new SignUpException(
					        "Your account is deactivated, Please contact your adminisrator!",
					        ErrorCodes.SIGNUP_ACCOUNT_DEACTIVATED);
				default:
					break;
				}
			}
		} catch (InstanceNotFoundException e) {
			LOGGER.error("User does not exist. Valid scenario, do nothing.");
		}
	}

	private void saveUser(SignUpStep1Form signUpStep1Form) throws SignUpException {
		try {
			User user = new User();
			user.setFirstName(signUpStep1Form.getFirstName());
			user.setLastName(signUpStep1Form.getLastName());
			user.setEmailId(signUpStep1Form.getEmail());
			user.setTempPassword(signUpStep1Form.getPassword());
			String salt = BCrypt.gensalt(12);
			user.setPassword(BCrypt.hashpw(signUpStep1Form.getPassword(), salt));
			user.setStatus(UserStatus.SIGNUP_INITIATED.toString());
		
			
			
			LOGGER.debug("Savig User");
			userDao.save(user);
			LOGGER.debug("User saved");
			
			if(signUpStep1Form.getUserType().equalsIgnoreCase(UserTypes.CUSTOMER.toString())){
				saveCustomer(signUpStep1Form);
			}
			if(signUpStep1Form.getUserType().equalsIgnoreCase(UserTypes.EMPLOYEE.toString())){
				saveEmployee(signUpStep1Form);
			}
			
			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new SignUpException("Error occurred while creating new User in database!",
			        ErrorCodes.DATABASE_ERROR);
		}
	}

	private void saveEmployee(SignUpStep1Form signUpStep1Form) {
	
		Employee emp=new Employee();
		emp.setFirstName(signUpStep1Form.getFirstName());
		emp.setLastName(signUpStep1Form.getLastName());
		emp.setStatus(UserStatus.SIGNUP_INITIATED.toString());
		emp.setEmail(signUpStep1Form.getEmail());
		LOGGER.debug("Savig Employee");
		empDao.save(emp);
		LOGGER.debug("Employee saved with status :"+UserStatus.SIGNUP_INITIATED.toString());
		
	}

	private void saveCustomer(SignUpStep1Form signUpStep1Form) {

        Customer cust=new Customer();
		
		cust.setFirstName(signUpStep1Form.getFirstName());
		cust.setLastName(signUpStep1Form.getLastName());
		cust.setStatus(UserStatus.SIGNUP_INITIATED.toString());
		cust.setEmail(signUpStep1Form.getEmail());

		LOGGER.debug("Savig Customer");
		custDao.save(cust);
		LOGGER.debug("Customer saved :"+UserStatus.SIGNUP_INITIATED.toString());
		
	}

	private void sendSignUpEmail(String email, String firstName, String lastName)
	        throws SignUpException {
		String url = ReadConfigurationFile.getProperties("ui-redirection-urls.properties")
		        .getProperty("signup.url");

		MailMessage mailHtmlMessage = new MailMessage();
		mailHtmlMessage.setSubject("Welcome to Prinkaari !!");
		mailHtmlMessage.setContent("<h2>Hello " + firstName + " " + lastName
		        + "!</h2><h3>Please click on below link to activate your account</h3>" + " <a href="
		        + MessageFormat.format(url, PasswordUtils.encode(email)) + ">"
		        + MessageFormat.format(url, PasswordUtils.encode(email)) + "</a>");
		mailHtmlMessage.setToAddresses(new String[] { email });
		try {
			mailService.sendHtmlMail(mailHtmlMessage);
		} catch (MailNotSentException e) {
			LOGGER.error(e.getMessage(), e);
			throw new SignUpException("Error occurred while sending Sign Up Email!",
			        ErrorCodes.EMAIL_ERROR);
		}
		LOGGER.info("HTML Email Sent");
	}

	@Override
	@Transactional
	public void initiateSignUp(SignUpStep1Form signUpstep1Form) throws SignUpException {
		// Validating Intiate Sign Up Request
		validateInitiateSignUpRequest(signUpstep1Form.getEmail());

		// Creating new user
		saveUser(signUpstep1Form);

		// Sending Welcome Email to user
		sendSignUpEmail(signUpstep1Form.getEmail(), signUpstep1Form.getFirstName(),
		        signUpstep1Form.getLastName());
	}

	@Override
	@Transactional
	public void resendEmail(String token) throws SignUpException {
		if (token == null || token.trim().length() <= 0) {
			throw new SignUpException("Email token should not be empty or null.",
			        ErrorCodes.VALIDATION_ERROR);
		}
		try {
			LOGGER.debug("Resend Email");
			String email = PasswordUtils.decode(token);
			if (!ValidationUtils.validateEmail(email)) {
				throw new SignUpException("Please provide valid Email.",
				        ErrorCodes.VALIDATION_ERROR);
			}

			User user = (User) userDao.getByCriteria(userDao.getFindByEmailCriteria(email));
			if (user == null) {
				throw new SignUpException("No user found with this Email",
				        ErrorCodes.USER_NOT_FOUND_ERROR);
			}
			UserStatus status = UserStatus.valueOf(user.getStatus());
			switch (status) {
			case SIGNUP_INITIATED:
				sendSignUpEmail(email, user.getFirstName(), user.getLastName());
				break;
			case ACTIVE:
				throw new SignUpException("User already registered with this Email!",
				        ErrorCodes.SIGNUP_ALREADY_ACTIVE);
			case FORGET_PASSWORD_INITIATED:
				throw new SignUpException("User already registered with this Email!",
				        ErrorCodes.SIGNUP_ALREADY_ACTIVE);
			case ARCHIVED:
				throw new SignUpException(
				        "Your account is deactivated, Please contact your administrator!",
				        ErrorCodes.SIGNUP_ACCOUNT_DEACTIVATED);
			default:
				break;

			}
		} catch (InstanceNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
			throw new SignUpException("No user found with this Email",
			        ErrorCodes.USER_NOT_FOUND_ERROR);
		}

	}

	@Override
	@Transactional
	public void sendForgotPasswordLink(String emailId)
	        throws MailNotSentException, PasswordException {
		if (!ValidationUtils.validateEmail(emailId)) {
			throw new PasswordException("Please provide valid Email.", ErrorCodes.VALIDATION_ERROR);
		}

		User user = validateForgetPasswordRequest(emailId);

		updateUser(user);
	}

	private void updateUser(User user) throws PasswordException {
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		sendForgotPasswordEmail(user.getEmailId(), firstName, lastName);
		user.setStatus(UserStatus.FORGET_PASSWORD_INITIATED.toString());
		userDao.update(user);
	}

	private User validateForgetPasswordRequest(String emailId) throws PasswordException {
		User user = null;
		try {
			user = (User) userDao.getByCriteria(userDao.getFindByEmailCriteria(emailId.trim()));
			if (user == null) {
				throw new PasswordException("No user found with this email id",
				        ErrorCodes.USER_NOT_FOUND_ERROR);
			}

			UserStatus status = UserStatus.valueOf(user.getStatus());
			switch (status) {
			case SIGNUP_INITIATED:
				throw new PasswordException(
				        "You have already initiated the signup, please complete it first!",
				        ErrorCodes.FORGOT_REQUEST_SIGNUP_INITIATED);
			case ARCHIVED:
				throw new PasswordException(
				        "Your account is deactivated, Please contact your administrator!",
				        ErrorCodes.SIGNUP_ACCOUNT_DEACTIVATED);
			default:
				break;
			}
		} catch (InstanceNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
			throw new PasswordException("No user found with this Email",
			        ErrorCodes.USER_NOT_FOUND_ERROR);
		}
		return user;
	}

	@Override
	@Transactional
	public void resetPassword(ResetPasswordForm resetPasswordForm) throws PasswordException {
		String token = resetPasswordForm.getEmailToken();
		String newPassword = resetPasswordForm.getNewPassword();
		LOGGER.debug("Resetting password");
		String email = PasswordUtils.decode(token);
		if (!ValidationUtils.validateEmail(email)) {
			throw new PasswordException("Please provide valid token.", ErrorCodes.VALIDATION_ERROR);
		}

		User user;
		try {
			user = (User) userDao.getByCriteria(userDao.getFindByEmailCriteria(email));
			if (user == null) {
				throw new PasswordException("No user found with this Email",
				        ErrorCodes.USER_NOT_FOUND_ERROR);
			}

			UserStatus status = UserStatus.valueOf(user.getStatus());
			switch (status) {
			case FORGET_PASSWORD_INITIATED:
				String salt = BCrypt.gensalt(12);

				user.setPassword(BCrypt.hashpw(newPassword, salt));
				user.setStatus(UserStatus.ACTIVE.toString());
				userDao.update(user);
				break;

			case SIGNUP_INITIATED:
				throw new PasswordException(
				        "You have already initiated the signup, please complete it first!",
				        ErrorCodes.FORGOT_REQUEST_SIGNUP_INITIATED);

			case ACTIVE:
				throw new PasswordException("User is in  already active state",
				        ErrorCodes.RESET_PASSWORD_USER_ALREADY_ACTIVE);

			case ARCHIVED:
				throw new PasswordException(
				        "Your account is deactivated, Please contact your administrator!",
				        ErrorCodes.SIGNUP_ACCOUNT_DEACTIVATED);

			default:
				break;
			}

		} catch (InstanceNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
			throw new PasswordException("No user found with this Email",
			        ErrorCodes.USER_NOT_FOUND_ERROR);
		}

	}

	private void sendForgotPasswordEmail(String email, String firstName, String lastName)
	        throws PasswordException {
		String url = ReadConfigurationFile.getProperties("ui-redirection-urls.properties")
		        .getProperty("forgot.password.url");

		MailMessage mailHtmlMessage = new MailMessage();
		mailHtmlMessage.setSubject("Welcome to Assessment!");
		mailHtmlMessage.setContent("<h2>Hello " + firstName + " " + lastName
		        + "!</h2><h3>Please click on below link to reset your password</h3>" + " <a href="
		        + MessageFormat.format(url, PasswordUtils.encode(email)) + ">"
		        + MessageFormat.format(url, PasswordUtils.encode(email)) + "</a>");
		mailHtmlMessage.setToAddresses(new String[] { email });
		try {
			mailService.sendHtmlMail(mailHtmlMessage);
		} catch (MailNotSentException e) {
			LOGGER.error(e.getMessage(), e);
			throw new PasswordException("Error occurred while sending Forgot password  Email!",
			        ErrorCodes.EMAIL_ERROR);
		}
		LOGGER.info("HTML Email Sent");
	}

	@Override
	@Transactional
	public List<UserDto> recruiterDTOList() throws EmptyListException {
		List<UserDto> userDtos = new ArrayList<>();
		try {
			User user = AuthorizationUtil.getLoggedInUser();
			//Long companyId = user.getCompany().getId();
			
			Long companyId=null;

			/*userDtos = userDao.getRecruiterDTOList(CommonStatus.ACTIVE.toString(), companyId,
			        SystemRoles.ROLE_COMPANY_RECRUITER);*/
			if (userDtos.isEmpty() || userDtos == null) {
				throw new EmptyListException("Recruiter Lsit is empty",
				        ErrorCodes.RECRUIERS_LIST_EMPTY);
			}

		} catch (Exception e) {
			throw new EmptyListException(
			        "Error occured while getting recruiters list through database",
			        ErrorCodes.DATABASE_ERROR);
		}

		return userDtos;
	}

	@Override
	@Transactional
	public String loginUser(String token, String tempPassword) throws InstanceNotFoundException {
		Properties props = ReadConfigurationFile.getProperties("auth_server.properties");
		String authTokenResponse = RestClientUtils.autoLogin(PasswordUtils.decode(token),
		        tempPassword, props.getProperty("aGrantType"), props.getProperty("aClientId"),
		        props.getProperty("aClientSecret"), props.getProperty("aScope"),
		        props.getProperty("aHost"), props.getProperty("aPort"));
		LOGGER.debug("Auth token response " + authTokenResponse);
		User user = (User) userDao
		        .getByCriteria(userDao.getFindByEmailCriteria(PasswordUtils.decode(token)));
		user.setTempPassword("");
		userDao.update(user);
		return authTokenResponse;
	}

}
