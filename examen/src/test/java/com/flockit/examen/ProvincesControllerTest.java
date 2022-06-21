package com.flockit.examen;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class ProvincesControllerTest extends AbstractTest {

	@Override
	@Before
	public void setMockMVC() {
		super.setMockMVC();
	}

	@Test
	public void getCoordinates() throws Exception {
		
		//Con una provincia que existe
		String endpoint = "/provinces/Chubut/coordinates";

		MvcResult mvcResult = mockMVC.perform(MockMvcRequestBuilders.get(endpoint)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String response = mvcResult.getResponse().getContentAsString();
		logger.info("ProvincesControllerTest SUCCESS RESPONSE: " + response);
		
		//Con una provincia que no existe
		endpoint = "/provinces/ninguna/coordinates";

		mvcResult = mockMVC.perform(MockMvcRequestBuilders.get(endpoint)).andReturn();

		status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);
	}
}