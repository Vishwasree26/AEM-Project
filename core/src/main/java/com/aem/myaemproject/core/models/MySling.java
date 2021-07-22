package com.aem.myaemproject.core.models;


import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables=Resource.class)
public class MySling {
	
	@Inject
	private String title;
	
	@Inject
	private String description;
	
	@PostConstruct
	private void init(){
		
	}
	
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
}
