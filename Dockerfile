FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/*.jar product-ms.jar
EXPOSE 9092
ENTRYPOINT ["java","-jar", "product-ms.jar"]