package com.printkaari.rest.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.printkaari.rest.constant.ErrorCodes;

public class ResetPasswordForm {

	@NotNull(message = ErrorCodes.FORGOT_PASSWORD_EMAILTOKEN_NULL)
	@NotBlank(message = ErrorCodes.FORGOT_PASSWORD_EMAILTOKEN_EMPTY)
	String	emailToken;

	@NotNull(message = ErrorCodes.RESET_PASSWORD_NULL)
	@NotEmpty(message = ErrorCodes.RESET_PASSWORD_EMPTY)
	@Length(max = 25, min = 8, message = ErrorCodes.RESET_PASSWORD_INVALID)
	String	newPassword;

	public String getEmailToken() {
		return emailToken;
	}

	public void setEmailToken(String emailToken) {
		this.emailToken = emailToken;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
