FROM openjdk:17-jdk-slim

RUN apt-get update && apt-get install findutils -y

EXPOSE 8080:8080

RUN mkdir /app

COPY ./build/install/kotlin-template/ /app/

WORKDIR /app/bin

CMD ["./kotlin-template"]