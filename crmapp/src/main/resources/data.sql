USE `crmapp`;

SET FOREIGN_KEY_CHECKS=0;

TRUNCATE `categories`;
INSERT INTO `categories` (`id`, `label`, `icon`, `router_link`, `expanded`, `items`, `optlock`)
VALUES 
	(1, 'Клиенты', 'fa-users', '/clients', b'0', null, 0),
	(2, 'Договорные отношения', 'fa-file-o', '/agreements', b'0', null, 0),
	(3, 'Документооборот', 'fa-file-o', '/documents', b'0', null, 0),
	(4, 'Физические лица', 'fa-users', '/persons', b'0', null, 0),
	(5, 'Сотрудники', 'fa-users', '/employees', b'0', null, 0),
	(6, 'Отпуски', 'fa-file-o', '/vacations', b'0', null, 0),
	(7, 'Больничные листы', 'fa-file-o', '/sicklists', b'0', null, 0),
	(8, 'Исходящий номер', 'fa-file-o', '/outputs', b'0', null, 0);

TRUNCATE `clients`;
INSERT INTO `clients` (`id`, `code`, `title`, `edrpou`, `inn`, `vat_certificate`, `optlock`)
VALUES 
	(1, 'kyivstar', 'ПРАТ "КИЇВСТАР"', '00987654', '0098765321', '345432678', 0),
	(2, 'farlep', 'ПРАТ "ФАРЛЕП-ІНВЕСТ"', '00678906', '00678905412', '087654322', 0),
	(3, 'ukrtelecom', 'ПАТ "УКРТЕЛЕКОМ"', '00312156', '00312156412', '085987432', 0),
	(4, 'pride-group', 'ТОВ "ПРАЙД-ГРУП"', '00352359', '', '', 0),
	(5, '2daytelecom', 'ТОО "2ДЭЙ ТЕЛЕКОМ"', '1111111111', '', '', 0),
	(6, 'golden-tel', 'ТОВ "ГОЛДЕН ТЕЛЕКОМ"', '19028202', '', '', 0),
	(7, 'test-client1', 'ТОВ "TEST-CLIENT1"', '19028202', '', '', 0),
	(8, 'test-client2', 'ТОВ "TEST-CLIENT2"', '19028202', '', '', 0),
	(9, 'test-client3', 'ТОВ "TEST-CLIENT3"', '19028202', '', '', 0),
	(10, 'test-client4', 'ТОВ "TEST-CLIENT4"', '19028202', '', '', 0),
	(11, 'test-client5', 'ТОВ "TEST-CLIENT5"', '19028202', '', '', 0),
	(12, 'test-client6', 'ТОВ "TEST-CLIENT6"', '19028202', '', '', 0),
	(13, 'test-client7', 'ТОВ "TEST-CLIENT7"', '19028202', '', '', 0);

TRUNCATE `contractors`;
INSERT INTO `contractors` (`id`, `code`, `title`, `edrpou`, `inn`, `vat_certificate`, `contractor_type_id`,`optlock`)
VALUES
	(1, 'kyivstar', 'ПРАТ "КИЇВСТАР"', '00987654', '0098765321', '345432678', 1, 0),
	(2, 'farlep', 'ПРАТ "ФАРЛЕП-ІНВЕСТ"', '00678906', '00678905412', '087654322', 1, 0),
	(3, 'ukrtelecom', 'ПАТ "УКРТЕЛЕКОМ"', '00312156', '00312156412', '085987432', 1, 0),
	(4, 'pride-group', 'ТОВ "ПРАЙД-ГРУП"', '00352359', '', '', 1, 0),
	(5, '2daytelecom', 'ТОО "2ДЭЙ ТЕЛЕКОМ"', '111111111', '', '', 1,  0),
	(6, 'golden-tel', 'ТОВ "ГОЛДЕН ТЕЛЕКОМ"', '19028203', '', '', 1,  0),
	(7, 'test-client1', 'ТОВ "TEST-CLIENT1"', '19028204', '', '', 1,  0),
  (8, 'test-client2', 'ТОВ "TEST-CLIENT2"', '19028205', '', '', 1,  0),
	(9, 'test-client3', 'ТОВ "TEST-CLIENT3"', '19028206', '', '', 1,  0),
	(10, 'test-client4', 'ТОВ "TEST-CLIENT4"', '19028207', '', '', 1,  0),
  (11, 'test-client5', 'ТОВ "TEST-CLIENT5"', '19028208', '', '', 1,  0),
  (12, 'test-client6', 'ТОВ "TEST-CLIENT6"', '19028209', '', '', 1,  0),
	(13, 'test-supplier1', 'ТОВ "TEST-SUPPLIER1"', '19028211', '', '', 2, 0),
	(14, 'test-supplier2', 'ТОВ "TEST-SUPPLIER2"', '19028212', '', '', 2, 0),
	(15, 'test-supplier3', 'ТОВ "TEST-SUPPLIER3"', '19028213', '', '', 2, 0),
	(16, 'test-supplier4', 'ТОВ "TEST-SUPPLIER4"', '19028214', '', '', 2, 0);

