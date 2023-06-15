package com.fdmgroup.client.service;

import java.util.List;

import com.fdmgroup.client.model.Employee;

public interface RestEmployeeService {

	Employee addEmployee(Employee book);

	Employee getEmployeeById(long id);

	Employee updateEmployee(Employee book, long id);

	void deleteEmployee(long id);

	List<Employee> getAllEmployees();

}