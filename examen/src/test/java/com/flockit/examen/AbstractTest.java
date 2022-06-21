package com.flockit.examen;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExamenApplication.class)
@WebAppConfiguration
public abstract class AbstractTest {

	protected Logger logger = LoggerFactory.getLogger(AbstractTest.class);

	protected MockMvc mockMVC;

	@Autowired
	WebApplicationContext webApplicationContext;

	protected void setMockMVC() {
		mockMVC = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapObjectToJSON(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}