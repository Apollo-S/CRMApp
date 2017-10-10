USE `crmapp`;

SET FOREIGN_KEY_CHECKS=0;

TRUNCATE `client`;
INSERT INTO `client` (`id`, `alias`, `title`, `edrpou`, `inn`, `vat_certificate`, `optlock`)
VALUES 
  (1, 'kyivstar', 'ПРАТ "КИЇВСТАР"', '00987654', '0098765321', '345432678', 0),
  (2, 'farlep', 'ПРАТ "ФАРЛЕП-ІНВЕСТ"', '00678906', '00678905412', '087654322', 0);

TRUNCATE `agreement`;
INSERT INTO `agreement` (`id`, `client_id`, `number`, `date_start`, `comment`, `optlock`)
VALUES 
  (1, 1, '20170701/45/76', '2017-07-01', '', 0),
  (2, 2, 'FLP-2015/3', '2015-03-01', 'Договор на ТП 2015 год', 0);

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
  (3, 'Оплачен', 0);
  
TRUNCATE `document`;
INSERT INTO `document` (`id`, `agreement_id`, `doc_type_id`, `number`, `dated`, `amount`, `payment_date`, `doc_status_id`, `comment`, `optlock`)
VALUES 
  (1, 1, 2, '18', '2017-06-30', 20000.0, null, '1', '', 0),
  (2, 2, 3, '3', '2017-07-01', 500000.0, '2017-07-01', '2', 'На доп. услуги за 3 квартал', 0),
  (3, 1, 1, '64', '2017-06-30', 20000.0, '2017-07-10', '3', '', 0);

SET FOREIGN_KEY_CHECKS=1;