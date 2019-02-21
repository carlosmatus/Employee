package com.cm.kenzan.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cm.kenzan.entity.Employee;
import com.cm.kenzan.exception.EmployeeNotFoundException;
import com.cm.kenzan.respository.EmployeeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeServicesMockTest {
	@InjectMocks
	EmployeeServiceImpl service;

	@Mock
	EmployeeRepository dao;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void get_Employees_Return_Employee_list() throws EmployeeNotFoundException {
		List<Employee> list = new ArrayList<Employee>();
		Employee employeeOne = new Employee();
		employeeOne.setFirstName("Frank");
		employeeOne.setMiddleInitial("M");
		employeeOne.setLastName("Medina");
		employeeOne.setDateOfBirth(LocalDate.of(1970, 8, 20));
		employeeOne.setDateOfEmployment(LocalDate.of(2016, 12, 1));

		Employee employeeTwo = new Employee();
		employeeTwo.setFirstName("Frank");
		employeeTwo.setMiddleInitial("M");
		employeeTwo.setLastName("Medina");
		employeeTwo.setDateOfBirth(LocalDate.of(1970, 8, 20));
		employeeTwo.setDateOfEmployment(LocalDate.of(2016, 12, 1));

		list.add(employeeOne);
		list.add(employeeTwo);

		when(dao.findByisActiveTrue()).thenReturn(list);

		List<Employee> empList = service.getAllEmployees();

		assertEquals(2, empList.size());
		verify(dao, times(1)).findByisActiveTrue();
	}

	@Test(expected = EmployeeNotFoundException.class)

	public void getEmployees_Throw_Not_Found_Exception_on_EmptyList() throws EmployeeNotFoundException {

		when(dao.findByisActiveTrue()).thenReturn(new ArrayList<Employee>());

		service.getAllEmployees();
	}

	@Test
	public void getEmployeeById_Returns_Employee() throws EmployeeNotFoundException {
		Employee employeeOne = new Employee();
		employeeOne.setId(1L);
		employeeOne.setFirstName("Frank");
		employeeOne.setMiddleInitial("M");
		employeeOne.setLastName("Medina");
		employeeOne.setDateOfBirth(LocalDate.of(1970, 8, 20));
		employeeOne.setDateOfEmployment(LocalDate.of(2016, 12, 1));
		
		when(dao.findByIdAndIsActiveTrue(1L)).thenReturn(Optional.of(employeeOne));

		Employee emp = service.getEmployeeById(1L);

		assertEquals("Frank", emp.getFirstName());
		assertEquals("Medina", emp.getLastName());

	}
	
	
	@Test(expected = EmployeeNotFoundException.class)
	public void getEmployeeById_Returns_Employee_NotFoundExc_If_Not_Found() throws EmployeeNotFoundException {
		
		when(dao.findByIdAndIsActiveTrue(1L)).thenReturn(Optional.empty());

		Employee emp = service.getEmployeeById(1L);

		assertEquals("Frank", emp.getFirstName());
		assertEquals("Medina", emp.getLastName());

	}

	@Test(expected = NullPointerException.class)
	public void createEmployee_Fail_IF_Missing_id_Test() {
		Employee emp = new Employee();

		service.createEmployee(emp);

		verify(dao, times(1)).save(emp);
	}

	@Test(expected = NullPointerException.class)
	public void createEmployee_Successful_Test() {
		Employee employeeOne = new Employee();
		employeeOne.setId(1L);
		employeeOne.setFirstName("Frank");
		employeeOne.setMiddleInitial("M");
		employeeOne.setLastName("Medina");
		employeeOne.setDateOfBirth(LocalDate.of(1970, 8, 20));
		employeeOne.setDateOfEmployment(LocalDate.of(2016, 12, 1));
		service.createEmployee(employeeOne);

		verify(dao, times(1)).save(employeeOne);
	}

	@Test(expected = EmployeeNotFoundException.class)
	public void updateEmployee_Returns_Employee_NotFoundExc_If_Not_Found() throws EmployeeNotFoundException {
		
		when(dao.findByIdAndIsActiveTrue(1L)).thenReturn(Optional.empty());
		Employee employeeOne = new Employee();
		Employee emp = service.updateEmployee(employeeOne);

		assertEquals("Frank", emp.getFirstName());
		assertEquals("Medina", emp.getLastName());
	}
	
	@Test(expected = EmployeeNotFoundException.class)
	public void updateEmployee_Successful_Test() throws ConstraintViolationException, EmployeeNotFoundException {
		Employee employeeOne = new Employee();
		employeeOne.setId(1L);
		employeeOne.setFirstName("Frank");
		employeeOne.setMiddleInitial("M");
		employeeOne.setLastName("Medina");
		employeeOne.setDateOfBirth(LocalDate.of(1970, 8, 20));
		employeeOne.setDateOfEmployment(LocalDate.of(2016, 12, 1));
		service.updateEmployee(employeeOne);

		verify(dao, times(1)).save(employeeOne);
	}

	@Test(expected = EmployeeNotFoundException.class)
	public void deleteEmployee_Returns_Employee_NotFoundExc_If_Not_Found() throws ConstraintViolationException, EmployeeNotFoundException {
		Employee employeeOne = new Employee();
		employeeOne.setId(1L);
		employeeOne.setFirstName("Frank");
		employeeOne.setMiddleInitial("M");
		employeeOne.setLastName("Medina");
		employeeOne.setDateOfBirth(LocalDate.of(1970, 8, 20));
		employeeOne.setDateOfEmployment(LocalDate.of(2016, 12, 1));
		service.deleteEmployee(employeeOne.getId());

		verify(dao, times(1)).save(employeeOne);
	}


	}
