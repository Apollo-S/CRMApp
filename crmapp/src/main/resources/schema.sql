DROP DATABASE IF EXISTS `crmapp`;
CREATE DATABASE IF NOT EXISTS `crmapp`;
USE `crmapp`;

DROP TABLE IF EXISTS `contractor`;
CREATE TABLE IF NOT EXISTS `contractor` (
  `id` int(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `alias` varchar(150) DEFAULT NULL,
  `title` varchar(150) DEFAULT NULL,
  `edrpou` varchar(15) DEFAULT NULL,
  `inn` varchar(15) DEFAULT NULL,
  `vat_certificate` varchar(15) DEFAULT NULL,
  `optlock` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `agreement`;
CREATE TABLE IF NOT EXISTS `agreement` (
  `id` int(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `contractor_id` int(11) UNSIGNED DEFAULT NULL,
  `number` varchar(150) DEFAULT NULL,
  `date_start` date DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `optlock` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `agreement`
  ADD CONSTRAINT `FKf80mc7qmk5p905f2hmfycw1e8` FOREIGN KEY(`contractor_id`) REFERENCES `contractor` (`id`);

DROP TABLE IF EXISTS `document`;
CREATE TABLE IF NOT EXISTS `document` (
  `id` int(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `agreement_id` int(11) UNSIGNED DEFAULT NULL,
  `doc_type_id` int(11) UNSIGNED DEFAULT NULL,
  `number` varchar(30) DEFAULT NULL,
  `dated` date DEFAULT NULL,
  `amount` decimal(10,2) NOT NULL DEFAULT '0',
  `payment_date` date DEFAULT NULL,
  `doc_status_id` int(11) UNSIGNED DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `optlock` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `document_type`;
CREATE TABLE IF NOT EXISTS `document_type` (
  `id` int(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(150) DEFAULT NULL,
  `optlock` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `document_status`;
CREATE TABLE IF NOT EXISTS `document_status` (
  `id` int(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `status` varchar(150) DEFAULT NULL,
  `optlock` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
	`id` INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`first_name` VARCHAR(50) NULL DEFAULT NULL,
	`last_name` VARCHAR(50) NULL DEFAULT NULL,
	`username` VARCHAR(25) NULL DEFAULT NULL,
	`password` VARCHAR(25) NULL DEFAULT NULL,
	`email` VARCHAR(50) NULL DEFAULT NULL,
	`role` VARCHAR(25) NULL DEFAULT NULL,
	`optlock` INT(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `acl_sid`;
CREATE TABLE IF NOT EXISTS `acl_sid` (
	`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`principal` BOOLEAN NOT NULL,
	`sid` VARCHAR(100) NOT NULL,
	UNIQUE KEY `unique_acl_sid` (`sid`, `principal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `acl_class`;
CREATE TABLE IF NOT EXISTS `acl_class` (
	`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`class` VARCHAR(100) NOT NULL,
	UNIQUE KEY `uk_acl_class` (`class`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `acl_object_identity`;
CREATE TABLE IF NOT EXISTS `acl_object_identity` (
	`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`object_id_class` BIGINT UNSIGNED NOT NULL,
	`object_id_identity` BIGINT NOT NULL,
	`parent_object` BIGINT UNSIGNED,
	`owner_sid` BIGINT UNSIGNED,
	`entries_inheriting` BOOLEAN NOT NULL,
	UNIQUE KEY `uk_acl_object_identity` (`object_id_class`, `object_id_identity`),
	CONSTRAINT `fk_acl_object_identity_parent` FOREIGN KEY (`parent_object`) REFERENCES `acl_object_identity` (`id`),
	CONSTRAINT `fk_acl_object_identity_class` FOREIGN KEY (`object_id_class`) REFERENCES `acl_class` (`id`),
	CONSTRAINT `fk_acl_object_identity_owner` FOREIGN KEY (`owner_sid`) REFERENCES `acl_sid` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `acl_entry`;
CREATE TABLE IF NOT EXISTS `acl_entry` (
	`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`acl_object_identity` BIGINT UNSIGNED NOT NULL,
	`ace_order` INTEGER NOT NULL,
	`sid` BIGINT UNSIGNED NOT NULL,
	`mask` INTEGER UNSIGNED NOT NULL,
	`granting` BOOLEAN NOT NULL,
	`audit_success` BOOLEAN NOT NULL,
	`audit_failure` BOOLEAN NOT NULL,
	UNIQUE KEY `unique_acl_entry` (`acl_object_identity`, `ace_order`),
	CONSTRAINT `fk_acl_entry_object` FOREIGN KEY (`acl_object_identity`) REFERENCES `acl_object_identity` (`id`),
	CONSTRAINT `fk_acl_entry_acl` FOREIGN KEY (`sid`) REFERENCES `acl_sid` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `contractor` (`alias`, `title`, `edrpou`, `inn`, `vat_certificate`, `optlock`)
VALUES 
  ('kyivstar', 'PRAT "KYIVCTAR"', '00987654', '0098765321', '345432678', 0),
  ('farlep', 'PRAT "FARLEP-INVEST"', '00678906', '00678905412', '087654322', 0);

INSERT INTO `agreement` (`contractor_id`, `number`, `date_start`, `comment`, `optlock`)
VALUES 
  ('1', '20170701/45/76', '2017-07-01', '', 0),
  ('2', 'FLP-2015/3', '2015-03-01', 'Договор на ТП 2015 год', 0);
  
INSERT INTO `document_type` (`title`, `optlock`)
VALUES 
  ('Акт выполненных работ', 0),
  ('Счет-фактура', 0),
  ('Доп. соглашение', 0);
  
INSERT INTO `document_status` (`status`, `optlock`)
VALUES 
  ('Передан', 0),
  ('Подписан', 0),
  ('Оплачен', 0);
  
INSERT INTO `document` (`agreement_id`, `doc_type_id`, `number`, `dated`, `amount`, `payment_date`, `doc_status_id`, `comment`, `optlock`)
VALUES 
  ('1', '2', '18', '2017-06-30', 20000.0, null, '1', '', 0),
  ('2', '3', '3', '2017-07-01', 500000.0, '2017-07-01', '2', 'На доп. услуги за 3 квартал', 0),
  ('1', '1', '64', '2017-06-30', 20000.0, '2017-07-10', '3', '', 0);
  
INSERT INTO `acl_sid` (`principal`, `sid`)
VALUES 
  (true, '');