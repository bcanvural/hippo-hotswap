# Use docker for local development
This project contains configuration for using a dockerized tomcat server instead 
 of OOTB cargo.run profile
 
 -------------------------------------------------------
 
 ## Skip all and use hotswap in your own hippo project:
 From inside an archetype project:
 ```bash
 $ docker run --mount type=bind,source=$(pwd),target=/home/myhippoproject bcanvural/hotswap-prepper:v1
 ```
 Then run 
 ```bash
 $ ./deploy_local_tomcat.sh
 ```
 
 -------------------------------------------------------
 
 ## Build project, build docker image, run image
 ```bash
 $ ./deploy_local_tomcat.sh
 ```
 
 ## Use HotSwap Agent
 Compile code in IDE, classes will be hot-swapped within container
 
 ## Redeploy site to running instance
 For cases where hotswap operation is not supported, repackage and redeploy site module:
 
 ```bash
 $ ./redeploy-site.sh
 ```
 
 ## Debugging
 
 Attach remote debugger at port 8000 (by default) or pass JAVA_DEBUG_PORT env variable in dockerfile
