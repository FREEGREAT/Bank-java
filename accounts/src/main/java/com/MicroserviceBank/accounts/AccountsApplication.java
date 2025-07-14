package com.MicroserviceBank.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts mircroservice REST API Documentation",
				description = "MicroserviceBank accounts REST API documentation",
				version = "v1",
				contact = @Contact(
						name = "Raiden",
						email = "raiden@g.com",
						url = "www.microservicebank.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "www.microservicebank.com"
				)

		),
		externalDocs = @ExternalDocumentation(
				description = "MicroserviceBank accounts REST API documentation",
				url = "www.microservicebank.com/swagger-ui.html"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
