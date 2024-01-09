 FROM openjdk:17-jdk-alpine as builder

 WORKDIR /app/msvc-cursos

 COPY ./pom.xml /app
 COPY ./msvc_cursos/.mvn ./.mvn
 COPY ./msvc_cursos/mvnw .
 COPY ./msvc_cursos/pom.xml .

 RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

 COPY ./msvc_cursos/src ./src

 RUN ./mvnw clean package -DskipTests

 FROM openjdk:17-jdk-alpine

 WORKDIR /app
 RUN mkdir ./logs
 COPY --from=builder /app/msvc-cursos/target/msvc_cursos-0.0.1-SNAPSHOT.jar .

 EXPOSE 8002

 CMD ["java", "-jar", "msvc_cursos-0.0.1-SNAPSHOT.jar"]


