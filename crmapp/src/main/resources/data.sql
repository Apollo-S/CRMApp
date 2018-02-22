--CREATE DATABASE IF NOT EXISTS `crmapp` COLLATE 'utf8_general_ci';
--SET GLOBAL max_allowed_packet = 1024*1024*50;
USE `crmapp`;

SET FOREIGN_KEY_CHECKS=0;

TRUNCATE `category`;
INSERT INTO `category` (`id`, `label`, `icon`, `router_link`, `comment`, `optlock`)
VALUES 
  (1, 'Клиенты', 'fa-users', '/clients', '', 0),
  (2, 'Договорные отношения', 'fa-file-o', '/agreements', '', 0),
  (3, 'Документооборот', 'fa-file-o', '/documents', '', 0),
  (4, 'Сотрудники', 'fa-users', '/employees', '', 0),
  (5, 'Отпуски', 'fa-file-o', '/vacations', '', 0),
  (6, 'Больничные листы', 'fa-file-o', '/sicklists', '', 0),
  (7, 'Исходящий номер', 'fa-file-o', '/outputs', '', 0);

TRUNCATE `client`;
INSERT INTO `client` (`id`, `alias`, `title`, `edrpou`, `inn`, `vat_certificate`, `optlock`)
VALUES 
  (1, 'kyivstar', 'ПРАТ "КИЇВСТАР"', '00987654', '0098765321', '345432678', 0),
  (2, 'farlep', 'ПРАТ "ФАРЛЕП-ІНВЕСТ"', '00678906', '00678905412', '087654322', 0),
  (3, 'ukrtelecom', 'ПАТ "УКРТЕЛЕКОМ"', '00312156', '00312156412', '085987432', 0),
  (4, 'pride-group', 'ТОВ "ПРАЙД-ГРУП"', '00352359', '', '', 0),
  (5, '2daytelecom', 'ТОО "2ДЭЙ ТЕЛЕКОМ"', '1111111111', '', '', 0),
  (6, 'golden-tel', 'ТОВ "ГОЛДЕН ТЕЛЕКОМ"', '19028202', '', '', 0);
  
TRUNCATE `client_address`;
INSERT INTO `client_address` (`id`, `client_id`, `presentation`, `date_start`, `optlock`)
VALUES 
  (1, 1, 'Україна, 03150, м.Київ, вул. Горького, буд. 172, офіс 1421', '2012-01-01', 0),
  (2, 2, 'Україна, 01210, м. Киев, вул. Крещатик, 38', '2012-01-01', 0),
  (3, 3, 'Україна, 00023, м. Киев, бульв. Т. Шевченко, 18', '2012-01-01', 0);
  
TRUNCATE `client_director`;
INSERT INTO `client_director` (`id`, `client_id`, `post_id`, `full_name`, `short_name`, `date_start`, `optlock`)
VALUES
	(1, 1, 1, 'Степанюк Алла Борисівна', 'Степанюк А.Б.', '2013-01-01', 0),
	(2, 2, 1, 'Слєпаков Семен Валерійович', 'Слєпаков С.В.', '2013-01-01', 0),
	(3, 3, 1, 'Махно Нестор Петрович', 'Махно Н.П.', '2013-01-01', 0);
	
TRUNCATE `client_account`;
INSERT INTO `client_account` (`id`, `client_id`, `presentation`, `date_start`, `optlock`)
VALUES
	(1, 1, '26007017100038 в АТ "Піреус Банк МКБ", МФО 300658', '2013-01-01', 0),
	(2, 2, '26007247100756 в АТ "ПРИВАТБАНК", МФО 320699', '2013-01-01', 0);
	
TRUNCATE `post`;
INSERT INTO `post` (`id`, `title`, `optlock`)
VALUES
	(1, 'Генеральный директор', 0),
	(2, 'Java Developer', 0),
	(3, 'Администратор', 0),
	(4, 'Senior Software Developer', 0),
	(5, 'Software Developer', 0);
	
TRUNCATE `person`;
INSERT INTO `person` (`id`, `surname`, `firstname`, `lastname`, `short_name`, `inn`, `birth_date`, `optlock`)
VALUES
	(1, 'Иванов', 'Александр', 'Владимирович', 'Иванов А.В.', '2344566541', '1972-03-24', 0),
	(2, 'Петров', 'Иван', 'Федорович', 'Петров И.Ф.', '3045679871', '1984-06-15', 0),
	(3, 'Сидоров', 'Петр', 'Валерьевич', 'Сидоров П.В.', '2874526548', '1978-09-20', 0),
	(4, 'Сидоренко', 'Федор', 'Степанович', 'Сидоренко Ф.С.', '2674826643', '1960-09-30', 0);
	
