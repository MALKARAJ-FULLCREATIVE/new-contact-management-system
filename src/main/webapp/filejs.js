/**
 * 
 */


function validateFirstName()
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
  
	
}

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




function addContact()
{
	
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/contact", true);
	xhr.setRequestHeader('Content-Type', 'application/json');
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
	 console.log(data);
     	var obj= data["contact"][0]["detail"] ;

 //createDetail( obj,data["contact"][0]["address"]  );
	  appendData(data);
	  createDetail( obj,data["contact"][0]["address"]  );
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


function createDetail(data,address)
{
	
	var x=document.getElementsByClassName("fx");
	
	
	
	for(let i=0;i<x.length;i++){
		
		if(x[i].getAttribute("id")!==data[0]["contact_id"]){
		x[i].setAttribute("style","color:black;");
		}
		else
		{
			x[i].setAttribute("style","color:red;");
		}
	
	 }
     
  
 
	

	
	
	var detailContainer = document.getElementById("detail");
	
		var txt1="";
		
		
		
	txt1+=`<li  id="A${data[0]["contact_id"]}"  >  address ${address}  </br> </li>`;
	for(let j=0;j<data.length;j++){
		
			
		
	txt1+=`<li id="${data[j]["detail_id"]}" > ${data[j]["contactType"]}  ${data[j]["value"]} <br/>  </li> `;
	 

	}
	
	
		
	detailContainer.innerHTML=txt1;	
	
	

}

function changeColor(id)
{
	var div=document.getElementById(id);
	div.style["color"]="red";
	
	console.log(div);
}

function appendData(data) {
		
		var txt="";
        var contactContainer = document.getElementById("contact");
        
        // var txt1="";

          

        for (let i = 0; i < data.contact.length; i++) 
		{
			
			txt+=`<div class=contact   >`;
			
			var obj= data["contact"][i]["detail"] ;
			 
	
			txt+=`<a onclick="createDetail( ${JSON.stringify(obj).split('"').join("&quot;")},'${data["contact"][i]["address"]}'   )" > 
			
			 <div id="${data["contact"][i]["contact_id"]}" style="color:black;"  class="fx"   >
			<li > ${data["contact"][i]["firstName"]} ${data["contact"][i]["lastName"]} <br/>
			<input type="button" onclick="editFuncion('${data["contact"][i]["contact_id"]}')" value="edit"/>
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

 //console.log(document.getElementById(data["contact"][0]["contact_id"] ));
	contactContainer.innerHTML=txt;
	
	//detailContainer.innerHTML=txt1;
}