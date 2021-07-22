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

import com.aem.myaemproject.core.bean.ParentNodeDataRetrievalBean;
import com.aem.myaemproject.core.interfac.ParentNodeDataRetrievalint;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=Employee Register Node Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
         "sling.servlet.paths=" + "/bin/nodedataretrievalservlet"            
})


public class ParentNodeDataRetrievalServlet extends SlingAllMethodsServlet {

	@Reference
	private ParentNodeDataRetrievalint obj;
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ParentNodeDataRetrievalBean bean = obj.employeesParentData();
		String ID= bean.getEmpID();
		String Name = bean.getEmpName();
		//response.getWriter().print("JAVA ---> Employee ID : "+ID+" Employee Name : "+Name);
		
		/*Gson gson = new Gson();
		String jsonObj = gson.toJson(obj);*/
		
		//Creating object of ObjectMapper define in jackson API
		ObjectMapper Obj = new ObjectMapper();
		// Converting the java object into a JSON string
		String jsonstr = Obj.writeValueAsString(bean);
		// Displaying java object into a Json string
		response.getWriter().write("  & JSON ---> "+jsonstr);
		
		//response.getWriter().print("Hai, Iam from NodeDataRetrievalServlet ");
		//super.doGet(request, response);
	}
	
}
