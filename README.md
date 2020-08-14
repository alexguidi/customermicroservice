![Build](https://github.com/alexguidi/customermicroservice/workflows/Build/badge.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=alexguidi_customermicroservice&metric=alert_status)](https://sonarcloud.io/dashboard?id=alexguidi_customermicroservice)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=alexguidi_customermicroservice&metric=coverage)](https://sonarcloud.io/dashboard?id=alexguidi_customermicroservice)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=alexguidi_customermicroservice&metric=bugs)](https://sonarcloud.io/dashboard?id=alexguidi_customermicroservice)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=alexguidi_customermicroservice&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=alexguidi_customermicroservice)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=alexguidi_customermicroservice&metric=code_smells)](https://sonarcloud.io/dashboard?id=alexguidi_customermicroservice)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=alexguidi_customermicroservice&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=alexguidi_customermicroservice)

# customermicroservice project
 This Spring Boot project will perform a CRUD (Create/Read/Update/Delete) of Customers. The project was developed using microservices concepts, continuous integration (CI) and continuous delivery (CD), the CI/CD Pipeline was automated using GitHub actions.

 ## Getting Started
 These instructions will get you a copy of the project up and running on your local machine, you'll be able to start use the application after following these steps.

### Prerequisites

```
- Docker

The prerequisites below are needed only if you want to run the application out of container:
- JDK 8
- Maven
- MongoDB
```

### Installing
The application was developed using containers, so basically to install and run the application you need to use only the command below:

```
docker-compose up
```

The application will be up in the following host and port

```
localhost:9000
```

## Using the application
The application is a REST API that accepts CRUD calls by using the HTTP methods (GET, POST and DELETE). You can call the application using cURL, postman or whatever the REST client you prefer.

The project is using Spring Security, so the calls need to set Authorization as basic and use the following user/password: basic_user_auth/basic_pass_auth

The cURLs examples below are based in the running application in Red Hat OpenShift platform, but you can replace ***customermicroservice-customermicroservice.apps.ca-central-1.starter.openshift-online.com*** with ***localhost:9000*** if you want to run only local.

Some cURLs examples:

**For Create (POST) operation (CREATE A NEW CUSTOMER)**

```
curl --location --request POST 'http://customermicroservice-customermicroservice.apps.ca-central-1.starter.openshift-online.com/api/v1/customer/' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YmFzaWNfdXNlcl9hdXRoOmJhc2ljX3Bhc3NfYXV0aA==' \
--data-raw '{
    "name": "Giusepe",
    "surname": "Guidi",
    "email": "giuseppe.guidi@hotmail.com",
    "initials": "GG",
    "mobile": "14988038312"
}'
```

**For Update (UPDATE) operation (UPDATE AN EXISTING CUSTOMER)**
To update an existing customer you need to get an idNumber returned from CREATE/READ operation and replace in {idNumber here without{} }

```
curl --location --request POST 'http://customermicroservice-customermicroservice.apps.ca-central-1.starter.openshift-online.com/api/v1/customer/' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YmFzaWNfdXNlcl9hdXRoOmJhc2ljX3Bhc3NfYXV0aA==' \
--data-raw '   {
            "idNumber": "{idNumber here without {} }",
            "name": "Bruno",
            "surname": "Guidi",
            "email": "bruno.guidi@hotmail.com",
            "initials": "GG",
            "mobile": "14988038314"
        }'
```

**For read (GET) operation (READ ALL CUSTOMERS WITH PAGINATION)**

You can chose which page you want to list (page parameter), the number of items per page (size parameter) and sort by one field of the customer (sort parameter)

```
curl --location --request GET 'http://customermicroservice-customermicroservice.apps.ca-central-1.starter.openshift-online.com/api/v1/customer?page=0&size=2&sort=name' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YmFzaWNfdXNlcl9hdXRoOmJhc2ljX3Bhc3NfYXV0aA=='
```

**For read (GET) operation (READ A SPECIFIC CUSTOMER PASSING IDNUMBER BY PARAMETER)**

Get an idNumber returned from CREATE/READ operation and replace in {idNumber here without{} }

```
curl --location --request GET 'http://customermicroservice-customermicroservice.apps.ca-central-1.starter.openshift-online.com/api/v1/customer/{idNumber here without{} }' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YmFzaWNfdXNlcl9hdXRoOmJhc2ljX3Bhc3NfYXV0aA=='
```

**DELETE A CUSTOMER BASED IN IDNUMBER PASSED BY PARAMETER**

Get an idNumber returned from CREATE/READ operation and replace in {idNumber here without{} }

```
curl --location --request DELETE 'http://customermicroservice-customermicroservice.apps.ca-central-1.starter.openshift-online.com/api/v1/customer/5f330d999c4d87000143dd4f' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YmFzaWNfdXNlcl9hdXRoOmJhc2ljX3Bhc3NfYXV0aA=='
```

## Running the tests
The tests in project are running automatically integrated in build (CI Pipeline by GitHub actions) but you can run the tests manually using JUnit, the project have the following test classes:
- CustomerRepositoryTest.java
- CustomerServiceV1ImplTest.java

## Technologies
In this section will explain all technologies included in the project.

### Continuous Integration/Delivery (CI/CD)
In this project CI/CD was performed using GitHub Actions, the pipeline called Microservice Pipeline contains the following steps

- Analyze with SonarCloud
    - this step is responsible to trigger the analysis in [SonarCloud](https://sonarcloud.io/dashboard?id=alexguidi_customermicroservice), SonarCloud will be responsible to check the quality of the project.

- Build and push docker images
    - this step is responsible to build (with Maven) the Spring Boot project, run the tests and push the docker image to [docker-hub](https://hub.docker.com/repository/docker/alexguidi/customermicroservice).

- OpenShift Action
    - this step is responsible to deploy and restart the application in Red Hat Open Shift platform.

All steps above run when the master branch receive a new push, if any of these steps fail the next step will not be executed. Also the badges in the beggining of this README will reflect the current status of Microservice Pipeline.

### Swagger - OpenAPI Specification - Version 3 
The specification of the API was developed using swagger, you can access it by your local or in Red Hat OpenShift

Local:
```
http://localhost:9000/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
```

Red Hat Open Shift:

```
http://customermicroservice-customermicroservice.apps.ca-central-1.starter.openshift-online.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
```

### MongoDB

In order to be able to connect you need first execute the command below in the machine you have oc and the container with mongodb:

oc port-forward service/mongodb 27018:27017

You can access the mongoDB database using mongo compass or any tool you prefer using the following connection string

```
mongodb://customermicroservice:customermicroservice@localhost:27018/?authSource=customermicroservicedb

```

### Red Hat UBI and Open Shift Red Hat Online
This project is using these images from Red Hat

```
registry.access.redhat.com/ubi8/ubi-minimal:8.0
```

```
registry.access.redhat.com/ubi8/openjdk-8:latest
```

Also the project is being deployed automatically in Open Shift Red Hat online, you can reach the application using the following URL (Authentication is required: basic_user_auth/basic_pass_auth):

```
customermicroservice-customermicroservice.apps.ca-central-1.starter.openshift-online.com
```

### OpenShift
OpenShift is a family of containerization software developed by Red Hat. It's built around docker containers orchestrated by Kubernetes. This project is using openshift enabling it by the following file in root folder

```
kubernetes.yaml
```

Openshift will enable to escalate the application, it's configured to start with one replic but we can create more according the needs (in run-time)

To create/publish the image on OpenShift you need to follow these steps:

- Download oc and put it inside of a folder regodnized by PATH (Or add the folder in PATH)
- In your OpenShift account click on your name and click on "copy login command"
- Execute the command on your Linux Environment
- Create an file .yaml (example kubernets.yaml in the root folder of this project)
- In the folder where you create the .yaml execute the follow command ```oc apply -f kubernetes.yaml``` (this will create the pod customermicroservice)
- route.route.openshift.io/customermicroservice
- In your linux machine execute the follow command ```oc expose deployment customermicroservice``` (it'll create a service)
- In your linux machine execute the follow command ```oc expose service customermicroservice``` (it'll create the route - ingress)


### JaCoCo
This project is using JaCoCo to check the test coverage in the project, JaCoCo provide a complete report saying the percentage coverages and also show where tests are missing.

## Documentation
The documentation of the project was performed using Javadocs comments and generating the documents from these special comments.
You can check the documentation in folder javadoc clicking in index.html file.

## Software Development Process
During the development all requeriments were splitted in tasks and Kanban was used to drive the development.

You can check the Kanban board in GitHub using the following URL

[GitHub Projects - KabanBoard](https://github.com/alexguidi/customermicroservice/projects/1)

## Built With

* [Java 8](https://www.oracle.com/it/java/technologies/javase/javase-jdk8-downloads.html) - Development Language
* [Maven](https://maven.apache.org/) - Build and dependency Management
* [Spring Boot](https://spring.io/projects/spring-boot) - Used to create the REST API
* [SpringSecurity](https://spring.io/projects/spring-security) - Used to garantee the security of the endpoints
* [SonarCloud](https://sonarcloud.io/) - Used to check the quality of the project
* [Jacoco](https://www.eclemma.org/jacoco/) - Used to check test coverage
* [MongoDB](https://www.mongodb.com/) - Database document-based
* [Docker](https://www.docker.com/) - Used to create containers/images and store in docker-hub
* [Red Hat UBI](https://catalog.redhat.com/software/containers/search) - Used to get RedHat image base

## Author

* **Alex Guidi** - [LinkedIn](https://www.linkedin.com/in/alex-guidi) - [GitHub](https://github.com/alexguidi?tab=repositories) - [DockerHub](https://hub.docker.com/u/alexguidi)