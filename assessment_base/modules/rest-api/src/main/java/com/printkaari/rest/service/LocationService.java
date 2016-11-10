package com.printkaari.rest.service;

import java.util.List;

import com.printkaari.data.dto.CityDto;
import com.printkaari.data.dto.CountryDto;
import com.printkaari.data.dto.StateDto;
import com.printkaari.data.exception.InstanceNotFoundException;
import com.printkaari.rest.exception.DatabaseException;

public interface LocationService {

	List<CountryDto> getAllCountries();

	List<StateDto> getAllStatesByCountry(Long countryId);

	List<CityDto> getAllCitiesByStates(Long stateId);

	List<CountryDto> getAllCountriesStatesByCountry(Long countryId, Long stateId)
	        throws InstanceNotFoundException, DatabaseException;

}
