FROM openjdk:15-oracle

MAINTAINER Sven Reymann <reymann78@freenet.de>

ADD backend/target/all-your-favorites.jar all-your-favorites.jar

CMD [ "sh", "-c", "java -jar -Dserver.port=$PORT all-your-favorites.jar"]
