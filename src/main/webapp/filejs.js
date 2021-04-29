/**
 * 
 */


/*function validateFirstName()
{


var firstName=document.getElementById("firstid").value;
   var letters = /^[A-Za-z]+$/;
   if(firstName.match(letters) && value!=null)
     {
      return true; 
     }
   else
     {
     alert("firstName is not in proper format");
     return false;
     } 
  
	
}*/

function togglePopup(){
  document.getElementById("popup-1").classList.toggle("active");
}

function removeDiv()
{
	 
	var elem  = document.getElementsByClassName('demo');
	
	    
	        elem[elem.length-1].remove();
	
}	          	



function addMoreField(){
	
	var txt=""
	

  var main=document.getElementById("container");

	
	var div=document.createElement("div");
	         
	var sel = document.createElement("select");
	var opt1 = document.createElement("option");
	var opt2 = document.createElement("option");
	 			
    var main=document.getElementById("container");
    sel.setAttribute('class','ex');

	opt1.value = "phone";
	opt1.text = "phone";

	opt2.value = "email";
	opt2.text = "email";

	sel.add(opt1, null);
	sel.add(opt2, null);
	
	
	var inp=document.createElement("input");
	    inp.setAttribute('class','ex');
	 
    div.setAttribute('class','demo');
	div.appendChild(sel);
	div.appendChild(inp);
	main.appendChild(div);
	
	


}


function editContact(data)
{
	var txt="";
	var contactid=data[0]["contact_id"];
	
	var div=document.getElementById(contactid);
var list=	div.getElementsByTagName('li');
	
	list[0].contentEditable=true;	
	
   

	
	
}


function addcontactfield()
{
	
	var firstName=document.getElementById("firstid").value;
	
	
	var lastName=document.getElementById("lastid").value;
	var address=document.getElementById("addressid").value;
	var contactType=document.getElementById("selectid").selectedOptions[0].value;
	   var value   =document.getElementById("inputid").value;
 
       var x = document.getElementById("container").querySelectorAll(".ex");
   
  var arr=new Array();
      for( let i=0;i<x.length;i+=2)
       {
	       var y={"contactType":x[i].value,"value":x[i+1].value};
		    arr.push(y);

	}
	
	
       
   

     var obj={"contact":{"firstName":firstName,"lastName":lastName,"address":address,
      "detail": arr}     };
       
	
	
	return obj;
}

function addContact()
{
	
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/contact", true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	
	
	/*var firstName=document.getElementById("firstid").value;
	
	
	var lastName=document.getElementById("lastid").value;
	var address=document.getElementById("addressid").value;
	var contactType=document.getElementById("selectid").selectedOptions[0].value;
	   var value   =document.getElementById("inputid").value;
 
       var x = document.getElementById("container").querySelectorAll(".ex");
   
  var arr=new Array();
      for( let i=0;i<x.length;i+=2)
       {
	       var y={"contactType":x[i].value,"value":x[i+1].value};
		    arr.push(y);

	}
	
	
       
   

     var obj={"contact":{"firstName":firstName,"lastName":lastName,"address":address,
      "detail": arr}     };*/
       
var obj=addcontactfield();
    console.log(JSON.stringify(obj));


	xhr.send(JSON.stringify(obj));
	
	
	xhr.onload = function() {
	 getContact();

	}
	
	
}
	






function getContact()
{
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "/contact", true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send();
	xhr.onload = function() {
	  var data = JSON.parse(this.responseText);
	// console.log(data);
     	var obj= data["contact"][0]["detail"] ;

 
       
toggleContact("delete");

	  addfirtslastname(data);

	  addDetail( obj,data["contact"][0]["address"]  );

	}
	
}


function getDeletedContact()
{
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "/contact/garbage", true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send();
	xhr.onload = function() {
	  var data = JSON.parse(this.responseText);
	// console.log(data);
     	var obj= data["contact"][0]["detail"] ;

 

    toggleBin("delete");
       
	  appendDeletedData(data);

	  addDetail( obj,data["contact"][0]["address"]  );

	}
	
}


function deleteDetail(cid,did)
{
	
	var str=`/contact/detail/delete/${cid}/${did}`;
	var xhr=new XMLHttpRequest();
	xhr.open("DELETE", str, true);
	xhr.setRequestHeader('Content-Type', 'application/json');	
	xhr.send();
	xhr.onload=function()
	{
		getContact();
	}
}







