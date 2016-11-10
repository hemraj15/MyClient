/**
 * 
 */
package com.printkaari.data.dao;

import java.util.List;

import com.printkaari.data.dao.entity.PrintStoreDepartment;
import com.printkaari.data.dto.DepartmentDto;

/**
 * @author Hemraj
 *
 */
public interface DepartmentDao extends GenericDao<PrintStoreDepartment, Long>{
	

	List<DepartmentDto> getDepartmentListByCompanyId(Long companyId);

}
