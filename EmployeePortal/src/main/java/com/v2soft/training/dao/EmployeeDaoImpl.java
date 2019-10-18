package com.v2soft.training.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.v2soft.training.dataModel.EmployeeInfo;
import com.v2soft.training.dataModel.EmployeeModel;
import com.v2soft.training.dataModel.LoginUser;
import com.v2soft.training.filter.AddResponseHeaderFilter;
import com.v2soft.training.dataModel.AddressTypeInfo;
import com.v2soft.training.dataModel.EmployeeAddressIdInfo;
import com.v2soft.training.dataModel.EmployeeAddressInfo;
import com.v2soft.training.model.AddressType;
import com.v2soft.training.model.Employee;
import com.v2soft.training.model.EmployeeAddress;
import com.v2soft.training.model.Login;
import com.v2soft.training.model.LoginSession;
import com.v2soft.training.model.LoginSessionId;
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
		 Query<Employee> query=session.createQuery("from Employee");  
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
	
	@Override
	public EmployeeInfo validateUser(LoginUser loginUser,HttpServletRequest request) {
		String userName = loginUser.getUserName();
		String password = loginUser.getPassword();	
		Boolean validated = false;
		Session session = sessionFactory.getCurrentSession();	
		EmployeeInfo employeeInfo = new EmployeeInfo();
		String employeeId = null;
		Query<Login> subquery = session.createQuery("from Login");					 
		List<Login> login = subquery.getResultList();	
		
		for(Login l : login) {
			if(l.getId().getUserName().equals(userName)) {
				if(l.getPassword().equals(password)) {
					employeeId = l.getEmployeeinfo().getEmployeeId();
					validated = true;
				}
			}
		}
		
		if(null != employeeId && validated == true) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = builder.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        cq.select(root).where(builder.equal(root.get("employeeId"),employeeId));
        Query<Employee> query = session.createQuery(cq);
        Employee employee = query.getSingleResult();
        employeeInfo.setEmployeeId(employee.getEmployeeId());
  	  	employeeInfo.setFirstName(employee.getFirstName());
	  	employeeInfo.setLastName(employee.getLastName());
	  	employeeInfo.setMiddleName(employee.getMiddleName());
	  	employeeInfo.setDateOfBirth(employee.getDateOfBirth());
	  	employeeInfo.setPassportNumber(employee.getPassportNumber());
	  	employeeInfo.setSsn(employee.getSsn());	
	  	 
	  	
	  	LoginSession loginSession = new LoginSession();	 
	  	LoginSessionId loginSessionId = new LoginSessionId();
	  	loginSessionId.setLoginSessionId(request.getSession().getId());
	  	loginSessionId.setEmployeeId(employeeId);
	  	loginSessionId.setUserName(userName);
	  	loginSession.setId(loginSessionId);
	  	loginSession.setCreatedTime(new Date(request.getSession().getCreationTime()));
	  	loginSession.setLoginTime(new Date(request.getSession().getCreationTime()));
	  	loginSession.setStatus("A");
	  	loginSession.setCreatedBy("System");
	  	loginSession.setUpdatedBy("System");	  	
	  	
	  	session.save(loginSession);
	  	
		}
		else {
			employeeInfo = null;
		}
                
       return employeeInfo;
	}
	@Override
	public List<EmployeeInfo> getEmployeeInfo(EmployeeModel employeeInfo) throws ParseException {
		
		Session session = sessionFactory.getCurrentSession();
		Query<Employee> subquery = session.createQuery("from Employee");					 
		List<Employee> employeeList = subquery.getResultList();
		List<EmployeeInfo> employeeInfoList = new ArrayList<>();
		for(Employee employee: employeeList) {
			 if(employeeInfo.getEmployeeId() !=null && employeeInfo.getEmployeeId().equals(employee.getEmployeeId())){
				 employeeInfoList.add(getSingleEmployee(employee));   			 
			 }
			 else if(employeeInfo.getFirstName() !=null && employeeInfo.getFirstName().equals(employee.getFirstName())){
				if(employeeInfo.getLastName() !=null && employeeInfo.getLastName().equals(employee.getLastName())){
				employeeInfoList.add(getSingleEmployee(employee)); 
				}	
			 }
			 else if(employeeInfo.getPassportNumber() !=null && employeeInfo.getPassportNumber().equals(employee.getPassportNumber())){
				employeeInfoList.add(getSingleEmployee(employee)); 
			 }
			 else if(employeeInfo.getSsn() !=null && employeeInfo.getSsn().equals(employee.getSsn())){
				employeeInfoList.add(getSingleEmployee(employee)); 
			 }    
			 else if(employeeInfo.getDateOfBirth() !=null){   			
					if(employeeInfo.getDateOfBirth().equals(employee.getDateOfBirth().toString())){
						employeeInfoList.add(getSingleEmployee(employee)); 
					}  
			 }
		 }  	
		
		
		return employeeInfoList;

	}
	
	/*public Employee result (String queryString) {
		Session session = sessionFactory.getCurrentSession();
		 Query<Employee> query = session.createQuery(queryString);
		 return query.getSingleResult(); 
	}*/
	
	public EmployeeInfo getSingleEmployee(Employee employee) {
		
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
	  
		
		return employeeInfo;
	}
	@Override
	public String getSessionId(String sessionId) {
		Session session = sessionFactory.getCurrentSession();
		//Query<LoginSessionId> subquery = session.createQuery("from LoginSessionId WHERE loginSessionId ='"+sessionId+"'");
		
		 Query<LoginSession> query = session.createQuery("from LoginSession WHERE id.loginSessionId = '"+sessionId+"'");
		 LoginSession loginSession = query.getSingleResult();
		 return loginSession.getStatus();
	}
	@Override
	public void setLogoutStatus(String sessionId) {
		Session session = sessionFactory.getCurrentSession();
		 String createQuery = "from LoginSession";
		    Query<LoginSession> subquery = session.createQuery(createQuery);		    
		    List<LoginSession> loginSession = subquery.getResultList();
		    for(LoginSession lSession: loginSession) {
		    	if(sessionId.equals(lSession.getId().getLoginSessionId())){
		    		
		    	}
		    }
		//Query<LoginSession> query = session.createQuery("update LoginSession s set s.status='T' where s.id.loginSessionId='"+sessionId+"'");
		//LoginSession loginSession = query.getSingleResult();
		
	} 
}


