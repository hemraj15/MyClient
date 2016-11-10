/**
 * 
 */
package com.printkaari.data.dao.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Hemraj
 *
 */

@Entity
@Table(name="product_catagory")
public class ProductCatagory extends PrintkaariBaseEntity implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1142218862072129199L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
