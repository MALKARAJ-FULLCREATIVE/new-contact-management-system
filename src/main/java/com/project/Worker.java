package com.project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * Servlet implementation class Worker
 */
@WebServlet("/worker")
public class Worker extends HttpServlet {
private static final long serialVersionUID = 1L;

public Worker() {
    super();
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	DatastoreService ds= DatastoreServiceFactory.getDatastoreService();
	String contact = request.getParameter("key");
	JSONObject obj=new JSONObject(contact);
	
	String user_id = request.getParameter("user_id");
	//System.out.println(obj);
	//HttpSession session=request.getSession(false);
	 //String user_id= session.getAttribute("user_id").toString();
	
	for(int i=0;i<obj.length();i++)
	{

		Key key = new KeyFactory.Builder("User", user_id).addChild("Contact",obj.get(String.valueOf(i)).toString()).getKey();
//	Key key1=	new KeyFactory.Builder("User",user_id).addChild("contact", obj.get(String.valueOf(i)).toString())
	//	     .addChild("detail", obj.get(String.valueOf(i))         ))
	ds.delete(key);
	}
	
	
	
	
	
	
}
}