function deleteContact(id)
{
	
	var str=`/contact/${id}`;
	var xhr=new XMLHttpRequest();
	xhr.open("DELETE", str, true);
	xhr.setRequestHeader('Content-Type', 'application/json');	
	xhr.send();
	xhr.onload=function()
	{
		getContact();
	}
}

function toggle(id)
{
	console.log("he");
	var div1=document.getElementById(id);
	if(div1.style["display"]=="none")
	{

		div1.style["display"]="block";
	}
	else
	{
		div1.style["display"]="none";
	}
}


function addDetail(data,address)
{
	
	var x=document.getElementsByClassName("fx");
	
	let check=0;
	
	/*for(let i=0;i<x.length;i++){ 
		
		
		
		if(x[i].getAttribute("id")!==data[0]["contact_id"]){
		x[i].setAttribute("style","color:black;");
		var li= x[i].getElementsByTagName('li');
	       li[0].contentEditable=false;
		
		}
		else
		{
			x[i].setAttribute("style","color:red;");
			
		}
	
	 }*/
     


  
 
	
 
	
	
	var detailContainer = document.getElementById("detail");
	
		var txt1="";
		 
		
		
	txt1+=`<li contenteditable=false id="A${data[0]["contact_id"]}"  >  address:${address}   </li>  <input type="button" value="delete"
	  onclick="deleteAddress('${data[0]["contact_id"]}'  )"/>`;
	
	for(let j=0;j<data.length;j++){
		
			
		
	txt1+=`<li contenteditable=false id="${data[j]["detail_id"]}" > ${data[j]["contactType"]}  ${data[j]["value"]}      </li>  <input type="button" value="delete"  
	onclick="deleteDetail('${data[j]["contact_id"]}',   '${data[j]["detail_id"]}' ) "/>`;
	 

	}
	
	 if(document.getElementById(data[0]["contact_id"]).getElementsByTagName('li')[0].isContentEditable==true)
     {         
	txt1+=`</br></br</br></br></br</br><input type="button" value="save"  onclick="updateContact(     ${JSON.stringify(data).split('"').join("&quot;")}        )" />`;
	}
		
	detailContainer.innerHTML=txt1;	
	
	     if(document.getElementById(data[0]["contact_id"]).getElementsByTagName('li')[0].isContentEditable==true)
      {

	          document.getElementById("A"+data[0]["contact_id"]).contentEditable=true;
				
				
			
				for(let i=0;i<data.length;i++)
				{
					document.getElementById(data[i]["detail_id"]  ).contentEditable=true;
				   
				}
				
				
								
		}
		

	
	

}

function deleteAddress(cid)
{
   
	var str=`/contact/${cid}`;
	var xhr=new XMLHttpRequest();
	xhr.open("PUT", str, true);
	xhr.setRequestHeader('Content-Type', 'application/json');		
	var obj= { "contact" :
	{"address":" "} 
	};
	
xhr.send(JSON.stringify(obj));

	xhr.onload=function()
	{
		getContact();
	}
	

}



function updateContact(data)
{
	
	var str=`/contact/${data[0]["contact_id"]}`;
	var xhr=new XMLHttpRequest();
	xhr.open("PUT", str, true);
	xhr.setRequestHeader('Content-Type', 'application/json');		
	
	
	var firstlastName=document.getElementById(data[0]["contact_id"]).getElementsByTagName('li')[0].innerHTML;
	 firstlastName=firstlastName.trim().split(" ");
	 
     var firstName="";
	 var lastName="";
     var address="";
     if(firstlastName.length==1)
     {
	     firstName=firstlastName[0];

		}
		else
		{
			firstName=firstlastName[0];
		   lastName=firstlastName[1];	
		}
		
		var address=document.getElementById("A"+data[0]["contact_id"]).innerHTML.trim().split(":");
		if(address[1]!="")
		{
			address=address[1].trim();
		}
		else
		{
			address=address[2].trim();
		}
		
		var arr=[];
		
		
		var value="";
		for(let i=0;i<data.length;i++)
		{
			var contactTypePhone=document.getElementById(data[i]["detail_id"] ).innerHTML.trim().split(" ");
			if(contactTypePhone[1]!="")
			  {
				value=contactTypePhone[1].trim();
			}
			else
			{
				value=contactTypePhone[2].trim();
			}
			
			
			 var y={"contact_id":data[i]["contact_id"],"detail_id":data[i]["detail_id"] ,"contactType":contactTypePhone[0].trim(),"value":value};
		    arr.push(y);
			
		}
		
		
		
		
		
		
		
 var obj={"contact":{"firstName":firstName,"lastName":lastName,"address":address,
      "detail": arr}     };
       


xhr.send(JSON.stringify(obj));


	xhr.onload=function()
	{
		getContact();
	}


       
	
}

