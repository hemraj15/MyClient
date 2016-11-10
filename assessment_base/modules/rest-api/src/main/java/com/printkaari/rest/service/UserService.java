package com.printkaari.rest.service;

import java.util.List;

import com.printkaari.data.dto.UserDto;
import com.printkaari.data.exception.InstanceNotFoundException;
import com.printkaari.message.exception.MailNotSentException;
import com.printkaari.rest.exception.EmptyListException;
import com.printkaari.rest.exception.PasswordException;
import com.printkaari.rest.exception.SignUpException;
import com.printkaari.rest.form.ResetPasswordForm;
import com.printkaari.rest.form.SignUpStep1Form;

public interface UserService {

	void initiateSignUp(SignUpStep1Form signUpstep1Form)
	        throws MailNotSentException, SignUpException;

	void resendEmail(String email) throws SignUpException;

	void sendForgotPasswordLink(String emailId) throws PasswordException, MailNotSentException;

	void resetPassword(ResetPasswordForm resetPasswordForm) throws PasswordException;

	List<UserDto> recruiterDTOList() throws EmptyListException;

	String loginUser(String token, String email) throws InstanceNotFoundException;
}
