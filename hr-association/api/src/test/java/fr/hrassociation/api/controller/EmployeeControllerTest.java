package fr.hrassociation.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import fr.hrassociation.api.service.EmployeeService;

@WebMvcTest
public class EmployeeControllerTest {

	@Autowired
	public MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;

	@Test
	public void testGetEmployees() throws Exception {
		mockMvc.perform(get("/employees")).andExpect(status().isOk());
	}
}
