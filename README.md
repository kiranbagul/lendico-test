#### Loan schedule generator : 

 - ##### Generate plan
     Request<br><br> ``POST /api/generate-plan``
      <br><br>Response
     ```json
      {
        "duration": 24,
        "loanAmount": 5000,
        "nominalRate": 5,
        "startDate": "2019-11-02T23:51:27Z"
      }
     ```
 
      
## How to start the service

      
#### Prerequisites
 
  - Java 8
  - maven
  - docker
 
#### Build & Execute

> mvn clean install  
> mvn spring-boot:run
    
#### Execute with Docker

> docker build -t loan-scheduler .  
> docker run -p 8081:8081 loan-scheduler

### Endpoints 

#### Application
    http://localhost:8081/api

#### Swagger Documentation
    http://localhost:8081/swagger-ui.html
