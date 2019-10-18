<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Employee Information</title>
</head>
<body>
	<table border="2" width="70%" cellpadding="2">  
	<tr>
		<th>Employee Id</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Middle Name</th>
		<th>Date Of Birth</th>
		<th>SSN</th>		
		<th>Passport Number</th>	
		<th>Current Address</th>
		<th>Permanent Address</th>			
	</tr>
	<c:forEach items="${lists}" var="emp">
		<tr>		
			<td>${emp.employeeId}</td>  
   			<td>${emp.firstName}</td>  
   			<td>${emp.lastName}</td>  
  		    <td>${emp.middleName}</td>
  		    <td>${emp.dateOfBirth}</td> 
  		    <td>${emp.ssn}</td>
  		    <td>${emp.passportNumber}</td> 
  			<c:forEach items="${emp.employeeAddresses}" var="address">	    	
  		    <td>${address.addressLineOne}${address.addressLineTwo}${address.city}${address.state}${address.zipcode}${address.zipfour}</td> 	
  		    </c:forEach>	   
		</tr> 		
	</c:forEach>	
	</table>	
</body>
</html>