-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.0.13-rc-nt - Official MySQL binary
-- Server OS:                    Win32
-- HeidiSQL Version:             8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for assessment_db
DROP DATABASE IF EXISTS `assessment_db`;
CREATE DATABASE IF NOT EXISTS `assessment_db` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `assessment_db`;


-- Dumping structure for table assessment_db.company
DROP TABLE IF EXISTS `company`;
CREATE TABLE IF NOT EXISTS `company` (
  `company_id` bigint(20) NOT NULL auto_increment,
  `company_address` varchar(255) collate utf8_unicode_ci default NULL,
  `city` varchar(50) collate utf8_unicode_ci default NULL,
  `company_contact_no` varchar(255) collate utf8_unicode_ci default NULL,
  `company_name` varchar(255) collate utf8_unicode_ci default NULL,
  `company_video_path` varchar(255) collate utf8_unicode_ci default NULL,
  `company_logo_path` varchar(255) collate utf8_unicode_ci default NULL,
  `country` varchar(50) collate utf8_unicode_ci default NULL,
  `state` varchar(50) collate utf8_unicode_ci default NULL,
  `status` varchar(25) collate utf8_unicode_ci default NULL,
  `zip_code` varchar(10) collate utf8_unicode_ci default NULL,
  `company_ext_video_url` longtext collate utf8_unicode_ci,
  `company_website` varchar(100) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table assessment_db.company: ~1 rows (approximately)
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`company_id`, `company_address`, `city`, `company_contact_no`, `company_name`, `company_video_path`, `company_logo_path`, `country`, `state`, `status`, `zip_code`, `company_ext_video_url`, `company_website`) VALUES
	(11, 'Pune', 'Pune', '70d3-234-9987', 'Infosys', NULL, NULL, 'India', 'Maharashtra', 'ACTIVE', '200983', '', NULL);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;


-- Dumping structure for table assessment_db.company_url
DROP TABLE IF EXISTS `company_url`;
CREATE TABLE IF NOT EXISTS `company_url` (
  `companyUrlId` bigint(20) NOT NULL auto_increment,
  `status` varchar(25) collate utf8_unicode_ci default NULL,
  `url` varchar(200) collate utf8_unicode_ci default NULL,
  `company_id` bigint(20) default NULL,
  `url_type_id` bigint(20) default NULL,
  PRIMARY KEY  (`companyUrlId`),
  KEY `FK9x3p4qe1b6opy7tc3g3k55b2g` (`company_id`),
  KEY `FKoxpaqiugivcglsh8yi1vq45ls` (`url_type_id`),
  CONSTRAINT `FK9x3p4qe1b6opy7tc3g3k55b2g` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`),
  CONSTRAINT `FKoxpaqiugivcglsh8yi1vq45ls` FOREIGN KEY (`url_type_id`) REFERENCES `url_type` (`urlTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table assessment_db.company_url: ~2 rows (approximately)
/*!40000 ALTER TABLE `company_url` DISABLE KEYS */;
INSERT INTO `company_url` (`companyUrlId`, `status`, `url`, `company_id`, `url_type_id`) VALUES
	(15, 'ACTIVE', 'xyz', 11, 2),
	(16, 'ACTIVE', 'abc', 11, 1);
/*!40000 ALTER TABLE `company_url` ENABLE KEYS */;


-- Dumping structure for table assessment_db.role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` bigint(20) NOT NULL auto_increment,
  `created_ts` datetime default NULL,
  `description` varchar(200) collate utf8_unicode_ci default NULL,
  `name` varchar(100) collate utf8_unicode_ci default NULL,
  `status` varchar(25) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table assessment_db.role: ~1 rows (approximately)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`role_id`, `created_ts`, `description`, `name`, `status`) VALUES
	(1, '2016-05-05 07:21:27', 'Company Admin Role', 'ROLE_COMPANY_ADMIN', 'ACTIVE');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


-- Dumping structure for table assessment_db.url_type
DROP TABLE IF EXISTS `url_type`;
CREATE TABLE IF NOT EXISTS `url_type` (
  `urlTypeId` bigint(20) NOT NULL auto_increment,
  `description` longtext collate utf8_unicode_ci,
  `status` varchar(25) collate utf8_unicode_ci default NULL,
  `url_type_name` varchar(25) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`urlTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table assessment_db.url_type: ~4 rows (approximately)
/*!40000 ALTER TABLE `url_type` DISABLE KEYS */;
INSERT INTO `url_type` (`urlTypeId`, `description`, `status`, `url_type_name`) VALUES
	(1, 'Linked In', 'ACTIVE\r\n', 'LinkedIn'),
	(2, 'Facebook', 'ACTIVE', 'Facebook'),
	(3, 'Twitter', 'ACTIVE', 'Twitter'),
	(4, 'Pinterest', 'ACTIVE', 'Pinterest');
/*!40000 ALTER TABLE `url_type` ENABLE KEYS */;


-- Dumping structure for table assessment_db.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` bigint(20) NOT NULL auto_increment,
  `email_id` varchar(100) collate utf8_unicode_ci NOT NULL,
  `first_name` varchar(255) collate utf8_unicode_ci default NULL,
  `last_login_date_time` datetime default NULL,
  `last_name` varchar(255) collate utf8_unicode_ci default NULL,
  `password` varchar(100) collate utf8_unicode_ci NOT NULL,
  `status` varchar(45) collate utf8_unicode_ci default NULL,
  `company_id` bigint(20) default NULL,
  `enabled` bit(1) default NULL,
  PRIMARY KEY  (`user_id`),
  KEY `FK2yuxsfrkkrnkn5emoobcnnc3r` (`company_id`),
  CONSTRAINT `FK2yuxsfrkkrnkn5emoobcnnc3r` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table assessment_db.user: ~1 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `email_id`, `first_name`, `last_login_date_time`, `last_name`, `password`, `status`, `company_id`, `enabled`) VALUES
	(9, 'hemraj.itech2015@gmail.com', 'Hemraj', NULL, 'Parmar', '$2a$12$A8lgamuoQOEMVImJ.v4J.uXnPL6S1du4AYW2I0mNNZ9/mZOcv6BUy', 'ACTIVE', 11, b'1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- Dumping structure for table assessment_db.user_role
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table assessment_db.user_role: ~1 rows (approximately)
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
	(9, 1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
