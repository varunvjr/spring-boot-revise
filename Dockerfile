FROM openjdk:17
WORKDIR /app
COPY ./target/backend-app.jar /app
EXPOSE 8080
CMD ["java","-jar","backend-app.jar"]