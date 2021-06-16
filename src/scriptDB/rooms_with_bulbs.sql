-- -----------------------------------------------------
-- Schema rooms_with_bulbs
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rooms_with_bulbs` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `rooms_with_bulbs` ;

-- -----------------------------------------------------
-- Table `rooms_with_bulbs`.`rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rooms_with_bulbs`.`rooms` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `country` VARCHAR(50) NOT NULL,
  `bulb_status` VARCHAR(5) NOT NULL DEFAULT 'OFF',
  PRIMARY KEY (`id`))
ENGINE = InnoDB

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
INSERT INTO `rooms` VALUES (1,'Room 1','Denmark','OFF'),(2,'Room 2','Finland','ON'),(3,'Room 3','Denmark','OFF'),(4,'Room 4','Monaco','OFF'),(5,'Room 5','Denmark','OFF'),(6,'Room 6','Monaco','ON'),(7,'Room 7','Angola','ON'),(8,'Room 8','Brazil','OFF'),(9,'Room 9','Egypt','ON'),(10,'Room 10','Russia','OFF'),(11,'Room 11','Lebanon','OFF'),(12,'Room 12','Ukraine','OFF');
UNLOCK TABLES;
