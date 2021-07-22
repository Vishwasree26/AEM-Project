package com.aem.myaemproject.cqdialogproperties;

import com.adobe.cq.sightly.WCMUsePojo;

public class MyWcmPojo extends WCMUsePojo {

	private String title;
	private String description;

	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
		title = getProperties().get("title", "").toLowerCase();
		description = getProperties().get("description", "").toLowerCase();		
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}	
}