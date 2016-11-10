package com.printkaari.data.dao;

import java.util.List;

import com.printkaari.data.dao.entity.City;
import com.printkaari.data.dto.CityDto;

public interface CityDao extends GenericDao<City, Long> {
	List<CityDto> getAllCitiesByState(Long stateId);

}
