-- MySQL dump 10.13  Distrib 5.7.28, for Linux (x86_64)
--
-- Host: localhost    Database: myproject2
-- ------------------------------------------------------
-- Server version	5.7.28

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
-- Table structure for table `memory_limit`
--

DROP TABLE IF EXISTS `memory_limit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `memory_limit` (
  `memory_limit_Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `problem_id` int(10) unsigned NOT NULL,
  `c_memory_limit` smallint(5) unsigned DEFAULT '128',
  `c_cpp_memory_limit` smallint(5) unsigned DEFAULT '128',
  `java_memory_limit` smallint(5) unsigned DEFAULT '128',
  `go_memory_limit` smallint(5) unsigned DEFAULT '128',
  `python3_memory_limit` smallint(5) unsigned DEFAULT '128',
  PRIMARY KEY (`memory_limit_Id`),
  KEY `memory_limit_FK` (`problem_id`),
  CONSTRAINT `memory_limit_FK` FOREIGN KEY (`problem_id`) REFERENCES `problem` (`problem_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `memory_limit`
--

LOCK TABLES `memory_limit` WRITE;
/*!40000 ALTER TABLE `memory_limit` DISABLE KEYS */;
INSERT INTO `memory_limit` VALUES (38,1041,126,128,127,129,130),(43,1048,128,128,128,128,128);
/*!40000 ALTER TABLE `memory_limit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
INSERT INTO `persistent_logins` VALUES ('1221@qq.com','Ga3FSiFwmFl/qsxQkApqDg==','bCkFhNEvu3w9gL1JEnm6yg==','2020-03-12 18:46:16'),('123@qq.com','IGFn0vLzzsFmiUVNqvLQUA==','OsR3GFF9iMgatr8hAQJyUg==','2019-10-18 02:37:03'),('123@qq.com','wzqPjH4NzUxkUm6FTus89w==','5vsqO9Xz8A12WFpinEXdIw==','2019-10-17 19:43:21');
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `problem`
--

DROP TABLE IF EXISTS `problem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `problem` (
  `problem_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '题目id',
  `problem_name` char(40) NOT NULL COMMENT '题目名称',
  `accept_num` int(10) unsigned DEFAULT '0' COMMENT '总通过人数',
  `submit_num` int(10) unsigned DEFAULT '0' COMMENT '总提交次数',
  `problem_content` text COMMENT '题目内容（包含题目描述，题目输入格式，输出格式）',
  `test_data_path` char(100) DEFAULT NULL COMMENT '相对路径，相对与项目下的resource目录',
  PRIMARY KEY (`problem_id`),
  UNIQUE KEY `problem_UN` (`problem_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1049 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problem`
--

LOCK TABLES `problem` WRITE;
/*!40000 ALTER TABLE `problem` DISABLE KEYS */;
INSERT INTO `problem` VALUES (1041,'计算A+B',55,139,'<h1 style=\\\"text-align: center;\\\">排序</h1><h2 style=\\\"text-align: left;\\\">题目描述：</h2><blockquote>给定n个整数，升序排序它们。</blockquote><h2>输入格式：</h2><blockquote>第一行，一个整数n。</blockquote><blockquote>第二行，n个整数，每两个整数之间用空格隔开。</blockquote><h2>输出格式：</h2><blockquote>一行，n个整数，每两个整数之间用空格隔开，末尾无空格。</blockquote><h2>输入样例：</h2><blockquote>5</blockquote><blockquote>1 3 5 2 4</blockquote><h2>输出样例：</h2><blockquote>1 2 3 4 5</blockquote><p></p><p></p><p></p>','/myproject2_data/testdata/problemId_1041/problem_1041.zip'),(1048,'排序',1,1,'<h2 style=\\\"text-align: left;\\\"><br></h2><h1 style=\\\"text-align: center;\\\">排序</h1><h2 style=\\\"text-align: left;\\\">题目描述：</h2><blockquote>给n个整数进行升序排序。</blockquote><h2>输入格式：</h2><blockquote>一行，一个整数n。</blockquote><blockquote>一行，n个整数，每两个整数之间用空格隔开。</blockquote><h2>输出格式：</h2><blockquote>一行，n个整数，每两个整数之间用空格隔开，末尾无空格。</blockquote><h2>输入样例：</h2><blockquote>5</blockquote><blockquote>1 4 5 2 3</blockquote><h2>输出样例：</h2><blockquote>1 2 3 4 5</blockquote><p></p><p></p><p></p><p><br></p>','/myproject2_data/testdata/problemId_1048/problem_1048.zip');
/*!40000 ALTER TABLE `problem` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `problem_insert` AFTER INSERT ON `problem` FOR EACH ROW BEGIN
	insert into myproject2.memory_limit(problem_id)
	values(new.problem_id);
	insert into myproject2.time_limit(problem_id)
	values(new.problem_id);
	insert into myproject2.test_data(problem_id)
	VALUES(new.problem_id);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `table_count_Insert` AFTER INSERT ON `problem` FOR EACH ROW BEGIN
	update table_count set table_number = table_number + 1 where table_name = 'problem';
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `test_data_path_update` AFTER UPDATE ON `problem` FOR EACH ROW BEGIN
	IF old.test_data_path != new.test_data_path THEN
		DELETE FROM test_data WHERE problem_id = old.problem_id;
	end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `table_count_delete` AFTER DELETE ON `problem` FOR EACH ROW BEGIN
	update table_count set table_number = table_number -1 where table_name = 'problem';
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` char(40) NOT NULL COMMENT '要以ROLE_开头',
  `notes` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER','普通用户权限'),(2,'ROLE_ADMIN_LEVEL1','普通管理员权限，主要有增，改权限'),(3,'ROLE_ADMIN_LEVEL2','二级管理员，删除权限'),(4,'ROLE_ADMIN_SUPER','超级管理员，拥有所有权限');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `run_code`
--

DROP TABLE IF EXISTS `run_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `run_code` (
  `run_code_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `submit_code_id` int(10) unsigned NOT NULL,
  `run_code_file` char(100) DEFAULT NULL,
  `code_type` varchar(20) DEFAULT NULL,
  `test_data_path` char(100) DEFAULT NULL,
  `test_data_serial` smallint(5) unsigned DEFAULT '0',
  `status` varchar(20) DEFAULT 'waiting',
  `run_time` mediumint(8) unsigned DEFAULT '0',
  `run_memory` mediumint(8) unsigned DEFAULT '0',
  PRIMARY KEY (`run_code_id`),
  KEY `run_code_FK` (`submit_code_id`),
  CONSTRAINT `run_code_FK` FOREIGN KEY (`submit_code_id`) REFERENCES `submit_code` (`submit_code_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2663 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `run_code`
--

LOCK TABLES `run_code` WRITE;
/*!40000 ALTER TABLE `run_code` DISABLE KEYS */;
INSERT INTO `run_code` VALUES (2652,464,'/myproject2_data/run/8c430c77-9908-4fea-a92a-b61bfc73ff78/Main','c_cpp','/myproject2_data/testdata/problemId_1041/t1',1,'AC',1,2012),(2653,464,'/myproject2_data/run/8c430c77-9908-4fea-a92a-b61bfc73ff78/Main','c_cpp','/myproject2_data/testdata/problemId_1041/t2',2,'AC',1,2012),(2654,464,'/myproject2_data/run/8c430c77-9908-4fea-a92a-b61bfc73ff78/Main','c_cpp','/myproject2_data/testdata/problemId_1041/t3',3,'AC',1,1956),(2655,464,'/myproject2_data/run/8c430c77-9908-4fea-a92a-b61bfc73ff78/Main','c_cpp','/myproject2_data/testdata/problemId_1041/t4',4,'AC',1,1956),(2656,464,'/myproject2_data/run/8c430c77-9908-4fea-a92a-b61bfc73ff78/Main','c_cpp','/myproject2_data/testdata/problemId_1041/t5',5,'AC',1,1952),(2657,464,'/myproject2_data/run/8c430c77-9908-4fea-a92a-b61bfc73ff78/Main','c_cpp','/myproject2_data/testdata/problemId_1041/t6',6,'AC',1,1960),(2658,464,'/myproject2_data/run/8c430c77-9908-4fea-a92a-b61bfc73ff78/Main','c_cpp','/myproject2_data/testdata/problemId_1041/t7',7,'AC',1,1960),(2659,464,'/myproject2_data/run/8c430c77-9908-4fea-a92a-b61bfc73ff78/Main','c_cpp','/myproject2_data/testdata/problemId_1041/t8',8,'AC',1,1972),(2660,464,'/myproject2_data/run/8c430c77-9908-4fea-a92a-b61bfc73ff78/Main','c_cpp','/myproject2_data/testdata/problemId_1041/t9',9,'AC',1,1944),(2661,464,'/myproject2_data/run/8c430c77-9908-4fea-a92a-b61bfc73ff78/Main','c_cpp','/myproject2_data/testdata/problemId_1041/t10',10,'AC',1,1948),(2662,465,'/myproject2_data/run/6d39f44c-11aa-4db9-8b1a-77012b08b4b5/Main','c_cpp','/myproject2_data/testdata/problemId_1048/t1',1,'AC',1,2032);
/*!40000 ALTER TABLE `run_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submit_code`
--

DROP TABLE IF EXISTS `submit_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submit_code` (
  `submit_code_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned DEFAULT NULL,
  `problem_id` int(10) unsigned NOT NULL,
  `submit_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `code_value` char(100) DEFAULT NULL,
  `code_type` varchar(20) DEFAULT NULL,
  `font_size` tinyint(3) unsigned DEFAULT '20',
  `judge_test_num` smallint(5) unsigned DEFAULT '0',
  `test_num` smallint(5) unsigned DEFAULT '0',
  `theme` varchar(15) DEFAULT NULL,
  `status` varchar(20) DEFAULT 'waiting',
  `compile_message` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`submit_code_id`),
  KEY `submit_code_FK_1` (`user_id`),
  KEY `submit_code_judge_status_IDX` (`status`) USING BTREE,
  KEY `submit_code_FK` (`problem_id`),
  CONSTRAINT `submit_code_FK` FOREIGN KEY (`problem_id`) REFERENCES `problem` (`problem_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=466 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submit_code`
--

LOCK TABLES `submit_code` WRITE;
/*!40000 ALTER TABLE `submit_code` DISABLE KEYS */;
INSERT INTO `submit_code` VALUES (464,1,1041,'2020-03-13 02:45:17','/myproject2_data/compile/91dc6743-d31c-4140-bac8-d1cd4c2eed39/Main.cpp','c_cpp',20,10,10,'xcode','AC',NULL),(465,1,1048,'2020-03-13 02:51:37','/myproject2_data/compile/71fdd7d2-bcea-4cf8-ac57-d351c3febbdb/Main.cpp','c_cpp',20,1,1,'xcode','AC',NULL);
/*!40000 ALTER TABLE `submit_code` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `insert` AFTER INSERT ON `submit_code` FOR EACH ROW BEGIN
	update table_count set table_number = table_number + 1 where table_name = 'submit_code';
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `delete` AFTER DELETE ON `submit_code` FOR EACH ROW BEGIN
	update table_count set table_number = table_number - 1 where table_name = 'submit_code';
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `table_count`
--

DROP TABLE IF EXISTS `table_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table_count` (
  `table_count_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `table_name` varchar(20) DEFAULT NULL,
  `table_number` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`table_count_id`),
  UNIQUE KEY `count_UN` (`table_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='对各种表的总记录条数进行计数';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_count`
--

LOCK TABLES `table_count` WRITE;
/*!40000 ALTER TABLE `table_count` DISABLE KEYS */;
INSERT INTO `table_count` VALUES (1,'user',5),(2,'problem',2),(4,'submit_code',2);
/*!40000 ALTER TABLE `table_count` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_data`
--

DROP TABLE IF EXISTS `test_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_data` (
  `test_data_id` int(11) NOT NULL AUTO_INCREMENT,
  `problem_id` int(10) unsigned NOT NULL,
  `test_data_order_number` tinyint(3) unsigned DEFAULT NULL,
  `data_path` char(255) DEFAULT NULL COMMENT '相对路径，相对于项目资源路径',
  PRIMARY KEY (`test_data_id`),
  KEY `test_data_FK` (`problem_id`),
  CONSTRAINT `test_data_FK` FOREIGN KEY (`problem_id`) REFERENCES `problem` (`problem_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=210 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_data`
--

LOCK TABLES `test_data` WRITE;
/*!40000 ALTER TABLE `test_data` DISABLE KEYS */;
INSERT INTO `test_data` VALUES (198,1041,1,'/myproject2_data/testdata/problemId_1041/t1'),(199,1041,2,'/myproject2_data/testdata/problemId_1041/t2'),(200,1041,3,'/myproject2_data/testdata/problemId_1041/t3'),(201,1041,4,'/myproject2_data/testdata/problemId_1041/t4'),(202,1041,5,'/myproject2_data/testdata/problemId_1041/t5'),(203,1041,6,'/myproject2_data/testdata/problemId_1041/t6'),(204,1041,7,'/myproject2_data/testdata/problemId_1041/t7'),(205,1041,8,'/myproject2_data/testdata/problemId_1041/t8'),(206,1041,9,'/myproject2_data/testdata/problemId_1041/t9'),(207,1041,10,'/myproject2_data/testdata/problemId_1041/t10'),(209,1048,1,'/myproject2_data/testdata/problemId_1048/t1');
/*!40000 ALTER TABLE `test_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_limit`
--

DROP TABLE IF EXISTS `time_limit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `time_limit` (
  `time_limit_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `problem_id` int(10) unsigned NOT NULL COMMENT '题目id',
  `c_time_limit` tinyint(3) unsigned DEFAULT '1',
  `c_cpp_time_limit` tinyint(3) unsigned DEFAULT '1',
  `java_time_limit` tinyint(3) unsigned DEFAULT '1',
  `go_time_limit` tinyint(3) unsigned DEFAULT '1',
  `python3_time_limit` tinyint(3) unsigned DEFAULT '1',
  PRIMARY KEY (`time_limit_id`),
  KEY `time_limit_FK` (`problem_id`),
  CONSTRAINT `time_limit_FK` FOREIGN KEY (`problem_id`) REFERENCES `problem` (`problem_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_limit`
--

LOCK TABLES `time_limit` WRITE;
/*!40000 ALTER TABLE `time_limit` DISABLE KEYS */;
INSERT INTO `time_limit` VALUES (159,1041,1,1,1,1,1),(164,1048,1,1,1,1,1);
/*!40000 ALTER TABLE `time_limit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` char(40) DEFAULT NULL,
  `user_email` char(20) DEFAULT NULL,
  `user_password` char(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_UN` (`user_name`),
  UNIQUE KEY `user_UN2` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'root','1221@qq.com','78946'),(2,'root2','1@qq.com','78946'),(3,'root3','2@qq.com','78946'),(4,'user','user@qq.com','123456'),(56,NULL,'123@qq.com','123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `user_insert` AFTER INSERT ON `user` FOR EACH ROW BEGIN
	update table_count set table_number = table_number + 1 where table_name = 'user';
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `user_delete` AFTER DELETE ON `user` FOR EACH ROW BEGIN
	update table_count set table_number = table_number - 1 where table_name = 'user';
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `user_role_UN` (`user_id`,`role_id`),
  KEY `user_role_FK_1` (`role_id`),
  CONSTRAINT `user_role_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_role_FK_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (10,1,1),(2,1,4),(3,2,2),(4,3,2),(9,3,3),(5,4,1),(21,56,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'myproject2'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-13 10:52:17
