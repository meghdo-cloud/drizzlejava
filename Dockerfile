# Use Eclipse Temurin as the base image for JDK 21
FROM eclipse-temurin:21-jdk

ARG  APP_PATH=/home/jenkins/agent/workspace

# Set the working directory
WORKDIR /app

# Copy the Maven build artifact to the container
COPY ${APP_PATH}/target/drizzle-1.0.0.jar /app/drizzle.jar

# Expose port 8080
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "/app/drizzle.jar", "--server.servlet.context-path=/drizzle"]
