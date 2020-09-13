CREATE DATABASE  IF NOT EXISTS `ship` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ship`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ship
-- ------------------------------------------------------
-- Server version	5.7.27-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `create` (
  `idcreate` int(11) NOT NULL AUTO_INCREMENT,
  `eventid` int(11) NOT NULL,
  `managerid` int(11) NOT NULL,
  `time` time NOT NULL,
  `DATE` date NOT NULL,
  PRIMARY KEY (`idcreate`),
  KEY `managerid_idx` (`managerid`),
  KEY `eventid_idx` (`eventid`),
  CONSTRAINT `eventid` FOREIGN KEY (`eventid`) REFERENCES `events` (`idevents`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `managerid` FOREIGN KEY (`managerid`) REFERENCES `user` (`id_used`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `create`
--

LOCK TABLES `create` WRITE;
/*!40000 ALTER TABLE `create` DISABLE KEYS */;
INSERT INTO `create` VALUES (1,1,1,'12:00:00','2020-09-11');
/*!40000 ALTER TABLE `create` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events` (
  `idevents` int(11) NOT NULL AUTO_INCREMENT,
  `eventName` varchar(45) CHARACTER SET latin1 NOT NULL,
  `location` varchar(45) CHARACTER SET latin1 NOT NULL,
  `capacity` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `Type` varchar(45) NOT NULL,
  PRIMARY KEY (`idevents`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=ujis;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (1,'Bowling 1','Deck 8',10,60,'Athletic'),(2,'Bowling 2','Deck 8',10,60,'Athletic'),(3,'Movie 1','Deck 3',25,120,'Show'),(4,'Movie 2','Deck 3',50,120,'Show'),(5,'Extreme Zipline','Deck 15',15,30,'Athletic'),(6,'Skycourse Ropes','Deck 14',8,30,'Athletic'),(7,'Ice Skating','Deck 9',35,60,'Athletic'),(8,'Go Karting','Deck 12',20,60,'Athletic'),(9,'Broadway Show','Deck 5',100,120,'Show'),(10,'Planetarium','Deck 7',100,60,'Show');
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserve`
--

DROP TABLE IF EXISTS `reserve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'manirudh95','Anirudh','Reddy','manager','mrushi95',120,'4693895980',12,'None','abc@gmail.com'),(4,'manirudhsd','Anirudh','Reddu','passenger','Mrushi!@#95',123,'4989879876',12,'none','anirudh@gmail.com'),(9,'Manir','ani','mas','passenger','Mrushi95@',123,'4694895980',12,'none','Mrushi95@gmail.com'),(10,'Manir5','qwq','qws','passenger','Mrushi96@',134,'3983839831',12,'none','Mrushi96@ghms.com'),(12,'mrushi95','asxa','asda','coordinator','mrushi95',123,'3983839831',12,'none','Mrushi96@ghms.com');
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

-- Dump completed on 2020-09-12 12:46:11
