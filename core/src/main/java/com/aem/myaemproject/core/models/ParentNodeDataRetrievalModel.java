package com.aem.myaemproject.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.aem.myaemproject.core.bean.ParentNodeDataRetrievalBean;
import com.aem.myaemproject.core.interfac.ParentNodeDataRetrievalint;

@Model(adaptables=Resource.class)
public class ParentNodeDataRetrievalModel {

	@OSGiService
	private ParentNodeDataRetrievalint obj;
	
	private String empid;
	private String empname;
	
	@PostConstruct
	private void init(){
		ParentNodeDataRetrievalBean nodbean =obj.employeesParentData();
		empid = nodbean.getEmpID();
		empname = nodbean.getEmpName();
	}
	
	public String getEmpID() {
		return empid;
	}
	public String getEmpName() {
		return empname;
	}
}
