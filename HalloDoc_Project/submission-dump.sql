CREATE DATABASE  IF NOT EXISTS `hallodoc` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hallodoc`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: hallodoc
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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `aspnet_userid` int NOT NULL,
  `first_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `last_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `address_one` varchar(500) DEFAULT NULL,
  `address_two` varchar(255) DEFAULT NULL,
  `city` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `region_id` int DEFAULT NULL,
  `zip` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `alt_phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `created_by` int NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` int DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`admin_id`),
  KEY `aspnet_userid` (`aspnet_userid`),
  KEY `created_by` (`created_by`),
  KEY `modified_by` (`modified_by`),
  KEY `region_id_idx` (`region_id`),
  KEY `role_id_idx` (`role_id`),
  CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`aspnet_userid`) REFERENCES `asp_net_users` (`id`),
  CONSTRAINT `admin_ibfk_2` FOREIGN KEY (`created_by`) REFERENCES `asp_net_users` (`id`),
  CONSTRAINT `admin_ibfk_3` FOREIGN KEY (`modified_by`) REFERENCES `asp_net_users` (`id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (10,29,'Kavit','Shah','13, Kusum Kunj','Mahavir Jain Society','Godhra',1,'389001','9909764648',29,'2024-04-03 16:22:28',29,NULL,0,_binary '\0',NULL);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_region`
--

DROP TABLE IF EXISTS `admin_region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_region` (
  `admin_region_id` int NOT NULL AUTO_INCREMENT,
  `admin_id` int NOT NULL,
  `region_id` int NOT NULL,
  PRIMARY KEY (`admin_region_id`),
  KEY `admin_id` (`admin_id`),
  KEY `region_id` (`region_id`),
  CONSTRAINT `admin_region_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`),
  CONSTRAINT `admin_region_ibfk_2` FOREIGN KEY (`region_id`) REFERENCES `region` (`region_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_region`
--

LOCK TABLES `admin_region` WRITE;
/*!40000 ALTER TABLE `admin_region` DISABLE KEYS */;
INSERT INTO `admin_region` VALUES (5,10,1),(6,10,2),(7,10,4);
/*!40000 ALTER TABLE `admin_region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asp_net_roles`
--

DROP TABLE IF EXISTS `asp_net_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asp_net_roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asp_net_roles`
--

LOCK TABLES `asp_net_roles` WRITE;
/*!40000 ALTER TABLE `asp_net_roles` DISABLE KEYS */;
INSERT INTO `asp_net_roles` VALUES (1,'Admin'),(2,'Provider'),(3,'Patient');
/*!40000 ALTER TABLE `asp_net_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asp_net_user_roles`
--

DROP TABLE IF EXISTS `asp_net_user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asp_net_user_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `asp_net_roles_user_roles_idx` (`role_id`),
  CONSTRAINT `asp_net_roles_user_roles` FOREIGN KEY (`role_id`) REFERENCES `asp_net_roles` (`id`),
  CONSTRAINT `asp_net_user_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `asp_net_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asp_net_user_roles`
--

LOCK TABLES `asp_net_user_roles` WRITE;
/*!40000 ALTER TABLE `asp_net_user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `asp_net_user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asp_net_users`
--

DROP TABLE IF EXISTS `asp_net_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asp_net_users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password_hash` varchar(256) DEFAULT NULL,
  `email` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `phone_number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `user_name` varchar(256) NOT NULL,
  `created_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=778 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asp_net_users`
--

LOCK TABLES `asp_net_users` WRITE;
/*!40000 ALTER TABLE `asp_net_users` DISABLE KEYS */;
INSERT INTO `asp_net_users` VALUES (29,'$2b$12$L8iZ/lJzDgPF7c8gDjPMuOhhkIgJUnaFZtTqXvcoYd8eqbUI3Lat6','admin@gmail.com','8855449631',NULL,'KavitShah29','2024-04-03 16:22:28'),(421,'$2b$12$ssTkf6jIV1YXbCXaMaWlC.FN07Rja8dXtnnNdozWkdcqBnlBewjhy','miyohop516@acentni.com','+91 1234567890',NULL,'miyohop516@acentni.com','2024-04-03 14:10:01'),(427,'$2b$12$A.NuUWqFq5lOwKLWpGaTV.Ud7jovyKnWO.QilIXWUXtOaAbeNdPpi','monibay451@centerf.com','7894561230','2024-04-03 14:25:54','monibay451@centerf.com','2024-04-03 14:16:45'),(439,NULL,'jihoh48005@centerf.com','1747474747','2024-04-03 14:48:33','jihoh48005@centerf.com','2024-04-03 14:33:41'),(453,NULL,'asda@gmail.com','7485963210',NULL,'asda@gmail.com','2024-04-03 16:14:12'),(461,NULL,'asdsadasdasd@gmail.com','7485963210',NULL,'asdsadasdasd@gmail.com','2024-04-03 16:15:56'),(468,NULL,'asdsadasdasdasdad@gmail.com','7485963210',NULL,'asdsadasdasdasdad@gmail.com','2024-04-03 16:20:07'),(476,NULL,'qwerasjhjh@gmail.com','7845129631',NULL,'qwerasjhjh@gmail.com','2024-04-03 16:22:28'),(483,'$2b$12$3Bzb4sFslsK44nnoqkCxCORcBwZROHBNoZSC9YHFZNA/cKIHslW.a','asdascas@gmail.com','7845129631',NULL,'asdascas@gmail.com','2024-04-04 12:50:04'),(500,'$2b$12$07WawKFz25XPG1wUiUxKRuTaLcPvk4f.s76pMlE0Bd8jAUe8ZEz.q','gaxal93643@adstam.com','7845121985','2024-04-10 10:16:27','gaxal93643@adstam.com','2024-04-10 10:13:58'),(508,'$2b$12$xEbX3pq3pTfloOySbjOgQOjFxJNiG1hmrLJZy3/5vS8855L5t2PzG','lovidi4112@agromgt.com','7539518462','2024-04-10 10:17:42','lovidi4112@agromgt.com','2024-04-10 10:17:13'),(516,'$2b$12$hd1XrnE8IuFNkHS.QDvUoeWALmMz8jQabt3hn0Syr.pTKCsF8wEzi','rokosi4878@acentni.com','7845121245','2024-04-10 10:22:07','rokosi4878@acentni.com','2024-04-10 10:21:35'),(524,NULL,'xores83799@adstam.com','8659742310',NULL,'xores83799@adstam.com','2024-04-10 10:26:07'),(531,'$2b$12$N1gnkjDv872xOYxH5/2JROPe6oZeffZ8sPRchJYv9cxvF8ymYrUSK','bikiyo1901@acentni.com','','2024-04-10 10:29:47','bikiyo1901@acentni.com','2024-04-10 10:29:15'),(538,'$2b$12$B6mu3L1VNiYTgpPcesSWKuOTD0LqOC2k2UB/2KO18oDpZ4hjRMYJ2','royevef681@abnovel.com','7894561230','2024-04-11 19:03:14','royevef681@abnovel.com','2024-04-11 18:52:55'),(544,NULL,'vasel27162@agaseo.com','7896541230','2024-04-19 11:06:44','vasel27162@agaseo.com','2024-04-11 19:07:22'),(554,NULL,'kgpatel12@gmail.com','',NULL,'kgpatel12@gmail.com','2024-04-12 11:50:10'),(556,'$2b$12$A.NuUWqFq5lOwKLWpGaTV.Ud7jovyKnWO.QilIXWUXtOaAbeNdPpi','kavit15@gmail.com','6351627219','2024-04-17 19:22:19','kavit15','2024-04-17 19:22:19'),(567,'$2b$12$YdqJ8Xh/NbrmtXCUlxhJeuePIYUGQn/1Dg9uSHKPAgPTbrrHSNJMe','krutarth21@gmail.com','8849430122','2024-04-17 19:22:19','krutarth21@gmail.com','2024-04-17 10:05:23'),(569,'$2b$12$/.dTVw37av6k33iIhcwMyu58xp.YARVftZMYHfCCrSGrJvazlGs8K','jemis69@gmail.com','8529637410','2024-05-01 11:42:50','jemis69@gmail.com','2024-04-17 10:11:38'),(571,'$2b$12$Npjv9AxXYb/251Ia8BOLpOI/Kup.TpO66uuDLQWDzYczaXB8LDpL2','chandan03@gmail.com','7845128956',NULL,'chandan03@gmail.com','2024-04-17 10:19:51'),(608,'$2b$12$7FMxuPnoxwakUBfcvBoKHuiLoXoiNSd4NyRa4ZN9JNuU/EgowQDpm','yegim25818@acname.com','','2024-04-19 15:59:21','yegim25818@acname.com','2024-04-19 15:53:42'),(618,NULL,'tejoric652@acname.com','',NULL,'tejoric652@acname.com','2024-04-19 16:21:33'),(631,NULL,'decero4798@eryod.com','7845129630',NULL,'decero4798@eryod.com','2024-04-19 16:55:38'),(715,'$2b$12$tuUC.Q9oOcK3MvTjJhkxj.QYc6XPXTANOrDiHhFRHUrmaUBq8wnHe','derekshah324@gmail.com','7894561231','2024-04-27 17:28:36','DerekShah29','2024-04-26 19:39:26'),(738,'$2b$12$/Sim/9R1EbbGPx60z9.Xoe5ZcyUTJyZRmyuLt0uiXsfHEp0zF1cpG','aashiv73@gmail.com','7894561230','2024-05-01 11:03:34','Aashiv73','2024-05-01 11:03:34'),(744,'$2b$12$cLwngr.A9h7w25qkJmqhOucI9fLdBjl2q1xOX5u/YHALpJNyO5iUG','shahsamyak@gmail.com','123','2024-05-01 11:10:30','Samyak07','2024-05-01 11:10:30'),(750,NULL,'asdjau@gmail.com','7894561230',NULL,'asdjau@gmail.com','2024-05-01 11:28:09'),(756,NULL,'samyakk@gmail.com','7894561230',NULL,'samyakk@gmail.com','2024-05-01 11:29:48'),(762,NULL,'derek@gmail.in','7894561230',NULL,'derek@gmail.in','2024-05-01 11:31:53'),(777,NULL,'ascnsjn@gmail.com','7894561230',NULL,'ascnsjn@gmail.com','2024-05-01 11:52:43');
/*!40000 ALTER TABLE `asp_net_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `block_requests`
--

