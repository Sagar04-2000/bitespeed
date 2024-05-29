

##Define base docker image
FROM openjdk:17
LABEL maintainer="sagar-bitespeed"

##ADD jar file
ADD target/bitespeed-0.0.1-SNAPSHOT.jar bitespeed-docker.jar

#COPY target/bitespeed-0.0.1-SNAPSHOT.jar bitespeed-docker.jar

##Adding entrypoint
ENTRYPOINT ["java","-jar","/bitespeed-docker.jar"]
