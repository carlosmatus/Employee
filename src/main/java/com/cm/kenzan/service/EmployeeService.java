package com.cm.kenzan.service;

import java.util.List;
import com.cm.kenzan.entity.Employee;
import com.cm.kenzan.exception.EmployeeNotFoundException;

public interface EmployeeService {
	public Employee getEmployeeById(Long id) throws EmployeeNotFoundException;
	public List<Employee> getAllEmployees() throws EmployeeNotFoundException;
	public Employee createEmployee(Employee employee);
	public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException;
	public boolean deleteEmployee(Long employeeId) throws EmployeeNotFoundException;

}
