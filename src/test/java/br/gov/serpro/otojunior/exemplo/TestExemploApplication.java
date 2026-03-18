package br.gov.serpro.otojunior.exemplo;

import org.springframework.boot.SpringApplication;

public class TestExemploApplication {

	public static void main(String[] args) {
		SpringApplication.from(ExemploApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
