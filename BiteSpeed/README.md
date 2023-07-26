# ***BITESPEED BACKEND TASK - IDENTITY RECONCILLATION*** 

#### FluxKart.com is an online shopping platform. The platform is very serious about their customer experience. A person named Doc is trying to use different email address and phone numbers for different orders. 
#### Bitespeed needs to identify the customer's identity across multiple purchases. 

Bitespeed has the people's data in the below format: 

![image](https://github.com/rupika71199/Java_Projects/assets/54763077/d4742dba-448f-4bbd-9545-37082b16e3d9)
 
### CONTEXT
* If a customer is ordering the product, they will be using eiter email address or phone number.
* If the customer orders for the first time, then the linkedPreceedence should be marked as primary
* If the customer orders for the second time with different email but with same phone number or vice versa, then the linkedPreceedence is marked as secondary and linkedId is primary id.

The task is to create the end point that gives the response: 
![image](https://github.com/rupika71199/Java_Projects/assets/54763077/b380337f-7a4b-43e0-a79d-61a69ebd2cf1)

when the request is given as 

![image](https://github.com/rupika71199/Java_Projects/assets/54763077/026cb789-2262-4815-a7e6-ad0714bf8d93)

### Tech Stacks Used: 
* Java
* Spring Boot
* Spring Data

### Project SetUp: 
* Clone this project and extract it.
* Create the database name called **bite** using **create database bite** in MySql terminal / workbench
* Open the project in ide.
* Navigate to BiteSpeedApplication.java and click on run.
* Open Postman and give this api in HTTP request : http://localhost:8081/identify
* The body should be selected as raw and paste this:
   {
	"email": "pqr@gmail.com",
	"phoneNumber": "23456"
  }
* Hit on Send
* Try changing anyone of the param and hit send.
  
![image](https://github.com/rupika71199/Java_Projects/assets/54763077/c7c48703-fc8b-4dbb-89b9-4acd645d8cd2)

* Now again change different value in body. In this case, the secondary which was assigned previously will get changed to primary.

![image](https://github.com/rupika71199/Java_Projects/assets/54763077/5f58c03d-030b-409b-9573-20a5974f9835)

* An another endpoint is created to fetch all the details in the database: http://localhost:8081/fetch
* The response received while hitting this api:

![image](https://github.com/rupika71199/Java_Projects/assets/54763077/c9d1d588-d3e7-490a-b98c-ac46bbbc3a38)



 

