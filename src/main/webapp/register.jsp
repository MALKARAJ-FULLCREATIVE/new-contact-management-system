<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<script>

function register()
{

		var xhr = new XMLHttpRequest();
		xhr.open("POST", "/register", true);
		xhr.setRequestHeader('Content-Type', 'application/json');
          	

		
		var email=document.getElementById("email").value;
		
		
		var password=document.getElementById("pwd").value;
		
	
	     var obj={"email":email,"password":password};
	
	    console.log(JSON.stringify(obj));


		xhr.send(JSON.stringify(obj));
		
	
	

}




</script>


  <label for="email">Email:</label>
  <input type="email" id="email" name="email"><br><br>
  <label for="pwd">Password:</label>
  <input type="password" id="pwd" name="pwd" ><br><br>
  <input type="button"   value="register"  onclick="register()"   />
  <a href="/loginpage">login</a> 


</body>
</html>