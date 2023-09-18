# Base image
FROM azul/zulu-openjdk:17

# Set working directory
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/*.jar app.jar

# Expose the port on which your application listens
EXPOSE 9090

# Set the entrypoint command to run your application
ENTRYPOINT ["java", "-jar", "app.jar"]
