#FROM openjdk:12
FROM maven:3.6.3-jdk-8

# copy file to docker (.xpdl , .....)
copy ./src/main/resources/data ./src/main/resources/data

ADD target/sample-0.0.1.jar sample.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "sample.jar"]
# to build -> goto project folder -> docker build -t sample .
# to run -> docker run -d -p 8080:8080 sample


#FROM maven:3.6.1-jdk-8-alpine AS MAVEN_BUILD
# 
## copy the pom and src code to the container
#COPY ./ ./
# 
## package our application code
#RUN mvn clean package
# 
## the second stage of our build will use open jdk 8 on alpine 3.9
#FROM openjdk:8-jre-alpine3.9
# 
## copy only the artifacts we need from the first stage and discard the rest
#COPY --from=MAVEN_BUILD /sample/target/sample-0.0.1.jar /sample.jar
# 
## set the startup command to execute the jar
#CMD ["java", "-jar", "/sample.jar"]