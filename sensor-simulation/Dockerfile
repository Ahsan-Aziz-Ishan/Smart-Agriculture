# Move the build stage first
FROM maven:3.9.9-eclipse-temurin-23-alpine as builder

WORKDIR /app

# Copy necessary files for build
COPY pom.xml .
COPY src src/

# Build the project
RUN mvn clean package -DskipTests

# Runtime stage
FROM amazoncorretto:23-alpine

WORKDIR /app

# Copy only the built JAR from builder stage
COPY --from=builder /app/target/sensor-simulation-0.0.1-SNAPSHOT.jar app.jar

# Set environment variable for Java options
ENV JAVA_TOOL_OPTIONS="-Djava.security.egd=file:/dev/./urandom"

# Start the application
CMD ["java", "-jar", "app.jar"]