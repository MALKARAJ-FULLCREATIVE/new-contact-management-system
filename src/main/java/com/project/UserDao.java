package com.project;

import org.json.JSONObject;

import com.google.appengine.api.datastore.EntityNotFoundException;

public interface UserDao {
	
	
	
	public boolean createUser(User u);
	public String getUserId(String email ,String password);
	public JSONObject getUser(String userId) throws EntityNotFoundException;
	public Boolean udpateImage(String userId,String name) throws EntityNotFoundException;
	public boolean deleteUser(String userId);
	
	

}
