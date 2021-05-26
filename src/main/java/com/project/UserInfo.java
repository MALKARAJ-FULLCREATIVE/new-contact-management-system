package com.project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import org.json.JSONObject;

import com.google.appengine.api.datastore.EntityNotFoundException;

@MultipartConfig
@WebServlet("/user")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UserInfo() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession(false);
        UserDao userOp=new UserDaoImplementation();
        response.setContentType("application/JSON");
		if(session!=null)
		{
			String userId=(String) session.getAttribute("user_id");
			try {
				JSONObject obj=userOp.getUser(userId);
				JSONObject obj1=new JSONObject();
				response.setStatus(200);
				obj1.put("code",200);
				obj1.put("success",true);
				obj1.put("user", obj);
				out.println(obj1);

				
			} catch (EntityNotFoundException e) {
				JSONObject obj1=new JSONObject();
				response.setStatus(400);
				obj1.put("code",400);
				obj1.put("success",false);
				obj1.put("error", "No user found");
				out.println(obj1);
			}
			
		}
		else
		{
			JSONObject obj1=new JSONObject();
			response.setStatus(500);
			obj1.put("code",500);
			obj1.put("success",false);
			obj1.put("error", "Please login to continue");
			out.println(obj1);		}
	}

	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        UserDao userOp=new UserDaoImplementation();
		PrintWriter writer=response.getWriter();
	//	InputStream inStream = new FileInputStream(request.getParameter("img"));
		//File file=new File("/Users/"+request.getAttribute("img"));
		HttpSession session=request.getSession(false);

		Part filePart = request.getPart("img");
		System.out.println(filePart);
		String userId=request.getParameter("user_id");
		String email=request.getParameter("email");
		String previousImage=request.getParameter("previousImage");
		if(previousImage!="null.png")
		{
			File f= new File("C:\\Users\\Malk-pc\\eclipse-workspace\\contactproject\\src\\main\\webapp\\profile_pics\\"+previousImage);
			
			f.delete();			
			
		}
     
        InputStream fileContent = filePart.getInputStream();
        
        OutputStream out = null;
        int read = 0;
        final byte[] bytes = new byte[1024];
    	UUID id=UUID.randomUUID();
        out = new FileOutputStream(new File("C:\\Users\\Malk-pc\\eclipse-workspace\\contactproject\\src\\main\\webapp\\profile_pics\\"+id+".png"));
        while ((read = fileContent.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.close();
        try {

			Boolean result;
			result = userOp.udpateImage(email,session.getAttribute("user_id").toString(),id.toString());
	        if(result)
	        {
				JSONObject obj1=new JSONObject();
				response.setStatus(200);
				obj1.put("code",200);
				obj1.put("success",true);
				obj1.put("message", "Profile picture updated succesfully");
				writer.println(obj1);
	        }
	        else
	        {
				JSONObject obj1=new JSONObject();
				response.setStatus(500);
				obj1.put("code",500);
				obj1.put("success",false);
				obj1.put("error", "Profile picture updation failed");
				writer.println(obj1);
	        }
	        
		} catch (EntityNotFoundException e) {
			JSONObject obj1=new JSONObject();
			response.setStatus(400);
			obj1.put("code",400);
			obj1.put("success",false);
			obj1.put("error", "No user found");
			writer.println(obj1);		
			}
 
	    StringBuffer jb = new StringBuffer();
	    String line = null;
	    BufferedReader reader = request.getReader();
	    while ((line = reader.readLine()) != null)
	        jb.append(line);
	    String str=jb.toString();
        JSONObject json=new JSONObject(str);
        
		ServletFileUpload sf=new ServletFileUpload(new DiskFileItemFactory());
		List<FileItem> files;
		try {
			files = sf.parseRequest(request);
			JSONObject json=new JSONObject(files.get(1));
			FileItem item=files.get(0);
			item.write(new File("/Users/"+item.getName()));
			Boolean result;
			result = userOp.udpateImage(json);
	        if(result)
	        {
				JSONObject obj1=new JSONObject();
				response.setStatus(200);
				obj1.put("code",200);
				obj1.put("success",true);
				obj1.put("message", "Profile picture updated succesfully");
				out.println(obj1);
	        }
	        else
	        {
				JSONObject obj1=new JSONObject();
				response.setStatus(500);
				obj1.put("code",500);
				obj1.put("success",false);
				obj1.put("error", "Profile picture updation failed");
				out.println(obj1);
	        }
	        
		} catch (EntityNotFoundException e) {
			JSONObject obj1=new JSONObject();
			response.setStatus(400);
			obj1.put("code",400);
			obj1.put("success",false);
			obj1.put("error", "No user found");
			out.println(obj1);		
			}
		catch (FileUploadException e1) {
			e1.printStackTrace();
		}


        
	}*/

}