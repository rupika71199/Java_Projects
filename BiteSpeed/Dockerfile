FROM openjdk:17
ENV PORT 8080
EXPOSE 8080
COPY target/bitespeed.jar bitespeed.jar
ENTRYPOINT ["java","-jar","/bitespeed.jar"]