package com.v2soft.training.dataModel;
// Generated Oct 9, 2019, 1:39:32 PM by Hibernate Tools 5.1.10.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * EmployeeInfo generated by hbm2java
 */
public class EmployeeInfo implements java.io.Serializable {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String middleName;
	private Date dateOfBirth;
	private String passportNumber;
	private String ssn;
	private Set<EmployeeAddressInfo> employeeAddresses = new HashSet<EmployeeAddressInfo>(0);

	public EmployeeInfo() {
	}

	public EmployeeInfo(String employeeId, String firstName, String lastName, Date dateOfBirth, String ssn) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.ssn = ssn;
	}

	public EmployeeInfo(String employeeId, String firstName, String lastName, String middleName, Date dateOfBirth,
			String passportNumber, String ssn, Set<EmployeeAddressInfo> employeeAddresses) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.dateOfBirth = dateOfBirth;
		this.passportNumber = passportNumber;
		this.ssn = ssn;
		this.employeeAddresses = employeeAddresses;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPassportNumber() {
		return this.passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Set<EmployeeAddressInfo> getEmployeeAddresses() {
		return this.employeeAddresses;
	}

	public void setEmployeeAddresses(Set<EmployeeAddressInfo> employeeAddresses) {
		this.employeeAddresses = employeeAddresses;
	}

}