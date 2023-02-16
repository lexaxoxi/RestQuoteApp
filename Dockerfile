FROM openjdk:17-alpine
LABEL maintrainer="Alexey"
ARG JAR_FILE=out/demo_jar/demo.jar

COPY ${JAR_FILE} application.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","application.jar"]
