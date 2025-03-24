# Use OpenJDK 18 as base image
FROM openjdk:18

# Set the working directory inside the container
WORKDIR /app

# Copy the project files to the container
COPY target/abedkiloo-b2c-1.0-SNAPSHOT.jar app.jar

# Expose the application's port (Change if necessary)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
