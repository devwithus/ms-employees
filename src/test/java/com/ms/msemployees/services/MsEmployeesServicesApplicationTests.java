package com.ms.msemployees.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ms.msemployees.models.Employee;
import com.ms.msemployees.repositories.EmployeeRepository;

@ActiveProfiles("default")
@RunWith(SpringRunner.class)
@SpringBootTest
public class MsEmployeesServicesApplicationTests {

	@Mock
	private EmployeeRepository emprepo;
	
	@InjectMocks
	private EmployeeServiceImpl empserv;
	
	@Test
    	public void whenFindAll()
    	{
        	List<Employee> list = new ArrayList<Employee>();
		Employee empOne   = new Employee(1, "John", "howtodoinjava@gmail.com");
		Employee empTwo   = new Employee(2, "Alex", "alexk@yahoo.com");
		Employee empThree = new Employee(3, "Steve", "swaugh@gmail.com");

		list.add(empOne);
		list.add(empTwo);
		list.add(empThree);

		Mockito.when(emprepo.findAll()).thenReturn(list);

		//test
		List<Employee> empList = empserv.getAllEmployees();

		assertThat(empList.size(),equalTo(3));

   	 }
	
	@Test
   	 public void whenFindById()
    	{
        	Employee empOne   = new Employee(1, "John", "howtodoinjava@gmail.com");
       		 Mockito.when(emprepo.findById(1)).thenReturn(Optional.of(empOne));
         
        	//test
        	Optional<Employee> emp = empserv.findById(1);
         
        	assertThat(emp.get().getEmail(),equalTo(empOne.getEmail()));

    	}
	
	

}
