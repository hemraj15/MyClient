/**
 * 
 */
package com.printkaari.rest.service;

import java.io.File;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.printkaari.auth.service.SystemRoles;
import com.printkaari.data.dao.CityDao;
import com.printkaari.data.dao.CountryDao;
import com.printkaari.data.dao.CustomerDao;
import com.printkaari.data.dao.EmployeeDao;
import com.printkaari.data.dao.PrintStoreDao;
import com.printkaari.data.dao.RoleDao;
import com.printkaari.data.dao.StateDao;
import com.printkaari.data.dao.UrlTypeDao;
import com.printkaari.data.dao.UserDao;
import com.printkaari.data.dao.entity.Address;
import com.printkaari.data.dao.entity.City;
import com.printkaari.data.dao.entity.Country;
import com.printkaari.data.dao.entity.Customer;
import com.printkaari.data.dao.entity.Employee;
import com.printkaari.data.dao.entity.PrintStore;
import com.printkaari.data.dao.entity.PrintStoreUrl;
import com.printkaari.data.dao.entity.Role;
import com.printkaari.data.dao.entity.State;
import com.printkaari.data.dao.entity.UrlType;
import com.printkaari.data.dao.entity.User;
import com.printkaari.data.exception.InstanceNotFoundException;
import com.printkaari.message.utils.ReadConfigurationFile;
import com.printkaari.rest.constant.CommonStatus;
import com.printkaari.rest.constant.ErrorCodes;
import com.printkaari.rest.constant.UserStatus;
import com.printkaari.rest.constant.UserTypes;
import com.printkaari.rest.exception.CompanyFileUploadException;
import com.printkaari.rest.exception.SignUpException;
import com.printkaari.rest.form.CompanyUrlForm;
import com.printkaari.rest.form.SignUpStep2Form;
import com.printkaari.rest.utils.FileUtils;
import com.printkaari.rest.utils.PasswordUtils;
import com.printkaari.rest.utils.ValidationUtils;

/**
 * @author Developer
 *
 */

@Service
public class PrintStoreServiceImpl implements PrintStoreService {

	private static String	BASE_UPLOAD_PATH	= "";

	private Logger			LOGGER				= LoggerFactory.getLogger(PrintStoreServiceImpl.class);

	@Autowired
	private PrintStoreDao		printStoreDao;

	@Autowired
	private UserDao			userDao;

	@Autowired
	private UrlTypeDao		urlTypeDao;

	@Autowired
	private RoleDao			roleDao;
	
	@Autowired 
	private CityDao cityDao;
	
	@Autowired
	private StateDao stateDao;
	
	@Autowired
	private CountryDao countryDao;
	
	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private CustomerDao custDao;

	static {
		Properties props = ReadConfigurationFile.getProperties("file-upload.properties");
		BASE_UPLOAD_PATH = props.getProperty("base_upload_path");
	}

	@Override
	@Transactional
	public String completeSignup(SignUpStep2Form signUpStep2Form) throws SignUpException {
		// Validating Complete Sign Up Request
		User user = validateCompleteSignUpRequest(signUpStep2Form);
		String tempPassword = user.getTempPassword();
		Long userId=null;
		
		
		if(signUpStep2Form.getUserType().equalsIgnoreCase(UserTypes.CUSTOMER.toString())){
			
			Customer cust=new Customer();
			
				 cust =validateCustomer(signUpStep2Form);
					userId=saveCustomer(signUpStep2Form,user,cust);
		
		}
		else if(signUpStep2Form.getUserType().equalsIgnoreCase(UserTypes.EMPLOYEE.toString())){
			
				
				Employee emp=validateEmployee(signUpStep2Form);
				userId=saveEmployee(signUpStep2Form,user,emp);
		
		}
		
		
		
		//Long userId = saveUser(signUpStep2Form, user);
		LOGGER.debug("Created User ID : " + userId);

		return tempPassword;
	}

