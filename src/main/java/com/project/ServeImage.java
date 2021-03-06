package com.project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

@WebServlet("/serve")
public class ServeImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
   
    	BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
    	blobstoreService.serve(blobKey, resp);
    }

}
