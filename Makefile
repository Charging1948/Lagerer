# Name of Docker image
IMAGE_NAME=my_java_app

# Docker container
CONTAINER_NAME=my_java_container

# Output directory
OUTPUT_DIR=$(PWD)

# 'make' command will execute 'build' and 'run' targets
all: build run

# Build the Docker image
build:
	docker build -t $(IMAGE_NAME):latest .

# Run the Docker container and copy the compiled JAR file to the current directory
run:
	-docker rm -f $(CONTAINER_NAME)
	docker run --name $(CONTAINER_NAME) -d $(IMAGE_NAME):latest
	docker cp $(CONTAINER_NAME):/app/bin/app.jar $(OUTPUT_DIR)/app.jar
	docker rm -f $(CONTAINER_NAME)

# Delete all Docker containers and images
clean:
	-docker rmi -f $(IMAGE_NAME):latest
	-docker system prune -f

# 'make help' command
help:
	@echo "-----------------HELP-----------------"
	@echo "To build the Docker image, use 'make build'"
	@echo "To run the Docker container, use 'make run'"
	@echo "To clean up Docker, use 'make clean'"
	@echo "--------------------------------------"