TRUNCATE `contractor_types`;
INSERT INTO `contractor_types` (`id`,`code`, `title`, `optlock`)
VALUES
	(1, 'clients', 'Клиенты', 0),
	(2, 'suppliers', 'Поставщики', 0);

TRUNCATE `addresses`;
INSERT INTO `addresses` (`id`, `country_id`, `city`, `region`, `street`, `building`, `apartment`, `zip`, `optlock`)
VALUES
	(1,  1, 'м. Київ', '', 'вул. Горького', 'буд. 172', 'оф. 1020', '03150', 0),
	(2,  1, 'м. Київ', '', 'вул. Крещатик', 'буд. 38', '', '01015', 0),
	(3,  1, 'м. Київ', '', 'бульв. Т. Шевченко', '18', '', '02010', 0),
	(4,  1, 'м. Київ', '', 'бульв. Т. Шевченко', '21', 'офіс 43', '02012', 0),
	(5,  1, 'м. Київ', '', 'ул. Лермонтова', '21', 'офіс 456', '04014', 0),
	(6,  1, 'м. Київ', '', 'пр-т Оболонський', 'буд. 23', 'кв. 34', '04205', 0),
	(7,  1, 'м. Киев', '', 'вул. Крещатик', '12', 'кв. 45', '01045', 0),
	(8,  1, 'м. Киев', '', 'бульв. Т. Шевченко', '34/1', 'кв. 2', '00023', 0),
  (9,  1, 'м. Київ', '', 'вул. Горького', 'буд. 172', 'офіс 1020', '03150', 0),
  (10, 1, 'м. Киев', '', 'вул. Козацька', '120/4', 'літ. Б', '03022', 0);

TRUNCATE `contractor_addresses`;
INSERT into `contractor_addresses` (`id`, `contractor_id`, `address_id`, `date_start`, `optlock`)
VALUES
  (1, 1, 1, '2017-09-30', 0),
  (2, 1, 2, '2018-09-30', 0),
  (3, 1, 3, '2019-09-30', 0),
  (4, 2, 4, '2017-10-25', 0),
  (5, 2, 1, '2018-07-15', 0);

TRUNCATE `client_addresses`;
INSERT INTO `client_addresses` (`id`, `client_id`, `country_id`, `city`, `region`, `street`, `building`, `apartment`, `zip`, `date_start`, `optlock`)
VALUES
	(1, 1, 1, 'м. Київ', '', 'вул. Горького', 'буд. 172', 'оф. 1020', '03150', '2012-01-01', 0),
	(2, 2, 1, 'м. Київ', '', 'вул. Крещатик', 'буд. 38', '', '01015', '2012-01-01', 0),
	(3, 3, 1, 'м. Київ', '', 'бульв. Т. Шевченко', '18', '', '02010', '2012-01-01', 0),
	(4, 1, 1, 'м. Київ', '', 'бульв. Т. Шевченко', '21', 'офіс 43', '02012', '2015-01-01', 0),
	(5, 2, 1, 'м. Київ', '', 'ул. Лермонтова', '21', 'офіс 456', '04014', '2015-01-01', 0);

