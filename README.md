# Demo for hotswap site support module
Run 
```bash
$ ./deploy_local_tomcat.sh
```
* Checkout localhost:8080/site/book . It's empty.
* Go to beanwriter tool and generate beans. Now there's a bean called Bookdocument. Now compile both Bookdocument
 and BookComponent classes (uncomment code in bookcomponent).
* Checkout localhost:8080/site/book again. It still does not work.
* Now go to console and check "refresh" boolean property to true on the root node. This will make the object converter to refresh.
* Checkout localhost:8080/site/book again. Now it works.
 


# Use docker for local development
This project contains configuration for using a dockerized tomcat server instead 
 of OOTB cargo.run profile
 
 ## Skip all and use hotswap in your own hippo project:
 From inside an archetype project:
 ```bash
 $ docker run --mount type=bind,source=$(pwd),target=/home/myhippoproject bcanvural/hotswap-prepper:v1
 ```
 Then run 
 ```bash
 $ ./deploy_local_tomcat.sh
 ```
 
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