package com.cm.kenzan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class EmployeeRepositoryApplication {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	public static void main(String[] args) {

		SpringApplication.run(EmployeeRepositoryApplication.class, args);
	}
}

