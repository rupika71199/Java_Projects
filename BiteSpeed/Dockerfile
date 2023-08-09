FROM maven:3.6.3-jdk-8 AS build
COPY . .
RUN mvn clean package 

FROM openjdk:8-jre-slim
COPY --from=build /target/bitespeed-identity-reconcillation.jar bitespeed-identity.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","bitespeed-identity.jar"]
