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
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `chapter_id` int DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  KEY `member_chapter_fk_idx` (`chapter_id`),
  CONSTRAINT `member_chapter_fk` FOREIGN KEY (`chapter_id`) REFERENCES `chapter` (`chapter_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ((17,"Will","Cox","willcox@gmail.com","346-976-8453","2000-09-25","Active",19),
                             (18,"Chase","Hammond","chasehammond@gmail.com","645-345-9761","2001-11-13","Active",19),
                             (19,"Randall","Boone","randallboone@gmail.com","946-873-1346","2001-07-26","Active",19),
                             (20,"Michael","Hernandez","michaelhernandez@gmail.com","346-516-9462","2003-05-16","Active",19),
                             (21,"Tony","Thomas","tonythomas@gmail.com","645-913-6485","2004-06-01","Active",19),
                             (22,"Jeffery","Munoz","jefferymunoz@gmail.com","346-578-2164","2004-04-25","Active",19),
                             (23,"Brad","Norris","bradnorris@gmail.com","346-678-9425","2002-10-24","Active",19),
                             (24,"Travis","Tucker","travistucker@gmail.com","316-946-5825","2003-09-25","Active",19),
                             (25,"Andrew","Brown","andrewbrown@gmail.com","346-761-1234","2003-06-09","Active",19),
                             (26,"Greg","Carrol","gregcarrol@gmail.com","124-654-9723","2003-12-15","Active",19),
                             (27,"Edwin","Thomas","edwinthomas@gmail.com","312-695-6758","2004-08-25","Active",20),
                             (28,"Daniel","Rice","danielrice@gmail.com","346-125-6485","2004-05-10","Active",20),
                             (29,"Scott","Phelps","scottphelps@gmail.com","405-605-9365","2003-10-30","Active",20),
                             (30,"Samuel","Reid","samuelreid@gmail.com","602-698-3021","2001-06-11","Active",20),
                             (31,"Matthew","Floyd","matthewfloyd@gmail.com","648-730-9630","2003-01-10","Active",20),
                             (32,"Chris","Sullivan","chrissullivan@gmail.com","203-604-5431","2004-10-13","Active",20),
                             (33,"Daniel","Anderson","danielanderson@gmail.com","301-946-5312","2003-06-08","Active",20),
                             (34,"John","Waters","johnwaters@gmail.com","316-648-5312","2004-05-02","Active",20),
                             (35,"Gary","White","garywhite@gmail.com","648-796-9431","2004-09-22","Active",20),
                             (36,"James","Villa","jamesvilla@gmail.com","346-655-9463","2001-09-25","Active",20),
                             (37,"David","Reed","davidreed@gmail.com","642-757-6416","2002-10-31","Active",21),
                             (38,"Jacob","Allen","jacoballen@gmail.com","312-645-7986","2003-10-22","Active",21),
                             (39,"Jordan","Hobbs","jordanhobbs@gmail.com","336-905-6123","2001-09-13",'Active',21),
                             (40,'Thomas','King','thomasking@gmail.com','648-973-9731','2001-08-26','Active',21),
                             (41,'Steven','Morrison','stevenmorrison@gmail.com','124-453-8164','2002-09-26','Active',21),
                             (42,'Christopher','King','christopherking@gmail.com','164-875-4513','2005-01-08','Active',21),
                             (43,'Craig','Palmer','craigpalmer@gmail.com','302-648-9130','2001-06-18','Active',21),
                             (44,'Ryan','Thompson','ryanthompson@gmail.com','378-918-8463','2003-06-27','Active',21),
                             (45,'Hector','Holmes','hectorholmes@gmail.com','648-815-9433','2004-12-01','Active',21),
                             (46,'Michael','Green','michaelgreen@gmail.com','405-609-8463','2003-02-10','Active',21));
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `member_AFTER_INSERT` AFTER INSERT ON `member` FOR EACH ROW BEGIN
	INSERT INTO payment(member_id, amount_due, amount_initial, payment_date, description) VALUES(new.member_id, 50.0, 50.0, "2024-01-01", "New Member Dues");
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-04 19:10:40
