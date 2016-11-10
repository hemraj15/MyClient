/**
 * 
 */
package com.printkaari.data.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Hemraj
 *
 */

@Entity
@Table(name="cust_order")
public class Order extends PrintkaariBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8995896702674504742L;
	
	private Double orderPrice;
	private Set<Product> products=new HashSet<>();
	private Customer customer;

//@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER,targetEntity=Product.class)
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "order_products", joinColumns = {
	        @JoinColumn(name = "order_id", nullable = false, updatable = false) }, inverseJoinColumns = {
	                @JoinColumn(name = "product_id", nullable = false, updatable = false) })

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}



	@Column(name="price")
	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
