package com.ms.msemployees.validation.validators;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.msemployees.cannotations.UniqName;
import com.ms.msemployees.models.Employee;
import com.ms.msemployees.repositories.EmployeeRepository;

@Component
public class UniqNameValidator implements ConstraintValidator<UniqName, String> {

	@Autowired
	private EmployeeRepository emprepo;
	
	public UniqNameValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		
		Optional<Employee> emp = emprepo.findByName(name);
		    
		return !emp.isPresent();
	}

}
