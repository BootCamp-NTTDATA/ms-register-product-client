FROM openjdk:11
VOLUME /tmp.
EXPOSE  8082
ADD     ./target/ms-register-product-client-0.0.1-SNAPSHOT.jar "ms-register-product-client.jar"
ENTRYPOINT ["java","-jar","ms-register-product-client.jar"]