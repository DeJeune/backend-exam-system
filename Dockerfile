FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD ./target/exam-system-0.0.1-SNAPSHOT.jar exam-system.jar
RUN sh -c 'touch /exam-system.jar'
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /exam-system.jar"]