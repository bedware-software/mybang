FROM amazoncorretto:17
LABEL authors="bedware"

COPY target/my-bang-1.0-SNAPSHOT.jar src/main/resources/config.yaml /

ENTRYPOINT ["java", "-jar", "/my-bang-1.0-SNAPSHOT.jar"]