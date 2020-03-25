package com.ms.msemployees.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ms.msemployees.dtos.EmployeeDTO;
import com.ms.msemployees.exceptions.ResourceNotFoundException;
import com.ms.msemployees.mappers.EmployeeMapper;
import com.ms.msemployees.models.Employee;
import com.ms.msemployees.services.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "Employees MS", description = "Microservice For Employees Management")
@RequestMapping(value="/api")
@Validated
public class EmployeeController {

	@Autowired
	EmployeeService empservice;
	
	
	@ApiOperation(value = "View a list of available employees", response = Iterable.class)
	@GetMapping(value="/employees")
	List<Employee> getAll(){
		return empservice.getAllEmployees();
	}
	
	@ApiOperation(value = "View employee details by ID", response = Employee.class)
	@GetMapping(value="/employee/{id}")
	ResponseEntity<Employee> getById(@ApiParam(value = "Employee ID") @PathVariable("id") @Min(1) int id) {
		
		Employee emp = empservice.findById(id)
				                 .orElseThrow(()->new ResourceNotFoundException("Employee Not Found with ID :"+id));
		
		return ResponseEntity.ok().body(emp);
	}
	
	@ApiOperation(value = "Add new Employee")
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value="/employees")
	ResponseEntity<?> addEmployee(@ApiParam(value = "Employee DTO object") @Valid @RequestBody EmployeeDTO empdto) {
		Employee emp      = EmployeeMapper.dtoToEntity(empdto);
		Employee addedemp = empservice.save(emp);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						                .path("/{id}")
						                .buildAndExpand(addedemp.getId())
						                .toUri();
		return ResponseEntity.created(location).build();
	}
	
	@ApiOperation(value = "Update Employee by ID")
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value="/employee/{id}")
	ResponseEntity<Employee> updateEmployee(@ApiParam(value = "Employee ID") @PathVariable("id")  @Min(1) int id, @ApiParam(value = "Employee DTO object") @Valid @RequestBody EmployeeDTO empdto) {

		Employee emp = empservice.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee Not Found with ID :"+id));
		
		Employee empu = EmployeeMapper.dtoToEntity(empdto);
		empu.setId(emp.getId());
		empservice.save(empu);
		return ResponseEntity.ok().body(empu);
		
	}
	
	@ApiOperation(value = "Delete Employee by ID")
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value="/employee/{id}")
	ResponseEntity<String> deleteEmployee(@ApiParam(value = "Employee ID") @PathVariable("id") @Min(1) int id) {
		Employee emp = empservice.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee Not Found with ID :"+id));
		
		empservice.delete(emp.getId());
		return ResponseEntity.ok().body("Employee deleted with success!");
		
	}

}
