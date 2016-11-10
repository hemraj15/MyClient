package com.printkaari.rest.constant;

public class ErrorCodes {

	private ErrorCodes() {
		// To stop instantiation of this constant class
	}

	public final static String	VALIDATION_ERROR						= "validation_error";

	public final static String	SIGNUP_EMAIL_EMPTY						= "signup_email_empty_error";

	public final static String	SIGNUP_EMAIL_NULL						= "signup_email_null_error";

	public final static String	SIGNUP_EMAIL_INVALID					= "signup_email_invalid_error";

	public final static String	DATABASE_ERROR							= "database_error";

	public final static String	EMAIL_ERROR								= "email_error";

	public final static String	SERVER_ERROR							= "server_error";

	public final static String	USER_NOT_FOUND_ERROR					= "user_not_found_error";

	public final static String	SIGNUP_FIRST_NAME_EMPTY					= "signup_firstname_empty_error";

	public final static String	SIGNUP_FIRST_NAME_NULL					= "signup_firstname_null_error";

	public final static String	SIGNUP_LAST_NAME_EMPTY					= "signup_lastname_empty_error";

	public final static String	SIGNUP_LAST_NAME_NULL					= "signup_lastname_null_error";

	public final static String	SIGNUP_PASSWORD_EMPTY					= "signup_password_empty_error";

	public final static String	SIGNUP_PASSWORD_NULL					= "signup_password_null_error";

	public final static String	SIGNUP_PASSWORD_INVALID					= "signup_password_invalid_error";

	public static final String	SIGNUP_ALREADY_INITIATED				= "signup_already_initiated_error";

	public static final String	SIGNUP_ALREADY_ACTIVE					= "signup_already_active_error";

	public static final String	SIGNUP_ACCOUNT_DEACTIVATED				= "signup_account_deactivated_error";

	public final static String	SIGNUP_COMPANY_NAME_EMPTY				= "signup_company_name_empty_error";

	public final static String	SIGNUP_COMPANY_NAME_NULL				= "signup_company_name_null_error";

	public final static String	SIGNUP_COMPANY_ADDRESS_EMPTY			= "signup_company_address_empty_error";

	public final static String	SIGNUP_COMPANY_ADDRESS_NULL				= "signup_company_address_null_error";

	public final static String	SIGNUP_COMPANY_COUNTRY_EMPTY			= "signup_company_country_empty_error";

	public final static String	SIGNUP_COMPANY_COUNTRY_NULL				= "signup_company_country_null_error";

	public final static String	SIGNUP_COMPANY_STATE_EMPTY				= "signup_company_state_empty_error";

	public final static String	SIGNUP_COMPANY_STATE_NULL				= "signup_company_state_null_error";

	public final static String	SIGNUP_COMPANY_CITY_EMPTY				= "signup_company_city_empty_error";

	public final static String	SIGNUP_COMPANY_CITY_NULL				= "signup_company_city_null_error";

	public final static String	SIGNUP_USER_TYPE_						= "signup_company_zipcode_empty_error";

	public final static String	SIGNUP_COMPANY_ZIPCODE_NULL				= "signup_company_zipcode_null_error";

	public final static String	SIGNUP_COMPANY_CONTACTNO_EMPTY			= "signup_company_contactno_empty_error";

	public final static String	SIGNUP_COMPANY_CONTACTNO_NULL			= "signup_company_contactno_null_error";

	public final static String	SIGNUP_COMPANY_LOGO_NULL				= "signup_company_logo_null_error";

	public static final String	SIGNUP_COMPANY_EMAILTOKEN_EMPTY			= "signup_company_emailtoken_empty_error";

	public static final String	SIGNUP_COMPANY_URLS_INVALID				= "signup_company_urls_invalid_error";

	public static final String	SIGNUP_REQUEST_NOT_MULTIPART			= "signup_request_not_multipart_error";

	public final static String	URL_LIST_EMPTY							= "url_list_empty_error";

	public static final String	COMPANY_FILE_UPLOAD_FILE_TYPE_EMPTY		= "company_file_upload_file_type_empty_error";

	public static final String	SIGNUP_COMPANY_FILE_UPLOAD_FAIL			= "signup_company_file_upload_fail";

	public static final String	SIGNUP_COMPANY_NOT_FOUND				= "signup_company_not_found";

	public static final String	SIGNUP_COMPANY_NOT_ACTIVE				= "signup_company_not_active";

	public static final String	SIGNUP_COMPANY_EMAILTOKEN_NULL			= "signup_company_emailtoken_null_error";

	public static final String	SIGNUP_URL_TYPE_INVALID					= "signup_url_type_invalid_error";

