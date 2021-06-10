package com.Demo;
import java.util.*;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.repackaged.org.joda.time.DateTime;
public class Hello {
	
	
	
	
	public static JSONObject createEntity()
	{
		DatastoreService ds=DatastoreServiceFactory.getDatastoreService();
		
		Entity contact=new Entity("Contact",1);
		
		contact.setProperty("name","malkaraj");
		contact.setProperty("address","no 234");
		
		ds.put(contact);
		
		
		Key k=KeyFactory.createKey("Contact", 1);
		
		Entity contactE=null;
		 try {
			 contactE= ds.get(k);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		  Entity detail=new Entity("Detail",2,contactE.getKey());
		  detail.setProperty("type", "phone");
		  
		 ds.put(detail);
		 
           		  
		
		 
		 Key  k1=KeyFactory.createKey("Contact",1);
		
		 Entity ContactE1=null;
		 try {
			ContactE1=ds.get(k1);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
		 JSONObject jsonobj=new JSONObject();
		 jsonobj.put("name",ContactE1.getProperty("name") );
		 jsonobj.put("address", ContactE1.getProperty("address"));
		 
		 return jsonobj;
		 
		 
		
		
	}

}
