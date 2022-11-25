package com.restapi.springbootrestapi.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {
	
	private String name;
	private String department;
	
	
	
	
	
	
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	
	

}
