package com.fdmgroup.api.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fdmgroup.api.model.Employee;
import com.fdmgroup.api.repository.EmployeeRepository;
import com.fdmgroup.api.service.EmployeeService;

@WebMvcTest
public class EmployeeControllerTest {

	@Autowired
	MockMvc mockMvc;
	
//	@Autowired
//	ObjectMapper mapper;
	
	@MockBean 
	EmployeeRepository repo;
	
	@MockBean 
	EmployeeService service;
	
	Employee EMP_1 = new Employee("dummyFirstName1","dummyLastName1", new BigDecimal(1050.00) , "dummyState1","dummyCountry1");
	Employee EMP_2 = new Employee("dummyFirstName2","dummyLastName2", new BigDecimal(1050.00) , "dummyState2","dummyCountry2");
	
	@Test
	public void getAllEmployees_sucess() throws Exception {
	    List<Employee> emps = new ArrayList<>(Arrays.asList(EMP_1, EMP_2));
	    
	    Mockito.when(service.getAllEmployees()).thenReturn(emps);
	    
	    mockMvc.perform(MockMvcRequestBuilders
	            .get("/allEmployees")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$", hasSize(2)))
	            .andExpect(jsonPath("$[2].firstName", is("dummyFirstName2")));
	}
}
