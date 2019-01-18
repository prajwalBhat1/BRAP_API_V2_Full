package com.brap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EntityScan(basePackages = { "com.brap.persistence.entity" })
@EnableJpaRepositories(basePackages = { "com.brap.schedule.persistence.repo" })
public class ApplicationInitializer {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApplicationInitializer.class, args);
	}
}
