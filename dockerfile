FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY ./target/CRMforDelivery-0.1.jar CRMforDelivery-0.1.jar
ENTRYPOINT ["java","-jar","/CRMforDelivery-0.1.jar"]