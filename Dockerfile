FROM openjdk:21
WORKDIR /usr/src/app
COPY ./build/libs/GoingGoing-0.0.1-SNAPSHOT.jar /build/libs/GoingGoing-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","/build/libs/GoingGoing-0.0.1-SNAPSHOT.jar"]