	private Long saveEmployee(SignUpStep2Form signUpStep2Form, User user,	Employee emp) throws SignUpException {
		

		Country contry=new Country();
		City city=new City();
		State state=new State();
		Address address=new Address();
		
		try {
             state=stateDao.find(signUpStep2Form.getStateId());
			
			city=cityDao.find(signUpStep2Form.getCityId());
			
			contry=countryDao.find(signUpStep2Form.getCountryId());
			
			address.setCity(city);
			address.setState(state);
			address.setCity(city);
			address.setArea(signUpStep2Form.getArea());
			address.setHouseNo(signUpStep2Form.getHouseNo());
			address.setLandMark(signUpStep2Form.getLandMark());
			address.setPinCode(signUpStep2Form.getZipCode());
			address.setCountry(contry);
			
			emp.setAddress(address);
			emp.setContactNo(signUpStep2Form.getContactNo());
			
			emp.setStatus(UserStatus.ACTIVE.toString());
			
			empDao.saveOrUpdate(emp);
			
			// Updating User
						Set<Role> roles = new HashSet<>();
						roles.add((Role) roleDao
						        .getByCriteria(roleDao.getFindByNameCriteria("ADMIN")));
						roles.add((Role) roleDao
						        .getByCriteria(roleDao.getFindByNameCriteria(SystemRoles.ADMIN)));
						roles.add((Role) roleDao.getByCriteria(
						        roleDao.getFindByNameCriteria(SystemRoles.ADMIN)));
						user.setRoles(roles);
						//user.setCompany(printStore);
						user.setStatus(UserStatus.ACTIVE.toString());
						userDao.update(user);
			
		} catch (Exception e) {
			
			if (e instanceof SignUpException) {
				throw (SignUpException) e;
			}
			LOGGER.error("Some error occurred while updating User or adding PrintStore. Reason : "
			        + e.getMessage(), e);
			throw new SignUpException("Error occurred while updating database!",
			        ErrorCodes.DATABASE_ERROR);
		}
		return user.getId();
	}

	private Long saveCustomer(SignUpStep2Form signUpStep2Form, User user,Customer cust) throws SignUpException {
		

		
		Country contry=new Country();
		City city=new City();
		State state=new State();
		Address address=new Address();
		
		try {
             state=stateDao.find(signUpStep2Form.getStateId());
			
			city=cityDao.find(signUpStep2Form.getCityId());
			
			contry=countryDao.find(signUpStep2Form.getCountryId());
			
			address.setCity(city);
			address.setState(state);
			address.setCity(city);
			address.setArea(signUpStep2Form.getArea());
			address.setHouseNo(signUpStep2Form.getHouseNo());
			address.setLandMark(signUpStep2Form.getLandMark());
			address.setPinCode(signUpStep2Form.getZipCode());
			address.setCountry(contry);
			
			cust.setAddress(address);
			cust.setContactNumber(signUpStep2Form.getContactNo());
			
			cust.setStatus(UserStatus.ACTIVE.toString());
			
			custDao.saveOrUpdate(cust);
			
			// Updating User
						Set<Role> roles = new HashSet<>();
						roles.add((Role) roleDao
						        .getByCriteria(roleDao.getFindByNameCriteria("CUSTOMER")));
						roles.add((Role) roleDao
						        .getByCriteria(roleDao.getFindByNameCriteria(SystemRoles.CUSTOMER)));
						roles.add((Role) roleDao.getByCriteria(
						        roleDao.getFindByNameCriteria(SystemRoles.CUSTOMER)));
						user.setRoles(roles);
						//user.setCompany(printStore);
						user.setStatus(UserStatus.ACTIVE.toString());
						userDao.update(user);
			
		} catch (Exception e) {
			
			if (e instanceof SignUpException) {
				throw (SignUpException) e;
			}
			LOGGER.error("Some error occurred while updating User or adding PrintStore. Reason : "
			        + e.getMessage(), e);
			throw new SignUpException("Error occurred while updating database!",
			        ErrorCodes.DATABASE_ERROR);
			
		}
		return user.getId();
	}

