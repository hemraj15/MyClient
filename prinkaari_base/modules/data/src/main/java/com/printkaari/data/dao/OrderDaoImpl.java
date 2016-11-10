/**
 * 
 */
package com.printkaari.data.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.printkaari.data.dao.entity.Order;
import com.printkaari.data.dto.OrderDto;

/**
 * @author Hemraj
 *
 */
@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order, Long> implements OrderDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDto> fetchAllOrdersByCustomerId(Long customerId) {
	
		List<OrderDto> dtos=null;
		//getCriteria().createAlias
		Criteria crit=getCriteria().createAlias("products","product").createAlias("customer","cust");
		 crit.setProjection(Projections.projectionList().add(Projections.property("id"),"id")
		.add(Projections.property("dateCreated"),"dateCreated")
		 .add(Projections.property("dateUpdated"),"dateUpdated")
		 .add(Projections.property("lastModifiedBy"),"lastModified")
		 .add(Projections.property("status"),"status")
		 .add(Projections.property("orderPrice"),"orderPrice")
		 .add(Projections.property("product.id"),"id")
		 .add(Projections.property("product.name"),"name"))
		 .add(Restrictions.eq("cust.id", customerId))
	     .setResultTransformer(Transformers.aliasToBean(OrderDto.class));// here is the priblem
		 
		
		 
		 System.out.println("order dto list for customer ----- > "+crit.list());
		 dtos=crit.list();
		
		System.out.println("order dto list for customer "+customerId);
		return dtos;
	}

}
