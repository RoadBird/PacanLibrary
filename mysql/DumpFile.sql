-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authors` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `first_name_id` bigint(20) unsigned NOT NULL,
  `middle_name_id` bigint(20) unsigned NOT NULL,
  `last_name_id` bigint(20) unsigned NOT NULL,
  `author_alias` varchar(225) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `date_gone` date DEFAULT NULL,
  `author_description` text,
  `author_generes` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `id_authors` (`id`),
  KEY `first_namefk_idx` (`first_name_id`),
  KEY `middle_namefk_idx` (`middle_name_id`),
  KEY `last_namefk_idx` (`last_name_id`),
  CONSTRAINT `authors_ibfk_1` FOREIGN KEY (`first_name_id`) REFERENCES `first_names` (`id`),
  CONSTRAINT `authors_ibfk_2` FOREIGN KEY (`middle_name_id`) REFERENCES `middle_names` (`id`),
  CONSTRAINT `authors_ibfk_3` FOREIGN KEY (`last_name_id`) REFERENCES `last_names` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (3,2,2,2,'Ptushkin','1985-03-16','2010-03-18',NULL,NULL),(4,2,1,1,'Uaz',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `tittle` varchar(100) NOT NULL,
  `id_authors` bigint(20) unsigned NOT NULL,
  `release_date` date DEFAULT NULL,
  `book_description` text,
  `isbn` varchar(50) DEFAULT NULL,
  `cover_path` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `id_books` (`id`),
  KEY `books_ibfk_1` (`id_authors`),
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`id_authors`) REFERENCES `authors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'It\'s my life',3,'2000-05-28','VIP secrets revieals','ABC-1234','https://vpupkin.com/portrait.jpg'),(2,'Poems',4,'1956-06-12',NULL,'test-2345',NULL);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `first_names`
--

DROP TABLE IF EXISTS `first_names`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `first_names` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `fname` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `fname` (`fname`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `first_names`
--

LOCK TABLES `first_names` WRITE;
/*!40000 ALTER TABLE `first_names` DISABLE KEYS */;
INSERT INTO `first_names` VALUES (1,''),(2,'Vasya');
/*!40000 ALTER TABLE `first_names` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `last_names`
--

DROP TABLE IF EXISTS `last_names`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `last_names` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `lname` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `lname` (`lname`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `last_names`
--

LOCK TABLES `last_names` WRITE;
/*!40000 ALTER TABLE `last_names` DISABLE KEYS */;
INSERT INTO `last_names` VALUES (1,''),(2,'Pupkin');
/*!40000 ALTER TABLE `last_names` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `middle_names`
--

DROP TABLE IF EXISTS `middle_names`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `middle_names` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `mname` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `mname` (`mname`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `middle_names`
--

LOCK TABLES `middle_names` WRITE;
/*!40000 ALTER TABLE `middle_names` DISABLE KEYS */;
INSERT INTO `middle_names` VALUES (1,''),(2,'Romualdpvich');
/*!40000 ALTER TABLE `middle_names` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-26 21:34:51
