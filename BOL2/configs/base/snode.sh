#!/bin/sh


##
## Cougaar Node Settings - CHANGE these as appropriate
##
SOCIETY_FILE=BolSociety.xml
NODE_NAME=BOLNode


for arg in \
    -Xbootclasspath/p:${COUGAAR_INSTALL_PATH}/lib/javaiopatch.jar \
    -Dorg.cougaar.system.path=${COUGAAR_INSTALL_PATH}/sys \
    -Dorg.cougaar.core.node.InitializationComponent=XML \
    -Xoss256k \
    -Dorg.cougaar.lib.web.scanRange=100 \
    -Dorg.cougaar.core.servlet.enable=true \
    -Dorg.cougaar.workspace=${COUGAAR_INSTALL_PATH}/workspace \
    -Xmx128m \
    -Dorg.cougaar.lib.web.https.port=-1 \
    -Dorg.cougaar.install.path=${COUGAAR_INSTALL_PATH} \
    -Duser.timezone=GMT \
    -Dorg.cougaar.core.logging.config.filename=log.properties \
    -Dorg.cougaar.config.path=.\;${COUGAAR_INSTALL_PATH}/configs/common\;${COUGAAR_INSTALL_PATH}/configs/glmtrans\
    -Dorg.cougaar.lib.web.http.port=8800 \
    -Xms64m \
    -Dorg.cougaar.core.agent.startTime=08/10/2005 \
    -Dorg.cougaar.society.file=${SOCIETY_FILE} \
    -Dorg.cougaar.node.name=${NODE_NAME} \
    -Dorg.cougaar.name.server=localhost:8000 \
    -Dorg.cougaar.core.load.wp.server=false 
do
    export args="${args} ${arg}"
done

# name of the bootstrapper class
bootclass="org.cougaar.bootstrap.Bootstrapper"
nodeclass="org.cougaar.core.node.Node"
boostrapper=${COUGAAR_INSTALL_PATH}/lib/bootstrap.jar
COUGAAR_DEV_PATH=../../build/jars/bol-1.0.jar:../../lib/jasper-runtime.jar

myclasspath=$boostrapper;$COUGAAR_DEV_PATH
#echo $myclasspath
echo $args
$JAVA_HOME/bin/java $args -classpath $myclasspath $bootclass $nodeclass 

