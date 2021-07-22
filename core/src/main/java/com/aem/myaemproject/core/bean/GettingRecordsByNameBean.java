package com.aem.myaemproject.core.bean;

public class GettingRecordsByNameBean {

	private String Name;
	private String Email;
	private String Password;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	@Override
	public String toString() {
		return "MyBean [Name=" + Name + ", Email=" + Email + ", Password=" + Password + "]";
	}
}
