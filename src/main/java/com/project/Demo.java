package com.project;

import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.repackaged.org.joda.time.DateTime;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*DateTime now = new DateTime();
		System.out.println(now);

 
		  JSONObject contact1=new JSONObject();
  
  JSONObject contact=new JSONObject();
  contact.put("firstName", "malkaraj");
  contact.put("lastName", "karuppaswamy");
   contact.put("address", "no 263 2nd street");
     
   
   JSONObject detail=new JSONObject();
   detail.put("contactType", "phone");
   detail.put("value", "7338937115");
   
    JSONArray list=new JSONArray();
    list.put(detail);
    list.put(detail);
    list.put(detail);
    
   contact.put("detail",list);
   

   

   contact1.put("contact", contact);
		
        JSONObject json=contact1.getJSONObject("contact");
        JSONArray arr=      json.getJSONArray("detail");
        
   /*     for(int i=0;i<arr.length();i++)
        {
        	
        	System.out.println(arr.getJSONObject(i).getString("contactType"));
        }
        
     System.out.println(contact1.has("contact"));       
*/
		
		DatastoreService datastore=DatastoreServiceFactory.getDatastoreService();
		
		String str="";
		if(str=="")
			System.out.println("hello");
			//long d=Long.parseLong(" ".toString());
		Query q=new Query();
		System.out.println(q);
	//Date date=new Date(d);
	//System.out.println(date);
        // for(Entity i: datastore.prepare(q).asIterable())
          //  System.out.println(i.getProperty("updated"));         
		
}
	
}