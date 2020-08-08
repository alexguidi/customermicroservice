# Target image ubi8/openjdk-8:latest for build stage
FROM registry.access.redhat.com/ubi8/openjdk-8:latest AS build-stage

# Define the working directory
WORKDIR /tmp

# Copy sources
COPY . .

# Build, test and pack project
RUN ./mvnw package -Dmaven.test.skip=true

# Target image ubi8/ubi-minimal:8.0
FROM registry.access.redhat.com/ubi8/ubi-minimal:8.0

# Install java dependencies
RUN microdnf install --nodocs java-1.8.0-openjdk-headless

# Clear package manager metadata
RUN microdnf clean all && [ ! -d /var/cache/yum ] || rm -rf /var/cache/yum

# Define the user
USER 185

# Define the working directory
WORKDIR /app

# Copy artifact from build stage
COPY --from=build-stage /tmp/target/customermicroservice-0.0.1-SNAPSHOT.jar customermicroservice.jar

# Define run cmd
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar customermicroservice.jar ${0} ${@}"]