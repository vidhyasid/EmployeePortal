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
    public List<Employee> list() {
		Query query = getCurrentSession().createSQLQuery("select * from employee_info");
    	List list = query.list();
    	
        /*@SuppressWarnings("unchecked")
        List<Employee> listUser = (List<Employee>) getCurrentSession()
                .createCriteria(Employee.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();*/
 
        return list;
    }
}
