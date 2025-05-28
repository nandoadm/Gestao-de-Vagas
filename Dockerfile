FROM ubuntu:latest AS build

# Atualiza pacotes e instala Java e Maven
RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:21-jdk-slim
EXPOSE 8080

COPY --from=build /app/target/gestao_vagas-0.0.1-SNAPSHOT.jar app.jar

# Executa o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
