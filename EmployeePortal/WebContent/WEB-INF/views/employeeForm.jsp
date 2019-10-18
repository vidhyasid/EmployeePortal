<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>   
     	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>EmployeeForm</title>
	</head>
    <body>
         <form:form id="EmployeeForm" modelAttribute="employeeForm" action="displayEmployee" method="post">
       
         <table align="center">
              <tr>	               
              <td>
              	<form:label path="employeeId">Employee Id: </form:label>
              </td>
                   <td>
                     <form:input path="employeeId" name="employeeId" id="employeeId" />
                   </td>
              </tr>
              <tr>
               <td>
              	<form:label path="firstName">First Name: </form:label>
              </td>
                   <td>
                     <form:input path="firstName" name="firstName" id="firstName" />
                   </td>
              </tr>
               <tr>
               <td>
              	<form:label path="lastName">Last Name: </form:label>
              </td>
                   <td>
                     <form:input path="lastName" name="lastName" id="firstName" />
                   </td>
              </tr>
              <tr>
               <td>
              	<form:label path="dateOfBirth">Date Of Birth(yyyy-mm-dd): </form:label>
              </td>
                   <td>                
                     <form:input path="dateOfBirth" name="dateOfBirth" id="dateOfBirth" />
                   </td>
              </tr>
              <tr>
              <td>
              	<form:label path="ssn">SSN : </form:label>
              </td>
                   <td>
                     <form:input path="ssn" name="ssn" id="ssn" />
                   </td>
              </tr>
              <tr>
              <td>
              	<form:label path="passportNumber">Passport Number : </form:label>
              </td>
                   <td>
                     <form:input path="passportNumber" name="passportNumber" id="passportNumber" />
                   </td>
              </tr> 
              <tr>
              <td>
              	<form:label path="addressLineOne">Address Line 1 : </form:label>
              </td>
                   <td>
                     <form:input path="addressLineOne" name="addressLineOne" id="addressLineOne" />
                   </td>
              </tr>
              <tr>
              <td>
              	<form:label path="addressLineTwo">Address Line 2 : </form:label>
              </td>
                   <td>
                     <form:input path="addressLineTwo" name="addressLineTwo" id="addressLineTwo" />
                   </td>
              </tr>
              <tr>
              <td>
              	<form:label path="city">City : </form:label>
              </td>
                   <td>
                     <form:input path="city" name="city" id="city" />
                   </td>
              </tr>
              <tr>
              <td>
              	<form:label path="state">State : </form:label>
              </td>
                   <td>
                     <form:input path="state" name="state" id="state" />
                   </td>
              </tr>
              <tr>
              <td>
              	<form:label path="zipcode">Zip Code : </form:label>
              </td>
                   <td>
                     <form:input path="zipcode" name="zipcode" id="zipcode" />
                   </td>
              </tr>
              <tr>
              <td>
              	<form:label path="zipfour">Zip 4 : </form:label>
              </td>
                   <td>
                     <form:input path="zipfour" name="zipfour" id="zipfour" />
                   </td>
              </tr>
               <tr>
                        <td></td>
                        <td align="left">
                            <form:button id="submit" name="submit">Submit</form:button>
                        </td>
                    </tr>
                 
              <tr></tr>                   
            </table>
            </form:form>
</html>