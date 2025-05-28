FROM ubuntu:latest AS build

# Define o diretório de trabalho
WORKDIR /app

# Atualiza pacotes e instala Java e Maven
RUN apt-get update && \
    apt-get install -y openjdk-21-jdk maven

# Copia os arquivos do projeto para dentro da imagem
COPY . .

# Compila o projeto com Maven
RUN mvn clean install -DskipTests

# Segunda imagem: runtime
FROM openjdk:21-jdk-slim

WORKDIR /app

EXPOSE 8080

# Copia o .jar gerado no build
COPY --from=build /app/target/gestao_vagas-0.0.1-SNAPSHOT.jar app.jar

# Executa o .jar
ENTRYPOINT ["java", "-jar", "app.jar"]
