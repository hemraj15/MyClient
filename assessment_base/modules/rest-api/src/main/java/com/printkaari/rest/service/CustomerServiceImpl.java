package com.printkaari.rest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.printkaari.data.dao.CustomerDao;
import com.printkaari.data.dao.OrderDao;
import com.printkaari.data.dao.entity.Customer;
import com.printkaari.data.dto.CustomerDto;
import com.printkaari.data.dto.OrderDto;
import com.printkaari.rest.constant.CommonStatus;
import com.printkaari.rest.constant.ErrorCodes;
import com.printkaari.rest.exception.DatabaseException;


@Service
public class CustomerServiceImpl implements CustomerService {

	private Logger			LOGGER	= LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerDao	customerDao;
	
	@Autowired
	private OrderDao orderDao;

	@Override
	@Transactional
	public List<CustomerDto> fetchAllCandidatesByModifiedDate(Integer records) throws DatabaseException {
		List<CustomerDto> customerDtos = null;
		Integer toIndex = records;

		try {
			customerDtos = customerDao.fetchAllCandidatesByModifiedDate(0, toIndex,
			        CommonStatus.ACTIVE.toString());

		} 
		catch (Exception e) {
			   LOGGER.error("Error occured while getting candidate list through database", e);
			   throw new DatabaseException("Error occured while getting candidate list through database",
			           ErrorCodes.DATABASE_ERROR);
			  }
		return customerDtos;

	}

	@Override
	@Transactional
	public Object fetchAllOrdersByCustomerId(Long customerId) throws DatabaseException {
		
		//Customer customer = null;
		
		List<OrderDto> orderDtos=null;
		
		try {
			
			orderDtos=orderDao.fetchAllOrdersByCustomerId(customerId);
			//customer=customerDao.find(customerId);
		} catch (Exception e) {
			   LOGGER.error("Error occured while getting candidate list through database", e);
			   throw new DatabaseException("Error occured while getting all orders for a customer through database",
			           ErrorCodes.DATABASE_ERROR);
			  }
		
		return orderDtos;
	}

}
