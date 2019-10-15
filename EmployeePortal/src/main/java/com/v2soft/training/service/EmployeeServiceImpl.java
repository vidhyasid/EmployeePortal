package com.v2soft.training.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.v2soft.training.dao.EmployeeDao;
import com.v2soft.training.dataModel.EmployeeAddressInfo;
import com.v2soft.training.dataModel.EmployeeInfo;
import com.v2soft.training.dataModel.LoginInfo;
import com.v2soft.training.model.AddressType;
import com.v2soft.training.model.Employee;
import com.v2soft.training.model.EmployeeAddress;
import com.v2soft.training.model.Login;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
	@Autowired
	private EmployeeDao employeeDao;
    
    @Transactional
	@Override
	public void save(Employee employee) {
		employeeDao.save(employee);
	}
    
    @Transactional
	@Override
	public EmployeeInfo get(String id) {
		return employeeDao.get(id);
	}

    @Transactional
	@Override
	public List <EmployeeInfo> list() {
		return employeeDao.list();
	}

    @Transactional
	@Override
	public void update(String id, Employee employee) {
		employeeDao.update(id, employee);
	}

    @Transactional
	@Override
	public void delete(String id) {
		employeeDao.delete(id);
		
	}
    
    @Transactional
	@Override
	public EmployeeAddressInfo  getAddress(String employeeId, String address_type) {
		return	employeeDao.getAddress(employeeId, address_type);
		
	}

    @Transactional
	@Override
	public List<EmployeeAddressInfo> addressList() {
		return employeeDao.addressList();
	}

    @Transactional
	@Override
	public List<Object[]> projectedList() {
		return employeeDao.projectedList();
	}

	 @Transactional
	@Override
	public Long getCount() {
		return employeeDao.getCount();
	}

	@Override
	public EmployeeInfo validateUser(LoginInfo login) {
		return employeeDao.validateUser(login);
	}
	
    
}
