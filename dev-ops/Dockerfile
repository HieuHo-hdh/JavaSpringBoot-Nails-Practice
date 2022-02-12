FROM openjdk:11
VOLUME /tmp
#WORKDIR /opt
COPY ./{JAR_FILE} app.jar
COPY ./config.properties config.properties

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

#ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-DskipTests","-jar","/app.jar"]
#ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-P{PROFILES_ACTIVE}","-jar","/app.jar"]
#ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active={PROFILES_ACTIVE}"]
#ENTRYPOINT ["java", "-Xmx256m", "-Xss32m","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]