package com.v2soft.training.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.v2soft.training.dataModel.EmployeeAddressInfo;
import com.v2soft.training.dataModel.EmployeeInfo;
import com.v2soft.training.dataModel.EmployeeModel;
import com.v2soft.training.dataModel.LoginIdInfo;
import com.v2soft.training.dataModel.LoginInfo;
import com.v2soft.training.dataModel.LoginUser;
import com.v2soft.training.filter.AddResponseHeaderFilter;
import com.v2soft.training.model.Employee;
import com.v2soft.training.model.Login;
import com.v2soft.training.model.LoginId;
import com.v2soft.training.service.EmployeeService;

@Controller
@RequestMapping("/")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService; 
	//private String UUID;
	
	@RequestMapping(value="/getEmployeeById/{employeeId}", method=RequestMethod.GET)
	@ResponseBody 
	public String printEmployeeGet(@PathVariable("employeeId") String employeeId) throws JsonProcessingException { 
		EmployeeInfo employeeInfo = employeeService.get(employeeId); 
		ObjectMapper objectMapper = new ObjectMapper(); 
		String json = objectMapper.writeValueAsString(employeeInfo);
		return json;
	}
	 
	@RequestMapping(value="/getAddressById/{employeeId}/{addressType}", method=RequestMethod.GET)
	@ResponseBody 
	public String printEmployeeAddressGet(@PathVariable("employeeId") String employeeId,@PathVariable("addressType") String address_type) throws JsonProcessingException { 
		//AddressType addressType =employeeService.getAddressType(addressTypeId);
		EmployeeAddressInfo employeeAddress = employeeService.getAddress(employeeId, address_type);
		ObjectMapper objectMapper = new ObjectMapper(); 
		String json = objectMapper.writeValueAsString(employeeAddress);
		return json;
	}
	
	 
	  
	  @RequestMapping(value="/getProjectedList", method=RequestMethod.GET)
	  @ResponseBody
		public String getProjectedList() throws JsonProcessingException {
		    ObjectMapper mapper = new ObjectMapper();
		    List <Object[]> list = employeeService.projectedList(); 
			return mapper.writeValueAsString(list);
		}
	  @RequestMapping(value="/getRowCount", method=RequestMethod.GET)
	  @ResponseBody
		public String getRowCount() throws JsonProcessingException {
		    ObjectMapper mapper = new ObjectMapper();
		    long count = employeeService.getCount(); 
			return mapper.writeValueAsString(count);
		}
	  
	  @RequestMapping(value="/getEmployeeAddressList", method=RequestMethod.GET)
	  @ResponseBody
		public String getAllEmployeeAddresses() throws JsonProcessingException {
		    ObjectMapper mapper = new ObjectMapper();
		    List <EmployeeAddressInfo> list = employeeService.addressList(); 
			return mapper.writeValueAsString(list);
		}
	  
	  @RequestMapping(value ="/addEmployeeObject", method=RequestMethod.POST)
	  @ResponseBody
		  public String addEmployee( HttpServletRequest request) throws ParseException, IOException {
		    	 	      
			  ObjectMapper mapper = new ObjectMapper();
			  Employee employee = mapper.readValue(request.getInputStream(),Employee.class);
			  employeeService.save(employee);
			  return ("The Employee with ID"+employee.getEmployeeId()+"is added");
	   	 
	  }

	  @RequestMapping(value = { "/updateEmployee" }, method = RequestMethod.POST)
	  @ResponseBody  
		   public String updateEmployee(HttpServletRequest request) throws IOException,ParseException {
			   ObjectMapper mapper = new ObjectMapper();
			   Employee employee = mapper.readValue(request.getInputStream(),Employee.class);	    
			   employeeService.update(employee.getEmployeeId(), employee);
			   return("Employee with Id" + employee.getEmployeeId() + "has been updated");
	     }
	  
	  @RequestMapping(value = "/employeeForm", method = RequestMethod.GET)	 
		 public ModelAndView employeeForm(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("employeeForm"); 		 
			 mav.addObject("employeeForm",new EmployeeModel());
			 return mav; 
			 }
	     @RequestMapping(value="/deleteEmployeeById/{employeeId}", method=RequestMethod.GET)
	 	@ResponseBody 
	 	public String deleteEmployeeGet(@PathVariable("employeeId") String employeeId){ 
	 		employeeService.delete(employeeId);
	 		return ("Employee with id"+employeeId+"has been deleted");
	 	}
	     
	     @RequestMapping(value = "/home", method = RequestMethod.GET)	 
		 public ModelAndView homePage(HttpServletRequest request, HttpServletResponse response) {
			 ModelAndView mav = new ModelAndView("home"); 		 			
			 return mav; 
			 }
	     
	 @RequestMapping(value = "/login", method = RequestMethod.GET)	 
	 public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		 ModelAndView mav = new ModelAndView("login"); 		 
		 mav.addObject("login",new LoginUser());
		 return mav; 
		 }
	 
	 @RequestMapping(value = "/logout", method = RequestMethod.GET)	 
	 public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		employeeService.setLogoutStatus(request.getSession().getId());
		return new ModelAndView("login");
		 }
	 
	     @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	     public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
	     @ModelAttribute("login") LoginUser login) {
	       ModelAndView mav = null;
	       EmployeeInfo employee = employeeService.validateUser(login,request);	    
	       if (null != employee) {
	    	   Cookie cookie = new Cookie("loginSessionId", request.getSession().getId());
	    	   response.addCookie(cookie);
	    	   mav = new ModelAndView("welcome");
	    	   mav.addObject("firstname",employee.getFirstName());   	       
	       } else {
	    	   mav = new ModelAndView("login");
	    	   mav.addObject("message", "Username or Password is wrong!!");
	       }
	       return mav;
	     }
	     
	     @RequestMapping(value="/displayEmployee", method=RequestMethod.POST)		 
			public ModelAndView displayEmployee(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("employeeForm") EmployeeModel employees) throws ParseException {
		    ModelAndView model = null; 			
		      List<EmployeeInfo> list = employeeService.getEmployeeInfo(employees);   
		      if(null != list) {
		    	  model = new ModelAndView("employeeInformation");
		    	  model.addObject("lists", list);
		      }
		      else {
		    	  model = new ModelAndView("getEmployeeDetails");
		      }		     
			    return model;		
				
		     }
	     
	     @RequestMapping(value="/getEmployeeList", method=RequestMethod.GET)		 
			public ModelAndView getAllEmployees(HttpServletRequest request, HttpServletResponse response) {		
	    	 ModelAndView mav = null;
		    List <EmployeeInfo> list = employeeService.list(); 
			    mav = new ModelAndView("employeeInformation");
			    mav.addObject("lists", list);			    			    	 
	    		return mav;
	     }
	     	 
}

