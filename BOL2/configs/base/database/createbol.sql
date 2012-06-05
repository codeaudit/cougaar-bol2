-- MySQL dump 9.08
--
-- Host: localhost    Database: bol
---------------------------------------------------------
-- Server version	4.0.13-nt

--
-- Table structure for table 'books'
--
DROP DATABASE IF EXISTS bol;
create database bol;
use bol;



-- MySQL dump 9.08
--
-- Host: localhost    Database: bol
---------------------------------------------------------
-- Server version	4.0.13-nt

--
-- Table structure for table 'books'
--

CREATE TABLE books (
  Title varchar(100) default NULL,
  TOC varchar(20) default NULL,
  Rank int(6) default NULL,
  PNotes varchar(20) default NULL,
  Keywords varchar(100) default NULL,
  Author varchar(200) default NULL,
  Affil varchar(50) default NULL,
  Intrvw varchar(50) default NULL,
  ShipMessage varchar(100) default NULL,
  Photo varchar(100) default NULL,
  Media varchar(100) default NULL,
  Lnth varchar(100) default NULL,
  Release varchar(100) default NULL,
  ISBN varchar(100) NOT NULL default '',
  Dimension varchar(100) default NULL,
  List varchar(100) default NULL,
  OurP decimal(3,0) default NULL,
  EndPrice varchar(100) default NULL,
  Reviews varchar(100) default NULL,
  NumReviews int(8) default NULL,
  AvgRating decimal(100,0) default NULL,
  PubName char(3) default NULL,
  PubAddress varchar(100) default NULL,
  PubCity varchar(100) default NULL,
  State varchar(100) default NULL,
  shelf int(4) default NULL,
  OnOrder int(4) default NULL,
  warehouse int(11) default NULL,
  PRIMARY KEY  (ISBN)
) TYPE=MyISAM;

--
-- Dumping data for table 'books'
--

