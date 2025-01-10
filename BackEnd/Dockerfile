# Usar una imagen de Java 17
FROM eclipse-temurin:17-jdk-alpine

# Crear un directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR generado por el build del backend
COPY target/backend-1.0.0.jar app.jar

# Exponer el puerto usado por el backend (ajústalo si es diferente)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
