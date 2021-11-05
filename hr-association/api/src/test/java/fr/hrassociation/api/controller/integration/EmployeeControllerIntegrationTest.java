package fr.hrassociation.api.controller.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerIntegrationTest {

	@Autowired
	public MockMvc mockMvc;

	@Test
	public void testGetEmployees() throws Exception {
		
		mockMvc.perform(get("/employees")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].firstName", is("Laurent")));
		
		mockMvc.perform(get("/employees")).andExpect(status().isOk())
				.andExpect(jsonPath("$[1].firstName", is("Sophie")));
		
		mockMvc.perform(get("/employees")).andExpect(status().isOk())
				.andExpect(jsonPath("$[2].firstName", is("Agathe")));
	}
}
