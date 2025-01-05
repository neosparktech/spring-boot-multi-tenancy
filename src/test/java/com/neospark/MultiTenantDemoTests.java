package com.neospark;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Slf4j
class MultiTenantDemoTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetUsersForTenantA() throws Exception {

		mockMvc.perform(get("/get-users").header("x-tenant-id", "tenantA").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(
						"[{\"id\":\"9ca09243-af29-4a82-8391-9a257bb4103e\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"tenant\":\"tenantA\"},{\"id\":\"4ca09243-af29-4a82-8391-9a257bb4103e\",\"firstName\":\"John\",\"lastName\":\"Smith\",\"tenant\":\"tenantA\"},{\"id\":\"5ca09243-af29-4a82-8391-9a257bb4103e\",\"firstName\":\"Robert\",\"lastName\":\"Downey\",\"tenant\":\"tenantA\"}]"));

	}

	@Test
	public void testGetUsersForTenantB() throws Exception {
		mockMvc.perform(get("/get-users").header("x-tenant-id", "tenantB").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(
						"[{\"id\":\"6ca09243-af29-4a82-8391-9a257bb4103e\",\"firstName\":\"Tom\",\"lastName\":\"Cruise\",\"tenant\":\"tenantB\"},"
								+ "{\"id\":\"7ca09243-af29-4a82-8391-9a257bb4103e\",\"firstName\":\"Bob\",\"lastName\":\"Smith\",\"tenant\":\"tenantB\"}]"));

	}

}
