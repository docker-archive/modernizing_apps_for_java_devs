FROM maven:latest AS messageservice
WORKDIR /usr/src/messageservice
COPY pom.xml .
RUN mvn -B -f pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve
COPY . .
RUN mvn -B -s /usr/share/maven/ref/settings-docker.xml package -DskipTests

FROM java:openjdk-8-jdk-alpine
WORKDIR /app
COPY --from=messageservice /usr/src/messageservice/target/messageservice-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "/app/messageservice-0.0.1-SNAPSHOT.jar"]
#CMD ["--spring.profiles.active=postgres"]