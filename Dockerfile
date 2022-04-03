FROM openjdk:17
ADD target/International_Phone_Numbers-0.0.1-SNAPSHOT.jar International_Phone_Numbers-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","International_Phone_Numbers-0.0.1-SNAPSHOT.jar"]