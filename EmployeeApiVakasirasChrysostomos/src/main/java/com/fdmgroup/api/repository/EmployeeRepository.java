package com.fdmgroup.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.api.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> findByFirstName(String firstName);

	Optional<Employee> findByLastName(String lastName);

}
