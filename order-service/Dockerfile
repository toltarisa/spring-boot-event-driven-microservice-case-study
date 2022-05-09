FROM adoptopenjdk/openjdk11:latest
COPY target/*.jar order-service.jar
ENTRYPOINT ["java","-jar","order-service.jar"]