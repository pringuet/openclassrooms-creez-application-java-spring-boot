package fr.hrassociation.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import fr.hrassociation.webapp.CustomProperties;
import fr.hrassociation.webapp.model.Employee;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeProxy {

	@Autowired
	private CustomProperties customProperties;

	public Employee getEmployee(final Long id) {

		String apiUrl = customProperties.getApiUrl();
		String getEmployeeUrl = apiUrl + "/employee/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Employee> responseEntity = restTemplate.exchange(getEmployeeUrl, HttpMethod.GET, null,
				Employee.class);

		log.debug("Get Employees Call " + responseEntity.getStatusCode().toString());

		return responseEntity.getBody();
	}

	public Iterable<Employee> getEmployees() {

		String apiUrl = customProperties.getApiUrl();
		String getEmployeeUrl = apiUrl + "/employees";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Employee>> responseEntity = restTemplate.exchange(getEmployeeUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<Iterable<Employee>>() {
				});

		log.debug("Get Employees Call " + responseEntity.getStatusCode().toString());

		return responseEntity.getBody();
	}

	public Employee createEmployee(Employee employee) {

		String apiUrl = customProperties.getApiUrl();
		String createEmployeeUrl = apiUrl + "/employee";

		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<Employee> request = new HttpEntity<Employee>(employee);
		ResponseEntity<Employee> responseEntity = restTemplate.exchange(createEmployeeUrl, HttpMethod.POST, request,
				Employee.class);

		log.debug("Create Employee Call " + responseEntity.getStatusCode().toString());

		return responseEntity.getBody();
	}

	public Employee updateEmployee(Employee employee) {

		String apiUrl = customProperties.getApiUrl();

//		String updateEmployeeUrl = apiUrl + "/employee/" + e.getId();
		String updateEmployeeUrl = apiUrl + "/employee";

		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<Employee> request = new HttpEntity<Employee>(employee);

		ResponseEntity<Employee> response = restTemplate.exchange(updateEmployeeUrl, HttpMethod.PUT, request,
				Employee.class);

		log.debug("Update Employee call " + response.getStatusCode().toString());

		return response.getBody();
	}

	public void deleteEmployee(final Long id) {
		
		String apiUrl = customProperties.getApiUrl();
		String deleteEmployeeUrl = apiUrl + "/employee/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(deleteEmployeeUrl, HttpMethod.DELETE, null, Void.class);

		log.debug("Delete Employee call " + response.getStatusCode().toString());
	}
}
