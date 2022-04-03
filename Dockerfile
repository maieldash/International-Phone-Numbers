FROM openjdk:17
ADD target/International_Phone_Numbers-0.0.1-SNAPSHOT.jar International_Phone_Numbers-0.0.1-SNAPSHOT.jar
EXPOSE 8080
LABEL org.opencontainers.image.source="https://github.com/maieldash/international_phone_numbers"
ENTRYPOINT ["java","-jar","International_Phone_Numbers-0.0.1-SNAPSHOT.jar"]
