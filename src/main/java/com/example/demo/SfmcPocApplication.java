package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class SfmcPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(SfmcPocApplication.class, args);
	}
	@Bean
	 RestTemplate restTemplate() {
		 RestTemplate restTemplate = new RestTemplate();
	    return restTemplate;
	}
}
