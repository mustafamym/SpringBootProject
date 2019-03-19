#!/bin/sh

echo "********************************************************"
echo "Waiting for the eureka server to start on port $DISCOVERY_SERVICE_PORT"
echo "********************************************************"
while ! `nc -z discovery-service $DISCOVERY_SERVICE_PORT`; do sleep 3; done
echo "******* Eureka Server has started"


echo "********************************************************"
echo "Starting Server with Configuration Service via Eureka :  $DISCOVERY_SERVICE_URI:$SERVER_PORT"
echo "USing Profile: $PROFILE"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT   \
     -Deureka.client.serviceUrl.defaultZone=$DISCOVERY_SERVICE_URI             \
     -Dspring.profiles.active=$PROFILE  \
     -DSPRING_PROFILES_ACTIVE=$PROFILE        \
     -jar /usr/local/target/@project.build.finalName@.jar