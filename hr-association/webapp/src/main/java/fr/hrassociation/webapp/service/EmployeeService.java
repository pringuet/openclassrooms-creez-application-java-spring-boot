package fr.hrassociation.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.hrassociation.webapp.model.Employee;
import fr.hrassociation.webapp.repository.EmployeeProxy;
import lombok.Data;

@Service
@Data
public class EmployeeService {

	@Autowired
	private EmployeeProxy employeeProxy;

	public Employee getEmployee(final Long id) {
		return employeeProxy.getEmployee(id);
	}

	public Iterable<Employee> getEmployees() {
		return employeeProxy.getEmployees();
	}

	public Employee saveEmployee(Employee employee) {
		Employee savedEmployee;
		employee.setLastName(employee.getLastName().toUpperCase());
		if (employee.getId() == null) {
			savedEmployee = employeeProxy.createEmployee(employee);
		} else {
			savedEmployee = employeeProxy.updateEmployee(employee);
		}
		return savedEmployee;
	}

	public void deleteEmployee(final Long id) {
		employeeProxy.deleteEmployee(id);
	}
}
