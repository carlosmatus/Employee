package com.cm.kenzan.respository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import com.cm.kenzan.entity.Employee;



/**
 * @author carlos_matus
 * Pattern: Repository 
 * Intended to create an abstraction layer between the data access layer and the business logic layer of an application
 * I used this pattern to help with Data related tasks.I extended the class from CrudRepository to emphasize 
 * the use of this pattern for this project
 *  
 */
@Transactional
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	
	Optional<Employee> findById(Long id);
	Optional<Employee> findByIdAndIsActiveTrue(Long id);
	Iterable<Employee> findByisActiveTrue();
	

}
