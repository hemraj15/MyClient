/**
 * 
 */
package com.printkaari.rest.service;

import org.springframework.web.multipart.MultipartFile;

import com.printkaari.rest.exception.CompanyFileUploadException;
import com.printkaari.rest.exception.SignUpException;
import com.printkaari.rest.form.SignUpStep2Form;

/**
 * @author Hemraj
 *
 */
public interface PrintStoreService {

	String completeSignup(SignUpStep2Form SignUpStep2Form) throws SignUpException;

	void uploadFile(Long companyId, String fileType, MultipartFile file)
	        throws CompanyFileUploadException;

}