	public static final String	COMPANY_FILE_UPLOAD_FILE_TYPE_INVALID	= "company_file_upload_file_type_invalid_error";

	public final static String	FORGOT_PASSWORD_INVALID					= "forgot_password_invalid_error";

	public static final String	FORGOT_PASSWORD_EMAILTOKEN_NULL			= "forgot_password_emailtoken_null_error";

	public static final String	FORGOT_PASSWORD_EMAILTOKEN_EMPTY		= "forgot_password_emailtoken_empty_error";

	public static final String	FORGOT_PASSWORD_NULL					= "forgot_password_null_error";

	public static final String	RESET_PASSWORD_NULL						= "reset_password_null_error";

	public static final String	RESET_PASSWORD_EMPTY					= "reset_password_empty_error";

	public static final String	RESET_PASSWORD_INVALID					= "reset_password_invalid_error";

	public static final String	FORGET_PASSWORD_INITIATED				= "forgot_password_initiated_error";

	public static final String	RESET_PASSWORD_USER_ALREADY_ACTIVE		= "reset_password_user_active_error";

	public static final String	RESET_PASSWORD_ALREADY_ACTIVE			= "reset_password_user_already_active_error";

	public static final String	FORGOT_REQUEST_SIGNUP_INITIATED			= "forgot_paswword_signup_initiated_error";

	public static final String	SKILLSET_CATEGORY_LIST_EMPTY			= "skillset_category_list_empty";

	public static final String	SKILLSET_SUB_CATEGORY_LIST_EMPTY		= "skillset_sub_category_list_empty";

	public static final String	JOB_TITLE_NULL							= "job_title_null_error";

	public static final String	JOB_TITLE_EMPTY							= "job_title_empty_error";

	public static final String	JOB_USER_NULL							= "job_user_null_error";

	public static final String	JOB_USER_EMPTY							= "job_user_empty_error";

	public static final String	JOB_DATE_OF_EXPIRY_NULL					= "job_date_of_expiry_null_error";

	public static final String	JOB_DATE_OF_EXPIRY_EMPTY				= "job_date_of_expiry_empty_error";

	public static final String	JOB_CATEGORY_NULL						= "job_category_null_error";

	public static final String	JOB_CATEGORY_EMPTY						= "job_category_empty_error";

	public static final String	JOB_SKILL_SET_NULL						= "job_skill_set_null_error";

	public static final String	JOB_SKILL_SET_EMPTY						= "job_skill_set_empty_error";

	public static final String	JOB_FROM_EXPERIENCE_NULL				= "job_from_experience_null_error";

	public static final String	JOB_FROM_EXPERIENCE_EMPTY				= "job_from_experience_empty_error";

	public static final String	JOB_TO_EXPERIENCE_NULL					= "job_to_experience_null_error";

	public static final String	JOB_TO_EXPERIENCE_EMPTY					= "job_to_experience_empty_error";

	public static final String	JOB_DESCRIPTION_NULL					= "job_description_null_error";

	public static final String	JOB_DESCRIPTION_EMPTY					= "job_description_empty_error";

	public static final String	JOB_COUNTRY_NULL						= "job_country_null_error";

	public static final String	JOB_COUNTRY_EMPTY						= "job_country_empty_error";

	public static final String	JOB_STATE_NULL							= "job_state_null_error";

	public static final String	JOB_STATE_EMPTY							= "job_state_empty_error";

	public static final String	JOB_CITY_NULL							= "job_city_null_error";

	public static final String	JOB_CITY_EMPTY							= "job_city_empty_error";

	public static final String	RECRUIERS_LIST_EMPTY					= "recruiter_list_empty_error";

	public static final String	COUNTRY_LIST_EMPTY						= "country_list_exception";

	public static final String	JOB_LIST_EMPTY							= "job_list_empty";

	public static final String	CANDIDATE_LIST_EMPTY					= "candidate_list_empty";

	public static final String	ONDEMAND_INTERVIEW_LIST_EMPTY			= "ondemand_interview_list_empty";

	public static final String	LIVE_INTERVIEW_LIST_EMPTY				= "live_interview_list_empty";

	public static final String	USER_TYPE_NULL							= "user_type_null";

	public static final String	USER_TYPE_EMPTY							= "user_type_empty";

	public static final String	SIGNUP_ZIPCODE_EMPTY					= "signup_zipcode_empty";

	public static final String	EMPLOYEE_NOT_FOUND_ERROR				= "employee_not_found_error";
	public static final String	CUSTOMER_NOT_FOUND_ERROR				= "customer_not_found_error";

}
