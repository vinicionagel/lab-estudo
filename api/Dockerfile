FROM adoptopenjdk:11-jre
ARG JAR_FILE=target/api-*.jar
COPY ${JAR_FILE} api.jar
ENTRYPOINT ["java","-jar","/api.jar"]