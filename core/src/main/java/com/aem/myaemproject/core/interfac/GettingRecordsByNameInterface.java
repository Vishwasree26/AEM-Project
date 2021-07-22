package com.aem.myaemproject.core.interfac;

import com.aem.myaemproject.core.bean.GettingRecordsByNameBean;

public interface GettingRecordsByNameInterface {

	public boolean add(String Name, String Email, String Password);
	
	public GettingRecordsByNameBean gettingRecordsByName(String Name);
}
