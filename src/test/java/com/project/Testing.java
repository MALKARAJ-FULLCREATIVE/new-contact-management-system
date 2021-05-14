package com.project;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import org.junit.Test;

public class Testing {

	private final LocalServiceTestHelper helper =
		      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
 
		  @Before
		  public void setUp() {
		    helper.setUp();
		  }

		  @After
		  public void tearDown() {
		    helper.tearDown();
    		  }
	     
	
		  
		  @Test
		  public void emailPassTest()
		  {
			  JSONObject obj=new JSONObject();
			  obj.put("firstName", "malka");
			  obj.put("lastName", "raj");
			  obj.put("address", "234");
			  obj.put("contactType", "email");
			  obj.put("value", "malkaraj99@gmail.com");
			  
			 boolean val= Validation.isValidEmail(obj.get("value").toString());
			 
			 assertEquals(val,true);
			 
			  
		  }
		  
		  @Test
		  public void emailFailTest()
		  {
			  JSONObject obj=new JSONObject();
			  obj.put("firstName", "malka");
			  obj.put("lastName", "raj");
			  obj.put("address", "234");
			  obj.put("contactType", "email");
			  obj.put("value", "malkaraj99gmail.com");
			  
			 boolean val= Validation.isValidEmail(obj.get("value").toString());
			 
			 assertEquals(val,false);
			 
			  
		  }
		  
		  
		  
		  
		  
		  
		  
		  @Test
		  public void phoneNumberPassTest()
		  {
			  JSONObject obj=new JSONObject();
			  obj.put("firstName", "malka");
			  obj.put("lastName", "raj");
			  obj.put("address", "234");
			  obj.put("contactType", "phone");
			  obj.put("value", "7338937115");
			  
			  
			  boolean val=Validation.isValidNumber(obj.get("value").toString());
			  assertEquals(val,true);
			  
			  
			  
		  }
		  

		  @Test
		  public void phoneNumberFailTest()
		  {
			  JSONObject obj=new JSONObject();
			  obj.put("firstName", "malka");
			  obj.put("lastName", "raj");
			  obj.put("address", "234");
			  obj.put("contactType", "phone");
			  obj.put("value", "733893711");
			  
			  
			  boolean val=Validation.isValidNumber(obj.get("value").toString());
			  assertEquals(val,false);
			  
			  
			  
		  }
		  
		  


		  
		  @Test
		  public void lastNamePassTest()
		  {

			  JSONObject obj=new JSONObject();
			  obj.put("firstName", "malka");
			  obj.put("lastName", "");
			  obj.put("address", "234");
			  obj.put("contactType", "phone");
			  obj.put("value", "7338937115");
			  boolean val=Validation.isLastNameExist(obj.getString("lastName")  );
			  assertEquals(val,true);
			  
		  }
		  @Test
		  public void lastNameFailTest()
		  {

			  JSONObject obj=new JSONObject();
			  obj.put("firstName", "malka");
			  obj.put("lastName", "raj1");
			  obj.put("address", "234");
			  obj.put("contactType", "phone");
			  obj.put("value", "7338937115");
			  boolean val=Validation.isLastNameExist(obj.getString("lastName")  );
			  assertEquals(val,false);
			  
		  }
		  @Test
		  public void firstNamePassTest()
		  {
			  JSONObject obj=new JSONObject();
			  obj.put("firstName", "malka");
			  obj.put("lastName", "raj");
			  obj.put("address", "234");
			  obj.put("contactType", "phone");
			  obj.put("value", "7338937115");
			  
			  boolean val=Validation.isFirstNameExist(obj.getString("firstName")  );
			  assertEquals(val,true);
			
		  }
		  
		  @Test
		  public void firstNameFailTest()
		  {
			  JSONObject obj=new JSONObject();
			  obj.put("firstName", " ");
			  obj.put("lastName", "raj");
			  obj.put("address", "234");
			  obj.put("contactType", "phone");
			  obj.put("value", "7338937115");
			  
			  boolean val=Validation.isFirstNameExist(obj.getString("firstName")  );
			  assertEquals(val,false);
			
		  }
		  
		  

		  @Test
		  public void contactTypePassTest()
		  {
			  
			  JSONObject obj=new JSONObject();
			  obj.put("firstName", "malka");
			  obj.put("lastName", "raj");
			  obj.put("address", "234");
			  obj.put("contactType", "email");
			  obj.put("value", "malkaraj99@gmail.com");
			  boolean val=Validation.isValidType(obj.getString("contactType"));
			  
			  assertEquals(val,true);
			  
		  }
		  
		  @Test
		  public void contactTypeFailTest()
		  {
			  
			  JSONObject obj=new JSONObject();
			  obj.put("firstName", "malka");
			  obj.put("lastName", "raj");
			  obj.put("address", "234");
			  obj.put("contactType", "em");
			  obj.put("value", "7338937115");
			  boolean val=Validation.isValidType(obj.getString("contactType"));
			  
			  assertEquals(val,false);
			  
			  
			  
			  
		  }
		  
		  @Test
		  public void addressPassTest()
		  {
			  JSONObject obj=new JSONObject();
			  obj.put("firstName", "malka");
			  obj.put("lastName", "raj");
			  obj.put("address", "1sfsdfsd  sfsdf a ");
			  obj.put("contactType", "phone");
			  obj.put("value", "7338937115");
			  
			  boolean val=Validation.isValidAddress(obj.getString("address"));
			  assertEquals(val,true);
			  
		  }
		  
		  @Test
		  public void addressFailTest()
		  {
			  JSONObject obj=new JSONObject();
			  obj.put("firstName", "malka");
			  obj.put("lastName", "raj");
			  obj.put("address", "235");
			  obj.put("contactType", "phone");
			  obj.put("value", "7338937115");
			  
			  boolean val=Validation.isValidAddress(obj.getString("address"));
			  assertEquals(val,false);
			  
		  }
		  
		  
		  
		  
		  
		  
}

