-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: park
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `cardinfo`
--

DROP TABLE IF EXISTS `cardinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cardinfo` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `telnum` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `cid` varchar(45) NOT NULL,
  `endtime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cardinfo`
--

LOCK TABLES `cardinfo` WRITE;
/*!40000 ALTER TABLE `cardinfo` DISABLE KEYS */;
INSERT INTO `cardinfo` VALUES (1,'甲','111','555','苏B-24147','2020-09-30 00:00:00'),(2,'乙','222','123','苏A-B8570','2020-09-26 00:00:00'),(3,'丙','333','123','苏D-8398学','2020-09-27 00:00:00'),(4,'丁','444','123','苏E-78733','2020-10-02 00:00:00');
/*!40000 ALTER TABLE `cardinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parkinginfo`
--

DROP TABLE IF EXISTS `parkinginfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parkinginfo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `carid` varchar(45) NOT NULL,
  `intime` datetime NOT NULL,
  `outtime` datetime DEFAULT NULL,
  `typeid` int DEFAULT NULL,
  `conditions` int DEFAULT NULL,
  `state` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parkinginfo`
--

LOCK TABLES `parkinginfo` WRITE;
/*!40000 ALTER TABLE `parkinginfo` DISABLE KEYS */;
INSERT INTO `parkinginfo` VALUES (1,'苏B-24147','2020-09-08 15:56:20','2020-09-10 14:28:20',1,1,1,100),(2,'苏A-B8570','2020-09-08 15:57:51','2020-09-08 15:57:04',2,0,1,20),(3,'苏D-8938学','2020-09-07 12:00:00','2020-09-09 11:59:59',1,1,1,100),(4,'苏A-00000','2020-09-09 00:47:17','2020-09-10 11:50:00',0,1,1,100),(5,'苏A-00001','2020-09-09 14:13:15','2020-09-09 19:20:43',3,0,1,500),(6,'苏F-08031','2020-09-10 00:00:00','2020-09-10 11:50:00',3,1,1,100),(7,'苏E-11011','2020-01-01 01:00:00','2020-09-09 15:14:16',2,0,1,121240),(8,'苏A-93210','2020-09-09 18:00:00','2020-09-09 23:00:00',3,0,1,500),(9,'222222222222','2020-09-09 16:58:09','2020-09-10 12:12:12',0,0,1,0),(10,'苏A122','2020-04-04 23:08:02','2023-03-04 23:04:08',0,0,1,0),(11,'testing','2020-09-01 18:52:01','2020-09-10 00:00:00',1,0,1,19700);
/*!40000 ALTER TABLE `parkinginfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `price`
--

DROP TABLE IF EXISTS `price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `price` (
  `typed_id` int NOT NULL,
  `typedname` varchar(45) NOT NULL,
  `perprice` int NOT NULL,
  PRIMARY KEY (`typed_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price`
--

LOCK TABLES `price` WRITE;
/*!40000 ALTER TABLE `price` DISABLE KEYS */;
INSERT INTO `price` VALUES (1,'lorry',100),(2,'suv',20),(3,'trunk',100),(4,'plane',300),(5,'train',1000),(6,'bicycle',10);
/*!40000 ALTER TABLE `price` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-09 23:33:56
