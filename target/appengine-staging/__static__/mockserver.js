var mocks={
    "code": "200",
    "success": true,
    "contact":[
        {
	 		"contact_id":"1f05986e-a343-421a-8d16-deb35ea44aa1",
		    "firstName":"xyz",
            "lastName":"abc",
           "detail":[
	
	  { 
		
		"detail_id":"3f05986e-a343-421a-8d16-deb35ea44aa3",
		"contactType":"phone",
		 "value":"8838973587"
	}
	
]
		}
		]
    
};

let getServer = {
    open: jest.fn(),
    setRequestHeader: jest.fn(),
    onload: ()=>{return JSON.stringify(mocks)},
    send: jest.fn(),
    status: 200
}

let postServer = {
    open: jest.fn(),
    setRequestHeader: jest.fn(),

    send: (data)=>{
			data["contact_id"]=UUID().toString();
			
			mocks.contact.push(data);

		},
    onload: ()=>{
					return JSON.stringify(mocks);
				},
    status: 200
}



let putServer = {
    open: jest.fn(),
    setRequestHeader: jest.fn(),

    send: (data)=>{
			
			for(let i=0;i<mocks.contact.length;i++)
			{
				if(mocks.contact[i]["contact_id"]==data["contact_id"])
				{
					delete mocks.contact[i].firstName;
					mocks.contact[i].firstName=data["firstName"];
				}
			}
		},
    onload: ()=>{
					return JSON.stringify(mocks);
				},
    status: 200
}


let DeleteServer={
	open:jest.fn(),
	setRequestHeader: jest.fn(),

    send: (data)=>{
			
			for(let i=0;i<mocks.contact.length;i++)
			{
				if(mocks.contact[i]["contact_id"]==data["contact_id"])
				{
					delete mocks.contact[i].firstName;
					mocks.contact[i].firstName=data["firstName"];
				}
			}
		},
    onload: ()=>{
					return JSON.stringify(mocks);
				},
    status: 200
}
	







function UUID(){
    var dt = new Date().getTime();
    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = (dt + Math.random()*16)%16 | 0;
        dt = Math.floor(dt/16);
        return (c=='x' ? r :(r&0x3|0x8)).toString(16);
    });
    return uuid;
}

module.exports={getServer,postServer,putServer};