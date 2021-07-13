FROM adoptopenjdk:11-jre

ENV API_HOME /usr/local/api
ENV PATH $API_HOME:$PATH
WORKDIR $API_HOME

ADD /home/runner/work/lab-estudo/lab-estudo/api/target/api*.jar ./api.jar

EXPOSE 8080
CMD ["java", "-jar", "-Dspring.profiles.active=prod","api.jar"]
