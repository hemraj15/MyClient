package com.printkaari.data.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.printkaari.data.dao.entity.City;
import com.printkaari.data.dto.CityDto;
import com.printkaari.data.dto.StateDto;

@Repository
public class CityDaoImpl extends GenericDaoImpl<City, Long> implements CityDao {

	private Logger LOGGER = LoggerFactory.getLogger(CityDaoImpl.class);

	@Override
	public List<CityDto> getAllCitiesByState(Long stateId) {
		LOGGER.info(">> getCityListDTOs");
		List<CityDto> cityDtos = null;
		Criteria criteria = getCriteria().add(Restrictions.eq("state.id", stateId))
		        .setProjection(Projections.projectionList().add(Projections.property("id"), "id")
		                .add(Projections.property("name"), "name"))
		        .setResultTransformer(Transformers.aliasToBean(StateDto.class));
		cityDtos = criteria.list();
		LOGGER.debug("<< getCityListDTOs" + cityDtos.toString());
		return cityDtos;
	}

}
