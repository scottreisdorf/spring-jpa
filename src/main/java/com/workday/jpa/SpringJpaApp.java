package com.workday.jpa;

import com.workday.jpa.repository.EncryptingRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.workday.jpa.repository", repositoryBaseClass= EncryptingRepositoryImpl.class)
@SpringBootApplication
public class SpringJpaApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApp.class, args);
	}
}
