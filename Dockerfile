FROM openjdk:17
WORKDIR /app
ADD target/International_Phone_Numbers-0.0.1-SNAPSHOT.jar International_Phone_Numbers-0.0.1-SNAPSHOT.jar
ADD sample.db sample.db
EXPOSE 8080
LABEL org.opencontainers.image.source="https://github.com/maieldash/international_phone_numbers"
ENTRYPOINT ["java","-jar","/app/International_Phone_Numbers-0.0.1-SNAPSHOT.jar"]