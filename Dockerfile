FROM openjdk:17-alpine
VOLUME /tmp
EXPOSE 3001
COPY target/MultipleJDBCClient-1.0.0.jar MultipleJDBCClient.jar
ENTRYPOINT ["java","-jar","MultipleJDBCClient.jar"]
