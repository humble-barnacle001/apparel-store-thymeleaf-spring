#!/bin/bash

[ -z $MONGO_URI ]  && (echo "Set \$MONGO_URI env variable with the MongoDB server URI" && exit)

./mvnw spring-boot:run