	/* private Long saveUser(SignUpStep2Form signUpStep2Form, User user)
	        throws SignUpException {
		// Saving PrintStore Entity
		PrintStore printStore = new PrintStore();
		
		Address adress=new Address();
		State state=new State();
		City city=new City();
		try {
			printStore.setStoreName(signUpStep2Form.getCompanyName());
			printStore.setAddress(signUpStep2Form.getAddress());
			printStore.setStoreContactNo(signUpStep2Form.getCompanyContactNo());
			
			state=stateDao.find(signUpStep2Form.getStateId());
			
			city=cityDao.find(signUpStep2Form.getCity());
			
			adress.setPinCode(signUpStep2Form.getZipCode());
			printStore.setStatus(CommonStatus.ACTIVE.toString());
			//printStore.setCompanyExtVideoUrl(signUpStep2Form.getCompanyExtVideoUrl());
			printStore.setStoreWebsite(signUpStep2Form.getCompanyWebsite());
			if (signUpStep2Form.getCompanyUrlForms() != null
			        && signUpStep2Form.getCompanyUrlForms().size() > 0) {
				Set<PrintStoreUrl> printStoreUrls = new HashSet<>();
				for (CompanyUrlForm companyUrlForm : signUpStep2Form.getCompanyUrlForms()) {
					PrintStoreUrl printStoreUrl = new PrintStoreUrl();
					//printStoreUrl.setCompany(printStore);
					try {
						UrlType urlType = urlTypeDao.load(companyUrlForm.getUrlTypeId());
						if (urlType == null
						        || urlType.getStatus()
						                .equalsIgnoreCase(CommonStatus.INACTIVE.toString())
						        || urlType.getStatus()
						                .equalsIgnoreCase(CommonStatus.ARCHIVED.toString())) {
							throw new SignUpException("Invalid URL Type Provided!",
							        ErrorCodes.SIGNUP_URL_TYPE_INVALID);
						}
					} catch (Exception e) {
						throw new SignUpException("Invalid URL Type Provided!",
						        ErrorCodes.SIGNUP_URL_TYPE_INVALID);
					}
					printStoreUrl.setUrlType(urlTypeDao.load(companyUrlForm.getUrlTypeId()));
					printStoreUrl.setUrl(companyUrlForm.getUrl());
					printStoreUrl.setStatus(CommonStatus.ACTIVE.toString());
					printStoreUrls.add(printStoreUrl);
				}
				printStore.setPrintStoreUrls((printStoreUrls));
			}

			// Updating User
			Set<Role> roles = new HashSet<>();
			roles.add((Role) roleDao
			        .getByCriteria(roleDao.getFindByNameCriteria("ROLE_COMPANY_ADMIN")));
			roles.add((Role) roleDao
			        .getByCriteria(roleDao.getFindByNameCriteria(SystemRoles.ADMIN)));
			roles.add((Role) roleDao.getByCriteria(
			        roleDao.getFindByNameCriteria(SystemRoles.ADMIN)));
			user.setRoles(roles);
			//user.setCompany(printStore);
			user.setStatus(UserStatus.ACTIVE.toString());
			userDao.update(user);
			//printStore = user.getCompany();

		} catch (Exception e) {
			if (e instanceof SignUpException) {
				throw (SignUpException) e;
			}
			LOGGER.error("Some error occurred while updating User or adding PrintStore. Reason : "
			        + e.getMessage(), e);
			throw new SignUpException("Error occurred while updating database!",
			        ErrorCodes.DATABASE_ERROR);
		}
		return printStore.getId();
	}*/
	
	private Employee validateEmployee(SignUpStep2Form signUpStep2Form)throws SignUpException{
		Employee emp=null;
        String email = PasswordUtils.decode(signUpStep2Form.getEmailToken());
		
		if (!ValidationUtils.validateEmail(email)) {
			throw new SignUpException("Please provide valid Email.", ErrorCodes.VALIDATION_ERROR);
		}
		
		try {
			emp=(Employee)empDao.getByCriteria(empDao.getFindByEmailCriteria(email));
			if (emp == null) {
				throw new SignUpException("No customer found with this Email",
				        ErrorCodes.USER_NOT_FOUND_ERROR);
			}
		} catch (InstanceNotFoundException e) {
			LOGGER.error("No user found with this Email", e);
			throw new SignUpException("No Employee found with this Email",
			        ErrorCodes.EMPLOYEE_NOT_FOUND_ERROR);
		}
		
		return emp;
		
		
		
	}
	
