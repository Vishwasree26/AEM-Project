package com.aem.myaemproject.core.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.myaemproject.core.interfac.ChildNodesCreationInt;


@Component(service=ChildNodesCreationInt.class, immediate=true)
public class ChildNodesCreationImpl implements ChildNodesCreationInt{
	private static Logger log = LoggerFactory.getLogger(ChildNodesCreationImpl.class);
	
	@Reference
	private ResourceResolverFactory resolverFactory;
	
	ResourceResolver resourceResolver = null;
	private Session session;
	Resource resource;
	String resourcePath = "/content/myaemproject/en/jcr:content/employees";
	
	
	public boolean registerEmployDetails(String firstName, String lastName, String userName, String password) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			resourceResolver = resolverFactory.getServiceResourceResolver(getSubServiceMap());
			session = resourceResolver.adaptTo(Session.class);		//adapt method is used to convert any type of object    
			log.info("session ****" + session);
			resource = resourceResolver.getResource(resourcePath);
		//create Random numbers
		
		java.util.Random r = new java.util.Random();
		
		int low = 10;
		int high = 1000;
		int result = r.nextInt(high-low)+low;
		log.info("result=="+result);
		
		String empnode = "employee" + result;
		
		Node node = resource.adaptTo(Node.class);		//converting resource object into node
		//Node root = session.getRootNode();
		
		if (node != null) {
			Node empRoot = node.addNode(empnode, "nt:unstructured");		//addNode is a predefined method;
			empRoot.setProperty("FirstName", firstName);
			empRoot.setProperty("LastName", lastName);
			empRoot.setProperty("UserName", userName);
			empRoot.setProperty("Password", password);
			session.save();
			flag = true;
			
		} 

} catch (Exception e) {      
	// TODO: handle exception
	e.printStackTrace();
} finally {
	if (session != null) {
		session.logout();
	}

}
	return false;
	}
	public boolean loginEmployee(String user, String pass) {
		// TODO Auto-generated method stub
		boolean flag = false;
	

		try {

			resourceResolver = resolverFactory.getServiceResourceResolver(getSubServiceMap());
			session = resourceResolver.adaptTo(Session.class);
			log.info("session ****" + session);
			resource = resourceResolver.getResource(resourcePath);
			Node node = resource.adaptTo(Node.class);

			NodeIterator nodeItr = node.getNodes();

			while (nodeItr.hasNext()) {

				Node cNode = nodeItr.nextNode();
				
				String userName = cNode.getProperty("UserName").getValue().getString();
				String pwd = cNode.getProperty("Password").getValue().getString();

				Map<String, String> map = new HashMap<String, String>();

				map.put("UserName", userName);
				map.put("Password", pwd);

				if (map.containsValue(user) && map.containsValue(pass)) {
					flag = true;
					break;
				} else {

					flag = false;
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return flag;
	}
	
	
			//	This method is used for ACCESSING SYSTEM USER by storing into serviceMap Object
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
	
	
