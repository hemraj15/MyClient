package com.printkaari.data.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Hemraj
 * 
 * */

@JsonInclude(Include.NON_NULL)
public class CountryDto {
private Integer id;
	
	private String name;
	
	private List<StateDto> states=new ArrayList<>();
	
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StateDto> getStateDtos() {
		return states;
	}

	public void setStateDtos(List<StateDto> states) {
		this.states = states;
	}

}
