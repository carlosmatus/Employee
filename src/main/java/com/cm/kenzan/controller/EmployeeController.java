package com.cm.kenzan.controller;

import java.net.URI;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cm.kenzan.dto.EmployeeDto;
import com.cm.kenzan.dto.EmployeeStatusEnum;
import com.cm.kenzan.entity.Employee;
import com.cm.kenzan.exception.EmployeeNotFoundException;
import com.cm.kenzan.service.EmployeeService;



/**
 * @author carlos_matus
 * Pattern: Content Negotiation  specific content and data representation formats to
 * be accepted or returned by a service capability is negotiated at
 * runtime as part of its invocation
 * 
 * I used this patter to handle JSON format as a standard that helped me to handle JSON datatypes serizalizacion and deseriailzation
 *  only and not a mix of formats  
 * 
 */
@RestController
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	private ModelMapper modelMapper;

	@Autowired
	public EmployeeController(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		this.modelMapper.addMappings(skipModifiedFieldsMap);
	}

	@GetMapping(path = "/employees", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<EmployeeDto> getAllEmployees() {

		try {
			List<Employee> employees = employeeService.getAllEmployees();
			return employees.stream().map(empl -> convertToDto(empl)).collect(Collectors.toList());
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}

	}

	@GetMapping(path = "/employees/{employeeId}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public EmployeeDto getEmployee(@PathVariable(value = "employeeId", required = true) Long employeeId)
			throws EmployeeNotFoundException {
		try {
			return convertToDto(employeeService.getEmployeeById(employeeId));
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@PostMapping(path = "/employees", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> createEmployee(@Valid @RequestBody EmployeeDto employee) {

		try {
			employee.setStatus(EmployeeStatusEnum.ACTIVE.toString());
			Employee updatedEmployee = employeeService.createEmployee(convertToEntity(employee));
			if (updatedEmployee == null || updatedEmployee.getId() == null) {
				return ResponseEntity.noContent().build();
			}
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(updatedEmployee.getId().toString())
					.build().toUri();
			return ResponseEntity.created(location).build();
		} catch (ConstraintViolationException | DataIntegrityViolationException | ParseException | MappingException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

		catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}

	}

	@PutMapping(path = "/employees/{employeeId}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> updateEmployee(@PathVariable("employeeId") long id,
			@Valid @RequestBody EmployeeDto employeeToUpdate) {
		try {
			employeeToUpdate.setId(id);
			employeeToUpdate.setStatus(EmployeeStatusEnum.ACTIVE.toString());
			EmployeeDto updatedEmployee = convertToDto(
					employeeService.updateEmployee(convertToEntity(employeeToUpdate)));
			if (updatedEmployee == null || updatedEmployee.getId() == null) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok().build();
		} catch (ConstraintViolationException | DataIntegrityViolationException | ParseException | ValidationException
				| MappingException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}

	}

	@DeleteMapping(path = "/employees/{employeeId}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> deleteEmployee(@PathVariable(value = "employeeId", required = true) Long employeeId,
			@RequestHeader(value="authorization", required=false) String authString ) {
		System.out.println("Auth string "+isUserAuthenticated(authString));
		try {
			if(authString == null ||!isUserAuthenticated(authString)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Not Autorized");
			boolean result = employeeService.deleteEmployee(employeeId);
			if (result)
				return ResponseEntity.ok().build();
			else {
				return ResponseEntity.notFound().build();
			}
		} catch (ResponseStatusException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());

		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	private EmployeeDto convertToDto(Employee employee) {
		EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
		employeeDto.setStatusConverted(employee.isActive());

		return employeeDto;
	}

	private Employee convertToEntity(EmployeeDto employeeDto) throws ParseException, ValidationException {

		Employee entityEmployee = modelMapper.map(employeeDto, Employee.class);
		entityEmployee.setActive(employeeDto.isActiveConverted());
		return entityEmployee;
	}

	PropertyMap<EmployeeDto, Employee> skipModifiedFieldsMap = new PropertyMap<EmployeeDto, Employee>() {
		protected void configure() {
			skip().setCreatedDate(null);
			skip().setLastUpdatedDate(null);
			skip().setUpdatedBy(null);
		}
	};
	
	 private boolean isUserAuthenticated(String authString){
		 
		 	if (authString == null ) return false;
         
	        String decodedAuth = "";
	        String authCode = "user:password";

	        String[] authParts = authString.split("\\s+");
	        String authInfo = authParts[1];
	        byte[] bytes = null;
	        bytes = Base64.getDecoder().decode(authInfo);
	        decodedAuth = new String(bytes);
	        System.out.println("decodificador "+decodedAuth);
	        return (authCode.contentEquals(decodedAuth));
	         

	    }
}
