/**
 * 
 */
package com.printkaari.rest.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.printkaari.data.dao.entity.Address;
import com.printkaari.rest.constant.ErrorCodes;

/**
 * @author Hemraj
 *
 */
public class SignUpStep2Form {

	@NotNull(message = ErrorCodes.SIGNUP_COMPANY_EMAILTOKEN_NULL)
	@NotBlank(message = ErrorCodes.SIGNUP_COMPANY_EMAILTOKEN_EMPTY)
	private String	emailToken;
	

	@NotNull(message = ErrorCodes.SIGNUP_COMPANY_CONTACTNO_NULL)
	//@NotBlank(message = ErrorCodes.SIGNUP_COMPANY_CONTACTNO_EMPTY)
	private Long	contactNo;

	@NotNull(message = ErrorCodes.SIGNUP_COMPANY_COUNTRY_NULL)
	//@NotBlank(message = ErrorCodes.SIGNUP_COMPANY_COUNTRY_EMPTY)
	private Long	countryId;

	@NotNull(message = ErrorCodes.SIGNUP_COMPANY_STATE_NULL)
	//@NotBlank(message = ErrorCodes.SIGNUP_COMPANY_STATE_EMPTY)
	private Long	stateId;

	@NotNull(message = ErrorCodes.SIGNUP_COMPANY_CITY_NULL)
	//@NotBlank(message = ErrorCodes.SIGNUP_COMPANY_CITY_EMPTY)
	private Long	cityId;

	@NotNull(message = ErrorCodes.SIGNUP_COMPANY_ZIPCODE_NULL)
	//@NotBlank(message = ErrorCodes.SIGNUP_ZIPCODE_EMPTY)
	private Integer	zipCode;
	
	@NotNull(message = ErrorCodes.USER_TYPE_EMPTY)
	@NotBlank(message = ErrorCodes.USER_TYPE_NULL)
	private String userType;

	
	private String	houseNo;
	private String	StreetNo;
	private String	landMark;
	private String	area;
	public String getEmailToken() {
		return emailToken;
	}
	public void setEmailToken(String emailToken) {
		this.emailToken = emailToken;
	}
	public Long getContactNo() {
		return contactNo;
	}
	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public Integer getZipCode() {
		return zipCode;
	}
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getStreetNo() {
		return StreetNo;
	}
	public void setStreetNo(String streetNo) {
		StreetNo = streetNo;
	}
	public String getLandMark() {
		return landMark;
	}
	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SignUpStep2Form [emailToken=");
		builder.append(emailToken);
		builder.append(", contactNo=");
		builder.append(contactNo);
		builder.append(", countryId=");
		builder.append(countryId);
		builder.append(", stateId=");
		builder.append(stateId);
		builder.append(", cityId=");
		builder.append(cityId);
		builder.append(", zipCode=");
		builder.append(zipCode);
		builder.append(", userType=");
		builder.append(userType);
		builder.append(", houseNo=");
		builder.append(houseNo);
		builder.append(", StreetNo=");
		builder.append(StreetNo);
		builder.append(", landMark=");
		builder.append(landMark);
		builder.append(", area=");
		builder.append(area);
		builder.append("]");
		return builder.toString();
	}
	
	

}
