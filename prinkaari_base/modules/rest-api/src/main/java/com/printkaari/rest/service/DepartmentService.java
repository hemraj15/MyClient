/**
 * 
 */
package com.printkaari.rest.service;

import java.util.List;

import com.printkaari.data.dto.DepartmentDto;
import com.printkaari.rest.exception.DatabaseException;

/**
 * @author Hemraj
 *
 */
public interface DepartmentService {

	List<DepartmentDto> getDepartmentListByCompanyId(Long companyId) throws DatabaseException;

}