TRUNCATE `client_directors`;
INSERT INTO `client_directors` (`id`, `client_id`, `post_id`, `full_name`, `short_name`, `date_start`, `optlock`)
VALUES
	(1, 1, 1, 'Степанюк Алла Борисівна', 'Степанюк А.Б.', '2013-01-01', 0),
	(2, 2, 1, 'Слєпаков Семен Валерійович', 'Слєпаков С.В.', '2013-01-01', 0),
	(3, 3, 1, 'Махно Нестор Петрович', 'Махно Н.П.', '2013-01-01', 0),
	(4, 1, 1, 'Зощенко Виктор Алексеевич', 'Зощенко В.А.', '2015-01-01', 0);

TRUNCATE `client_accounts`;
INSERT INTO `client_accounts` (`id`, `client_id`, `number`,`bank_id`, `currency_type_id`, `date_start`, `optlock`)
VALUES
	(1, 1, '26007017100038', 1, 1, '2013-01-01', 0),
	(2, 2, '26007247100756', 1, 1, '2013-01-01', 0),
	(3, 1, '26007017134344', 2, 1, '2015-01-01', 0);

TRUNCATE `client_agreements`;
INSERT INTO `client_agreements` (`id`, `client_id`, `code`, `number`, `date_start`, `comment`, `optlock`)
VALUES
	(1, 1, '', '20170701/45/76', '2017-07-01', '', 0),
	(2, 2, '', 'FLP-2015/3', '2015-03-01', 'Договор на ТП 2015 год', 0),
	(3, 1, '', '20170701/45/77', '2017-08-01', '', 0),
	(4, 3, '', '2017-07-01/4578/7709', '2017-09-01', '', 0),
	(5, 1, '', '2015/3', '2015-03-01', 'Договор на ТП 2015 год', 0),
	(6, 2, '', '2016/6', '2016-03-01', 'Договор на ТП 2016 год', 0),
	(7, 3, '', '2017/8', '2017-03-01', 'Договор на ТП 2017 год', 0);

TRUNCATE `posts`;
INSERT INTO `posts` (`id`, `title`, `optlock`)
VALUES
	(1, 'Генеральный директор', 0),
	(2, 'Java Developer', 0),
	(3, 'Администратор', 0),
	(4, 'Senior Software Developer', 0),
	(5, 'Software Developer', 0);
	
TRUNCATE `persons`;
INSERT INTO `persons` (`id`, `surname`, `firstname`, `lastname`, `short_name`, `inn`, `birth_date`, `optlock`)
VALUES
	(1, 'Иванов', 'Александр', 'Владимирович', 'Иванов А.В.', '2344566541', '1972-03-24', 0),
	(2, 'Петров', 'Иван', 'Федорович', 'Петров И.Ф.', '3045679871', '1984-06-15', 0),
	(3, 'Сидоров', 'Петр', 'Валерьевич', 'Сидоров П.В.', '2874526548', '1978-09-20', 0),
	(4, 'Сидоренко', 'Федор', 'Степанович', 'Сидоренко Ф.С.', '2674826643', '1960-09-30', 0);
	
TRUNCATE `employees`;
INSERT INTO `employees` (`id`, `person_id`, `is_entrepreneur`, `hire_date`, `fired_date`, `post_id`, `optlock`)
VALUES
	(1, 1, b'0', '2012-01-01', null, 5, 0),
	(2, 2, b'1', '2013-06-01', null, 5, 0),
	(3, 3, b'0', '2012-01-01', null, 5, 0),
	(4, 4, b'0', '2012-01-01', null, 4, 0);

