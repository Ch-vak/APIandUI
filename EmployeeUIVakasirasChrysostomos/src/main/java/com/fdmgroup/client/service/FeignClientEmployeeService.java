package com.fdmgroup.client.service;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fdmgroup.client.model.Employee;

@Primary
@FeignClient(name = "EMPLOYEE-API" , path= "/api/v1/employees")
@LoadBalancerClient( name = "EMPLOYEE-API")
public interface FeignClientEmployeeService  extends RestEmployeeService{

	@Override
	@GetMapping
	public List<Employee> getAllEmployees();
	
	@Override
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable(value = "id") long id);

	@Override
	@PostMapping
	public Employee addEmployee(@RequestBody Employee emp);
	
	@PostMapping
	public Employee addEmployeeSubmit(@RequestBody Employee emp);
	
	@Override
	@PutMapping("/{id}")
	public Employee updateEmployee(@RequestBody Employee emp,@PathVariable(value = "id") long id);
	
	
	@Override
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable(value = "id") long id);
}