TRUNCATE `employee`;
INSERT INTO `employee` (`id`, `person_id`, `is_entrepreneur`, `hire_date`, `fired_date`, `post_id`, `optlock`)
VALUES
	(1, 1, b'0', '2012-01-01', null, 5, 0),
	(2, 2, b'1', '2013-06-01', null, 5, 0),
	(3, 3, b'0', '2012-01-01', null, 5, 0),
	(4, 4, b'0', '2012-01-01', null, 4, 0);

TRUNCATE `employee_address`;
INSERT INTO `employee_address` (`id`, `employee_id`, `presentation`, `date_start`, `optlock`)
VALUES 
  (1, 1, 'Україна, 04205, м.Київ, пр-т Оболонський, буд. 23, кв. 34', '2012-01-01', 0),
  (2, 2, 'Україна, 01210, м. Киев, вул. Крещатик, 12', '2012-01-01', 0),
  (3, 3, 'Україна, 00023, м. Киев, бульв. Т. Шевченко, 34/1', '2012-01-01', 0);

TRUNCATE `employee_account`;
INSERT INTO `employee_account` (`id`, `employee_id`, `presentation`, `date_start`, `optlock`)
VALUES
	(1, 1, '26007017100038 в АТ "Піреус Банк МКБ", МФО 300658', '2013-01-01', 0),
	(2, 2, '26007247100756 в АТ "ПРИВАТБАНК", МФО 320699', '2013-01-01', 0),
	(3, 3, '26007547230756 в АТ "ПРИВАТБАНК", МФО 320699', '2013-01-01', 0);

TRUNCATE `vacation`;
INSERT INTO `vacation` (`id`, `employee_id`, `description`, `date_start`, `date_final`, `days_amount`, `holiday_amount`, `comment`, `optlock`)
VALUES
	(1, 1, 'Отпуск (1 часть) за 2013 год', '2014-02-01', '2014-02-12', 12, 0, '', 0),
	(2, 1, 'Отпуск (2 часть) за 2013 год', '2014-07-01', '2014-07-12', 12, 0, '', 0),
	(3, 2, 'Отпуск (1 часть) за 2013 год', '2014-02-01', '2014-02-12', 12, 0, '', 0),
	(4, 2, 'Отпуск (2 часть) за 2013 год', '2014-07-01', '2014-07-12', 12, 0, '', 0),
	(5, 3, 'Отпуск (1 часть) за 2013 год', '2014-02-01', '2014-02-12', 12, 0, '', 0),
	(6, 3, 'Отпуск (2 часть) за 2013 год', '2014-07-01', '2014-07-12', 12, 0, '', 0);
	
TRUNCATE `sick_list`;
INSERT INTO `sick_list` (`id`, `employee_id`, `description`, `date_start`, `date_final`, `days_amount`, `comment`, `optlock`)
VALUES
	(1, 1, 'Больничный в феврале 2014 года', '2014-02-01', '2014-02-12', 12, '', 0);

TRUNCATE `client_agreement`;
INSERT INTO `client_agreement` (`id`, `client_id`, `number`, `date_start`, `comment`, `optlock`)
VALUES 
  (1, 1, '20170701/45/76', '2017-07-01', '', 0),
  (2, 2, 'FLP-2015/3', '2015-03-01', 'Договор на ТП 2015 год', 0),
  (3, 1, '20170701/45/77', '2017-08-01', '', 0),
  (4, 3, '2017-07-01/4578/7709', '2017-09-01', '', 0);

TRUNCATE `document_type`;
INSERT INTO `document_type` (`id`, `title`, `optlock`)
VALUES 
  (1, 'Акт выполненных работ', 0),
  (2, 'Счет-фактура', 0),
  (3, 'Доп. соглашение', 0);
  
TRUNCATE `document_status`;
INSERT INTO `document_status` (`id`, `status`, `optlock`)
VALUES 
  (1, 'Передан', 0),
  (2, 'Подписан', 0),
  (3, 'Оплачен', 0),
  (4, 'Введен', 0);
  
TRUNCATE `document`;
INSERT INTO `document` (`id`, `client_agreement_id`, `doc_type_id`, `number`, `dated`, `amount`, `payment_date`, `doc_status_id`, `comment`, `optlock`)
VALUES 
  (1, 1, 2, '18', '2017-06-30', 20000.0, null, '1', '', 0),
  (2, 2, 3, '3', '2017-07-01', 500000.0, '2017-07-01', '4', 'На доп. услуги за 3 квартал', 0),
  (3, 3, 1, '64', '2017-06-30', 20000.0, '2017-07-10', '3', '', 0),
  (4, 3, 1, '65', '2017-07-10', 30000.0, '2017-08-14', '3', '', 0),
  (5, 3, 1, '66', '2017-08-15', 30001.0, '2017-09-16', '3', '', 0),
  (6, 4, 1, '67', '2017-09-30', 40000.0, '2017-12-01', '3', '', 0),
  (7, 3, 1, '67', '2017-09-30', 40000.0, '2017-12-01', '3', '', 0);

SET FOREIGN_KEY_CHECKS=1;