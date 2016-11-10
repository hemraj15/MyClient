package com.printkaari.rest.service;

import java.util.List;

import com.printkaari.data.dao.entity.PrintStore;
import com.printkaari.data.exception.InstanceNotFoundException;

public interface TestService {

	List<PrintStore> fetchAllCompanies();

	Long createEntity();

	void updateEntity(Long id) throws InstanceNotFoundException;

}
