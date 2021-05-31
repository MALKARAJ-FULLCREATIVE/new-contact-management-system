package com.project;

import com.google.appengine.api.datastore.Query.FilterPredicate;
import java.io.BufferedReader;
import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.repackaged.org.joda.time.DateTime;
import com.google.appengine.api.datastore.Key;

/**
 * Servlet implementation class ContactManagement
 */
@WebServlet("/contact/*")

/*
 * 
 * 
 * {
    
    "contact":{
        "firstName": "malk",
        "lastName": "Joseph",
        "address": "no 264 2nd street",
        "detail": [
            {
                "contactType": "phone",
                "detail_id": "e6fd6df1-5714-4e3b-b88b-de1757a8fce1",
                "contact_id": "63742307-124a-440f-bc69-50f79c23c160",
                "value": "7338937115"
            }
        ],
        "contact_id": "63742307-124a-440f-bc69-50f79c23c160"
    }
}
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

public class ContactManagement extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ContactManagement.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

    
 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	       response.setContentType("application/json");
		StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }

		  String str=jb.toString();
		  JSONObject jsonobject= new JSONObject(str);
		 
		  HttpSession session=request.getSession(false);
		  String user_id=session.getAttribute("user_id").toString();
		  
		  ContactDao contactDao=new ContactDaoImplementation();
		  
		JSONObject obj= contactDao.addContactWithDetails(jsonobject,user_id);
			response.setStatus(obj.getInt("code"));
		
		  response.getWriter().print(obj);
		  
		
		  
}
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		  		response.setContentType("application/json");
		  		StringBuffer jb = new StringBuffer();
		  		String line = null;
		  		try {
			    BufferedReader reader = request.getReader();
			    while ((line = reader.readLine()) != null)
			      jb.append(line);
			  } catch (Exception e) { /*report an error*/ }


		  		String pathInfo=request.getPathInfo();

		  		String path[] = null;
		  		if(pathInfo!=null) {
		  		path=pathInfo.split("/");
		  		}
			  	 String contact_id= path[1];	
			  	 
			  	 
			  	String str=jb.toString();
			  		
			  	             
			  				JSONObject jsonObject= new JSONObject(str);

			  			  HttpSession session=request.getSession(false);
			  			  String user_id=session.getAttribute("user_id").toString();
			  			  
			  				 ContactDao contactDao=new ContactDaoImplementation();
			  			JSONObject obj=	contactDao.updateContactWithDetails(jsonObject,contact_id,user_id);
			  			response.setStatus(obj.getInt("code") );
			  			response.getWriter().print(obj);
			  				
			  				
			  				
		  
			  
			        			  
	}
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	response.setContentType("application/json");
	
		  log.info("Your information log message.");

	String pathInfo=request.getPathInfo();
	HttpSession session =request.getSession(false);
	
	String cursor=request.getParameter("cursor");
	//System.out.println(cursor+"hello gelosffjsdfj");
	log.info("doGetRequest");

	String tagInfo="";
	
	if(pathInfo!=null) {
	String path[]=pathInfo.split("/");
	if(path.length==3) {
 tagInfo=path[2];
	}
	}
	
	if(pathInfo!=null && pathInfo.contains("tag"))
	{
		System.out.println(tagInfo);
		ContactDao contactDao=new ContactDaoImplementation();
		JSONObject obj= contactDao.getContactByCategory(tagInfo,session.getAttribute("user_id").toString());
		
    	response.setStatus(obj.getInt("code"));
		response.setCharacterEncoding("UTF-8");
	    response.getWriter().print(obj);
	}
	else
	if(pathInfo!=null && pathInfo.contains("garbage"))//deleted
	{
		
		ContactDao contactDao=new ContactDaoImplementation();
		JSONObject obj=contactDao.displayContact(cursor,pathInfo,true,session.getAttribute("user_id").toString());
		response.setStatus(obj.getInt("code"));
		response.setCharacterEncoding("UTF-8");
	    response.getWriter().print(obj);
	}
	else {
		System.out.println("hello");
	ContactDao contactDao=new ContactDaoImplementation();
		JSONObject obj=contactDao.displayContact(cursor,pathInfo,false,session.getAttribute("user_id").toString());
		response.setStatus(obj.getInt("code"));
		response.setCharacterEncoding("UTF-8");
		
		String name = obj.toString();
		 response.getWriter().print(obj);
		 
		
		
		 
	}
}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatastoreService datastoreService=DatastoreServiceFactory.getDatastoreService();
		response.setContentType("application/json");
		
		
		String pathInfo=request.getPathInfo();

		String path[]=pathInfo.split("/");
		String contact_id=path[1];
		
		
		  HttpSession session=request.getSession(false);
		  String user_id=session.getAttribute("user_id").toString();
			  
			ContactDao contactDao=new ContactDaoImplementation();
			JSONObject obj=contactDao.deleteContact(contact_id,user_id);
			response.setStatus(obj.getInt("code"));
			response.getWriter().print(obj);
			
		
		
		
	}

	
	
}