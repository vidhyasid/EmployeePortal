package com.v2soft.training.dao;

import java.util.List;
 
import org.hibernate.Criteria;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.v2soft.training.entity.EmployeeInfo;
import com.v2soft.training.model.Employee;

public class EmployeeDao {
	
	//@Autowired
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    
    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
    
	@Transactional
    public List<Employee> getEmployeeList() {
		String query = "SELECT * FROM employee_info";
        return getCurrentSession().createSQLQuery(query).list();
    }
	
	@Transactional
	public List getEmployeeById(String employeeId) {
		String queryString = "SELECT * FROM employee_info WHERE employee_id = :id";
		Query query = getCurrentSession().createSQLQuery(queryString);
		query.setParameter("id", employeeId);
		
		if(query.getResultList().size() <= 0)
			return null;
		return query.getResultList();
	}
	
	/*@Transactional
	public boolean addNewEmployee(String employeeId) {
		String queryString = "SELECT * FROM employee_info WHERE employee_id = :id";
		Query query = getCurrentSession().createSQLQuery(queryString);
		query.setParameter("id", employeeId);
		if(query.getResultList().size() >= 1) {
			//update info instead?
			return false;
		} else {
			queryString = "INSERT INTO employee_info "
					+ "(employee_id, first_name, last_name, middle_name, "
					+ "date_of_birth, passport_number, ssn) "
					+ "VALUES :id, :fn, :ln, :mn, :dob, :pn, :ssn";
			query = getCurrentSession().createSQLQuery(queryString);
			query.setParameter("id", employeeId);
			return true;
		}
	}*/
}
