package com.ms.msemployees.mappers;

import com.ms.msemployees.dtos.EmployeeDTO;
import com.ms.msemployees.dtos.UEmployeeDTO;
import com.ms.msemployees.models.Employee;

public class EmployeeMapper {

	public static EmployeeDTO entityTodto(Employee emp) {
		return new EmployeeDTO()
					.setName(emp.getName())
					.setEmail(emp.getEmail());
	}
	
	public static Employee dtoToEntity(EmployeeDTO empdto) {
		return new Employee()
					.setName(empdto.getName())
					.setEmail(empdto.getEmail());
	}
	
	public static Employee udtoToEntity(UEmployeeDTO empdto) {
		return new Employee()
					.setName(empdto.getName())
					.setEmail(empdto.getEmail());
	}

}
