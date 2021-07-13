#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/api/src
COPY pom.xml /home/api
RUN mvn -f /home/api/pom.xml clean package

#
# Package stage
#
FROM adoptopenjdk:11-jre
COPY --from=build /home/api/target/*.jar /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
