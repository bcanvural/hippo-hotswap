#!/usr/bin/env bash
mvn clean verify -T2C -DskipTests=true
mvn -Pdist
mvn -Pdocker.build
docker run -p 8080:8080 -p 8000:8000 \
--mount type=bind,source="$(pwd)"/cms/target/classes,target=/usr/local/tomcat/cms-classes \
--mount type=bind,source="$(pwd)"/site/target/classes,target=/usr/local/tomcat/site-classes \
--mount type=bind,source="$(pwd)"/site/target/site.war,target=/usr/local/tomcat/webapps/site.war \
--mount type=bind,source="$(pwd)",target=/usr/local/tomcat/hippoproject \
bcanvural/hippo-hotswap:v1