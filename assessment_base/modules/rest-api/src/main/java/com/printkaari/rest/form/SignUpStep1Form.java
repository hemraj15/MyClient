package com.printkaari.rest.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.printkaari.rest.constant.ErrorCodes;
import com.printkaari.rest.utils.ValidationUtils;

public class SignUpStep1Form {

	@NotNull(message = ErrorCodes.SIGNUP_FIRST_NAME_NULL)
	@NotBlank(message = ErrorCodes.SIGNUP_FIRST_NAME_EMPTY)
	private String	firstName;

	@NotNull(message = ErrorCodes.SIGNUP_LAST_NAME_NULL)
	@NotBlank(message = ErrorCodes.SIGNUP_LAST_NAME_EMPTY)
	private String	lastName;

	@NotNull(message = ErrorCodes.SIGNUP_EMAIL_NULL)
	@NotBlank(message = ErrorCodes.SIGNUP_EMAIL_EMPTY)
	@Email(message = ErrorCodes.SIGNUP_EMAIL_INVALID, regexp = ValidationUtils.EMAIL_PATTERN)
	private String	email;

	@NotNull(message = ErrorCodes.SIGNUP_PASSWORD_NULL)
	@NotEmpty(message = ErrorCodes.SIGNUP_PASSWORD_EMPTY)
	@Length(max = 25, min = 8, message = ErrorCodes.SIGNUP_PASSWORD_INVALID)
	private String	password;
	
	@NotNull(message = ErrorCodes.USER_TYPE_NULL)
	@NotBlank(message = ErrorCodes.USER_TYPE_EMPTY)
	private String	userType;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	
}
