 FROM openjdk:17-jdk-alpine as builder

 WORKDIR /app/mscv-usuarios

 COPY ./pom.xml /app
 COPY ./mscv-usuarios/.mvn ./.mvn
 COPY ./mscv-usuarios/mvnw .
 COPY ./mscv-usuarios/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./mscv-usuarios/src ./src

 RUN ./mvnw clean package -DskipTests

 FROM openjdk:17-jdk-alpine

 WORKDIR /app
 RUN mkdir ./logs
 COPY --from=builder /app/mscv-usuarios/target/mscv-usuarios-0.0.1-SNAPSHOT.jar .

 EXPOSE 8001

 CMD ["java", "-jar", "mscv-usuarios-0.0.1-SNAPSHOT.jar"]


