package br.edu.ifpr.projeto02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude =  SecurityAutoConfiguration.class)
public class Projeto02Application {

	public static void main(String[] args) {
		SpringApplication.run(Projeto02Application.class, args);


		
	}

}
