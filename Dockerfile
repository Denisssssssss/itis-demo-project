FROM openjdk:17-slim
COPY build/libs/minimyini.jar app.jar
CMD ["java", "-jar", "app.jar"]
EXPOSE 8080