# Use an official Java runtime as a base image
FROM eclipse-temurin:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/adminservice-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8765

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
