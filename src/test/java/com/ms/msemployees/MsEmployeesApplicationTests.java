package com.ms.msemployees;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ms.msemployees.controllers.EmployeeController;
import com.ms.msemployees.models.Employee;
import com.ms.msemployees.services.EmployeeServiceImpl;


@ActiveProfiles("default")
@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class MsEmployeesApplicationTests {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	EmployeeServiceImpl empsrv;
	
	@Test
	public void whenFindById() throws Exception {
	  
		Employee empOne   = new Employee(1, "John", "john-depp@gmail.com");
        Mockito.when(empsrv.findById(1)).thenReturn(Optional.of(empOne));
        
        mvc.perform(
        		get("/api/employee/1")
        		.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		.andExpect(content().json("{ 'name': 'John' }"));
		
	}
	
	@Test
	public void whenFindByIdv2() throws Exception {
	  
		Employee empOne   = new Employee(1, "John", "john-depp@gmail.com");
        Mockito.when(empsrv.findById(3)).thenReturn(Optional.of(empOne));
        
        mvc.perform(
        		get("/api/employee/3")
        		.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.name",is(empOne.getName())));
		
	}

}
