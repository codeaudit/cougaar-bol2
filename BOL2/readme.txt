The first release of the BooksOnline Tutorial adapted from the original BooksOnline.  

This first release only contains the source code, the tutorial trails will be posted soon.

Not all of the functionality of the original tutorial has been implemented, this will be coming in future releases.

The tutorial has been tested with Cougaar versions 10 and 10.2.

Description of file release:
	bol2_javadoc.zip/tar.gz - contains javadoc.
	
	bol2_src.zip/tar.gz - contains the Java source for BOL
	
	bol2.zip/tar.gz - contains the third party jars, the bol-1.0.jar file and the configuration files necessary to 

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
	Windows
		From the INSTALL_PATH/configs/base directory run the following:	sNode BOLNode
	Unix/Linux
		From the INSTALL_PATH/configs/base directory run the following: sNode.sh BOLNode
