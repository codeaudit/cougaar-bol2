#!/bin/sh

COUGAAR_DEV_PATH=../../../../build/jars/bol-1.0.jar:../../../../lib/jasper-runtime.jar


if [ -z "$COUGAAR_INSTALL_PATH" ]; then
    echo "COUGAAR_INSTALL_PATH is not set. Using ~/COUGAAR9"
    COUGAAR_INSTALL_PATH=`~/COUGAAR9`
fi

LIBPATHS=$COUGAAR_INSTALL_PATH/lib/bootstrap.jar
DEVP=""

# Optional environment variable COUGAAR_DEV_PATH can be
# used to point to custom developer code: one or more colon separated
# directories/jars/zips. It may also be left unset.

if [ "$COUGAAR_DEV_PATH" != "" ]; then
    DEVP="-Dorg.cougaar.class.path=${COUGAAR_DEV_PATH}"
fi
BOOTPATH=$COUGAAR_INSTALL_PATH/lib/javaiopatch.jar
if [ "$COUGAAR3RDPARTY" = "" ]; then
     COUGAAR3RDPARTY=$COUGAAR_INSTALL_PATH/lib
fi

if [ "$COUGAAR_WORKSPACE"="" ]; then
    COUGAAR_WORKSPACE="${COUGAAR_INSTALL_PATH}/workspace"
fi

MYDOMAINS=""
BOOTSTRAPPER=org.cougaar.bootstrap.Bootstrapper
MYCLASSES=org.cougaar.core.node.Node
MYPROPERTIES="-Xbootclasspath/p:$COUGAAR_INSTALL_PATH/lib/javaiopatch.jar $MYPROPERTIES $MYDOMAINS  -Dorg.cougaar.system.path=$COUGAAR3RDPARTY -Dorg.cougaar.install.path=$COUGAAR_INSTALL_PATH -Dorg.cougaar.workspace=$COUGAAR_WORKSPACE"
MYPROPERTIES="$MYPROPERTIES -Duser.timezone=GMT  -Dorg.cougaar.core.agent.startTime=01/18/2008 -Dorg.cougaar.core.useBootstrapper=true -Dorg.cougaar.core.logging.config.filename=log.properties"
MYMEMORY="-Xms100m -Xmx300m"


node="$1"
shift
rest="$*"
if [ -z "$node" ]; then
  node="Clusters"
fi

if [ "$node" = "admin" ]; then
    args="-c -r -n Administrator -p 8000 $rest"
    MYMEMORY="-Djava.compiler=NONE"
else
    args="-n $node -c $rest"
    # arguments to adjust (defaults are given)
    # -Xmx64m         # max java heap
    # -Xms3m          # min (initial) java heap
    # -Xmaxf0.6       # max heap free percent
    # -Xminf0.35      # min heap free percent
    # -Xmaxe4m        # max heap expansion increment
    # -Xmine1m        # min heap expansion increment
    # -Xoss400k       # per-thread *java* stack size
    MYMEMORY="-Xmx768m -Xms64m -Xmaxf0.9 -Xminf0.1 -Xoss128k"
    #set MYMEMORY="-Xmx300m -Xms100m"
fi

if [ "$OS" = "Linux" ]; then
    # set some system runtime limits
    limit stacksize 16m    #up from 8m
    limit coredumpsize 0   #down from 1g
    #turn this on to enable inprise JIT
    #setenv JAVA_COMPILER javacomp
fi

#set javaargs="$osargs $MYPROPERTIES $MYMEMORY -classpath $LIBPATHS -Dorg.cougaar.core.message.isLogging=true -Djava.rmi.server.logCalls=true -Dsun.rmi.server.exceptionTrace=true -Dsun.rmi.transport.tcp.readTimeout=150000 "
javaargs="$MYPROPERTIES $MYMEMORY -classpath $LIBPATHS $DEVP $BOOTSTRAPPER $MYCLASSES"

if [ "$COUGAAR_DEV_PATH" != "" ]; then
    echo java $javaargs $args
fi

# exec instead of eval
exec java $javaargs $args

