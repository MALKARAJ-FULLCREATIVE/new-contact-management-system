package com.project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validation {

	
	public static boolean isValidEmail(String email)
	{
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
							"[a-zA-Z0-9_+&*-]+)*@" +
							"(?:[a-zA-Z0-9-]+\\.)+[a-z" +
							"A-Z]{2,7}$";
							
		Pattern pat = Pattern.compile(emailRegex);
		if (email.trim().equals(""))
			return false;
		return pat.matcher(email).matches();
	}

	public static boolean isFirstNameExist(String str)
	{
		
		if(str.trim().equals("") )
			return false;
		
		
		str = str.toLowerCase();
	      char[] charArray = str.toCharArray();
	      for (int i = 0; i < charArray.length; i++) {
	         char ch = charArray[i];
	         if (!(ch >= 'a' && ch <= 'z')) {
	            return false;
	         }
	      }
	      return true;
		
		
		
			
		
	}
	
	public static boolean isValidAddress(String str)
	{
		
		 String regex = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9' ']+$";
		 
	       
	        Pattern p = Pattern.compile(regex);
	 
	        if (str.trim().equals("")) {
	            return true;
	        }
	 
	        
	        Matcher m = p.matcher(str);
	 
	        
	        return m.matches();
		
		
	}
	public static boolean isLastNameExist(String str)
	{
		if(str.trim().equals(""))
			return true;
		

		str = str.toLowerCase();
	      char[] charArray = str.toCharArray();
	      for (int i = 0; i < charArray.length; i++) {
	         char ch = charArray[i];
	         if (!(ch >= 'a' && ch <= 'z')) {
	            return false;
	         }
	      }
	      return true;
		
		
	}
	
	public static boolean isValidId(String id)
	{
		
		
		return false;
	}
	
	
	public static boolean isValidNumber(String s)
	{
	     
	    
	    Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
	 
	   
	    Matcher m = p.matcher(s);
	    if(s.trim().equals(""))
	    	return false;
	    return (m.find() && m.group().equals(s));
	}
	
	
	public static boolean isValidType(String str)
	{
		
		if(str.equals("email")||str.equals("phone"))
			return true;
		else
			return false;
		
		
	}

}