TRUNCATE `employee_accounts`;
INSERT INTO `employee_accounts` (`id`, `employee_id`, `number`, `bank_id`, `currency_type_id`, `date_start`, `optlock`)
VALUES
	(1, 1, '26007017100038', 1, 1, '2013-01-01', 0),
	(2, 2, '26007247100756', 2, 1, '2013-01-01', 0),
	(3, 3, '26007547230756', 1, 1, '2013-01-01', 0);

TRUNCATE `vacations`;
INSERT INTO `vacations` (`id`, `employee_id`, `description`, `date_start`, `date_final`, `days_amount`, `holiday_amount`, `comment`, `optlock`)
VALUES
	(1, 1, 'Отпуск (1 часть) за 2013 год', '2014-02-01', '2014-02-12', 12, 0, '', 0),
	(2, 1, 'Отпуск (2 часть) за 2013 год', '2014-07-01', '2014-07-12', 12, 0, '', 0),
	(3, 2, 'Отпуск (1 часть) за 2013 год', '2014-02-01', '2014-03-05', 12, 0, '', 0),
	(4, 2, 'Отпуск (2 часть) за 2013 год', '2014-07-01', '2014-07-12', 12, 0, '', 0),
	(5, 3, 'Отпуск (1 часть) за 2013 год', '2014-02-01', '2014-02-12', 12, 0, '', 0),
	(6, 3, 'Отпуск (2 часть) за 2013 год', '2014-07-01', '2014-07-12', 12, 0, '', 0);
	
TRUNCATE `sick_lists`;
INSERT INTO `sick_lists` (`id`, `employee_id`, `description`, `date_start`, `date_final`, `days_amount`, `comment`, `optlock`)
VALUES
	(1, 1, 'Больничный в феврале 2014 года', '2014-02-01', '2014-02-12', 12, '', 0);

TRUNCATE `agreements`;
INSERT INTO `agreements` (`id`, `client_id`, `employee_id`, `number`, `date_start`, `comment`, `optlock`, `agreement_type_code`)
VALUES 
	(1, 1, null, '20170701/45/76', '2017-07-01', '', 0, 'clients'),
	(2, 2, null, 'FLP-2015/3', '2015-03-01', 'Договор на ТП 2015 год', 0, 'clients'),
	(3, 1, null, '20170701/45/77', '2017-08-01', '', 0, 'clients'),
	(4, 3, null, '2017-07-01/4578/7709', '2017-09-01', '', 0, 'clients'),
	(5, null, 1, '2015/3', '2015-03-01', 'Договор на ТП 2015 год', 0, 'employees'),
	(6, null, 1, '2016/6', '2016-03-01', 'Договор на ТП 2016 год', 0, 'employees'),
	(7, null, 1, '2017/8', '2017-03-01', 'Договор на ТП 2017 год', 0, 'employees');

TRUNCATE `agreement_types`;
INSERT INTO `agreement_types` (`id`,`code`, `title`, `optlock`)
VALUES
	(1, 'clients', 'Клиенты', 0),
	(2, 'employees', 'Сотрудники', 0);

TRUNCATE `document_types`;
INSERT INTO `document_types` (`id`, `title`, `short_title`, `optlock`)
VALUES 
	(1, 'Акт выполненных работ', 'акт вып. раб.', 0),
	(2, 'Счет-фактура', 'сч/ф', 0),
	(3, 'Доп. соглашение', 'доп. согл.', 0),
	(4, 'Акт передачи лицензий', 'акт пер. лиц.', 0);

TRUNCATE `document_statuses`;
INSERT INTO `document_statuses` (`id`, `status`, `background_color`, `color`, `allow_payment_date`, `allow_passing_date`, `optlock`)
VALUES
	(1, 'Передан', '#DAA7BB', '#FFFFFF', b'0', b'1', 0),
	(2, 'Подписан', '#5FDAB1', '#FFFFFF', b'0', b'0', 0),
	(3, 'Оплачен', '#32DA14', '#FFFFFF', b'1', b'0', 0),
	(4, 'Введен', '#BD7EDA', '#FFFFFF', b'0', b'0', 0);

