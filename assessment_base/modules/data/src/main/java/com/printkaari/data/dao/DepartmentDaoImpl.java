/**
 * 
 */
package com.printkaari.data.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.printkaari.data.dao.entity.PrintStoreDepartment;
import com.printkaari.data.dto.DepartmentDto;

/**
 * @author Hemraj
 *
 */
@Repository
public class DepartmentDaoImpl extends GenericDaoImpl<PrintStoreDepartment, Long> implements DepartmentDao{
	private Logger LOGGER = LoggerFactory.getLogger(DepartmentDaoImpl.class);

	@Override
	@Transactional
	public List<DepartmentDto> getDepartmentListByCompanyId(Long companyId) {
		List<DepartmentDto> deptDtos=null;
		Criteria criteria = getCriteria().add(Restrictions.eq("company.id", companyId))
		        .setProjection(Projections.projectionList().add(Projections.property("id"), "id")
		                .add(Projections.property("name"), "name"))
		        .setResultTransformer(Transformers.aliasToBean(DepartmentDto.class));
		deptDtos = criteria.list();
		LOGGER.debug("<< getDepartmentDto's" + deptDtos.toString());
		return deptDtos;
		
	}
}
