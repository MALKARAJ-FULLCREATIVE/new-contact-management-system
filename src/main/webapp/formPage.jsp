<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style>

div:focus {
    background-color:red;
}

#mydata {
  margin: auto;
  width: 40%;
border-style: dotted;
  border-color: blue;
  padding: 10px;
} 



#contact{
overflow:scroll;
height:500px;
}


.contact
{
     margin: auto;
     border-style: solid;
      border-color: black;
}

.active{
background-color:#e4ffff;

}



.flex-container {

  display: flex;
  
  font-size: 20px;
  text-align: center;
}

.flex-item-left {

  padding: 10px;
  flex: 10%;
  
       border-style: solid;
        text-align: center;
  border-color: black;
}

.flex-item-right {
  
  padding: 10px;
  
  flex: 50%;
 
       border-style: solid;
  border-color: black;
}

.popup .overlay {
  position:fixed;
  top:0px;
  left:0px;
  width:100vw;
  height:100vh;
  background:rgba(0,0,0,0.7);
  z-index:1;
  display:none;
}
 
.popup .content {
  position:absolute;
  top:50%;
  left:50%;
  transform:translate(-50%,-50%) scale(0);
  background:#fff;
   margin: auto;
     border-style: solid;
      border-color: black;
  z-index:2;
  text-align:center;
  padding:20px;
  box-sizing:border-box;
  font-family:"Open Sans",sans-serif;
}
 
.popup .close-btn {
  cursor:pointer;
  position:absolute;
  right:20px;
  top:20px;
  width:30px;
  height:30px;
  background:#222;
  color:#fff;
  font-size:25px;
  font-weight:600;
  line-height:30px;
  text-align:center;
  border-radius:50%;
}
 
.popup.active .overlay {
  display:block;
}
 
.popup.active .content {
  transition:all 300ms ease-in-out;
  transform:translate(-50%,-50%) scale(1);
}
 



</style>









</head>



<style>
li{
list-style-type:none;

}

</style>


<body onload="getContact()">


<%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
if(session.getAttribute("user_id")==null)
	response.sendRedirect("/loginpage");
%>


<div class="imageHover" id="profileButton">
<input type="button"  value="profile" onclick="getProfile();setActive('profileButton');toggleProfile();" />
</div>
<div class="imageHover active" id="feedButton">
<input type="button"  value="contacts" onclick="getContact();setActive('feedButton');toggleToFeed();"/>
</div>













<div id="myProfile"></div>


<div   id="container" class="flex-container">
  <div id="contact" class="flex-item-left"> </div>
  <div id="detail" class="flex-item-right"> </div>
 
</div>



<div class="popup" id="popup-1">

  <div class="overlay"></div>
  <div class="content">
    <div class="close-btn" onclick="togglePopup()">&times;</div>
    <h1>Title</h1>
    <form  >

firstName:     <input id="firstid"  name="firstName" required /><br/>
lastName:             <input id="lastid" type="text" name="lastName"/><br/>
address:   <input id="addressid" type="text" name="address"/><br/>

<div id="container1">
contactType:   <select id="selectid" name="contactType" class="ex">
	<option value="phone">    phone 
 	<option value="email">    email     </option>
</select>

<input id="inputid" type="text" name="value"  class="ex"/> 
</div>




<input type="button" value="add" onclick="addMoreField()"/>
<input type="button" value="remove" onclick="removeDiv()"/>  <br/>

 <input type="button" value="submit" onclick="addContact();" />
 
 
 

 
 


</form>
    
    
  </div>
</div>







 <br/>
 
 <div id="buttons">
<img src= "images/plusnew.jpg" onclick="togglePopup()" width="40" height="40"> 

<img id="delete" src= "images/delete.png" onclick="getDeletedContact()" width="40" height="40"> 
 <input type="button" value="logout" onclick="logoutfn()"/>
 
 
<input type="button" value="addcontact" onclick="addfn()"/>
 <input type="button" value="deleteall" onclick="showMessage(),deleteAll()"/>
 

</div>


	            

<script type="text/javascript" src="file.js"></script>


<script type="text/javascript" src="cachejs.js">
</script>

<script type="text/javascript" src="profilejs.js">
</script>


</body>
</html>