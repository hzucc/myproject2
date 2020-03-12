-- MySQL dump 10.13  Distrib 8.0.18, for Linux (x86_64)
--
-- Host: localhost    Database: myproject2
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `memory_limit`
--

LOCK TABLES `memory_limit` WRITE;
/*!40000 ALTER TABLE `memory_limit` DISABLE KEYS */;
INSERT INTO `memory_limit` VALUES (38,1041,126,128,127,129,130),(39,1044,125,126,127,128,129),(40,1045,128,128,128,128,128),(41,1046,128,128,128,128,128),(42,1047,128,128,128,128,128);
/*!40000 ALTER TABLE `memory_limit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
INSERT INTO `persistent_logins` VALUES ('123@qq.com','IGFn0vLzzsFmiUVNqvLQUA==','OsR3GFF9iMgatr8hAQJyUg==','2019-10-18 02:37:03'),('123@qq.com','wzqPjH4NzUxkUm6FTus89w==','5vsqO9Xz8A12WFpinEXdIw==','2019-10-17 19:43:21');
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `problem`
--

DROP TABLE IF EXISTS `problem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `problem` (
  `problem_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '题目id',
  `problem_name` char(40) NOT NULL COMMENT '题目名称',
  `accept_num` int(10) unsigned DEFAULT '0' COMMENT '总通过人数',
  `submit_num` int(10) unsigned DEFAULT '0' COMMENT '总提交次数',
  `problem_content` text COMMENT '题目内容（包含题目描述，题目输入格式，输出格式）',
  `test_data_path` char(100) DEFAULT NULL,
  PRIMARY KEY (`problem_id`),
  UNIQUE KEY `problem_UN` (`problem_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1048 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problem`
--

LOCK TABLES `problem` WRITE;
/*!40000 ALTER TABLE `problem` DISABLE KEYS */;
INSERT INTO `problem` VALUES (1041,'计算A+B',53,117,'<h1 style=\"text-align: center;\">计算A+B</h1><h2 style=\"text-align: left;\">题目描述：</h2><blockquote>计算两个数的和。</blockquote><h2>输入格式：</h2><blockquote>一行，两个整数，中间用空格隔开。</blockquote><h2>输出格式：</h2><blockquote>一行，一个整数。</blockquote><h2>输入样例：</h2><blockquote>1 2</blockquote><h2>输出样例：</h2><blockquote>3</blockquote><p></p><p></p><p></p>','/myproject2/testdata/problemId_1041/problem_1041.zip'),(1044,'计算几何1',1,4,'<h1 style=\\\"\\\\&quot;text-align:\\\" center;\\\\\\\"=\\\"\\\">计算几何1</h1><h2 style=\\\"\\\\&quot;text-align:\\\" left;\\\\\\\"=\\\"\\\">题目描述：</h2><blockquote>给出三个点，求出三点围城的三角形的面积。</blockquote><h2>输入格式：</h2><blockquote>三行，每行两个整数x和y，中间用空格隔开。</blockquote><h2>输出格式：</h2><blockquote>一行，一个浮点数，保留两位小数。</blockquote><h2>输入样例：</h2><blockquote>0 0<br>0 1<br>1 0</blockquote><h2>输出样例：</h2><blockquote>0.50</blockquote>','/myproject2/testdata/problemId_1044/problem_1044.zip'),(1045,'排序',1,5,'<h1 style=\\\"\\\\&quot;text-align:\\\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <span style=\\\"font-weight: bold; font-size: xx-large;\\\">排序</span></h1><h2 style=\\\"\\\\&quot;text-align:\\\" left;\\\\\\\"=\\\"\\\">题目描述：</h2><blockquote>给定n个整数，对它们排序。</blockquote><h2>输入格式：</h2><blockquote>第一行，一个整数n。</blockquote><blockquote>第二行，n个整数，整数之间用空格隔开。</blockquote><h2>输出格式：</h2><blockquote>一行，n个整数，整数之间用空格隔开，末尾无空格无换行。</blockquote><h2>输入样例：</h2><blockquote>5</blockquote><blockquote>1 4 2 2 5</blockquote><h2>输出样例：</h2><blockquote>1 2 2 4 5</blockquote><p></p><p></p><p></p><p><br></p>','/myproject2/testdata/problemId_1045/problem_1045.zip'),(1046,'斐波那契数列',0,1,'<h1 style=\\\"text-align: center;\\\">斐波那契数列</h1><h2 style=\\\"text-align: left;\\\">题目描述：</h2><blockquote>计算两个数的和。</blockquote><h2>输入格式：</h2><blockquote>一行，两个整数，中间用空格隔开。</blockquote><h2>输出格式：</h2><blockquote>一行，一个整数。</blockquote><h2>输入样例：</h2><blockquote>1 2</blockquote><h2>输出样例：</h2><blockquote>3</blockquote><p></p><p></p><p></p><p><br></p>','/myproject2/testdata/problemId_1046/problem_1046.zip'),(1047,'过河卒',2,8,'<h1 style=\\\"text-align: center;\\\">&nbsp;过河卒</h1><h2 style=\\\"text-align: left;\\\">题目描述：</h2><blockquote><p>棋盘上<math xmlns=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;http://www.w3.org/1998/Math/MathML\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\"><semantics><annotation encoding=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;application/x-tex\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\">A</annotation></semantics></math><span aria-hidden=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;true\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\">A</span>点有一个过河卒，需要走到目标<math xmlns=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;http://www.w3.org/1998/Math/MathML\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\"><semantics><annotation encoding=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;application/x-tex\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\">B</annotation></semantics></math><span aria-hidden=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;true\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\">B</span>点。卒行走的规则：可以向下、或者向右。同时在棋盘上<math xmlns=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;http://www.w3.org/1998/Math/MathML\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\"><semantics><annotation encoding=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;application/x-tex\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\">C</annotation></semantics></math><span aria-hidden=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;true\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\">C</span>点有一个对方的马，该马所在的点和所有跳跃一步可达的点称为对方马的控制点。因此称之为“马拦过河卒”。</p><p>棋盘用坐标表示，<math xmlns=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;http://www.w3.org/1998/Math/MathML\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\"><semantics><annotation encoding=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;application/x-tex\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\">A</annotation></semantics></math>点<math xmlns=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;http://www.w3.org/1998/Math/MathML\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\"><semantics><annotation encoding=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;application/x-tex\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\">(0, 0)</annotation></semantics></math>、<math xmlns=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;http://www.w3.org/1998/Math/MathML\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\"><semantics><annotation encoding=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;application/x-tex\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\">B</annotation></semantics></math>点<math xmlns=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;http://www.w3.org/1998/Math/MathML\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\"><semantics><annotation encoding=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;application/x-tex\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\">(n, m)</annotation></semantics></math>(<span aria-hidden=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;true\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\">n</span>,&nbsp;<span aria-hidden=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;true\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\">m</span>为不超过<math xmlns=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;http://www.w3.org/1998/Math/MathML\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\"><semantics><annotation encoding=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;application/x-tex\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\">20</annotation></semantics></math>的整数)，同样马的位置坐标是需要给出的。</p><p>现在要求你计算出卒从<math xmlns=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;http://www.w3.org/1998/Math/MathML\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\"><semantics><annotation encoding=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;application/x-tex\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\">A</annotation></semantics></math><span aria-hidden=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;true\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\">A</span>点能够到达<math xmlns=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;http://www.w3.org/1998/Math/MathML\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\"><semantics><annotation encoding=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;application/x-tex\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\">B</annotation></semantics></math><span aria-hidden=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;true\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\">B</span>点的路径的条数，假设马的位置是固定不动的，并不是卒走一步马走一步。</p></blockquote><h2>输入格式：</h2><blockquote>一行四个数据，分别表示<math xmlns=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;http://www.w3.org/1998/Math/MathML\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\"><semantics><annotation encoding=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;application/x-tex\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\">B</annotation></semantics></math><span aria-hidden=\\\"\\\\&quot;\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;true\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\&quot;\\\\\\\\\\\\\\\\&quot;\\\\\\\\&quot;\\\\&quot;\\\">B</span>点坐标和马的坐标。<br></blockquote><h2>输出格式：</h2><blockquote>一个数据，表示所有的路径条数，末尾无空格。<br></blockquote><h2>输入样例：</h2><blockquote>6 6 3 3<br></blockquote><h2>输出样例：</h2><blockquote>6<br></blockquote><p>题目来源：洛谷P1002</p><p></p><p></p><p></p><p><br></p>','/myproject2/testdata/problemId_1047/problem_1047.zip');
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
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2642 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `run_code`
--

