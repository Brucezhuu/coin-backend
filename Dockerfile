# Step 1: Use Maven with Java 17 as the base image for building the application
FROM maven:3.9.4-eclipse-temurin-17 as build

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the project files into the container
COPY . /app

# Step 4: Build the application using Maven
RUN mvn clean package

# Step 5: Use a lightweight OpenJDK 17 image to run the application
FROM openjdk:17-jdk-slim

# Step 6: Set the working directory for the runtime container
WORKDIR /app

# Step 7: Copy the compiled JAR file and configuration file
COPY --from=build /app/target/hola-dropwizard-1.0.jar /app/app.jar
COPY config.yml /app/config.yml

# Step 8: Expose the Dropwizard application port
EXPOSE 8080

# Step 9: Command to run the Dropwizard application
CMD ["java", "-jar", "app.jar", "server", "config.yml"]
