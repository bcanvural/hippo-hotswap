# Use docker for local development
This project contains configuration for using a dockerized tomcat server instead 
 of OOTB cargo.run profile
 
 ## Build project, build docker image, run image
 ```bash
 $ ./deploy_local_tomcat.sh
 ```
 ## Change things in site module and redeploy site to running instance
 ```bash
 $ ./redeploy-site.sh
 ```
 
 ## Debugging
 
 Attach remote debugger at port 5005 (by default) or pass JAVA_DEBUG_PORT env variable in dockerfile