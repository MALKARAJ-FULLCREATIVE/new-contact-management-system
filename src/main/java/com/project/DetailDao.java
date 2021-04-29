package com.project;

import org.json.JSONObject;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;

interface DetailDao {
	
	
public String addDetail(Detail d,String contact_id,String user_id) throws EntityNotFoundException;
public JSONObject deleteDetail(String contact_id,String detail_id,String user_id);



}
