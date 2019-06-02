CREATE DATABASE  IF NOT EXISTS `TaxiDB` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `TaxiDB`;
-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: TaxiDB
-- ------------------------------------------------------
-- Server version	5.7.25

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
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `driver` (
  `iddriver` int(11) NOT NULL AUTO_INCREMENT,
  `first_name_ua` varchar(45) NOT NULL,
  `last_name_ua` varchar(45) NOT NULL,
  `first_name_en` varchar(45) NOT NULL,
  `last_name_en` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `car_type` varchar(45) NOT NULL,
  `rating` float NOT NULL,
  `rides_count` int(11) NOT NULL,
  PRIMARY KEY (`iddriver`),
  UNIQUE KEY `iddriver_UNIQUE` (`iddriver`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` VALUES (1,'Домінік','Торетто','Dominic','Toretto','dt@fmail.not','sedan',0,0),(2,'Браян','О\'Коннор','Brian','O`Connor','b0c@fmail.not','sedan',0,0),(3,'Френк','Мартін','Frank','Martin','m_f@fmail.not','sedan',0,3),(4,'Макс','Роккатанський','Max','Rockatansky','themax@fmail.not','suv',0,1),(5,'Джеймс','Бонд','James','Bond','007@fmail.not','sedan',0,0),(6,'Джейсон','Борн','Jason','Bourne','Foma_KINAEV@fmail.not','sedan',0,2),(7,'Летті','Ортіз','Letty','Ortiz','letty@fmail.not','suv',0,0),(8,'Рендал','Реінз','Randall','Raines','RR@fmail.not','wagon',0,0),(9,'Джон','Уік','John','Wick','wick@fmail.not','van',0,0),(10,'Роман','Пірс','Roman','Pearce','pierce_r@fmail.not','wagon',0,0);
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ride`
--

DROP TABLE IF EXISTS `ride`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ride` (
  `idride` int(11) NOT NULL AUTO_INCREMENT,
  `user_iduser` int(11) NOT NULL,
  `driver_iddriver` int(11) NOT NULL,
  `from_addr` varchar(100) NOT NULL,
  `to_addr` varchar(100) NOT NULL,
  `dist` int(11) NOT NULL,
  `cost` int(11) NOT NULL,
  `date_time` datetime NOT NULL,
  PRIMARY KEY (`idride`),
  UNIQUE KEY `idride_UNIQUE` (`idride`),
  KEY `fk_ride_user_idx` (`user_iduser`),
  KEY `fk_ride_driver1_idx` (`driver_iddriver`),
  CONSTRAINT `fk_ride_driver1` FOREIGN KEY (`driver_iddriver`) REFERENCES `driver` (`iddriver`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ride_user` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ride`
--

LOCK TABLES `ride` WRITE;
/*!40000 ALTER TABLE `ride` DISABLE KEYS */;
INSERT INTO `ride` VALUES (57,20,3,'Liskivska St, 12, Kyiv, Ukraine, 02000','Khreschatyk St, 1, Kyiv, Ukraine, 02000',14,88,'2019-05-30 16:10:56'),(58,20,6,'Khreschatyk St, 1, Kyiv, Ukraine, 02000','Liskivska St, 12, Kyiv, Ukraine, 02000',13,167,'2019-05-30 16:11:46'),(59,20,3,'Liskivska St, 12, Kyiv, Ukraine, 02000','Volodymyrska St, 1, Kyiv, Ukraine, 02000',15,185,'2019-05-30 19:05:33'),(60,20,4,'Volodymyrska St, 1, Kyiv, Ukraine, 02000','Liskivska St, 12, Kyiv, Ukraine, 02000',14,228,'2019-05-30 20:14:50');
/*!40000 ALTER TABLE `ride` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ride_has_special`
--

DROP TABLE IF EXISTS `ride_has_special`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ride_has_special` (
  `ride_idride` int(11) NOT NULL,
  `special_idspecial` int(11) NOT NULL,
  PRIMARY KEY (`ride_idride`,`special_idspecial`),
  KEY `fk_ride_has_special_special1_idx` (`special_idspecial`),
  KEY `fk_ride_has_special_ride1_idx` (`ride_idride`),
  CONSTRAINT `fk_ride_has_special_ride1` FOREIGN KEY (`ride_idride`) REFERENCES `ride` (`idride`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ride_has_special_special1` FOREIGN KEY (`special_idspecial`) REFERENCES `special` (`idspecial`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ride_has_special`
--

LOCK TABLES `ride_has_special` WRITE;
/*!40000 ALTER TABLE `ride_has_special` DISABLE KEYS */;
INSERT INTO `ride_has_special` VALUES (57,3);
/*!40000 ALTER TABLE `ride_has_special` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `special`
--

DROP TABLE IF EXISTS `special`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `special` (
  `idspecial` int(11) NOT NULL AUTO_INCREMENT,
  `name_en` varchar(45) NOT NULL,
  `name_ua` varchar(45) NOT NULL,
  `desc_en` varchar(500) NOT NULL,
  `desc_ua` varchar(500) NOT NULL,
  `discount_type` varchar(11) NOT NULL,
  `discount_amount` int(11) NOT NULL,
  `special_type` varchar(20) NOT NULL,
  `rule` varchar(65) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`idspecial`),
  UNIQUE KEY `idSpecial_UNIQUE` (`idspecial`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `special`
--

LOCK TABLES `special` WRITE;
/*!40000 ALTER TABLE `special` DISABLE KEYS */;
INSERT INTO `special` VALUES (1,'Night rides','Нічні кілометри','10% off during night hours.','Знижка 10% в нічні години.','percent',10,'day_hours','0,6',1),(2,'Weekend','Вихідні','Discount of 20 hrn. on weekends.','Знижки 20 грн. у вихідні.','raw_cost',20,'week_days','SATURDAYSUNDAY',1),(3,'Half price','Ціни навпіл','First and every seventh ride at half-price.','Знижка 50% на першу та кожну сьому поїздку.','percent',50,'ride_count','7',1);
/*!40000 ALTER TABLE `special` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `first_name_ua` varchar(45) NOT NULL,
  `last_name_ua` varchar(45) NOT NULL,
  `first_name_en` varchar(45) NOT NULL,
  `last_name_en` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `pass` varchar(60) NOT NULL,
  `money_spent` int(11) NOT NULL,
  `rides_count` int(11) NOT NULL,
  `personal_discount` int(11) NOT NULL,
  `additional_discount` int(11) NOT NULL,
  `role` varchar(5) NOT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `iduser_UNIQUE` (`iduser`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Анатолій','Буківський','Anatolii','Bukivskii','ap.bukivskii@gmail.com','admin','$2a$10$hUUaDsko6K437MnshqHIv.ujBeJSjm7aBeJkRV/ZIS7mUB9RkHzDG',0,0,10,0,'admin'),(20,'Чудовий','Тестувальник','Great','Tester','tester@fmail.not','tester','$2a$10$gpU4PJCgix3GNAqjfOaKf.S/GX8AUNebvOasvdSqPMuXSUwI0/NpW',668,4,0,0,'user'),(25,'Новий','Тестувальник','New','Tester','teester3@fmail.not','tester3','$2a$10$bw2kGbrQmgV.W5AcuEfsoebntqu8SxJnaPMpWj7SdGC8jPaziyzj2',0,0,0,0,'user');
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

-- Dump completed on 2019-06-02 19:07:25
