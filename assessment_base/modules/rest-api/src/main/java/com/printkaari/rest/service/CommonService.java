/**
 * 
 */
package com.printkaari.rest.service;

import java.util.List;

import com.printkaari.data.dao.entity.UrlType;

/**
 * @author Developer
 *
 */
public interface CommonService {

	List<UrlType> getActiveUrlTypesCriteria();

}
