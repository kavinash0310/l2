# Library Management App

A Java 8 and Spring Boot learning project for a library workflow. It combines a JSP interface with Spring MVC, Spring Data JPA, and MySQL.

## Run locally

Requirements: Java 8 or newer and MySQL.

1. Create the local database expected by `librarymanagementapp/src/main/resources/application.properties`.
2. Replace the sample database credentials with your own local values.
3. Start the app:

```bash
cd librarymanagementapp
./mvnw spring-boot:run
```

On Windows, use `mvnw.cmd spring-boot:run`. The app is configured for port `8010`.

This is a local learning demo. Update the committed database settings before connecting to any database you care about.
