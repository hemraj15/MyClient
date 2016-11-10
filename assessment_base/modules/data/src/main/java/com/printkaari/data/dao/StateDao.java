package com.printkaari.data.dao;

import java.util.List;

import com.printkaari.data.dao.entity.State;
import com.printkaari.data.dto.StateDto;

public interface StateDao extends GenericDao<State, Long> {
	List<StateDto> getAllStatesByCountry(Long companyId);

}
