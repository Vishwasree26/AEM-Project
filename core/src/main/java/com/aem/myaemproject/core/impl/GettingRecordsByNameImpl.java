package com.aem.myaemproject.core.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.myaemproject.core.bean.GettingRecordsByNameBean;
import com.aem.myaemproject.core.dbutil.GettingRecordsByNameDatabase;
import com.aem.myaemproject.core.interfac.GettingRecordsByNameInterface;



@Component(service=GettingRecordsByNameInterface.class, immediate=true)
public class GettingRecordsByNameImpl implements GettingRecordsByNameInterface {
	
	protected static final Logger log=LoggerFactory.getLogger(GettingRecordsByNameInterface.class);

	@Reference
	GettingRecordsByNameDatabase objs;
	Connection con = null;
	PreparedStatement p = null;

	@Override
	public boolean add(String Name, String Email, String Password) {
		// TODO Auto-generated method stub
		
		log.info("Details invoked");
		String sql = "INSERT INTO aem(Name, Email, Password) VALUES (?,?,?)";
		boolean flag = false;
		try {
			con = objs.getDataBaseConnection("aemproject");
			p = con.prepareStatement(sql);
			
			p.setString(1,Name);
			p.setString(2,Email);
			p.setString(3,Password);
			
			int i = p.executeUpdate();
			if (i==1) {
				
				flag = true;
				log.info("Record inserted successfully");
			} else {
				flag = false;
				log.info("OOPS...! Try again");
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			
			if(con != null){
				
				try {
					p.close();
					con.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			
		}
		
		log.info("Got all employee methods");
		
		return flag;
	}

	@Override
	public GettingRecordsByNameBean gettingRecordsByName(String Name) {
		// TODO Auto-generated method stub
		
	GettingRecordsByNameBean bean = new GettingRecordsByNameBean();
	
	String sql = "select * from aem where Name=?";
	
	
	try {
		// logic here
		
		con = objs.getDataBaseConnection("aemproject");
		System.out.println("Connection Successfully Established"+ con);
	
		
		p.setString(1, Name);
		ResultSet rs = p.executeQuery();	//data from the database will be available in ResultSet object 'rs'
		
		if (rs.next()) {
			bean.setName(rs.getString(1));
			bean.setEmail(rs.getString(2));
			bean.setPassword(rs.getString(3));
			
		}
	
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
	finally {
		
	}
	return bean;
}
}
