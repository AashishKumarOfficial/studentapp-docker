FROM openjdk:22-jdk
ADD target/studentapp-docker.jar studentapp-docker.jar
ENTRYPOINT ["java","-jar","/studentapp-docker.jar"]