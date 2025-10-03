# ===========================
# Etapa de build
# ===========================
FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /app

# Copia o pom.xml e baixa dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código fonte e compila
COPY src ./src
RUN mvn clean package -DskipTests -Dquarkus.package.jar.type=uber-jar

# ===========================
# Etapa de execução
# ===========================
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia qualquer JAR gerado pelo build
COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 3000
EXPOSE 5005

ENTRYPOINT ["java", "-jar", "app.jar"]