CREATE DATABASE  IF NOT EXISTS `fraternity` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fraternity`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: fraternity
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `member_id` int DEFAULT NULL,
  `amount_due` float DEFAULT NULL,
  `amount_initial` float NOT NULL,
  `payment_date` date DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `payment_member_fk_idx` (`member_id`),
  CONSTRAINT `payment_member_fk` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (15,17,50,50,'2024-01-01',"New Member Dues"),
                              (16,18,50,50,'2024-01-01',"New Member Dues"),
                              (17,19,50,50,'2024-01-01',"New Member Dues"),
                              (18,20,50,50,'2024-01-01',"New Member Dues"),
                              (19,21,50,50,'2024-01-01',"New Member Dues"),
                              (20,22,50,50,'2024-01-01',"New Member Dues"),
                              (21,23,50,50,'2024-01-01',"New Member Dues"),
                              (22,24,50,50,'2024-01-01',"New Member Dues"),
                              (23,25,50,50,'2024-01-01',"New Member Dues"),
                              (24,26,50,50,'2024-01-01',"New Member Dues"),
                              (25,27,50,50,'2024-01-01',"New Member Dues"),
                              (26,28,50,50,'2024-01-01',"New Member Dues"),
                              (27,29,50,50,'2024-01-01',"New Member Dues"),
                              (28,30,50,50,'2024-01-01',"New Member Dues"),
                              (29,31,50,50,'2024-01-01',"New Member Dues"),
                              (30,32,50,50,'2024-01-01',"New Member Dues"),
                              (31,33,50,50,'2024-01-01',"New Member Dues"),
                              (32,34,50,50,'2024-01-01',"New Member Dues"),
                              (33,35,50,50,'2024-01-01',"New Member Dues"),
                              (34,36,50,50,'2024-01-01',"New Member Dues"),
                              (35,37,50,50,'2024-01-01',"New Member Dues"),
                              (36,38,50,50,'2024-01-01',"New Member Dues"),
                              (37,39,50,50,'2024-01-01',"New Member Dues"),
                              (38,40,50,50,'2024-01-01',"New Member Dues"),
                              (39,41,50,50,'2024-01-01',"New Member Dues"),
                              (40,42,50,50,'2024-01-01',"New Member Dues"),
                              (41,43,50,50,'2024-01-01',"New Member Dues"),
                              (42,44,50,50,'2024-01-01',"New Member Dues"),
                              (43,45,50,50,'2024-01-01',"New Member Dues"),
                              (44,46,50,50,'2024-01-01',"New Member Dues");
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-04 19:10:40
