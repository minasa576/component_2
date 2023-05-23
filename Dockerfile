FROM openjdk:latest
WORKDIR /app
COPY src /app
RUN javac Component2.java
VOLUME /app/data/batch
CMD ["java", "Component2"]