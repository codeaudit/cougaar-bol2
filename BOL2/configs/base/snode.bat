@echo OFF



SET SOCIETY_FILE=BolSociety.xml
SET NODE_NAME=BOLNode


set args=-Dorg.cougaar.system.path=%COUGAAR_INSTALL_PATH%/sys -Dorg.cougaar.core.node.InitializationComponent=XML  -Xoss256k  -Dorg.cougaar.lib.web.scanRange=100 -Dorg.cougaar.core.servlet.enable=true  -Dorg.cougaar.workspace=%COUGAAR_INSTALL_PATH%/workspace -Xmx128m -Dorg.cougaar.lib.web.https.port=-1 -Dorg.cougaar.install.path=%COUGAAR_INSTALL_PATH% -Duser.timezone=GMT -Dorg.cougaar.core.logging.config.filename=log.properties -Dorg.cougaar.config.path=.\;%COUGAAR_INSTALL_PATH%/configs/common\;%COUGAAR_INSTALL_PATH%/configs/glmtrans -Dorg.cougaar.lib.web.http.port=8800 -Xms64m -Dorg.cougaar.core.agent.startTime=08/10/2005 -Dorg.cougaar.society.file=%SOCIETY_FILE% -Dorg.cougaar.node.name=%NODE_NAME% -Dorg.cougaar.name.server=127.0.0.1:8000 -Dorg.cougaar.core.load.wp.server=true

set bootclass="org.cougaar.bootstrap.Bootstrapper"
set nodeclass="org.cougaar.core.node.Node"
set boostrapper=%COUGAAR_INSTALL_PATH%/lib/bootstrap.jar


set myclasspath=%boostrapper%

echo %args%
java %args% -classpath %myclasspath% %bootclass% %nodeclass% 


 

