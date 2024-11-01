package com.dux.equipos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.dux.equipos.repositories")
@EntityScan(basePackages = "com.dux.equipos.entities")
public class EquiposApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquiposApplication.class, args);
	}

}
