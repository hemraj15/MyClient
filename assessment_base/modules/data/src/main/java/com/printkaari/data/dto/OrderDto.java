/**
 * 
 */
package com.printkaari.data.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Hemraj
 *
 */

@JsonInclude(Include.NON_NULL)
public class OrderDto {

	private Long			id;
	private Date			dateCreated;
	protected Date			dateUpdated;
	private String			createdBy;
	private String			lastModified;
	private String			status;
	private Double			orderPrice;
	private Set<ProductDto>	productDto	= new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Set<ProductDto> getProductDto() {
		return productDto;
	}

	public void setProductDto(Set<ProductDto> productDto) {
		this.productDto = productDto;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderDto [id=");
		builder.append(id);
		builder.append(", dateCreated=");
		builder.append(dateCreated);
		builder.append(", dateUpdated=");
		builder.append(dateUpdated);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", lastModified=");
		builder.append(lastModified);
		builder.append(", status=");
		builder.append(status);
		builder.append(", orderPrice=");
		builder.append(orderPrice);
		builder.append("]");
		return builder.toString();
	}

}
