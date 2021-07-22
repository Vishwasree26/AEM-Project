package com.aem.myaemproject.core.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.myaemproject.core.dbutil.GettingRecordsByNameDatabase;
import com.aem.myaemproject.core.interfac.GettingRecordsByNameInterface;



@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        
        "sling.servlet.paths=" + "/bin/mydbservlet"
})
public class MyDBServlet extends SlingAllMethodsServlet{
	protected static final Logger log=LoggerFactory.getLogger(MyDBServlet.class);
	
	@Reference
	GettingRecordsByNameInterface obj;
	
	@Reference
	GettingRecordsByNameDatabase db;
	
	Connection con = null;
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doGet(request, response);
		
		//con = obj.getDataBaseConnection("aemproject");
		con = db.getDataBaseConnection("aemproject");
		log.info("connection success from servlet"+con);
		
		String Name =request.getParameter("name");
		String Email =request.getParameter("email");
		String Password =request.getParameter("pwd");
		log.info("Name "+Name+"Email"+Email+"Password"+Password);
		
		try {
			
			boolean b = obj.add(Name,Email,Password);
			log.info("Details inserted Successfully from servlet "+b);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		response.getWriter().write("Hai, Iam From My DBServlet");
	}
	
	/*protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doGet(request, response);
		response.getWriter().write("Hai, Iam From My Post Sling Servlet");
	}*/
	
}
