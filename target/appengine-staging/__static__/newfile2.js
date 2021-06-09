/**
 * 
 *//**
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



function addfn()
{


	for(let i=0;i<1000;i++)
	{
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/contact", true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	
   var obj={"contact":{"firstName":"firstName","lastName":"lastName","address":"address",
      "detail": [ {"contactType":"phone" ,"value":"7338937115"}]  }     };
       

	xhr.send(JSON.stringify(obj));
	
	

		}
getContact();

}



function togglePopup(){
console.log(togglePopup);
  document.getElementById("popup-1").classList.toggle("active");
}

function togglePopupMoreDetail(){
console.log(togglePopupMoreDetail);
  document.getElementById("popup-2").classList.toggle("active");
}

function removeDiv()
{
	 
	 console.log(removeDiv);
	var elem  = document.getElementsByClassName('demo');
	
	    
	        elem[elem.length-1].remove();
	
}	          	


function newaddMoreField(){
	
	//console.log(addMoreField);
	var txt=""
	

  var main=document.getElementById("container2");

	
	var div=document.createElement("div");
	         
	var sel = document.createElement("select");
	var opt1 = document.createElement("option");
	var opt2 = document.createElement("option");
	 			

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




function addMoreField(){
	
	//console.log(addMoreField);
	var txt=""
	

  var main=document.getElementById("container1");

	
	var div=document.createElement("div");
	         
	var sel = document.createElement("select");
	var opt1 = document.createElement("option");
	var opt2 = document.createElement("option");
	 			

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




function editContact(cid,data)
{
//console.log(editContact);
	var txt="";
	var contactid=cid;
	
	var div=document.getElementById(contactid);
var list=	div.getElementsByTagName('li');
	
	list[0].contentEditable=true;	
	
/*	var address=document.getElementById("da"+cid);
	 address.style["display"]="block";
	console.log(address.style["display"]);
   for(let i=0;i<data.length;i++)
{
	
	var div=document.getElementById("de"+data[i]["detail_id"]);
	div.style["display"]="block";
	
}
  */ 

	
	
}


function addContact()
{

//console.log(addContact);	
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/contact", true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	
	
	var firstName=document.getElementById("firstid").value;
	
	
	var lastName=document.getElementById("lastid").value;
	var address=document.getElementById("addressid").value;
	var contactType=document.getElementById("selectid").selectedOptions[0].value;
	   var value   =document.getElementById("inputid").value;
 
       var x = document.getElementById("container1").querySelectorAll(".ex");
   
  var arr=new Array();
      for( let i=0;i<x.length;i+=2)
       {
	       var y={"contactType":x[i].value,"value":x[i+1].value};
		    arr.push(y);

	}
	
	
       
   

     var obj={"contact":{"firstName":firstName,"lastName":lastName,"address":address,
      "detail": arr}     };
       
//var obj=addcontactfield();
    console.log(JSON.stringify(obj));


	xhr.send(JSON.stringify(obj));
	
	
	xhr.onload = function() {
	 getContact();

	}
	
	
}
	






function getContact(cursor="")
{
	
	
	if(Cache.has(cursor))
	{
			console.log("frome cache");
		data=Cache.get(cursor);
		
			var obj= data["contact"][0]["detail"] ;
 
 
       
toggleContact("delete");

	  addfirtslastname(data);

	  addDetail( data["contact"][0]["contact_id"],obj,data["contact"][0]["address"]  );
	

		
	}
	else
	{
		
	    
	
console.log("from server")
	//console.log(getContact);
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "/contact?cursor="+cursor, true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send();
	xhr.onload = function() {
	  var data = JSON.parse(this.responseText);
	 //console.log(data.contact["length"]);
	Cache.set(cursor,data);
	 if(data.contact["length"]!=0){
     	var obj= data["contact"][0]["detail"] ;

 
       
toggleContact("delete");

	  addfirtslastname(data);

	  addDetail( data["contact"][0]["contact_id"],obj,data["contact"][0]["address"]  );

}
	
	
}



}
}

function getDeletedContact()
{
//console.log(getDeletedContact());
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

	  addDetail( data["contact"][0]["contact_id"],obj,data["contact"][0]["address"]  );

	}
	
}


