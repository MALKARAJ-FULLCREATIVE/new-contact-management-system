package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.api.datastore.EntityNotFoundException;

/**
 * Servlet implementation class DetailManagement
 */
@WebServlet("/contact/detail/*")
public class DetailManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
    
	/**
	 * 
	 * 
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * 
	 * 
	 * 
	 */
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	  response.setContentType("application/json");
		  String pathInfo=request.getPathInfo();
		  
		  
		    			String path[]=pathInfo.split("/");
		    			
	                   	String contact_id=path[2].trim() ;
	                   	String detail_id=path[3].trim();

	      			  HttpSession session=request.getSession(false);
	      			  String user_id=session.getAttribute("user_id").toString();
	        
	                   DetailDao detaildao=new ContactDaoImplementation();
	                   JSONObject jsondetail=detaildao.deleteDetail(contact_id,detail_id,user_id);
	                    
	                    
	                   	response.getWriter().print(jsondetail);
			
		  
		  
		
	}
	
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

			  
			  
			  HttpSession session=request.getSession(false);
			  String user_id=session.getAttribute("user_id").toString();
			  
			  String str=jb.toString();
			  JSONObject jsonobject= new JSONObject(str);
		       JSONArray newjsondetailarray=new JSONArray();
		       JSONObject newjsoncontact=new JSONObject();
	    
		
		       
		       
		       
		       
		       
		       
		       
	JSONObject  jsoncontact=jsonobject.getJSONObject("contact");
	     String contact_id=jsoncontact.getString("contact_id");
	  
	     newjsoncontact.put("contact_id", contact_id);
	     
	  
	      
	 
	     
	     DetailDao detaildao=new ContactDaoImplementation();
	     
	     JSONArray jsondetailarray=jsoncontact.getJSONArray("detail");
		       
	     for(int i=0;i<jsondetailarray.length();i++)
	     {
	    	 
	         JSONObject jsondetail=jsondetailarray.getJSONObject(i);  
			    String contactType=jsondetail.getString("contactType");
			    String value= jsondetail.getString("value");
			    
			    if(contactType.equals("phone"))
			    {
			    	if(Validation.isValidNumber(value)==false)
			    	{
			    		 JSONObject obj=new JSONObject();
			    	   		obj.put("status", false);
			    	   		obj.put("code", 400);
			    	   		obj.put("message"," phone number is not in proper format");
			    	   		
			    			  
			    	         response.getWriter().print(obj);
			    	         
			    	         
			    	         return ;
			    		
			    	}
			    		
			    }
			    else
			    {
			    	
			    	if(Validation.isValidEmail(value)==false)
			    	{
			    		JSONObject obj=new JSONObject();
		    	   		obj.put("status", false);
		    	   		obj.put("code", 400);
		    	   		obj.put("message"," email is not in proper format");
		    	   		
		    			  
		    	         response.getWriter().print(obj);
		    	         
		    	         
		    	         return ;
			    		
			    	}
			    }
	    	 
	    	 
	     }
	     
	
		  for(int i=0;i<jsondetailarray.length();i++)
		  {
			        JSONObject jsondetail=jsondetailarray.getJSONObject(i);  
			                   
			           
			               String contactType=jsondetail.getString("contactType");
			               String value=jsondetail.getString("value");
			               
			               
			               Detail detail=new Detail(contactType,value);
			               try {
							detaildao.addDetail(detail, contact_id,user_id);
						} catch (EntityNotFoundException e) {
							// TODO Auto-generated catch block
							return ;
							//e.printStackTrace();
						}
			              
			           	JSONObject obj1=new JSONObject();
			      		
			      	    obj1.put("contact_id",contact_id);
			      	    obj1.put("created", detail.getCreatedDate());
			      	    obj1.put("updated", detail.getUpdatedDate());
			      	    obj1.put("detail_id",detail.getDetail_id());
			      		obj1.put("contactType",contactType);
			      		obj1.put("value", value);
			               
			      		newjsondetailarray.put(obj1);		                        	               
			               
               
			               
			               
			               
			    
		  }
	
		  newjsoncontact.put("detail", newjsondetailarray);
		  
		  response.setStatus(200);
		  JSONObject obj=new JSONObject();
   		obj.put("status", true);
   		obj.put("code", 200);
   		obj.put("message","added");
   		obj.put("contact",newjsoncontact);
		  
         response.getWriter().print(obj);
}
	
	
	
	
}