TRUNCATE `documents`;
INSERT INTO `documents` (`id`, `agreement_id`, `doc_type_id`, `number`, `dated`, `amount`, `payment_date`, `passing_date`, `doc_status_id`, `comment`, `optlock`)
VALUES 
	(1, 1, 2, '18', '2017-06-30', 20000.0, null, '2017-06-30', '1', '', 0),
	(2, 2, 3, '3', '2017-07-01', 500000.0, null, null, '4', 'На доп. услуги за 3 квартал', 0),
	(3, 3, 1, '64', '2017-06-30', 20000.0, '2017-07-10', null, '3', '', 0),
	(4, 3, 1, '65', '2017-07-10', 30000.0, null, '2017-07-12', '1', '', 0),
	(5, 3, 1, '66', '2017-08-15', 30001.0, null, null, '2', '', 0),
	(6, 4, 1, '67', '2017-09-30', 40000.0, '2017-12-01', null, '3', '', 0),
	(7, 3, 1, '68', '2017-09-30', 40000.0, null, null, '4', '', 0),
	(8, 3, 1, '69', '2017-10-31', 300000.0, null, null, '4', '', 0),
	(9, 4, 1, '90', '2018-09-30', 450000.0, '2018-11-01', '2018-09-30', '3', '', 0);

TRUNCATE `mail_document_types`;
INSERT INTO `mail_document_types` (`id`, `title`, `optlock`)
VALUES 
	(1, 'Письмо', 0),
	(2, 'Доверенность', 0),
	(3, 'Коммерческое предложение', 0);

TRUNCATE `mail_outputs`;
INSERT INTO `mail_outputs` (`id`, `mail_doc_type_id`, `number`, `dated`, `receiver`, `comment`, `optlock`)
VALUES 
	(1, 1, '401', '2017-04-30', 'Тестовый Получатель', '', 0),
	(2, 2, '402', '2017-05-01', 'Тестовый Получатель', '', 0),
	(3, 3, '403', '2017-06-02', 'Тестовый Получатель', '', 0),
	(4, 1, '404', '2017-06-02', 'Тестовый Получатель', '', 0),
	(5, 1, '405', '2017-07-03', 'Тестовый Получатель', '', 0),
	(6, 2, '406', '2017-09-06', 'Тестовый Получатель', '', 0);

TRUNCATE `mail_inputs`;
INSERT INTO `mail_inputs` (`id`, `mail_doc_type_id`, `number`, `dated`, `sender`, `comment`, `optlock`)
VALUES 
	(1, 1, '1', '2017-04-30', 'ГНИ', '', 0),
	(2, 2, '2', '2017-05-01', 'ГНИ', '', 0),
	(3, 3, '3', '2017-06-02', 'Фарлеп', '', 0),
	(4, 1, '4', '2017-06-02', 'ГНИ', '', 0),
	(5, 1, '5', '2017-07-03', 'ГНИ', '', 0),
	(6, 2, '6', '2017-09-06', 'ГНИ', '', 0);

RUNCATE `our_companies`;
INSERT INTO `our_companies` (`id`, `alias`, `title`, `edrpou`, `inn`, `vat_certificate`, `optlock`)
VALUES 
	(1, 'geosap', 'ТОВ "ГЕОСАП"', '36483010', '', '', 0);

TRUNCATE `our_company_directors`;
INSERT INTO `our_company_directors` (`id`, `our_company_id`, `post_id`, `full_name`, `short_name`, `date_start`, `optlock`)
VALUES
	(1, 1, 1, 'Стоян Олександр Володимирович', 'Стоян О.В.', '2015-01-01', 0);

TRUNCATE `countries`;
INSERT INTO `countries` (`id`, `title`, `code`, `optlock`)
VALUES
	(1, 'Украина', 'UA', 0),
	(2, 'Германия', 'DE', 0),
	(3, 'Канада', 'CA', 0);

