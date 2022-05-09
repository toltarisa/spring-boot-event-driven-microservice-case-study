FROM adoptopenjdk/openjdk11:latest
COPY target/*.jar email-service.jar
ENTRYPOINT ["java","-jar","email-service.jar"]