package com.aem.myaemproject.core.osgiconfiguration;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service=ConfigInterfaceService.class,immediate=true)
@Designate(ocd=ConfigInterface.class)
public class ConfigImpl implements ConfigInterfaceService {

	private ConfigInterface configuration;
	
	
	@Activate
	protected void activate(ConfigInterface configuration) {
		this.configuration = configuration;
	}
	
	@Override
	public String makeHttpCall() {
		// TODO Auto-generated method stub
		
		String empId=configuration.getEmpId();
		String empName=configuration.getEmpName();
		String empSalary=configuration.getEmpSalary();
		return empId + empName +  empSalary;
		//return null;
	}
}