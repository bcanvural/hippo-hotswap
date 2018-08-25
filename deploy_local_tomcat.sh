#!/usr/bin/env bash
mvn clean verify -T2C -DskipTests=true
mvn -Pdist
mvn -Pdocker.build
docker run -p 8080:8080 -p 5005:5005 \
--mount type=bind,source="$(pwd)"/cms/target/classes,target=/usr/local/tomcat/cms-classes \
--mount type=bind,source="$(pwd)"/cms/target/classes,target=/usr/local/tomcat/site-classes \
--mount type=bind,source="$(pwd)"/repository-data,target=/usr/local/tomcat/hippoproject/repository-data \
bcanvural/hippo-hotswap:v1