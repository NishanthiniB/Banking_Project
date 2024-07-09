package com.banking;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info=@Info
		(title="Banking Application",
description = "Backend Rest APIs for Banking",
		version = "v1.0",
		contact = @Contact(
				name ="Nishanthini B",
				email="nishasuresh1305@gmail.com",
				url = "https://github.com/NishanthiniB/Banking_Project"
		), license = @License(name="Nishanthini",
				url="https://github.com/NishanthiniB/Banking_Project")

		),
		externalDocs= @ExternalDocumentation(description = "Banking Project Documentation",
		url ="name=\"Nishanthini\",\n" +
				"\t\turl=\"https://github.com/NishanthiniB/Banking_Project"))
public class BankingProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingProjectApplication.class, args);
	}

}
