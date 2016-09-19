-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: BOOKING
-- ------------------------------------------------------
-- Server version	5.6.31-log

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
-- Table structure for table `HOTELS`
--

DROP TABLE IF EXISTS `HOTELS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HOTELS` (
  `HOTEL_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOCATION_ID` bigint(20) NOT NULL,
  `HOTEL_NAME` varchar(45) NOT NULL,
  `HOTEL_STATUS` varchar(45) NOT NULL,
  PRIMARY KEY (`HOTEL_ID`),
  KEY `CITY_ID_idx` (`LOCATION_ID`),
  CONSTRAINT `HOTEL_CITY_KEY` FOREIGN KEY (`LOCATION_ID`) REFERENCES `LOCATIONS` (`LOCATION_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HOTELS`
--

LOCK TABLES `HOTELS` WRITE;
/*!40000 ALTER TABLE `HOTELS` DISABLE KEYS */;
INSERT INTO `HOTELS` VALUES (1,1,'Belarus Hotel','active'),(2,1,'U Fontana','active'),(3,2,'Gan van chi','active'),(4,3,'Poland ball','active');
/*!40000 ALTER TABLE `HOTELS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-19  2:00:01
