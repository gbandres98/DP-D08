start transaction;
drop database if exists `acmerendezvous`;
create database `acmerendezvous`;
use `acmerendezvous`;
create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';
grant select, insert, update, delete 
	on `acmerendezvous`.* to 'acme-user'@'%';
grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `acmerendezvous`.* to 'acme-manager'@'%';
-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: AcmeRendezvous
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `birthDate` date DEFAULT NULL,
  `emailAddress` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_cgls5lrufx91ufsyh467spwa3` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `birthDate` date DEFAULT NULL,
  `emailAddress` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_idt4b4u259p6vs4pyr9lax4eg` (`userAccount_id`),
  CONSTRAINT `FK_idt4b4u259p6vs4pyr9lax4eg` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (4,0,'1986-02-16','Admin@email.com','adminname','AdminphoneNumber','AdminpostalAdrdress','Adminsurname',3);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcement` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `moment` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `rendezvous_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ff49x8lk2mmqi9noe8xwqg0ah` (`rendezvous_id`),
  CONSTRAINT `FK_ff49x8lk2mmqi9noe8xwqg0ah` FOREIGN KEY (`rendezvous_id`) REFERENCES `rendezvous` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcement`
--

LOCK TABLES `announcement` WRITE;
/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `question_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_10g8xii7lw9kq0kcsobgmtw72` (`question_id`),
  CONSTRAINT `FK_10g8xii7lw9kq0kcsobgmtw72` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` date DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `parentComment_id` int(11) DEFAULT NULL,
  `rendezvous_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t4l8ri4kvik847hh7ei7y1l4u` (`parentComment_id`),
  KEY `FK_cub35s8xr5ip2yamdkqky5dxf` (`rendezvous_id`),
  KEY `FK_jhvt6d9ap8gxv67ftrmshdfhj` (`user_id`),
  CONSTRAINT `FK_jhvt6d9ap8gxv67ftrmshdfhj` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_cub35s8xr5ip2yamdkqky5dxf` FOREIGN KEY (`rendezvous_id`) REFERENCES `rendezvous` (`id`),
  CONSTRAINT `FK_t4l8ri4kvik847hh7ei7y1l4u` FOREIGN KEY (`parentComment_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gpscoordinates`
--

DROP TABLE IF EXISTS `gpscoordinates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gpscoordinates` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gpscoordinates`
--

LOCK TABLES `gpscoordinates` WRITE;
/*!40000 ALTER TABLE `gpscoordinates` DISABLE KEYS */;
/*!40000 ALTER TABLE `gpscoordinates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `rendezvous_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ejk14ged2t2jaquwya6f97awi` (`rendezvous_id`),
  CONSTRAINT `FK_ejk14ged2t2jaquwya6f97awi` FOREIGN KEY (`rendezvous_id`) REFERENCES `rendezvous` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rendezvous`
--

DROP TABLE IF EXISTS `rendezvous`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rendezvous` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `adultOnly` bit(1) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `finalVersion` bit(1) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `GPSCoordinates_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_mterpdyl0l35sx03iwmcp5xi2` (`finalVersion`),
  KEY `FK_qoibhp1nh1vbmy8lddc1rc8qq` (`GPSCoordinates_id`),
  KEY `FK_3jo20dmf5k76cjtc98xqjmaoy` (`user_id`),
  CONSTRAINT `FK_3jo20dmf5k76cjtc98xqjmaoy` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_qoibhp1nh1vbmy8lddc1rc8qq` FOREIGN KEY (`GPSCoordinates_id`) REFERENCES `gpscoordinates` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rendezvous`
--

LOCK TABLES `rendezvous` WRITE;
/*!40000 ALTER TABLE `rendezvous` DISABLE KEYS */;
/*!40000 ALTER TABLE `rendezvous` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rendezvous_rendezvous`
--

DROP TABLE IF EXISTS `rendezvous_rendezvous`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rendezvous_rendezvous` (
  `Rendezvous_id` int(11) NOT NULL,
  `rendezvouses_id` int(11) NOT NULL,
  KEY `FK_d4jmno5qg9i427qenlp5t0l5l` (`rendezvouses_id`),
  KEY `FK_fuhs4y1oqtlba5sencebgmosa` (`Rendezvous_id`),
  CONSTRAINT `FK_fuhs4y1oqtlba5sencebgmosa` FOREIGN KEY (`Rendezvous_id`) REFERENCES `rendezvous` (`id`),
  CONSTRAINT `FK_d4jmno5qg9i427qenlp5t0l5l` FOREIGN KEY (`rendezvouses_id`) REFERENCES `rendezvous` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rendezvous_rendezvous`
--

LOCK TABLES `rendezvous_rendezvous` WRITE;
/*!40000 ALTER TABLE `rendezvous_rendezvous` DISABLE KEYS */;
/*!40000 ALTER TABLE `rendezvous_rendezvous` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rsvp`
--

DROP TABLE IF EXISTS `rsvp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rsvp` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `cancelled` bit(1) NOT NULL,
  `joined` bit(1) NOT NULL,
  `rendezvous_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_s95wan5c1jg6mwu12co9ge8d` (`joined`),
  KEY `FK_n2jrtjuxh1nfd91vvo2jsyews` (`rendezvous_id`),
  KEY `FK_do8m6p15stxyd5cvernhv2x6y` (`user_id`),
  CONSTRAINT `FK_do8m6p15stxyd5cvernhv2x6y` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_n2jrtjuxh1nfd91vvo2jsyews` FOREIGN KEY (`rendezvous_id`) REFERENCES `rendezvous` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rsvp`
--

LOCK TABLES `rsvp` WRITE;
/*!40000 ALTER TABLE `rsvp` DISABLE KEYS */;
/*!40000 ALTER TABLE `rsvp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rsvp_answer`
--

DROP TABLE IF EXISTS `rsvp_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rsvp_answer` (
  `RSVP_id` int(11) NOT NULL,
  `answers_id` int(11) NOT NULL,
  UNIQUE KEY `UK_7hxxcr187s6galgqmnxr0ushk` (`answers_id`),
  KEY `FK_67wer14qe7vm6ymdtwnmx706s` (`RSVP_id`),
  CONSTRAINT `FK_67wer14qe7vm6ymdtwnmx706s` FOREIGN KEY (`RSVP_id`) REFERENCES `rsvp` (`id`),
  CONSTRAINT `FK_7hxxcr187s6galgqmnxr0ushk` FOREIGN KEY (`answers_id`) REFERENCES `answer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rsvp_answer`
--

LOCK TABLES `rsvp_answer` WRITE;
/*!40000 ALTER TABLE `rsvp_answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `rsvp_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `birthDate` date DEFAULT NULL,
  `emailAddress` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o6s94d43co03sx067ili5760c` (`userAccount_id`),
  CONSTRAINT `FK_o6s94d43co03sx067ili5760c` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (3,0,'21232f297a57a5a743894a0e4a801fc3','admin');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (3,'ADMINISTRATOR');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-27 18:31:14
commit;