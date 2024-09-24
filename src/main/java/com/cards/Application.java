package com.cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")

@OpenAPIDefinition(
			info = @Info(
					 title = "Cards microservice REST API Documentation",
					 description = "EazyBank Cards microservice REST API Documentation",
					 version = "v1",
					 contact = @Contact(
							 	name = "Avinash Verma",
							 	email = "avinash@gmail.com",
							 	url = "www.youtube.com"
							 )
					)
			,
			externalDocs = @ExternalDocumentation(
						description = "EazyBank Cards microservice REST API Documentation",
						url = "http://localhost:8081/swagger-ui/index.html"
					)
		
		)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
