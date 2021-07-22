package com.aem.myaemproject.core.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.myaemproject.core.bean.ParentNodeDataRetrievalBean;
import com.aem.myaemproject.core.interfac.ParentNodeDataRetrievalint;


@Component(service=ParentNodeDataRetrievalint.class, immediate=true)
public class ParentNodeDataRetrievalimpl implements ParentNodeDataRetrievalint {

	private static Logger log = LoggerFactory.getLogger(ParentNodeDataRetrievalimpl.class);
	
	@Reference
	private ResourceResolverFactory resolverFactory;
	
	ResourceResolver resourceResolver = null;
	private Session session;
	Resource resource;
	String resourcePath = "/content/myaemproject/en/jcr:content/employees";
	ParentNodeDataRetrievalBean nodebean = null;
	
	@Override
	public ParentNodeDataRetrievalBean employeesParentData() {
		// TODO Auto-generated method stub
		try {
			
			resourceResolver = resolverFactory.getServiceResourceResolver(getSubServiceMap());
			session = resourceResolver.adaptTo(Session.class);		//adapt method is used to convert any type of object    
			log.info("session ****" + session);
			resource = resourceResolver.getResource(resourcePath);
			
			Node node = resource.adaptTo(Node.class);
			
			String EmpID = node.getProperty("EmpID").getValue().getString();
			String EmpName = node.getProperty("EmpName").getValue().getString();
			
			log.info(" EmpID ---- "+EmpID);
			log.info(" EmpName ---- "+EmpName);
			
			nodebean = new ParentNodeDataRetrievalBean();
			nodebean.setEmpID(EmpID);
			nodebean.setEmpName(EmpName);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	return nodebean;
		
	}
	
	private Map<String, Object> getSubServiceMap() {
		log.info("*****Inside getSubservice method **");
		Map<String, Object> serviceMap = null;

		try {

			serviceMap = new HashMap<String, Object>();
			serviceMap.put(ResourceResolverFactory.SUBSERVICE, "vishwasystemuser");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			log.info("errors ***" + errors.toString());
		}
		log.info("*****getSubservice Method End**");
		return serviceMap;

	}

}
