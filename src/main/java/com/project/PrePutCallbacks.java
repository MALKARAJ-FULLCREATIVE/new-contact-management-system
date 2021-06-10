package com.project;
import com.google.appengine.api.datastore.PrePut;
import com.google.appengine.api.datastore.PutContext;
import com.google.appengine.repackaged.org.joda.time.DateTime;

import java.util.Date;
public class PrePutCallbacks {

	
	
	
	

    @PrePut
    void updateTimestamp(PutContext context) {
    	
		DateTime d = new DateTime();
		
        context.getCurrentElement().setProperty("updated", d.getMillis());
    }
    
    
}
