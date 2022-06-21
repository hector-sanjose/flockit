package com.flockit.examen.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfiguration {
	
	@Bean
	public OpenAPI customOpenAPI(@Value("${application-title}") String appTitle, @Value("${application-version}") String appVersion) {
		
		return new OpenAPI()
				.info(new Info()
						.title(appTitle)
						.version(appVersion));

	}	
}