DROP TABLE IF EXISTS `block_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `block_requests` (
  `block_request_id` int NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `reason` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `request_id` int NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`block_request_id`),
  KEY `request_id_idx` (`request_id`),
  CONSTRAINT `FKnpvr62jied9w4ro7umeks6wul` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=566 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `block_requests`
--

LOCK TABLES `block_requests` WRITE;
/*!40000 ALTER TABLE `block_requests` DISABLE KEYS */;
INSERT INTO `block_requests` VALUES (563,'7894561230','bikiyo1901@acentni.com',_binary '','Not Appropriate',533,'2024-04-15 15:57:49','2024-04-29 11:22:11'),(565,'7845129631','qwerasjhjh@gmail.com',_binary '\0','bas maan kiya',478,'2024-04-15 16:01:34','2024-04-15 16:01:34');
/*!40000 ALTER TABLE `block_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business`
--

DROP TABLE IF EXISTS `business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business` (
  `business_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `address_one` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `address_two` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `city` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `region_id` int DEFAULT NULL,
  `zip_code` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `phone_number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `fax_number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` int DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `property_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`business_id`),
  KEY `region_id` (`region_id`),
  CONSTRAINT `business_ibfk_3` FOREIGN KEY (`region_id`) REFERENCES `region` (`region_id`)
) ENGINE=InnoDB AUTO_INCREMENT=777 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business`
--

LOCK TABLES `business` WRITE;
/*!40000 ALTER TABLE `business` DISABLE KEYS */;
INSERT INTO `business` VALUES (460,'sadsad adasd',NULL,NULL,NULL,1,NULL,'1234567890',NULL,0,'2024-04-03 16:15:56',0,'2024-04-03 16:20:07',_binary '\0','adasd@gmail.com','Grisha'),(475,'qwertyu asdfghj',NULL,NULL,NULL,1,NULL,'1234567890',NULL,0,'2024-04-03 16:22:28',0,NULL,_binary '\0','qwertyuio@gmail.com','sdfgh'),(515,'Ishan  Bhatt',NULL,NULL,NULL,4,NULL,'7894568598',NULL,0,'2024-04-10 10:21:35',0,NULL,_binary '\0','iibhatt@gmail.com','Avni Hills'),(523,'Thor Odinson',NULL,NULL,NULL,3,NULL,'7895632450',NULL,0,'2024-04-10 10:26:07',0,NULL,_binary '\0','thor@gmail.com','Asguard'),(776,'Kavit Shah',NULL,NULL,NULL,1,NULL,'1234567890',NULL,0,'2024-05-01 11:52:43',0,NULL,_binary '\0','kavitshah32124@gmail.com','Suba Star');
/*!40000 ALTER TABLE `business` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `case_tag`
--

DROP TABLE IF EXISTS `case_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `case_tag` (
  `case_tag_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  PRIMARY KEY (`case_tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `case_tag`
--

LOCK TABLES `case_tag` WRITE;
/*!40000 ALTER TABLE `case_tag` DISABLE KEYS */;
INSERT INTO `case_tag` VALUES (1,'No Respone to call or text, left message'),(2,'Cost Issue'),(3,'Insurance Issue'),(4,'Out of Service Area'),(5,'Not appropriate for service'),(6,'Referral to Clinic or Hospital');
/*!40000 ALTER TABLE `case_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `concierge`
--

DROP TABLE IF EXISTS `concierge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `concierge` (
  `concierge_id` int NOT NULL AUTO_INCREMENT,
  `concierge_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `address` varchar(150) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `street` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `city` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `state` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `zip_code` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `created_date` datetime NOT NULL,
  `region_id` int NOT NULL,
  `role_id` int DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`concierge_id`),
  KEY `region_id` (`region_id`),
  KEY `role_id_idx` (`role_id`),
  CONSTRAINT `concierge_ibfk_1` FOREIGN KEY (`region_id`) REFERENCES `region` (`region_id`)
) ENGINE=InnoDB AUTO_INCREMENT=508 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concierge`
--

LOCK TABLES `concierge` WRITE;
/*!40000 ALTER TABLE `concierge` DISABLE KEYS */;
INSERT INTO `concierge` VALUES (438,'Denis Richhe',NULL,'Judges Bunglow Road','Ahmedabad','Gujarat','389001','2024-04-03 14:33:41',1,0,'dennis@gmail.com'),(446,'Night Manager 1234',NULL,'Judges Bunglow','Ahmedabad','Gujarat','389001','2024-04-03 14:48:33',1,0,'nightmanager@gmail.com'),(452,'asdasc zczxczxc',NULL,'asdsad','sada','Gujarat','389001','2024-04-03 16:14:12',1,0,'asds@gmail.com'),(507,'Servenus Snape',NULL,'Lower Parole','Mumbai','Maharashtra','388120','2024-04-10 10:17:13',2,0,'snape@potterhead.com');
/*!40000 ALTER TABLE `concierge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_log`
--

DROP TABLE IF EXISTS `email_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `email_log` (
  `email_log_id` int NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `email_id` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `confirmation_number` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `request_id` int DEFAULT NULL,
  `admin_id` int DEFAULT NULL,
  `physician_id` int DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `sent_date` datetime DEFAULT NULL,
  `is_email_sent` bit(1) DEFAULT NULL,
  `sent_tries` int DEFAULT NULL,
  `action` varchar(256) DEFAULT NULL,
  `recipient_name` varchar(255) DEFAULT NULL,
  `email_type` varchar(255) DEFAULT NULL,
  `is_expired` bit(1) DEFAULT NULL,
  PRIMARY KEY (`email_log_id`),
  KEY `role_id_idx` (`role_id`),
  KEY `physician_id_idx` (`physician_id`),
  KEY `admin_id_idx` (`admin_id`),
  KEY `request_id_idx` (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=776 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_log`
--

LOCK TABLES `email_log` WRITE;
/*!40000 ALTER TABLE `email_log` DISABLE KEYS */;
INSERT INTO `email_log` VALUES (491,'Create New Request Page Link','jihoh48005@centerf.com',NULL,3,0,0,0,'2024-04-09 18:45:27','2024-04-09 18:45:27',_binary '',1,'1','Derek Shah','1',_binary '\0'),(492,'Create New Request Page Link','xobowis371@centerf.com',NULL,3,0,0,0,'2024-04-09 18:49:28','2024-04-09 18:49:28',_binary '\0',3,'1','Failed Attempt','1',_binary '\0'),(493,'Create New Request Page Link','aksdkasd@gmail.com',NULL,3,0,0,0,'2024-04-09 19:01:28','2024-04-09 19:01:28',_binary '',1,'1','asdsad asdasdasd','1',_binary '\0'),(494,'Create New Request Page Link','nadjsbdj@gmail.com',NULL,3,0,0,0,'2024-04-10 09:34:15','2024-04-10 09:34:15',_binary '',1,'1','adsa dsdad','1',_binary '\0'),(495,'Create New Request Page Link','asdsad@gmail.com',NULL,3,0,0,0,'2024-04-10 09:34:55','2024-04-10 09:34:55',_binary '',1,'1','assdasasd asdsadsa','1',_binary '\0'),(496,'Create New Request Page Link','xobowis371@centerf.com',NULL,3,0,0,0,'2024-04-10 09:35:42','2024-04-10 09:35:42',_binary '',1,'1','ff ffaa','1',_binary '\0'),(497,'Create New Request Page Link','xobowis371@centerf.com',NULL,3,0,0,0,'2024-04-10 09:36:20','2024-04-10 09:36:20',_binary '',1,'1','sdfdsf sdfsdfdsf','1',_binary '\0'),(498,'Create New Request Page Link','xobowis371@centerf.com',NULL,3,0,0,0,'2024-04-10 09:39:10','2024-04-10 09:39:10',_binary '',1,'1','Derek Shah','1',_binary '\0'),(499,'Create New Request Page Link','xobowis371@centerf.com',NULL,3,0,0,0,'2024-04-10 09:40:15','2024-04-10 09:40:15',_binary '',1,'1','1234 78789','1',_binary '\0'),(585,'Agreement Link','monibay451@centerf.com','GJ030424WORK0002',3,434,10,0,'2024-04-19 11:04:16','2024-04-19 11:04:16',_binary '',1,'2','RKO Wood',NULL,_binary '\0'),(586,'Agreement Link','monibay451@centerf.com','GJ030424WORK0002',3,434,10,0,'2024-04-19 11:04:18','2024-04-19 11:04:18',_binary '',1,'2','RKO Wood',NULL,_binary '\0'),(593,'Agreement Link','vasel27162@agaseo.com','GJ190424JOBR0000',3,587,10,0,'2024-04-19 11:10:13','2024-04-19 11:10:13',_binary '',1,'2','Brinda Joshi',NULL,_binary '\0'),(594,'Agreement Link','vasel27162@agaseo.com','GJ190424JOBR0000',3,587,10,0,'2024-04-19 11:15:31','2024-04-19 11:15:31',_binary '',1,'2','Brinda Joshi',NULL,_binary '\0'),(595,'Agreement Link','vasel27162@agaseo.com','GJ190424JOBR0000',3,587,10,0,'2024-04-19 11:17:21','2024-04-19 11:17:21',_binary '',1,'2','Brinda Joshi',NULL,_binary '\0'),(596,'Agreement Link','vasel27162@agaseo.com','GJ190424JOBR0000',3,587,10,0,'2024-04-19 11:22:28','2024-04-19 11:22:28',_binary '',1,'2','Brinda Joshi',NULL,_binary '\0'),(597,'Agreement Link','vasel27162@agaseo.com','GJ190424JOBR0000',3,587,10,0,'2024-04-19 11:46:38','2024-04-19 11:46:38',_binary '',1,'2','Brinda Joshi',NULL,_binary '\0'),(598,'Agreement Link','vasel27162@agaseo.com','GJ190424JOBR0000',3,587,10,0,'2024-04-19 11:46:40','2024-04-19 11:46:40',_binary '',1,'2','Brinda Joshi',NULL,_binary '\0'),(600,'Agreement Link','monibay451@centerf.com','GJ030424WORK0002',3,434,10,0,'2024-04-19 12:13:50','2024-04-19 12:13:50',_binary '',1,'2','RKO Wood',NULL,_binary '\0'),(602,'Agreement Link','monibay451@centerf.com','GJ030424WORK0002',3,434,10,0,'2024-04-19 12:35:50','2024-04-19 12:35:50',_binary '',1,'2','RKO Wood',NULL,_binary '\0'),(604,'Agreement Link','monibay451@centerf.com','GJ030424WORK0002',3,434,10,0,'2024-04-19 12:40:18','2024-04-19 12:40:18',_binary '',1,'2','RKO Wood',NULL,_binary '\0'),(606,'Agreement Link','monibay451@centerf.com','GJ030424WORK0002',3,434,10,0,'2024-04-19 14:27:42','2024-04-19 14:27:42',_binary '',1,'2','RKO Wood',NULL,_binary '\0'),(615,'Agreement Link','yegim25818@acname.com','GJ190424SHBH0001',3,610,10,0,'2024-04-19 15:59:00','2024-04-19 15:59:00',_binary '',1,'2','Bhavyaa Shah',NULL,_binary '\0'),(624,'Agreement Link','tejoric652@acname.com','GJ190424CHCH0002',3,620,10,0,'2024-04-19 16:27:49','2024-04-19 16:27:49',_binary '',1,'2','Chandan Chabaiyaa',NULL,_binary '\0'),(637,'Agreement Link','decero4798@eryod.com','GJ190424GUCH0003',3,633,10,0,'2024-04-19 16:57:11','2024-04-19 16:57:11',_binary '',1,'2','Chirag Gundariya',NULL,_binary '\0'),(640,'Agreement Link','vasel27162@agaseo.com','GJ190424JOBR0000',3,587,10,0,'2024-04-19 17:06:07','2024-04-19 17:06:07',_binary '',1,'2','Brinda Joshi',NULL,_binary '\0'),(642,'Agreement Link','monibay451@centerf.com','GJ030424WORK0002',3,434,10,0,'2024-04-19 17:08:55','2024-04-19 17:08:55',_binary '',1,'2','RKO Wood',NULL,_binary '\0'),(644,'Agreement Link','monibay451@centerf.com','GJ030424WORK0002',3,434,10,0,'2024-04-19 17:14:14','2024-04-19 17:14:14',_binary '',1,'2','RKO Wood',NULL,_binary '\0'),(645,'Agreement Link','vasel27162@agaseo.com','GJ190424JOBR0000',3,587,10,0,'2024-04-22 09:35:14','2024-04-22 09:35:14',_binary '',1,'2','Brinda Joshi',NULL,_binary '\0'),(703,'Health Reports/ Documents','monibay451@centerf.com','GJ030424WORK0002',3,434,10,1,'2024-04-22 19:34:30','2024-04-22 19:34:30',_binary '',1,'3','RKO Wood',NULL,_binary '\0'),(707,'New Order Details','asdascas@gmail.com','GJ040424CASA0000',4,485,10,0,'2024-04-23 15:58:37','2024-04-23 15:58:37',_binary '',1,'4','Apollo Pharmacy',NULL,_binary '\0'),(718,'Reset Credentials','derekshah324@gmail.com',NULL,2,0,10,716,'2024-04-27 15:23:43','2024-04-27 15:23:43',_binary '',1,'5','Derek Shah',NULL,_binary '\0'),(719,'Reset Credentials','derekshah324@gmail.com',NULL,2,0,10,716,'2024-04-27 15:23:43','2024-04-27 15:23:43',_binary '',1,'5','Derek Shah',NULL,_binary '\0'),(720,'Reset Credentials','derekshah324@gmail.com',NULL,2,0,10,716,'2024-04-27 15:27:56','2024-04-27 15:27:56',_binary '',1,'5','Derek Shah',NULL,_binary '\0'),(721,'Reset Credentials','derekshah324@gmail.com',NULL,2,0,10,716,'2024-04-27 15:27:56','2024-04-27 15:27:56',_binary '',1,'5','Derek Shah',NULL,_binary '\0'),(722,'Reset Credentials','derekshah324@gmail.com',NULL,2,0,10,716,'2024-04-27 15:31:48','2024-04-27 15:31:48',_binary '',1,'5','Derek Shah',NULL,_binary '\0'),(723,'Reset Credentials','derekshah324@gmail.com',NULL,2,0,10,716,'2024-04-27 15:31:48','2024-04-27 15:31:48',_binary '',1,'5','Derek Shah',NULL,_binary '\0'),(724,'Communication Email','kavit15@gmail.com',NULL,2,0,10,1,'2024-04-28 00:20:23','2024-04-28 00:20:23',_binary '\0',3,'6','Kavit Shah',NULL,_binary '\0'),(725,'Communication Email','kavit15@gmail.com',NULL,2,0,10,1,'2024-04-28 00:20:23','2024-04-28 00:20:23',_binary '\0',3,'6','Kavit Shah',NULL,_binary '\0'),(727,'Communication Email','kavit15@gmail.com',NULL,2,0,10,1,'2024-04-28 00:26:06','2024-04-28 00:26:06',_binary '',1,'6','Kavit Shah',NULL,_binary '\0'),(728,'Communication Email','kavit15@gmail.com',NULL,2,0,10,1,'2024-04-28 00:26:06','2024-04-28 00:26:06',_binary '',1,'6','Kavit Shah',NULL,_binary '\0'),(736,'Create New Request Page Link','kavitshah324@gmail.com',NULL,3,0,10,0,'2024-05-01 09:23:44','2024-05-01 09:23:44',_binary '\0',3,'1','Kavit Shah',NULL,_binary '\0'),(742,'Credentials','aashiv73@gmail.com',NULL,2,0,10,739,'2024-05-01 11:03:35','2024-05-01 11:03:35',_binary '',1,'5','Aashiv Shah',NULL,_binary '\0'),(743,'Credentials','aashiv73@gmail.com',NULL,2,0,10,739,'2024-05-01 11:03:35','2024-05-01 11:03:35',_binary '',1,'5','Aashiv Shah',NULL,_binary '\0'),(748,'Credentials','shahsamyak@gmail.com',NULL,2,0,10,745,'2024-05-01 11:10:30','2024-05-01 11:10:30',_binary '',1,'5','Samyak Shah',NULL,_binary '\0'),(749,'Credentials','shahsamyak@gmail.com',NULL,2,0,10,745,'2024-05-01 11:10:30','2024-05-01 11:10:30',_binary '',1,'5','Samyak Shah',NULL,_binary '\0'),(768,'Agreement Link','monibay451@centerf.com','GJ030424WORK0002',3,434,10,0,'2024-05-01 11:32:35','2024-05-01 11:32:35',_binary '',1,'2','RKO Wood',NULL,_binary '\0'),(771,'New Order Details','asdascas@gmail.com','GJ040424CASA0000',4,485,10,0,'2024-05-01 11:35:19','2024-05-01 11:35:19',_binary '',1,'4','Unicron Instruments',NULL,_binary '\0'),(772,'Health Reports/ Documents','monibay451@centerf.com','GJ030424VARK0001',3,429,10,2,'2024-05-01 11:41:13','2024-05-01 11:41:13',_binary '\0',3,'3','RKO Valdimar',NULL,_binary '\0'),(773,'Health Reports/ Documents','monibay451@centerf.com','GJ030424VARK0001',3,429,10,2,'2024-05-01 11:41:24','2024-05-01 11:41:24',_binary '\0',3,'3','RKO Valdimar',NULL,_binary '\0'),(774,'Reset Credentials','jemis69@gmail.com',NULL,2,0,10,3,'2024-05-01 11:42:50','2024-05-01 11:42:50',_binary '',1,'5','Jemis Lathiya',NULL,_binary '\0'),(775,'Reset Credentials','jemis69@gmail.com',NULL,2,0,10,3,'2024-05-01 11:42:50','2024-05-01 11:42:50',_binary '',1,'5','Jemis Lathiya',NULL,_binary '\0');
/*!40000 ALTER TABLE `email_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_token`
--

DROP TABLE IF EXISTS `email_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `email_token` (
  `token_id` int NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_reset_completed` bit(1) DEFAULT NULL,
  `sent_date` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_token`
--

LOCK TABLES `email_token` WRITE;
/*!40000 ALTER TABLE `email_token` DISABLE KEYS */;
INSERT INTO `email_token` VALUES (433,'monibay451@centerf.com',_binary '','2024-04-03 14:16:45.640804','2337560f-d937-4e56-8def-d1d90370c3e1'),(445,'jihoh48005@centerf.com',_binary '','2024-04-03 14:33:41.546144','e0108266-2903-4d22-b32f-09ad4fe33795'),(451,'jihoh48005@centerf.com',_binary '\0','2024-04-03 14:48:33.652264','ab3ce3e3-bfe2-4bee-a50a-c5e527b64870'),(459,'asda@gmail.com',_binary '\0','2024-04-03 16:14:13.054631','803221cb-6407-4393-bd67-8f86eb82b4ee'),(467,'asdsadasdasd@gmail.com',_binary '\0','2024-04-03 16:15:56.732859','ec5ba373-9dc6-474a-8831-aa72d4fab5d0'),(474,'asdsadasdasdasdad@gmail.com',_binary '\0','2024-04-03 16:20:07.189522','a5ee1899-0b30-4c8e-bd17-1a54243b9316'),(482,'qwerasjhjh@gmail.com',_binary '\0','2024-04-03 16:22:28.797548','83720b16-8c44-49bf-958f-4e9ee5c4a995'),(506,'gaxal93643@adstam.com',_binary '','2024-04-10 10:13:58.780087','022d8bd7-50a5-43fa-8260-c95b0f5d870a'),(514,'lovidi4112@agromgt.com',_binary '','2024-04-10 10:17:13.430851','ccaed73d-2532-4ea3-9c23-9b530c74523b'),(522,'rokosi4878@acentni.com',_binary '','2024-04-10 10:21:34.819395','43c94492-97d5-418c-98da-f0b3922358c6'),(530,'xores83799@adstam.com',_binary '\0','2024-04-10 10:26:06.769530','abab7cf7-8543-42f0-a533-913c0e816359'),(537,'bikiyo1901@acentni.com',_binary '','2024-04-10 10:29:15.262525','f68a0efc-9437-4837-86c3-c6228b0ff29c'),(543,'royevef681@abnovel.com',_binary '','2024-04-11 18:52:56.369451','df85b375-45fa-4e11-a62d-d6ab752f873c'),(549,'vasel27162@agaseo.com',_binary '','2024-04-11 19:07:22.043782','929bd579-1d18-4a9d-9829-0f553bae9ac8'),(550,'vasel27162@agaseo.com',_binary '','2024-04-11 19:15:35.889798','83713107-e328-4e96-8bf2-6b14ba503958'),(553,'vasel27162@agaseo.com',_binary '','2024-04-11 19:22:19.255719','a3e8b83d-e3c4-4236-902b-fe2e8bc3d1a3'),(559,'kgpatel12@gmail.com',_binary '\0','2024-04-12 11:50:10.781453','2a144b77-9d20-4133-8f2d-40d18b4fab77'),(590,'vasel27162@agaseo.com',_binary '\0','2024-04-19 11:06:45.255426','70c7b44b-3f83-4492-8845-9c7389ca077e'),(613,'yegim25818@acname.com',_binary '','2024-04-19 15:53:42.478851','f0b99b12-e418-4f27-801d-308b45739988'),(623,'tejoric652@acname.com',_binary '\0','2024-04-19 16:21:33.774862','76262e4e-57b4-4bb6-819c-7e1b0b847a86'),(636,'decero4798@eryod.com',_binary '\0','2024-04-19 16:55:38.281788','aab8abb4-21cd-4257-938d-a7fba0e73c91'),(755,'asdjau@gmail.com',_binary '\0','2024-05-01 11:28:08.907294','95ff5e12-13c6-4d16-a53c-4ba85a3613e9'),(761,'samyakk@gmail.com',_binary '\0','2024-05-01 11:29:48.595742','9cc56e48-799e-4050-89f7-333947c32a57'),(767,'derek@gmail.in',_binary '\0','2024-05-01 11:31:52.964267','f95e2e48-6c0c-47dc-ba66-b4d9a5bb8eb0'),(782,'ascnsjn@gmail.com',_binary '\0','2024-05-01 11:52:43.521985','7e3e21ce-b9f9-4cf3-ac85-3aa3fd60271f');
/*!40000 ALTER TABLE `email_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `encounter_form`
--

DROP TABLE IF EXISTS `encounter_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `encounter_form` (
  `encounter_form_id` int NOT NULL,
  `abd` varchar(255) DEFAULT NULL,
  `admin_id` int DEFAULT NULL,
  `allergies` varchar(255) DEFAULT NULL,
  `blood_presure_plus` varchar(255) DEFAULT NULL,
  `blood_presure_neg` varchar(255) DEFAULT NULL,
  `chest` varchar(255) DEFAULT NULL,
  `cv` varchar(255) DEFAULT NULL,
  `diagnosis` varchar(255) DEFAULT NULL,
  `extr` varchar(255) DEFAULT NULL,
  `follow_up` varchar(255) DEFAULT NULL,
  `heent` varchar(255) DEFAULT NULL,
  `history_of_illness` varchar(255) DEFAULT NULL,
  `hr` varchar(255) DEFAULT NULL,
  `medical_history` varchar(255) DEFAULT NULL,
  `medications` varchar(255) DEFAULT NULL,
  `medications_despensed` varchar(255) DEFAULT NULL,
  `neuro` varchar(255) DEFAULT NULL,
  `o2` varchar(255) DEFAULT NULL,
  `other` varchar(255) DEFAULT NULL,
  `pain` varchar(255) DEFAULT NULL,
  `physician_id` int DEFAULT NULL,
  `procedures` varchar(255) DEFAULT NULL,
  `rr` varchar(255) DEFAULT NULL,
  `skin` varchar(255) DEFAULT NULL,
  `temp` varchar(255) DEFAULT NULL,
  `treatment_plan` varchar(255) DEFAULT NULL,
  `request_id` int DEFAULT NULL,
  PRIMARY KEY (`encounter_form_id`),
  KEY `FKpcyspiiok3ek1ryst5kgny1n9` (`request_id`),
  CONSTRAINT `FKpcyspiiok3ek1ryst5kgny1n9` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `encounter_form`
--

LOCK TABLES `encounter_form` WRITE;
/*!40000 ALTER TABLE `encounter_form` DISABLE KEYS */;
INSERT INTO `encounter_form` VALUES (708,' Soft',10,'Mold, pollen, pet dander ','120 mmHg','80 mmHg','Clear',' Normal ',' Allergic rhinitis ','Not specified','Regular check-ups.','Clear ','Allergic rhinitis','80','Asthma, seasonal allergies','Albuterol, loratadine ','Albuterol, loratadine ','Not specified','98%','Not specified','4/10',0,'Not specified','16','Rash','98.6°F','Allergy medication medications',485);
/*!40000 ALTER TABLE `encounter_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `health_professional_type`
--

DROP TABLE IF EXISTS `health_professional_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `health_professional_type` (
  `health_professional_id` int NOT NULL AUTO_INCREMENT,
  `profession_name` varchar(256) NOT NULL,
  `created_date` datetime NOT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`health_professional_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `health_professional_type`
--

LOCK TABLES `health_professional_type` WRITE;
/*!40000 ALTER TABLE `health_professional_type` DISABLE KEYS */;
INSERT INTO `health_professional_type` VALUES (1,'Pharmacy','2024-04-22 19:34:30',_binary '',_binary '\0'),(2,'Laboratory','2024-04-22 19:34:30',_binary '',_binary '\0'),(3,'Instruments','2024-04-22 19:34:30',_binary '',_binary '\0'),(4,'Homeopathy','2024-04-22 19:34:30',_binary '',_binary '\0');
/*!40000 ALTER TABLE `health_professional_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `health_professionals`
--

DROP TABLE IF EXISTS `health_professionals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `health_professionals` (
  `vendor_id` int NOT NULL AUTO_INCREMENT,
  `vendor_name` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `profession` int DEFAULT NULL,
  `fax_number` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `address` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `city` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `state` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `zip` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `region_id` int DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `phone_number` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `email` varchar(256) DEFAULT NULL,
  `business_contact` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`vendor_id`),
  KEY `profession` (`profession`),
  KEY `region_id_idx` (`region_id`),
  CONSTRAINT `health_professionals_ibfk_1` FOREIGN KEY (`profession`) REFERENCES `health_professional_type` (`health_professional_id`),
  CONSTRAINT `region_id` FOREIGN KEY (`region_id`) REFERENCES `region` (`region_id`)
) ENGINE=InnoDB AUTO_INCREMENT=712 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `health_professionals`
--

LOCK TABLES `health_professionals` WRITE;
/*!40000 ALTER TABLE `health_professionals` DISABLE KEYS */;
INSERT INTO `health_professionals` VALUES (1,'Apollo Pharmacy',1,'8585858585','Bamroli Road','Godhra','Gujarat','389001',1,'2024-04-22 19:34:30','2024-04-22 19:34:30','6568642598',_binary '\0','kavitshah324@gmail.com','Kavit'),(2,'Medikart Pharmacy',1,'8574965281','Kevelam','Rajkot','Gujarat','360001',1,'2024-04-22 19:34:30','2024-04-26 11:07:00','8596321521',_binary '\0','krutarth212002@gmail.com','Krutarth'),(3,'Raj Pathology',2,'8547596325','Bamroli Road','Godhra','Gujarat','389001',1,'2024-04-22 19:34:30','2024-04-22 19:34:30','5574856932',_binary '\0','lathiyajem@gmail.com','Jemis'),(4,'Unicron Instruments',3,'8426953125','Silver Falls','Panchmahri','Madhya Pradesh','389001',3,'2024-04-22 19:34:30','2024-04-22 19:34:30','8452163951',_binary '\0','utubekiller1@gmail.com','Derek'),(8,'Solitude Homeopathy',4,'8596845219','Juhu Beach','Mumbai','Maharashtra','389001',2,'2024-04-22 19:34:30','2024-04-22 19:34:30','8545963201',_binary '\0','kavitshah2101@gmail.com','Yashraj'),(711,'Zydus Pharmacy',1,'7895685412','Bamroli','Godhra','Gujarat','389001',1,'2024-04-25 20:35:47','2024-04-26 11:23:10','7894561230',_binary '','zydus@gmail.com','Kalpesh');
/*!40000 ALTER TABLE `health_professionals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (783);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `menu_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `account_type` tinyint NOT NULL,
  `sort_order` int DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'AdminDashboard',1,0),(2,'MyProfile',1,0),(3,'Role',1,0),(4,'Providers',1,0),(5,'BlockHistory',1,0),(6,'Invoicing',1,0),(7,'SmsLogs',1,0),(8,'EmailLogs',1,0),(9,'ProviderLocation',1,0),(10,'History',1,0),(11,'AdminDahboard',2,0),(12,'MyProfilePhysician',2,0),(13,'MySchedule',2,0),(14,'Invoicing',2,0),(15,'Partners',1,0),(16,'Access',1,0),(17,'Records',1,0),(18,'Provider',1,0),(19,'Scheduling',1,0),(20,'UserAccess',1,0),(21,'CreateAdmin',1,0),(22,'AccountAccess',1,0),(23,'SearchRecords',1,0),(24,'PatientHistory',1,0);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `orderId` int NOT NULL,
  `business_contact` varchar(255) DEFAULT NULL,
  `business_email` varchar(255) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `fax_number` varchar(256) DEFAULT NULL,
  `number_of_refills` int DEFAULT NULL,
  `prescriptions` varchar(255) DEFAULT NULL,
  `request_id` int DEFAULT NULL,
  `vendor_id` int DEFAULT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (706,'6568642598','kavitshah324@gmail.com',10,'2024-04-23 15:58:36.965814','8585858585',1,'Metacin 100mg',485,NULL),(770,'Derek','utubekiller1@gmail.com',10,'2024-05-01 11:35:19.289909','8426953125',1,'Daily Order',485,4);
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `physician`
--

DROP TABLE IF EXISTS `physician`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `physician` (
  `physician_id` int NOT NULL AUTO_INCREMENT,
  `aspnet_user_id` int DEFAULT NULL,
  `first_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `last_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `mobile` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `medical_license` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `admin_notes` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `is_agreement_doc` bit(1) DEFAULT NULL,
  `is_background_doc` bit(1) DEFAULT NULL,
  `is_non_disclosure_doc` bit(1) DEFAULT NULL,
  `address_one` varchar(500) DEFAULT NULL,
  `address_two` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `city` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `region_id` int DEFAULT NULL,
  `zip` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `alt_phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `created_by` int NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` int DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `business_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `business_website` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `npi_number` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `is_license_doc` bit(1) DEFAULT NULL,
  `signature` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `sync_email_address` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `is_hipaa_doc` bit(1) DEFAULT NULL,
  PRIMARY KEY (`physician_id`),
  KEY `created_by` (`created_by`),
  KEY `modified_by` (`modified_by`),
  KEY `aspnet_user_id` (`aspnet_user_id`),
  KEY `region_id_idx` (`region_id`),
  KEY `role_id_idx` (`role_id`),
  CONSTRAINT `FKdw6xryyihmdvf9pdutncxssaa` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `physician_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `asp_net_users` (`id`),
  CONSTRAINT `physician_ibfk_2` FOREIGN KEY (`modified_by`) REFERENCES `asp_net_users` (`id`),
  CONSTRAINT `physician_ibfk_3` FOREIGN KEY (`aspnet_user_id`) REFERENCES `asp_net_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=746 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `physician`
--

LOCK TABLES `physician` WRITE;
/*!40000 ALTER TABLE `physician` DISABLE KEYS */;
INSERT INTO `physician` VALUES (1,556,'Kavit','Shah','kavit15@gmail.com','6351627219',NULL,NULL,NULL,NULL,NULL,NULL,'Bamroli Road','Gujarat','Godhra',1,'389001','9909764648',29,'2024-04-17 09:50:10',29,'2024-04-17 09:50:10',1,'Doctor','Doctor Website',_binary '\0',NULL,NULL,NULL,NULL,NULL,NULL),(2,567,'Krutarth','Gondailya','krutarth21@gmail.com','8849430122',NULL,NULL,NULL,NULL,NULL,NULL,'AG Chowk','Gujarat','Rajkot',1,'360005','6351627219',29,'2024-04-17 09:50:10',29,'2024-04-17 09:50:10',1,'Doctor','Doctor Website',_binary '\0',NULL,NULL,NULL,NULL,NULL,NULL),(3,569,'Jemis','Lathiya','jemis69@gmail.com','8529637410',NULL,NULL,NULL,NULL,NULL,NULL,'Lower Parol','Maharashtra','Mumbai',2,'40009','6969696985',29,'2024-04-17 09:50:10',29,'2024-04-17 09:50:10',1,'Doctor','Doctor Website',_binary '\0',NULL,NULL,NULL,NULL,NULL,NULL),(4,571,'Chandan','Chabbaiya','chandan03@gmail.com','7845128956',NULL,NULL,NULL,NULL,NULL,NULL,'Silver Falls','Mandhya Pradesh','Panchmahri',3,'461881','7845128956',29,'2024-04-17 09:50:10',29,'2024-04-17 09:50:10',1,'Doctor','Doctor Website',_binary '\0',NULL,NULL,NULL,NULL,NULL,NULL),(716,715,'Derek','Shah','derekshah324@gmail.com','7894561231','1234567891','as.png','New doctor',_binary '',_binary '',_binary '','13, Kusum Kunj','Mahavir Jain Society','Godhraa',1,'389001','7894561230',29,'2024-04-26 19:39:26',29,'2024-04-27 17:28:36',1,'Dr. Shah','www.shah.com',_binary '\0',NULL,'1234567891',_binary '','Capture1 (1).PNG','derekshah344@gmail.com',_binary ''),(739,738,'Aashiv','Shah','aashiv73@gmail.com','6351627219','123','app.js','New Physician',_binary '',_binary '',_binary '','13, Kusum Kunj','Mahavair Jain Society','Godhra',1,'389001','6351627219',29,'2024-05-01 11:03:34',29,'2024-05-01 11:03:34',1,'AashivShah','www.shahs.com',_binary '\0',735,'123',_binary '','daypilot (5).txt','kavitshah324@gmail.com',_binary ''),(745,744,'Samyak','Shah','shahsamyak@gmail.com','8956231470','123','as.png','New Physician',_binary '',_binary '',_binary '','B-204','Samasavli','Vadodra',1,'389001','8956231470',29,'2024-05-01 11:10:30',29,'2024-05-01 11:17:28',1,'Samyak','www.samyak.com',_binary '\0',737,'123',_binary '','Capture1 (1).PNG','shahsamyak@gmail.com',_binary '');
/*!40000 ALTER TABLE `physician` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `physician_location`
--

DROP TABLE IF EXISTS `physician_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `physician_location` (
  `location_id` int NOT NULL AUTO_INCREMENT,
  `physician_id` int NOT NULL,
  `latitude` decimal(9,0) DEFAULT NULL,
  `longitude` decimal(9,0) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `physician_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `address` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  KEY `physician_id_idx` (`physician_id`),
  CONSTRAINT `physician_id` FOREIGN KEY (`physician_id`) REFERENCES `physician` (`physician_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `physician_location`
--

LOCK TABLES `physician_location` WRITE;
/*!40000 ALTER TABLE `physician_location` DISABLE KEYS */;
/*!40000 ALTER TABLE `physician_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `physician_notification`
--

DROP TABLE IF EXISTS `physician_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `physician_notification` (
  `id` int NOT NULL AUTO_INCREMENT,
  `physician_id` int NOT NULL,
  `is_notification_stopped` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `physician_id` (`physician_id`),
  CONSTRAINT `physician_notification_ibfk_1` FOREIGN KEY (`physician_id`) REFERENCES `physician` (`physician_id`)
) ENGINE=InnoDB AUTO_INCREMENT=748 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `physician_notification`
--

LOCK TABLES `physician_notification` WRITE;
/*!40000 ALTER TABLE `physician_notification` DISABLE KEYS */;
INSERT INTO `physician_notification` VALUES (1,716,_binary '\0'),(4,1,_binary '\0'),(5,2,_binary '\0'),(6,3,_binary '\0'),(7,4,_binary '\0'),(741,739,_binary '\0'),(747,745,_binary '\0');
/*!40000 ALTER TABLE `physician_notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `physician_region`
--

DROP TABLE IF EXISTS `physician_region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `physician_region` (
  `physician_region_id` int NOT NULL AUTO_INCREMENT,
  `physician_id` int NOT NULL,
  `region_id` int NOT NULL,
  PRIMARY KEY (`physician_region_id`),
  KEY `physician_id` (`physician_id`),
  KEY `region_id` (`region_id`),
  CONSTRAINT `physician_region_ibfk_1` FOREIGN KEY (`physician_id`) REFERENCES `physician` (`physician_id`),
  CONSTRAINT `physician_region_ibfk_2` FOREIGN KEY (`region_id`) REFERENCES `region` (`region_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `physician_region`
--

LOCK TABLES `physician_region` WRITE;
/*!40000 ALTER TABLE `physician_region` DISABLE KEYS */;
INSERT INTO `physician_region` VALUES (1,1,1),(2,2,1),(3,3,3),(4,4,4),(5,1,2),(6,2,3),(7,4,1),(8,1,4),(9,2,4),(19,716,1),(20,716,3),(21,716,4),(22,739,1),(23,739,2),(24,739,3),(25,739,4),(26,745,1),(27,745,2),(28,745,3),(29,745,4);
/*!40000 ALTER TABLE `physician_region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `region` (
  `region_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `abbreviation` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`region_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES (1,'Gujarat','GJ'),(2,'Maharashtra','MH'),(3,'Madhya Pradesh','MP'),(4,'Rajasthan','RJ');
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `request_id` int NOT NULL AUTO_INCREMENT,
  `request_type_id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `first_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `last_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `phone_number` varchar(23) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `status` tinyint NOT NULL,
  `physician_id` int DEFAULT NULL,
  `confirmation_number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `declined_by` int DEFAULT NULL,
  `last_wellness_date` datetime DEFAULT NULL,
  `call_type` tinyint DEFAULT NULL,
  `completed_by_physician` bit(1) DEFAULT NULL,
  `last_reservation_date` datetime DEFAULT NULL,
  `accepted_date` datetime DEFAULT NULL,
  `relation_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `case_number` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `case_tag` int DEFAULT NULL,
  `case_tag_physician` int DEFAULT NULL,
  PRIMARY KEY (`request_id`),
  KEY `physician_id` (`physician_id`),
  KEY `user_id` (`user_id`),
  KEY `declined_by_idx` (`declined_by`),
  KEY `request_ibfk_3_idx` (`request_type_id`),
  CONSTRAINT `declined_by` FOREIGN KEY (`declined_by`) REFERENCES `asp_net_users` (`id`),
  CONSTRAINT `request_ibfk_1` FOREIGN KEY (`physician_id`) REFERENCES `physician` (`physician_id`),
  CONSTRAINT `request_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `request_ibfk_3` FOREIGN KEY (`request_type_id`) REFERENCES `request_type` (`request_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=780 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (423,2,422,'Jay!','Shah','+91 1234567890','miyohop516@acentni.com',1,2,'GJ030424SHJA0000','2024-04-03 14:10:01',_binary '','2024-04-18 16:52:40',NULL,NULL,0,_binary '\0',NULL,'2024-04-03 14:10:01',NULL,NULL,NULL,NULL),(429,3,428,'John','Cena','7845129856','youcantseeme@gmail.com',9,2,'GJ030424VARK0001','2024-04-03 14:16:45',_binary '\0','2024-04-24 11:36:32',NULL,NULL,0,_binary '\0',NULL,'2024-04-15 12:59:02',NULL,NULL,1,NULL),(434,3,428,'Great','Khali','7845129630','dhaikilokahaath@gmail.com',2,1,'GJ030424WORK0002','2024-04-03 14:25:54',_binary '\0','2024-04-17 15:41:18',NULL,NULL,0,_binary '\0',NULL,'2024-04-03 14:25:54',NULL,NULL,NULL,NULL),(441,2,440,'Denis','Richhe','7845129652','dennis@gmail.com',6,3,'GJ030424DORA0003','2024-04-03 14:33:41',_binary '\0','2024-04-03 14:33:41',NULL,NULL,0,_binary '\0',NULL,'2024-04-03 14:33:41',NULL,NULL,NULL,NULL),(447,2,440,'Raj','Doshi','1747474747','nightmanager@gmail.com',8,4,'GJ030424DORA0004','2024-04-03 14:48:33',_binary '\0','2024-04-03 14:48:33',NULL,NULL,0,_binary '\0',NULL,'2024-04-03 14:48:33',NULL,NULL,NULL,NULL),(455,4,454,'asdasc','zczxczxc','','asds@gmail.com',9,1,'GJ030424SHDE0005','2024-04-03 16:14:12',_binary '\0','2024-04-03 16:14:12',NULL,NULL,0,_binary '\0',NULL,'2024-04-03 16:14:12',NULL,NULL,NULL,NULL),(463,1,462,'sadsad','adasd','1234567890','adasd@gmail.com',1,NULL,'GJ030424ASSA0006','2024-04-03 16:15:56',_binary '\0','2024-04-03 16:15:56',NULL,NULL,0,_binary '\0',NULL,'2024-04-03 16:15:56',NULL,'11',NULL,NULL),(470,1,469,'sadsad','adasd','1234567890','adasd@gmail.com',1,NULL,'GJ030424ASSA0007','2024-04-03 16:20:07',_binary '\0','2024-04-03 16:20:07',NULL,NULL,0,_binary '\0',NULL,'2024-04-03 16:20:07',NULL,'11',NULL,NULL),(478,1,477,'qwertyu','asdfghj','1234567890','qwertyuio@gmail.com',11,3,'GJ030424CVWE0008','2024-04-03 16:22:28',_binary '\0','2024-04-15 16:01:34',NULL,NULL,0,_binary '\0',NULL,'2024-04-03 16:22:28',NULL,'978',NULL,NULL),(485,2,484,'Shresths','Bhakta','7845129631','asdascas@gmail.com',4,2,'GJ040424CASA0000','2024-04-04 12:50:04',_binary '\0','2024-04-04 12:50:04',NULL,NULL,0,_binary '\0',NULL,'2024-04-04 12:50:04',NULL,NULL,NULL,NULL),(502,3,501,'Rishi','Oza','7894561230','rishi@gmail.com',1,NULL,'GJ100424POGI0000','2024-04-10 10:13:58',_binary '\0','2024-04-10 10:13:58',NULL,NULL,0,_binary '\0',NULL,'2024-04-10 10:13:58',NULL,NULL,NULL,NULL),(510,4,509,'Servenus','Snape','8596754153','snape@potterhead.com',1,NULL,'MH100424POLI0001','2024-04-10 10:17:13',_binary '\0','2024-04-10 10:17:13',NULL,NULL,0,_binary '\0',NULL,'2024-04-10 10:17:13',NULL,NULL,NULL,NULL),(518,1,517,'Ishan ','Bhatt','7894568598','iibhatt@gmail.com',1,NULL,'RJ100424SHKA0002','2024-04-10 10:21:35',_binary '\0','2024-04-10 10:21:35',NULL,NULL,0,_binary '\0',NULL,'2024-04-10 10:21:35',NULL,'',NULL,NULL),(526,1,525,'Thor','Odinson','7895632450','thor@gmail.com',1,NULL,'MP100424LOLU0003','2024-04-10 10:26:07',_binary '\0','2024-04-10 10:26:07',NULL,NULL,0,_binary '\0',NULL,'2024-04-10 10:26:07',NULL,'',NULL,NULL),(533,3,532,'Raju','Rasthogi','7489687123','dinohab538@acentni.com',1,4,'MH100424PAHI0004','2024-04-10 10:29:15',_binary '\0','2024-04-29 11:22:11',NULL,NULL,0,_binary '\0',NULL,'2024-04-10 10:29:15',NULL,NULL,NULL,NULL),(540,2,539,'Rocky','Singh','7894561230','royevef681@abnovel.com',3,2,'GJ110424SIRO0000','2024-04-11 18:52:55',_binary '\0','2024-04-15 16:48:54',NULL,NULL,0,_binary '\0',NULL,'2024-04-11 18:52:55',NULL,NULL,1,NULL),(546,2,545,'Yashraj','Chauhan','7845129635','vasel27162@agaseo.com',1,NULL,'GJ110424CHYA0001','2024-04-11 19:07:22',_binary '\0','2024-04-11 19:07:22',NULL,NULL,0,_binary '\0',NULL,'2024-04-11 19:07:22',NULL,NULL,NULL,NULL),(551,2,545,'Yashraj','Chauhan','3890013890','vasel27162@agaseo.com',1,1,'RJ110424CHYA0002','2024-04-11 19:22:19',_binary '\0','2024-04-18 12:30:01',NULL,NULL,0,_binary '\0',NULL,'2024-04-11 19:22:19',NULL,NULL,NULL,NULL),(556,2,555,'Krutarth','Gondaliya','','kgpatel12@gmail.com',1,1,'GJ120424GOKR0000','2024-04-12 11:50:10',_binary '\0','2024-04-18 16:56:43',NULL,NULL,0,_binary '\0',NULL,'2024-04-12 11:50:10',NULL,NULL,NULL,NULL),(587,2,545,'Brinda','Joshi','7896541230','vasel27162@agaseo.com',2,2,'GJ190424JOBR0000','2024-04-19 11:06:44',_binary '\0','2024-04-19 11:47:35',NULL,NULL,0,_binary '\0',NULL,'2024-04-19 11:06:44',NULL,NULL,NULL,NULL),(610,2,609,'Bhavyaa','Shah','9898989898','yegim25818@acname.com',4,2,'GJ190424SHBH0001','2024-04-19 15:53:42',_binary '\0','2024-04-19 15:59:57',NULL,NULL,0,_binary '\0',NULL,'2024-04-19 15:54:07',NULL,NULL,NULL,NULL),(620,2,619,'Chandan','Chabaiyaa','7485961230','tejoric652@acname.com',7,3,'GJ190424CHCH0002','2024-04-19 16:21:33',_binary '\0','2024-04-19 16:40:35',NULL,NULL,0,_binary '\0',NULL,'2024-04-19 16:21:33',NULL,NULL,NULL,NULL),(633,2,632,'Chirag','Gundariya','7845129630','decero4798@eryod.com',7,4,'GJ190424GUCH0003','2024-04-19 16:55:38',_binary '\0','2024-04-19 17:03:08',NULL,NULL,0,_binary '\0',NULL,'2024-04-19 16:55:38',NULL,NULL,NULL,NULL),(752,2,751,'Kavir','Shah','7894561230','asdjau@gmail.com',1,NULL,'GJ010524SHKA0000','2024-05-01 11:28:09',_binary '\0','2024-05-01 11:28:09',NULL,NULL,0,_binary '\0',NULL,'2024-04-19 16:55:38',NULL,NULL,NULL,NULL),(758,2,757,'Samyak','Shankeshra','7894561230','samyakk@gmail.com',1,NULL,'GJ010524SHSA0001','2024-05-01 11:29:48',_binary '\0','2024-05-01 11:29:48',NULL,NULL,0,_binary '\0',NULL,'2024-04-19 16:55:38',NULL,NULL,NULL,NULL),(764,2,763,'Derek','Shah','7894561230','derek@gmail.in',1,NULL,'GJ010524SHDE0002','2024-05-01 11:31:53',_binary '\0','2024-05-01 11:31:53',NULL,NULL,0,_binary '\0',NULL,'2024-04-19 16:55:38',NULL,NULL,NULL,NULL),(779,1,778,'Kavit','Shah','1234567890','kavitshah32124@gmail.com',1,NULL,'GJ010524SHKA0003','2024-05-01 11:52:43',_binary '\0','2024-05-01 11:52:43',NULL,NULL,0,_binary '\0',NULL,NULL,NULL,'123',NULL,NULL);
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_business`
--

DROP TABLE IF EXISTS `request_business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request_business` (
  `request_business_id` int NOT NULL AUTO_INCREMENT,
  `request_id` int NOT NULL,
  `business_id` int NOT NULL,
  PRIMARY KEY (`request_business_id`),
  KEY `request_id` (`request_id`),
  KEY `business_id` (`business_id`),
  CONSTRAINT `request_business_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`),
  CONSTRAINT `request_business_ibfk_2` FOREIGN KEY (`business_id`) REFERENCES `business` (`business_id`)
) ENGINE=InnoDB AUTO_INCREMENT=782 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_business`
--

LOCK TABLES `request_business` WRITE;
/*!40000 ALTER TABLE `request_business` DISABLE KEYS */;
INSERT INTO `request_business` VALUES (466,463,460),(473,470,460),(481,478,475),(521,518,515),(529,526,523),(781,779,776);
/*!40000 ALTER TABLE `request_business` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_client`
--

DROP TABLE IF EXISTS `request_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request_client` (
  `request_client_id` int NOT NULL AUTO_INCREMENT,
  `request_id` int NOT NULL,
  `first_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `last_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `phone_number` varchar(23) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `location` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `address` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `region_id` int DEFAULT NULL,
  `noti_mobile` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `noti_email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `notes` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `str_month` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `int_year` int DEFAULT NULL,
  `int_date` int DEFAULT NULL,
  `street` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `city` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `state` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `zipcode` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `latitude` decimal(9,0) DEFAULT NULL,
  `longitude` decimal(9,0) DEFAULT NULL,
  PRIMARY KEY (`request_client_id`),
  KEY `request_id` (`request_id`),
  KEY `region_id` (`region_id`),
  CONSTRAINT `request_client_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`),
  CONSTRAINT `request_client_ibfk_2` FOREIGN KEY (`region_id`) REFERENCES `region` (`region_id`)
) ENGINE=InnoDB AUTO_INCREMENT=781 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_client`
--

LOCK TABLES `request_client` WRITE;
/*!40000 ALTER TABLE `request_client` DISABLE KEYS */;
INSERT INTO `request_client` VALUES (425,423,'Jay!','Shah','+91 1234567890',NULL,NULL,1,'1234567890','miyohop516@acentni.com','Helllo There.','miyohop516@acentni.com','June',2024,2,'Gujan Vatika','Rajkot','Gujarat','389001',0,0),(431,429,'RKO','Valdimar','7858964152',NULL,NULL,1,'7858964152','monibay451@centerf.com','He can\'t see me.','monibay451@centerf.com','April',2024,16,'Bamroli Road','Godhra','Gujarat','389001',0,0),(436,434,'RKO','Wood','7894561230',NULL,NULL,1,'7894561230','monibay451@centerf.com','zkjda','monibay451@centerf.com','April',2024,16,'Bamroli Road','Godhra','Gujarat','389001',0,0),(442,441,'Raj','Doshi','7845125478',NULL,NULL,1,'7845125478','jihoh48005@centerf.com','asdadsd','jihoh48005@centerf.com','April',2024,1,'Judges Bunglow Road','Ahmedabad','Gujarat','389001',0,0),(448,447,'Raj','Doshi','1747474747',NULL,NULL,1,'1747474747','jihoh48005@centerf.com','asdsadsad','jihoh48005@centerf.com','April',2024,1,'Judges Bunglow','Ahmedabad','Gujarat','389001',0,0),(456,455,'Dere','Sha','7485963210',NULL,NULL,1,'7485963210','asda@gmail.com','asda','asda@gmail.com','May',2024,2,'asdsad','sada','Gujarat','389001',0,0),(464,463,'sadsa','asdadsada','7485963210',NULL,NULL,1,'7485963210','asdsadasdasd@gmail.com','adssa','asdsadasdasd@gmail.com','April',2024,20,'zxccsa','csdcsc','Gujarat','389001',0,0),(471,470,'sadsa','asdadsada','7485963210',NULL,NULL,1,'7485963210','asdsadasdasdasdad@gmail.com','adssa','asdsadasdasdasdad@gmail.com','April',2024,20,'zxccsa','csdcsc','Gujarat','389001',0,0),(479,478,'wert','cvbnm','7845129631',NULL,NULL,1,'7845129631','qwerasjhjh@gmail.com','cvbnm','qwerasjhjh@gmail.com','April',2024,12,'dcfghj','swdfghyju','Gujarat','985661',0,0),(487,485,'Shresths','Bhakta','7845129631',NULL,NULL,1,'7845129630','asdascas@gmail.com','cascsa','asdascas@gmail.com','January',2024,15,'asd','asd','Gujarat','389001',0,0),(504,502,'Ginni','Potter','7845121985',NULL,NULL,1,'7845121985','gaxal93643@adstam.com','qwertyuiop','gaxal93643@adstam.com','April',2024,17,'Bamroli Road','Godhra','Gujarat','389001',0,0),(511,510,'Lilly','Potter','7539518462',NULL,NULL,2,'7539518462','lovidi4112@agromgt.com','asjk','lovidi4112@agromgt.com','April',2024,1,'Lower Parole','Mumbai','Maharashtra','388120',0,0),(519,518,'Kandarp','Shah','7845121245',NULL,NULL,4,'7845121245','rokosi4878@acentni.com','asdfghjk','rokosi4878@acentni.com','April',2024,2,'Badi Lake','Udaipur','Rajasthan','303103',0,0),(527,526,'Luna','Lovegood','8659742310',NULL,NULL,3,'8659742310','xores83799@adstam.com','zxcvbnnm','xores83799@adstam.com','April',2024,1,'Silver Falls','Pachmarhi','Madhya Pradesh','461881',0,0),(535,533,'Hiren','Pandya','',NULL,NULL,2,'','bikiyo1901@acentni.com','asdfghjbh qwertyu sdfghj','bikiyo1901@acentni.com','April',2024,8,'Juhu Beach','Mumbai','Maharashtra','388120',0,0),(541,540,'Rocky','Singh','7894561230',NULL,NULL,1,'7894561230','royevef681@abnovel.com',NULL,'royevef681@abnovel.com','April',2024,12,'Bamroli Road','Godhra','Gujarat','389001',0,0),(547,546,'Yashraj','Chauhan','7845129635',NULL,NULL,1,'7845129635','vasel27162@agaseo.com',NULL,'vasel27162@agaseo.com','April',2024,1,'Badi Lake','Godhra','Gujarat','389001',0,0),(552,551,'Yashraj','Chauhan','3890013890',NULL,NULL,4,'3890013890','vasel27162@agaseo.com',NULL,'vasel27162@agaseo.com','April',2024,1,'Ambari Ghatt','Udaipur','Rajasthan','313001',0,0),(557,556,'Krutarth','Gondaliya','',NULL,NULL,1,'','kgpatel12@gmail.com',NULL,'kgpatel12@gmail.com','April',2024,1,'Uttampura','Rajkot','Gujarat','389001',0,0),(588,587,'Brinda','Joshi','7896541230',NULL,NULL,1,'7896541230','vasel27162@agaseo.com',NULL,'vasel27162@agaseo.com','April',2024,1,'Tatvasoft','Ahmedabad','Gujarat','388120',0,0),(611,610,'Bhavyaa','Shah','9898989898',NULL,NULL,1,'','yegim25818@acname.com',NULL,'yegim25818@acname.com','April',2024,11,'Urmi School','Vadodra','Gujarat','380120',0,0),(621,620,'Chandan','Chabaiyaa','7485961230',NULL,NULL,1,'','tejoric652@acname.com',NULL,'tejoric652@acname.com','April',2024,2,'Amit Nagar','Vadodra','Gujarat','388120',0,0),(634,633,'Chirag','Gundariya','7845129630',NULL,NULL,1,'7845129630','decero4798@eryod.com',NULL,'decero4798@eryod.com','April',2024,9,'Tatvasoft','Ahmedabad','Gujarat','388012',0,0),(753,752,'Kavir','Shah','7894561230',NULL,NULL,1,'7894561230','asdjau@gmail.com',NULL,'asdjau@gmail.com','May',2024,21,'Bamroli','Godhra','Gujarat','389001',0,0),(759,758,'Samyak','Shankeshra','7894561230',NULL,NULL,1,'7894561230','samyakk@gmail.com',NULL,'samyakk@gmail.com','May',2024,2,'asdasd','asdasd','Gujarat','389001',0,0),(765,764,'Derek','Shah','7894561230',NULL,NULL,1,'7894561230','derek@gmail.in',NULL,'derek@gmail.in','May',2024,17,'asdasd','asdv','Gujarat','389001',0,0),(780,779,'Kavit','Shah','7894561230',NULL,NULL,1,'7894561230','ascnsjn@gmail.com','jsdjcsj','ascnsjn@gmail.com','May',2024,3,'Bamroli','Godhra','Gujarat','389001',0,0);
/*!40000 ALTER TABLE `request_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_closed`
--

DROP TABLE IF EXISTS `request_closed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request_closed` (
  `request_closed_id` int NOT NULL AUTO_INCREMENT,
  `request_id` int NOT NULL,
  `request_status_log_id` int NOT NULL,
  `phy_notes` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `client_notes` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`request_closed_id`),
  KEY `request_id` (`request_id`),
  KEY `request_status_log_id` (`request_status_log_id`),
  CONSTRAINT `request_closed_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`),
  CONSTRAINT `request_closed_ibfk_2` FOREIGN KEY (`request_status_log_id`) REFERENCES `request_status_log` (`request_status_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_closed`
--

LOCK TABLES `request_closed` WRITE;
/*!40000 ALTER TABLE `request_closed` DISABLE KEYS */;
/*!40000 ALTER TABLE `request_closed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_concierge`
--

DROP TABLE IF EXISTS `request_concierge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request_concierge` (
  `request_concierge_id` int NOT NULL AUTO_INCREMENT,
  `request_id` int NOT NULL,
  `concierge_id` int NOT NULL,
  PRIMARY KEY (`request_concierge_id`),
  KEY `fk1_idx` (`concierge_id`),
  KEY `fk2_idx` (`request_id`),
  CONSTRAINT `fk1` FOREIGN KEY (`concierge_id`) REFERENCES `concierge` (`concierge_id`),
  CONSTRAINT `fk2` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=514 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_concierge`
--

LOCK TABLES `request_concierge` WRITE;
/*!40000 ALTER TABLE `request_concierge` DISABLE KEYS */;
INSERT INTO `request_concierge` VALUES (444,441,438),(450,447,446),(458,455,452),(513,510,507);
/*!40000 ALTER TABLE `request_concierge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_notes`
--

DROP TABLE IF EXISTS `request_notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request_notes` (
  `request_notes_id` int NOT NULL AUTO_INCREMENT,
  `request_id` int NOT NULL,
  `str_month` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `int_year` int DEFAULT NULL,
  `int_date` int DEFAULT NULL,
  `physician_notes` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `admin_notes` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `created_by` int NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` int DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `administrative_notes` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  `physican_notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`request_notes_id`),
  KEY `request_id` (`request_id`),
  KEY `created_by_idx` (`created_by`),
  KEY `modified_by_idx` (`modified_by`),
  CONSTRAINT `FKd941q072svvwrrnt5qcmafoh1` FOREIGN KEY (`modified_by`) REFERENCES `asp_net_users` (`id`),
  CONSTRAINT `FKej1yqplm70kiu1o571y3rp2j5` FOREIGN KEY (`created_by`) REFERENCES `asp_net_users` (`id`),
  CONSTRAINT `request_notes_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=767 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_notes`
--

LOCK TABLES `request_notes` WRITE;
/*!40000 ALTER TABLE `request_notes` DISABLE KEYS */;
INSERT INTO `request_notes` VALUES (542,540,NULL,0,0,NULL,'hello moto!',29,'2024-04-11 18:52:56',29,'2024-04-17 10:57:54',NULL,NULL,NULL),(548,546,NULL,0,0,NULL,'qwertyuioasdfghjkl',29,'2024-04-11 19:07:22',NULL,'2024-04-11 19:07:22',NULL,NULL,NULL),(549,551,NULL,0,0,NULL,'random',29,'2024-04-11 19:22:19',NULL,'2024-04-18 12:30:01',NULL,NULL,NULL),(558,556,NULL,0,0,NULL,'asdasd',29,'2024-04-12 11:50:11',NULL,'2024-04-18 16:56:43',NULL,NULL,NULL),(589,587,NULL,0,0,NULL,'12345',29,'2024-04-19 11:06:45',NULL,'2024-04-19 11:47:35',NULL,NULL,NULL),(612,610,NULL,0,0,NULL,'asfk qwerty',29,'2024-04-19 15:53:42',NULL,'2024-04-19 15:59:57',NULL,NULL,NULL),(622,620,NULL,0,0,NULL,'asdfgh',29,'2024-04-19 16:21:34',NULL,'2024-04-19 16:40:35',NULL,NULL,NULL),(635,633,NULL,0,0,NULL,'qwe dhj vbnm',29,'2024-04-19 16:55:38',NULL,'2024-04-19 17:03:08',NULL,NULL,NULL),(754,752,NULL,0,0,NULL,'New',29,'2024-05-01 11:28:09',NULL,'2024-05-01 11:28:09',NULL,NULL,NULL),(760,758,NULL,0,0,NULL,'asdd',29,'2024-05-01 11:29:49',NULL,'2024-05-01 11:29:49',NULL,NULL,NULL),(766,764,NULL,0,0,NULL,'asdasd',29,'2024-05-01 11:31:53',NULL,'2024-05-01 11:31:53',NULL,NULL,NULL);
/*!40000 ALTER TABLE `request_notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_status_log`
--

DROP TABLE IF EXISTS `request_status_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request_status_log` (
  `request_status_log_id` int NOT NULL AUTO_INCREMENT,
  `request_id` int NOT NULL,
  `status` tinyint NOT NULL,
  `physician_id` int DEFAULT NULL,
  `admin_id` int DEFAULT NULL,
  `trans_to_physician_id` int DEFAULT NULL,
  `notes` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `trans_to_admin` bit(1) DEFAULT NULL,
  PRIMARY KEY (`request_status_log_id`),
  KEY `request_id` (`request_id`),
  KEY `physician_id` (`physician_id`),
  KEY `admin_id` (`admin_id`),
  KEY `trans_to_physician_id` (`trans_to_physician_id`),
  CONSTRAINT `request_status_log_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`),
  CONSTRAINT `request_status_log_ibfk_2` FOREIGN KEY (`physician_id`) REFERENCES `physician` (`physician_id`),
  CONSTRAINT `request_status_log_ibfk_3` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`),
  CONSTRAINT `request_status_log_ibfk_4` FOREIGN KEY (`trans_to_physician_id`) REFERENCES `physician` (`physician_id`)
) ENGINE=InnoDB AUTO_INCREMENT=733 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_status_log`
--

LOCK TABLES `request_status_log` WRITE;
/*!40000 ALTER TABLE `request_status_log` DISABLE KEYS */;
INSERT INTO `request_status_log` VALUES (426,423,1,NULL,NULL,NULL,'qwertyu','2024-04-03 14:10:01',_binary '\0'),(443,441,1,NULL,NULL,NULL,'sdfghj','2024-04-03 14:33:41',_binary '\0'),(449,447,1,NULL,NULL,NULL,'kjhgd','2024-04-03 14:48:33',_binary '\0'),(457,455,1,NULL,NULL,NULL,'oiuytr','2024-04-03 16:14:12',_binary '\0'),(488,485,1,NULL,NULL,NULL,'mnbvcxxz','2024-04-04 12:50:04',_binary '\0'),(560,429,3,NULL,10,NULL,'Patient did not reponded to any call or message.','2024-04-15 12:59:02',_binary '\0'),(561,533,11,NULL,10,NULL,'No Reason','2024-04-15 15:52:22',_binary '\0'),(562,533,11,NULL,10,NULL,'Not Appropriate','2024-04-15 15:57:49',_binary '\0'),(564,478,11,NULL,10,NULL,'bas maan kiya','2024-04-15 16:01:34',_binary '\0'),(566,540,3,NULL,10,NULL,'No response','2024-04-15 16:48:54',_binary '\0'),(567,434,1,NULL,10,NULL,'Transfered Case','2024-04-16 13:14:29',_binary '\0'),(568,546,1,NULL,10,NULL,'Transfered Case','2024-04-16 13:14:29',_binary '\0'),(573,434,1,1,10,1,'Admin transferred case to Dr. Kavit on April 17, 2024 at 03:41:18 pm : 123','2024-04-17 15:41:18',_binary '\0'),(574,556,1,2,10,2,'Admin transferred case to Dr. Krutarth on April 18, 2024 at 00:24:26 pm : 1234','2024-04-18 12:24:27',_binary '\0'),(575,556,1,2,10,2,'Admin transferred case to Dr. Krutarth on April 18, 2024 at 00:27:04 pm : 12345123','2024-04-18 12:27:05',_binary '\0'),(576,551,1,1,10,1,'Admin transferred case to Dr. Kavit on April 18, 2024 at 00:30:00 pm : Take Meds!','2024-04-18 12:30:01',_binary '\0'),(577,556,1,2,10,2,'Admin transferred case to Dr. Krutarth on April 18, 2024 at 00:31:14 pm : 12','2024-04-18 12:31:14',_binary '\0'),(583,423,1,2,10,2,'Admin transferred case to Dr. Krutarth on April 18, 2024 at 04:52:39 pm : Transferred to other Krutarth','2024-04-18 16:52:40',_binary '\0'),(584,556,1,1,10,1,'Admin transferred case to Dr. Kavit on April 18, 2024 at 04:56:43 pm : 123','2024-04-18 16:56:43',_binary '\0'),(591,587,1,1,10,1,'Admin transferred case to Dr. Kavit on April 19, 2024 at 11:07:22 am : Bohot sikhna padega!','2024-04-19 11:07:22',_binary '\0'),(592,587,1,1,10,1,'Admin transferred case to Dr. Kavit on April 19, 2024 at 11:08:19 am : 123123','2024-04-19 11:08:19',_binary '\0'),(599,587,1,2,10,2,'Admin transferred case to Dr. Krutarth on April 19, 2024 at 11:47:34 am : 1234','2024-04-19 11:47:35',_binary '\0'),(614,610,1,2,10,2,'Admin transferred case to Dr. Krutarth on April 19, 2024 at 03:54:06 pm : Take Meds!','2024-04-19 15:54:07',_binary '\0'),(617,610,4,2,NULL,NULL,'Patient accepted the agreement on Fri Apr 19 15:59:56 IST 2024','2024-04-19 15:59:57',_binary '\0'),(629,620,7,3,NULL,NULL,'Region out of bound','2024-04-19 16:38:18',_binary '\0'),(630,620,7,3,NULL,NULL,'Region out of bound','2024-04-19 16:40:35',_binary '\0'),(639,633,7,4,NULL,NULL,'Family Doctor consulted','2024-04-19 17:03:08',_binary '\0'),(710,429,9,NULL,10,NULL,'AdminKavit Shah closed this case on Wed Apr 24 11:36:32 IST 2024','2024-04-24 11:36:32',_binary '\0'),(729,533,1,NULL,10,NULL,'Case Unblocked by Admin on 29-3-124','2024-04-29 11:20:59',_binary '\0'),(730,533,1,NULL,10,NULL,'Case Unblocked by Admin on 29-3-124','2024-04-29 11:21:09',_binary '\0'),(731,533,1,NULL,10,NULL,'Case Unblocked by Admin on 29-3-124','2024-04-29 11:21:17',_binary '\0'),(732,533,1,NULL,10,NULL,'Case Unblocked by Admin on 29-3-124','2024-04-29 11:22:11',_binary '\0');
/*!40000 ALTER TABLE `request_status_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_type`
--

DROP TABLE IF EXISTS `request_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request_type` (
  `request_type_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`request_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_type`
--

LOCK TABLES `request_type` WRITE;
/*!40000 ALTER TABLE `request_type` DISABLE KEYS */;
INSERT INTO `request_type` VALUES (1,'Business'),(2,'Patient'),(3,'Family'),(4,'Concierge');
/*!40000 ALTER TABLE `request_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_wise_file`
--

DROP TABLE IF EXISTS `request_wise_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request_wise_file` (
  `request_wise_file_id` int NOT NULL AUTO_INCREMENT,
  `request_id` int NOT NULL,
  `file_name` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `created_date` datetime NOT NULL,
  `physician_id` int DEFAULT NULL,
  `admin_id` int DEFAULT NULL,
  `doc_type` tinyint DEFAULT NULL,
  `is_finalize` bit(1) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `is_patient_records` bit(1) DEFAULT NULL,
  `file_extension` varchar(255) DEFAULT NULL,
  `uploader_name` varchar(255) DEFAULT NULL,
  `stored_file_name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`request_wise_file_id`),
  KEY `request_id` (`request_id`),
  KEY `physician_id` (`physician_id`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `request_wise_file_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`),
  CONSTRAINT `request_wise_file_ibfk_2` FOREIGN KEY (`physician_id`) REFERENCES `physician` (`physician_id`),
  CONSTRAINT `request_wise_file_ibfk_3` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=710 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_wise_file`
--

LOCK TABLES `request_wise_file` WRITE;
/*!40000 ALTER TABLE `request_wise_file` DISABLE KEYS */;
INSERT INTO `request_wise_file` VALUES (424,423,'toaster.txt','2024-04-03 14:10:01',NULL,NULL,1,_binary '\0',_binary '\0',_binary '\0','txt','Jay Shah','patient03-Apr-2024-14-10-01-toaster.txt'),(430,429,'daypilot.txt','2024-04-03 14:16:45',NULL,NULL,1,_binary '\0',_binary '\0',_binary '\0','txt','John Cena','patient03-Apr-2024-14-16-45-daypilot.txt'),(435,434,'trash.svg','2024-04-03 14:25:54',NULL,NULL,1,_binary '\0',_binary '\0',_binary '\0','svg','Great Khali','patient03-Apr-2024-14-25-53-trash.svg'),(486,485,'daypilot.txt','2024-04-04 12:50:04',NULL,NULL,1,_binary '\0',_binary '\0',_binary '\0','txt','sadasca cascac','patient04-Apr-2024-12-50-03-daypilot.txt'),(503,502,'jackson-databind-2.16.1.jar','2024-04-10 10:13:58',NULL,NULL,1,_binary '\0',_binary '\0',_binary '\0','jar','Rishi Oza','patient10-Apr-2024-10-13-57-jackson-databind-2.16.1.jar'),(534,533,'jackson-core-2.16.1.jar','2024-04-10 10:29:15',NULL,NULL,1,_binary '\0',_binary '\0',_binary '\0','jar','Raju Rasthogi','patient10-Apr-2024-10-29-15-jackson-core-2.16.1.jar'),(648,434,'medium-article.PNG','2024-04-22 11:44:30',NULL,NULL,1,_binary '\0',_binary '',_binary '\0','PNG','Kavit Shah','patient22-Apr-2024-11-44-30-medium-article.PNG'),(649,434,'work-meme2.avif','2024-04-22 11:45:21',NULL,NULL,1,_binary '\0',_binary '',_binary '\0','avif','Kavit Shah','patient22-Apr-2024-11-45-20-work-meme2.avif'),(650,434,'ishan report.docx','2024-04-22 11:48:41',NULL,NULL,1,_binary '\0',_binary '',_binary '\0','docx','Kavit Shah','patient22-Apr-2024-11-48-40-ishan report.docx'),(653,434,'DumpLatest.sql','2024-04-22 11:58:45',NULL,NULL,1,_binary '\0',_binary '',_binary '\0','sql','Kavit Shah','patient22-Apr-2024-11-58-45-DumpLatest.sql'),(699,434,'Capture1.PNG','2024-04-22 14:57:25',NULL,10,1,_binary '\0',_binary '\0',_binary '\0','PNG','Kavit Shah','patient22-Apr-2024-14-57-24-Capture1.PNG'),(704,434,'DummyFile.png','2024-04-23 09:23:04',NULL,10,1,_binary '\0',_binary '',_binary '\0','png','Kavit Shah','patient23-Apr-2024-09-23-04-DummyFile.png'),(709,429,'table-data.csv','2024-04-24 10:50:10',NULL,10,1,_binary '\0',_binary '\0',_binary '\0','csv','Kavit Shah','patient24-Apr-2024-10-50-09-table-data.csv');
/*!40000 ALTER TABLE `request_wise_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `account_type` tinyint NOT NULL,
  `created_by` int NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` int DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL,
  PRIMARY KEY (`role_id`),
  KEY `created_by_idx` (`created_by`),
  KEY `modified_by_idx` (`modified_by`),
  CONSTRAINT `FKhna7qu73vb47y8vtfsm5fwd19` FOREIGN KEY (`modified_by`) REFERENCES `asp_net_users` (`id`),
  CONSTRAINT `FKsk75tmo41qrrk5sji0ebd5nws` FOREIGN KEY (`created_by`) REFERENCES `asp_net_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=738 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (733,'Master Admin',1,29,'2024-04-30 11:26:45',29,'2024-04-30 11:26:45',_binary '\0'),(734,'Admin',1,29,'2024-04-30 11:44:13',29,'2024-04-30 11:44:13',_binary '\0'),(735,'Physician',2,29,'2024-04-30 13:10:29',29,'2024-04-30 16:47:44',_binary '\0'),(737,'Surgeon',2,29,'2024-05-01 10:56:04',29,'2024-05-01 10:56:04',_binary '\0');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_menu`
--

DROP TABLE IF EXISTS `role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_menu` (
  `role_menu_id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `menu_id` int NOT NULL,
  PRIMARY KEY (`role_menu_id`),
  KEY `role_id` (`role_id`),
  KEY `menu_id` (`menu_id`),
  CONSTRAINT `role_menu_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `role_menu_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_menu`
--

LOCK TABLES `role_menu` WRITE;
/*!40000 ALTER TABLE `role_menu` DISABLE KEYS */;
INSERT INTO `role_menu` VALUES (1,733,1),(2,733,2),(3,733,3),(4,733,4),(5,733,5),(6,733,6),(7,733,7),(8,733,8),(9,733,9),(10,733,10),(11,733,15),(12,733,16),(13,733,17),(14,733,18),(15,733,19),(16,733,20),(17,733,21),(18,733,22),(19,733,23),(20,733,24),(21,734,1),(22,734,2),(23,734,3),(24,734,4),(25,734,5),(26,734,6),(27,734,7),(28,734,8),(29,734,9),(30,734,10),(31,734,15),(32,734,16),(33,734,17),(34,734,18),(35,734,19),(36,734,23),(37,734,24),(48,735,11),(49,735,12),(50,735,13),(51,735,14),(52,737,11),(53,737,12),(54,737,13),(55,737,14);
/*!40000 ALTER TABLE `role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shift`
--

DROP TABLE IF EXISTS `shift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shift` (
  `shift_id` int NOT NULL AUTO_INCREMENT,
  `physician_id` int NOT NULL,
  `state_date` date NOT NULL,
  `is_repeat` bit(1) NOT NULL,
  `week_days` char(7) DEFAULT NULL,
  `repeat_upto` int DEFAULT NULL,
  `created_by` int NOT NULL,
  `created_date` datetime NOT NULL,
  PRIMARY KEY (`shift_id`),
  KEY `physician_id` (`physician_id`),
  KEY `created_by_idx` (`created_by`),
  CONSTRAINT `created_by` FOREIGN KEY (`created_by`) REFERENCES `asp_net_users` (`id`),
  CONSTRAINT `shift_ibfk_1` FOREIGN KEY (`physician_id`) REFERENCES `physician` (`physician_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shift`
--

LOCK TABLES `shift` WRITE;
/*!40000 ALTER TABLE `shift` DISABLE KEYS */;
/*!40000 ALTER TABLE `shift` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shift_detail`
--

DROP TABLE IF EXISTS `shift_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shift_detail` (
  `shift_detail_id` int NOT NULL AUTO_INCREMENT,
  `shift_id` int NOT NULL,
  `shift_date` datetime NOT NULL,
  `region_id` int DEFAULT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `status` tinyint NOT NULL,
  `is_deleted` bit(1) NOT NULL,
  `modified_by` int DEFAULT NULL,
  `modifie_date` datetime DEFAULT NULL,
  `last_running_date` datetime DEFAULT NULL,
  `event_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`shift_detail_id`),
  KEY `shift_id` (`shift_id`),
  KEY `modified_by_idx` (`modified_by`),
  KEY `region_id_idx` (`region_id`),
  CONSTRAINT `modified_by` FOREIGN KEY (`modified_by`) REFERENCES `asp_net_users` (`id`),
  CONSTRAINT `shift_detail_ibfk_1` FOREIGN KEY (`shift_id`) REFERENCES `shift` (`shift_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shift_detail`
--

LOCK TABLES `shift_detail` WRITE;
/*!40000 ALTER TABLE `shift_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `shift_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shift_detail_region`
--

DROP TABLE IF EXISTS `shift_detail_region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shift_detail_region` (
  `shift_detail_region_id` int NOT NULL AUTO_INCREMENT,
  `shift_detail_id` int NOT NULL,
  `region_id` int NOT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`shift_detail_region_id`),
  KEY `shift_detail_id` (`shift_detail_id`),
  KEY `region_id` (`region_id`),
  CONSTRAINT `shift_detail_region_ibfk_1` FOREIGN KEY (`shift_detail_id`) REFERENCES `shift_detail` (`shift_detail_id`),
  CONSTRAINT `shift_detail_region_ibfk_2` FOREIGN KEY (`region_id`) REFERENCES `region` (`region_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shift_detail_region`
--

LOCK TABLES `shift_detail_region` WRITE;
/*!40000 ALTER TABLE `shift_detail_region` DISABLE KEYS */;
/*!40000 ALTER TABLE `shift_detail_region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sms_log`
--

DROP TABLE IF EXISTS `sms_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sms_log` (
  `sms_log_id` int NOT NULL AUTO_INCREMENT,
  `mobile_number` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `confirmation_number` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `admin_id` int DEFAULT NULL,
  `request_id` int DEFAULT NULL,
  `physician_id` int DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `sent_date` datetime DEFAULT NULL,
  `is_sms_sent` bit(1) DEFAULT NULL,
  `sent_tries` int NOT NULL,
  `action` varchar(100) DEFAULT NULL,
  `is_expired` bit(1) DEFAULT NULL,
  `recipient_name` varchar(255) DEFAULT NULL,
  `sms_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sms_log_id`),
  KEY `role_id_idx` (`role_id`),
  KEY `physician_id_idx` (`physician_id`),
  KEY `admin_id_idx` (`admin_id`),
  KEY `request_id_idx` (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=770 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sms_log`
--

LOCK TABLES `sms_log` WRITE;
/*!40000 ALTER TABLE `sms_log` DISABLE KEYS */;
INSERT INTO `sms_log` VALUES (601,'7894561230','GJ030424WORK0002',3,10,434,0,'2024-04-19 12:13:56','2024-04-19 12:13:56',_binary '',1,'2',_binary '\0','RKO Wood',NULL),(603,'7894561230','GJ030424WORK0002',3,10,434,0,'2024-04-19 12:35:55','2024-04-19 12:35:55',_binary '',1,'2',_binary '\0','RKO Wood',NULL),(605,'7894561230','GJ030424WORK0002',3,10,434,0,'2024-04-19 12:40:23','2024-04-19 12:40:23',_binary '',1,'2',_binary '\0','RKO Wood',NULL),(607,'7894561230','GJ030424WORK0002',3,10,434,0,'2024-04-19 14:27:48','2024-04-19 14:27:48',_binary '',1,'2',_binary '\0','RKO Wood',NULL),(616,'9898989898','GJ190424SHBH0001',3,10,610,0,'2024-04-19 15:59:04','2024-04-19 15:59:04',_binary '',1,'2',_binary '\0','Bhavyaa Shah',NULL),(625,'7485961230','GJ190424CHCH0002',3,10,620,0,'2024-04-19 16:27:53','2024-04-19 16:27:53',_binary '',1,'2',_binary '\0','Chandan Chabaiyaa',NULL),(638,'7845129630','GJ190424GUCH0003',3,10,633,0,'2024-04-19 16:57:16','2024-04-19 16:57:16',_binary '',1,'2',_binary '\0','Chirag Gundariya',NULL),(641,'7896541230','GJ190424JOBR0000',3,10,587,0,'2024-04-19 17:07:49','2024-04-19 17:07:49',_binary '',1,'2',_binary '\0','Brinda Joshi',NULL),(643,'7894561230','GJ030424WORK0002',3,10,434,0,'2024-04-19 17:13:08','2024-04-19 17:13:08',_binary '',1,'2',_binary '\0','RKO Wood',NULL),(646,'7896541230','GJ190424JOBR0000',3,10,587,0,'2024-04-22 09:35:20','2024-04-22 09:35:20',_binary '',1,'2',_binary '\0','Brinda Joshi',NULL),(726,'6351627219',NULL,2,10,0,1,'2024-04-28 00:20:44','2024-04-28 00:20:44',_binary '',1,'6',_binary '\0','Kavit Shah',NULL),(769,'7894561230','GJ030424WORK0002',3,10,434,0,'2024-05-01 11:32:40','2024-05-01 11:32:40',_binary '',1,'2',_binary '\0','RKO Wood',NULL);
/*!40000 ALTER TABLE `sms_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `aspnet_user_id` int NOT NULL,
  `first_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `last_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `mobile` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `street` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `city` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `state` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `region_id` int DEFAULT NULL,
  `zipcode` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `str_month` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `int_year` int DEFAULT NULL,
  `int_date` int DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` int DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `is_request_with_email` bit(1) DEFAULT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `aspnet_user_id` (`aspnet_user_id`),
  KEY `region_id_idx` (`region_id`),
  KEY `created_by_idx` (`created_by`),
  KEY `modified_by_idx` (`modified_by`),
  KEY `role_idx` (`role_id`),
  CONSTRAINT `FK4c9epedjxkdfvgb456f12rykp` FOREIGN KEY (`created_by`) REFERENCES `asp_net_users` (`id`),
  CONSTRAINT `FKc260q8rbpd2r8m08ntog83vhb` FOREIGN KEY (`aspnet_user_id`) REFERENCES `asp_net_users` (`id`),
  CONSTRAINT `FKtcxhdexx769fgncq42i5u8uwv` FOREIGN KEY (`modified_by`) REFERENCES `asp_net_users` (`id`),
  CONSTRAINT `role_foreign_key` FOREIGN KEY (`role_id`) REFERENCES `asp_net_roles` (`id`),
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`region_id`) REFERENCES `region` (`region_id`)
) ENGINE=InnoDB AUTO_INCREMENT=779 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (422,421,'Jay!','Shah','miyohop516@acentni.com','+91 1234567890','Gujan Vatika','Rajkot','Gujarat',1,'389001','June',2024,2,421,'2024-04-03 14:10:01',NULL,NULL,0,_binary '\0',_binary '\0',3),(428,427,'RKO','Wood','monibay451@centerf.com','7894561230',NULL,NULL,NULL,NULL,NULL,'April',2024,16,427,'2024-04-03 14:16:45',NULL,'2024-04-03 14:25:54',0,_binary '\0',_binary '\0',3),(440,439,'Raj','Doshi','jihoh48005@centerf.com','1747474747',NULL,NULL,NULL,NULL,NULL,'April',2024,1,NULL,'2024-04-03 14:33:41',NULL,'2024-04-03 14:48:33',0,_binary '\0',_binary '',3),(454,453,'Dere','Sha','asda@gmail.com','7485963210',NULL,NULL,NULL,NULL,NULL,'May',2024,2,NULL,'2024-04-03 16:14:12',NULL,NULL,0,_binary '\0',_binary '',3),(462,461,'sadsa','asdadsada','asdsadasdasd@gmail.com','7485963210',NULL,NULL,NULL,NULL,NULL,'April',2024,20,NULL,'2024-04-03 16:15:56',NULL,NULL,0,_binary '\0',_binary '',3),(469,468,'sadsa','asdadsada','asdsadasdasdasdad@gmail.com','7485963210',NULL,NULL,NULL,NULL,NULL,'April',2024,20,NULL,'2024-04-03 16:20:07',NULL,NULL,0,_binary '\0',_binary '',3),(477,476,'wert','cvbnm','qwerasjhjh@gmail.com','7845129631',NULL,NULL,NULL,NULL,NULL,'April',2024,12,NULL,'2024-04-03 16:22:28',NULL,NULL,0,_binary '\0',_binary '',3),(478,29,'Kavit','Shah','admin@gmail.com','8855449631','13, Kusum Kunj, Mahavir Jain Society','Godhra','Gujarat',1,'389001','April',2024,1,NULL,'2024-04-03 16:22:28',NULL,NULL,0,_binary '\0',_binary '\0',1),(484,483,'Shresths','Bhakta','asdascas@gmail.com','7845129631','asd','asd','Gujarat',1,'389001','January',2024,15,483,'2024-04-04 12:50:04',NULL,NULL,0,_binary '\0',_binary '\0',3),(501,500,'Ginni','Potter','gaxal93643@adstam.com','7845121985',NULL,NULL,NULL,NULL,NULL,'April',2024,17,500,'2024-04-10 10:13:58',NULL,NULL,0,_binary '\0',_binary '\0',3),(509,508,'Lilly','Potter','lovidi4112@agromgt.com','7539518462',NULL,NULL,NULL,NULL,NULL,'April',2024,1,NULL,'2024-04-10 10:17:13',NULL,NULL,0,_binary '\0',_binary '\0',3),(517,516,'Kandarp','Shah','rokosi4878@acentni.com','7845121245',NULL,NULL,NULL,NULL,NULL,'April',2024,2,NULL,'2024-04-10 10:21:35',NULL,NULL,0,_binary '\0',_binary '\0',3),(525,524,'Luna','Lovegood','xores83799@adstam.com','8659742310',NULL,NULL,NULL,NULL,NULL,'April',2024,1,NULL,'2024-04-10 10:26:07',NULL,NULL,0,_binary '\0',_binary '',3),(532,531,'Hiren','Pandya','bikiyo1901@acentni.com','8989898989',NULL,NULL,NULL,NULL,NULL,'April',2024,8,531,'2024-04-10 10:29:15',NULL,NULL,0,_binary '\0',_binary '\0',3),(539,538,'Rocky','Singh','royevef681@abnovel.com','7894561230',NULL,NULL,NULL,NULL,NULL,'April',2024,12,NULL,'2024-04-11 18:52:55',NULL,NULL,0,_binary '\0',_binary '\0',3),(545,544,'Brinda','Joshi','vasel27162@agaseo.com','7896541230',NULL,NULL,NULL,NULL,NULL,'April',2024,1,NULL,'2024-04-11 19:07:22',544,'2024-04-19 11:06:44',0,_binary '\0',_binary '\0',3),(555,554,'Krutarth','Gondaliya','kgpatel12@gmail.com','8547596523',NULL,NULL,NULL,NULL,NULL,'April',2024,1,NULL,'2024-04-12 11:50:10',NULL,NULL,0,_binary '\0',_binary '\0',3),(557,556,'Kavit','Shah','kavit15@gmail.com','6351627219','Bamroli Road','Godhra','Gujarat',1,'389001','January',2003,15,NULL,'2024-04-17 09:50:10',NULL,'2024-04-17 09:50:10',0,_binary '\0',_binary '\0',2),(568,567,'Krutarth','Gondaliya','krutarth21@gmail.com','8849430122','AG Chowk','Rajkot','Gujarat',1,'360005','April',2024,9,567,'2024-04-17 10:05:23',NULL,NULL,0,_binary '\0',_binary '\0',2),(570,569,'Jemis','Lathiya','jemis69@gmail.com','8529637410','Lower Parol','Mumbai','Maharashtra',2,'40009','April',2024,1,569,'2024-04-17 10:11:38',NULL,NULL,0,_binary '\0',_binary '\0',2),(572,571,'Chandan','Chabbaiya','chandan03@gmail.com','7845128956','Silver Falls','Panchmahri','Madhya Pradesh',3,'461881','April',2024,3,571,'2024-04-17 10:19:51',NULL,NULL,0,_binary '\0',_binary '\0',2),(609,608,'Bhavyaa','Shah','yegim25818@acname.com','7878541236',NULL,NULL,NULL,NULL,NULL,'April',2024,11,NULL,'2024-04-19 15:53:42',NULL,NULL,0,_binary '\0',_binary '\0',3),(619,618,'Chandan','Chabaiyaa','tejoric652@acname.com','6595945896',NULL,NULL,NULL,NULL,NULL,'April',2024,2,NULL,'2024-04-19 16:21:33',NULL,NULL,0,_binary '\0',_binary '\0',3),(632,631,'Chirag','Gundariya','decero4798@eryod.com','7845129630',NULL,NULL,NULL,NULL,NULL,'April',2024,9,NULL,'2024-04-19 16:55:38',NULL,NULL,0,_binary '\0',_binary '\0',3),(717,715,'Derek','Shah','derekshah324@gmail.com','7894561231','13, Kusum Kunj, Mahavir Jain Society','Godhraa','Gujarat',1,'389001',NULL,0,0,29,'2024-04-26 19:39:26',29,'2024-04-27 17:28:36',1,_binary '\0',_binary '\0',2),(740,738,'Aashiv','Shah','aashiv73@gmail.com','7894561230','13, Kusum Kunj Mahavair Jain Society','Godhra','Gujarat',1,'389001',NULL,0,0,29,'2024-05-01 11:03:34',29,'2024-05-01 11:03:34',1,_binary '\0',_binary '\0',2),(746,744,'Samyak','Shah','shahsamyak@gmail.com','123','B-204 Samasavli','Vadodra','Gujarat',1,'389001',NULL,0,0,29,'2024-05-01 11:10:30',29,'2024-05-01 11:10:30',1,_binary '\0',_binary '\0',2),(751,750,'Kavir','Shah','asdjau@gmail.com','7894561230',NULL,NULL,NULL,NULL,NULL,'May',2024,21,NULL,'2024-05-01 11:28:09',NULL,NULL,0,_binary '\0',_binary '\0',3),(757,756,'Samyak','Shankeshra','samyakk@gmail.com','7894561230',NULL,NULL,NULL,NULL,NULL,'May',2024,2,NULL,'2024-05-01 11:29:48',NULL,NULL,0,_binary '\0',_binary '\0',3),(763,762,'Derek','Shah','derek@gmail.in','7894561230',NULL,NULL,NULL,NULL,NULL,'May',2024,17,NULL,'2024-05-01 11:31:53',NULL,NULL,0,_binary '\0',_binary '\0',3),(778,777,'Kavit','Shah','ascnsjn@gmail.com','7894561230',NULL,NULL,NULL,NULL,NULL,'May',2024,3,NULL,'2024-05-01 11:52:43',NULL,NULL,0,_binary '\0',_binary '',3);
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

-- Dump completed on 2024-05-01 12:00:59
