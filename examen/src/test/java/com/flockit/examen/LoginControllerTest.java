package com.flockit.examen;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.flockit.examen.dto.LoginDTO;

public class LoginControllerTest extends AbstractTest {

	@Override
	@Before
	public void setMockMVC() {
		super.setMockMVC();
	}

	@Test
	public void login() throws Exception {
		String endpoint = "/login";

		// Con un usuario que existe en la base de datos
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail("hector.sanjose@gmail.com");
		loginDTO.setPassword("admin");

		String jsonLoginDTO = super.mapObjectToJSON(loginDTO);

		MvcResult mvcResult = mockMVC.perform(MockMvcRequestBuilders.post(endpoint)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonLoginDTO)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String response = mvcResult.getResponse().getContentAsString();
		logger.info("LoginControllerTest SUCCESS RESPONSE: " + response);

		// Con un usuario que no existe en la base de datos
		loginDTO = new LoginDTO();
		loginDTO.setEmail("john.doe@gmail.com");
		loginDTO.setPassword("java12345");

		jsonLoginDTO = super.mapObjectToJSON(loginDTO);

		mvcResult = mockMVC.perform(MockMvcRequestBuilders.post(endpoint)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonLoginDTO)).andReturn();

		status = mvcResult.getResponse().getStatus();
		assertEquals(403, status);

		response = mvcResult.getResponse().getContentAsString();
		logger.info("LoginControllerTest FORBIDDEN RESPONSE: " + response);
	}
}