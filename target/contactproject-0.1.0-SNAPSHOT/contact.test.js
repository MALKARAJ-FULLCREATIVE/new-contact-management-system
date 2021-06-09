

var server=require("./mockServer.js")


describe('Mock CRUD operations',()=>
{
	
		
	test('Get Requests', () =>
	{
		window.XMLHttpRequest = jest.fn().mockImplementation(server.getServer);
		server.getServer.open("Get", "/contact", true);
		server.getServer.send();
		expect(server.getServer.open).toBeCalledWith("Get", "/contact", true)
		expect(server.getServer.send).toBeCalled();
		var data=JSON.parse(server.getServer.onload());
		console.log(data);
		expect(data["success"]).toBe(true);
		jest.clearAllMocks();

	});
	
	test('Post Requests', () =>
	{
		window.XMLHttpRequest = jest.fn().mockImplementation(server.postServer);
		server.postServer.open("POST", "/contact", true);
		var obj= {
			
		    "firstName":"xyz",
            "lastName":"abc",
           "detail":[
	
	  { 
		
		"detail_id":"3f05986e-a343-421a-8d16-deb35ea44aa3",
		"contactType":"phone",
		 "value":"8838973587"
	}
	
]
		};

		server.postServer.send(obj);
		var data=JSON.parse(server.postServer.onload());
		console.log(data);
		expect(data["success"]).toBe(true);
		expect(data["contact"].length==2);
		jest.clearAllMocks();


	});
	
	
	test('Put Request'  ,()  =>    {
		window.XMLHttpRequest = jest.fn().mockImplementation(server.postServer);
		var str=`/contact`;	
	server.putServer.open("PUT", str, true);
	
		var obj= {
		"contact_id":"1f05986e-a343-421a-8d16-deb35ea44aa1",
		    "firstName":"malk",
            
		};
		server.putServer.send(obj);
		var data=JSON.parse(server.putServer.onload());
	  	expect(data["success"]).toBe(true);  
console.log(data["contact"]);
        expect(data["contact"][0].firstName).toBe("malk");
jest.clearAllMocks();
		
		
		
		
		
		
		
	});
	
	test('Delete Request',()=>{
		
	});
	
	

	
	
});



