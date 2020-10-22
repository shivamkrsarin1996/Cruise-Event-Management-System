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
-- Dumping data for table `create`
--

LOCK TABLES `create` WRITE;
/*!40000 ALTER TABLE `create` DISABLE KEYS */;
INSERT INTO `create` VALUES (1,201,2,'12:00:00','2020-10-16',5),(2,202,2,'12:00:00','2020-10-13',10),(9,209,12,'12:00:00','2020-10-19',30),(134,207,2,'20:40:00','2020-10-10',10),(135,201,12,'11:00:00','2020-10-08',10),(136,202,12,'12:46:00','2020-10-08',10),(137,203,42,'14:50:00','2020-10-08',25),(138,204,12,'14:50:00','2020-10-08',25),(139,201,2,'11:15:00','2020-10-10',10),(140,203,12,'11:16:00','2020-10-10',10),(141,205,40,'11:16:00','2020-10-10',10),(142,206,38,'11:17:00','2020-10-10',8),(143,204,39,'11:18:00','2020-10-10',10),(144,208,40,'07:18:00','2020-10-10',10),(145,209,41,'11:19:00','2020-10-07',100),(146,210,42,'11:19:00','2020-10-10',50),(147,201,2,'11:20:00','2020-10-11',10),(148,202,12,'11:21:00','2020-10-11',10),(149,203,37,'11:21:00','2020-10-11',25),(150,204,38,'11:21:00','2020-10-11',30),(151,205,39,'11:21:00','2020-10-11',10),(152,206,40,'11:22:00','2020-10-11',8),(153,207,41,'11:22:00','2020-10-11',10),(154,208,42,'11:22:00','2020-10-11',20),(155,209,2,'11:23:00','2020-10-12',100),(156,210,12,'11:23:00','2020-10-12',100),(157,201,37,'11:24:00','2020-10-12',10),(158,202,38,'11:24:00','2020-10-12',10),(159,203,39,'11:24:00','2020-10-12',25),(160,205,40,'11:24:00','2020-10-12',12),(161,206,41,'11:25:00','2020-10-12',8),(162,208,42,'11:27:00','2020-10-12',10),(163,202,2,'11:27:00','2020-10-14',10),(164,203,12,'11:28:00','2020-10-14',20),(165,204,37,'11:28:00','2020-10-14',30),(166,205,38,'11:28:00','2020-10-14',10),(167,206,39,'11:29:00','2020-10-07',8),(168,207,40,'11:29:00','2020-10-14',20),(169,208,41,'11:29:00','2020-10-14',20),(170,210,42,'11:29:00','2020-10-14',100),(171,201,2,'14:45:00','2020-10-07',10),(172,209,12,'14:14:00','2020-10-10',20),(173,208,2,'18:46:00','2020-10-10',20),(174,210,40,'18:15:00','2020-10-20',99),(175,205,38,'13:55:00','2020-10-27',5);
/*!40000 ALTER TABLE `create` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (201,'Bowling 1','Deck 8',10,60,'Athletic'),(202,'Bowling 2','Deck 8',10,60,'Athletic'),(203,'Movie 1','Deck 3',25,120,'Show'),(204,'Movie 2','Deck 3',50,120,'Show'),(205,'Extreme Zipline','Deck 15',15,30,'Athletic'),(206,'Skycourse Ropes','Deck 14',8,30,'Athletic'),(207,'Ice Skating','Deck 9',35,60,'Athletic'),(208,'Go Karting','Deck 12',20,60,'Athletic'),(209,'Broadway Show','Deck 5',100,120,'Show'),(210,'Planetarium','Deck 7',100,60,'Show');
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `reserve`
--

LOCK TABLES `reserve` WRITE;
/*!40000 ALTER TABLE `reserve` DISABLE KEYS */;
INSERT INTO `reserve` VALUES (5481,1,20),(8377,1,22),(8385,2,20),(8386,9,20),(8387,142,22),(8388,142,43),(8389,142,44),(8390,142,46),(8391,142,47),(8392,142,48),(8393,142,49),(8394,142,50),(8406,137,45),(8407,140,45),(8408,141,45),(8409,144,45),(8410,151,45),(8411,174,20),(8412,160,20),(8413,175,20);
/*!40000 ALTER TABLE `reserve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'manirudh95','Anirudh','Reddy','manager','mrushi95',120,'4693895980',12,'None','abc@gmail.com'),(2,'coordinator','neel','deo','coordinator','mrushi95',123,'4693896480',2,'none','Mrushi95@gmai.com'),(12,'mrushi95','Rushi','Madaka','coordinator','mrushi95',123,'3983839831',12,'none','Mrushi96@ghms.com'),(20,'saik95','Sam','Kumar','passenger','Mrushi95@',123,'9701721112',15,'premium','abc@gads.com'),(21,'ldsdd','anirudh','anirudh','passenger','Mrushi95@',123,'9999999999',12,'none','m@njsdc.com'),(22,'maniru','Asn','Mas','passenger','Mrushi95@',123,'4693896480',2,'none','Mrushi95@gmai.com'),(37,'coordinator5','coordinator_first5','coordinator_last5','coordinator','Mrushi95@',123,'4693896480',2,'none','Mrushi95@gmai.com'),(38,'coordinator2','coordinator_first2','coordinatorl_last2','coordinator','Mrushi95@',123,'4693896480',2,'none','Mrushi95@gmai.com'),(39,'coordinator3','coordinator_first3','coordinator_last3','coordinator','Mrushi95@',123,'4693896480',2,'none','Mrushi95@gmai.com'),(40,'coordinator4','coordinator_first4','coordinatorl_last4','coordinator','Mrushi95@',123,'4693896480',2,'none','Mrushi95@gmai.com'),(41,'coordinator1','coordinator_first1','coordinator_last1','coordinator','Mrushi95@',123,'4693896480',2,'none','Mrushi95@gmai.com'),(42,'coordinator6','coordinator_first6','coordinatorl_last6','coordinator','Mrushi95@',123,'4693896480',2,'none','Mrushi95@gmai.com'),(43,'shivam123','Shivam','Sareen','passenger','Mrushi95@',123,'0000000000',12,'premium','shivam123@gmail.com'),(44,'ajith123','Ajith','Ajith','passenger','Mrushi95@',199,'0000000000',1,'superior','ajith123@gmail.com'),(45,'manirudh96','Anirudh','Reddy','passenger','Mrushi95@',124,'9701372000',3,'standard','manirudh95@gmail.com'),(46,'abhishek123','Abhishek','Abhishek','passenger','Mrushi95@',101,'0000000000',3,'standard','abhishek123@gmail.com'),(47,'sandeep123','Sandeep','Reddy','passenger','Mrushi95@',123,'0000000000',1,'premium','sandeep123@gmail.com'),(48,'gokul95','Gokul','Reddy','passenger','Mrushi95@',145,'0000000000',2,'none','gokul95@gmail.com'),(49,'mrohith98','Rohith','Madaka','passenger','Mrushi95@',123,'0000000000',5,'none','mrohith95@gmail.com'),(50,'mukesh123','Mukesh','TheDon','passenger','Mrushi95@',124,'0000000000',2,'premium','mukesh123@gmail.com'),(51,'ozil10','Ozil','Muzet','passenger','Mrushi95@',169,'1020310203',7,'superior','ozil@arsenal.com');
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

-- Dump completed on 2020-10-22  9:05:41
