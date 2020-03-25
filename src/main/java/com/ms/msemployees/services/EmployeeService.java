package com.ms.msemployees.services;

import java.util.List;
import java.util.Optional;

import com.ms.msemployees.models.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	Optional<Employee> findById(int id);
	Optional<Employee> findByName(String name);
	Optional<Employee> findByEmail(String email);
	Employee save(Employee emp);
	void update(Employee emp);
	void delete(int id);
}
