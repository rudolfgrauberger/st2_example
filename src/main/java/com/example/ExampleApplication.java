package com.example;

import com.example.service.BestellungService;
import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ExampleApplication {

	public static void main(String[] args) {
		BestellungService bestellungService = new BestellungService();
		SpringApplication.run(ExampleApplication.class, args);
		
	}
	@Bean
	public ServletRegistrationBean h2servletRegistration() {
	    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
	    registration.addUrlMappings("/h2-console/*");
	    return registration;
	}
}
