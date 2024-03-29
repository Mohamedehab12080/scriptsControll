FROM maven:3.8.5-openjdk-17 As build
COPY . .
RUN mvn clean package -DskilTests

FROM openjdk:17.0.1-jdk-slim

COPY --from=build /target/scriptsController-0.0.1-SNAPSHOT.jar scriptsController.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","scriptsController.jar"]