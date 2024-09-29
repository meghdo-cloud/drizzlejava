# Use Eclipse Temurin as the base image for JDK 21
FROM eclipse-temurin:21-jdk
# Set the working directory
WORKDIR /app
# Copy the Maven build artifact to the container
COPY /target/*.jar /app/app.jar
# Expose port 8080
EXPOSE 8080
# Define the command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
