package com.project;

import java.util.Date;
import java.util.UUID;

import com.google.appengine.repackaged.org.joda.time.DateTime;

public class Contact {
	
	private String firstName;
	private String lastName;
	private String address;
	private String user_id;
	private String contact_id;
	private Date createdDate;  //Date
	private Date updatedDate;  
	private String tag;
	private boolean isDeleted;
	
	
	
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
	
	
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	Contact(String firstName,String lastName,String address,String user_id,String tag)
	{
		this.tag=tag;
		this.firstName=firstName;
		this.lastName=lastName;
		this.address=address;
		 String contact_id= UUID. randomUUID().toString();
		this.contact_id=contact_id;
	this.user_id=user_id;

  	  DateTime now = new DateTime();
  	   Date millis=new Date(now.getMillis());
		this.createdDate=millis;
		this.updatedDate=millis;
		
		
		
		this.isDeleted=false;
		
		
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Override
	public String toString() {
		return "Contact [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", contact_id="
				+ contact_id + ", date=" + createdDate +  "]";
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact_id() {
		return contact_id;
	}
	public void setContact_id(String contact_id) {
		this.contact_id = contact_id;
	}
	
	

	
	
	

}
