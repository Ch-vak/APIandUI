package com.fdmgroup.api.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empgen")
	@SequenceGenerator(name = "empgen", sequenceName = "emp_id_seq", allocationSize = 1)
	private long id;
	@NotBlank(message = "First name must not ve null or blank.")
	@Size(min=2,max=50, message= "First name must be between 2 and 50")
	private String firstName;
	@NotBlank(message = "Last name must not ve null or blank.")
	@Size(min=2,max=50, message= "Last name must be between 2 and 50")
	private String lastName;
	@DecimalMin(value ="1.0" ,message = "Salary must be greater than 1.")
	@DecimalMax(value ="500000.0" ,message = "price must be less than 500,000.")
	private BigDecimal salary;
	@NotBlank(message = "State  must not ve null or blank.")
	@Size(min=2,max=50, message= "State name must be between 2 and 50")
	private String state;
	@NotBlank(message = "Country must not ve null or blank.")
	@Size(min=2,max=50, message= "Country  must be between 2 and 50")
	private String country;
	
	
	public Employee() {
		super();
	}


	public Employee(String firstName, String lastName, BigDecimal salary, String state, String country) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.state = state;
		this.country = country;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public BigDecimal getSalary() {
		return salary;
	}


	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