	private Customer validateCustomer(SignUpStep2Form signUpStep2Form) throws SignUpException{
		
		Customer cust=null;
		String email = PasswordUtils.decode(signUpStep2Form.getEmailToken());
		
		if (!ValidationUtils.validateEmail(email)) {
			throw new SignUpException("Please provide valid Email.", ErrorCodes.VALIDATION_ERROR);
		}
		
		try {
			cust=(Customer)custDao.getByCriteria(custDao.getFindByEmailCriteria(email));
			if (cust == null) {
				throw new SignUpException("No customer found with this Email",
				        ErrorCodes.USER_NOT_FOUND_ERROR);
			}
		} catch (InstanceNotFoundException e) {
			LOGGER.error("No user found with this Email", e);
			throw new SignUpException("No Customer found with this Email",
			        ErrorCodes.CUSTOMER_NOT_FOUND_ERROR);
		}
		
		return cust;
		
		
	}
	
	

	private User validateCompleteSignUpRequest(SignUpStep2Form signUpStep2Form)
	        throws SignUpException {
		User user = null;
		String email = PasswordUtils.decode(signUpStep2Form.getEmailToken());
		if (!ValidationUtils.validateEmail(email)) {
			throw new SignUpException("Please provide valid Email.", ErrorCodes.VALIDATION_ERROR);
		}
		try {
			user = (User) userDao.getByCriteria(userDao.getFindByEmailCriteria(email));
			if (user == null) {
				throw new SignUpException("No user found with this Email",
				        ErrorCodes.USER_NOT_FOUND_ERROR);
			}
			UserStatus status = UserStatus.valueOf(user.getStatus());
			switch (status) {

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

		} catch (InstanceNotFoundException e) {
			LOGGER.error("No user found with this Email", e);
			throw new SignUpException("No user found with this Email",
			        ErrorCodes.USER_NOT_FOUND_ERROR);
		}
		return user;
	}

	@Override
	@Transactional
	public void uploadFile(Long companyId, String fileType, MultipartFile file)
	        throws CompanyFileUploadException {
		// Validating Request
		validateUploadFileInput(fileType);

		try {
			PrintStore printStore = printStoreDao.find(companyId);
			if (printStore.getStatus().equalsIgnoreCase(CommonStatus.ARCHIVED.toString())
			        || printStore.getStatus().equalsIgnoreCase(CommonStatus.INACTIVE.toString())) {
				throw new CompanyFileUploadException("No active company found with this ID!",
				        ErrorCodes.SIGNUP_COMPANY_NOT_ACTIVE);
			}
			String companyFileName = file.getOriginalFilename();
			String companyFormattedName = printStore.getStoreName().trim().replaceAll(" +", "_");
			String companyRelativePath = "assessment_files" + File.separator + "company"
			      //  + File.separator + companyFormattedName + "_" + printStore.getId()
			        + File.separator;
			String outputFileName = companyFormattedName + "_" + fileType.trim().toUpperCase()
			        + companyFileName.substring(companyFileName.lastIndexOf("."));
			LOGGER.debug("companyRelativePath : " + companyRelativePath);
			LOGGER.debug("logoOutputFileName : " + outputFileName);
			switch (fileType.toUpperCase()) {
			case "LOGO":
				//printStore.setCompanylogoPath(companyRelativePath + outputFileName);
				break;
			case "VIDEO":
				//printStore.setCompanyVideoPath(companyRelativePath + outputFileName);
			default:
				throw new CompanyFileUploadException("Invalid File Path!",
				        ErrorCodes.COMPANY_FILE_UPLOAD_FILE_TYPE_INVALID);
			}
			FileUtils.uploadFile(BASE_UPLOAD_PATH + File.separator + companyRelativePath,
			        outputFileName, file);
			printStoreDao.update(printStore);
		} catch (InstanceNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
			throw new CompanyFileUploadException("No PrintStore found with this ID!",
			        ErrorCodes.SIGNUP_COMPANY_NOT_FOUND);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new CompanyFileUploadException("Some error occurred while uploading file!",
			        ErrorCodes.SIGNUP_COMPANY_FILE_UPLOAD_FAIL);
		}
	}

	private void validateUploadFileInput(String fileType) throws CompanyFileUploadException {
		if (fileType == null || fileType.trim().length() <= 0) {
			throw new CompanyFileUploadException("File type is required!",
			        ErrorCodes.COMPANY_FILE_UPLOAD_FILE_TYPE_EMPTY);
		} else if (!fileType.equalsIgnoreCase("logo") && !fileType.equals("video")) {
			throw new CompanyFileUploadException("Invalid File Path!",
			        ErrorCodes.COMPANY_FILE_UPLOAD_FILE_TYPE_INVALID);
		}
	}
}