LOCK TABLES `run_code` WRITE;
/*!40000 ALTER TABLE `run_code` DISABLE KEYS */;
INSERT INTO `run_code` VALUES (2302,386,'/myproject2/run/7b7c7e19-3405-4ce8-8eba-0b8b2b4b9822/Main','c_cpp','/myproject2/testdata/problemId_1041/t1',1,'AC',3,1768),(2303,386,'/myproject2/run/7b7c7e19-3405-4ce8-8eba-0b8b2b4b9822/Main','c_cpp','/myproject2/testdata/problemId_1041/t2',2,'AC',4,1768),(2304,386,'/myproject2/run/7b7c7e19-3405-4ce8-8eba-0b8b2b4b9822/Main','c_cpp','/myproject2/testdata/problemId_1041/t3',3,'AC',3,1804),(2305,386,'/myproject2/run/7b7c7e19-3405-4ce8-8eba-0b8b2b4b9822/Main','c_cpp','/myproject2/testdata/problemId_1041/t4',4,'AC',4,1768),(2306,386,'/myproject2/run/7b7c7e19-3405-4ce8-8eba-0b8b2b4b9822/Main','c_cpp','/myproject2/testdata/problemId_1041/t5',5,'AC',4,1784),(2307,386,'/myproject2/run/7b7c7e19-3405-4ce8-8eba-0b8b2b4b9822/Main','c_cpp','/myproject2/testdata/problemId_1041/t6',6,'AC',4,1768),(2308,386,'/myproject2/run/7b7c7e19-3405-4ce8-8eba-0b8b2b4b9822/Main','c_cpp','/myproject2/testdata/problemId_1041/t7',7,'AC',4,1784),(2309,386,'/myproject2/run/7b7c7e19-3405-4ce8-8eba-0b8b2b4b9822/Main','c_cpp','/myproject2/testdata/problemId_1041/t8',8,'AC',3,1776),(2310,386,'/myproject2/run/7b7c7e19-3405-4ce8-8eba-0b8b2b4b9822/Main','c_cpp','/myproject2/testdata/problemId_1041/t9',9,'AC',4,1796),(2311,386,'/myproject2/run/7b7c7e19-3405-4ce8-8eba-0b8b2b4b9822/Main','c_cpp','/myproject2/testdata/problemId_1041/t10',10,'AC',4,1808),(2312,387,'/myproject2/run/75c8309e-ccee-4d82-a55e-98160bd4cbd4/Main','c_cpp','/myproject2/testdata/problemId_1044/t1',1,'WA',2,1840),(2313,387,'/myproject2/run/75c8309e-ccee-4d82-a55e-98160bd4cbd4/Main','c_cpp','/myproject2/testdata/problemId_1044/t2',2,'WA',2,1848),(2314,387,'/myproject2/run/75c8309e-ccee-4d82-a55e-98160bd4cbd4/Main','c_cpp','/myproject2/testdata/problemId_1044/t3',3,'WA',2,1840),(2315,387,'/myproject2/run/75c8309e-ccee-4d82-a55e-98160bd4cbd4/Main','c_cpp','/myproject2/testdata/problemId_1044/t4',4,'WA',2,1848),(2316,387,'/myproject2/run/75c8309e-ccee-4d82-a55e-98160bd4cbd4/Main','c_cpp','/myproject2/testdata/problemId_1044/t5',5,'WA',1,1816),(2317,387,'/myproject2/run/75c8309e-ccee-4d82-a55e-98160bd4cbd4/Main','c_cpp','/myproject2/testdata/problemId_1044/t6',6,'WA',1,1824),(2318,387,'/myproject2/run/75c8309e-ccee-4d82-a55e-98160bd4cbd4/Main','c_cpp','/myproject2/testdata/problemId_1044/t7',7,'WA',2,1844),(2319,387,'/myproject2/run/75c8309e-ccee-4d82-a55e-98160bd4cbd4/Main','c_cpp','/myproject2/testdata/problemId_1044/t8',8,'WA',2,1828),(2320,387,'/myproject2/run/75c8309e-ccee-4d82-a55e-98160bd4cbd4/Main','c_cpp','/myproject2/testdata/problemId_1044/t9',9,'WA',2,1848),(2321,387,'/myproject2/run/75c8309e-ccee-4d82-a55e-98160bd4cbd4/Main','c_cpp','/myproject2/testdata/problemId_1044/t10',10,'WA',2,1848),(2322,388,'/myproject2/run/a3bfbb9b-c3cf-4457-a1a4-f7a47bf7fe31/Main','c_cpp','/myproject2/testdata/problemId_1044/t1',1,'WA',2,1764),(2323,388,'/myproject2/run/a3bfbb9b-c3cf-4457-a1a4-f7a47bf7fe31/Main','c_cpp','/myproject2/testdata/problemId_1044/t2',2,'WA',2,1844),(2324,388,'/myproject2/run/a3bfbb9b-c3cf-4457-a1a4-f7a47bf7fe31/Main','c_cpp','/myproject2/testdata/problemId_1044/t3',3,'WA',2,1828),(2325,388,'/myproject2/run/a3bfbb9b-c3cf-4457-a1a4-f7a47bf7fe31/Main','c_cpp','/myproject2/testdata/problemId_1044/t4',4,'WA',2,1836),(2326,388,'/myproject2/run/a3bfbb9b-c3cf-4457-a1a4-f7a47bf7fe31/Main','c_cpp','/myproject2/testdata/problemId_1044/t5',5,'WA',2,1848),(2327,388,'/myproject2/run/a3bfbb9b-c3cf-4457-a1a4-f7a47bf7fe31/Main','c_cpp','/myproject2/testdata/problemId_1044/t6',6,'WA',2,1784),(2328,388,'/myproject2/run/a3bfbb9b-c3cf-4457-a1a4-f7a47bf7fe31/Main','c_cpp','/myproject2/testdata/problemId_1044/t7',7,'WA',2,1828),(2329,388,'/myproject2/run/a3bfbb9b-c3cf-4457-a1a4-f7a47bf7fe31/Main','c_cpp','/myproject2/testdata/problemId_1044/t8',8,'WA',2,1816),(2330,388,'/myproject2/run/a3bfbb9b-c3cf-4457-a1a4-f7a47bf7fe31/Main','c_cpp','/myproject2/testdata/problemId_1044/t9',9,'WA',2,1836),(2331,388,'/myproject2/run/a3bfbb9b-c3cf-4457-a1a4-f7a47bf7fe31/Main','c_cpp','/myproject2/testdata/problemId_1044/t10',10,'WA',3,1840),(2332,389,'/myproject2/run/40f65ac3-3e2b-4730-a5dc-f9f6e5c1f124/Main','c_cpp','/myproject2/testdata/problemId_1044/t1',1,'AC',3,1816),(2333,389,'/myproject2/run/40f65ac3-3e2b-4730-a5dc-f9f6e5c1f124/Main','c_cpp','/myproject2/testdata/problemId_1044/t2',2,'WA',2,1800),(2334,389,'/myproject2/run/40f65ac3-3e2b-4730-a5dc-f9f6e5c1f124/Main','c_cpp','/myproject2/testdata/problemId_1044/t3',3,'AC',2,1808),(2335,389,'/myproject2/run/40f65ac3-3e2b-4730-a5dc-f9f6e5c1f124/Main','c_cpp','/myproject2/testdata/problemId_1044/t4',4,'AC',2,1824),(2336,389,'/myproject2/run/40f65ac3-3e2b-4730-a5dc-f9f6e5c1f124/Main','c_cpp','/myproject2/testdata/problemId_1044/t5',5,'AC',2,1812),(2337,389,'/myproject2/run/40f65ac3-3e2b-4730-a5dc-f9f6e5c1f124/Main','c_cpp','/myproject2/testdata/problemId_1044/t6',6,'AC',2,1804),(2338,389,'/myproject2/run/40f65ac3-3e2b-4730-a5dc-f9f6e5c1f124/Main','c_cpp','/myproject2/testdata/problemId_1044/t7',7,'AC',2,1764),(2339,389,'/myproject2/run/40f65ac3-3e2b-4730-a5dc-f9f6e5c1f124/Main','c_cpp','/myproject2/testdata/problemId_1044/t8',8,'AC',2,1768),(2340,389,'/myproject2/run/40f65ac3-3e2b-4730-a5dc-f9f6e5c1f124/Main','c_cpp','/myproject2/testdata/problemId_1044/t9',9,'AC',2,1824),(2341,389,'/myproject2/run/40f65ac3-3e2b-4730-a5dc-f9f6e5c1f124/Main','c_cpp','/myproject2/testdata/problemId_1044/t10',10,'AC',2,1824),(2342,390,'/myproject2/run/4eafcd0b-2622-44fa-8a64-e9b4de72de9f/Main','c_cpp','/myproject2/testdata/problemId_1044/t1',1,'AC',3,1804),(2343,390,'/myproject2/run/4eafcd0b-2622-44fa-8a64-e9b4de72de9f/Main','c_cpp','/myproject2/testdata/problemId_1044/t2',2,'AC',3,1768),(2344,390,'/myproject2/run/4eafcd0b-2622-44fa-8a64-e9b4de72de9f/Main','c_cpp','/myproject2/testdata/problemId_1044/t3',3,'AC',3,1764),(2345,390,'/myproject2/run/4eafcd0b-2622-44fa-8a64-e9b4de72de9f/Main','c_cpp','/myproject2/testdata/problemId_1044/t4',4,'AC',2,1820),(2346,390,'/myproject2/run/4eafcd0b-2622-44fa-8a64-e9b4de72de9f/Main','c_cpp','/myproject2/testdata/problemId_1044/t5',5,'AC',2,1824),(2347,390,'/myproject2/run/4eafcd0b-2622-44fa-8a64-e9b4de72de9f/Main','c_cpp','/myproject2/testdata/problemId_1044/t6',6,'AC',3,1828),(2348,390,'/myproject2/run/4eafcd0b-2622-44fa-8a64-e9b4de72de9f/Main','c_cpp','/myproject2/testdata/problemId_1044/t7',7,'AC',2,1808),(2349,390,'/myproject2/run/4eafcd0b-2622-44fa-8a64-e9b4de72de9f/Main','c_cpp','/myproject2/testdata/problemId_1044/t8',8,'AC',2,1768),(2350,390,'/myproject2/run/4eafcd0b-2622-44fa-8a64-e9b4de72de9f/Main','c_cpp','/myproject2/testdata/problemId_1044/t9',9,'AC',3,1824),(2351,390,'/myproject2/run/4eafcd0b-2622-44fa-8a64-e9b4de72de9f/Main','c_cpp','/myproject2/testdata/problemId_1044/t10',10,'AC',2,1816),(2352,391,'/myproject2/run/8a475d6e-2191-445a-8733-98376b95b907/Main','c_cpp','/myproject2/testdata/problemId_1045/t1',1,'running',0,0),(2353,391,'/myproject2/run/8a475d6e-2191-445a-8733-98376b95b907/Main','c_cpp','/myproject2/testdata/problemId_1045/t2',2,'running',0,0),(2354,391,'/myproject2/run/8a475d6e-2191-445a-8733-98376b95b907/Main','c_cpp','/myproject2/testdata/problemId_1045/t3',3,'running',0,0),(2355,391,'/myproject2/run/8a475d6e-2191-445a-8733-98376b95b907/Main','c_cpp','/myproject2/testdata/problemId_1045/t4',4,'running',0,0),(2356,391,'/myproject2/run/8a475d6e-2191-445a-8733-98376b95b907/Main','c_cpp','/myproject2/testdata/problemId_1045/t5',5,'running',0,0),(2357,391,'/myproject2/run/8a475d6e-2191-445a-8733-98376b95b907/Main','c_cpp','/myproject2/testdata/problemId_1045/t6',6,'running',0,0),(2358,391,'/myproject2/run/8a475d6e-2191-445a-8733-98376b95b907/Main','c_cpp','/myproject2/testdata/problemId_1045/t7',7,'running',0,0),(2359,391,'/myproject2/run/8a475d6e-2191-445a-8733-98376b95b907/Main','c_cpp','/myproject2/testdata/problemId_1045/t8',8,'running',0,0),(2360,391,'/myproject2/run/8a475d6e-2191-445a-8733-98376b95b907/Main','c_cpp','/myproject2/testdata/problemId_1045/t9',9,'running',0,0),(2361,391,'/myproject2/run/8a475d6e-2191-445a-8733-98376b95b907/Main','c_cpp','/myproject2/testdata/problemId_1045/t10',10,'running',0,0),(2362,392,'/myproject2/run/caba916d-7643-4d39-a024-cad08de3d8b8/Main','c_cpp','/myproject2/testdata/problemId_1045/t1',1,'running',0,0),(2363,392,'/myproject2/run/caba916d-7643-4d39-a024-cad08de3d8b8/Main','c_cpp','/myproject2/testdata/problemId_1045/t2',2,'running',0,0),(2364,392,'/myproject2/run/caba916d-7643-4d39-a024-cad08de3d8b8/Main','c_cpp','/myproject2/testdata/problemId_1045/t3',3,'running',0,0),(2365,392,'/myproject2/run/caba916d-7643-4d39-a024-cad08de3d8b8/Main','c_cpp','/myproject2/testdata/problemId_1045/t4',4,'running',0,0),(2366,392,'/myproject2/run/caba916d-7643-4d39-a024-cad08de3d8b8/Main','c_cpp','/myproject2/testdata/problemId_1045/t5',5,'running',0,0),(2367,392,'/myproject2/run/caba916d-7643-4d39-a024-cad08de3d8b8/Main','c_cpp','/myproject2/testdata/problemId_1045/t6',6,'running',0,0),(2368,392,'/myproject2/run/caba916d-7643-4d39-a024-cad08de3d8b8/Main','c_cpp','/myproject2/testdata/problemId_1045/t7',7,'running',0,0),(2369,392,'/myproject2/run/caba916d-7643-4d39-a024-cad08de3d8b8/Main','c_cpp','/myproject2/testdata/problemId_1045/t8',8,'running',0,0),(2370,392,'/myproject2/run/caba916d-7643-4d39-a024-cad08de3d8b8/Main','c_cpp','/myproject2/testdata/problemId_1045/t9',9,'running',0,0),(2371,392,'/myproject2/run/caba916d-7643-4d39-a024-cad08de3d8b8/Main','c_cpp','/myproject2/testdata/problemId_1045/t10',10,'running',0,0),(2372,393,'/myproject2/run/64dd6fe2-9200-483e-bdf6-cb8ece635c01/Main','c_cpp','/myproject2/testdata/problemId_1045/t1',1,'running',0,0),(2373,393,'/myproject2/run/64dd6fe2-9200-483e-bdf6-cb8ece635c01/Main','c_cpp','/myproject2/testdata/problemId_1045/t2',2,'running',0,0),(2374,393,'/myproject2/run/64dd6fe2-9200-483e-bdf6-cb8ece635c01/Main','c_cpp','/myproject2/testdata/problemId_1045/t3',3,'running',0,0),(2375,393,'/myproject2/run/64dd6fe2-9200-483e-bdf6-cb8ece635c01/Main','c_cpp','/myproject2/testdata/problemId_1045/t4',4,'running',0,0),(2376,393,'/myproject2/run/64dd6fe2-9200-483e-bdf6-cb8ece635c01/Main','c_cpp','/myproject2/testdata/problemId_1045/t5',5,'running',0,0),(2377,393,'/myproject2/run/64dd6fe2-9200-483e-bdf6-cb8ece635c01/Main','c_cpp','/myproject2/testdata/problemId_1045/t6',6,'running',0,0),(2378,393,'/myproject2/run/64dd6fe2-9200-483e-bdf6-cb8ece635c01/Main','c_cpp','/myproject2/testdata/problemId_1045/t7',7,'running',0,0),(2379,393,'/myproject2/run/64dd6fe2-9200-483e-bdf6-cb8ece635c01/Main','c_cpp','/myproject2/testdata/problemId_1045/t8',8,'running',0,0),(2380,393,'/myproject2/run/64dd6fe2-9200-483e-bdf6-cb8ece635c01/Main','c_cpp','/myproject2/testdata/problemId_1045/t9',9,'running',0,0),(2381,393,'/myproject2/run/64dd6fe2-9200-483e-bdf6-cb8ece635c01/Main','c_cpp','/myproject2/testdata/problemId_1045/t10',10,'running',0,0),(2382,394,'/myproject2/run/9eb50b57-1cfc-4fc0-be90-be2bd40116ea/Main','c_cpp','/myproject2/testdata/problemId_1045/t1',1,'WA',1103,1928),(2383,394,'/myproject2/run/9eb50b57-1cfc-4fc0-be90-be2bd40116ea/Main','c_cpp','/myproject2/testdata/problemId_1045/t2',2,'WA',1131,2032),(2384,394,'/myproject2/run/9eb50b57-1cfc-4fc0-be90-be2bd40116ea/Main','c_cpp','/myproject2/testdata/problemId_1045/t3',3,'WA',1093,2060),(2385,394,'/myproject2/run/9eb50b57-1cfc-4fc0-be90-be2bd40116ea/Main','c_cpp','/myproject2/testdata/problemId_1045/t4',4,'WA',1064,1868),(2386,394,'/myproject2/run/9eb50b57-1cfc-4fc0-be90-be2bd40116ea/Main','c_cpp','/myproject2/testdata/problemId_1045/t5',5,'WA',991,2184),(2387,394,'/myproject2/run/9eb50b57-1cfc-4fc0-be90-be2bd40116ea/Main','c_cpp','/myproject2/testdata/problemId_1045/t6',6,'WA',1009,2080),(2388,394,'/myproject2/run/9eb50b57-1cfc-4fc0-be90-be2bd40116ea/Main','c_cpp','/myproject2/testdata/problemId_1045/t7',7,'WA',970,2072),(2389,394,'/myproject2/run/9eb50b57-1cfc-4fc0-be90-be2bd40116ea/Main','c_cpp','/myproject2/testdata/problemId_1045/t8',8,'WA',1003,1944),(2390,394,'/myproject2/run/9eb50b57-1cfc-4fc0-be90-be2bd40116ea/Main','c_cpp','/myproject2/testdata/problemId_1045/t9',9,'WA',949,2004),(2391,394,'/myproject2/run/9eb50b57-1cfc-4fc0-be90-be2bd40116ea/Main','c_cpp','/myproject2/testdata/problemId_1045/t10',10,'WA',991,2060),(2392,395,'/myproject2/run/1272d690-da70-4d68-a543-730c43112eb0/Main','c_cpp','/myproject2/testdata/problemId_1045/t1',1,'AC',20,1920),(2393,395,'/myproject2/run/1272d690-da70-4d68-a543-730c43112eb0/Main','c_cpp','/myproject2/testdata/problemId_1045/t2',2,'AC',35,2004),(2394,395,'/myproject2/run/1272d690-da70-4d68-a543-730c43112eb0/Main','c_cpp','/myproject2/testdata/problemId_1045/t3',3,'AC',47,2028),(2395,395,'/myproject2/run/1272d690-da70-4d68-a543-730c43112eb0/Main','c_cpp','/myproject2/testdata/problemId_1045/t4',4,'AC',16,1884),(2396,395,'/myproject2/run/1272d690-da70-4d68-a543-730c43112eb0/Main','c_cpp','/myproject2/testdata/problemId_1045/t5',5,'AC',63,2152),(2397,395,'/myproject2/run/1272d690-da70-4d68-a543-730c43112eb0/Main','c_cpp','/myproject2/testdata/problemId_1045/t6',6,'AC',39,2036),(2398,395,'/myproject2/run/1272d690-da70-4d68-a543-730c43112eb0/Main','c_cpp','/myproject2/testdata/problemId_1045/t7',7,'AC',48,2040),(2399,395,'/myproject2/run/1272d690-da70-4d68-a543-730c43112eb0/Main','c_cpp','/myproject2/testdata/problemId_1045/t8',8,'AC',22,1876),(2400,395,'/myproject2/run/1272d690-da70-4d68-a543-730c43112eb0/Main','c_cpp','/myproject2/testdata/problemId_1045/t9',9,'AC',34,1980),(2401,395,'/myproject2/run/1272d690-da70-4d68-a543-730c43112eb0/Main','c_cpp','/myproject2/testdata/problemId_1045/t10',10,'AC',46,2056),(2402,396,'/myproject2/run/636ce91e-6061-41c6-9ada-d92467aec960/Main','c_cpp','/myproject2/testdata/problemId_1046/t1',1,'WA',2,1832),(2403,396,'/myproject2/run/636ce91e-6061-41c6-9ada-d92467aec960/Main','c_cpp','/myproject2/testdata/problemId_1046/t2',2,'WA',2,1828),(2404,396,'/myproject2/run/636ce91e-6061-41c6-9ada-d92467aec960/Main','c_cpp','/myproject2/testdata/problemId_1046/t3',3,'WA',3,1792),(2405,396,'/myproject2/run/636ce91e-6061-41c6-9ada-d92467aec960/Main','c_cpp','/myproject2/testdata/problemId_1046/t4',4,'WA',3,1800),(2406,396,'/myproject2/run/636ce91e-6061-41c6-9ada-d92467aec960/Main','c_cpp','/myproject2/testdata/problemId_1046/t5',5,'WA',4,1800),(2407,396,'/myproject2/run/636ce91e-6061-41c6-9ada-d92467aec960/Main','c_cpp','/myproject2/testdata/problemId_1046/t6',6,'WA',3,1800),(2408,396,'/myproject2/run/636ce91e-6061-41c6-9ada-d92467aec960/Main','c_cpp','/myproject2/testdata/problemId_1046/t7',7,'WA',3,1800),(2409,396,'/myproject2/run/636ce91e-6061-41c6-9ada-d92467aec960/Main','c_cpp','/myproject2/testdata/problemId_1046/t8',8,'WA',2,1796),(2410,396,'/myproject2/run/636ce91e-6061-41c6-9ada-d92467aec960/Main','c_cpp','/myproject2/testdata/problemId_1046/t9',9,'WA',2,1840),(2411,396,'/myproject2/run/636ce91e-6061-41c6-9ada-d92467aec960/Main','c_cpp','/myproject2/testdata/problemId_1046/t10',10,'WA',3,1800),(2412,397,'/myproject2/run/bb49134f-afb2-4ebf-a919-006d5404ed64/Main','c_cpp','/myproject2/testdata/problemId_1041/t1',1,'AC',2,1780),(2413,397,'/myproject2/run/bb49134f-afb2-4ebf-a919-006d5404ed64/Main','c_cpp','/myproject2/testdata/problemId_1041/t2',2,'AC',3,1800),(2414,397,'/myproject2/run/bb49134f-afb2-4ebf-a919-006d5404ed64/Main','c_cpp','/myproject2/testdata/problemId_1041/t3',3,'AC',2,1780),(2415,397,'/myproject2/run/bb49134f-afb2-4ebf-a919-006d5404ed64/Main','c_cpp','/myproject2/testdata/problemId_1041/t4',4,'AC',2,1848),(2416,397,'/myproject2/run/bb49134f-afb2-4ebf-a919-006d5404ed64/Main','c_cpp','/myproject2/testdata/problemId_1041/t5',5,'AC',2,1832),(2417,397,'/myproject2/run/bb49134f-afb2-4ebf-a919-006d5404ed64/Main','c_cpp','/myproject2/testdata/problemId_1041/t6',6,'AC',2,1788),(2418,397,'/myproject2/run/bb49134f-afb2-4ebf-a919-006d5404ed64/Main','c_cpp','/myproject2/testdata/problemId_1041/t7',7,'AC',2,1844),(2419,397,'/myproject2/run/bb49134f-afb2-4ebf-a919-006d5404ed64/Main','c_cpp','/myproject2/testdata/problemId_1041/t8',8,'AC',2,1808),(2420,397,'/myproject2/run/bb49134f-afb2-4ebf-a919-006d5404ed64/Main','c_cpp','/myproject2/testdata/problemId_1041/t9',9,'AC',2,1820),(2421,397,'/myproject2/run/bb49134f-afb2-4ebf-a919-006d5404ed64/Main','c_cpp','/myproject2/testdata/problemId_1041/t10',10,'AC',2,1796),(2422,404,'/myproject2/run/ca50bc6c-3e16-4182-9ca6-1a39cda64c42/Main','c_cpp','/myproject2/testdata/problemId_1041/t1',1,'AC',0,1792),(2423,404,'/myproject2/run/ca50bc6c-3e16-4182-9ca6-1a39cda64c42/Main','c_cpp','/myproject2/testdata/problemId_1041/t2',2,'AC',0,1760),(2424,404,'/myproject2/run/ca50bc6c-3e16-4182-9ca6-1a39cda64c42/Main','c_cpp','/myproject2/testdata/problemId_1041/t3',3,'AC',0,1796),(2425,404,'/myproject2/run/ca50bc6c-3e16-4182-9ca6-1a39cda64c42/Main','c_cpp','/myproject2/testdata/problemId_1041/t4',4,'AC',0,1736),(2426,404,'/myproject2/run/ca50bc6c-3e16-4182-9ca6-1a39cda64c42/Main','c_cpp','/myproject2/testdata/problemId_1041/t5',5,'AC',0,1740),(2427,404,'/myproject2/run/ca50bc6c-3e16-4182-9ca6-1a39cda64c42/Main','c_cpp','/myproject2/testdata/problemId_1041/t6',6,'running',0,0),(2428,404,'/myproject2/run/ca50bc6c-3e16-4182-9ca6-1a39cda64c42/Main','c_cpp','/myproject2/testdata/problemId_1041/t7',7,'AC',0,1792),(2429,404,'/myproject2/run/ca50bc6c-3e16-4182-9ca6-1a39cda64c42/Main','c_cpp','/myproject2/testdata/problemId_1041/t8',8,'AC',0,1732),(2430,404,'/myproject2/run/ca50bc6c-3e16-4182-9ca6-1a39cda64c42/Main','c_cpp','/myproject2/testdata/problemId_1041/t9',9,'AC',0,1724),(2431,404,'/myproject2/run/ca50bc6c-3e16-4182-9ca6-1a39cda64c42/Main','c_cpp','/myproject2/testdata/problemId_1041/t10',10,'AC',0,1784),(2432,405,'/myproject2/run/8a6d1fcf-1e4d-49a7-9645-1307d4288548/Main','c_cpp','/myproject2/testdata/problemId_1041/t1',1,'AC',0,1720),(2433,405,'/myproject2/run/8a6d1fcf-1e4d-49a7-9645-1307d4288548/Main','c_cpp','/myproject2/testdata/problemId_1041/t2',2,'AC',0,1792),(2434,405,'/myproject2/run/8a6d1fcf-1e4d-49a7-9645-1307d4288548/Main','c_cpp','/myproject2/testdata/problemId_1041/t3',3,'AC',0,1760),(2435,405,'/myproject2/run/8a6d1fcf-1e4d-49a7-9645-1307d4288548/Main','c_cpp','/myproject2/testdata/problemId_1041/t4',4,'AC',0,1784),(2436,405,'/myproject2/run/8a6d1fcf-1e4d-49a7-9645-1307d4288548/Main','c_cpp','/myproject2/testdata/problemId_1041/t5',5,'AC',0,1732),(2437,405,'/myproject2/run/8a6d1fcf-1e4d-49a7-9645-1307d4288548/Main','c_cpp','/myproject2/testdata/problemId_1041/t6',6,'AC',0,1792),(2438,405,'/myproject2/run/8a6d1fcf-1e4d-49a7-9645-1307d4288548/Main','c_cpp','/myproject2/testdata/problemId_1041/t7',7,'AC',0,1760),(2439,405,'/myproject2/run/8a6d1fcf-1e4d-49a7-9645-1307d4288548/Main','c_cpp','/myproject2/testdata/problemId_1041/t8',8,'AC',0,1768),(2440,405,'/myproject2/run/8a6d1fcf-1e4d-49a7-9645-1307d4288548/Main','c_cpp','/myproject2/testdata/problemId_1041/t9',9,'AC',0,1720),(2441,405,'/myproject2/run/8a6d1fcf-1e4d-49a7-9645-1307d4288548/Main','c_cpp','/myproject2/testdata/problemId_1041/t10',10,'AC',0,1768),(2442,406,'/myproject2/run/9b2ad4eb-093b-4797-9037-4927355f6d75/Main','c_cpp','/myproject2/testdata/problemId_1041/t1',1,'AC',0,1756),(2443,406,'/myproject2/run/9b2ad4eb-093b-4797-9037-4927355f6d75/Main','c_cpp','/myproject2/testdata/problemId_1041/t2',2,'AC',0,1744),(2444,406,'/myproject2/run/9b2ad4eb-093b-4797-9037-4927355f6d75/Main','c_cpp','/myproject2/testdata/problemId_1041/t3',3,'AC',0,1792),(2445,406,'/myproject2/run/9b2ad4eb-093b-4797-9037-4927355f6d75/Main','c_cpp','/myproject2/testdata/problemId_1041/t4',4,'AC',0,1824),(2446,406,'/myproject2/run/9b2ad4eb-093b-4797-9037-4927355f6d75/Main','c_cpp','/myproject2/testdata/problemId_1041/t5',5,'AC',0,1768),(2447,406,'/myproject2/run/9b2ad4eb-093b-4797-9037-4927355f6d75/Main','c_cpp','/myproject2/testdata/problemId_1041/t6',6,'AC',0,1768),(2448,406,'/myproject2/run/9b2ad4eb-093b-4797-9037-4927355f6d75/Main','c_cpp','/myproject2/testdata/problemId_1041/t7',7,'AC',0,1784),(2449,406,'/myproject2/run/9b2ad4eb-093b-4797-9037-4927355f6d75/Main','c_cpp','/myproject2/testdata/problemId_1041/t8',8,'AC',0,1840),(2450,406,'/myproject2/run/9b2ad4eb-093b-4797-9037-4927355f6d75/Main','c_cpp','/myproject2/testdata/problemId_1041/t9',9,'AC',0,1796),(2451,406,'/myproject2/run/9b2ad4eb-093b-4797-9037-4927355f6d75/Main','c_cpp','/myproject2/testdata/problemId_1041/t10',10,'AC',0,1828),(2452,411,'/myproject2/run/260b748c-1972-49a7-aa9d-02a31c0485b3/Main','c_cpp','/myproject2/testdata/problemId_1041/t1',1,'AC',0,1776),(2453,411,'/myproject2/run/260b748c-1972-49a7-aa9d-02a31c0485b3/Main','c_cpp','/myproject2/testdata/problemId_1041/t2',2,'AC',0,1772),(2454,411,'/myproject2/run/260b748c-1972-49a7-aa9d-02a31c0485b3/Main','c_cpp','/myproject2/testdata/problemId_1041/t3',3,'AC',0,1768),(2455,411,'/myproject2/run/260b748c-1972-49a7-aa9d-02a31c0485b3/Main','c_cpp','/myproject2/testdata/problemId_1041/t4',4,'AC',0,1764),(2456,411,'/myproject2/run/260b748c-1972-49a7-aa9d-02a31c0485b3/Main','c_cpp','/myproject2/testdata/problemId_1041/t5',5,'AC',0,1768),(2457,411,'/myproject2/run/260b748c-1972-49a7-aa9d-02a31c0485b3/Main','c_cpp','/myproject2/testdata/problemId_1041/t6',6,'AC',0,1764),(2458,411,'/myproject2/run/260b748c-1972-49a7-aa9d-02a31c0485b3/Main','c_cpp','/myproject2/testdata/problemId_1041/t7',7,'AC',0,1796),(2459,411,'/myproject2/run/260b748c-1972-49a7-aa9d-02a31c0485b3/Main','c_cpp','/myproject2/testdata/problemId_1041/t8',8,'AC',0,1816),(2460,411,'/myproject2/run/260b748c-1972-49a7-aa9d-02a31c0485b3/Main','c_cpp','/myproject2/testdata/problemId_1041/t9',9,'AC',0,1780),(2461,411,'/myproject2/run/260b748c-1972-49a7-aa9d-02a31c0485b3/Main','c_cpp','/myproject2/testdata/problemId_1041/t10',10,'AC',0,1772),(2462,412,'/myproject2/run/55b41f4d-1c1f-4aa1-b12e-e94d13e73883/Main','c_cpp','/myproject2/testdata/problemId_1041/t1',1,'AC',0,1804),(2463,412,'/myproject2/run/55b41f4d-1c1f-4aa1-b12e-e94d13e73883/Main','c_cpp','/myproject2/testdata/problemId_1041/t2',2,'AC',0,1760),(2464,412,'/myproject2/run/55b41f4d-1c1f-4aa1-b12e-e94d13e73883/Main','c_cpp','/myproject2/testdata/problemId_1041/t3',3,'AC',0,1776),(2465,412,'/myproject2/run/55b41f4d-1c1f-4aa1-b12e-e94d13e73883/Main','c_cpp','/myproject2/testdata/problemId_1041/t4',4,'AC',0,1768),(2466,412,'/myproject2/run/55b41f4d-1c1f-4aa1-b12e-e94d13e73883/Main','c_cpp','/myproject2/testdata/problemId_1041/t5',5,'AC',0,1812),(2467,412,'/myproject2/run/55b41f4d-1c1f-4aa1-b12e-e94d13e73883/Main','c_cpp','/myproject2/testdata/problemId_1041/t6',6,'AC',0,1808),(2468,412,'/myproject2/run/55b41f4d-1c1f-4aa1-b12e-e94d13e73883/Main','c_cpp','/myproject2/testdata/problemId_1041/t7',7,'AC',0,1808),(2469,412,'/myproject2/run/55b41f4d-1c1f-4aa1-b12e-e94d13e73883/Main','c_cpp','/myproject2/testdata/problemId_1041/t8',8,'AC',0,1808),(2470,412,'/myproject2/run/55b41f4d-1c1f-4aa1-b12e-e94d13e73883/Main','c_cpp','/myproject2/testdata/problemId_1041/t9',9,'AC',0,1816),(2471,412,'/myproject2/run/55b41f4d-1c1f-4aa1-b12e-e94d13e73883/Main','c_cpp','/myproject2/testdata/problemId_1041/t10',10,'AC',0,1752),(2472,413,'/myproject2/run/b1fb6a99-6fdf-40f6-b975-cdacb904c946/Main','c_cpp','/myproject2/testdata/problemId_1041/t1',1,'AC',0,1800),(2473,413,'/myproject2/run/b1fb6a99-6fdf-40f6-b975-cdacb904c946/Main','c_cpp','/myproject2/testdata/problemId_1041/t2',2,'AC',0,1760),(2474,413,'/myproject2/run/b1fb6a99-6fdf-40f6-b975-cdacb904c946/Main','c_cpp','/myproject2/testdata/problemId_1041/t3',3,'AC',0,1780),(2475,413,'/myproject2/run/b1fb6a99-6fdf-40f6-b975-cdacb904c946/Main','c_cpp','/myproject2/testdata/problemId_1041/t4',4,'AC',0,1764),(2476,413,'/myproject2/run/b1fb6a99-6fdf-40f6-b975-cdacb904c946/Main','c_cpp','/myproject2/testdata/problemId_1041/t5',5,'AC',0,1800),(2477,413,'/myproject2/run/b1fb6a99-6fdf-40f6-b975-cdacb904c946/Main','c_cpp','/myproject2/testdata/problemId_1041/t6',6,'AC',0,1800),(2478,413,'/myproject2/run/b1fb6a99-6fdf-40f6-b975-cdacb904c946/Main','c_cpp','/myproject2/testdata/problemId_1041/t7',7,'AC',0,1780),(2479,413,'/myproject2/run/b1fb6a99-6fdf-40f6-b975-cdacb904c946/Main','c_cpp','/myproject2/testdata/problemId_1041/t8',8,'AC',0,1768),(2480,413,'/myproject2/run/b1fb6a99-6fdf-40f6-b975-cdacb904c946/Main','c_cpp','/myproject2/testdata/problemId_1041/t9',9,'AC',0,1796),(2481,413,'/myproject2/run/b1fb6a99-6fdf-40f6-b975-cdacb904c946/Main','c_cpp','/myproject2/testdata/problemId_1041/t10',10,'AC',0,1760),(2482,419,'/myproject2/run/d3b3ea4e-39e2-49a5-af51-e4a5a60763ac/Main','c_cpp','/myproject2/testdata/problemId_1041/t1',1,'AC',0,1772),(2483,419,'/myproject2/run/d3b3ea4e-39e2-49a5-af51-e4a5a60763ac/Main','c_cpp','/myproject2/testdata/problemId_1041/t2',2,'AC',0,1760),(2484,419,'/myproject2/run/d3b3ea4e-39e2-49a5-af51-e4a5a60763ac/Main','c_cpp','/myproject2/testdata/problemId_1041/t3',3,'AC',0,1768),(2485,419,'/myproject2/run/d3b3ea4e-39e2-49a5-af51-e4a5a60763ac/Main','c_cpp','/myproject2/testdata/problemId_1041/t4',4,'AC',0,1768),(2486,419,'/myproject2/run/d3b3ea4e-39e2-49a5-af51-e4a5a60763ac/Main','c_cpp','/myproject2/testdata/problemId_1041/t5',5,'AC',0,1764),(2487,419,'/myproject2/run/d3b3ea4e-39e2-49a5-af51-e4a5a60763ac/Main','c_cpp','/myproject2/testdata/problemId_1041/t6',6,'AC',0,1764),(2488,419,'/myproject2/run/d3b3ea4e-39e2-49a5-af51-e4a5a60763ac/Main','c_cpp','/myproject2/testdata/problemId_1041/t7',7,'AC',0,1796),(2489,419,'/myproject2/run/d3b3ea4e-39e2-49a5-af51-e4a5a60763ac/Main','c_cpp','/myproject2/testdata/problemId_1041/t8',8,'AC',0,1756),(2490,419,'/myproject2/run/d3b3ea4e-39e2-49a5-af51-e4a5a60763ac/Main','c_cpp','/myproject2/testdata/problemId_1041/t9',9,'AC',0,1792),(2491,419,'/myproject2/run/d3b3ea4e-39e2-49a5-af51-e4a5a60763ac/Main','c_cpp','/myproject2/testdata/problemId_1041/t10',10,'AC',0,1768),(2492,420,'/myproject2/run/9777adac-f77a-4e6e-af50-266d65cdbd64/Main','c_cpp','/myproject2/testdata/problemId_1041/t1',1,'AC',0,1776),(2493,420,'/myproject2/run/9777adac-f77a-4e6e-af50-266d65cdbd64/Main','c_cpp','/myproject2/testdata/problemId_1041/t2',2,'AC',0,1772),(2494,420,'/myproject2/run/9777adac-f77a-4e6e-af50-266d65cdbd64/Main','c_cpp','/myproject2/testdata/problemId_1041/t3',3,'AC',0,1808),(2495,420,'/myproject2/run/9777adac-f77a-4e6e-af50-266d65cdbd64/Main','c_cpp','/myproject2/testdata/problemId_1041/t4',4,'AC',0,1824),(2496,420,'/myproject2/run/9777adac-f77a-4e6e-af50-266d65cdbd64/Main','c_cpp','/myproject2/testdata/problemId_1041/t5',5,'AC',0,1776),(2497,420,'/myproject2/run/9777adac-f77a-4e6e-af50-266d65cdbd64/Main','c_cpp','/myproject2/testdata/problemId_1041/t6',6,'AC',0,1820),(2498,420,'/myproject2/run/9777adac-f77a-4e6e-af50-266d65cdbd64/Main','c_cpp','/myproject2/testdata/problemId_1041/t7',7,'AC',0,1812),(2499,420,'/myproject2/run/9777adac-f77a-4e6e-af50-266d65cdbd64/Main','c_cpp','/myproject2/testdata/problemId_1041/t8',8,'AC',0,1764),(2500,420,'/myproject2/run/9777adac-f77a-4e6e-af50-266d65cdbd64/Main','c_cpp','/myproject2/testdata/problemId_1041/t9',9,'AC',0,1812),(2501,420,'/myproject2/run/9777adac-f77a-4e6e-af50-266d65cdbd64/Main','c_cpp','/myproject2/testdata/problemId_1041/t10',10,'AC',0,1756),(2502,422,'/myproject2/run/184a40ce-50f0-4b07-b993-affdea3b7714/Main','c_cpp','/myproject2/testdata/problemId_1041/t1',1,'AC',0,1780),(2503,422,'/myproject2/run/184a40ce-50f0-4b07-b993-affdea3b7714/Main','c_cpp','/myproject2/testdata/problemId_1041/t2',2,'AC',0,1800),(2504,422,'/myproject2/run/184a40ce-50f0-4b07-b993-affdea3b7714/Main','c_cpp','/myproject2/testdata/problemId_1041/t3',3,'AC',0,1768),(2505,422,'/myproject2/run/184a40ce-50f0-4b07-b993-affdea3b7714/Main','c_cpp','/myproject2/testdata/problemId_1041/t4',4,'AC',0,1820),(2506,422,'/myproject2/run/184a40ce-50f0-4b07-b993-affdea3b7714/Main','c_cpp','/myproject2/testdata/problemId_1041/t5',5,'AC',0,1820),(2507,422,'/myproject2/run/184a40ce-50f0-4b07-b993-affdea3b7714/Main','c_cpp','/myproject2/testdata/problemId_1041/t6',6,'AC',0,1760),(2508,422,'/myproject2/run/184a40ce-50f0-4b07-b993-affdea3b7714/Main','c_cpp','/myproject2/testdata/problemId_1041/t7',7,'AC',0,1768),(2509,422,'/myproject2/run/184a40ce-50f0-4b07-b993-affdea3b7714/Main','c_cpp','/myproject2/testdata/problemId_1041/t8',8,'AC',0,1772),(2510,422,'/myproject2/run/184a40ce-50f0-4b07-b993-affdea3b7714/Main','c_cpp','/myproject2/testdata/problemId_1041/t9',9,'AC',0,1804),(2511,422,'/myproject2/run/184a40ce-50f0-4b07-b993-affdea3b7714/Main','c_cpp','/myproject2/testdata/problemId_1041/t10',10,'AC',0,1768),(2512,423,'/myproject2/run/c1df1fe1-9b05-488a-93c3-e4c8332ab585/Main','c_cpp','/myproject2/testdata/problemId_1041/t1',1,'AC',0,1768),(2513,423,'/myproject2/run/c1df1fe1-9b05-488a-93c3-e4c8332ab585/Main','c_cpp','/myproject2/testdata/problemId_1041/t2',2,'AC',0,1816),(2514,423,'/myproject2/run/c1df1fe1-9b05-488a-93c3-e4c8332ab585/Main','c_cpp','/myproject2/testdata/problemId_1041/t3',3,'AC',0,1772),(2515,423,'/myproject2/run/c1df1fe1-9b05-488a-93c3-e4c8332ab585/Main','c_cpp','/myproject2/testdata/problemId_1041/t4',4,'AC',0,1764),(2516,423,'/myproject2/run/c1df1fe1-9b05-488a-93c3-e4c8332ab585/Main','c_cpp','/myproject2/testdata/problemId_1041/t5',5,'AC',0,1824),(2517,423,'/myproject2/run/c1df1fe1-9b05-488a-93c3-e4c8332ab585/Main','c_cpp','/myproject2/testdata/problemId_1041/t6',6,'AC',0,1768),(2518,423,'/myproject2/run/c1df1fe1-9b05-488a-93c3-e4c8332ab585/Main','c_cpp','/myproject2/testdata/problemId_1041/t7',7,'AC',0,1804),(2519,423,'/myproject2/run/c1df1fe1-9b05-488a-93c3-e4c8332ab585/Main','c_cpp','/myproject2/testdata/problemId_1041/t8',8,'AC',0,1804),(2520,423,'/myproject2/run/c1df1fe1-9b05-488a-93c3-e4c8332ab585/Main','c_cpp','/myproject2/testdata/problemId_1041/t9',9,'AC',0,1772),(2521,423,'/myproject2/run/c1df1fe1-9b05-488a-93c3-e4c8332ab585/Main','c_cpp','/myproject2/testdata/problemId_1041/t10',10,'AC',0,1824),(2522,425,'/myproject2/run/938816f2-adf1-4c8f-a388-173964a6ad21/Main','c_cpp','/myproject2/testdata/problemId_1047/t1',1,'AC',0,1780),(2523,425,'/myproject2/run/938816f2-adf1-4c8f-a388-173964a6ad21/Main','c_cpp','/myproject2/testdata/problemId_1047/t2',2,'AC',0,1812),(2524,425,'/myproject2/run/938816f2-adf1-4c8f-a388-173964a6ad21/Main','c_cpp','/myproject2/testdata/problemId_1047/t3',3,'AC',0,1824),(2525,425,'/myproject2/run/938816f2-adf1-4c8f-a388-173964a6ad21/Main','c_cpp','/myproject2/testdata/problemId_1047/t4',4,'running',0,0),(2526,425,'/myproject2/run/938816f2-adf1-4c8f-a388-173964a6ad21/Main','c_cpp','/myproject2/testdata/problemId_1047/t5',5,'running',0,0),(2527,425,'/myproject2/run/938816f2-adf1-4c8f-a388-173964a6ad21/Main','c_cpp','/myproject2/testdata/problemId_1047/t6',6,'running',0,0),(2528,425,'/myproject2/run/938816f2-adf1-4c8f-a388-173964a6ad21/Main','c_cpp','/myproject2/testdata/problemId_1047/t7',7,'running',0,0),(2529,425,'/myproject2/run/938816f2-adf1-4c8f-a388-173964a6ad21/Main','c_cpp','/myproject2/testdata/problemId_1047/t8',8,'running',0,0),(2530,425,'/myproject2/run/938816f2-adf1-4c8f-a388-173964a6ad21/Main','c_cpp','/myproject2/testdata/problemId_1047/t9',9,'running',0,0),(2531,425,'/myproject2/run/938816f2-adf1-4c8f-a388-173964a6ad21/Main','c_cpp','/myproject2/testdata/problemId_1047/t10',10,'running',0,0),(2532,427,'/myproject2/run/562923b8-72af-4370-b4f7-dcc227c6eed9/Main','c_cpp','/myproject2/testdata/problemId_1041/t1',1,'AC',0,1768),(2533,427,'/myproject2/run/562923b8-72af-4370-b4f7-dcc227c6eed9/Main','c_cpp','/myproject2/testdata/problemId_1041/t2',2,'AC',0,1776),(2534,427,'/myproject2/run/562923b8-72af-4370-b4f7-dcc227c6eed9/Main','c_cpp','/myproject2/testdata/problemId_1041/t3',3,'AC',0,1792),(2535,427,'/myproject2/run/562923b8-72af-4370-b4f7-dcc227c6eed9/Main','c_cpp','/myproject2/testdata/problemId_1041/t4',4,'AC',0,1796),(2536,427,'/myproject2/run/562923b8-72af-4370-b4f7-dcc227c6eed9/Main','c_cpp','/myproject2/testdata/problemId_1041/t5',5,'AC',0,1772),(2537,427,'/myproject2/run/562923b8-72af-4370-b4f7-dcc227c6eed9/Main','c_cpp','/myproject2/testdata/problemId_1041/t6',6,'AC',0,1800),(2538,427,'/myproject2/run/562923b8-72af-4370-b4f7-dcc227c6eed9/Main','c_cpp','/myproject2/testdata/problemId_1041/t7',7,'AC',0,1816),(2539,427,'/myproject2/run/562923b8-72af-4370-b4f7-dcc227c6eed9/Main','c_cpp','/myproject2/testdata/problemId_1041/t8',8,'AC',0,1796),(2540,427,'/myproject2/run/562923b8-72af-4370-b4f7-dcc227c6eed9/Main','c_cpp','/myproject2/testdata/problemId_1041/t9',9,'AC',0,1816),(2541,427,'/myproject2/run/562923b8-72af-4370-b4f7-dcc227c6eed9/Main','c_cpp','/myproject2/testdata/problemId_1041/t10',10,'AC',0,1816),(2542,431,'/myproject2/run/a78a11ab-497b-456b-bb3d-1db175bbe47d/Main','c_cpp','/myproject2/testdata/problemId_1041/t1',1,'AC',0,1844),(2543,431,'/myproject2/run/a78a11ab-497b-456b-bb3d-1db175bbe47d/Main','c_cpp','/myproject2/testdata/problemId_1041/t2',2,'AC',0,1808),(2544,431,'/myproject2/run/a78a11ab-497b-456b-bb3d-1db175bbe47d/Main','c_cpp','/myproject2/testdata/problemId_1041/t3',3,'AC',0,1780),(2545,431,'/myproject2/run/a78a11ab-497b-456b-bb3d-1db175bbe47d/Main','c_cpp','/myproject2/testdata/problemId_1041/t4',4,'AC',0,1780),(2546,431,'/myproject2/run/a78a11ab-497b-456b-bb3d-1db175bbe47d/Main','c_cpp','/myproject2/testdata/problemId_1041/t5',5,'AC',0,1844),(2547,431,'/myproject2/run/a78a11ab-497b-456b-bb3d-1db175bbe47d/Main','c_cpp','/myproject2/testdata/problemId_1041/t6',6,'AC',0,1784),(2548,431,'/myproject2/run/a78a11ab-497b-456b-bb3d-1db175bbe47d/Main','c_cpp','/myproject2/testdata/problemId_1041/t7',7,'AC',0,1796),(2549,431,'/myproject2/run/a78a11ab-497b-456b-bb3d-1db175bbe47d/Main','c_cpp','/myproject2/testdata/problemId_1041/t8',8,'AC',0,1800),(2550,431,'/myproject2/run/a78a11ab-497b-456b-bb3d-1db175bbe47d/Main','c_cpp','/myproject2/testdata/problemId_1041/t9',9,'AC',0,1788),(2551,431,'/myproject2/run/a78a11ab-497b-456b-bb3d-1db175bbe47d/Main','c_cpp','/myproject2/testdata/problemId_1041/t10',10,'AC',0,1832),(2552,432,'/myproject2/run/068ceee9-9761-419f-8365-4297437a77f5/Main','c_cpp','/myproject2/testdata/problemId_1047/t1',1,'AC',0,1796),(2553,432,'/myproject2/run/068ceee9-9761-419f-8365-4297437a77f5/Main','c_cpp','/myproject2/testdata/problemId_1047/t2',2,'AC',0,1808),(2554,432,'/myproject2/run/068ceee9-9761-419f-8365-4297437a77f5/Main','c_cpp','/myproject2/testdata/problemId_1047/t3',3,'AC',0,1848),(2555,432,'/myproject2/run/068ceee9-9761-419f-8365-4297437a77f5/Main','c_cpp','/myproject2/testdata/problemId_1047/t4',4,'AC',0,1828),(2556,432,'/myproject2/run/068ceee9-9761-419f-8365-4297437a77f5/Main','c_cpp','/myproject2/testdata/problemId_1047/t5',5,'AC',0,1788),(2557,432,'/myproject2/run/068ceee9-9761-419f-8365-4297437a77f5/Main','c_cpp','/myproject2/testdata/problemId_1047/t6',6,'AC',0,1844),(2558,432,'/myproject2/run/068ceee9-9761-419f-8365-4297437a77f5/Main','c_cpp','/myproject2/testdata/problemId_1047/t7',7,'AC',0,1784),(2559,432,'/myproject2/run/068ceee9-9761-419f-8365-4297437a77f5/Main','c_cpp','/myproject2/testdata/problemId_1047/t8',8,'AC',0,1812),(2560,432,'/myproject2/run/068ceee9-9761-419f-8365-4297437a77f5/Main','c_cpp','/myproject2/testdata/problemId_1047/t9',9,'AC',0,1800),(2561,432,'/myproject2/run/068ceee9-9761-419f-8365-4297437a77f5/Main','c_cpp','/myproject2/testdata/problemId_1047/t10',10,'AC',0,1840),(2562,435,'/myproject2/run/c563c390-5a56-4a12-8ff2-a6ddf42e38ce/Main','c_cpp','/myproject2/testdata/problemId_1041/t1',1,'AC',0,1776),(2563,435,'/myproject2/run/c563c390-5a56-4a12-8ff2-a6ddf42e38ce/Main','c_cpp','/myproject2/testdata/problemId_1041/t2',2,'AC',0,1772),(2564,435,'/myproject2/run/c563c390-5a56-4a12-8ff2-a6ddf42e38ce/Main','c_cpp','/myproject2/testdata/problemId_1041/t3',3,'AC',0,1784),(2565,435,'/myproject2/run/c563c390-5a56-4a12-8ff2-a6ddf42e38ce/Main','c_cpp','/myproject2/testdata/problemId_1041/t4',4,'AC',0,1768),(2566,435,'/myproject2/run/c563c390-5a56-4a12-8ff2-a6ddf42e38ce/Main','c_cpp','/myproject2/testdata/problemId_1041/t5',5,'AC',0,1816),(2567,435,'/myproject2/run/c563c390-5a56-4a12-8ff2-a6ddf42e38ce/Main','c_cpp','/myproject2/testdata/problemId_1041/t6',6,'AC',0,1820),(2568,435,'/myproject2/run/c563c390-5a56-4a12-8ff2-a6ddf42e38ce/Main','c_cpp','/myproject2/testdata/problemId_1041/t7',7,'AC',0,1780),(2569,435,'/myproject2/run/c563c390-5a56-4a12-8ff2-a6ddf42e38ce/Main','c_cpp','/myproject2/testdata/problemId_1041/t8',8,'AC',0,1768),(2570,435,'/myproject2/run/c563c390-5a56-4a12-8ff2-a6ddf42e38ce/Main','c_cpp','/myproject2/testdata/problemId_1041/t9',9,'AC',0,1800),(2571,435,'/myproject2/run/c563c390-5a56-4a12-8ff2-a6ddf42e38ce/Main','c_cpp','/myproject2/testdata/problemId_1041/t10',10,'AC',0,1792),(2572,436,'/myproject2/run/0f0baa2b-815b-4d69-bcd9-7e432f61a928/Main','c_cpp','/myproject2/testdata/problemId_1047/t1',1,'WA',0,1804),(2573,436,'/myproject2/run/0f0baa2b-815b-4d69-bcd9-7e432f61a928/Main','c_cpp','/myproject2/testdata/problemId_1047/t2',2,'WA',0,1804),(2574,436,'/myproject2/run/0f0baa2b-815b-4d69-bcd9-7e432f61a928/Main','c_cpp','/myproject2/testdata/problemId_1047/t3',3,'WA',0,1800),(2575,436,'/myproject2/run/0f0baa2b-815b-4d69-bcd9-7e432f61a928/Main','c_cpp','/myproject2/testdata/problemId_1047/t4',4,'WA',0,1760),(2576,436,'/myproject2/run/0f0baa2b-815b-4d69-bcd9-7e432f61a928/Main','c_cpp','/myproject2/testdata/problemId_1047/t5',5,'WA',0,1772),(2577,436,'/myproject2/run/0f0baa2b-815b-4d69-bcd9-7e432f61a928/Main','c_cpp','/myproject2/testdata/problemId_1047/t6',6,'WA',0,1800),(2578,436,'/myproject2/run/0f0baa2b-815b-4d69-bcd9-7e432f61a928/Main','c_cpp','/myproject2/testdata/problemId_1047/t7',7,'WA',0,1800),(2579,436,'/myproject2/run/0f0baa2b-815b-4d69-bcd9-7e432f61a928/Main','c_cpp','/myproject2/testdata/problemId_1047/t8',8,'WA',0,1780),(2580,436,'/myproject2/run/0f0baa2b-815b-4d69-bcd9-7e432f61a928/Main','c_cpp','/myproject2/testdata/problemId_1047/t9',9,'WA',0,1804),(2581,436,'/myproject2/run/0f0baa2b-815b-4d69-bcd9-7e432f61a928/Main','c_cpp','/myproject2/testdata/problemId_1047/t10',10,'WA',0,1784),(2582,437,'/myproject2/run/f6ffbaff-1ff2-4bb5-a7e6-be51f5260405/Main','c_cpp','/myproject2/testdata/problemId_1041/t1',1,'AC',0,1760),(2583,437,'/myproject2/run/f6ffbaff-1ff2-4bb5-a7e6-be51f5260405/Main','c_cpp','/myproject2/testdata/problemId_1041/t2',2,'AC',0,1812),(2584,437,'/myproject2/run/f6ffbaff-1ff2-4bb5-a7e6-be51f5260405/Main','c_cpp','/myproject2/testdata/problemId_1041/t3',3,'AC',0,1792),(2585,437,'/myproject2/run/f6ffbaff-1ff2-4bb5-a7e6-be51f5260405/Main','c_cpp','/myproject2/testdata/problemId_1041/t4',4,'AC',0,1764),(2586,437,'/myproject2/run/f6ffbaff-1ff2-4bb5-a7e6-be51f5260405/Main','c_cpp','/myproject2/testdata/problemId_1041/t5',5,'AC',0,1760),(2587,437,'/myproject2/run/f6ffbaff-1ff2-4bb5-a7e6-be51f5260405/Main','c_cpp','/myproject2/testdata/problemId_1041/t6',6,'AC',0,1768),(2588,437,'/myproject2/run/f6ffbaff-1ff2-4bb5-a7e6-be51f5260405/Main','c_cpp','/myproject2/testdata/problemId_1041/t7',7,'AC',0,1816),(2589,437,'/myproject2/run/f6ffbaff-1ff2-4bb5-a7e6-be51f5260405/Main','c_cpp','/myproject2/testdata/problemId_1041/t8',8,'AC',0,1760),(2590,437,'/myproject2/run/f6ffbaff-1ff2-4bb5-a7e6-be51f5260405/Main','c_cpp','/myproject2/testdata/problemId_1041/t9',9,'AC',0,1820),(2591,437,'/myproject2/run/f6ffbaff-1ff2-4bb5-a7e6-be51f5260405/Main','c_cpp','/myproject2/testdata/problemId_1041/t10',10,'AC',0,1776),(2592,438,'/myproject2/run/c330a4e1-99e5-478a-8877-b117b632b01a/Main.class','java','/myproject2/testdata/problemId_1041/t1',1,'AC',104,24588),(2593,438,'/myproject2/run/c330a4e1-99e5-478a-8877-b117b632b01a/Main.class','java','/myproject2/testdata/problemId_1041/t2',2,'AC',100,24356),(2594,438,'/myproject2/run/c330a4e1-99e5-478a-8877-b117b632b01a/Main.class','java','/myproject2/testdata/problemId_1041/t3',3,'AC',116,24388),(2595,438,'/myproject2/run/c330a4e1-99e5-478a-8877-b117b632b01a/Main.class','java','/myproject2/testdata/problemId_1041/t4',4,'AC',120,24276),(2596,438,'/myproject2/run/c330a4e1-99e5-478a-8877-b117b632b01a/Main.class','java','/myproject2/testdata/problemId_1041/t5',5,'AC',116,24436),(2597,438,'/myproject2/run/c330a4e1-99e5-478a-8877-b117b632b01a/Main.class','java','/myproject2/testdata/problemId_1041/t6',6,'AC',116,24424),(2598,438,'/myproject2/run/c330a4e1-99e5-478a-8877-b117b632b01a/Main.class','java','/myproject2/testdata/problemId_1041/t7',7,'AC',128,24396),(2599,438,'/myproject2/run/c330a4e1-99e5-478a-8877-b117b632b01a/Main.class','java','/myproject2/testdata/problemId_1041/t8',8,'AC',124,24276),(2600,438,'/myproject2/run/c330a4e1-99e5-478a-8877-b117b632b01a/Main.class','java','/myproject2/testdata/problemId_1041/t9',9,'AC',120,24280),(2601,438,'/myproject2/run/c330a4e1-99e5-478a-8877-b117b632b01a/Main.class','java','/myproject2/testdata/problemId_1041/t10',10,'AC',120,24356),(2602,439,'/myproject2/run/2cba6291-d670-468a-8d41-5cc9a15f741e/Main','c_cpp','/myproject2/testdata/problemId_1047/t1',1,'WA',0,1808),(2603,439,'/myproject2/run/2cba6291-d670-468a-8d41-5cc9a15f741e/Main','c_cpp','/myproject2/testdata/problemId_1047/t2',2,'WA',0,1804),(2604,439,'/myproject2/run/2cba6291-d670-468a-8d41-5cc9a15f741e/Main','c_cpp','/myproject2/testdata/problemId_1047/t3',3,'WA',0,1796),(2605,439,'/myproject2/run/2cba6291-d670-468a-8d41-5cc9a15f741e/Main','c_cpp','/myproject2/testdata/problemId_1047/t4',4,'WA',0,1824),(2606,439,'/myproject2/run/2cba6291-d670-468a-8d41-5cc9a15f741e/Main','c_cpp','/myproject2/testdata/problemId_1047/t5',5,'WA',0,1772),(2607,439,'/myproject2/run/2cba6291-d670-468a-8d41-5cc9a15f741e/Main','c_cpp','/myproject2/testdata/problemId_1047/t6',6,'WA',0,1808),(2608,439,'/myproject2/run/2cba6291-d670-468a-8d41-5cc9a15f741e/Main','c_cpp','/myproject2/testdata/problemId_1047/t7',7,'WA',0,1764),(2609,439,'/myproject2/run/2cba6291-d670-468a-8d41-5cc9a15f741e/Main','c_cpp','/myproject2/testdata/problemId_1047/t8',8,'WA',0,1780),(2610,439,'/myproject2/run/2cba6291-d670-468a-8d41-5cc9a15f741e/Main','c_cpp','/myproject2/testdata/problemId_1047/t9',9,'WA',0,1808),(2611,439,'/myproject2/run/2cba6291-d670-468a-8d41-5cc9a15f741e/Main','c_cpp','/myproject2/testdata/problemId_1047/t10',10,'WA',0,1772),(2612,440,'/myproject2/run/03288176-6178-4606-9cc4-d50c684ecabd/Main','c_cpp','/myproject2/testdata/problemId_1047/t1',1,'AC',0,1776),(2613,440,'/myproject2/run/03288176-6178-4606-9cc4-d50c684ecabd/Main','c_cpp','/myproject2/testdata/problemId_1047/t2',2,'AC',0,1808),(2614,440,'/myproject2/run/03288176-6178-4606-9cc4-d50c684ecabd/Main','c_cpp','/myproject2/testdata/problemId_1047/t3',3,'AC',0,1772),(2615,440,'/myproject2/run/03288176-6178-4606-9cc4-d50c684ecabd/Main','c_cpp','/myproject2/testdata/problemId_1047/t4',4,'AC',0,1764),(2616,440,'/myproject2/run/03288176-6178-4606-9cc4-d50c684ecabd/Main','c_cpp','/myproject2/testdata/problemId_1047/t5',5,'AC',0,1804),(2617,440,'/myproject2/run/03288176-6178-4606-9cc4-d50c684ecabd/Main','c_cpp','/myproject2/testdata/problemId_1047/t6',6,'AC',0,1824),(2618,440,'/myproject2/run/03288176-6178-4606-9cc4-d50c684ecabd/Main','c_cpp','/myproject2/testdata/problemId_1047/t7',7,'AC',0,1796),(2619,440,'/myproject2/run/03288176-6178-4606-9cc4-d50c684ecabd/Main','c_cpp','/myproject2/testdata/problemId_1047/t8',8,'AC',0,1824),(2620,440,'/myproject2/run/03288176-6178-4606-9cc4-d50c684ecabd/Main','c_cpp','/myproject2/testdata/problemId_1047/t9',9,'AC',0,1824),(2621,440,'/myproject2/run/03288176-6178-4606-9cc4-d50c684ecabd/Main','c_cpp','/myproject2/testdata/problemId_1047/t10',10,'AC',0,1804),(2622,441,'/myproject2/run/1cfa93e4-e58a-4dce-8f76-3b915c9b891b/Main','c_cpp','/myproject2/testdata/problemId_1041/t1',1,'AC',0,1760),(2623,441,'/myproject2/run/1cfa93e4-e58a-4dce-8f76-3b915c9b891b/Main','c_cpp','/myproject2/testdata/problemId_1041/t2',2,'AC',0,1820),(2624,441,'/myproject2/run/1cfa93e4-e58a-4dce-8f76-3b915c9b891b/Main','c_cpp','/myproject2/testdata/problemId_1041/t3',3,'AC',0,1764),(2625,441,'/myproject2/run/1cfa93e4-e58a-4dce-8f76-3b915c9b891b/Main','c_cpp','/myproject2/testdata/problemId_1041/t4',4,'AC',0,1768),(2626,441,'/myproject2/run/1cfa93e4-e58a-4dce-8f76-3b915c9b891b/Main','c_cpp','/myproject2/testdata/problemId_1041/t5',5,'AC',0,1812),(2627,441,'/myproject2/run/1cfa93e4-e58a-4dce-8f76-3b915c9b891b/Main','c_cpp','/myproject2/testdata/problemId_1041/t6',6,'AC',0,1760),(2628,441,'/myproject2/run/1cfa93e4-e58a-4dce-8f76-3b915c9b891b/Main','c_cpp','/myproject2/testdata/problemId_1041/t7',7,'AC',0,1764),(2629,441,'/myproject2/run/1cfa93e4-e58a-4dce-8f76-3b915c9b891b/Main','c_cpp','/myproject2/testdata/problemId_1041/t8',8,'AC',0,1768),(2630,441,'/myproject2/run/1cfa93e4-e58a-4dce-8f76-3b915c9b891b/Main','c_cpp','/myproject2/testdata/problemId_1041/t9',9,'AC',0,1772),(2631,441,'/myproject2/run/1cfa93e4-e58a-4dce-8f76-3b915c9b891b/Main','c_cpp','/myproject2/testdata/problemId_1041/t10',10,'AC',0,1804),(2632,442,'/myproject2/run/81b3e2be-704e-46cf-8402-694040882b9d/Main','c_cpp','/myproject2/testdata/problemId_1041/t1',1,'AC',0,1760),(2633,442,'/myproject2/run/81b3e2be-704e-46cf-8402-694040882b9d/Main','c_cpp','/myproject2/testdata/problemId_1041/t2',2,'AC',0,1764),(2634,442,'/myproject2/run/81b3e2be-704e-46cf-8402-694040882b9d/Main','c_cpp','/myproject2/testdata/problemId_1041/t3',3,'AC',0,1800),(2635,442,'/myproject2/run/81b3e2be-704e-46cf-8402-694040882b9d/Main','c_cpp','/myproject2/testdata/problemId_1041/t4',4,'AC',0,1768),(2636,442,'/myproject2/run/81b3e2be-704e-46cf-8402-694040882b9d/Main','c_cpp','/myproject2/testdata/problemId_1041/t5',5,'AC',0,1764),(2637,442,'/myproject2/run/81b3e2be-704e-46cf-8402-694040882b9d/Main','c_cpp','/myproject2/testdata/problemId_1041/t6',6,'AC',0,1776),(2638,442,'/myproject2/run/81b3e2be-704e-46cf-8402-694040882b9d/Main','c_cpp','/myproject2/testdata/problemId_1041/t7',7,'AC',0,1764),(2639,442,'/myproject2/run/81b3e2be-704e-46cf-8402-694040882b9d/Main','c_cpp','/myproject2/testdata/problemId_1041/t8',8,'AC',0,1804),(2640,442,'/myproject2/run/81b3e2be-704e-46cf-8402-694040882b9d/Main','c_cpp','/myproject2/testdata/problemId_1041/t9',9,'AC',0,1768),(2641,442,'/myproject2/run/81b3e2be-704e-46cf-8402-694040882b9d/Main','c_cpp','/myproject2/testdata/problemId_1041/t10',10,'AC',0,1800);
/*!40000 ALTER TABLE `run_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submit_code`
--

DROP TABLE IF EXISTS `submit_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=443 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submit_code`
--

LOCK TABLES `submit_code` WRITE;
/*!40000 ALTER TABLE `submit_code` DISABLE KEYS */;
INSERT INTO `submit_code` VALUES (386,1,1041,'2019-11-08 02:27:00','/myproject2/compile/493fe08e-9e4f-4eb5-b1b1-7a17924bb11b/Main.cpp','c_cpp',20,10,10,'gob','AC',NULL),(387,1,1044,'2019-11-21 02:55:16','/myproject2/compile/359d5778-92c1-4291-a42c-4688ef71ad54/Main.cpp','c_cpp',20,10,10,'cobalt','WA',NULL),(388,1,1044,'2019-11-21 03:02:49','/myproject2/compile/f1a54124-c0dc-44cb-aef9-b8bd67e1d965/Main.cpp','c_cpp',20,10,10,'cobalt','WA',NULL),(389,1,1044,'2019-11-21 04:14:26','/myproject2/compile/22d72924-ee4b-4e91-84e9-14c6d23ebf01/Main.cpp','c_cpp',20,10,10,'cobalt','WA',NULL),(390,1,1044,'2019-11-21 04:20:39','/myproject2/compile/204ff303-d964-402b-9e1c-053a45da674b/Main.cpp','c_cpp',20,10,10,'cobalt','AC',NULL),(391,1,1045,'2019-11-21 09:45:47','/myproject2/compile/7dbbdc6c-83f3-44e5-8aaf-9ca486ab7115/Main.cpp','c_cpp',20,0,10,'xcode','running',NULL),(392,1,1045,'2019-11-21 09:53:59','/myproject2/compile/9be597d1-1131-4bf7-9720-adc91775fea2/Main.cpp','c_cpp',20,0,10,'xcode','running',NULL),(393,1,1045,'2019-11-21 09:58:20','/myproject2/compile/b3d5f0e1-8ba2-4a4d-9eb4-fc3178ce68c1/Main.cpp','c_cpp',20,0,10,'xcode','running',NULL),(394,1,1045,'2019-11-21 10:04:09','/myproject2/compile/3beb0e06-42eb-4b2b-bd23-80f13ed49391/Main.cpp','c_cpp',20,10,10,'xcode','WA',NULL),(395,1,1045,'2019-11-23 08:41:10','/myproject2/compile/5ce8ffdf-f31d-4f30-a1fa-40deb890eabf/Main.cpp','c_cpp',20,10,10,'xcode','AC',NULL),(396,1,1046,'2019-11-23 10:16:00','/myproject2/compile/6f895377-0624-499d-a296-4c4e40d02b5c/Main.cpp','c_cpp',20,10,10,'chrome','WA',NULL),(397,1,1041,'2019-11-23 11:04:18','/myproject2/compile/8343213f-885b-466a-920d-a5e2c6535521/Main.cpp','c_cpp',20,10,10,'gob','AC',NULL),(398,0,1041,'2019-11-23 12:09:30','/myproject2/compile/a2ae0848-c5c1-42d0-a845-9b779f9f8884/Main.cpp','c_cpp',20,0,0,'twilight','compile fail',''),(399,0,1041,'2019-11-23 13:22:29','/myproject2/compile/c439dede-38cc-49b2-a470-3ad7a2f45b9a/Main.cpp','c_cpp',20,0,0,'crimson_editor','compile fail',NULL),(400,0,1041,'2019-11-23 13:26:46','/myproject2/compile/f931fcaf-2d55-46d1-9b62-a5fa67264ee5/Main.cpp','c_cpp',20,0,0,'xcode','compile fail',NULL),(401,0,1041,'2019-11-23 13:27:59','/myproject2/compile/037e7a57-4533-4680-8c17-299439da6b74/Main.cpp','c_cpp',20,0,0,'twilight','compile fail',NULL),(402,0,1041,'2019-11-23 13:28:06','/myproject2/compile/05cabb7a-7bd0-4083-a3bf-ccc6fe714558/Main.cpp','c_cpp',20,0,0,'xcode','compile fail',NULL),(403,0,1041,'2019-11-23 13:55:56','/myproject2/compile/1bf87b71-3dd4-4f47-b2d4-919aa92bb279/Main.cpp','c_cpp',20,0,0,'eclipse','compile fail',NULL),(404,0,1041,'2019-11-23 14:04:55','/myproject2/compile/2e9db3de-ab48-4181-91b9-873bb3e357c3/Main.cpp','c_cpp',20,9,10,'crimson_editor','running',NULL),(405,0,1041,'2019-11-23 14:05:39','/myproject2/compile/8d67d4ea-f8db-4329-acbc-10303f8587b5/Main.cpp','c_cpp',20,10,10,'gob','AC',NULL),(406,0,1041,'2019-11-23 14:17:43','/myproject2/compile/d54a0e4f-279b-4831-8680-6440f4e77de5/Main.cpp','c_cpp',20,10,10,'gob','AC',NULL),(407,0,1041,'2019-11-23 14:58:04','/myproject2/compile/c5f3f7bd-8a1f-4345-81bc-8c82c0dfc249/Main.cpp','c_cpp',20,0,0,'chrome','compile fail',NULL),(408,0,1041,'2019-11-23 14:59:49','/myproject2/compile/cdae53ee-0de6-4863-a89d-e7692b263eaf/Main.cpp','c_cpp',20,0,0,'chrome','compile fail',NULL),(409,0,1041,'2019-11-23 15:03:17','/myproject2/compile/ab741c23-7d97-4d56-aa5d-b0ac9847fd7e/Main.cpp','c_cpp',20,0,0,'crimson_editor','compile fail',NULL),(410,0,1041,'2019-11-23 15:08:57','/myproject2/compile/8206c07f-05bb-40b3-ad3d-ae1ccccbfda0/Main.cpp','c_cpp',20,0,0,'xcode','compile fail',NULL),(411,0,1041,'2019-11-23 15:21:31','/myproject2/compile/895e6f9d-105e-4a94-8a7c-56de0092c637/Main.cpp','c_cpp',20,10,10,'eclipse','AC',NULL),(412,0,1041,'2019-11-23 15:28:31','/myproject2/compile/676ea939-1aa8-4bff-826c-04e60037d7de/Main.cpp','c_cpp',20,10,10,'dawn','AC',NULL),(413,0,1041,'2019-11-23 15:28:58','/myproject2/compile/dda589e3-a2bb-4c55-8b83-4f1d38fea0b6/Main.cpp','c_cpp',20,10,10,'gob','AC',NULL),(414,0,1041,'2019-11-23 15:42:36','/myproject2/compile/57a685a9-b216-4921-a723-e3c42968dede/Main.cpp','c_cpp',20,0,0,'chrome','compile fail',NULL),(415,0,1041,'2019-11-23 15:42:58','/myproject2/compile/1a5fa982-ef2d-48c6-90d3-6a0994be1b9b/Main.cpp','c_cpp',20,0,0,'twilight','compile fail',NULL),(416,0,1041,'2019-11-23 15:48:34','/myproject2/compile/3764be40-4549-4151-b99e-cb878625aa4f/Main.cpp','c_cpp',20,0,0,'eclipse','compile fail',NULL),(417,0,1041,'2019-11-23 15:48:55','/myproject2/compile/87f26064-cf0b-476a-98ef-eaf16d77f3d5/Main.cpp','c_cpp',20,0,0,'eclipse','compile fail',NULL),(418,0,1041,'2019-11-23 15:52:55','/myproject2/compile/416c7aa0-ade9-4e50-98f8-4067f41f62f8/Main.cpp','c_cpp',20,0,0,'twilight','compile fail',NULL),(419,0,1041,'2019-11-23 15:56:33','/myproject2/compile/39b219da-8c02-4aa2-9c2d-0e73710bee0f/Main.cpp','c_cpp',20,10,10,'chrome','AC',NULL),(420,0,1041,'2019-11-23 16:16:23','/myproject2/compile/28b0571e-f7fc-4822-9fcf-0934aa839746/Main.cpp','c_cpp',20,10,10,'dawn','AC',NULL),(421,0,1041,'2019-11-23 16:20:59','/myproject2/compile/1c19deb0-6872-4ad2-9095-66c2dda71865/Main.cpp','c_cpp',20,0,0,'eclipse','compile fail',NULL),(422,0,1041,'2019-11-23 16:24:01','/myproject2/compile/3597ee32-6fd6-4751-acb0-33abc6092103/Main.cpp','c_cpp',20,10,10,'cobalt','AC',NULL),(423,1,1041,'2019-11-23 16:27:44','/myproject2/compile/69aa29e7-7643-4260-976a-3e174410c1a0/Main.cpp','c_cpp',20,10,10,'eclipse','AC',NULL),(424,1,1047,'2019-11-26 08:40:00','/myproject2/compile/4a044131-9aca-4f2d-9941-93677cfcb1ea/Main.cpp','c_cpp',20,0,0,'crimson_editor','compiling',NULL),(425,1,1047,'2019-11-26 08:40:58','/myproject2/compile/665b2674-f74b-4983-80fb-3db44b3d8bee/Main.cpp','c_cpp',20,3,10,'crimson_editor','running',NULL),(426,1,1047,'2019-11-26 08:41:31','/myproject2/compile/c0c21293-a5ea-4463-9798-58c8ebabb40b/Main.cpp','c_cpp',20,0,0,'crimson_editor','compiling',NULL),(427,1,1041,'2019-11-26 08:47:29','/myproject2/compile/a1297770-1f2d-4e1f-bb90-bdf08653fd2d/Main.cpp','c_cpp',20,10,10,'eclipse','AC',NULL),(428,1,1047,'2019-11-26 11:56:01','/myproject2/compile/af871b4e-61d1-4d89-868a-96ec352db526/Main.cpp','c_cpp',20,0,0,'crimson_editor','compile fail',NULL),(429,1,1041,'2019-11-26 11:56:09','/myproject2/compile/343f9c21-ae77-4e7a-bad3-9bec26b5a17b/Main.cpp','c_cpp',20,0,0,'eclipse','compile fail',NULL),(430,1,1041,'2019-11-26 12:00:06','/myproject2/compile/8ae8a5ed-e20e-4e32-b7f9-b40c7256a08a/Main.cpp','c_cpp',20,0,0,'eclipse','compile fail',NULL),(431,1,1041,'2019-11-26 12:09:33','/myproject2/compile/ac733b79-68a9-4dea-ab44-26c49cedc02b/Main.cpp','c_cpp',20,10,10,'eclipse','AC',NULL),(432,1,1047,'2019-11-26 12:09:49','/myproject2/compile/07d680ca-8317-47ad-b642-bcdb769b23b9/Main.cpp','c_cpp',20,10,10,'crimson_editor','AC',NULL),(433,0,1041,'2019-11-26 12:13:40','/myproject2/compile/8dbc8afd-a021-4aa1-89ef-d542d18f5437/Main.cpp','c_cpp',20,0,0,'chrome','compile fail',NULL),(434,0,1041,'2019-11-26 12:13:58','/myproject2/compile/9929aefc-40e3-4313-8840-358424126eaa/Main.cpp','c_cpp',20,0,0,'eclipse','compile fail',NULL),(435,0,1041,'2019-11-26 16:34:02','/myproject2/compile/003f9554-1524-406c-bb71-b8ceac993894/Main.cpp','c_cpp',20,10,10,'chaos','AC',NULL),(436,0,1047,'2019-11-26 16:34:18','/myproject2/compile/cb0f97f5-56b3-4025-8e4b-2ce5cc265eaf/Main.cpp','c_cpp',20,10,10,'chaos','WA',NULL),(437,0,1041,'2019-11-26 16:37:41','/myproject2/compile/1b1b4755-ba48-4ff6-9664-ab6201af80dd/Main.cpp','c_cpp',20,10,10,'chaos','AC',NULL),(438,0,1041,'2019-11-26 16:38:04','/myproject2/compile/84232407-4c62-4db4-a510-f831f10a57be/Main.java','java',20,10,10,'crimson_editor','AC',NULL),(439,0,1047,'2019-11-26 16:39:25','/myproject2/compile/a4367791-b7f4-4cb9-8497-ac28a61beac3/Main.cpp','c_cpp',20,10,10,'github','WA',NULL),(440,0,1047,'2019-11-26 16:40:03','/myproject2/compile/6efb099c-fe23-4a0e-8b9e-42e54d655675/Main.cpp','c_cpp',20,10,10,'gob','AC',NULL),(441,0,1041,'2019-11-27 01:14:29','/myproject2/compile/a0efca5a-4a8f-481b-9f46-fe50fb3f9cfa/Main.cpp','c_cpp',20,10,10,'crimson_editor','AC',NULL),(442,0,1041,'2019-11-27 01:18:04','/myproject2/compile/7a382f1d-04bc-4488-8312-8b4de86ff349/Main.cpp','c_cpp',20,10,10,'chrome','AC',NULL);
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `table_count` (
  `table_count_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `table_name` varchar(20) DEFAULT NULL,
  `table_number` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`table_count_id`),
  UNIQUE KEY `count_UN` (`table_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='对各种表的总记录条数进行计数';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_count`
--

LOCK TABLES `table_count` WRITE;
/*!40000 ALTER TABLE `table_count` DISABLE KEYS */;
INSERT INTO `table_count` VALUES (1,'submit_code',59),(2,'problem',5),(3,'user',5);
/*!40000 ALTER TABLE `table_count` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_data`
--

DROP TABLE IF EXISTS `test_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_data` (
  `test_data_id` int(11) NOT NULL AUTO_INCREMENT,
  `problem_id` int(10) unsigned NOT NULL,
  `test_data_order_number` tinyint(3) unsigned DEFAULT NULL,
  `data_path` char(100) DEFAULT NULL,
  PRIMARY KEY (`test_data_id`),
  KEY `test_data_FK` (`problem_id`),
  CONSTRAINT `test_data_FK` FOREIGN KEY (`problem_id`) REFERENCES `problem` (`problem_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_data`
--

LOCK TABLES `test_data` WRITE;
/*!40000 ALTER TABLE `test_data` DISABLE KEYS */;
INSERT INTO `test_data` VALUES (85,1041,1,'/myproject2/testdata/problemId_1041/t1'),(86,1041,2,'/myproject2/testdata/problemId_1041/t2'),(87,1041,3,'/myproject2/testdata/problemId_1041/t3'),(88,1041,4,'/myproject2/testdata/problemId_1041/t4'),(89,1041,5,'/myproject2/testdata/problemId_1041/t5'),(90,1041,6,'/myproject2/testdata/problemId_1041/t6'),(91,1041,7,'/myproject2/testdata/problemId_1041/t7'),(92,1041,8,'/myproject2/testdata/problemId_1041/t8'),(93,1041,9,'/myproject2/testdata/problemId_1041/t9'),(94,1041,10,'/myproject2/testdata/problemId_1041/t10'),(105,1044,1,'/myproject2/testdata/problemId_1044/t1'),(106,1044,2,'/myproject2/testdata/problemId_1044/t2'),(107,1044,3,'/myproject2/testdata/problemId_1044/t3'),(108,1044,4,'/myproject2/testdata/problemId_1044/t4'),(109,1044,5,'/myproject2/testdata/problemId_1044/t5'),(110,1044,6,'/myproject2/testdata/problemId_1044/t6'),(111,1044,7,'/myproject2/testdata/problemId_1044/t7'),(112,1044,8,'/myproject2/testdata/problemId_1044/t8'),(113,1044,9,'/myproject2/testdata/problemId_1044/t9'),(114,1044,10,'/myproject2/testdata/problemId_1044/t10'),(136,1045,1,'/myproject2/testdata/problemId_1045/t1'),(137,1045,2,'/myproject2/testdata/problemId_1045/t2'),(138,1045,3,'/myproject2/testdata/problemId_1045/t3'),(139,1045,4,'/myproject2/testdata/problemId_1045/t4'),(140,1045,5,'/myproject2/testdata/problemId_1045/t5'),(141,1045,6,'/myproject2/testdata/problemId_1045/t6'),(142,1045,7,'/myproject2/testdata/problemId_1045/t7'),(143,1045,8,'/myproject2/testdata/problemId_1045/t8'),(144,1045,9,'/myproject2/testdata/problemId_1045/t9'),(145,1045,10,'/myproject2/testdata/problemId_1045/t10'),(147,1046,1,'/myproject2/testdata/problemId_1046/t1'),(148,1046,2,'/myproject2/testdata/problemId_1046/t2'),(149,1046,3,'/myproject2/testdata/problemId_1046/t3'),(150,1046,4,'/myproject2/testdata/problemId_1046/t4'),(151,1046,5,'/myproject2/testdata/problemId_1046/t5'),(152,1046,6,'/myproject2/testdata/problemId_1046/t6'),(153,1046,7,'/myproject2/testdata/problemId_1046/t7'),(154,1046,8,'/myproject2/testdata/problemId_1046/t8'),(155,1046,9,'/myproject2/testdata/problemId_1046/t9'),(156,1046,10,'/myproject2/testdata/problemId_1046/t10'),(158,1047,1,'/myproject2/testdata/problemId_1047/t1'),(159,1047,2,'/myproject2/testdata/problemId_1047/t2'),(160,1047,3,'/myproject2/testdata/problemId_1047/t3'),(161,1047,4,'/myproject2/testdata/problemId_1047/t4'),(162,1047,5,'/myproject2/testdata/problemId_1047/t5'),(163,1047,6,'/myproject2/testdata/problemId_1047/t6'),(164,1047,7,'/myproject2/testdata/problemId_1047/t7'),(165,1047,8,'/myproject2/testdata/problemId_1047/t8'),(166,1047,9,'/myproject2/testdata/problemId_1047/t9'),(167,1047,10,'/myproject2/testdata/problemId_1047/t10');
/*!40000 ALTER TABLE `test_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_limit`
--

DROP TABLE IF EXISTS `time_limit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_limit`
--

LOCK TABLES `time_limit` WRITE;
/*!40000 ALTER TABLE `time_limit` DISABLE KEYS */;
INSERT INTO `time_limit` VALUES (159,1041,1,1,1,1,1),(160,1044,1,1,1,1,1),(161,1045,1,1,2,1,2),(162,1046,1,1,1,1,1),(163,1047,1,1,1,1,1);
/*!40000 ALTER TABLE `time_limit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
INSERT INTO `user` VALUES (1,'root','122130826@qq.com','78946'),(2,'root2','1@qq.com','78946'),(3,'root3','2@qq.com','78946'),(4,'user','user@qq.com','123456'),(56,NULL,'123@qq.com','123');
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
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-19 21:35:30
