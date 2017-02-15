CREATE DATABASE  IF NOT EXISTS codersdb;
USE codersdb;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS role;
CREATE TABLE role (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO codersdb.role (role_name) VALUES ('ROLE_SUPER_ADMIN');
INSERT INTO codersdb.role (role_name) VALUES ('ROLE_ADMIN');
INSERT INTO codersdb.role (role_name) VALUES ('ROLE_BLOGGER');


--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `mod_by` varchar(45) DEFAULT NULL,
  `mod_date` datetime DEFAULT NULL,
  `img_file_name` varchar(45) DEFAULT NULL,
  `img_file_size` bigint(20) DEFAULT NULL,
  `img_file_type` varchar(45) DEFAULT NULL,
  `img_file_ext` varchar(45) DEFAULT NULL,
  `verification_key` varchar(45) DEFAULT NULL,
  `verified` tinyint(1) DEFAULT '0',
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO codersdb.user (name,user_name,password,created_by,created_date,mod_by,mod_date,verified)
VALUES('Coderszone admin','admin@coderszone.in','admin1q2w3e','system',now(),'system',now(),1);


--
-- Table structure for table `user_role_map`
--

DROP TABLE IF EXISTS `user_role_map`;
CREATE TABLE `user_role_map` (
  `user_id` varchar(45) NOT NULL,
  `role_id` varchar(45) NOT NULL,
  `mod_by` varchar(45) DEFAULT NULL,
  `mod_date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO codersdb.user_role_map (user_id, role_id,mod_by,mod_date) VALUES ('admin@coderszone.in', 'ROLE_SUPERADMIN','system',now());
INSERT INTO codersdb.user_role_map (user_id, role_id,mod_by,mod_date) VALUES ('admin@coderszone.in', 'ROLE_ADMIN','system',now());
INSERT INTO codersdb.user_role_map (user_id, role_id,mod_by,mod_date) VALUES ('admin@coderszone.in', 'ROLE_BLOGGER','system',now());



--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keywords` varchar(200) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `content` longtext,
  `created_by` varchar(45) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `mod_by` varchar(45) DEFAULT NULL,
  `mod_date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO codersdb.tags (name) VALUES ('C++');
INSERT INTO codersdb.tags (name) VALUES ('JAVA');
INSERT INTO codersdb.tags (name) VALUES ('JAVASCRIPT');
INSERT INTO codersdb.tags (name) VALUES ('JQUERY');
INSERT INTO codersdb.tags (name) VALUES ('AWS');
INSERT INTO codersdb.tags (name) VALUES ('PYTHON');
INSERT INTO codersdb.tags (name) VALUES ('LUA');
INSERT INTO codersdb.tags (name) VALUES ('LINUX/UNIX');
INSERT INTO codersdb.tags (name) VALUES ('SPRING');
INSERT INTO codersdb.tags (name) VALUES ('C#');
INSERT INTO codersdb.tags (name) VALUES ('ALGORITHM');
INSERT INTO codersdb.tags (name) VALUES ('MYSQL');
INSERT INTO codersdb.tags (name) VALUES ('NOSQL');
INSERT INTO codersdb.tags (name) VALUES ('BIGDATA');
INSERT INTO codersdb.tags (name) VALUES ('NETWORK');
INSERT INTO codersdb.tags (name) VALUES ('HOWTODO');
INSERT INTO codersdb.tags (name) VALUES ('OTHERS');
--
-- Table structure for table `blog_tags_map`
--

DROP TABLE IF EXISTS `blog_tags_map`;
CREATE TABLE `blog_tags_map` (
  `blog_id` int(11) NOT NULL,
  `tag_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`blog_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_time` datetime DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `content` text,
  `blog_id` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

