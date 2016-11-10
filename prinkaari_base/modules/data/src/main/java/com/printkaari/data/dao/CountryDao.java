package com.printkaari.data.dao;

import java.util.List;

import com.printkaari.data.dao.entity.Country;
import com.printkaari.data.dto.CountryDto;

public interface CountryDao extends GenericDao<Country, Long> {
	List<CountryDto> getAllCountries();

}
