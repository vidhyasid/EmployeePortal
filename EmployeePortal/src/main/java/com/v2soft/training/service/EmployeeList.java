package com.v2soft.training.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.v2soft.training.model.Employee;

public class EmployeeList {
	private List<Employee> employeeList;
	
	public EmployeeList() {
		loadCurrentEmployeeList();
	}
	
	public int size() {
		return employeeList.size();
	}
	
	public void addToList(Employee em) {
		employeeList.add(em);
	}
	
	public Employee get(int i) {
		return employeeList.get(i);
	}
	
	public List<Employee> getList() {
		return employeeList;
	}
	
	public Employee getEmployeeById(String id) {
		Employee em = new Employee();
		for(int i = 0; i < employeeList.size(); i++) {
			if(employeeList.get(i).getEmployeeId().equals(id)) {
				em = employeeList.get(i);
				break;
			}
		}
		if(em.getEmployeeId() == null)
			return null;
		return em;
	}
	
	public List<Employee> getEmployeesByFirstName(String firstname) {
		List<Employee> list = new ArrayList<Employee>();
		for(int i = 0; i < employeeList.size(); i++) {
			if(employeeList.get(i).getFirstName().equals(firstname))
				list.add(employeeList.get(i));
		}
		if(list.isEmpty())
			return null;
		return list;
	}
	
	public List<Employee> getEmployeesByLastName(String lastname) {
		List<Employee> list = new ArrayList<Employee>();
		for(int i = 0; i < employeeList.size(); i++) {
			if(employeeList.get(i).getLastName().equals(lastname))
				list.add(employeeList.get(i));
		}
		if(list.isEmpty())
			return null;
		return list;
	}
	
	public void removeEmployeeById(String id) {
		for(int i = 0; i < employeeList.size(); i++) {
			if(employeeList.get(i).getEmployeeId().equals(id)) {
				employeeList.remove(i);
				break;
			}
		}
		System.out.println("Employee Doesn't Exist!");
	}
	
	public void loadCurrentEmployeeList() {
		Employee emp1 = new Employee();
		emp1.setEmployeeId("emp1");
		emp1.setFirstName("Kishan");
		emp1.setLastName("Trivedi");
		emp1.setMiddleName("P");
		emp1.setDateOfBirth(new Date());
		emp1.setPassportNumber("12345678");
		emp1.setSsn("SSN54245");
		
		Employee emp2 = new Employee();
		emp2.setEmployeeId("emp2");
		emp2.setFirstName("John");
		emp2.setLastName("Smith");
		emp2.setMiddleName("A");
		emp2.setDateOfBirth(new Date());
		emp2.setPassportNumber("23456789");
		emp2.setSsn("SSN41543");
		
		Employee emp3 = new Employee();
		emp3.setEmployeeId("emp3");
		emp3.setFirstName("James");
		emp3.setLastName("Macdonald");
		emp3.setMiddleName("B");
		emp3.setDateOfBirth(new Date());
		emp3.setPassportNumber("34567890");
		emp3.setSsn("SSN67425");
		
		Employee emp4 = new Employee();
		emp4.setEmployeeId("emp4");
		emp4.setFirstName("Random");
		emp4.setLastName("Name");
		emp4.setMiddleName("G");
		emp4.setDateOfBirth(new Date());
		emp4.setPassportNumber("45678901");
		emp4.setSsn("SSN21454");
		
		Employee emp5 = new Employee();
		emp5.setEmployeeId("emp5");
		emp5.setFirstName("Tim");
		emp5.setLastName("Romney");
		emp5.setMiddleName("Q");
		emp5.setDateOfBirth(new Date());
		emp5.setPassportNumber("56789012");
		emp5.setSsn("SSN94326");
		
		employeeList = new ArrayList<Employee>();
		employeeList.add(emp1);
		employeeList.add(emp2);
		employeeList.add(emp3);
		employeeList.add(emp4);
		employeeList.add(emp5);
	}
	
	
	/*private void printEmployee(Employee em) {
		System.out.println(em.getEmployeeId());
		System.out.println(em.getFirstName());
		System.out.println(em.getLastName());
		System.out.println(em.getMiddleName());
		System.out.println(em.getPassportNumber());
		System.out.println(em.getSsn());
		System.out.println(em.getDateOfBirth());
		System.out.println(em.getEmployeeAddresses());
	}*/
}
