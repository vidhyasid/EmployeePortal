package com.v2soft.training.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.v2soft.training.model.Employee;
import com.v2soft.training.model.EmployeeAddress;
import com.v2soft.training.service.EmployeeList;

@Controller
public class EmployeeController {
	
	private EmployeeList employeeList = new EmployeeList();
	private ObjectMapper mapper = new ObjectMapper();
	
	//http://localhost:8080/EmployeePortal/getEmployeeById/{id}
	@RequestMapping(value="/getEmployeeById/{employeeId}", method=RequestMethod.GET)
	@ResponseBody
	public String getEmployeeById(@PathVariable String employeeId) throws JsonProcessingException {
		Employee employee = employeeList.getEmployeeById(employeeId);
		return mapper.writeValueAsString(employee);
	}
	
	//http://localhost:8080/EmployeePortal/getEmployeesByFirstName/{fn}
	@RequestMapping(value="/getEmployeesByFirstName/{firstname}", method=RequestMethod.GET)
	@ResponseBody
	public String getEmployeeByFirstName(@PathVariable String firstname) throws JsonProcessingException {
		List<Employee> list = employeeList.getEmployeesByFirstName(firstname);
		return listToString(list);
	}
	
	//http://localhost:8080/EmployeePortal/getEmployeesByLastName/{ln}
	@RequestMapping(value="/getEmployeesByLastName/{lastname}", method=RequestMethod.GET)
	@ResponseBody
	public String getEmployeeByLastName(@PathVariable String lastname) throws JsonProcessingException {
		List<Employee> list = employeeList.getEmployeesByLastName(lastname);
		return listToString(list);
	}
	
	//http://localhost:8080/EmployeePortal/getAllEmployees
	@RequestMapping(value="/getAllEmployees", method=RequestMethod.GET)
	@ResponseBody
	public String getAllEmployees() throws JsonProcessingException {
		return listToString(employeeList.getList());
	}
	
	/*
	{"employeeId":"emp6","firstName":"Test","lastName":"Employee","middleName":"XYZ","dateOfBirth":413454322345,"passportNumber":"67890123","ssn":"SSN12435","employeeAddresses":[]} 
	 */
	//http://localhost:8080/EmployeePortal/addEmployee
	@RequestMapping(value="/addEmployee", method=RequestMethod.POST)
	public ModelAndView addEmployee(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
		Employee em = mapper.readValue(json, Employee.class);
		employeeList.addToList(em);
		
		return new ModelAndView("redirect:http://localhost:8080/EmployeePortal/getAllEmployees");
	}
	
	//http://localhost:8080/EmployeePortal/removeEmployeeById/{id}
	@RequestMapping(value="/removeEmployeeById/{employeeId}", method=RequestMethod.POST)
	public ModelAndView removeEmployeeById(@PathVariable String employeeId) throws JsonProcessingException {
		employeeList.removeEmployeeById(employeeId);
		
		return new ModelAndView("redirect:http://localhost:8080/EmployeePortal/getAllEmployees");
	}
	
	
	//Helper method to convert list to string of jsons
	private String listToString(List<Employee> list) throws JsonProcessingException {
		String result = "";
		for(int i = 0; i < list.size(); i++) {
			result += "\n" + mapper.writeValueAsString(list.get(i));
		}
		return result;
	}
}
