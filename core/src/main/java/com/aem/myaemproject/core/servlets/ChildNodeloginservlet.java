package com.aem.myaemproject.core.servlets;

import java.io.IOException;

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

import com.aem.myaemproject.core.interfac.ChildNodesCreationInt;


@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=Employee Register Node Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
         "sling.servlet.paths=" + "/bin/emploginnodeservlet"            
})
public class ChildNodeloginservlet extends SlingAllMethodsServlet{

	private static final Logger log = LoggerFactory.getLogger(ChildNodesCreationServlet.class);

	@Reference
	private ChildNodesCreationInt interfac;
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String uname = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		
		boolean b = interfac.loginEmployee(uname,pwd);
		log.info("Registration Done="+b);
		
		if (b) {
			response.getWriter().print("logged in successfully");
		} else {
			response.getWriter().print("login failed, please try again");
		}
		
		response.getWriter().print("EmployRegistration login doGet servlet called");
		super.doGet(request, response);
	}
	
	/*@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		
		boolean b = interfac.loginEmployee(uname,pwd);
		//log.info("Registration Done="+b);
		
		if (b) {
			response.getWriter().print("logged in successfully "+b);
		} else {
			response.getWriter().print("login failed, please try again");
		}
		
		response.getWriter().print("EmployRegistration login doPost servlet called");
		
		super.doPost(request, response);
	}*/
	
}