INSERT INTO books VALUES ('Peg o\' my Heart','pegtoc.txt',4,'none','big band, swing','Glenn Miller','none','none','Available in 2-3 days','-1','CD','1','19430511','GRD-2-629','1 CD Track','14',12,'20010511','pegheartrevw.txt',2,5,'Orr','555 W. 57th Street','New York','NY',1500,0,NULL);
INSERT INTO books VALUES ('Black Bottom Stomp','none',3,'bbpubnotes.txt','jazz','Jelly Roll Morton','none','none','Available in 4-6 weeks','-1','CD','1','19260215','07863-66641-2','1 CD Track','15',6,'0','none',0,0,'Orr','555 W. 57th Street','New York','NY',1500,1600,NULL);
INSERT INTO books VALUES ('Fleet Tactics','none',1,'none','war, tactics, naval combat','Wayne P. Hughes','hughesaffil.txt','none','Available in 24 hours','-1','HBOOK','562','19990314','1-56592-326-X','5\" x 8\"','49.99',45,'20010824','fleetacticsreview.txt',1,4,'O\'R','101 Morris Street','Sebastopol','CA',1500,12,NULL);
INSERT INTO books VALUES ('Panassie Stomp','none',2,'none','jazz','Count Basie','none','basieinterview.txt','On backorder, allow 2-3 months','-1','cd','1','19300531','someisbn','1 CD Track','11',9,'20000601','none',0,0,'Dec','2222 Donald Street','Ft. Wayne','IN',1500,1,NULL);
INSERT INTO books VALUES ('Harry Potter and the Goblet of Fire (Harry Potter IV)','none',1,'none','children','J. K. Rowling','none','none','Available in 24 hours','-1','HBOOK','562','20000709','439139597','5\" x 8\"','49.99',45,'20010824','none',1,4,'Sch','101 Morris Street','Sebastopol','CA',1500,12,NULL);
INSERT INTO books VALUES ('Harry Potter and the Chamber of Secrets','none',1,'none','children','J. K. Rowling','none','none','Available in 24 hours','-1','HBOOK','562','19990314','439064864','5\" x 8\"','17.95',9,'20010824','none',1,4,'Sch','101 Morris Street','Sebastopol','CA',1500,12,NULL);
INSERT INTO books VALUES ('Harry Potter and the Prisoner of Azkaban','none',1,'none','children','J. K. Rowling','none','none','Available in 24 hours','-1','HBOOK','562','19990314','439136350','5\" x 8\"','19.95',10,'20010824','none',1,4,'Sch','101 Morris Street','Sebastopol','CA',1500,12,NULL);
INSERT INTO books VALUES ('Who Moved My Cheese?: An Amazing Way to Deal with Change in Your Work and in Your Life','none',1,'none','life, work','Spencer Johnson','none','none','Available in 24 hours','-1','HBOOK','562','19990314','399144463','5\" x 8\"','19.95',10,'20010824','none',1,4,'Put','101 Morris Street','Sebastopol','CA',1500,12,NULL);
INSERT INTO books VALUES ('Harry Potter and the Sorcerer\'s Stone','none',1,'none','children','J. K. Rowling','none','none','Available in 24 hours','-1','HBOOK','562','19990314','590353403','5\" x 8\"','19.95',10,'20010824','none',1,4,'Sch','101 Morris Street','Sebastopol','CA',1500,12,NULL);
INSERT INTO books VALUES ('The Brethren','none',1,'none','mystery','John Grisham','none','none','Available in 24 hours','-1','HBOOK','562','20000314','385497466','5\" x 8\"','27.95',14,'20010824','none',1,4,'Dou','101 Morris Street','Sebastopol','CA',1500,12,NULL);
INSERT INTO books VALUES ('Body for Life: 12 Weeks to Mental and Physical Fitness Forever','none',1,'none','fitness, body, life','Bill Phillips','none','none','Available in 24 hours','-1','HBOOK','562','19990314','60193395','5\" x 8\"','26',13,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',1500,12,NULL);
INSERT INTO books VALUES ('The Poisonwood Bible Belt','none',1,'none','mystery','Barbara Kingsolver','none','none','Available in 24 hours','-1','HBOOK','562','19990314','60930535','5\" x 8\"','14',7,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',1500,12,NULL);
INSERT INTO books VALUES ('The Perfect Storm: A True Story of Men Against the Sea','none',1,'none','naval, seafaring, weather, storm','Sebastian Junger','none','none','Available in 24 hours','-1','HBOOK','561','19990314','006101351X','5\" x 8\"','6.99',4,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',1500,12,NULL);
INSERT INTO books VALUES ('The Matrix','none',1,'none','sci fi','Warner Bros','none','none','Available in 24 hours','-1','DVD','1000','19990314','6101351000','5\" x 8\"','19.99',20,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Foundation','none',1,'none','sci fi','Isaac Asimov','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351001','5\" x 8\"','6.99',7,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Foundation and Empire','none',1,'none','sci fi','Isaac Asimov','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351002','5\" x 8\"','6.99',7,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('I Robot','none',1,'none','sci fi','Isaac Asimov','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351003','5\" x 8\"','6.99',7,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Starship Troopers','none',1,'none','sci fi','Robert Heinlein','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351004','5\" x 8\"','5.99',6,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Lucifers Hammer','none',1,'none','sci fi','Robert Heinlein','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351005','5\" x 8\"','7.99',8,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Uplift Wars','none',1,'none','sci fi','David Brinn','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351006','5\" x 8\"','6.99',7,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Quantum Reality','none',1,'none','sci fi','John Doe','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351007','5\" x 8\"','13.99',14,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Relativity','none',1,'none','science','Albert Einstein','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351008','5\" x 8\"','15',15,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Nightwatch','none',1,'none','astronomy','Terrence Dickinson','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351009','5\" x 8\"','22',22,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Backyard Astronomer','none',1,'none','astronomy','Terrence Dickinson','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351010','5\" x 8\"','16.95',17,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Fourth Protocol','none',1,'none','sci fi','Frederick Forsythe','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351011','5\" x 8\"','14',14,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Rollerball','none',1,'none','sci fi','MGM','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351012','5\" x 8\"','22',22,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Artifact','none',1,'none','sci fi','David Benford','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351013','5\" x 8\"','7.1',7,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Jupiter Project','none',1,'none','sci fi','David Benford','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351014','5\" x 8\"','6.99',7,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Shiva Descending','none',1,'none','sci fi','David Benford','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351015','5\" x 8\"','6.99',7,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Timescape','none',1,'none','sci fi','David Benford','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351016','5\" x 8\"','7',7,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Heart of the Comet','none',1,'none','sci fi','David Benford','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351018','5\" x 8\"','8.99',9,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Timescape','none',1,'none','sci fi','David Benford','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351019','5\" x 8\"','7.99',8,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Grapes of Wrath','none',1,'none','literature','John Steinbeck','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351020','5\" x 8\"','12.99',13,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('2001: A Space Odyssey','none',1,'none','sci fi','Arthur C. Clarke','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351021','5\" x 8\"','14.99',15,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Green Eggs and Ham','none',1,'none','children','Dr. Seuss','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351022','5\" x 8\"','8.39',8,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('The Sleep Book','none',1,'none','children','Dr. Seuss','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351023','5\" x 8\"','7.99',8,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('The Lorax','none',1,'none','children','Dr. Seuss','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351024','5\" x 8\"','6.29',6,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('How the Grinch Stole Christmas','none',1,'none','children','Dr. Seuss','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351025','5\" x 8\"','7.99',8,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('The Hobbit','none',1,'none','fantasy','J.R.R. Tolkien','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351026','5\" x 8\"','5.99',6,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('The Two Towers','none',1,'none','fantasy','J.R.R. Tolkien','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351027','5\" x 8\"','5.99',6,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Return of the King','none',1,'none','fantasy','J.R.R. Tolkien','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351028','5\" x 8\"','5.99',6,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('The Silmarillion','none',1,'none','fantasy','J.R.R. Tolkien','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351029','5\" x 8\"','7.99',8,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('The Hitchhikers Guide to the Galaxy','none',1,'none','sci fi','Douglas Adams','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351030','5\" x 8\"','8.99',9,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('So long and thanks for all the Fish','none',1,'none','sci fi','Douglas Adams','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351031','5\" x 8\"','6.99',7,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Longitude','none',1,'none','sci fi','William Wyler','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351032','5\" x 8\"','23.99',24,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('12 Monkeys','none',1,'none','sci fi','MGM','none','none','Available in 24 hours','-1','DVD','1000','19990314','6101351033','5\" x 8\"','19.99',20,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Mercury Rising','none',1,'none','sci fi','Universal Studios','none','none','Available in 24 hours','-1','DVD','1000','19990314','6101351034','5\" x 8\"','19.99',20,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Die Hard','none',1,'none','sci fi','Universal Studios','none','none','Available in 24 hours','-1','DVD','1000','19990314','6101351035','5\" x 8\"','19.99',20,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Contact','none',1,'none','sci fi','MGM','none','none','Available in 24 hours','-1','DVD','1000','19990314','6101351036','5\" x 8\"','19.99',20,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO books VALUES ('Return of the Jedi','none',1,'none','sci fi','20th Century Fox','none','none','Available in 24 hours','-1','DVD','1000','19990314','6101351037','5\" x 8\"','16.99',17,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);

--
-- Table structure for table 'publisherbooks'
--

CREATE TABLE publisherbooks (
  Title varchar(100) default NULL,
  TOC varchar(20) default NULL,
  Rank int(6) default NULL,
  PNotes varchar(20) default NULL,
  Keywords varchar(100) default NULL,
  Author varchar(200) default NULL,
  Affil varchar(50) default NULL,
  Intrvw varchar(50) default NULL,
  ShipMessage varchar(100) default NULL,
  Photo varchar(100) default NULL,
  Media varchar(100) default NULL,
  Lnth varchar(100) default NULL,
  Release varchar(100) default NULL,
  ISBN varchar(100) NOT NULL default '',
  Dimension varchar(100) default NULL,
  List varchar(100) default NULL,
  OurP decimal(3,0) default NULL,
  EndPrice varchar(100) default NULL,
  Reviews varchar(100) default NULL,
  NumReviews int(8) default NULL,
  AvgRating decimal(100,0) default NULL,
  PubName char(3) default NULL,
  PubAddress varchar(100) default NULL,
  PubCity varchar(100) default NULL,
  State varchar(100) default NULL,
  shelf int(4) default NULL,
  OnOrder int(4) default NULL,
  publisher int(11) default NULL,
  PRIMARY KEY  (ISBN)
) TYPE=MyISAM;

--
-- Dumping data for table 'publisherbooks'
--

INSERT INTO publisherbooks VALUES ('Peg o\' my Heart','pegtoc.txt',4,'none','big band, swing','Glenn Miller','none','none','Available in 2-3 days','-1','CD','1','19430511','GRD-2-629','1 CD Track','14',12,'20010511','pegheartrevw.txt',2,5,'Orr','555 W. 57th Street','New York','NY',1500,0,NULL);
INSERT INTO publisherbooks VALUES ('Black Bottom Stomp','none',3,'bbpubnotes.txt','jazz','Jelly Roll Morton','none','none','Available in 4-6 weeks','-1','CD','1','19260215','07863-66641-2','1 CD Track','15',6,'0','none',0,0,'Orr','555 W. 57th Street','New York','NY',1500,1600,NULL);
INSERT INTO publisherbooks VALUES ('Fleet Tactics','none',1,'none','war, tactics, naval combat','Wayne P. Hughes','hughesaffil.txt','none','Available in 24 hours','-1','HBOOK','562','19990314','1-56592-326-X','5\" x 8\"','49.99',45,'20010824','fleetacticsreview.txt',1,4,'O\'R','101 Morris Street','Sebastopol','CA',1500,12,NULL);
INSERT INTO publisherbooks VALUES ('Panassie Stomp','none',2,'none','jazz','Count Basie','none','basieinterview.txt','On backorder, allow 2-3 months','-1','cd','1','19300531','someisbn','1 CD Track','11',9,'20000601','none',0,0,'Dec','2222 Donald Street','Ft. Wayne','IN',1500,1,NULL);
INSERT INTO publisherbooks VALUES ('Harry Potter and the Goblet of Fire (Harry Potter IV)','none',1,'none','children','J. K. Rowling','none','none','Available in 24 hours','-1','HBOOK','562','20000709','439139597','5\" x 8\"','49.99',45,'20010824','none',1,4,'Sch','101 Morris Street','Sebastopol','CA',1500,12,NULL);
INSERT INTO publisherbooks VALUES ('Harry Potter and the Chamber of Secrets','none',1,'none','children','J. K. Rowling','none','none','Available in 24 hours','-1','HBOOK','562','19990314','439064864','5\" x 8\"','17.95',9,'20010824','none',1,4,'Sch','101 Morris Street','Sebastopol','CA',1500,12,NULL);
INSERT INTO publisherbooks VALUES ('Harry Potter and the Prisoner of Azkaban','none',1,'none','children','J. K. Rowling','none','none','Available in 24 hours','-1','HBOOK','562','19990314','439136350','5\" x 8\"','19.95',10,'20010824','none',1,4,'Sch','101 Morris Street','Sebastopol','CA',1500,12,NULL);
INSERT INTO publisherbooks VALUES ('Who Moved My Cheese?: An Amazing Way to Deal with Change in Your Work and in Your Life','none',1,'none','life, work','Spencer Johnson','none','none','Available in 24 hours','-1','HBOOK','562','19990314','399144463','5\" x 8\"','19.95',10,'20010824','none',1,4,'Put','101 Morris Street','Sebastopol','CA',1500,12,NULL);
INSERT INTO publisherbooks VALUES ('Harry Potter and the Sorcerer\'s Stone','none',1,'none','children','J. K. Rowling','none','none','Available in 24 hours','-1','HBOOK','562','19990314','590353403','5\" x 8\"','19.95',10,'20010824','none',1,4,'Sch','101 Morris Street','Sebastopol','CA',1500,12,NULL);
INSERT INTO publisherbooks VALUES ('The Brethren','none',1,'none','mystery','John Grisham','none','none','Available in 24 hours','-1','HBOOK','562','20000314','385497466','5\" x 8\"','27.95',14,'20010824','none',1,4,'Dou','101 Morris Street','Sebastopol','CA',1500,12,NULL);
INSERT INTO publisherbooks VALUES ('Body for Life: 12 Weeks to Mental and Physical Fitness Forever','none',1,'none','fitness, body, life','Bill Phillips','none','none','Available in 24 hours','-1','HBOOK','562','19990314','60193395','5\" x 8\"','26',13,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',1500,12,NULL);
INSERT INTO publisherbooks VALUES ('The Poisonwood Bible Belt','none',1,'none','mystery','Barbara Kingsolver','none','none','Available in 24 hours','-1','HBOOK','562','19990314','60930535','5\" x 8\"','14',7,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',1500,12,NULL);
INSERT INTO publisherbooks VALUES ('The Perfect Storm: A True Story of Men Against the Sea','none',1,'none','naval, seafaring, weather, storm','Sebastian Junger','none','none','Available in 24 hours','-1','HBOOK','561','19990314','006101351X','5\" x 8\"','6.99',4,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',1500,12,NULL);
INSERT INTO publisherbooks VALUES ('The Matrix','none',1,'none','sci fi','Warner Bros','none','none','Available in 24 hours','-1','DVD','1000','19990314','6101351000','5\" x 8\"','19.99',20,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Foundation','none',1,'none','sci fi','Isaac Asimov','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351001','5\" x 8\"','6.99',7,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Foundation and Empire','none',1,'none','sci fi','Isaac Asimov','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351002','5\" x 8\"','6.99',7,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('I Robot','none',1,'none','sci fi','Isaac Asimov','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351003','5\" x 8\"','6.99',7,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Starship Troopers','none',1,'none','sci fi','Robert Heinlein','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351004','5\" x 8\"','5.99',6,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Lucifers Hammer','none',1,'none','sci fi','Robert Heinlein','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351005','5\" x 8\"','7.99',8,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Uplift Wars','none',1,'none','sci fi','David Brinn','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351006','5\" x 8\"','6.99',7,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Quantum Reality','none',1,'none','sci fi','John Doe','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351007','5\" x 8\"','13.99',14,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Relativity','none',1,'none','science','Albert Einstein','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351008','5\" x 8\"','15',15,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Nightwatch','none',1,'none','astronomy','Terrence Dickinson','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351009','5\" x 8\"','22',22,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Backyard Astronomer','none',1,'none','astronomy','Terrence Dickinson','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351010','5\" x 8\"','16.95',17,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Fourth Protocol','none',1,'none','sci fi','Frederick Forsythe','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351011','5\" x 8\"','14',14,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Rollerball','none',1,'none','sci fi','MGM','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351012','5\" x 8\"','22',22,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Artifact','none',1,'none','sci fi','David Benford','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351013','5\" x 8\"','7.1',7,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Jupiter Project','none',1,'none','sci fi','David Benford','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351014','5\" x 8\"','6.99',7,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Shiva Descending','none',1,'none','sci fi','David Benford','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351015','5\" x 8\"','6.99',7,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Timescape','none',1,'none','sci fi','David Benford','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351016','5\" x 8\"','7',7,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Heart of the Comet','none',1,'none','sci fi','David Benford','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351018','5\" x 8\"','8.99',9,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Timescape','none',1,'none','sci fi','David Benford','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351019','5\" x 8\"','7.99',8,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Grapes of Wrath','none',1,'none','literature','John Steinbeck','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351020','5\" x 8\"','12.99',13,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('2001: A Space Odyssey','none',1,'none','sci fi','Arthur C. Clarke','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351021','5\" x 8\"','14.99',15,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Green Eggs and Ham','none',1,'none','children','Dr. Seuss','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351022','5\" x 8\"','8.39',8,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('The Sleep Book','none',1,'none','children','Dr. Seuss','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351023','5\" x 8\"','7.99',8,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('The Lorax','none',1,'none','children','Dr. Seuss','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351024','5\" x 8\"','6.29',6,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('How the Grinch Stole Christmas','none',1,'none','children','Dr. Seuss','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351025','5\" x 8\"','7.99',8,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('The Hobbit','none',1,'none','fantasy','J.R.R. Tolkien','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351026','5\" x 8\"','5.99',6,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('The Two Towers','none',1,'none','fantasy','J.R.R. Tolkien','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351027','5\" x 8\"','5.99',6,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Return of the King','none',1,'none','fantasy','J.R.R. Tolkien','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351028','5\" x 8\"','5.99',6,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('The Silmarillion','none',1,'none','fantasy','J.R.R. Tolkien','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351029','5\" x 8\"','7.99',8,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('The Hitchhikers Guide to the Galaxy','none',1,'none','sci fi','Douglas Adams','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351030','5\" x 8\"','8.99',9,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('So long and thanks for all the Fish','none',1,'none','sci fi','Douglas Adams','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351031','5\" x 8\"','6.99',7,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Longitude','none',1,'none','sci fi','William Wyler','none','none','Available in 24 hours','-1','HBOOK','1000','19990314','6101351032','5\" x 8\"','23.99',24,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('12 Monkeys','none',1,'none','sci fi','MGM','none','none','Available in 24 hours','-1','DVD','1000','19990314','6101351033','5\" x 8\"','19.99',20,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Mercury Rising','none',1,'none','sci fi','Universal Studios','none','none','Available in 24 hours','-1','DVD','1000','19990314','6101351034','5\" x 8\"','19.99',20,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Die Hard','none',1,'none','sci fi','Universal Studios','none','none','Available in 24 hours','-1','DVD','1000','19990314','6101351035','5\" x 8\"','19.99',20,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Contact','none',1,'none','sci fi','MGM','none','none','Available in 24 hours','-1','DVD','1000','19990314','6101351036','5\" x 8\"','19.99',20,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);
INSERT INTO publisherbooks VALUES ('Return of the Jedi','none',1,'none','sci fi','20th Century Fox','none','none','Available in 24 hours','-1','DVD','1000','19990314','6101351037','5\" x 8\"','16.99',17,'20010824','none',1,4,'Har','101 Morris Street','Sebastopol','CA',400,12,NULL);


