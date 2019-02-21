package com.cm.kenzan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cm.kenzan.entity.Employee;
import com.cm.kenzan.exception.EmployeeNotFoundException;
import com.cm.kenzan.service.EmployeeServiceImpl;

import java.time.LocalDate;

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
      //  main(args);
//		Employee employee = new Employee(n,"M","Pavon",LocalDate.parse("1970-01-01"),
//                LocalDate.parse("2010-01-26"),true); 
//		employeeRepository.createEmploye(employee);
       try {
		System.out.println("empleado :"+employeeRepository.getEmployeeById(1L));
	//	System.out.println("Todos los empleados :"+employeeRepository.getAllEmployees());
	} catch (EmployeeNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	}
       
    }
}

