The first release of the BooksOnline Tutorial adapted from the original BooksOnline.  

This first release only contains the source code, the tutorial trails will be posted soon.

Not all of the functionality of the original tutorial has been implemented, this will be coming in future releases.

The tutorial has been tested with Cougaar versions 10 and 10.2.

Description of file release:
	The src folder contains all of the Java Source code.
	The build folder contains an already build bol.jar file, so the BOL Society can be run right after the download.
	The configs folder contains all of the Cougaar configuration files and a database sub directory containing the scripts necessary to create the MySQL database.
	The docs folder contains the PDF manual, giving an overview of the BooksOnline Tutorial.

run the BOLSociety.
	
	bol_full.zip/tar.gz - contains all of the above.
	

Running and installing BOL

Install the database to MySQL 
	Windows
		From the  INSTALL_PATH/configs/base/database directory run the following:  createDB.bat
	Unix/Linux
		From the INSTALL_PATH/configs/base/database directory run the following: createDatabase.sh


	Now you must change the database parameters in the  INSTALL_PATH/configs/base/q.xml file for your MySQL 

database. E.G. change the username and password parameters. 


Run the Society
	NOTE:  change the log.properties file to DEBUG if you want to see more output on the command line
	
	Windows
		From the INSTALL_PATH/configs/base directory run the following:	sNode BOLNode
	Unix/Linux
		From the INSTALL_PATH/configs/base directory run the following: sNode.sh BOLNode
		
View The Web page

	Open a web browser and goto:  http://localhost:8800/$OrderManager/bol		(Must use the port you are running on)
