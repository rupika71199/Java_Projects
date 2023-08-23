# ***BITESPEED BACKEND TASK - IDENTITY RECONCILLATION - STEPS*** 

## Below are the steps required to clone the project and run in the local system

## Required Applications
* Docker Desktop
* Postman

## STEPS
* Download the project in zip format from GitHub
  
  ![image](https://github.com/rupika71199/Java_Projects/assets/54763077/483fae59-4b53-4a7a-a8b4-b60a04a06d9e)

* Extract the project and navigate to Bitespeed project
  
  ![image](https://github.com/rupika71199/Java_Projects/assets/54763077/bf881e82-7ea3-405f-b1af-4f3c1ac4ad76)

* Open Docker Desktop
* Make sure there is no instances that is running on 8080 port and 3307 port number. 
* Open 'cmd' in this path and type **'docker compose up'** and click Enter.
  
  ![image](https://github.com/rupika71199/Java_Projects/assets/54763077/8cf3eaa3-acab-42c8-a284-23fdc8c996df)
  ![image](https://github.com/rupika71199/Java_Projects/assets/54763077/7dba2ad6-7465-4d6a-843e-eb846ba54c68)

* Scroll down to the bottom, the message 'Started BitespeedApplication' message will get displayed along with the port number (8080)
* Now when you navigate to the Docker, there will be two containers that is running in the same network.
  
  ![image](https://github.com/rupika71199/Java_Projects/assets/54763077/c1b795a7-3073-475b-b9c5-db25cb423c66)

* Now open the postman and hit this api to add phone number or email number **(http://localhost:8080/identify)**
  
  ![image](https://github.com/rupika71199/Java_Projects/assets/54763077/30eea812-6a30-4bfa-95a3-3ad4db97c339)

*  Fetching the list of data in database can be done with this api **(http://localhost:8080/fetch)**
  
   ![image](https://github.com/rupika71199/Java_Projects/assets/54763077/789929d6-102e-4c3e-a44b-1e48407fe868)