TRUNCATE `suppliers`;
INSERT INTO `suppliers` (`id`, `code`, `title`, `edrpou`, `inn`, `vat_certificate`, `optlock`)
VALUES
	(1, 'google', 'GOOGLE LLC', '32987654', '', '', 0),
	(2, 'microsoft', 'MICROSOFT CORP LLC', '12678906', '12678905412', '187654322', 0),
	(3, 'oracle', 'ORACLE CORP LLC', '21312156', '21312156412', '285987432', 0);

TRUNCATE `supplier_addresses`;
INSERT INTO `supplier_addresses` (`id`, `supplier_id`, `country_id`, `city`, `region`, `street`, `building`, `apartment`, `zip`, `date_start`, `optlock`)
VALUES
	(1, 1, 1, 'м. Київ', '', 'вул. Горького', 'буд. 172', 'оф. 1020', '03150', '2012-01-01', 0),
	(2, 2, 1, 'м. Київ', '', 'вул. Крещатик', 'буд. 38', '', '01015', '2012-01-01', 0),
	(3, 3, 1, 'м. Київ', '', 'бульв. Т. Шевченко', '18', '', '02010', '2012-01-01', 0),
	(4, 1, 1, 'м. Київ', '', 'бульв. Т. Шевченко', '21', 'офіс 43', '02012', '2015-01-01', 0),
	(5, 2, 1, 'м. Київ', '', 'ул. Лермонтова', '21', 'офіс 456', '04014', '2015-01-01', 0);

	TRUNCATE `supplier_directors`;
INSERT INTO `supplier_directors` (`id`, `supplier_id`, `post_id`, `full_name`, `short_name`, `date_start`, `optlock`)
VALUES
	(1, 1, 1, 'Степанюк Алла Борисівна', 'Степанюк А.Б.', '2013-01-01', 0),
	(2, 2, 1, 'Слєпаков Семен Валерійович', 'Слєпаков С.В.', '2013-01-01', 0),
	(3, 3, 1, 'Махно Нестор Петрович', 'Махно Н.П.', '2013-01-01', 0),
	(4, 1, 1, 'Зощенко Виктор Алексеевич', 'Зощенко В.А.', '2015-01-01', 0);

TRUNCATE `supplier_accounts`;
INSERT INTO `supplier_accounts` (`id`, `supplier_id`, `number`,`bank_id`, `currency_type_id`, `date_start`, `optlock`)
VALUES
	(1, 1, '26007017100038', 1, 1, '2013-01-01', 0),
	(2, 2, '26007247100756', 2, 1, '2013-01-01', 0),
	(3, 1, '26007017134344', 3, 1, '2015-01-01', 0);

TRUNCATE `supplier_agreements`;
INSERT INTO `supplier_agreements` (`id`, `supplier_id`, `code`, `number`, `date_start`, `comment`, `optlock`)
VALUES
	(1, 1, '', '20170701/45/76', '2017-07-01', '', 0),
	(2, 2, '', 'FLP-2015/3', '2015-03-01', 'Договор на ТП 2015 год', 0),
	(3, 1, '', '20170701/45/77', '2017-08-01', '', 0),
	(4, 3, '', '2017-07-01/4578/7709', '2017-09-01', '', 0),
	(5, 1, '', '2015/3', '2015-03-01', 'Договор на ТП 2015 год', 0),
	(6, 2, '', '2016/6', '2016-03-01', 'Договор на ТП 2016 год', 0),
	(7, 3, '', '2017/8', '2017-03-01', 'Договор на ТП 2017 год', 0);

TRUNCATE `banks`;
INSERT INTO `banks` (`id`, `title`, `mfo`, `optlock`)
VALUES
    (1, 'АТ "Піреус Банк МКБ"', '300658', 0),
    (2, 'АТ "ПРИВАТБАНК"', '320699', 0),
    (3, 'АТ "Сити-Банк"', '311009', 0);

TRUNCATE `currency_types`;
INSERT into `currency_types` (`id`, `curr_code`, `curr_name`, `curr_short_name`, `optlock`)
VALUES
    (1, '980', 'Украинская гривна', 'UAH', 0),
    (2, '840', 'Доллар США', 'USD', 0);

SET FOREIGN_KEY_CHECKS=1;
