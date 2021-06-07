<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="google-signin-client_id" content="354739725161-49gg1fnf7qhehejirguegte1ovlkaup2.apps.googleusercontent.com">
<title>Insert title here</title>
</head>
<body>

<%

response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
if(session.getAttribute("user_id")!=null)
{
	session.invalidate();
	response.sendRedirect("/loginpage");
	
	
	//response.sendRedirect("/");
}

%>


<script src="https://apis.google.com/js/platform.js" async defer></script>

<script>


function login()
{

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/loginpage", true);
	xhr.setRequestHeader('Content-Type', 'application/json');
      	

	
	var email=document.getElementById("email").value;
	
	
	var password=document.getElementById("pwd").value;
	

     var obj={"email":email,"password":password};

    console.log(JSON.stringify(obj));
    
    


	xhr.send(JSON.stringify(obj));
	
	
	xhr.onload=function()
	{
		var data=  JSON.parse(this.responseText);
		
		if(data["success"]==true)
		{
		window.location.href = "/";

		}
		else
			{
			alert("invalid username and password");
			}
		
		
		//console.log(data);
		
	}
	
	
	}
	
function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
				  
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
	}


</script>



  <label for="email">Email:</label>
  <input type="email" id="email" name="email"><br><br>
  <label for="pwd">Password:</label>
  <input type="password" id="pwd" name="pwd" ><br><br>
  <input type="button"   value="login"  onclick="login()">
	<div class="g-signin2" data-onsuccess="onSignIn"></div>


</body>
</html>