FROM openjdk:17-alpine
VOLUME /tmp
EXPOSE 3001
COPY target/multiple-jdbc-client-1.0.0.jar multiple-jdbc-client-1.0.0.jar
ENTRYPOINT ["java","-jar","multiple-jdbc-client.jar"]
