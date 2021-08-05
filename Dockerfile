FROM amazoncorretto:11
EXPOSE 8080
ADD target/spring-boot-jwt-0.0.1-SNAPSHOT.jar authService.jar 
ENTRYPOINT ["java","-jar","/authService.jar"]