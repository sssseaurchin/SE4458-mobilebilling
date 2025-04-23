FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN ./mvnw clean install -DskipTests

CMD ["java", "-jar", "target/mobilebilling-0.0.1-SNAPSHOT.jar"]
