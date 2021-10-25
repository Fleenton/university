FROM openjdk:11
ARG JAR_FILE=target/university-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} university.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/university.jar"]