package com.printkaari.data.dao;

import org.hibernate.Criteria;

import com.printkaari.data.dao.entity.UrlType;

public interface UrlTypeDao extends GenericDao<UrlType, Long> {
	Criteria getListByStatusCriteria(String status);

}
