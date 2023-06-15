package com.fdmgroup.api.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdmgroup.api.controller.EmployeeControllerAdvice;
import com.fdmgroup.api.exception.EmployeeAlreadyExistException;
import com.fdmgroup.api.exception.ResourceNotFoundException;
import com.fdmgroup.api.exception.ZeroCredentialException;
import com.fdmgroup.api.model.Employee;
import com.fdmgroup.api.repository.EmployeeRepository;
/** 
 * Service gets connected to the Repository layer of the application
 * Has @CustomExceptions for customs error messages
 * 
 * @see EmployeeControllerAdvice
 * @author Chrysostomos Vakasiras
 *
 */
@Service
public class EmployeeService implements CRUDService {

	private final EmployeeRepository empRepo;

	public EmployeeService(EmployeeRepository empRepo) {
		super();
		this.empRepo = empRepo;
	}
	
	public List<Employee> getAllEmployees(){
		return empRepo.findAll();
	}
	
	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> empOpt = empRepo.findById(id);
		if(empOpt.isEmpty()) {
			throw new ResourceNotFoundException(getNotfoundMessage(id));
		}
		return empOpt.get();
 	}
	
	@Override
	public Employee addEmployee(Employee emp) { 
		if (emp.getSalary().compareTo(BigDecimal.ZERO) == 0 || "".equals(emp.getFirstName()) ||"".equals(emp.getLastName()) ) {
			throw new ZeroCredentialException("Cant have 0 or empty string values");
		}
		Optional<Employee> empOpt = empRepo.findByLastName(emp.getLastName());

		if(empOpt.isPresent()) {
			Optional<Employee> empOptF = empRepo.findByFirstName(empOpt.get().getFirstName());
			if (empOptF.isPresent()) {
				throw new EmployeeAlreadyExistException("Employee with credentials:" + emp.getFirstName() +" " + emp.getLastName() + " already exists");
			}
		}
		return empRepo.save(emp);
	}
	
	@Override
	public Employee updateEmployee(Employee emp, long id) {
		if (emp.getSalary().compareTo(BigDecimal.ZERO) == 0 || "".equals(emp.getFirstName()) ||"".equals(emp.getLastName()) ) {
			throw new ZeroCredentialException("Cant have 0 or empty string values");
		}
		Optional<Employee> empOpt = empRepo.findById(id);
		if(empOpt.isEmpty()) {
			throw new ResourceNotFoundException(getNotfoundMessage(id));
		}
		Employee empToSave = empOpt.get();
		empToSave.setId(id);
		empToSave.setFirstName(emp.getFirstName());
		empToSave.setLastName(emp.getLastName());
		empToSave.setSalary(emp.getSalary());
		return empRepo.save(empToSave);
	}
	
	@Override
	public void deleteEmployee(long id) {
		if(empRepo.findById(id).isEmpty()) {
			throw new ResourceNotFoundException(getNotfoundMessage(id));
		}
		empRepo.deleteById(id);
	}
	
	private String getNotfoundMessage(long id) {
		return "Employee with id of :"+ id +" not found.";
	}
}
