# Stage 1: Build the application using Maven with JDK 21
#------------------------------------------------------
# Use an official Maven image with Eclipse Temurin JDK 21
FROM maven:3.9-eclipse-temurin-21 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml first to leverage Docker layer caching for dependencies
COPY pom.xml .

# Optional: Download dependencies to improve layer caching if only source changes
# RUN mvn dependency:go-offline

# Copy the source code into the container
COPY src ./src

# Package the application using Maven, skipping tests.
# Ensure your pom.xml is also configured to compile for Java 21.
RUN mvn package -DskipTests

# Stage 2: Create the final runtime image using JRE 21
#-----------------------------------------------------
# Use a minimal Eclipse Temurin JRE 21 base image (Alpine based)
FROM eclipse-temurin:21-jre-alpine

# Set the working directory
WORKDIR /app

# Copy *only* the built JAR file from the builder stage
# Rename it to app.jar for consistency in the ENTRYPOINT.
# Adjust '*.jar' if needed for your specific artifact ID or if multiple JARs exist.
COPY --from=builder /app/target/*.jar app.jar

# Expose the port your application listens on (if any)
# Replace 8080 with your application's actual port if it's a web service
# EXPOSE 8080

# Define the command to run your application using Java 21 JRE
ENTRYPOINT ["java", "-jar", "app.jar"]
