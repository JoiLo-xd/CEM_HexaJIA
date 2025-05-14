CREATE DATABASE  IF NOT EXISTS `cem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cem`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cem
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `inscripcio`
--

DROP TABLE IF EXISTS `inscripcio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inscripcio` (
  `dorsal` int NOT NULL,
  `modalitat` bit(1) NOT NULL,
  `hora_sortida` time DEFAULT NULL,
  `hora_arribada` time DEFAULT NULL,
  `asistencia` varchar(45) NOT NULL,
  `nif` varchar(9) NOT NULL,
  `edicio` int NOT NULL,
  PRIMARY KEY (`dorsal`,`edicio`),
  KEY `nif_idx` (`nif`),
  KEY `edicio_idx` (`edicio`),
  CONSTRAINT `edicio` FOREIGN KEY (`edicio`) REFERENCES `marxa` (`edicio`),
  CONSTRAINT `nif` FOREIGN KEY (`nif`) REFERENCES `participant` (`nif`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inscripcio`
--

LOCK TABLES `inscripcio` WRITE;
/*!40000 ALTER TABLE `inscripcio` DISABLE KEYS */;
/*!40000 ALTER TABLE `inscripcio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marxa`
--

DROP TABLE IF EXISTS `marxa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marxa` (
  `edicio` int NOT NULL,
  PRIMARY KEY (`edicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marxa`
--

LOCK TABLES `marxa` WRITE;
/*!40000 ALTER TABLE `marxa` DISABLE KEYS */;
/*!40000 ALTER TABLE `marxa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participant`
--

DROP TABLE IF EXISTS `participant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participant` (
  `nif` varchar(9) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `cognom` varchar(45) NOT NULL,
  `naixement` date NOT NULL,
  `sexe` bit(1) NOT NULL,
  `poblacio` varchar(45) NOT NULL,
  `num_telf` varchar(9) NOT NULL,
  `gmail` varchar(45) NOT NULL,
  `federat` bit(1) NOT NULL,
  `entitat` varchar(45) DEFAULT NULL,
  `observacions` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`nif`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participant`
--

LOCK TABLES `participant` WRITE;
/*!40000 ALTER TABLE `participant` DISABLE KEYS */;
/*!40000 ALTER TABLE `participant` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-14 17:27:13
