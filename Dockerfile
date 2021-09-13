FROM openjdk:11
ADD target/Signing-0.0.1-SNAPSHOT.jar Signing-0.0.1-SNAPSHOT.jar
EXPOSE 3030
ENTRYPOINT ["java","-jar","Signing-0.0.1-SNAPSHOT.jar"]