/*if(employeeInfo.getEmployeeId() !=null){
	// Query<Employee> query = session.createQuery("from Employee WHERE employeeId = '"+employeeInfo.getEmployeeId()+"'");
	 employeeInfoList.add(getSingleEmployee(result ("from Employee WHERE employeeId = '"+employeeInfo.getEmployeeId()+"'")));   			 
}
else if(employeeInfo.getFirstName() !=null){
	// Query<Employee> query = session.createQuery("from Employee WHERE firstName = '"+employeeInfo.getFirstName()+"'");
	 employeeInfoList.add(getSingleEmployee(result ("from Employee WHERE firstName = '"+employeeInfo.getFirstName()+"'")));   	
}   
else if(employeeInfo.getLastName() !=null){
	// Query<Employee> query = session.createQuery("from Employee WHERE lastName = '"+employeeInfo.getLastName()+"'");
	 employeeInfoList.add(getSingleEmployee(result("from Employee WHERE lastName = '"+employeeInfo.getLastName()+"'")));   	
}  
else if(employeeInfo.getDateOfBirth() !=null){   			
	// Query<Employee> query = session.createQuery("from Employee WHERE dateOfBirth = '"+employeeInfo.getDateOfBirth()+"'");
	 employeeInfoList.add(getSingleEmployee(result("from Employee WHERE dateOfBirth = '"+employeeInfo.getDateOfBirth()+"'")));   			
}
else if(employeeInfo.getPassportNumber() !=null){
	// Query<Employee> query = session.createQuery("from Employee WHERE passportNumber = '"+employeeInfo.getPassportNumber()+"'");
	 employeeInfoList.add(getSingleEmployee(result("from Employee WHERE passportNumber = '"+employeeInfo.getPassportNumber()+"'"))); 
}
else if(employeeInfo.getSsn() !=null){
	 //Query<Employee> query = session.createQuery("from Employee WHERE ssn = '"+employeeInfo.getSsn()+"'");
	 employeeInfoList.add(getSingleEmployee(result("from Employee WHERE ssn = '"+employeeInfo.getSsn()+"'"))); 
}      */

