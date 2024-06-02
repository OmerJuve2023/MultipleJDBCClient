# Multiple JDBC Client Application

This application demonstrates the use of multiple JDBC clients in a Spring Boot application. It includes two
services: `PostService` and `SubscriberService`.

## Project Overview

The project is built with Java and uses the Spring Boot framework for creating standalone, production-grade Spring based
applications. It uses SQL for database interactions and Maven as a build tool.

## Design database schema

The project uses two tables: `post` and `subscriber`. The `post` table stores information about posts, including the ID,
title, and content. The `subscriber` table stores information about subscribers, including the ID, name, and email.

![img.png](img.png)
![img_1.png](img_1.png)

## Dependencies

The project uses several dependencies, including:

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

## Docker Configuration

The project includes a `Dockerfile` and a `docker-compose.yml` file for containerization and orchestration.

The `Dockerfile` specifies how to build a Docker image for the application. It starts from a base image with Java and
Maven, copies the source code into the image, builds the application using Maven, and specifies the command to run the
application.

```dockerfile
FROM openjdk:17-alpine
VOLUME /tmp
EXPOSE 3001
COPY target/MultipleJDBCClient-1.0.0.jar MultipleJDBCClient.jar
ENTRYPOINT ["java","-jar","MultipleJDBCClient.jar"]
```

The `docker-compose.yml` file defines services, networks, and volumes. It specifies how to run the application and its
dependencies in separate containers.

```yaml
version: "3.9"
services:
  web:
    container_name: MultipleJDBCClient
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8000:8000"
```

## Project Initializer

To build the application, use the following command:

1. Build the project

```bash
mvn clean install
```

2. Execute to container

```shell
docker build -t multiple-jdbc-client . 
```

3. Run the container

```shell
docker run -p 8080:8080 multiple-jdbc-client
```

# Build with GraalVM

1. Build the native image

```bash
mvn -Pnative spring-boot:build-image
```

2. Run the native image

```bash
docker run -p 8080:8080 multiple-jdbc-client
```


## Testing

The project includes unit tests for the `PostService` and `SubscriberService` classes. The tests use JUnit and Mockito
for
testing and mocking.

To run the tests, use the following command:

```bash
mvn test
```
