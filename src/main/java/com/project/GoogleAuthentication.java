package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;


import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.appengine.repackaged.com.google.api.client.http.HttpTransport;
import com.google.appengine.repackaged.com.google.api.client.http.javanet.NetHttpTransport;
import com.google.appengine.repackaged.com.google.api.client.json.jackson.JacksonFactory;
import com.google.appengine.repackaged.org.joda.time.DateTime;




@WebServlet("/google")
public class GoogleAuthentication extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(GoogleAuthentication.class.getName());	

    public GoogleAuthentication() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        StringBuffer jb = new StringBuffer();
	    PrintWriter out=response.getWriter();
	    String line = null;
	    BufferedReader reader = request.getReader();
	    while ((line = reader.readLine()) != null)
	        jb.append(line);
	    String str=jb.toString();
        JSONObject json=new JSONObject(str);

        
        
        
        
        HttpTransport transport = new NetHttpTransport();
        JacksonFactory jacksonFactory = new JacksonFactory();
        String idTokenString=json.getString("idtoken");
        log.warning(idTokenString);
        System.out.println(idTokenString);
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
		    .setAudience(Collections.singletonList("354739725161-49gg1fnf7qhehejirguegte1ovlkaup2.apps.googleusercontent.com"))
		    .build();

		GoogleIdToken idToken;
		HttpSession session=request.getSession(true);
		try {
				
			idToken = verifier.verify(idTokenString);
	
			if (idToken != null) {
				
				  Payload payload = idToken.getPayload();
		
				  String user_id = payload.getSubject();
				  System.out.println("User ID: " + user_id);
		
				  String email = payload.getEmail();
				  String pictureUrl = (String) payload.get("picture");
				  UserDao u=new UserDaoImplementation();
				  User user=new User(email," ");
				  user.setEmail(email);
				  user.setUser_id(user_id);
			      DateTime now = new DateTime();
			      Date date=new Date(now.getMillis());
			      user.setCreatedDate(date);
				  user.setImage(pictureUrl);
				  user.setActive(true);
				 boolean check=u.createUser(user);
				  session.setAttribute("user_id", user_id);
				 
				  if (check == true) {

						JSONObject obj = new JSONObject();
						JSONObject obj1 = new JSONObject();
						response.setStatus(200);
						obj1.put("email", email);
						obj.put("success", true);
						obj.put("code", 200);
						obj.put("message", "registration successfull!");
						obj.put("user", obj1);
						out.println(obj);

					} /*else {
						JSONObject obj = new JSONObject();
						JSONObject obj1 = new JSONObject();
						response.setStatus(200);
						obj1.put("email", email);
						obj.put("success", true);
						obj.put("code", 200);
						obj.put("message", "user already exist");
						obj.put("user", obj1);
						out.println(obj);

					}*/

		
			} 
			else 
			{
			  JSONObject obj1=new JSONObject();
			  System.out.println("Invalid ID token.");
			  response.setStatus(400);
			  obj1.put("success", false);
			  obj1.put("code",400);
			  obj1.put("error","Invalid ID token");
   			  out.println(obj1);

				
			}
		
		} catch (GeneralSecurityException | IOException e) {
			
			  JSONObject obj1=new JSONObject();
			  System.out.println("Invalid ID token.");
			  response.setStatus(400);
			  obj1.put("success", false);
			  obj1.put("code",400);
			  obj1.put("error","Security Issues");
 			  out.println(obj1);			
 			  e.printStackTrace();
        }
            catch (Exception e) {
			
			  JSONObject obj1=new JSONObject();
			  System.out.println("Invalid ID token.");
			  response.setStatus(500);
			  obj1.put("success", false);
			  obj1.put("code",500);
			  obj1.put("error","Exception");
 			  out.println(obj1);			
 			  e.printStackTrace();
		}
    }


}