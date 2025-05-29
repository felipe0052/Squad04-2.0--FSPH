# Etapa de build
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copia todos os arquivos do contexto atual para o contêiner
COPY . .

# Compila o projeto sem executar os testes
RUN mvn clean package -DskipTests

# Etapa de execução
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copia o JAR gerado da etapa de build
COPY --from=build /app/target/*.jar app.jar

# Comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]
