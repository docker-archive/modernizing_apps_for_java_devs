FROM java:openjdk-8-jdk-alpine

RUN MAVEN_VERSION=3.3.3 \
 && cd /usr/share \
 && wget http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz -O - | tar xzf - \
 && mv /usr/share/apache-maven-$MAVEN_VERSION /usr/share/maven \
 && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

WORKDIR /app
RUN mkdir ./logs

ADD pom.xml /app/pom.xml
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]

# Adding source, compile and package into a fat jar
ADD src /app/src
RUN ["mvn", "package"]

CMD ["java", "-Djava.util.logging.config.file=./src/logging.properties", "-jar", "target/worker-jar-with-dependencies.jar"]
