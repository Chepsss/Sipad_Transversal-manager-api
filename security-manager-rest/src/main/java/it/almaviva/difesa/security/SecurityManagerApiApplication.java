package it.almaviva.difesa.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "it.almaviva.difesa")
public class SecurityManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityManagerApiApplication.class, args);
	}

}
