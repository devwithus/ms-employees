package com.ms.msemployees.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.msemployees.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	

}
