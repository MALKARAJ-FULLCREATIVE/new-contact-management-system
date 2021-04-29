/**
 * 
 */

let open, send, onload, onerror;

var mocks={"success":true,"code":200};

let xhrMock = {
    open: jest.fn(),
    setRequestHeader: jest.fn(),
    onload: ()=>{return JSON.stringify(mocks)},
    send: jest.fn(),
    status: 200
}


beforeAll(()=>{
	window.XMLHttpRequest = jest.fn().mockImplementation(xhrMock);
});
	
beforeEach(() => {
		jest.clearAllMocks();
	});
	

describe('Mock CRUD operations',()=>
{
	test('Get Requests', () =>
		 {

			xhrMock.open("GET", "/contact", true);
			xhrMock.send({"name":"Malkaraj"});
			expect(xhrMock.open).toBeCalledWith('GET', '/contact', true);
	    	expect(xhrMock.send).toBeCalled();
			var data=JSON.parse(xhrMock.onload());
			xhrMock.status
			expect(data["success"]).toBe(true);
	});
	
	test('Post Requests', () =>
	{
		xhrMock.open("POST", "/feed", true);
		xhrMock.send({"name":"Malkaraj","age":21});
		expect(xhrMock.open).toBeCalledWith('POST', '/contact', true);
    	expect(xhrMock.send).toBeCalledWith({"name":"Malkaraj","age":21});
		var data=JSON.parse(xhrMock.onload());
		expect(data["success"]).toBe(true);
	});

	
});