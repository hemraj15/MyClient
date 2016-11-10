package com.printkaari.data.dao;

import org.hibernate.Criteria;

import com.printkaari.data.dao.entity.Role;

public interface RoleDao extends GenericDao<Role, Long> {

	Criteria getFindByNameCriteria(String string);

}
