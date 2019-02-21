package com.cm.kenzan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cm.kenzan.entity.Employee;
import com.cm.kenzan.exception.EmployeeNotFoundException;
import com.cm.kenzan.respository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl() {
	}

	@Override
	public Employee getEmployeeById(Long id) throws EmployeeNotFoundException {
		Optional<Employee> employee = employeeRepository.findByIdAndIsActiveTrue(id);
		employee.orElseThrow(() -> new EmployeeNotFoundException("No Employee found with id " + id));
		return employee.get();
	}

	@Override
	public List<Employee> getAllEmployees() throws EmployeeNotFoundException {

		List<Employee> lst = new ArrayList<Employee>();
		Iterable<Employee> employeeIterator = employeeRepository.findByisActiveTrue();
		employeeIterator.forEach(lst::add);
		if (lst.size() == 0)
			throw new EmployeeNotFoundException("No Employees found ");
		return lst;
	}

	@Override
	public Employee createEmployee(Employee employee) throws ConstraintViolationException {

		Employee resultEmployee = employeeRepository.save(employee);
		if (resultEmployee.getId() == null) {
			throw new NullPointerException("Employee couldn't be updated");

		}
		return resultEmployee;

	}

	@Override
	public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException,ConstraintViolationException {
		Optional<Employee> respEmployee = employeeRepository.findByIdAndIsActiveTrue(employee.getId());
		respEmployee.orElseThrow(() -> new EmployeeNotFoundException("No Employee found with id " + employee.getId()));
		Employee resultEmployee = employeeRepository.save(employee);

		return resultEmployee;
	}


	@Override
	public boolean deleteEmployee(Long employeeId) throws EmployeeNotFoundException,ConstraintViolationException {
		Employee respEmployee = employeeRepository.findByIdAndIsActiveTrue(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("No Employee found with id " + employeeId));
		respEmployee.setActive(false);
		employeeRepository.save(respEmployee);

		return true;
	}

}
