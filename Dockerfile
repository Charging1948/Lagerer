# Use the official OpenJDK image as a parent image
FROM openjdk:17-jdk-alpine3.14

# Set the working directory
WORKDIR /app

# Copy all project files into the working directory
COPY . /app

# Compile the application
RUN mkdir -p bin && \
    javac --release 17 -d ./bin $(find ./src -name "*.java") && \
    cd bin && \
    jar cfm app.jar ../manifest.txt .

# Default command to run the application
CMD ["java", "-jar", "/app/bin/app.jar"]
