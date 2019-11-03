FROM openjdk:8-jdk-alpine
RUN mkdir /usr/app
COPY ./target/loan-scheduler-1.0-SNAPSHOT.jar /usr/app
WORKDIR /usr/app
EXPOSE 8081
ENTRYPOINT ["java","-jar","loan-scheduler-1.0-SNAPSHOT.jar"]
