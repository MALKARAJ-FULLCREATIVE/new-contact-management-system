package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Servlet implementation class RegistrationPage
 */
@WebServlet("/register")
public class RegistrationPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationPage() {
		super();
		// TODO Auto-generated constructor stub
    }
    

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    { 
        // pre-flight request processing
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */




	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.getRequestDispatcher("register.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub 

		response.setContentType("application/json");
        //response.setHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Origin","https://georgefulltraining12.uc.r.appspot.com/");

        
          

		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
			/* report an error */ }

		String str = jb.toString();
		JSONObject jsonobject = new JSONObject(str);

		String email = jsonobject.getString("email");
		String pwd = BCrypt.hashpw(jsonobject.get("password").toString(), BCrypt.gensalt());

		User user = new User(email, pwd);
      
		UserDao userdao = new UserDaoImplementation();
		boolean check = userdao.createUser(user);

		PrintWriter out = response.getWriter();

		if (check == true) {

            
         

         URLFetchService fetcher = URLFetchServiceFactory.getURLFetchService();
                    URL url=new URL("https://georgefulltraining12.uc.r.appspot.com/register");
                     
					HTTPRequest req = new HTTPRequest(url, HTTPMethod.POST);
					JSONObject reqObj=new JSONObject();
					reqObj.put("email", email);
					reqObj.put("password", pwd);
                
                    req.setPayload(reqObj.toString().getBytes());
					HTTPResponse res = fetcher.fetch(req);
         
                    
			JSONObject obj = new JSONObject();
			JSONObject obj1 = new JSONObject();
			response.setStatus(200);
			obj1.put("email", email);
			obj.put("success", true);
			obj.put("code", 200);
			obj.put("message", "registration successfull!");
			obj.put("user", obj1);
			out.println(obj);

		} else {
			JSONObject obj = new JSONObject();
			JSONObject obj1 = new JSONObject();
			response.setStatus(400);
			obj1.put("email", email);
			obj.put("success", false);
			obj.put("code", 400);
			obj.put("message", "user already exist");
			obj.put("user", obj1);
			out.println(obj);

		}

		// request.getRequestDispatcher("loginpage").forward(request, response);

	}

}