function deleteDetail(cid,did)
{
	
	//console.log(deleteDetail);
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
	
	console.log(deleteContact);
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

/*function toggle(id)
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
}*/


/*{contact:{
	
	"contact_id",
	"detail":{
	
	}
}
*/

function addMoreDetail(cid)
{
//	console.log(addMoreDetail);
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/contact/detail", true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	
	 
       var x = document.getElementById("container2").querySelectorAll(".ex");
   
  var arr=new Array();
      for( let i=0;i<x.length;i+=2)
       {
	       var y={"contactType":x[i].value,"value":x[i+1].value};
		    arr.push(y);

	}
	
	
       
   

     var obj={"contact":{"contact_id":cid,
      "detail": arr}     };
       

    console.log(JSON.stringify(obj));


	xhr.send(JSON.stringify(obj));
	
	
	xhr.onload = function() {
	 getContact();

	}

	
	
	
	
	
	
}


function addDetail(cid,data,address)
{
	
	//console.log(addDetail);
	var x=document.getElementsByClassName("fx");
	
	let check=0;
	
	
	
	
	for(let i=0;i<x.length;i++){ 
		
		
		if(x[i].getAttribute("id")!==cid){
		x[i].setAttribute("style","color:black;");
		var li= x[i].getElementsByTagName('li');
	       li[0].contentEditable=false;
		
		}
		else
		{
			x[i].setAttribute("style","color:red;");
			
		}
	
	 }

     


  
 
	
 
	
	
	var detailContainer = document.getElementById("detail");
	
		var txt1=`
<div class="popup" id="popup-2">

  <div class="overlay"></div>
  <div class="content">
    <div class="close-btn" onclick="togglePopupMoreDetail()">&times;</div>
    <h1>Title</h1>
    <form  >


<div id="container2">
contactType:   <select id="selectid" name="contactType" class="ex">
	<option value="phone">    phone 
 	<option value="email">    email     </option>
</select>

<input id="inputid" type="text" name="value"  class="ex"/> 
</div>

<input type="button" value="add" onclick="newaddMoreField()"/>
<input type="button" value="remove" onclick="removeDiv()"/>  <br/>

 <input type="button" value="submit" onclick="addMoreDetail('${cid}');" />
 
 


</form>
    
    
  </div>
</div>



`;
		
		
		
		
		
	
		 
		
		
	txt1+=`<li contenteditable=false id="A${cid}"  >  address:${address}   </li>  <input id="da${cid}"type="button" value="delete" 
	  onclick="deleteAddress('${cid}'  )"  />  `;
	
	for(let j=0;j<data.length;j++){
		
			
		
	txt1+=`<li contenteditable=false id="${data[j]["detail_id"]}" > ${data[j]["contactType"]}  ${data[j]["value"]}      </li>  <input
	id="de${data[j]["detail_id"]}"   type="button" value="delete"  
	onclick="deleteDetail('${data[j]["contact_id"]}',   '${data[j]["detail_id"]}' ) "/>`;
	 

	}
	txt1+=`<input type="button" value="addmore" onclick="togglePopupMoreDetail()"  /> `;
	
	 if(document.getElementById(cid).getElementsByTagName('li')[0].isContentEditable==true)
     {         
	txt1+=`</br></br</br></br></br</br><input type="button" value="save"  onclick="updateContact('${cid}', ${JSON.stringify(data).split('"').join("&quot;")}        )" />`;
	}
		
	detailContainer.innerHTML=txt1;	
	
	     if(document.getElementById(cid).getElementsByTagName('li')[0].isContentEditable==true)
      {

	          document.getElementById("A"+cid).contentEditable=true;
				
				
			
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



function updateContact(cid,data)
{
	
	var str=`/contact/${cid}`;
	var xhr=new XMLHttpRequest();
	xhr.open("PUT", str, true);
	xhr.setRequestHeader('Content-Type', 'application/json');		
	
	
	var firstlastName=document.getElementById(cid).getElementsByTagName('li')[0].innerHTML;
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
		
		var address=document.getElementById("A"+cid).innerHTML.trim().split(":");
		if(address[1]!="")
		{
			address=address[1];
		}
		else
		{
			address=address[2];
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
		
		
		
	/*	  for(let i=0;i<data.length;i++)
{
	
	var div=document.getElementById("de"+data[i]["detail_id"]);
	div.style["display"]="none";
	
}*/
		
		/*var addr=document.getElementById("da"+cid);
		addr.style["display"]="none";*/
		
		
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
function logoutfn()
{

 var url='/contact';
    caches.open('getcontact').then(cache => {
	cache.delete(url);
 	})

		

var xhr = new XMLHttpRequest();
	xhr.open("GET", "/logout", true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send();
	xhr.onload = function() {
	  var data = JSON.parse(this.responseText);
	console.log(data);
	
	if(data["success"]==true)
	{
	  window.location.href="/loginpage";
	}
    
     
     
  

}


}


function appendDeletedData(data) {
		
		var txt="";
        var contactContainer = document.getElementById("contact");
        
      
        for (let i = 0; i < data.contact.length; i++) 
		{
			
			txt+=`<div class=contact   >`;
			
			var obj= data["contact"][i]["detail"] ;
			 
	
			txt+=`<a onclick="addDeletedDetail('${data["contact"][i]["contact_id"]}', ${JSON.stringify(obj).split('"').join("&quot;")},'${data["contact"][i]["address"]}'   )" > 
			
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

function addDeletedDetail(cid,data,address)
{
	
	var x=document.getElementsByClassName("fx");
	
	let check=0;
	
	
	
	
	for(let i=0;i<x.length;i++){ 
		
		
		if(x[i].getAttribute("id")!==cid){
		x[i].setAttribute("style","color:black;");
		var li= x[i].getElementsByTagName('li');
	       li[0].contentEditable=false;
		
		}
		else
		{
			x[i].setAttribute("style","color:red;");
			
		}
	
	 }

     


  
 
	
 
	
	
	var detailContainer = document.getElementById("detail");
	
		var txt1="";
		 
		
		
	txt1+=`<li contenteditable=false id="A${cid}"  >  address:${address}   </li>  `;
	
	for(let j=0;j<data.length;j++){
		
			
		
	txt1+=`<li contenteditable=false id="${data[j]["detail_id"]}" > ${data[j]["contactType"]}  ${data[j]["value"]}      </li> `;
	 

	}
	
	 if(document.getElementById(cid).getElementsByTagName('li')[0].isContentEditable==true)
     {         
	txt1+=`</br></br</br></br></br</br><input type="button" value="save"  onclick="updateContact('${cid}', ${JSON.stringify(data).split('"').join("&quot;")}        )" />`;
	}
		
	detailContainer.innerHTML=txt1;	
	
	     if(document.getElementById(cid).getElementsByTagName('li')[0].isContentEditable==true)
      {

	          document.getElementById("A"+cid).contentEditable=true;
				
				
			
				for(let i=0;i<data.length;i++)
				{
					document.getElementById(data[i]["detail_id"]  ).contentEditable=true;
				   
				}
				
				
								
		}
		

	


}


function addfirtslastname(data) {
		
		
		//console.log(data);
		var txt="";
        var contactContainer = document.getElementById("contact");
        
      //${JSON.stringify(obj).split('"').join("&quot;")}
        for (let i = 0; i < data.contact.length; i++) 
		{
			
			txt+=`<div class=contact   >`;
			
			var obj= data["contact"][i]["detail"] ;
			// console.log("hi"+obj+'hi');
	
			txt+=`<a onclick="addDetail('${data["contact"][i]["contact_id"]}', ${JSON.stringify(obj).split('"').join("&quot;")},'${data["contact"][i]["address"]}'   )" > 
			
			 <div id="${data["contact"][i]["contact_id"]}" style="color:black;"  class="fx"   >
			<li contenteditable=false > ${data["contact"][i]["firstName"]} ${data["contact"][i]["lastName"]} </li>
			<img src= "images/delete.png" onclick="deleteContact('${data["contact"][i]["contact_id"]}')" width="40" height="40"> 
			<img src= "images/edit.png" onclick="editContact('${data["contact"][i]["contact_id"]}' ,${JSON.stringify(obj).split('"').join("&quot;")} )" style="padding:8px;"  width="40" height="25"   />
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

txt+=`<input type="button" value="loadnext20"  onclick="getContact('${data.cursor}')" /> `


	contactContainer.innerHTML+=txt;
	
	
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


