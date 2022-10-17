FROM openjdk:18-alpine3.15
COPY target/firstProject-0.0.1-SNAPSHOT.jar /demo.jar
CMD ["java", "-jar", "/demo.jar"]