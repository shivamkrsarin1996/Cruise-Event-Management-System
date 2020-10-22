-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: ship
-- ------------------------------------------------------
-- Server version	5.7.31-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `create`
--

DROP TABLE IF EXISTS `create`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `create` (
  `idcreate` int(11) NOT NULL AUTO_INCREMENT,
  `eventid` int(11) NOT NULL,
  `managerid` int(11) NOT NULL,
  `time` time NOT NULL,
  `DATE` date NOT NULL,
  `estimated` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcreate`),
  KEY `managerid_idx` (`managerid`),
  KEY `eventid_idx` (`eventid`),
  CONSTRAINT `eventid` FOREIGN KEY (`eventid`) REFERENCES `events` (`idevents`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `managerid` FOREIGN KEY (`managerid`) REFERENCES `user` (`id_used`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `create`
--

LOCK TABLES `create` WRITE;
/*!40000 ALTER TABLE `create` DISABLE KEYS */;
INSERT INTO `create` VALUES (1,201,12,'11:55:00','2020-10-21',1),(3,203,12,'12:00:00','2020-09-18',30),(4,204,12,'12:00:00','2020-09-19',30),(5,205,12,'12:00:00','2020-09-19',30),(6,206,12,'12:00:00','2020-09-19',30),(7,207,12,'12:00:00','2020-09-19',30),(8,208,12,'12:00:00','2020-09-19',30),(9,209,12,'12:00:00','2020-09-19',30),(10,210,12,'12:00:00','2020-09-20',30),(12,210,12,'12:00:00','2020-09-21',30),(121,210,12,'09:59:00','2020-10-21',30),(122,208,12,'02:00:00','2020-09-21',12),(123,201,12,'20:18:00','2020-09-25',10),(124,201,12,'20:47:00','2020-09-25',9),(125,201,12,'21:00:00','2020-10-22',10),(126,201,12,'20:24:00','2020-09-26',5),(127,201,12,'21:00:00','2020-09-26',5),(128,201,12,'20:46:00','2020-09-27',3),(129,209,12,'19:06:00','2020-09-28',10),(130,201,12,'15:25:00','2020-11-24',8),(131,201,12,'09:15:00','2020-11-23',5),(132,201,12,'12:00:00','2020-11-22',10),(133,201,12,'13:20:00','2020-11-22',10),(134,203,12,'16:00:00','2020-11-22',15),(135,201,12,'13:45:00','2020-11-23',10),(136,201,12,'16:10:00','2020-11-22',9),(137,205,12,'18:00:00','2020-11-22',8),(139,201,12,'19:36:00','2020-11-22',5);
/*!40000 ALTER TABLE `create` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `events` (
  `idevents` int(11) NOT NULL AUTO_INCREMENT,
  `eventName` varchar(45) CHARACTER SET latin1 NOT NULL,
  `location` varchar(45) CHARACTER SET latin1 NOT NULL,
  `capacity` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `Type` varchar(45) NOT NULL,
  PRIMARY KEY (`idevents`)
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=ujis;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (201,'Bowling 1','Deck 8',10,60,'Athletic'),(202,'Bowling 2','Deck 8',10,60,'Athletic'),(203,'Movie 1','Deck 3',25,120,'Show'),(204,'Movie 2','Deck 3',50,120,'Show'),(205,'Extreme Zipline','Deck 15',15,30,'Athletic'),(206,'Skycourse Ropes','Deck 14',8,30,'Athletic'),(207,'Ice Skating','Deck 9',35,60,'Athletic'),(208,'Go Karting','Deck 12',20,60,'Athletic'),(209,'Broadway Show','Deck 5',100,120,'Show'),(210,'Planetarium','Deck 7',100,60,'Show');
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserve`
--

DROP TABLE IF EXISTS `reserve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserve` (
  `idreserve` int(11) NOT NULL AUTO_INCREMENT,
  `eventcreateid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`idreserve`),
  KEY `createid_idx` (`eventcreateid`),
  KEY `userid_idx` (`userid`),
  CONSTRAINT `createid` FOREIGN KEY (`eventcreateid`) REFERENCES `create` (`idcreate`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `user` (`id_used`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserve`
--

LOCK TABLES `reserve` WRITE;
/*!40000 ALTER TABLE `reserve` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id_used` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `first_name` varchar(45) CHARACTER SET latin1 NOT NULL,
  `last_name` varchar(45) CHARACTER SET latin1 NOT NULL,
  `role` varchar(45) CHARACTER SET latin1 NOT NULL,
  `password` varchar(45) CHARACTER SET latin1 NOT NULL,
  `room_number` int(11) NOT NULL,
  `phone` varchar(45) CHARACTER SET latin1 NOT NULL,
  `decknumber` int(11) NOT NULL,
  `memtype` varchar(45) CHARACTER SET latin1 NOT NULL,
  `email` varchar(50) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_used`),
  UNIQUE KEY `id_used_UNIQUE` (`id_used`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'manirudh95','Anirudh','Reddy','manager','mrushi95',120,'4693895980',12,'None','abc@gmail.com'),(12,'mrushi95','Rushi','Madaka','coordinator','mrushi95',123,'3983839831',12,'none','Mrushi96@ghms.com'),(13,'saik9','Sai','Kumar','passenger','Mrushi95@',122,'9999999999',1,'none','abc@gmail.com'),(20,'saik95','Anirudh','Madaka','passenger','Mrushi95@',123,'9701721111',15,'superior','abc@gads.com'),(21,'ldsdd','anirudh','anirudh','passenger','Mrushi95@',123,'9999999999',12,'none','m@njsdc.com'),(22,'maniru','Asn','Mas','passenger','Mrushi95@',123,'4693896480',2,'none','Mrushi95@gmai.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-22  2:24:52
