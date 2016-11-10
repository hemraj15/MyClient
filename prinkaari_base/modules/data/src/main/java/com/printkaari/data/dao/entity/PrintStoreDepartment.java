/**
 * 
 */
package com.printkaari.data.dao.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Hemraj
 *
 */
@Entity
@Table(name = "printstore_department")
public class PrintStoreDepartment extends PrintkaariBaseEntity {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3498103970963330753L;

	private String				name;
	private PrintStore			printStore;

	@Column(name = "department_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = PrintStore.class)
	@JoinColumn(name = "store_id")
	public PrintStore getPrintStore() {
		return printStore;
	}

	public void setPrintStore(PrintStore printStore) {
		this.printStore = printStore;
	}

}
