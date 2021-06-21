package com.project;

import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

public class SyncApp {
	
	
	
	
	static URLFetchService fetcher = URLFetchServiceFactory.getURLFetchService();

	static final String receiveKey = "9ac80b0c-d004-11eb-b8bc-0242ac130003";
	static final String sentKey = "0115daa6-d00a-11eb-b8bc-0242ac130003";

}
