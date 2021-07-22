package com.aem.myaemproject.core.dbutil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.commons.datasource.poolservice.DataSourcePool;


@Component(service=GettingRecordsByNameDatabase.class, immediate=true)

public class GettingRecordsByNameDatabase {

	protected static final Logger log=LoggerFactory.getLogger(GettingRecordsByNameDatabase.class);

@Reference
DataSourcePool obj;

Connection con = null;


public Connection getDataBaseConnection(String dataSourceName){
	
	log.info("Database Started");
	
	try {
		
		DataSource ds = (DataSource) obj.getDataSource(dataSourceName);
		con = ds.getConnection();
		log.info("Connection successfully established "+con);
	} catch (Exception e) {
		// TODO: handle exception
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		log.error(sw.toString());
		
	}
	
	return con;
}
}
