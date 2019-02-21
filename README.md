Project: Employee Web Service with CRUD tasks
=============================================

The project is an implementation of CRUD tasks through RestFul Web
Services

Used Tecnhologies 
------------------

Java 1.8

SpringBoot 2.1.3

H2 DB

Desing Patterns used in particular 
-----------------------------------

DTO

Content Negotiation

Repository

Note:

See the following classes in order to see additional comments

EmployeeController,EmployeeDto,EmployeeRepository

Prerequisites
-------------

None

Getting Started
---------------

git clone https://github.com/carlosmatus/Employee.git

mvn package -f Employee/

java -jar Employee/target/EmployeeRepository-0.0.1-SNAPSHOT.jar

CURL commands
-------------

GET All employees

curl -X GET \\

http://localhost:8080/employees \\

-H \'Accept: application/json\' \\

-H \'Content-Type: application/json\' \\

-H \'Postman-Token: 0e235755-61c9-4eec-bf71-9ba48f1d9acb\' \\

-H \'cache-control: no-cache\'

GET Specific Employee

curl -X GET \\

http://localhost:8080/employees/1 \\

-H \'Accept: application/json\' \\

-H \'Content-Type: application/json\' \\

-H \'Postman-Token: 48510edb-adb8-482c-8e32-543f86502bea\' \\

-H \'cache-control: no-cache\'

Update Employee

{\"id\":1,\"firstName\":\"Thomas\",\"middleInitial\":\"J\",\"lastName\":\"Zuno\",\"dateOfBirth\":\"1980-02-14\",\"dateOfEmployment\":\"2019-01-02\",\"status\":\"ACTIVE\"}

curl -X PUT \\

http://localhost:8080/employees/1 \\

-H \'Accept: application/json\' \\

-H \'Content-Type: application/json\' \\

-H \'Postman-Token: 0b260d02-c2c1-40c7-a6fb-e5cfea02da7d\' \\

-H \'cache-control: no-cache\' \\

-d \'{

\"firstName\": \"Thomas Updated\",

\"middleInitial\": \"J\",

\"lastName\": \"Zuno Updated\",

\"dateOfBirth\": \"1980-02-14\",

\"dateOfEmployment\": \"2018-01-02\"

}\'

Create Employee

curl -X POST \\

http://localhost:8080/employees/ \\

-H \'Accept: application/json\' \\

-H \'Content-Type: application/json\' \\

-H \'Postman-Token: 6255fca5-306e-4877-bbc3-6da2a4e15e0b\' \\

-H \'cache-control: no-cache\' \\

-d \'
{\"firstName\":\"Luis\",\"middleInitial\":\"K\",\"lastName\":\"Miranda\",\"dateOfBirth\":\"1970-09-14\",\"dateOfEmployment\":\"1999-01-02\"}\'

Delete Employee

Note:

In Postman you can send the request with Basic Auth and user name =
user, pasword:password

curl -X DELETE \\

http://localhost:8080/employees/3 \\

-H \'Accept: application/json\' \\

-H \'Authorization: Basic dXNlcjpwYXNzd29yZA==\' \\

-H \'Content-Type: application/json\' \\

-H \'Postman-Token: e7dba0ec-2d3e-4c90-a10b-85ef95fc0e87\' \\

-H \'cache-control: no-cache\'


Author: Carlos Matus
