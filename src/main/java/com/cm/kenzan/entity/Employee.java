package com.cm.kenzan.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Employee implements Serializable {

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    private Long id;

	    @Column(name="firstName",nullable = false)
	    private String firstName;

	    @Column(length=1)
	    private String middleInitial;

	    @Column(name="lastName", nullable = false)
	    private String lastName;

	    @Column(name="dateOfBirth")
	    private LocalDate dateOfBirth;

	    @Column(name="dateOfEmployment")
	    private LocalDate dateOfEmployment;
	    
	    @Column(name="isActive")
	    private boolean isActive;
	    
	    @Column(name="createdDate")
	    @CreationTimestamp
	    private LocalDateTime createdDate;
	    @PrePersist
	    protected void onCreate() {
	    	createdDate = LocalDateTime.now();
	    }
	    
	    @Column(name="lastUpdatedDate")
	    @UpdateTimestamp
	    private LocalDateTime lastUpdatedDate;
	    
	    @PreUpdate
	    protected void onUpdate() {
	    	lastUpdatedDate = LocalDateTime.now();
	    }
	    
	    @Column(name="updatedBy")
	    private String updatedBy;
	    
	    public Employee(){}



		public Employee(String firstName, String middleInitial, String lastName, LocalDate dateOfBirth,
				LocalDate dateOfEmployment, boolean isActive) {
			super();
			this.firstName = firstName;
			this.middleInitial = middleInitial;
			this.lastName = lastName;
			this.dateOfBirth = dateOfBirth;
			this.dateOfEmployment = dateOfEmployment;
			this.isActive = isActive;
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


		public LocalDate getDateOfBirth() {
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


		public static long getSerialversionuid() {
			return serialVersionUID;
		}


		public boolean isActive() {
			return isActive;
		}

		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}

		public LocalDateTime getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(LocalDateTime createdDate) {
			this.createdDate = createdDate;
		}

		public LocalDateTime getLastUpdatedDate() {
			return lastUpdatedDate;
		}


		public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
			this.lastUpdatedDate = lastUpdatedDate;
		}

		public String getUpdatedBy() {
			return updatedBy;
		}

		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
		}



		@Override
		public String toString() {
			return "Employee [id=" + id + ", firstName=" + firstName + ", middleInitial=" + middleInitial
					+ ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", dateOfEmployment="
					+ dateOfEmployment + ", isActive=" + isActive + ", createdDate=" + createdDate
					+ ", lastUpdatedDate=" + lastUpdatedDate + ", updatedBy=" + updatedBy + "]";
		}

    

}
