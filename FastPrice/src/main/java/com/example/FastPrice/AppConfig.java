package com.example.FastPrice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
//This Class Is Bean class to interact with resttemplate 
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
