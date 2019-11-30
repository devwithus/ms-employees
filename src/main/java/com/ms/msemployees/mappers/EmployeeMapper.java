package com.ms.msemployees.mappers;

import com.ms.msemployees.dtos.EmployeeDTO;
import com.ms.msemployees.models.Employee;

public class EmployeeMapper {

	
	public static EmployeeDTO entityTodto(Employee emp) {
		return new EmployeeDTO()
					.setName(emp.getName())
					.setEmail(emp.getEmail());
	}
	

}
