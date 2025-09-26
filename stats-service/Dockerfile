FROM eclipse-temurin:21-jdk-jammy AS build

WORKDIR /app

COPY pom.xml .
COPY ../pom.xml ../pom.xml
COPY ../common-dto ../common-dto
COPY src src

RUN apt-get update && apt-get install -y maven

RUN mvn dependency:go-offline -B
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENV JAVA_OPTS="-XX:+UseZGC \
               -XX:+UnlockExperimentalVMOptions \
               -Xms256m \
               -Xmx512m \
               --enable-preview"

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
