CREATE DATABASE  IF NOT EXISTS codersdb;
USE codersdb;

--
-- Table structure for table 'role'
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
-- Table structure for table 'user'
--

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email_id` varchar(45) DEFAULT NULL,
  `push_id` varchar(255) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `mod_by` varchar(45) DEFAULT NULL,
  `mod_date` datetime DEFAULT NULL,
  `img_file_name` varchar(45) DEFAULT NULL,
  `img_file_size` bigint(20) DEFAULT NULL,
  `img_file_type` varchar(45) DEFAULT NULL,
  `img_file_ext` varchar(45) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO codersdb.user (first_name,last_name,email_id,user_name,password,created_by,created_date,mod_by,mod_date)
VALUES('system','system','system@coderszone.in','system','system','system',now(),'system',now());

INSERT INTO codersdb.user (first_name,last_name,email_id,user_name,password,created_by,created_date,mod_by,mod_date)
VALUES('admin','admin','admin@coderszone.in','admin','admin','system',now(),'system',now());

--
-- Table structure for table 'user_role_map'
--

DROP TABLE IF EXISTS user_role_map;
CREATE TABLE user_role_map (
  `user_id` varchar(45) NOT NULL,
  `role_id` varchar(45) NOT NULL,
  `mod_by` varchar(45) DEFAULT NULL,
  `mod_date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO codersdb.user_role_map (user_id, role_id,mod_by,mod_date) VALUES ('system', 'ROLE_SUPERADMIN','system',now());
INSERT INTO codersdb.user_role_map (user_id, role_id,mod_by,mod_date) VALUES ('system', 'ROLE_ADMIN','system',now());
INSERT INTO codersdb.user_role_map (user_id, role_id,mod_by,mod_date) VALUES ('system', 'ROLE_BLOGGER','system',now());
INSERT INTO codersdb.user_role_map (user_id, role_id,mod_by,mod_date) VALUES ('admin', 'ROLE_ADMIN','system',now());
INSERT INTO codersdb.user_role_map (user_id, role_id,mod_by,mod_date) VALUES ('admin', 'ROLE_BLOGGER','system',now());



CREATE TABLE `codersdb`.`blog` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL,
  `desc` VARCHAR(500) NULL,
  `content` TEXT NULL,
  `created_by` VARCHAR(45) NULL,
  `created_date` DATETIME NULL,
  `mod_by` VARCHAR(45) NULL,
  `mod_date` DATETIME NULL,
  `deleted` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`));
