<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Welcome</title>
    </head>
    <body>
        <table>
            <tr>
                <td>Welcome ${firstname}</td>
            </tr>
            <tr>
            </tr>
            <tr>
            </tr>     
            
            <tr>
		        <td></td>
		        <td><a href="home">Home</a>
		        </td>
	        </tr>   
            
            <tr>
		        <td></td>
		       <%--  <a href="<c:url value="/logout" />">Logout</a> --%>
		        <td><a href="login">Logout</a>
		        </td>
	        </tr>  
	        
	        <tr>
		        <td></td>
		        <td><a href="/EmployeePortal/filter-response-header/getEmployeeList">All Employees</a>
		        </td>
	        </tr>  
	        
            <tr>
		        <td></td>
		        <td><a href="/EmployeePortal/filter-response-header/employeeForm">Get Employee Information</a>
		        </td>
	        </tr>
        </table>
        
        
        
    </body>
    </html>