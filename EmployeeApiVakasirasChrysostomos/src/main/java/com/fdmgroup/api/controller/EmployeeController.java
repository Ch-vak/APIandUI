 package com.fdmgroup.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.api.model.Employee;
import com.fdmgroup.api.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

/** 
 * Rest Controller gets connected to the Service layer of the application
 * Has @operation to create custom Api responses
 * 
 * @see src/test/java for Test handling
 * @author Chrysostomos Vakasiras
 *
 */


@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController  {

	private final EmployeeService empService;

	public EmployeeController(EmployeeService empService) {
		super();
		this.empService = empService;
	}
	
	@Operation(
			summary = "Returns all emloyees",
			description = "Diplay all present in the database employees",
			method = "GET",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = " passed employees returned",
							content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }
							)
			}
	)
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees(){
		return ResponseEntity
				.ok(empService.getAllEmployees());
	}
	

	@Operation(
			summary = "returns a single Employee described by the URI",
			description = "using the id from the path searches  for the corresponding Employee "
					+ "entity and returns it as a json representation.",
			method = "GET",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Employee resource found and returned as json object",
							content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}
					),
					@ApiResponse(
							responseCode = "404",
							description = "Employee resource not found",
							content = {@Content(mediaType = MediaType.TEXT_PLAIN_VALUE)}
					)
			}
	)
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
		return ResponseEntity.ok(empService.getEmployeeById(id));
	}
	

	@Operation(
			summary = "creates a new Employee resource",
			description = "accepts and validate a employee object, then checks if the title is already present, then "
					+ "saves the employee.",
			method = "POST",
			responses = {
					@ApiResponse(
							responseCode = "201",
							description = "Employee created",
							headers = {
									@Header(
											name = "location",
											description = "uri of the new employee resource"
									)									
							},
							content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}
					),
					@ApiResponse(
							responseCode = "409",
							description = "employee  is already present",
							content = {@Content(mediaType = MediaType.TEXT_PLAIN_VALUE)}
					),
					@ApiResponse(
							responseCode = "400",
							description = "invalid employee, returns CSV string with validation errors",
							content = {@Content(mediaType = MediaType.TEXT_PLAIN_VALUE)}
					),
					@ApiResponse(
							responseCode = "405",
							description = "invalid employee, method not allowed fixed when giving non credentials",
							content = {@Content(mediaType = MediaType.TEXT_PLAIN_VALUE)}
					)
			}
	)
	@PostMapping
	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee emp){
		emp = empService.addEmployee(emp);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")		 
				.build(emp.getId()); 
		return ResponseEntity.created(location).body(emp);
	}
	
	
	@Operation(
			summary = "returns a single updated Employee ",
			description = "using the id from the path searches the JPA context for the corresponding Employee "
					+ "entity.Updates it and returns it as a json representation.",
			method = "PUT",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Employee resource updated and returned as json object",
							content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}
					),
					@ApiResponse(
							responseCode = "404",
							description = "Employee resource not found",
							content = {@Content(mediaType = MediaType.TEXT_PLAIN_VALUE)}
					)
			}
	)
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee emp, @PathVariable long id){
		return ResponseEntity
				.ok(empService.updateEmployee(emp,id));
	}
	
	@Operation(
			summary = "Deletes an Employee",
			description = "using the id from the path searches the JPA context for the corresponding Employee "
					+ "entity and deletes it.",
			method = "DELETE",
			responses = {
					@ApiResponse(
							responseCode = "204",
							description = "Employee resource found and deleted",
							content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}
					),
					@ApiResponse(
							responseCode = "404",
							description = "Employee resource not found",
							content = {@Content(mediaType = MediaType.TEXT_PLAIN_VALUE)}
					)
			}
	)
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable int id){
		empService.deleteEmployee(id);
		return ResponseEntity.status(HttpStatus.OK).build(); 
	}
	
}
