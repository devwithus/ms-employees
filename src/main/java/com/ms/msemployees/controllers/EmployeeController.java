package com.ms.msemployees.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping(value="/api")
public class EmployeeController {

	@Autowired
	EmployeeService empservice;
	
	@GetMapping(value="/employees")
	List<Employee> getAll(){
		return empservice.getAllEmployees();
	}
	
	@GetMapping(value="/employee/{id}")
	ResponseEntity<Employee> getById(@PathVariable("id") int id) {
		
		Employee emp = empservice.findById(id)
				                 .orElseThrow(()->new ResourceNotFoundException("Employee Not Found with ID :"+id));
		
		return ResponseEntity.ok().body(emp);
	}
	
	@PostMapping(value="/employees")
	ResponseEntity<?> addEmployee(@RequestBody EmployeeDTO empdto) {
		Employee emp = EmployeeMapper.dtoToEntity(empdto);
		Employee addedemp = empservice.save(emp);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						                .path("/{id}")
						                .buildAndExpand(addedemp.getId())
						                .toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(value="/employee/{id}")
	ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id, @RequestBody EmployeeDTO empdto) {

		Employee emp = empservice.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee Not Found with ID :"+id));
		
		Employee empu = EmployeeMapper.dtoToEntity(empdto);
		empu.setId(emp.getId());
		empservice.save(empu);
		return ResponseEntity.ok().body(empu);
		
	}
	
	@DeleteMapping(value="/employee/{id}")
	ResponseEntity<String> deleteEmployee(@PathVariable("id") int id) {
		Employee emp = empservice.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee Not Found with ID :"+id));
		
		empservice.delete(emp.getId());
		return ResponseEntity.ok().body("Employee deleted with success!");
		
	}

}
