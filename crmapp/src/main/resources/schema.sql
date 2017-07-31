DROP DATABASE IF EXISTS `crmapp`;
CREATE DATABASE IF NOT EXISTS `crmapp`;
USE `crmapp`;

DROP TABLE IF EXISTS `contractor`;
CREATE TABLE IF NOT EXISTS `contractor` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `alias` varchar(150) DEFAULT NULL,
  `title` varchar(150) DEFAULT NULL,
  `edrpou` varchar(15) DEFAULT NULL,
  `inn` varchar(15) DEFAULT NULL,
  `vat_certificate` varchar(15) DEFAULT NULL,
  `optlock` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `agreement`;
CREATE TABLE IF NOT EXISTS `agreement` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `contractor_id` int(11) DEFAULT NULL,
  `number` varchar(150) DEFAULT NULL,
  `date_start` date DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `optlock` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `document`;
CREATE TABLE IF NOT EXISTS `document` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `agreement_id` int(11) DEFAULT NULL,
  `doc_type_id` int(11) DEFAULT NULL,
  `number` varchar(30) DEFAULT NULL,
  `dated` date DEFAULT NULL,
  `amount` decimal(10,2) NOT NULL DEFAULT '0',
  `payment_date` date DEFAULT NULL,
  `doc_status_id` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `optlock` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `document_type`;
CREATE TABLE IF NOT EXISTS `document_type` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(150) DEFAULT NULL,
  `optlock` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `document_status`;
CREATE TABLE IF NOT EXISTS `document_status` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `status` varchar(150) DEFAULT NULL,
  `optlock` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `agreement`
  ADD CONSTRAINT `FKf80mc7qmk5p905f2hmfycw1e8` 
  FOREIGN KEY(`contractor_id`) 
  REFERENCES `contractor` (`id`);
  
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