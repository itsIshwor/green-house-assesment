package io.greenhouse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

@Configuration
public class SwaggerConfig {

	@Autowired
	private ResourceLoader resourceLoader;

	@Bean
	OpenAPI customOpenAPI() {
		try {
			Resource resource = resourceLoader.getResource("classpath:/api.yml");

			SwaggerParseResult parseResult = new OpenAPIV3Parser().readLocation(resource.getURI().toString(), null,
					null);

			if (parseResult.getOpenAPI() == null) {
				throw new RuntimeException("Failed to parse the API specification from api.yml");
			}

			return parseResult.getOpenAPI();
		} catch (Exception e) {
			throw new RuntimeException("Error reading api.yml", e);
		}
	}
}