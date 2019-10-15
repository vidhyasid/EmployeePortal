package com.v2soft.training.dao;

import java.util.Collection;
import java.util.List;

import com.v2soft.training.dataModel.EmployeeAddressInfo;
import com.v2soft.training.dataModel.EmployeeInfo;
import com.v2soft.training.dataModel.LoginInfo;
import com.v2soft.training.model.AddressType;
import com.v2soft.training.model.Employee;
import com.v2soft.training.model.EmployeeAddress;
import com.v2soft.training.model.Login;

public interface EmployeeDao {
	
	//String save(Employee employee);
	 
	void save(Employee employee);
	
	EmployeeInfo get(String id);
	 
	List <EmployeeInfo> list();
	 
	void update(String id, Employee employee);
	 
	void delete(String id);
	
	EmployeeAddressInfo  getAddress(String employeeId,String address_type);
	
	List <EmployeeAddressInfo> addressList();
	
	List <Object[]> projectedList();
	
	Long getCount();
	
	EmployeeInfo validateUser(LoginInfo login);
	//List <EmployeeInfo> limitedlist();
	
	
}

