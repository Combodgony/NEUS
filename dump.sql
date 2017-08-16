-- MySQL dump 10.13  Distrib 5.1.41, for Win32 (ia32)
--
-- Host: localhost    Database: terminal
-- ------------------------------------------------------
-- Server version	5.1.41-community

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
-- Table structure for table `admission`
--

DROP TABLE IF EXISTS `admission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idContract` int(11) NOT NULL,
  `idCargo` int(11) DEFAULT NULL,
  `idDraionLocation` int(11) DEFAULT NULL,
  `idTank` int(11) DEFAULT NULL,
  `volume` int(11) DEFAULT NULL,
  `factBeginDate` datetime DEFAULT NULL,
  `factEndDate` datetime DEFAULT NULL,
  `idStationaryStorage` int(11) DEFAULT NULL,
  `planBeginDate` datetime DEFAULT NULL,
  `plan` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK_admission_cargo_id` (`idCargo`),
  KEY `FK_admission_drainlocation_id` (`idDraionLocation`),
  KEY `FK_admission_stationarystorage_id` (`idStationaryStorage`),
  KEY `FK_admission_tank_id` (`idTank`),
  KEY `FK_admission_treaty_id` (`idContract`),
  CONSTRAINT `FK_admission_cargo_id` FOREIGN KEY (`idCargo`) REFERENCES `cargo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_admission_drainlocation_id` FOREIGN KEY (`idDraionLocation`) REFERENCES `drainlocation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_admission_stationarystorage_id` FOREIGN KEY (`idStationaryStorage`) REFERENCES `stationarystorage` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_admission_tank_id` FOREIGN KEY (`idTank`) REFERENCES `tank` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_admission_treaty_id` FOREIGN KEY (`idContract`) REFERENCES `contract` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=282;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admission`
--

