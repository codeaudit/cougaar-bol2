@echo OFF


set COUGAAR_DEV_PATH=.
set COUGAAR_DEV_PATH=%COUGAAR_DEV_PATH%;../../../../build/jars/bol-1.0.jar;../../../../lib/jasper-runtime.jar

REM Make sure that COUGAAR_INSTALL_PATH is specified
IF NOT "%COUGAAR_INSTALL_PATH%" == "" GOTO L_2

REM Unable to find cougaar-install-path
ECHO COUGAAR_INSTALL_PATH not set!
GOTO L_END
:L_2

REM Make sure that COUGAAR3RDPARTY is specified
IF NOT "%COUGAAR3RDPARTY%" == "" GOTO L_3

REM Unable to find "sys" path for 3rd-party jars
REM This is usually COUGAAR_INSTALL_PATH/sys
ECHO COUGAAR3RDPARTY not set! Defaulting to CIP\sys
SET COUGAAR3RDPARTY=%COUGAAR_INSTALL_PATH%\sys
:L_3

REM Make sure that COUGAAR_WORKSPACE is set
IF NOT "%COUGAAR_WORKSPACE%" == "" GOTO L_4

REM Path for runtime output not set.
REM Default is CIP/workspace
ECHO COUGAAR_WORKSPACE not set. Defaulting to CIP/workspace
SET COUGAAR_WORKSPACE=%COUGAAR_INSTALL_PATH%\workspace
:L_4

REM calls setlibpath.bat which sets the path to the required jar files.
REM calls setarguments.bat which sets input parameters for system behavior
CALL %COUGAAR_INSTALL_PATH%\bin\setlibpath.bat
CALL %COUGAAR_INSTALL_PATH%\bin\setarguments.bat

REM pass in "NodeName" to run a specific named Node
REM pass in "admin" to run SANode separately
set MYARGUMENTS= -c -n "%1"
set MYPROPERTIES=%MYPROPERTIES% -Dorg.cougaar.core.logging.config.filename=log.properties


if "%1"=="admin" set MYARGUMENTS= -n Administrator -c -r -p 8000
if "%1"=="admin" set MYMEMORY= -Djava.compiler=NONE -Xms16m 
if "%1"=="EmptyNode" set MYMEMORY= -Xms16m 

@ECHO ON

java.exe %MYPROPERTIES% %MYMEMORY% -classpath %LIBPATHS% %MYCLASSES% %MYARGUMENTS%

:L_END
