package com.fdmgroup.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.client.model.Employee;
import com.fdmgroup.client.service.FeignClientEmployeeService;

@Controller
public class EmployeeController {
	
	private final FeignClientEmployeeService service;

	public EmployeeController(FeignClientEmployeeService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("")
	public String index() {
		return "index";
	}
	
	@GetMapping("index")
	public String index1() {
		return "index";
	}
	
	@GetMapping("/allEmployees")
	public String allEmployees(Model model) {
		model.addAttribute("listOfEmployees",service.getAllEmployees());
		return "employee/all-employees";
	}
	
	@GetMapping("/findById")
	public String findById(Model model,@RequestParam long id) {
		model.addAttribute("employee",service.getEmployeeById(id));
		return "employee/one-employee";
	}
	
	@RequestMapping("/addEmployee")
	public String addEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		return "employee/add-employee";
	}
	
	@PostMapping("/addEmployeeSubmit")
	public String addEmployeeSubmit(Model model, @ModelAttribute Employee emp) {
		service.addEmployee(emp);
		return "index";
	}
	
	@RequestMapping("/editEmployee")
	public String editEmployee(Model model,@RequestParam long id) {
		model.addAttribute("employee",service.getEmployeeById(id));
		return "employee/edit-employee";
	}
	
	@PostMapping("/editEmployeeSubmit")
	public String editEmployeeSubmit(Model model, @ModelAttribute Employee emp) {
		service.updateEmployee(emp, emp.getId());
		return "index";
	}
	
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(Model model,@RequestParam long id) {
		service.deleteEmployee(id);
		return "index";
	}
	
}
