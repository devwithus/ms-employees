package com.ms.msemployees.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.msemployees.models.Employee;
import com.ms.msemployees.repositories.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository emprepo;

	public EmployeeServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return emprepo.findAll();
	}

	@Override
	public Optional<Employee> findByName(String name) {
		// TODO Auto-generated method stub
		return emprepo.findByName(name);
	}

	@Override
	public Optional<Employee> findByEmail(String email) {
		// TODO Auto-generated method stub
		return emprepo.findByEmail(email);
	}

	@Override
	public Optional<Employee> findById(int id) {
		// TODO Auto-generated method stub
		return emprepo.findById(id);
	}

	@Override
	public Employee save(Employee emp) {
		// TODO Auto-generated method stub
		return emprepo.save(emp);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		emprepo.deleteById(id);
	}

}