LOCK TABLES `admission` WRITE;
/*!40000 ALTER TABLE `admission` DISABLE KEYS */;
INSERT INTO `admission` VALUES (1,1,1,4,102,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-16 15:59:32',1),(7,1,1,1,103,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 01:37:00',1),(8,1,1,3,104,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 03:23:30',1),(9,1,1,4,105,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-16 16:50:43',1),(10,1,1,5,106,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 03:29:30',1),(11,1,1,1,107,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-16 16:01:52',1),(12,1,1,7,108,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 03:58:00',1),(13,1,1,8,109,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 04:10:30',1),(14,1,1,1,110,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 05:45:00',1),(15,1,1,1,111,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-16 16:51:56',1),(16,1,1,3,112,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 04:32:30',1),(17,1,1,4,113,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 09:37:00',1),(18,1,1,5,114,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 06:03:30',1),(19,1,1,6,115,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-12 13:28:30',1),(20,1,1,7,116,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 02:42:30',1),(21,1,1,8,117,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 06:59:30',1),(22,1,1,11,118,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 05:50:00',1),(23,1,1,2,119,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 04:05:30',1),(24,1,1,3,120,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 05:35:30',1),(25,1,1,4,121,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 04:36:30',1),(26,1,1,5,122,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 04:20:00',1),(27,1,1,6,123,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 04:35:30',1),(28,1,1,7,124,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 04:50:30',1),(29,1,1,4,125,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-16 18:32:21',1),(30,1,1,1,126,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-16 17:41:56',1),(31,1,1,2,127,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 09:07:00',1),(32,1,1,3,128,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 06:25:30',1),(33,1,1,1,129,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 10:32:00',1),(34,1,1,5,130,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 09:02:00',1),(35,1,1,6,131,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 06:24:30',1),(36,1,1,7,132,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 07:32:00',1),(37,1,1,8,133,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 05:05:30',1),(38,1,1,11,134,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 09:10:00',1),(39,1,1,11,135,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 11:55:45',1),(40,1,1,11,136,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 02:43:00',1),(41,1,1,4,137,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 01:54:00',1),(42,1,1,5,138,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 05:10:30',1),(43,1,1,6,138,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 05:29:30',1),(44,1,1,7,140,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 06:41:00',1),(45,1,1,8,141,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 03:17:00',1),(46,1,1,1,142,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 02:34:30',1),(47,1,1,2,143,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 07:13:00',1),(48,1,1,2,144,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 02:25:30',1),(49,1,1,3,145,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 01:39:30',1),(50,1,1,5,146,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 06:59:00',1),(51,1,1,6,147,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 01:30:30',1),(52,1,1,7,148,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 01:52:00',1),(53,1,1,8,149,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 02:22:00',1),(54,1,1,11,150,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 07:10:00',1),(55,1,1,11,151,30,'2017-08-20 00:00:00','2017-08-20 00:00:00',NULL,'2017-08-10 03:46:30',1);
/*!40000 ALTER TABLE `admission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargo`
--

DROP TABLE IF EXISTS `cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=5461;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
INSERT INTO `cargo` VALUES (1,'Соняшникова олія'),(2,'Рапсова олія'),(3,'Меласа');
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `adress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=4096;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Дніпро','Україна, Миколаївська обл., м. Новий Буг вул. В\'язов 34 '),(2,'Інгул','Україна, м. Київ, Заводська 34 а'),(3,'Зоря','Адреса організації \"Зоря\"'),(4,'Буг','Адреса огранізації \"Буг\"');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contentcontract`
--

DROP TABLE IF EXISTS `contentcontract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contentcontract` (
  `idCargo` int(11) NOT NULL,
  `volume` int(11) NOT NULL,
  `idContract` int(11) NOT NULL,
  KEY `FK_contentcontract_cargo_id` (`idCargo`),
  KEY `FK_contentcontract_contract_id` (`idContract`),
  CONSTRAINT `FK_contentcontract_cargo_id` FOREIGN KEY (`idCargo`) REFERENCES `cargo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_contentcontract_contract_id` FOREIGN KEY (`idContract`) REFERENCES `contract` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=5461;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contentcontract`
--

LOCK TABLES `contentcontract` WRITE;
/*!40000 ALTER TABLE `contentcontract` DISABLE KEYS */;
INSERT INTO `contentcontract` VALUES (1,1500,1),(1,1000,2),(2,2000,2),(1,500,4),(1,200,5),(3,300,6),(2,250,6),(1,20,7),(1,1500,8),(2,500,8),(3,125,9),(3,150,10);
/*!40000 ALTER TABLE `contentcontract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idClient` int(11) NOT NULL,
  `number` varchar(50) NOT NULL,
  `beginDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_treaty_client_id` (`idClient`),
  CONSTRAINT `FK_treaty_client_id` FOREIGN KEY (`idClient`) REFERENCES `client` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=8192;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` VALUES (1,1,'ДО-51 R','2017-08-03 00:00:00','2017-08-23 00:00:00'),(2,4,'ДА-1','2017-06-15 11:43:29','2017-08-31 11:43:34'),(4,2,'qw123 -09','2017-08-01 00:00:00','2017-08-31 00:00:00'),(5,1,'fgh','2017-08-09 00:00:00','2017-08-09 00:00:00'),(6,1,'АК-264','2017-08-16 00:00:00','2017-08-09 00:00:00'),(7,2,'1526','2017-08-09 00:00:00','2017-08-09 00:00:00'),(8,3,'ДО-52','2017-08-21 00:00:00','2017-10-31 00:00:00'),(9,3,'TRK-50','2017-08-03 00:00:00','2017-08-13 00:00:00'),(10,4,'MRT-536','2017-08-14 00:00:00','2017-08-31 00:00:00');
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dependencyadmission`
--

DROP TABLE IF EXISTS `dependencyadmission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dependencyadmission` (
  `idDependent` int(11) NOT NULL,
  `idIndependent` int(11) NOT NULL,
  KEY `FK_dependencyadmission` (`idIndependent`),
  KEY `FK_dependencyadmission_admission_id` (`idDependent`),
  CONSTRAINT `FK_dependencyadmission` FOREIGN KEY (`idIndependent`) REFERENCES `admission` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_dependencyadmission_admission_id` FOREIGN KEY (`idDependent`) REFERENCES `admission` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dependencyadmission`
--

LOCK TABLES `dependencyadmission` WRITE;
/*!40000 ALTER TABLE `dependencyadmission` DISABLE KEYS */;
INSERT INTO `dependencyadmission` VALUES (1,9),(9,29),(11,15),(15,30),(30,29);
/*!40000 ALTER TABLE `dependencyadmission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drainlocation`
--

DROP TABLE IF EXISTS `drainlocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drainlocation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(60) DEFAULT NULL,
  `idEstakada` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_drainlocation_estakada_id` (`idEstakada`),
  CONSTRAINT `FK_drainlocation_estakada_id` FOREIGN KEY (`idEstakada`) REFERENCES `estakada` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=1638;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drainlocation`
--

LOCK TABLES `drainlocation` WRITE;
/*!40000 ALTER TABLE `drainlocation` DISABLE KEYS */;
INSERT INTO `drainlocation` VALUES (1,'1',1),(2,'2',1),(3,'3',1),(4,'4',1),(5,'1',3),(6,'2',3),(7,'3',3),(8,'4',3),(9,'1',2),(10,'2',2),(11,'A-25',1);
/*!40000 ALTER TABLE `drainlocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estakada`
--

DROP TABLE IF EXISTS `estakada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estakada` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idTypeEstakada` int(11) NOT NULL,
  `number` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_estakada_typeestakada_id` (`idTypeEstakada`),
  CONSTRAINT `FK_estakada_typeestakada_id` FOREIGN KEY (`idTypeEstakada`) REFERENCES `typeestakada` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=5461;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estakada`
--

LOCK TABLES `estakada` WRITE;
/*!40000 ALTER TABLE `estakada` DISABLE KEYS */;
INSERT INTO `estakada` VALUES (1,1,'1'),(2,2,'1'),(3,1,'2');
/*!40000 ALTER TABLE `estakada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pumping`
--

DROP TABLE IF EXISTS `pumping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pumping` (
  `id` int(11) NOT NULL DEFAULT '0',
  `idSource` int(11) NOT NULL,
  `idReceiver` int(11) NOT NULL,
  `beginDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `Volume` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pumping_stationarystorage_id` (`idSource`),
  KEY `FK_pumping1_stationarystorage_id` (`idReceiver`),
  CONSTRAINT `FK_pumping1_stationarystorage_id` FOREIGN KEY (`idReceiver`) REFERENCES `stationarystorage` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_pumping_stationarystorage_id` FOREIGN KEY (`idSource`) REFERENCES `stationarystorage` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pumping`
--

LOCK TABLES `pumping` WRITE;
/*!40000 ALTER TABLE `pumping` DISABLE KEYS */;
/*!40000 ALTER TABLE `pumping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pumpingtotanker`
--

DROP TABLE IF EXISTS `pumpingtotanker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pumpingtotanker` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idTanker` int(11) NOT NULL,
  `idStationaryStorage` int(11) NOT NULL,
  `idCargo` int(11) NOT NULL,
  `volume` int(11) NOT NULL,
  `status` enum('План','Виконано') NOT NULL DEFAULT 'План',
  PRIMARY KEY (`id`),
  KEY `FK_pumpingtotanker_cargo_id` (`idCargo`),
  KEY `FK_pumpingtotanker_stationarystorage_id` (`idStationaryStorage`),
  KEY `FK_pumpingtotanker_tanker_id` (`idTanker`),
  CONSTRAINT `FK_pumpingtotanker_cargo_id` FOREIGN KEY (`idCargo`) REFERENCES `cargo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_pumpingtotanker_stationarystorage_id` FOREIGN KEY (`idStationaryStorage`) REFERENCES `stationarystorage` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_pumpingtotanker_tanker_id` FOREIGN KEY (`idTanker`) REFERENCES `tanker` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pumpingtotanker`
--

LOCK TABLES `pumpingtotanker` WRITE;
/*!40000 ALTER TABLE `pumpingtotanker` DISABLE KEYS */;
/*!40000 ALTER TABLE `pumpingtotanker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipment`
--

DROP TABLE IF EXISTS `shipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idContract` int(11) NOT NULL,
  `idCargo` int(11) NOT NULL,
  `idPumpingTanker` int(11) DEFAULT NULL,
  `idTanker` int(11) NOT NULL,
  `beginDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `volume` int(11) NOT NULL,
  `status` enum('План','Виконано') NOT NULL DEFAULT 'План',
  PRIMARY KEY (`id`),
  KEY `FK_shioment_cargo_id` (`idCargo`),
  KEY `FK_shioment_contract_id` (`idContract`),
  KEY `FK_shioment_pumpingtotanker_id` (`idPumpingTanker`),
  KEY `FK_shioment_tanker_id` (`idTanker`),
  CONSTRAINT `FK_shioment_cargo_id` FOREIGN KEY (`idCargo`) REFERENCES `cargo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_shioment_contract_id` FOREIGN KEY (`idContract`) REFERENCES `contract` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_shioment_pumpingtotanker_id` FOREIGN KEY (`idPumpingTanker`) REFERENCES `pumpingtotanker` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_shioment_tanker_id` FOREIGN KEY (`idTanker`) REFERENCES `tanker` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipment`
--

LOCK TABLES `shipment` WRITE;
/*!40000 ALTER TABLE `shipment` DISABLE KEYS */;
/*!40000 ALTER TABLE `shipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stationarystorage`
--

DROP TABLE IF EXISTS `stationarystorage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stationarystorage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) NOT NULL,
  `idTypeStationaryStorage` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tank_typetank_id` (`idTypeStationaryStorage`),
  CONSTRAINT `FK_tank_typetank_id` FOREIGN KEY (`idTypeStationaryStorage`) REFERENCES `typestationarystorage` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=4096 COMMENT='цистерна';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stationarystorage`
--

LOCK TABLES `stationarystorage` WRITE;
/*!40000 ALTER TABLE `stationarystorage` DISABLE KEYS */;
INSERT INTO `stationarystorage` VALUES (1,'1',1),(2,'2',1),(3,'3',1),(4,'1',6);
/*!40000 ALTER TABLE `stationarystorage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tank`
--

DROP TABLE IF EXISTS `tank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(50) NOT NULL,
  `idTypeTank` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tank_typetank_id1` (`idTypeTank`),
  CONSTRAINT `FK_tank_typetank_id1` FOREIGN KEY (`idTypeTank`) REFERENCES `typetank` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=242 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=117;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tank`
--

LOCK TABLES `tank` WRITE;
/*!40000 ALTER TABLE `tank` DISABLE KEYS */;
INSERT INTO `tank` VALUES (102,'LJ5470WE',1),(103,'VK6767WF',1),(104,'LR6185EQ',1),(105,'UU5226VC',1),(106,'XT6815IF',1),(107,'ZJ3194MB',1),(108,'YP6147NN',1),(109,'FA1069UF',1),(110,'ZH6389CR',1),(111,'CL7680RL',1),(112,'ZZ3821IB',1),(113,'UF3211FL',1),(114,'GY5713FJ',1),(115,'HN6935NC',1),(116,'WD3462WV',1),(117,'BR6170ZY',1),(118,'DN4362VR',1),(119,'ZE6231HW',1),(120,'OH9805ZO',1),(121,'PI3833HR',1),(122,'TQ6862LI',1),(123,'OP2039ON',1),(124,'EU7971EK',1),(125,'TR7603AF',1),(126,'OX2124SC',1),(127,'QE4793TH',1),(128,'QX6198PL',1),(129,'GN9311YF',1),(130,'FI5945KA',1),(131,'CO3301KD',1),(132,'ME4590FV',1),(133,'CU5421UL',1),(134,'DO2994GO',1),(135,'BN9935HE',1),(136,'RG6174DR',1),(137,'XH2700GL',1),(138,'RY4197QW',1),(139,'QI6037JS',1),(140,'GB9248RT',1),(141,'XR6569AQ',1),(142,'XE1102IB',1),(143,'QC5848HT',1),(144,'LN1943OI',1),(145,'WB2241QJ',1),(146,'FK8205QQ',1),(147,'CU2866KX',1),(148,'SR1121NE',1),(149,'BI7234SY',1),(150,'OB7818HX',1),(151,'LD6313EU',1),(152,'FC9403YZ',1),(153,'NN3776IE',1),(154,'YG6792XU',1),(155,'PU3175DP',1),(156,'DD4551YF',1),(157,'ZX8538AR',1),(158,'XJ4568SB',1),(159,'GP3420NY',1),(160,'HE7744RE',1),(161,'KP7745TL',1),(162,'IY1291LD',1),(163,'LC9457BU',1),(164,'AZ5181YG',1),(165,'YP1361CK',1),(166,'AG9380HH',1),(167,'ZI8931NL',1),(168,'UI3789YM',1),(169,'FN7219MM',1),(170,'RH1364FF',1),(171,'SQ8408TO',1),(172,'QB6168KN',1),(173,'YG6390AL',1),(174,'CI1394ID',1),(175,'XP3456FT',1),(176,'YT3297AQ',1),(177,'PW8563UM',1),(178,'KX6415VF',1),(179,'IL3976TO',1),(180,'RY3807OL',1),(181,'TS1050UC',1),(182,'XD6735HG',1),(183,'YB5865FJ',1),(184,'TI9457ES',1),(185,'DS2280WC',1),(186,'RT4888BS',1),(187,'RN7180EG',1),(188,'QV4868NC',1),(189,'WB6294SE',1),(190,'EO3017OV',1),(191,'XJ5264GY',1),(192,'UG5865JV',1),(193,'TJ3110FB',1),(194,'NR9302UI',1),(195,'ED8945ED',1),(196,'BL1802TD',1),(197,'WF1312HN',1),(198,'ZX4048HR',1),(199,'IS7565RZ',1),(200,'HV7267NZ',1),(201,'XF4899QT',1),(202,'586095',2),(203,'181691',2),(204,'779449',2),(205,'471874',2),(206,'385893',2),(207,'256356',2),(208,'730857',2),(209,'595481',2),(210,'128576',2),(211,'367745',2),(212,'101888',2),(213,'912876',2),(214,'682876',2),(215,'381646',2),(216,'418632',2),(217,'501340',2),(218,'616427',2),(219,'685048',2),(220,'351573',2),(221,'174045',2),(222,'202087',2),(223,'897235',2),(224,'682121',2),(225,'407535',2),(226,'211990',2),(227,'863809',2),(228,'400667',2),(229,'563019',2),(230,'490426',2),(231,'251969',2),(232,'268040',2),(233,'695643',2),(234,'207098',2),(235,'146393',2),(236,'911854',2),(237,'858309',2),(238,'649815',2),(239,'575784',2),(240,'334247',2),(241,'147167',2);
/*!40000 ALTER TABLE `tank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tanker`
--

DROP TABLE IF EXISTS `tanker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tanker` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tanker`
--

LOCK TABLES `tanker` WRITE;
/*!40000 ALTER TABLE `tanker` DISABLE KEYS */;
/*!40000 ALTER TABLE `tanker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typeestakada`
--

DROP TABLE IF EXISTS `typeestakada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `typeestakada` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=8192;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typeestakada`
--

LOCK TABLES `typeestakada` WRITE;
/*!40000 ALTER TABLE `typeestakada` DISABLE KEYS */;
INSERT INTO `typeestakada` VALUES (1,'авто'),(2,'ж/д');
/*!40000 ALTER TABLE `typeestakada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typestationarystorage`
--

DROP TABLE IF EXISTS `typestationarystorage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `typestationarystorage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Volume` int(11) NOT NULL COMMENT 'тон',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=2340;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typestationarystorage`
--

LOCK TABLES `typestationarystorage` WRITE;
/*!40000 ALTER TABLE `typestationarystorage` DISABLE KEYS */;
INSERT INTO `typestationarystorage` VALUES (1,2000),(2,6000),(3,6600),(4,3200),(5,980),(6,190),(7,4000);
/*!40000 ALTER TABLE `typestationarystorage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typetank`
--

DROP TABLE IF EXISTS `typetank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `typetank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idTypeEstakada` int(11) NOT NULL,
  `maxV` int(11) NOT NULL,
  `timeshipment` int(11) NOT NULL COMMENT 'minuts',
  PRIMARY KEY (`id`),
  KEY `FK_typetank_typeestakada_id` (`idTypeEstakada`),
  CONSTRAINT `FK_typetank_typeestakada_id` FOREIGN KEY (`idTypeEstakada`) REFERENCES `typeestakada` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=8192;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typetank`
--

LOCK TABLES `typetank` WRITE;
/*!40000 ALTER TABLE `typetank` DISABLE KEYS */;
INSERT INTO `typetank` VALUES (1,1,30,50),(2,2,60,70);
/*!40000 ALTER TABLE `typetank` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-16 18:41:43
