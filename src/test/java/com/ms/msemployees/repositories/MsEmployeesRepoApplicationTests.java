package com.ms.msemployees.repositories;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ms.msemployees.models.Employee;


@ActiveProfiles("default")
@RunWith(SpringRunner.class)
@SpringBootTest
public class MsEmployeesRepoApplicationTests {

	@Autowired
	private EmployeeRepository emprepo;
	
	@Test
	public void whenFindAll() {
       
       //when
       List<Employee> emps = emprepo.findAll();

       //then
       assertThat(emps.size(),equalTo(4));
	}
	
	@Test
	public void whenFindById() {
       
       //when
       Optional<Employee> emp = emprepo.findById(3);
       
       //then
       assertThat(emp.get().getName(),equalTo("Nicole"));
	}


}
