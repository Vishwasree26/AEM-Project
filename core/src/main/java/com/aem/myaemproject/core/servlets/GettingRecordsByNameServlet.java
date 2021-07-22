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

import com.aem.myaemproject.core.bean.GettingRecordsByNameBean;
import com.aem.myaemproject.core.dbutil.GettingRecordsByNameDatabase;
import com.aem.myaemproject.core.interfac.GettingRecordsByNameInterface;




@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        
        "sling.servlet.paths=" + "/bin/gettingrecordsbynameservlet"
})
public class GettingRecordsByNameServlet extends SlingAllMethodsServlet{
	protected static final Logger log=LoggerFactory.getLogger(GettingRecordsByNameServlet.class);
	
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

		String Name =request.getParameter("name");
		
		try {
			GettingRecordsByNameBean bean = obj.gettingRecordsByName(Name);
			System.out.println("Employe data by Name"+bean);
			log.info("Successful . . Iam from servlet "+bean);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		response.getWriter().write("Hai, Iam From GettingRecordsByNameServlet");
	}
	
	/*protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doGet(request, response);
		response.getWriter().write("Hai, Iam From My Post Sling Servlet");
	}*/
	
}
