#/bin/bash

java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=8000 -jar ./target/junit5-1.0.0-SNAPSHOT.jar

