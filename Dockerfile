FROM openjdk:8-jre-alpine
MAINTAINER Jeeva Kandasamy(jkandasa)

RUN adduser -D -u 7000 jaeger
USER jaeger
WORKDIR /app
COPY ./target/jaeger-test-server-1.0.0-SNAPSHOT-single.jar .
EXPOSE 7000
CMD ["java","-jar", "jaeger-test-server-1.0.0-SNAPSHOT-single.jar"]
