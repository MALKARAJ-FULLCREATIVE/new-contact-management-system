package com.project;

import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

import java.io.IOException;
import java.util.logging.Logger;

import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;

import org.json.JSONObject;

public class SyncApp {
	
	
		private static final Logger log = Logger.getLogger("logger");
	
	static URLFetchService fetcher = URLFetchServiceFactory.getURLFetchService();

	 static final String receiveKey = "9ac80b0c-d004-11eb-b8bc-0242ac130003";
     static final String sentKey = "0115daa6-d00a-11eb-b8bc-0242ac130003";
    
    public static JSONObject sentRequest(HTTPRequest req) throws IOException
	 {
		JSONObject obj1=new JSONObject();
		int code = 500;
		int retryLimit=3;
		HTTPResponse res = null;
        int i=0;


        while(i<retryLimit)
		{
			log.info("registration attempt no : "+ i);
			res = fetcher.fetch(req);
			
            code= res.getResponseCode();
            log.info("code :"+code);
			if(code>=200 && code<300)
			{
				
				obj1.put("success", true);
				obj1.put("code",code);
				break;
			}
			else
			{
				i++;
			}
		}
		JSONObject json=new JSONObject(res);
		if(i>2)
		{
			obj1.put("message", json.get("message"));
			obj1.put("success", false);
			obj1.put("code",code);
		}
		return obj1;
	 }



}
