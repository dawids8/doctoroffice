FROM openjdk:8
COPY ./target/doctorsoffice-0.0.1-SNAPSHOT.jar doctorsoffice-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","doctorsoffice-0.0.1-SNAPSHOT.jar"]