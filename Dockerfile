# Use Eclipse Temurin as the base image for JDK 21
FROM eclipse-temurin:21-jdk

USER root

# Set the working directory
WORKDIR /app

# Copy the Maven build artifact to the container
COPY /target/drizzle-1.0.0.jar /app/drizzle.jar

# Expose port 8080
EXPOSE 8080

USER 1000

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "/app/drizzle.jar", "--server.servlet.context-path=/drizzle"]
