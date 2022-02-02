FROM openjdk:11
ARG BUILDED_JAR=target/*.jar
COPY ${BUILDED_JAR} lemon-fuckoff.jar
EXPOSE 8080
ENV PROFILE=development
ENTRYPOINT ["java","-jar","/lemon-fuckoff.jar"]