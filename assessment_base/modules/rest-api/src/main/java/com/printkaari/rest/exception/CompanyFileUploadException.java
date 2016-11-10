package com.printkaari.rest.exception;

public class CompanyFileUploadException extends Exception {

	private static final long	serialVersionUID	= 1L;

	private String	          errorCode;

	public CompanyFileUploadException(String specificMessage) {
		super(specificMessage);
	}

	public CompanyFileUploadException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public CompanyFileUploadException(String specificMessage, String errorCode) {
		super(specificMessage);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
