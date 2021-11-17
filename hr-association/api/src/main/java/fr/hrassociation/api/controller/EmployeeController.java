package fr.hrassociation.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.hrassociation.api.model.Employee;
import fr.hrassociation.api.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable("id") final Long id) {
		Optional<Employee> employee = employeeService.getEmployee(id);
		if (employee.isPresent()) {
			return employee.get();
		} else {
			return null;
		}
	}

	@GetMapping("/employees")
	public Iterable<Employee> getEmployees() {
		return employeeService.getEmployees();
	}

	@PostMapping("/employee")
	public Employee createEmploye(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}

	@PutMapping("/employee")
	public Employee updateEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}

	@DeleteMapping("/employee/{id}")
	public void deleteEmploye(@PathVariable("id") final Long id) {
		employeeService.deleteEmployee(id);
	}
}
