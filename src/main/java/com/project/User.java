package com.project;

import java.util.Date;
import java.util.UUID;

import com.google.appengine.repackaged.org.joda.time.DateTime;

public class User {


	private String user_id;
	private String password;
	private  String email;
	private Date createdDate; 
	private Date updatedDate;
	private String image;
	private boolean active;
	private boolean isDeleted;
	
	

	
	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public User(String email,String password) {
		super();
		this.password = password;
		this.email = email;
		this.active=true;
		this.image="null.png";
        this.isDeleted=false;
		this.user_id=UUID. randomUUID().toString();
	  	  DateTime now = new DateTime();
	  	   Date millis=new Date(now.getMillis());
			this.createdDate=millis;
			this.updatedDate=millis;
			
	
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
