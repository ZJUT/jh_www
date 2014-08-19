-- MySQL dump 10.13  Distrib 5.1.41, for debian-linux-gnu (i486)
--
-- Host: localhost    Database: new_www
-- ------------------------------------------------------
-- Server version	5.1.41-3ubuntu12.10

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
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `eid` int(11) NOT NULL AUTO_INCREMENT,
  `econtent` text,
  `etime` bigint(19) DEFAULT NULL,
  `create_time` bigint(19) DEFAULT NULL,
  `ephoto_url` text,
  `etitle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`eid`)
) ENGINE=MyISAM AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (28,' 精弘1周年，好开心，好开心，太开心，太开心',1111111,1111111,'/dddd','精弘成立1周 '),(29,' 精弘2周年，好开心，好开心，太开心，太开心',2222222,2222222,'/dddd','精弘成立2周 '),(30,' 精弘3周年，好开心，好开心，太开心，太开心',3333333,3333333,'/dddd','精弘成立3周 '),(32,'                                                                                                                                                              精弘4周年，好开心，好开心，太开心，太开心',4444444,4444444,'/dddd','精弘成立4周年'),(33,'                                                                                                                                                              精弘5周年，好开心，好开心，太开心，太开心',5555555,5555555,'/dddd','精弘成立5周年'),(34,'                                                                                                                                                              精弘6周年，好开心，好开心，太开心，太开心',6666666,6666666,'/dddd','精弘成立6周年'),(35,'                                                                                                                                                              精弘7周年，好开心，好开心，太开心，太开心',7777777,7777777,'/dddd','精弘成立7周年'),(36,'                                                                                                                                                              精弘8周年，好开心，好开心，太开心，太开心',8888888,8888888,'/dddd','精弘成立8周年'),(37,'                                                                                                                                                              精弘9周年，好开心，好开心，太开心，太开心',9999999,9999999,'/dddd','精弘成立9周年'),(38,'                                                                                                                                                              精弘10周年，好开心，好开心，太开心，太开心',11111111,11111111,'/dddd','精弘成立10周年');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `navigator`
--

DROP TABLE IF EXISTS `navigator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `navigator` (
  `naid` int(11) NOT NULL AUTO_INCREMENT,
  `naname` varchar(10) NOT NULL,
  `destination_url` text,
  `weight` int(3) DEFAULT NULL,
  PRIMARY KEY (`naid`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `navigator`
--

LOCK TABLES `navigator` WRITE;
/*!40000 ALTER TABLE `navigator` DISABLE KEYS */;
INSERT INTO `navigator` VALUES (5,'去论论坛','http://bbs.zjut.com',1),(6,'去主页','http://www.zjut.com',1),(7,'去walk','http://walk.zjut.com',2),(8,'去feel电台','http://feel.zjut.com',4);
/*!40000 ALTER TABLE `navigator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice` (
  `nid` int(11) NOT NULL AUTO_INCREMENT,
  `ncontent` varchar(100) DEFAULT NULL,
  `destination_url` text,
  `create_time` bigint(19) DEFAULT NULL,
  `nphoto_url` text,
  PRIMARY KEY (`nid`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (8,'精弘招新了','http://join.zjut.com',111111111111,'/img/fds.jpg'),(9,'精弘煞笔了','http://bbs.zjut.com',111111111111,'/img/fds.jpg'),(10,'精弘毅行','http://walk.zjut.com',11111111111123,'/img/fds.jpg');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `stu_id` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) NOT NULL,
  `flag` int(1) DEFAULT NULL,
  PRIMARY KEY (`stu_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('201226630218','westion','westion717',1),('admin','admin','admin',1);
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

-- Dump completed on 2014-06-27 12:47:38
