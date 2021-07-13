FROM adoptopenjdk:11-jre

ENV API_HOME /usr/local/api
ENV PATH $API_HOME:$PATH
WORKDIR $API_HOME

ADD /home/runner/.m2/repository/br/com/labestudo/api/0.0.1-SNAPSHOT/api*.jar ./api.jar

EXPOSE 8080
CMD ["java", "-jar", "-Dspring.profiles.active=prod","api.jar"]
