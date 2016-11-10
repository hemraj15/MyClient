package com.printkaari.rest.service;

import java.util.List;

import com.printkaari.data.dto.CustomerDto;
import com.printkaari.rest.exception.DatabaseException;

public interface CustomerService {

	List<CustomerDto> fetchAllCandidatesByModifiedDate(Integer records) throws DatabaseException;

	Object fetchAllOrdersByCustomerId(Long customerId)throws DatabaseException;

}
