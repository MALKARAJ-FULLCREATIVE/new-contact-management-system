package com.project;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;

interface DetailDao {
	
	
public  String addDetail(Detail d,String contact_id) throws EntityNotFoundException;




}
