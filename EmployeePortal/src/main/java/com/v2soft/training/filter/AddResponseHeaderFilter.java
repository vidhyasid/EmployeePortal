package com.v2soft.training.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

 

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.v2soft.training.dao.EmployeeDao;
import com.v2soft.training.dao.EmployeeDaoImpl;
import com.v2soft.training.model.LoginSession;


public class AddResponseHeaderFilter implements Filter {
 	
	//private EmployeeDao employeeDao;
	private SessionFactory sessionFactory;
	private List<String> excludedUrls;
	
	 public void setSessionFactory(SessionFactory sessionFactory){
	        this.sessionFactory = sessionFactory;
	    }
	    
	    public Session getCurrentSession() {
	        //return sessionFactory.getCurrentSession();
	        return sessionFactory.openSession();
	    }
	    
	
	  @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	    	   String excludePattern = filterConfig.getInitParameter("excludedUrls");
	           excludedUrls = Arrays.asList(excludePattern.split(","));
	           
	           ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
	           sessionFactory = (SessionFactory)ctx.getBean("sessionFactory");
	    }
	 
	
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {   	
       HttpServletResponse httpServletResponse = (HttpServletResponse) response;      
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;       
        String path = httpServletRequest.getServletPath();       
        if(!excludedUrls.contains(path)) {
        Cookie [] cookies =httpServletRequest.getCookies();
        String loginSessionId = "";
        if(cookies !=null) {
        for (Cookie cookie : cookies) {
			if (cookie.getName().equals("loginSessionId")) {
				 loginSessionId = cookie.getValue();
            }
        }
			 if(checkLoginSession(loginSessionId)) {
				 chain.doFilter(request, response);
	             System.out.println("THE STATUS IS ACTIVE");
			 }
	            else {
	                System.out.println("THE STATUS IS TERMINATED");
	                httpServletResponse.sendRedirect("/login.jsp");
	            }
	                
        }
        }
        else {
        chain.doFilter(request, response); 
        }
    }
@Transactional
public boolean checkLoginSession(String loginSessionId) {
    String createQuery = "from LoginSession";
    Query<LoginSession> query = getCurrentSession().createQuery(createQuery);
    String status = "";
    List<LoginSession> loginSession = query.getResultList();
    for(LoginSession lSession: loginSession) {
    	if(loginSessionId.equals(lSession.getId().getLoginSessionId())){
    		status = lSession.getStatus();
    	}
    }
    if(status.equals("A"))
        return true;   
    return false;
}
    @Override
    public void destroy() {
        // ...
    }
}
