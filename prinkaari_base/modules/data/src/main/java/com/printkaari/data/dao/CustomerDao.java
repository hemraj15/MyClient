package com.printkaari.data.dao;

import java.util.List;

import org.hibernate.Criteria;

import com.printkaari.data.dao.entity.Customer;
import com.printkaari.data.dto.CustomerDto;

public interface CustomerDao extends GenericDao<Customer, Long> {

	List<CustomerDto> fetchAllCandidatesByModifiedDate(Integer fromIndex, Integer toIndex,
	        String status);

	Criteria getFindByEmailCriteria(String emailToken);

	CustomerDto fetchAllOrdersByCustomerId(Long customerId);

}
