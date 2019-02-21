package com.cm.kenzan.controller;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.cm.kenzan.dto.EmployeeDto;


public class EmployeeControllerTest extends AbstractControllerTest {
   @Override
   @Before
   public void setUp() {
      super.setUp();
   }
   @Test
   public void getEmployees_SUCCESS_On_Correct_Request() throws Exception {
      String uri = "/employees";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      EmployeeDto[] productlist = super.mapFromJson(content, EmployeeDto[].class);
      assertNotNull(productlist);
   }
   
   @Test
   public void getEmployeeById_SUCESS_On_Corrent_Request() throws Exception {
      String uri = "/employees/1";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      EmployeeDto productlist = super.mapFromJson(content, EmployeeDto.class);
      assertNotNull(productlist);
   }
   @Test
   public void getEmployeById_Returns_NOT_FOUND_On_Not_Existing_Id() throws Exception {
	      String uri = "/employees/100";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(404, status);
	      
	   }
   @Test
   public void getById_Returns_BAD_REQUEST_On_Wrong_Request() throws Exception {
	      String uri = "/employees/n";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(400, status);
	      
	   }
   @Test
   public void post_Employee_Returns_CREATED_On_Successful_Creation() throws Exception {
      String uri = "/employees";
      EmployeeDto employeeDto = new EmployeeDto();
      employeeDto.setFirstName("Carlos");
      employeeDto.setLastName("Matus");
      employeeDto.setMiddleInitial("M");
      employeeDto.setDateOfBirth(LocalDate.of(1990, 8, 20));
      employeeDto.setDateOfEmployment(LocalDate.of(2018, 12, 1));

      String inputJson = super.mapToJson(employeeDto);
      System.out.println("Lo que se envia "+inputJson);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(201, status);
     
   }
   
   @Test
   public void post_Employee_returns_BAD_REQUEST_On_Missing_Required_Param() throws Exception {
	      String uri = "/employees";
	      EmployeeDto employeeDto = new EmployeeDto();
	      employeeDto.setFirstName("Carlos");
	      employeeDto.setLastName("Matus");
	      employeeDto.setMiddleInitial("M");
	    //  employeeDto.setDateOfBirth(LocalDate.of(1990, 8, 20));
	      employeeDto.setDateOfEmployment(LocalDate.of(2018, 12, 1));

	      String inputJson = super.mapToJson(employeeDto);
	      System.out.println("Lo que se envia "+inputJson);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(400, status);
	     
	   }
   @Test
   public void put_Employee_Returns_OK_On_Successful_Update() throws Exception {
      String uri = "/employees/1";
      
      EmployeeDto employeeDto = new EmployeeDto();
      employeeDto.setFirstName("Frank");
      employeeDto.setMiddleInitial("M");
      employeeDto.setLastName("Medina");
      employeeDto.setDateOfBirth(LocalDate.of(1970, 8, 20));
      employeeDto.setDateOfEmployment(LocalDate.of(2016, 12, 1));
      
      String inputJson = super.mapToJson(employeeDto);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);

   }
   
   @Test
   public void update_Employee_returns_BAD_REQUEST_On_Missing_Required_Param() throws Exception {
      String uri = "/employees/1";
      
      EmployeeDto employeeDto = new EmployeeDto();
     // employeeDto.setFirstName("Frank");
      employeeDto.setMiddleInitial("M");
      employeeDto.setLastName("Medina");
      employeeDto.setDateOfBirth(LocalDate.of(1970, 8, 20));
      employeeDto.setDateOfEmployment(LocalDate.of(2016, 12, 1));
      
      String inputJson = super.mapToJson(employeeDto);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(400, status);

   }
   
   @Test
   public void update_Employee_returns_BAD_REQUEST_On_Wrong_Request() throws Exception {
      String uri = "/employees/nn";
      
      EmployeeDto employeeDto = new EmployeeDto();
      employeeDto.setFirstName("Frank");
      employeeDto.setMiddleInitial("M");
      employeeDto.setLastName("Medina");
      employeeDto.setDateOfBirth(LocalDate.of(1970, 8, 20));
      employeeDto.setDateOfEmployment(LocalDate.of(2016, 12, 1));
      
      String inputJson = super.mapToJson(employeeDto);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(400, status);

   }
   
   @Test
   public void update_Employee_returns_NOT_FOUND_On_Missing_Employee() throws Exception {
      String uri = "/employees/100";
      
      EmployeeDto employeeDto = new EmployeeDto();
      employeeDto.setFirstName("Frank");
      employeeDto.setMiddleInitial("M");
      employeeDto.setLastName("Medina");
      employeeDto.setDateOfBirth(LocalDate.of(1970, 8, 20));
      employeeDto.setDateOfEmployment(LocalDate.of(2016, 12, 1));
      
      String inputJson = super.mapToJson(employeeDto);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(404, status);

   }

   
   @Test
   public void delete_Employee_returns_UNAUTHORIZED_On_Failed_Auth() throws Exception {
      String uri = "/employees/1";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      int status = mvcResult.getResponse().getStatus();
      assertEquals(401, status);
     
   }
}