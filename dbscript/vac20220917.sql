-- MySQL dump 10.13  Distrib 8.0.20, for macos10.15 (x86_64)
--
-- Host: localhost    Database: vac
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch` (
  `CompCode` varchar(50) NOT NULL,
  `BranchCode` varchar(50) NOT NULL,
  `NameTh` varchar(100) DEFAULT NULL,
  `NameEn` varchar(100) DEFAULT NULL,
  `Addr1` varchar(100) DEFAULT NULL,
  `Addr2` varchar(100) DEFAULT NULL,
  `Addr3` varchar(50) DEFAULT NULL,
  `Zip` varchar(50) DEFAULT NULL,
  `Tel` varchar(50) DEFAULT NULL,
  `Fax` varchar(50) DEFAULT NULL,
  `WebSite` varchar(100) DEFAULT NULL,
  `RegId` varchar(50) DEFAULT NULL,
  `RegDate` varchar(10) DEFAULT NULL,
  `VatRate` double DEFAULT NULL,
  `CreateBy` varchar(50) DEFAULT NULL,
  `CreateDate` varchar(10) DEFAULT NULL,
  `CreateTime` varchar(10) DEFAULT NULL,
  `UpdateBy` varchar(50) DEFAULT NULL,
  `UpdateDate` varchar(10) DEFAULT NULL,
  `UpdateTime` varchar(10) DEFAULT NULL,
  `Version` int DEFAULT NULL,
  PRIMARY KEY (`CompCode`,`BranchCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES ('VAC','000','สำนักงานใหญ่','Head office','11111','','','','','','','','',0,NULL,NULL,NULL,NULL,'2022-03-15','14:19:25',NULL);
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `CompCode` varchar(50) NOT NULL,
  `NameTh` varchar(100) DEFAULT NULL,
  `NameEn` varchar(100) DEFAULT NULL,
  `Addr1` varchar(100) DEFAULT NULL,
  `Addr2` varchar(100) DEFAULT NULL,
  `Addr3` varchar(100) DEFAULT NULL,
  `Zip` varchar(50) DEFAULT NULL,
  `Tel` varchar(50) DEFAULT NULL,
  `Fax` varchar(50) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `WebSite` varchar(50) DEFAULT NULL,
  `RegId` varchar(50) DEFAULT NULL,
  `RegDate` varchar(10) DEFAULT NULL,
  `VatRate` double DEFAULT NULL,
  `TodayDate` varchar(10) DEFAULT NULL,
  `UseSystemDate` varchar(1) DEFAULT NULL,
  `UpdateBy` varchar(50) DEFAULT NULL,
  `UpdateDate` varchar(10) DEFAULT NULL,
  `UpdateTime` varchar(10) DEFAULT NULL,
  `Version` int DEFAULT NULL,
  PRIMARY KEY (`CompCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES ('VAC','บริษัท ทดสอบ','Test Company','1111111','xxxxxxxx','','','','','','','','',7,'2020-12-25','N','','2022-03-15','10:37:29',0);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_data`
--

DROP TABLE IF EXISTS `user_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_data` (
  `UserId` varchar(50) NOT NULL,
  `UserFname` varchar(100) DEFAULT NULL,
  `UserLname` varchar(100) DEFAULT NULL,
  `Password` varchar(100) DEFAULT NULL,
  `CompCode` varchar(50) DEFAULT NULL,
  `BranchCode` varchar(50) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Phone1` varchar(50) DEFAULT NULL,
  `Phone2` varchar(50) DEFAULT NULL,
  `Version` int DEFAULT NULL,
  PRIMARY KEY (`UserId`),
  UNIQUE KEY `UserId_UNIQUE` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_data`
--

LOCK TABLES `user_data` WRITE;
/*!40000 ALTER TABLE `user_data` DISABLE KEYS */;
INSERT INTO `user_data` VALUES ('Admin','Administrator','Test','$2a$10$wdC3KOnQCbBtVmbei6noeOGcApTrNJt/WeprzmwwpSfCWr0DFz7vy','VAC','000','11111111','1111111111','2222222222',0),('Admin1','Admin01','xxxxxxxxxxx','$2a$10$wdC3KOnQCbBtVmbei6noeOGcApTrNJt/WeprzmwwpSfCWr0DFz7vy','VAC','000','','11111111','',0);
/*!40000 ALTER TABLE `user_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userrole` (
  `UserId` varchar(50) NOT NULL,
  `Role` varchar(50) NOT NULL,
  PRIMARY KEY (`UserId`,`Role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` VALUES ('Admin','Administrator'),('Admin','Security_Admin'),('Admin1','Security_Admin');
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-17 15:14:34
