schema for contactmanagement

2 table one is contact and detail

contact(parent)
 contact_id(primary key) , 
 Firstname ,  
 lastname , 
 address ,  


details(child) -d
 contact_id(foreign key , 
 detail_id(primary key) , 
 contacttype(email/phonenumber) , 
 value







{
   
   
   "contact": 
        {
           
           
           "firstName": "malkaraj",
           
           
           "lastName":" ",
           
           
           "address":"no 264 2nd street",
           
           "detail": 
            
            [
                {
                   
                    "contactType": "phone",
                    "value": "1234567890"
                },
                 {
                   
                    "contactType": "email",
                    "value": "malk@gmail.com"
                }

            ]
        }
}
