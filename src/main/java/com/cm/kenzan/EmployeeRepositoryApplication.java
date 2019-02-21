package com.cm.kenzan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cm.kenzan.exception.EmployeeNotFoundException;
import com.cm.kenzan.service.EmployeeServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class EmployeeRepositoryApplication implements CommandLineRunner{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EmployeeServiceImpl employeeRepository;

	public static void main(String[] args) {
		
		
		SpringApplication.run(EmployeeRepositoryApplication.class, args);
	}

	@Override
    public void run(String...args){

       try {
		System.out.println("empleado :"+employeeRepository.getEmployeeById(1L));
	} catch (EmployeeNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	}
       
    }
}