function changeColor(id)
{
	var div=document.getElementById(id);
	div.style["color"]="red";
	
	console.log(div);
}



function appendDeletedData(data) {
		
		var txt="";
        var contactContainer = document.getElementById("contact");
        
      
        for (let i = 0; i < data.contact.length; i++) 
		{
			
			txt+=`<div class=contact   >`;
			
			var obj= data["contact"][i]["detail"] ;
			 
	
			txt+=`<a onclick="addDetail( ${JSON.stringify(obj).split('"').join("&quot;")},'${data["contact"][i]["address"]}'   )" > 
			
			 <div id="${data["contact"][i]["contact_id"]}" style="color:black;"  class="fx"   >
			<li contenteditable=false > ${data["contact"][i]["firstName"]} ${data["contact"][i]["lastName"]} </li>
		 
		
			</li> 
			</div>
			
			
			 </a>`;
		   
			
			
			
			//txt1+=`<div id= "${data["contact"][i]["contact_id"]}" style="display:none;"    class="contact">`; 
			
			//txt1+=`<h4 > address</br> ${data["contact"][i]["address"]} </h4>`; 
			
		//for(let j=0;j<data.contact[i].detail.length;j++){
				
			//txt1+=`<h4 id="${data["contact"][i]["detail"][j]["detail_id"]}">  ${data["contact"][i]["detail"][j]["contactType"]}  <br/> ${data["contact"][i]["detail"][j]["value"] } <br/>  </h4> `;
	 

		// }	
			txt+=`</div>`;
          //   txt1+=`</div>`;		

}


	contactContainer .innerHTML=txt;
	
	
}



function addfirtslastname(data) {
		
		var txt="";
        var contactContainer = document.getElementById("contact");
        
      
        for (let i = 0; i < data.contact.length; i++) 
		{
			
			txt+=`<div class=contact   >`;
			
			var obj= data["contact"][i]["detail"] ;
			 
	
			txt+=`<a onclick="addDetail( ${JSON.stringify(obj).split('"').join("&quot;")},'${data["contact"][i]["address"]}'   )" > 
			
			 <div id="${data["contact"][i]["contact_id"]}" style="color:black;"  class="fx"   >
			<li contenteditable=false > ${data["contact"][i]["firstName"]} ${data["contact"][i]["lastName"]} </li>
			<img src= "images/delete.png" onclick="deleteContact('${data["contact"][i]["contact_id"]}')" width="40" height="40"> 
			<img src= "images/edit.png" onclick="editContact( ${JSON.stringify(obj).split('"').join("&quot;")})" style="padding:8px;"  width="40" height="25"   />
			</li> 
			</div>
			
			
			 </a>`;
		   
			
			
			
			//txt1+=`<div id= "${data["contact"][i]["contact_id"]}" style="display:none;"    class="contact">`; 
			
			//txt1+=`<h4 > address</br> ${data["contact"][i]["address"]} </h4>`; 
			
		//for(let j=0;j<data.contact[i].detail.length;j++){
				
			//txt1+=`<h4 id="${data["contact"][i]["detail"][j]["detail_id"]}">  ${data["contact"][i]["detail"][j]["contactType"]}  <br/> ${data["contact"][i]["detail"][j]["value"] } <br/>  </h4> `;
	 

		// }	
			txt+=`</div>`;
          //   txt1+=`</div>`;		

}


	contactContainer.innerHTML=txt;
	
	
}


var toggleContact=(id)=>{
var cont=document.getElementById(id);
cont.onclick=function(){getDeletedContact()};
cont.src="images/delete.png";
}
var toggleBin=(id)=>{
	
var bin=document.getElementById(id);
bin.onclick=function(){getContact()};
bin.src="images/contact.png"
console.log(bin);
}



