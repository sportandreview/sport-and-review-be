FROM eclipse-temurin:20-jdk-alpine as build

WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN --mount=type=cache,target=/root/.m2 ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:20-jdk-alpine
LABEL authors="Ugo Giordano"
MAINTAINER Ugo Giordano <ugo.amati@softwarecare.it>

VOLUME /tmp

ARG DEPENDENCY=/workspace/app/target/dependency
ARG ACTIVE_PROFILES="docker"
ARG PORT=8080

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENV ACTIVE_PROFILES=$ACTIVE_PROFILES

RUN addgroup -S sar && adduser -S sar -G sar
USER sar

EXPOSE ${PORT}

ENTRYPOINT java -cp app:app/lib/* \
           it.sportandreview.SportAndReviewApiApplication \
           --spring.profiles.active=$ACTIVE_PROFILES