FROM openjdk:8-jre-alpine
MAINTAINER Jeeva Kandasamy(jkandasa)

RUN adduser -D -u 7000 jaeger
USER jaeger
WORKDIR /jts
COPY ./target/jts .
EXPOSE 7000
CMD ["java", "-Dlogback.configurationFile=conf/logback-docker.xml", "-DuseDefaultListeners=false", "-jar", "lib/jaeger-test-server-1.0.2-single.jar"]
