package com.v2soft.training.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.v2soft.training.dataModel.LoginInfo;
import com.v2soft.training.model.Employee;

import com.v2soft.training.service.EmployeeService;

@Controller
@RequestMapping("/")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService; 
	
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
	
	  @RequestMapping(value="/getEmployeeList", method=RequestMethod.GET)
	  @ResponseBody
		public String getAllEmployees() throws JsonProcessingException {
		    ObjectMapper mapper = new ObjectMapper();
		    List <EmployeeInfo> list = employeeService.list(); 
			return mapper.writeValueAsString(list);
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
	  
	  

	    /* @RequestMapping(value ="/SearchEmployee", method=RequestMethod.POST)
	     @ResponseBody
	     public String SearchEmployee(@RequestParam(value="Id",required=false) String Id,
	    		                      @RequestParam(value="Fname",required=false) String Fname,
	    		                      @RequestParam(value="Lname",required=false) String Lname,
	    		                      @RequestParam(value="Mname",required=false) String Mname,
	    		                      @RequestParam(value="DOB",required=false) String DOB,
	    		                      @RequestParam(value="Passport",required=false) String Passport,
	    		                      @RequestParam(value="SSN",required=false) String SSN)    		                    
	    		 throws JsonProcessingException, ParseException {
	    	 
	    	 List<Employee> employeeList = employeeService.list();	    	
	    	 ObjectMapper objectMapper = new ObjectMapper();
	         String json = "";
	    	
			for(Employee employee: employeeList) {
	    		 if(Id !=null && Id.equals(employee.getEmployeeId())){
	    			 json = json.concat(objectMapper.writeValueAsString(employee)) + "\n";
	    		 }
	    		 else if(Fname !=null && Fname.equals(employee.getFirstName())){
	    			 json = json.concat(objectMapper.writeValueAsString(employee)) + "\n";
	    		 }
	    		 else if(Lname !=null && Lname.equals(employee.getLastName())){
	    			 json = json.concat(objectMapper.writeValueAsString(employee)) + "\n";
	    		 }
	    		 else if(Mname !=null && Mname.equals(employee.getMiddleName())){
	    			 json = json.concat(objectMapper.writeValueAsString(employee)) + "\n";
	    		 }
	    		 else if(DOB !=null && DOB.equals(objectMapper.writeValueAsString(employee.getDateOfBirth()))){
	    			 json = json.concat(objectMapper.writeValueAsString(employee)) + "\n";
	    		 }
	    		 else if(Passport !=null && Passport.equals(employee.getPassportNumber())){
	    			 json = json.concat(objectMapper.writeValueAsString(employee)) + "\n";
	    		 }
	    		 else if(SSN !=null && SSN.equals(employee.getSsn())){
	    			 json = json.concat(objectMapper.writeValueAsString(employee)) + "\n";
	    		 }
	    	 }  
	    	 if(json.equals(""))
	    	 {
	    		 json = "No Such Employee";
	    	 }
	    	 return json;
	     }*/

	     @RequestMapping(value="/deleteEmployeeById/{employeeId}", method=RequestMethod.GET)
	 	@ResponseBody 
	 	public String deleteEmployeeGet(@PathVariable("employeeId") String employeeId){ 
	 		employeeService.delete(employeeId);
	 		return ("Employee with id"+employeeId+"has been deleted");
	 	}
	     
	   /*  @RequestMapping(value="/getLoginInfo", method=RequestMethod.GET)
	     @ResponseBody
	     public String getLoginInfo() throws JsonProcessingException {
	    	 ObjectMapper mapper = new ObjectMapper();
	         return mapper.writeValueAsString(employeeService.getLoginInfo());
	     }*/
	    
	
	 @RequestMapping(value = "/login", method = RequestMethod.GET)	 
	 public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		 ModelAndView mav = new ModelAndView("login"); 
		 //return new ModelAndView("login", "command", new LoginInfo());
		 mav.addObject("login",new LoginInfo());
		 return mav; 
		 }
	 
	     @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	     public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
	     @ModelAttribute("login") LoginInfo login) {
	       ModelAndView mav = null;
	       EmployeeInfo employee = employeeService.validateUser(login);
	     
	       if (null != employee) {
	       mav = new ModelAndView("welcome");
	       mav.addObject("firstname",employee.getFirstName());
	       } else {
	       mav = new ModelAndView("login");
	       mav.addObject("message", "Username or Password is wrong!!");
	       }
	       return mav;
	     }
}

