FROM amazoncorretto:18.0.1

ADD target/test_task-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]