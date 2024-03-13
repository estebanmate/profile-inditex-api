FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} profile-inditex-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/profile-inditex-api.jar"]
