package com.project;
import com.google.appengine.api.datastore.PrePut;
import com.google.appengine.api.datastore.PutContext;
import com.google.appengine.repackaged.org.joda.time.DateTime;

import java.util.logging.Logger;
//import java.util.Date;
public class PrePutCallbacks {
private static final Logger log = Logger.getLogger(PrePutCallbacks.class.getName());
	
	
	
	

    @PrePut
    void updateTimestamp(PutContext context) {
      
        log.info("updateTimeStamp");
		DateTime d = new DateTime();
	        context.getCurrentElement().setProperty("updated", d.getMillis());
    }
    
    
}
