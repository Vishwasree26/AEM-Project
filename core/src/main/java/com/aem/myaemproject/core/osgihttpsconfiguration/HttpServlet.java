package com.aem.myaemproject.core.osgihttpsconfiguration;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=HTTP servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/httpcall" })
public class HttpServlet extends SlingSafeMethodsServlet {
	
	private static final long serialVersionUID = -2014397651676211439L;
	
	private static final Logger log = LoggerFactory.getLogger(HttpServlet.class);
	
	@Reference
	private HttpService httpService;
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
		
		try {
		
		String jsonResponse = httpService.makeHttpCall();
		response.getWriter().println(jsonResponse);
		
		} catch (Exception e) {
			
			log.error(e.getMessage(), e);
		}
	}

}