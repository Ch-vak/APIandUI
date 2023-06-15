package com.fdmgroup.api.service;

import com.fdmgroup.api.model.Employee;

public interface CRUDService {

	Employee getEmployeeById(long id);

	Employee addEmployee(Employee emp);

	Employee updateEmployee(Employee emp, long id);

	void deleteEmployee(long id);

}