FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD ./eureka-server-1.0.0-SNAPSHOT.jar app.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=docker"]
EXPOSE 8760