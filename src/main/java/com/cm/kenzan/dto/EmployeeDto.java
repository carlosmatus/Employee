package com.cm.kenzan.dto;

import java.text.ParseException;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.dao.DataIntegrityViolationException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;



/**
 * @author carlos_matus
 * 
 * Pattern :DTO 
 * Pattern was used to encapsulate data on Client/ Response 
 * I decide to use it also to break dependency between Client Data and JPA Entity 
 *
 */
@JsonIgnoreProperties(value = { "dateOfEmploymentConverted", "dateOfBirthConverted", "activeConverted" })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDto {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull(message = "First name not null")
	@Pattern(regexp = "[a-zA-Z ]+", message = "At least one character from A-Z or space is allowed")
	private String firstName;

	@Size(min = 0, max = 1, message = "Middle Initial Max is 1")
	@Pattern(regexp = "[a-zA-Z]", message = "Only [A-Z] characters allowed")

	private String middleInitial;

	@NotNull(message = "Last name not null")
	@Pattern(regexp = "[a-zA-Z ]+", message = "At least one character from A-Z or space is allowed")
	private String lastName;

	@NotNull(message = "Date of Birth cannot be null")
	@Past(message = "Only dates in the past are allowed")
	// @JsonDeserialize(using = com.fasterxml.jackson.datatype.jsr310.deser.class)
	// or @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDate dateOfBirth;

	private LocalDate dateOfEmployment;

	@JsonProperty()
	@NotNull
	private String status = EmployeeStatusEnum.ACTIVE.toString();

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public LocalDate getDateOfBirthConverted() throws ParseException {

		if (dateOfBirth == null)
			throw new DataIntegrityViolationException("Date of Birth is missing");
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {

		this.dateOfBirth = dateOfBirth;
	}

	public LocalDate getDateOfEmployment() {

		return dateOfEmployment;
	}

	public void setDateOfEmployment(LocalDate dateOfEmployment) {
		this.dateOfEmployment = dateOfEmployment;
	}

	public String getStatus() {
		return status;
	}

	public boolean isActiveConverted() {
		String localStatu = status.toUpperCase();
		if (localStatu.equals(EmployeeStatusEnum.ACTIVE.toString()))
			return true;
		else if (localStatu.equals(EmployeeStatusEnum.INACTIVE.toString())) {
			return false;
		}
		throw new DataIntegrityViolationException("Bad Request");
	}

	@Override
	public String toString() {
		return "EmployeeDto [id=" + id + ", firstName=" + firstName + ", middleInitial=" + middleInitial + ", lastName="
				+ lastName + ", dateOfBirth=" + dateOfBirth + ", dateOfEmployment=" + dateOfEmployment + ", status="
				+ status + "]";
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setStatusConverted(boolean isActive) {
		this.status = isActive ? EmployeeStatusEnum.ACTIVE.toString() : EmployeeStatusEnum.INACTIVE.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EmployeeDto(Long id,
			@NotNull(message = "First name not null") @Pattern(regexp = "[a-zA-Z ]+", message = "At least one character from A-Z or space is allowed") String firstName,
			@Size(min = 0, max = 1, message = "Middle Initial Max is 1") @Pattern(regexp = "[a-zA-Z]", message = "Only [A-Z] characters allowed") String middleInitial,
			@NotNull(message = "Last name not null") @Pattern(regexp = "[a-zA-Z ]+", message = "At least one character from A-Z or space is allowed") String lastName,
			@NotNull(message = "Date of Birth cannot be null") @Past(message = "Only dates in the past are allowed") LocalDate dateOfBirth,
			LocalDate dateOfEmployment, @NotNull String status) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.dateOfEmployment = dateOfEmployment;
		this.status = status;

	}

	public EmployeeDto() {
		super();

	}

}
