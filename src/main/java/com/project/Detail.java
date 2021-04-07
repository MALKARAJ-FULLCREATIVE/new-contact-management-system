package com.project;
/*
 * 
 * 
 * 
 * 
 *{ first george
 * last
 * address
 * contacttype email
 * values ]sfsd
 * contacttype pho
 * values 21313
 *} 
 */

import java.util.Date;
import java.util.UUID;

import com.google.appengine.repackaged.org.joda.time.DateTime;

public class Detail {
	//String phoneNumber;
	//String email;
	private String value;
	
	private String contactType;//email/phone
	private String detail_id;
	private String contact_id;
	private Date createdDate;
	private Date updatedDate;
	private boolean isDeleted;
	
	
	
	public Detail( String contactType, String value) {
		// TODO Auto-generated constructor stub
		
		 String detail_id=UUID. randomUUID().toString();
		this.detail_id=detail_id;
		this.contactType =contactType;
		this.value =value;
		
		 DateTime now = new DateTime();
	        Date millis=new Date(now.getMillis());
		this.createdDate=millis;
	     isDeleted=false;
	     this.updatedDate=millis;
	     this.isDeleted=false;
	     
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

	public String getDetail_id() {
		return detail_id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public void setDetail_id(String detail_id) {
		this.detail_id = detail_id;
	}

	public String getContact_id() {
		return contact_id;
	}

	public void setContact_id(String contact_id) {
		this.contact_id = contact_id;
	}

	
	
	
}
