 package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Servlet implementation class LoginPage
 */
@WebServlet("/loginpage")
public class LoginPage extends HttpServlet {
    private static final long serialVersionUID = 1L;
      private static final Logger log = Logger.getLogger(LoginPage.class.getName());	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
	request.getRequestDispatcher("login.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
  


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		 response.setContentType("application/json");
		PrintWriter out=response.getWriter() ;
		
		
		StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }

		  String str=jb.toString();
		  JSONObject jsonobject= new JSONObject(str);
		    
		  
		 String plainText= jsonobject.get("password").toString();
	//	 System.out.println("Hello   "+plainText);
		//  String pwd=BCrypt.hashpw(jsonobject.get("password").toString(),BCrypt.gensalt());
		  
		  
		  
		  String email=jsonobject.getString("email");
		  //String pwd= Encryption.encrypt(jsonobject.getString("password"),"3");
		  
		  HttpSession session=request.getSession();
		  UserDao u=new UserDaoImplementation();
		  


		  if(Validation.isUserExist(email, plainText))
		  {
			  session.setAttribute("user_id",u.getUserId(email, plainText));
			  JSONObject obj=new JSONObject();
			  JSONObject obj1=new JSONObject();
			  response.setStatus(200);
			  obj1.put("email", email);
			  obj.put("success", true);
			  obj.put("code", 200);
			  obj.put("message", "Login successfull");
			  obj.put("user",obj1);
			  out.println(obj);
			  
			  
		  }
		  else
		  {
			  JSONObject obj=new JSONObject();
			  JSONObject obj1=new JSONObject();
			  response.setStatus(200);
			  obj1.put("email", email);
			  obj.put("success", false);
			  obj.put("code", 200);
			  obj.put("message", "Login failed");
			  out.println(obj);
			  
			  
			  
		  }
	     
		
		
		
		
	}

}
