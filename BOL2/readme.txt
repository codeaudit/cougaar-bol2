This zip file should be unzipped into your Cougaar install path creating a bol2 subdirectory and installing serveral jar 
files in the COUGAAR_INSTALL_PATH/sys directory.

	

Running and installing BOL

Install the database to MySQL 
	Windows
		From the  COUGAAR_INSTALL_PATH/configs/base/database directory run the following:  createDB.bat
	Unix/Linux
		From the COUGAAR_INSTALL_PATH/configs/base/database directory run the following: createDatabase.sh


	Now you must change the database parameters in the  INSTALL_PATH/configs/base/q.xml file for your MySQL 

database. E.G. change the username and password parameters. 


Run the Society
	NOTE:  change the log.properties file to DEBUG if you want to see more output on the command line
	
	Windows
		From the COUGAAR_INSTALL_PATH/configs/base directory run the following:	sNode BOLNode
	Unix/Linux
		From the COUGAAR_INSTALL_PATH/configs/base directory run the following: sNode.sh BOLNode
		
View The Web page

	Open a web browser and goto:  http://localhost:8800/$OrderManager/bol		(Must use the port you are running on)

	
**NOTE You can also run the society using Cougaar IDE's run from Society feature.