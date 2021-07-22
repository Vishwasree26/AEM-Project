package com.aem.myaemproject.core.bean;

public class ParentNodeDataRetrievalBean {

	private String EmpID;
	private String EmpName;
	
	public String getEmpID() {
		return EmpID;
	}
	public void setEmpID(String empID) {
		EmpID = empID;
	}
	public String getEmpName() {
		return EmpName;
	}
	public void setEmpName(String empName) {
		EmpName = empName;
	}
	@Override
	public String toString() {
		return "NodeBean [EmpID=" + EmpID + ", EmpName=" + EmpName + "]";
	}

}
