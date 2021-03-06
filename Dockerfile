FROM openjdk:11-jdk

EXPOSE 8080:8080

RUN mkdir /app

COPY ./build/install/kotlin-template/ /app/

WORKDIR /app/bin

CMD ["./kotlin-template"]