package com.v2soft.training.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.v2soft.training.dataModel.EmployeeInfo;
import com.v2soft.training.dataModel.LoginInfo;
import com.v2soft.training.dataModel.AddressTypeInfo;
import com.v2soft.training.dataModel.EmployeeAddressIdInfo;
import com.v2soft.training.dataModel.EmployeeAddressInfo;
import com.v2soft.training.model.AddressType;
import com.v2soft.training.model.Employee;
import com.v2soft.training.model.EmployeeAddress;
import com.v2soft.training.model.Login;
@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	
	private SessionFactory sessionFactory;

	/*@Override
	public String save(Employee employee) {
		 sessionFactory.getCurrentSession().save(employee);
	      return employee.getEmployeeId();
	}*/
	@Override
	public void save(Employee employee) {		
		sessionFactory.getCurrentSession().persist(employee);
		//sessionFactory.getCurrentSession().save(employee);	     
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public EmployeeInfo get(String id) {
		EmployeeInfo employeeInfo = new EmployeeInfo();
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = builder.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        cq.select(root).where(builder.equal(root.get("employeeId"), id));
        Query<Employee> query = session.createQuery(cq);
        Employee employee = query.getSingleResult();
        
  	  employeeInfo.setEmployeeId(employee.getEmployeeId());
  	  employeeInfo.setFirstName(employee.getFirstName());
  	  employeeInfo.setLastName(employee.getLastName());
  	  employeeInfo.setMiddleName(employee.getMiddleName());
  	  employeeInfo.setDateOfBirth(employee.getDateOfBirth());
  	  employeeInfo.setPassportNumber(employee.getPassportNumber());
  	  employeeInfo.setSsn(employee.getSsn());
  	  Set<EmployeeAddressInfo> employeeAddresses = new HashSet<EmployeeAddressInfo>();
  	  for(EmployeeAddress empAddress:employee.getEmployeeAddresses()) {
  		  
  		  EmployeeAddressInfo employeeAddressInfo = new EmployeeAddressInfo();
  		 
  		  AddressTypeInfo AddressTypeInfo = new AddressTypeInfo();
  		  AddressTypeInfo.setId(empAddress.getAddressType().getId());
  		  AddressTypeInfo.setType(empAddress.getAddressType().getType());
  		  
  		  EmployeeAddressIdInfo employeeAddressIdInfo = new EmployeeAddressIdInfo();
  		  employeeAddressIdInfo.setAddressTypeId(empAddress.getId().getAddressTypeId());
  		  employeeAddressIdInfo.setEmployeeId(empAddress.getId().getEmployeeId());
  		  
  		  employeeAddressInfo.setId(employeeAddressIdInfo);
  		  employeeAddressInfo.setAddressType(AddressTypeInfo);
  		  employeeAddressInfo.setAddressLineOne(empAddress.getAddressLine1());
  		  employeeAddressInfo.setAddressLineTwo(empAddress.getAddressLine2());
  		  employeeAddressInfo.setCity(empAddress.getAddressLine2());
  		  employeeAddressInfo.setState(empAddress.getState());
  		  employeeAddressInfo.setZipcode(empAddress.getZipCode());
  		  employeeAddressInfo.setZipfour(empAddress.getZip4());
  		  employeeAddresses.add(employeeAddressInfo);
  		  
  	  }
  	  employeeInfo.setEmployeeAddresses(employeeAddresses);
  	  
		
		return employeeInfo;
	}

	@Override
	public List <EmployeeInfo> list() {
		
		/*
		 * Supplier result = (Supplier) getSession().find(Supplier.class, id);
		 * Hibernate.initialize(result.getIngredients()); return result;
		 */
		List<EmployeeInfo> employeeList = new ArrayList<>();
		 Session session = sessionFactory.getCurrentSession();
		 Query<Employee> query=session.createQuery("from Employee");//here persistent class name is Emp  
		 //List<Employee> list =query.list(); 
	     /* CriteriaBuilder cb = session.getCriteriaBuilder();
	      CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
	      Root<Employee> root = cq.from(Employee.class);
	      cq.select(root);
	      Query<Employee> query = session.createQuery(cq);*/
		 	query.setFirstResult(0 * 4);
	        query.setMaxResults(4);
		 List <Employee> emp = query.getResultList();
	      for(Employee employee: emp) {
	    	  EmployeeInfo employeeInfo = new EmployeeInfo();
	    	  employeeInfo.setEmployeeId(employee.getEmployeeId());
	    	  employeeInfo.setFirstName(employee.getFirstName());
	    	  employeeInfo.setLastName(employee.getLastName());
	    	  employeeInfo.setMiddleName(employee.getMiddleName());
	    	  employeeInfo.setDateOfBirth(employee.getDateOfBirth());
	    	  employeeInfo.setPassportNumber(employee.getPassportNumber());
	    	  employeeInfo.setSsn(employee.getSsn());	    	  
	    	  Set<EmployeeAddressInfo> employeeAddresses = new HashSet<EmployeeAddressInfo>();
	    	  for(EmployeeAddress empAddress:employee.getEmployeeAddresses()) {
	    		  
	    		  EmployeeAddressInfo employeeAddressInfo = new EmployeeAddressInfo();
	    		 
	    		  AddressTypeInfo AddressTypeInfo = new AddressTypeInfo();
	    		  AddressTypeInfo.setId(empAddress.getAddressType().getId());
	    		  AddressTypeInfo.setType(empAddress.getAddressType().getType());
	    		  
	    		  EmployeeAddressIdInfo employeeAddressIdInfo = new EmployeeAddressIdInfo();
	    		  employeeAddressIdInfo.setAddressTypeId(empAddress.getId().getAddressTypeId());
	    		  employeeAddressIdInfo.setEmployeeId(empAddress.getId().getEmployeeId());
	    		  
	    		  employeeAddressInfo.setId(employeeAddressIdInfo);
	    		  employeeAddressInfo.setAddressType(AddressTypeInfo);
	    		  employeeAddressInfo.setAddressLineOne(empAddress.getAddressLine1());
	    		  employeeAddressInfo.setAddressLineTwo(empAddress.getAddressLine2());
	    		  employeeAddressInfo.setCity(empAddress.getAddressLine2());
	    		  employeeAddressInfo.setState(empAddress.getState());
	    		  employeeAddressInfo.setZipcode(empAddress.getZipCode());
	    		  employeeAddressInfo.setZipfour(empAddress.getZip4());
	    		  employeeAddresses.add(employeeAddressInfo);
	    		  
	    	  }
	    	  employeeInfo.setEmployeeAddresses(employeeAddresses);
	    	  employeeList.add(employeeInfo);
	      }
	      
	      return employeeList;
	}

	@Override
	public void update(String id, Employee employee) {
		Session session = sessionFactory.getCurrentSession();
	      Employee newEmployee = session.byId(Employee.class).load(id);
	      newEmployee.setEmployeeId(employee.getEmployeeId());
	      newEmployee.setFirstName(employee.getFirstName());
	      newEmployee.setLastName(employee.getLastName());
	      newEmployee.setMiddleName(employee.getMiddleName());
	      newEmployee.setDateOfBirth(employee.getDateOfBirth());
	      newEmployee.setPassportNumber(employee.getPassportNumber());
	      newEmployee.setSsn(employee.getSsn());
	      session.flush();
	}

	@Override
	public void delete(String id) {
		Session session = sessionFactory.getCurrentSession();
		 Employee employee = session.byId(Employee.class).load(id);
	      session.delete(employee);
	}
	@Override
	public  EmployeeAddressInfo getAddress(String employeeId, String address_type) {

		 Session session = sessionFactory.getCurrentSession();
	       /* CriteriaBuilder builder = session.getCriteriaBuilder();
	        
	        //Subquery to capture addresstype id
	        CriteriaQuery<AddressType> subquery = builder.createQuery(AddressType.class);
	        Root<AddressType> subroot = subquery.from(AddressType.class);
	        subquery.select(subroot).where(builder.equal(subroot.get("type"), address_type));
	        Query<AddressType> subq = session.createQuery(subquery);
	        AddressType addressType = subq.getSingleResult();
	       
	        //Query to use id from subquery and employeeid to receive address
	        CriteriaQuery<EmployeeAddress> query = builder.createQuery(EmployeeAddress.class);
	        Root<EmployeeAddress> root = query.from(EmployeeAddress.class);
	        query.where(
	                builder.and(
	                builder.equal(root.get("id").get("employeeId"), employeeId)),
	                builder.equal(root.get("id").get("addressTypeId"), addressType.getId())
	        );
	       
	        Query<EmployeeAddress> q = session.createQuery(query);
	        EmployeeAddress employeeAddress = q.getSingleResult();*/
		 
		 Query<AddressType> subquery= session.createQuery("from AddressType where type = '"+address_type+"'");		
		 AddressType addressType = subquery.getSingleResult();
		 int addressTypeId = addressType.getId();
	     Query<EmployeeAddress> query = session.createQuery("from EmployeeAddress where id.employeeId ='"+employeeId+"'"+"and addressType.id = '"+addressTypeId+"'") ; 
	    // query.setParameter("employeeId", employeeId);
	    // query.setParameter("addressTypeId", addressTypeId);
	     EmployeeAddress employeeAddress = query.getSingleResult();
	     EmployeeAddressInfo employeeAddressInfo = new EmployeeAddressInfo();
	    		 
	    		  AddressTypeInfo AddressTypeInfo = new AddressTypeInfo();
	    		  AddressTypeInfo.setId(employeeAddress.getAddressType().getId());
	    		  AddressTypeInfo.setType(employeeAddress.getAddressType().getType());
	    		  
	    		  EmployeeAddressIdInfo employeeAddressIdInfo = new EmployeeAddressIdInfo();
	    		  employeeAddressIdInfo.setAddressTypeId(employeeAddress.getId().getAddressTypeId());
	    		  employeeAddressIdInfo.setEmployeeId(employeeAddress.getId().getEmployeeId());
	    		  
	    		  employeeAddressInfo.setId(employeeAddressIdInfo);
	    		  employeeAddressInfo.setAddressType(AddressTypeInfo);
	    		  employeeAddressInfo.setAddressLineOne(employeeAddress.getAddressLine1());
	    		  employeeAddressInfo.setAddressLineTwo(employeeAddress.getAddressLine2());
	    		  employeeAddressInfo.setCity(employeeAddress.getAddressLine2());
	    		  employeeAddressInfo.setState(employeeAddress.getState());
	    		  employeeAddressInfo.setZipcode(employeeAddress.getZipCode());
	    		  employeeAddressInfo.setZipfour(employeeAddress.getZip4());
	    		 
	    		  
	    	  
	        
	        return employeeAddressInfo;
	}
	/*@Transactional
    @JsonIgnore
    public List<EmployeeAddress> getEmployeeAddressByIdAndType(String address_type, String employee_id) {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
       
        //Subquery to capture addresstype id
        CriteriaQuery<AddressType> subquery = builder.createQuery(AddressType.class);
        Root<AddressType> subroot = subquery.from(AddressType.class);
        subquery.select(subroot).where(builder.equal(subroot.get("type"), address_type));
        Query<AddressType> subq = getCurrentSession().createQuery(subquery);
        AddressType at = subq.getSingleResult();
       
        //Query to use id from subquery and employeeid to receive address
        CriteriaQuery<EmployeeAddress> query = builder.createQuery(EmployeeAddress.class);
        Root<EmployeeAddress> root = query.from(EmployeeAddress.class);
        query.where(
                builder.and(
                builder.equal(root.get("id").get("employeeId"), employee_id)),
                builder.equal(root.get("id").get("addressTypeId"), at.getId())
        );
       
        Query<EmployeeAddress> q = getCurrentSession().createQuery(query);
        return q.getResultList();
    }*/
	@Override
	public List<EmployeeAddressInfo> addressList() {
		List<EmployeeAddressInfo> employeeAddressList = new ArrayList<>();
		 Session session = sessionFactory.getCurrentSession();
	      CriteriaBuilder cb = session.getCriteriaBuilder();
	      CriteriaQuery<EmployeeAddress> cq = cb.createQuery(EmployeeAddress.class);
	      Root<EmployeeAddress> root = cq.from(EmployeeAddress.class);
	      cq.select(root);
	      Query<EmployeeAddress> query = session.createQuery(cq);
	      List <EmployeeAddress> employeeAddresses = query.getResultList();
	      for(EmployeeAddress empAddress:employeeAddresses) {
	      EmployeeAddressInfo employeeAddressInfo = new EmployeeAddressInfo();
	      AddressTypeInfo AddressTypeInfo = new AddressTypeInfo();
		  AddressTypeInfo.setId(empAddress.getAddressType().getId());
		  AddressTypeInfo.setType(empAddress.getAddressType().getType());
		  
		  EmployeeAddressIdInfo employeeAddressIdInfo = new EmployeeAddressIdInfo();
		  employeeAddressIdInfo.setAddressTypeId(empAddress.getId().getAddressTypeId());
		  employeeAddressIdInfo.setEmployeeId(empAddress.getId().getEmployeeId());
		  
		  employeeAddressInfo.setId(employeeAddressIdInfo);
		  employeeAddressInfo.setAddressType(AddressTypeInfo);
		  employeeAddressInfo.setAddressLineOne(empAddress.getAddressLine1());
		  employeeAddressInfo.setAddressLineTwo(empAddress.getAddressLine2());
		  employeeAddressInfo.setCity(empAddress.getAddressLine2());
		  employeeAddressInfo.setState(empAddress.getState());
		  employeeAddressInfo.setZipcode(empAddress.getZipCode());
		  employeeAddressInfo.setZipfour(empAddress.getZip4());
		  employeeAddressList.add(employeeAddressInfo);  
	  }
	      return employeeAddressList;
	}
	@Override
	public List <Object[]> projectedList(){
		List<EmployeeInfo> employeeList = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
				
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
		Root<Employee> root = query.from(Employee.class);
		query.multiselect(root.get("employeeId"), root.get("firstName"), root.get("lastName"),root.get("ssn"));
		List<Object[]> resultList = session.createQuery(query).getResultList();
		return resultList;
	}
	@Override
	public Long getCount() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Employee> root = query.from(Employee.class);
		query.multiselect(builder.count(root));
		Long count = session.createQuery(query).getSingleResult();
		return count;
	}
	/*@Override
	public List<EmployeeInfo> limitedlist() {
		int pageSize = 4;
		int lastPageNumber = (int) (Math.ceil(getCount() / pageSize));
		
	}*/
	@Override
	public EmployeeInfo validateUser(LoginInfo login) {
		
		String id = login.getEmployeeinfo().getEmployeeId();
		EmployeeInfo employeeInfo = new EmployeeInfo();
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = builder.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        cq.select(root).where(builder.equal(root.get("employeeId"), id));
        Query<Employee> query = session.createQuery(cq);
        Employee employee = query.getSingleResult();
        employeeInfo.setEmployeeId(employee.getEmployeeId());
  	  	employeeInfo.setFirstName(employee.getFirstName());
	  	employeeInfo.setLastName(employee.getLastName());
	  	employeeInfo.setMiddleName(employee.getMiddleName());
	  	employeeInfo.setDateOfBirth(employee.getDateOfBirth());
	  	employeeInfo.setPassportNumber(employee.getPassportNumber());
	  	employeeInfo.setSsn(employee.getSsn());	
                
       return employeeInfo;
	}
	
	
}
	


