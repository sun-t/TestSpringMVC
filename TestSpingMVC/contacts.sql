# MySQL-Front 5.0  (Build 1.0)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: mldn
# ------------------------------------------------------
# Server version 5.5.19

#
# Table structure for table contacts
#

DROP TABLE IF EXISTS `samplecontacts`;
CREATE TABLE `samplecontacts` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `caseNum` varchar(45) DEFAULT NULL,
  `dateBegin` date DEFAULT NULL,
  `dateEnd` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `multiRun` varchar(15) DEFAULT NULL,
  `resultRec` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
INSERT INTO `samplecontacts` VALUES (7,'taohang', '12345678','case2,3','2014-07-28','2014-08-05','01:00:00','multi','taohang');
INSERT INTO `samplecontacts` VALUES (10,'wanghua','23456789','case1,2,3','2014-08-02','2014-08-03','03:30:00','once','wanghua');

/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
