# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

###############################################################################
# Base Image
###############################################################################

FROM tomcat:8.5-jre8

# Tomcat Base Directory, inherited from the base image by default.
ENV CATALINA_BASE "/usr/local/tomcat"

# Customize catalina.properties to add additional common and shared loader jar paths.
RUN sed -i s:common.loader=\.\*:common.loader=\${catalina.base}/lib,\${catalina.base}/lib/\*\.jar,\${catalina.base}/common/lib/\*\.jar,\${catalina.home}/lib,\${catalina.home}/lib/\*\.jar:g ${CATALINA_BASE}/conf/catalina.properties
RUN sed -i s:shared.loader=\.\*:shared.loader=\${catalina.base}/shared/classes,\${catalina.base}/shared/lib/\*\.jar:g ${CATALINA_BASE}/conf/catalina.properties

###############################################################################
# Environment Variable Configurations
###############################################################################

# JVM Heap Size
ENV MIN_HEAP_SIZE "1024m"
ENV MAX_HEAP_SIZE "2048m"

# Repository Configuration File
ARG REPOSITORY_XML
COPY ${REPOSITORY_XML} ${CATALINA_BASE}/conf/repository.xml
ENV REPO_CONFIG "file:${CATALINA_BASE}/conf/repository.xml"

# Repository Directory
ENV REPO_PATH "${CATALINA_BASE}/repository"

# Bootstrapping enabled?
ENV REPO_BOOTSTRAP "true"

# Repository Cluster Node ID
ENV CLUSTER_ID "$(whoami)-$(hostname -f)"

###############################################################################
# Remove existing artifacts and install new ones by extracting the tar ball
###############################################################################

RUN rm -rf ${CATALINA_BASE}/common/lib/*.jar
RUN rm -rf ${CATALINA_BASE}/shared/lib/*.jar
RUN rm -rf ${CATALINA_BASE}/webapps/*

ARG SETENV_SH
COPY ${SETENV_SH} ${CATALINA_BASE}/bin/

## Java debug config
ENV JAVA_ENABLE_DEBUG "true"
ENV JAVA_DEBUG_PORT 8000

## Auto-export config
RUN mkdir -p ${CATALINA_BASE}/hippoproject
ENV PROJECT_BASEDIR ${CATALINA_BASE}/hippoproject
ENV AUTO_EXPORT_ENABLED "true"

## Hotswap config
RUN apt-get update && apt-get -y install openjdk-8-jre-dcevm
# Download hotswap agent jar
RUN mkdir -p /opt/hotswap-agent
RUN curl -L "https://github.com/HotswapProjects/HotswapAgent/releases/download/RELEASE-1.3.0/hotswap-agent-1.3.0.jar" \
 -o /opt/hotswap-agent/hotswap-agent-1.3.0.jar > /dev/null
ENV HOTSWAP_JAR_LOCATION /opt/hotswap-agent/hotswap-agent-1.3.0.jar

ARG TAR_BALL
ADD ${TAR_BALL} ${CATALINA_BASE}/