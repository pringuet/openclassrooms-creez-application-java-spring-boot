package fr.hrassociation.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.hrassociation.webapp.model.Employee;
import fr.hrassociation.webapp.service.EmployeeService;
import lombok.Data;

@Controller
@Data
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/")
	public String home(Model model) {
		Iterable<Employee> employees = employeeService.getEmployees();
		model.addAttribute("employees", employees);
		return "home";
	}

	@GetMapping("/createEmployee")
	public String createEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "formNewEmployee";
	}

	@GetMapping("/updateEmployee/{id}")
	public String updateEmployee(@PathVariable("id") final Long id, Model model) {
		Employee employee = employeeService.getEmployee(id);
		model.addAttribute("employee", employee);
		return "formUpdateEmployee";
	}

	@PostMapping("/saveEmployee")
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
		if (employee.getId() != null) {
			Employee currentEmployee = employeeService.getEmployee(employee.getId());
			employee.setPassword(currentEmployee.getPassword());
		}
		employeeService.saveEmployee(employee);
		return new ModelAndView("redirect:/");
	}

	@GetMapping("/deleteEmployee/{id}")
	public ModelAndView deleteEmployee(@PathVariable("id") final Long id) {
		employeeService.deleteEmployee(id);
		return new ModelAndView("redirect:/");
	}
}
