FROM openjdk:15-oracle

MAINTAINER Sven Reymann <reymann78@freenet.de>

ADD backend/target/all-your-favorites.jar all-your-favorites.jar

CMD [ "sh", "-c", "java -jar -Dserver.port=$PORT -Djwt.secretkey=$JWT_SECRETKEY -Dspring.data.mongodb.uri=$MONGO_DB_URI all-your-favorites.jar"]
