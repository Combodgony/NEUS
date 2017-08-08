--
-- Скрипт сгенерирован Devart dbForge Studio for MySQL, Версия 7.0.52.0
-- Домашняя страница продукта: http://www.devart.com/ru/dbforge/mysql/studio
-- Дата скрипта: 08.08.2017 9:11:49
-- Версия сервера: 5.5.23
-- Версия клиента: 4.1
--


-- 
-- Отключение внешних ключей
-- 
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;

-- 
-- Установить режим SQL (SQL mode)
-- 
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 
-- Установка кодировки, с использованием которой клиент будет посылать запросы на сервер
--
SET NAMES 'utf8';

-- 
-- Установка базы данных по умолчанию
--
USE terminal;

--
-- Описание для таблицы cargo
--
CREATE TABLE cargo (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 4
AVG_ROW_LENGTH = 5461
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы client
--
CREATE TABLE client (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  adress VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 5
AVG_ROW_LENGTH = 4096
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы tanker
--
CREATE TABLE tanker (
  id INT(11) NOT NULL AUTO_INCREMENT,
  number VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы typeestakada
--
CREATE TABLE typeestakada (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 3
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы typestationarystorage
--
CREATE TABLE typestationarystorage (
  id INT(11) NOT NULL AUTO_INCREMENT,
  Volume INT(11) NOT NULL COMMENT 'тон',
  PRIMARY KEY (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 8
AVG_ROW_LENGTH = 2340
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы contract
--
CREATE TABLE contract (
  id INT(11) NOT NULL AUTO_INCREMENT,
  idClient INT(11) NOT NULL,
  number VARCHAR(50) NOT NULL,
  beginDate DATETIME DEFAULT NULL,
  endDate DATETIME DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_treaty_client_id FOREIGN KEY (idClient)
    REFERENCES client(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 3
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы estakada
--
CREATE TABLE estakada (
  id INT(11) NOT NULL AUTO_INCREMENT,
  idTypeEstakada INT(11) NOT NULL,
  number VARCHAR(50) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_estakada_typeestakada_id FOREIGN KEY (idTypeEstakada)
    REFERENCES typeestakada(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 4
AVG_ROW_LENGTH = 5461
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы stationarystorage
--
CREATE TABLE stationarystorage (
  id INT(11) NOT NULL AUTO_INCREMENT,
  number VARCHAR(255) NOT NULL,
  idTypeStationaryStorage INT(11) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_tank_typetank_id FOREIGN KEY (idTypeStationaryStorage)
    REFERENCES typestationarystorage(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 5
AVG_ROW_LENGTH = 4096
CHARACTER SET utf8
COLLATE utf8_general_ci
COMMENT = 'цистерна';

--
-- Описание для таблицы typetank
--
CREATE TABLE typetank (
  id INT(11) NOT NULL AUTO_INCREMENT,
  idTypeEstakada INT(11) NOT NULL,
  maxV INT(11) NOT NULL,
  timeshipment INT(11) NOT NULL COMMENT 'minuts',
  PRIMARY KEY (id),
  CONSTRAINT FK_typetank_typeestakada_id FOREIGN KEY (idTypeEstakada)
    REFERENCES typeestakada(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 3
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы contentcontract
--
CREATE TABLE contentcontract (
  idCargo INT(11) NOT NULL,
  volume INT(11) NOT NULL,
  idContract INT(11) NOT NULL,
  CONSTRAINT FK_contentcontract_cargo_id FOREIGN KEY (idCargo)
    REFERENCES cargo(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_contentcontract_contract_id FOREIGN KEY (idContract)
    REFERENCES contract(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AVG_ROW_LENGTH = 5461
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы drainlocation
--
CREATE TABLE drainlocation (
  id INT(11) NOT NULL AUTO_INCREMENT,
  number INT(11) NOT NULL,
  idEstakada INT(11) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_drainlocation_estakada_id FOREIGN KEY (idEstakada)
    REFERENCES estakada(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 11
AVG_ROW_LENGTH = 1638
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы pumping
--
CREATE TABLE pumping (
  id INT(11) NOT NULL DEFAULT 0,
  idSource INT(11) NOT NULL,
  idReceiver INT(11) NOT NULL,
  beginDate DATETIME DEFAULT NULL,
  endDate DATETIME DEFAULT NULL,
  Volume INT(11) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_pumping_stationarystorage_id FOREIGN KEY (idSource)
    REFERENCES stationarystorage(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_pumping1_stationarystorage_id FOREIGN KEY (idReceiver)
    REFERENCES stationarystorage(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы pumpingtotanker
--
CREATE TABLE pumpingtotanker (
  id INT(11) NOT NULL AUTO_INCREMENT,
  idTanker INT(11) NOT NULL,
  idStationaryStorage INT(11) NOT NULL,
  idCargo INT(11) NOT NULL,
  volume INT(11) NOT NULL,
  status ENUM('План','Виконано') NOT NULL DEFAULT 'План',
  PRIMARY KEY (id),
  CONSTRAINT FK_pumpingtotanker_cargo_id FOREIGN KEY (idCargo)
    REFERENCES cargo(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_pumpingtotanker_stationarystorage_id FOREIGN KEY (idStationaryStorage)
    REFERENCES stationarystorage(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_pumpingtotanker_tanker_id FOREIGN KEY (idTanker)
    REFERENCES tanker(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы tank
--
CREATE TABLE tank (
  id INT(11) NOT NULL AUTO_INCREMENT,
  number VARCHAR(50) NOT NULL,
  idTypeTank INT(11) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_tank_typetank_id1 FOREIGN KEY (idTypeTank)
    REFERENCES typetank(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 242
AVG_ROW_LENGTH = 117
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы admission
--
CREATE TABLE admission (
  id INT(11) NOT NULL AUTO_INCREMENT,
  idContract INT(11) NOT NULL,
  idCargo INT(11) DEFAULT NULL,
  idDraionLocation INT(11) DEFAULT NULL,
  idTank INT(11) DEFAULT NULL,
  volume INT(11) DEFAULT NULL,
  beginDate DATETIME DEFAULT NULL,
  endDate DATETIME DEFAULT NULL,
  idStationaryStorage INT(11) DEFAULT NULL,
  status ENUM('План','Виконано') NOT NULL DEFAULT 'План',
  PRIMARY KEY (id),
  CONSTRAINT FK_admission_cargo_id FOREIGN KEY (idCargo)
    REFERENCES cargo(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_admission_drainlocation_id FOREIGN KEY (idDraionLocation)
    REFERENCES drainlocation(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_admission_stationarystorage_id FOREIGN KEY (idStationaryStorage)
    REFERENCES stationarystorage(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_admission_tank_id FOREIGN KEY (idTank)
    REFERENCES tank(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_admission_treaty_id FOREIGN KEY (idContract)
    REFERENCES contract(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 64
AVG_ROW_LENGTH = 282
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы shipment
--
CREATE TABLE shipment (
  id INT(11) NOT NULL AUTO_INCREMENT,
  idContract INT(11) NOT NULL,
  idCargo INT(11) NOT NULL,
  idPumpingTanker INT(11) DEFAULT NULL,
  idTanker INT(11) NOT NULL,
  beginDate DATETIME DEFAULT NULL,
  endDate DATETIME DEFAULT NULL,
  volume INT(11) NOT NULL,
  status ENUM('План','Виконано') NOT NULL DEFAULT 'План',
  PRIMARY KEY (id),
  CONSTRAINT FK_shioment_cargo_id FOREIGN KEY (idCargo)
    REFERENCES cargo(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_shioment_contract_id FOREIGN KEY (idContract)
    REFERENCES contract(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_shioment_pumpingtotanker_id FOREIGN KEY (idPumpingTanker)
    REFERENCES pumpingtotanker(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_shioment_tanker_id FOREIGN KEY (idTanker)
    REFERENCES tanker(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы dependencyadmission
--
CREATE TABLE dependencyadmission (
  idDependent INT(11) NOT NULL,
  idIndependent INT(11) NOT NULL,
  CONSTRAINT FK_dependencyadmission FOREIGN KEY (idIndependent)
    REFERENCES admission(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_dependencyadmission_admission_id FOREIGN KEY (idDependent)
    REFERENCES admission(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

-- 
-- Вывод данных для таблицы cargo
--
INSERT INTO cargo VALUES
(1, 'Соняшникова олія'),
(2, 'Рапсова олія'),
(3, 'Меласа');

-- 
-- Вывод данных для таблицы client
--
INSERT INTO client VALUES
(1, 'Дніпро', 'Україна, Миколаївська обл., м. Новий Буг вул. В''язов 34 '),
(2, 'Інгул', 'Україна, м. Київ, Заводська 34 а'),
(3, 'Зоря', 'Адреса організації "Зоря"'),
(4, 'Буг', 'Адреса огранізації "Буг"');

-- 
-- Вывод данных для таблицы tanker
--

-- Таблица terminal.tanker не содержит данных

-- 
-- Вывод данных для таблицы typeestakada
--
INSERT INTO typeestakada VALUES
(1, 'авто'),
(2, 'ж/д');

-- 
-- Вывод данных для таблицы typestationarystorage
--
INSERT INTO typestationarystorage VALUES
(1, 2000),
(2, 6000),
(3, 6600),
(4, 3200),
(5, 980),
(6, 190),
(7, 4000);

-- 
-- Вывод данных для таблицы contract
--
INSERT INTO contract VALUES
(1, 2, 'ДО-51', '2017-08-21 11:30:39', '2017-10-31 11:30:57'),
(2, 4, 'ДА-1', '2017-06-15 11:43:29', '2017-08-31 11:43:34');

-- 
-- Вывод данных для таблицы estakada
--
INSERT INTO estakada VALUES
(1, 1, '1'),
(2, 2, '1'),
(3, 1, '2');

-- 
-- Вывод данных для таблицы stationarystorage
--
INSERT INTO stationarystorage VALUES
(1, '1', 1),
(2, '2', 1),
(3, '3', 1),
(4, '1', 6);

-- 
-- Вывод данных для таблицы typetank
--
INSERT INTO typetank VALUES
(1, 1, 30, 50),
(2, 2, 60, 70);

-- 
-- Вывод данных для таблицы contentcontract
--
INSERT INTO contentcontract VALUES
(1, 1500, 1),
(1, 1000, 2),
(2, 2000, 2);

-- 
-- Вывод данных для таблицы drainlocation
--
INSERT INTO drainlocation VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 1, 3),
(6, 2, 3),
(7, 3, 3),
(8, 4, 3),
(9, 1, 2),
(10, 2, 2);

-- 
-- Вывод данных для таблицы pumping
--

-- Таблица terminal.pumping не содержит данных

-- 
-- Вывод данных для таблицы pumpingtotanker
--

-- Таблица terminal.pumpingtotanker не содержит данных

-- 
-- Вывод данных для таблицы tank
--
INSERT INTO tank VALUES
(102, 'LJ5470WE', 1),
(103, 'VK6767WF', 1),
(104, 'LR6185EQ', 1),
(105, 'UU5226VC', 1),
(106, 'XT6815IF', 1),
(107, 'ZJ3194MB', 1),
(108, 'YP6147NN', 1),
(109, 'FA1069UF', 1),
(110, 'ZH6389CR', 1),
(111, 'CL7680RL', 1),
(112, 'ZZ3821IB', 1),
(113, 'UF3211FL', 1),
(114, 'GY5713FJ', 1),
(115, 'HN6935NC', 1),
(116, 'WD3462WV', 1),
(117, 'BR6170ZY', 1),
(118, 'DN4362VR', 1),
(119, 'ZE6231HW', 1),
(120, 'OH9805ZO', 1),
(121, 'PI3833HR', 1),
(122, 'TQ6862LI', 1),
(123, 'OP2039ON', 1),
(124, 'EU7971EK', 1),
(125, 'TR7603AF', 1),
(126, 'OX2124SC', 1),
(127, 'QE4793TH', 1),
(128, 'QX6198PL', 1),
(129, 'GN9311YF', 1),
(130, 'FI5945KA', 1),
(131, 'CO3301KD', 1),
(132, 'ME4590FV', 1),
(133, 'CU5421UL', 1),
(134, 'DO2994GO', 1),
(135, 'BN9935HE', 1),
(136, 'RG6174DR', 1),
(137, 'XH2700GL', 1),
(138, 'RY4197QW', 1),
(139, 'QI6037JS', 1),
(140, 'GB9248RT', 1),
(141, 'XR6569AQ', 1),
(142, 'XE1102IB', 1),
(143, 'QC5848HT', 1),
(144, 'LN1943OI', 1),
(145, 'WB2241QJ', 1),
(146, 'FK8205QQ', 1),
(147, 'CU2866KX', 1),
(148, 'SR1121NE', 1),
(149, 'BI7234SY', 1),
(150, 'OB7818HX', 1),
(151, 'LD6313EU', 1),
(152, 'FC9403YZ', 1),
(153, 'NN3776IE', 1),
(154, 'YG6792XU', 1),
(155, 'PU3175DP', 1),
(156, 'DD4551YF', 1),
(157, 'ZX8538AR', 1),
(158, 'XJ4568SB', 1),
(159, 'GP3420NY', 1),
(160, 'HE7744RE', 1),
(161, 'KP7745TL', 1),
(162, 'IY1291LD', 1),
(163, 'LC9457BU', 1),
(164, 'AZ5181YG', 1),
(165, 'YP1361CK', 1),
(166, 'AG9380HH', 1),
(167, 'ZI8931NL', 1),
(168, 'UI3789YM', 1),
(169, 'FN7219MM', 1),
(170, 'RH1364FF', 1),
(171, 'SQ8408TO', 1),
(172, 'QB6168KN', 1),
(173, 'YG6390AL', 1),
(174, 'CI1394ID', 1),
(175, 'XP3456FT', 1),
(176, 'YT3297AQ', 1),
(177, 'PW8563UM', 1),
(178, 'KX6415VF', 1),
(179, 'IL3976TO', 1),
(180, 'RY3807OL', 1),
(181, 'TS1050UC', 1),
(182, 'XD6735HG', 1),
(183, 'YB5865FJ', 1),
(184, 'TI9457ES', 1),
(185, 'DS2280WC', 1),
(186, 'RT4888BS', 1),
(187, 'RN7180EG', 1),
(188, 'QV4868NC', 1),
(189, 'WB6294SE', 1),
(190, 'EO3017OV', 1),
(191, 'XJ5264GY', 1),
(192, 'UG5865JV', 1),
(193, 'TJ3110FB', 1),
(194, 'NR9302UI', 1),
(195, 'ED8945ED', 1),
(196, 'BL1802TD', 1),
(197, 'WF1312HN', 1),
(198, 'ZX4048HR', 1),
(199, 'IS7565RZ', 1),
(200, 'HV7267NZ', 1),
(201, 'XF4899QT', 1),
(202, '586095', 2),
(203, '181691', 2),
(204, '779449', 2),
(205, '471874', 2),
(206, '385893', 2),
(207, '256356', 2),
(208, '730857', 2),
(209, '595481', 2),
(210, '128576', 2),
(211, '367745', 2),
(212, '101888', 2),
(213, '912876', 2),
(214, '682876', 2),
(215, '381646', 2),
(216, '418632', 2),
(217, '501340', 2),
(218, '616427', 2),
(219, '685048', 2),
(220, '351573', 2),
(221, '174045', 2),
(222, '202087', 2),
(223, '897235', 2),
(224, '682121', 2),
(225, '407535', 2),
(226, '211990', 2),
(227, '863809', 2),
(228, '400667', 2),
(229, '563019', 2),
(230, '490426', 2),
(231, '251969', 2),
(232, '268040', 2),
(233, '695643', 2),
(234, '207098', 2),
(235, '146393', 2),
(236, '911854', 2),
(237, '858309', 2),
(238, '649815', 2),
(239, '575784', 2),
(240, '334247', 2),
(241, '147167', 2);

-- 
-- Вывод данных для таблицы admission
--
INSERT INTO admission VALUES
(1, 1, 1, 1, 102, 30, '2017-08-20 14:22:43', '2017-08-20 15:12:53', 1, 'План'),
(7, 1, 1, 2, 103, 30, '2017-08-20 14:22:43', '2017-08-20 15:12:53', 1, 'План'),
(8, 1, 1, 3, 104, 30, '2017-08-20 14:22:43', '2017-08-20 15:12:53', 1, 'План'),
(9, 1, 1, 4, 105, 30, '2017-08-20 14:22:43', '2017-08-20 15:12:53', 1, 'План'),
(10, 1, 1, 5, 106, 30, '2017-08-20 14:22:43', '2017-08-20 15:12:53', 1, 'План'),
(11, 1, 1, 6, 107, 30, '2017-08-20 14:22:43', '2017-08-20 15:12:53', 1, 'План'),
(12, 1, 1, 7, 108, 30, '2017-08-20 14:22:43', '2017-08-20 15:12:53', 1, 'План'),
(13, 1, 1, 8, 109, 30, '2017-08-20 14:22:43', '2017-08-20 15:12:53', 1, 'План'),
(14, 1, 1, 1, 110, 30, '2017-08-20 15:12:00', '2017-08-20 16:02:00', 1, 'План'),
(15, 1, 1, 2, 111, 30, '2017-08-20 15:12:00', '2017-08-20 16:02:00', 1, 'План'),
(16, 1, 1, 3, 112, 30, '2017-08-20 15:12:00', '2017-08-20 16:02:00', 1, 'План'),
(17, 1, 1, 4, 113, 30, '2017-08-20 15:12:00', '2017-08-20 16:02:00', 1, 'План'),
(18, 1, 1, 5, 114, 30, '2017-08-20 15:12:00', '2017-08-20 16:02:00', 1, 'План'),
(19, 1, 1, 6, 115, 30, '2017-08-20 15:12:00', '2017-08-20 16:02:00', 1, 'План'),
(20, 1, 1, 7, 116, 30, '2017-08-20 15:12:00', '2017-08-20 16:02:00', 1, 'План'),
(21, 1, 1, 8, 117, 30, '2017-08-20 15:12:00', '2017-08-20 16:02:00', 1, 'План'),
(22, 1, 1, 1, 118, 30, '2017-08-20 16:02:00', '2017-08-20 16:52:00', 1, 'План'),
(23, 1, 1, 2, 119, 30, '2017-08-20 16:02:00', '2017-08-20 16:52:00', 1, 'План'),
(24, 1, 1, 3, 120, 30, '2017-08-20 16:02:00', '2017-08-20 16:52:00', 1, 'План'),
(25, 1, 1, 4, 121, 30, '2017-08-20 16:02:00', '2017-08-20 16:52:00', 1, 'План'),
(26, 1, 1, 5, 122, 30, '2017-08-20 16:02:00', '2017-08-20 16:52:00', 1, 'План'),
(27, 1, 1, 6, 123, 30, '2017-08-20 16:02:00', '2017-08-20 16:52:00', 1, 'План'),
(28, 1, 1, 7, 124, 30, '2017-08-20 16:02:00', '2017-08-20 16:52:00', 1, 'План'),
(29, 1, 1, 8, 125, 30, '2017-08-20 16:02:00', '2017-08-20 16:52:00', 1, 'План'),
(30, 1, 1, 1, 126, 30, '2017-08-20 16:52:00', '2017-08-20 17:42:00', 1, 'План'),
(31, 1, 1, 2, 127, 30, '2017-08-20 16:52:00', '2017-08-20 17:42:00', 1, 'План'),
(32, 1, 1, 3, 128, 30, '2017-08-20 16:52:00', '2017-08-20 17:42:00', 1, 'План'),
(33, 1, 1, 4, 129, 30, '2017-08-20 16:52:00', '2017-08-20 17:42:00', 1, 'План'),
(34, 1, 1, 5, 130, 30, '2017-08-20 16:52:00', '2017-08-20 17:42:00', 1, 'План'),
(35, 1, 1, 6, 131, 30, '2017-08-20 16:52:00', '2017-08-20 17:42:00', 1, 'План'),
(36, 1, 1, 7, 132, 30, '2017-08-20 16:52:00', '2017-08-20 17:42:00', 1, 'План'),
(37, 1, 1, 8, 133, 30, '2017-08-20 16:52:00', '2017-08-20 17:42:00', 1, 'План'),
(38, 1, 1, 1, 134, 30, '2017-08-20 17:42:00', '2017-08-20 18:32:00', 1, 'План'),
(39, 1, 1, 2, 135, 30, '2017-08-20 17:42:00', '2017-08-20 18:32:00', 1, 'План'),
(40, 1, 1, 3, 136, 30, '2017-08-20 17:42:00', '2017-08-20 18:32:00', 1, 'План'),
(41, 1, 1, 4, 137, 30, '2017-08-20 17:42:00', '2017-08-20 18:32:00', 1, 'План'),
(42, 1, 1, 5, 138, 30, '2017-08-20 17:42:00', '2017-08-20 18:32:00', 1, 'План'),
(43, 1, 1, 6, 138, 30, '2017-08-20 17:42:00', '2017-08-20 18:32:00', 1, 'План'),
(44, 1, 1, 7, 140, 30, '2017-08-20 17:42:00', '2017-08-20 18:32:00', 1, 'План'),
(45, 1, 1, 8, 141, 30, '2017-08-20 17:42:00', '2017-08-20 18:32:00', 1, 'План'),
(46, 1, 1, 1, 142, 30, '2017-08-20 18:32:00', '2017-08-20 19:22:00', 1, 'План'),
(47, 1, 1, 2, 143, 30, '2017-08-20 18:32:00', '2017-08-20 19:22:00', 1, 'План'),
(48, 1, 1, 3, 144, 30, '2017-08-20 18:32:00', '2017-08-20 19:22:00', 1, 'План'),
(49, 1, 1, 4, 145, 30, '2017-08-20 18:32:00', '2017-08-20 19:22:00', 1, 'План'),
(50, 1, 1, 5, 146, 30, '2017-08-20 18:32:00', '2017-08-20 19:22:00', 1, 'План'),
(51, 1, 1, 6, 147, 30, '2017-08-20 18:32:00', '2017-08-20 19:22:00', 1, 'План'),
(52, 1, 1, 7, 148, 30, '2017-08-20 18:32:00', '2017-08-20 19:22:00', 1, 'План'),
(53, 1, 1, 8, 149, 30, '2017-08-20 18:32:00', '2017-08-20 19:22:00', 1, 'План'),
(54, 1, 1, 1, 150, 30, '2017-08-20 19:22:00', '2017-08-20 20:12:00', 1, 'План'),
(55, 1, 1, 2, 151, 30, '2017-08-20 19:22:00', '2017-08-20 20:12:00', 1, 'План'),
(56, 2, NULL, 3, 152, 30, NULL, NULL, 2, 'План'),
(57, 2, NULL, 4, 153, 30, NULL, NULL, 2, 'План'),
(58, 2, NULL, 5, NULL, 30, NULL, NULL, 2, 'План'),
(59, 2, NULL, 6, NULL, 30, NULL, NULL, 2, 'План'),
(60, 2, NULL, 7, NULL, 30, NULL, NULL, 2, 'План'),
(61, 2, NULL, 8, NULL, 30, NULL, NULL, 2, 'План'),
(62, 2, NULL, 1, NULL, 30, NULL, NULL, 2, 'План'),
(63, 2, NULL, 2, NULL, 30, NULL, NULL, 2, 'План');

-- 
-- Вывод данных для таблицы shipment
--

-- Таблица terminal.shipment не содержит данных

-- 
-- Вывод данных для таблицы dependencyadmission
--

-- Таблица terminal.dependencyadmission не содержит данных

-- 
-- Восстановить предыдущий режим SQL (SQL mode)
-- 
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;

-- 
-- Включение внешних ключей
-- 
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;