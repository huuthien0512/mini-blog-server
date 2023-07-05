#
# Build stage
#
FROM maven:3.9.1 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:20-jdk-slim
COPY --from=build /target/mini-blog-server-0.0.1-SNAPSHOT.jar mini-blog-server.jar
# ENV PORT=8081
EXPOSE 8081
ENTRYPOINT ["java","-jar","mini-blog-server.jar"]