/**
 * 
 */
package com.printkaari.data.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Hemraj
 *
 */

@Entity(name = "url_type")
public class UrlType extends PrintkaariBaseEntity {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -4001090034029840972L;

	private String				urlTypeName;
	private String				description;

	@Column(name = "url_type_name", length = 25)
	public String getUrlTypeName() {
		return urlTypeName;
	}

	public void setUrlTypeName(String urlTypeName) {
		this.urlTypeName = urlTypeName;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
