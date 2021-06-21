package com.project;

import java.io.IOException;

import org.json.JSONObject;

import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;

public class SyncAppFunc{
	
	
	

public static JSONObject  register(HTTPRequest req) throws IOException
		{

			JSONObject obj1=new JSONObject();
			int retryLimit=3;
			int i=0;
			while(i<retryLimit)
			{
				//log.info("registration attempt no : "+ i);
				HTTPResponse res = SyncApp.fetcher.fetch(req);
                int code= res.getResponseCode();
                //log.info("code :"+code);
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
			if(i>2)
			{
				obj1.put("message", "retry limit exeeded");
				obj1.put("success", false);
				obj1.put("code",500);
			}
			return obj1;
		}

	
	
	

}