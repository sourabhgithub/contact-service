Contact-Service
===============

Contact core business service.

## Developing

To develop this application, you will need Spring-Boot Hibernate and Java 8. 

You can run this project locally using the [Spring Boot Maven Plugin][]. When running locally, the application will make use of a local database. Other developer-specific functionality is provided by the [Spring Boot Developer Tools][].

[Spring Boot Maven Plugin]: https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html
[Spring Boot Developer Tools]: https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-devtools.html

### Running the application

To run the application, execute the following Maven goal:

On Windows, make sure to point to the correct file based on the version number:

```
mvn clean package && java -jar target\contact-service-1.0.0-SNAPSHOT.war
```

On Mac or Linux:



```
mvn spring-boot:run
```

This will start the application on your local machine on port 8080 using an in-memory database..

### Application Contact-Service Endpoints
GET - http://localhost:8080/contacts
PUT - http://localhost:8080/contacts/{id}
POST - http://localhost:8080/contacts
DELETE - http://localhost:8080/contacts/{id}

#### Request Payloads
POST: ```{
    "name": "Sourabh",
    "company": "ABC Company",
    "profileImage": "new",
    "birthDate": "198310-01",
    "phoneNumber": {
        "workNumber": "1111111112",
        "personalNumber": "1111111112"
    },
    "email":"sourabhkumar.verma@gmail.com",
    "address": {
        "addressLine": "149 WOODLAKE BLVD",
        "city": "gurnee",
        "state": "il",
        "zipCode": "60031",
        "countryCode": "US"
    }
}```

PUT : ```{
    "name": "Sourabh",
    "company": "ABC Company",
    "profileImage": "new",
    "birthDate": "198310-01",
    "phoneNumber": {
        "workNumber": "1111111112",
        "personalNumber": "1111111112"
    },
    "email":"sourabhkumar.verma@gmail.com",
    "address": {
        "addressLine": "149 WOODLAKE BLVD",
        "city": "gurnee",
        "state": "il",
        "zipCode": "60031",
        "countryCode": "US"
    }
}```

     

### Managing the local database

The application uses an embedded, in-memory database provided by [H2](https://h2database.com/). The contents of this database are initialized when the application starts and do not persist between application restarts.

While the application is running, you can inspect its contents using the H2 console at http://localhost:8080/h2-console/. When you access this console, you will be presented with a settings screen. Press "Connect" to connect to the database; all the default settings should be correct.

