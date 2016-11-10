package com.printkaari.data.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.printkaari.data.dao.entity.Role;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role, Long> implements RoleDao {

	@Override
	public Criteria getFindByNameCriteria(String roleName) {
		return getCriteria().add(Restrictions.eq("name", roleName));
	}

}
