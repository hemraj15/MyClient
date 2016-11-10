package com.printkaari.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.printkaari.data.dao.CityDao;
import com.printkaari.data.dao.CountryDao;
import com.printkaari.data.dao.StateDao;
import com.printkaari.data.dao.entity.City;
import com.printkaari.data.dao.entity.Country;
import com.printkaari.data.dao.entity.State;
import com.printkaari.data.dto.CityDto;
import com.printkaari.data.dto.CountryDto;
import com.printkaari.data.dto.StateDto;
import com.printkaari.data.exception.InstanceNotFoundException;
import com.printkaari.rest.constant.ErrorCodes;
import com.printkaari.rest.exception.DatabaseException;

@Service
public class LocationServiceImpl implements LocationService {
	private Logger		LOGGER	= LoggerFactory.getLogger(LocationServiceImpl.class);

	@Autowired
	private CountryDao	countryDao;

	@Autowired
	private StateDao	stateDao;

	@Autowired
	private CityDao		cityDao;

	@Override
	@Transactional
	public List<CountryDto> getAllCountries() {

		return countryDao.getAllCountries();
	}

	@Override
	@Transactional
	public List<StateDto> getAllStatesByCountry(Long countryId) {

		return stateDao.getAllStatesByCountry(countryId);
	}

	@Override
	@Transactional
	public List<CityDto> getAllCitiesByStates(Long stateId) {

		return cityDao.getAllCitiesByState(stateId);
	}

	@Override
	@Transactional
	public List<CountryDto> getAllCountriesStatesByCountry(Long countryId, Long stateId)
	        throws InstanceNotFoundException, DatabaseException {
		LOGGER.info("<<<<<");
		List<Country> countries = countryDao.getEntities();
		List<CountryDto> countryDtos = new ArrayList<CountryDto>();
		List<StateDto> stateDtos = new ArrayList<StateDto>();
		List<CityDto> cityDtos = new ArrayList<CityDto>();

		try {
			for (Country fetchcountry : countries) {
				CountryDto countryDto = new CountryDto();
				//countryDto.setId(fetchcountry.getId());
				countryDto.setName(fetchcountry.getName());
				if (fetchcountry.getId() == countryId) {
					for (State state_new : fetchcountry.getStates()) {
						if (state_new.getId() == stateId) {
							StateDto stateDto = new StateDto();
							stateDto.setId(state_new.getId());
							stateDto.setName(state_new.getName());
							Set<City> cities = state_new.getCities();
							for (City city : cities) {
								CityDto cityDto = new CityDto();
								cityDto.setId(city.getId());
								cityDto.setName(city.getName());
								cityDtos.add(cityDto);
							}
							stateDto.setCityDtos(cityDtos);
							stateDtos.add(stateDto);

						}

					}
					countryDto.setStateDtos(stateDtos);
				}
				countryDtos.add(countryDto);
				LOGGER.debug("<<< Country Dtos :" + countryDtos);
			}

		} catch (Exception e) {
			LOGGER.error("Error occured while getting job list through database", e);
			throw new DatabaseException("Error occured while getting job list through database",
			        ErrorCodes.DATABASE_ERROR);
		}

		return countryDtos;

	}
}
