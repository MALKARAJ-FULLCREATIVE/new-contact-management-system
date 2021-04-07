package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.api.datastore.EntityNotFoundException;

/**
 * Servlet implementation class DetailManagement
 */
@WebServlet("/contact/detail")
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

		  JsonNode json=null;
		  try {
		  //  JSONObject jsonObject =  HTTP.toJSONObject(jb.toString());
		    
			  String str=jb.toString();
			  ObjectMapper mapper = new ObjectMapper();
			   json = mapper.readTree(str);
			    
			  
		  } catch (JSONException e) {
		    // crash and burn
		    throw new IOException("Error parsing JSON request string");
	}
		  
	if(json.size()==3 && json.has("contact_id") && json.has("contactType") && json.has("value"))	  
	{
		  String contact_id =json.get("contact_id").asText();
		  
		  String contactType=json.get("contactType").asText();
          String value=json.get("value").asText();
          boolean isEmail=false;
          
      if(Validation.isValidType(contactType)) {
    	  
      
          		if(contactType.equals("email"))
          		{
          			isEmail=  Validation.isValidEmail(value);
        	  
        	  
          		}
          		boolean isPhone=false;
          
          		if(contactType.equals("phone"))
          		{
          			isPhone=Validation.isValidNumber(value);
          		}
          
          		if(isEmail==true ||isPhone==true) {  
          			Detail detail=new Detail(contactType,value);
		
          			DetailDao cont=new ContactDaoImplementation();
          			try {
          				cont.addDetail(detail, contact_id);
          			} catch (EntityNotFoundException e) {
          				// TODO Auto-generated catch block
          				JSONObject obj=new JSONObject();
          				obj.put("status", "failed");
          				obj.put("code", "400");
          				obj.put("message", "entity not found");
          				response.getWriter().print(obj);
          				return;
		}
		JSONObject obj1=new JSONObject();
  		
  	    obj1.put("contact_id",contact_id);
  	    obj1.put("detail_id",detail.getDetail_id());
  		obj1.put("contactType",contactType);
  		obj1.put("value", value);
  		
  		response.setStatus(200);

  	  JSONObject obj=new JSONObject();
  	  obj.put("status", "success");
  	  obj.put("code", "200");
  	 obj.put("message","added");
  	  obj.put("data",obj1);
  	 
  	  
  	  response.getWriter().print(obj);
		
        }
        else
        {
        	 response.setStatus(400);

       	  JSONObject obj=new JSONObject();
       	  obj.put("status", "failed");
       	  obj.put("code", "400");
       	  obj.put("message", "phonenumber/email is not in proper format!");
       	  response.getWriter().print(obj);
        	
        }
		
		
	}
	
	
	else
	{

   	 response.setStatus(400);

  	  JSONObject obj=new JSONObject();
  	  obj.put("status", "failed");
  	  obj.put("code", "400");
  	  obj.put("message", "contacttype is not in proper format");
  	  response.getWriter().print(obj);
		
	}

}
	else
	{

	   	 response.setStatus(400);

	  	  JSONObject obj=new JSONObject();
	  	  obj.put("status", "failed");
	  	  obj.put("code", "400");
	  	  obj.put("message", "some attribute values are missing");
	  	  response.getWriter().print(obj);
	}

}
}