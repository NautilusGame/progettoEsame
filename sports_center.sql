-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Feb 06, 2017 alle 22:12
-- Versione del server: 5.7.14
-- Versione PHP: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sports_center`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `activity`
--

CREATE TABLE `activity` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(100) NOT NULL,
  `cost` float NOT NULL,
  `room_id` int(11) NOT NULL,
  `level_id` int(11) NOT NULL,
  `center_manager_email` varchar(45) NOT NULL,
  `type_room_id` int(11) NOT NULL,
  `image_path` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `activity_type`
--

CREATE TABLE `activity_type` (
  `id` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  `center_manager_email` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `center_manager`
--

CREATE TABLE `center_manager` (
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `phone_number` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `center_manager`
--

INSERT INTO `center_manager` (`email`, `password`, `name`, `surname`, `phone_number`) VALUES
('ciao', 'ciao', 'ciao', 'ciao', '6165');

-- --------------------------------------------------------

--
-- Struttura della tabella `csrc`
--

CREATE TABLE `csrc` (
  `id` int(11) NOT NULL,
  `path` varchar(200) NOT NULL,
  `deadline` date NOT NULL,
  `member_email` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `event`
--

CREATE TABLE `event` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `start_date` date NOT NULL,
  `finish_date` date NOT NULL,
  `cost` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  `trainer_email` varchar(45) NOT NULL,
  `free` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `level`
--

CREATE TABLE `level` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL DEFAULT 'easy',
  `description` varchar(100) NOT NULL,
  `center_manager_email` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `member`
--

CREATE TABLE `member` (
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `birthday` date NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `center_manager_email` varchar(45) DEFAULT NULL,
  `confirmed` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `member`
--

INSERT INTO `member` (`name`, `surname`, `birthday`, `email`, `password`, `center_manager_email`, `confirmed`) VALUES
('c', 'c', '2017-02-01', 'c', '4a8a08f09d37b73795649038408b5f33', NULL, 0),
('h', 'h', '2017-02-08', 'h', '2510c39011c5be704182423e3a695e91', NULL, 0),
('benny', 'taccardi', '1996-04-10', 'mia@gmail.com', 'sdfadfdfdsf', 'ciao', 0),
('benny', 'taccardi', '1996-04-10', 'mia@gmail.comy', 'sdfadfdfdsf', 'ciao', 0),
('o', 'o', '2017-02-08', 'o', 'd95679752134a2d9eb61dbd7b91c4bcc', NULL, 0),
('q', 'q', '2017-02-08', 'q', '7694f4a66316e53c8cdd9d9954bd611d', NULL, 0),
('q', 'q', '2017-02-08', 'qtyu', '7694f4a66316e53c8cdd9d9954bd611d', NULL, 0),
('t', 't', '2017-02-08', 't', 't', 'ciao', 0),
('t', 't', '2017-02-08', 'tsfsfw', 't', 'ciao', 0),
('t', 't', '2017-02-08', 'tsfsfw4545', 't', 'ciao', 0),
('w', 'w', '2017-02-27', 'w', 'w', 'ciao', 0),
('w', 'w', '2017-02-27', 'w7587', 'w', 'ciao', 0),
('w', 'w', '2017-02-27', 'wdfgdg', 'w', 'ciao', 0),
('w', 'w', '2017-02-27', 'wdfgdg7358375', 'w', 'ciao', 0),
('w', 'w', '2017-02-27', 'wdfgdg7358375234', 'w', 'ciao', 0),
('w', 'w', '2017-02-27', 'wdfgdgyj', 'w', 'ciao', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  `number` varchar(45) DEFAULT NULL,
  `member_email` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `registration`
--

CREATE TABLE `registration` (
  `id` int(11) NOT NULL,
  `deadline` date DEFAULT NULL,
  `date` date DEFAULT NULL,
  `member_email` varchar(45) NOT NULL,
  `schedule_id` int(11) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `review`
--

CREATE TABLE `review` (
  `activity_id` int(11) NOT NULL,
  `member_email` varchar(45) NOT NULL,
  `text` varchar(300) NOT NULL,
  `rate` int(11) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `room`
--

CREATE TABLE `room` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `capability` int(11) NOT NULL,
  `center_manager_email` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `schedule`
--

CREATE TABLE `schedule` (
  `id` int(11) NOT NULL,
  `day` varchar(45) NOT NULL,
  `time` int(11) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `trainer_email` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `temp_alter_registration`
--

CREATE TABLE `temp_alter_registration` (
  `id` int(11) NOT NULL,
  `deadline` date NOT NULL,
  `date` date NOT NULL,
  `registration_id` int(11) NOT NULL,
  `center_manager_email` varchar(45) NOT NULL,
  `member_email` varchar(45) NOT NULL,
  `confirmed` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `trainer`
--

CREATE TABLE `trainer` (
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  `center_manager_email` varchar(45) DEFAULT NULL,
  `confirmed` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `trainer`
--

INSERT INTO `trainer` (`email`, `password`, `name`, `surname`, `phone_number`, `center_manager_email`, `confirmed`) VALUES
('e', 'e', 'e', 'e', 'e', NULL, 0);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `activity`
--
ALTER TABLE `activity`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_activity_room1_idx` (`room_id`),
  ADD KEY `fk_activity_level1_idx` (`level_id`),
  ADD KEY `fk_activity_center_manager1_idx` (`center_manager_email`),
  ADD KEY `fk_activity_type_room1_idx` (`type_room_id`);

--
-- Indici per le tabelle `activity_type`
--
ALTER TABLE `activity_type`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_activity_type_center_manager_idx` (`center_manager_email`);

--
-- Indici per le tabelle `center_manager`
--
ALTER TABLE `center_manager`
  ADD PRIMARY KEY (`email`);

--
-- Indici per le tabelle `csrc`
--
ALTER TABLE `csrc`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_csrc_member1_idx` (`member_email`);

--
-- Indici per le tabelle `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_event_trainer1_idx` (`trainer_email`);

--
-- Indici per le tabelle `level`
--
ALTER TABLE `level`
  ADD PRIMARY KEY (`id`,`center_manager_email`),
  ADD KEY `fk_level_center_manager1_idx` (`center_manager_email`);

--
-- Indici per le tabelle `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`email`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`),
  ADD KEY `fk_member_center_manager1_idx` (`center_manager_email`);

--
-- Indici per le tabelle `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`,`member_email`),
  ADD UNIQUE KEY `number_UNIQUE` (`number`),
  ADD KEY `fk_payment_member1_idx` (`member_email`);

--
-- Indici per le tabelle `registration`
--
ALTER TABLE `registration`
  ADD PRIMARY KEY (`id`,`schedule_id`,`activity_id`),
  ADD KEY `fk_registration_member1_idx` (`member_email`),
  ADD KEY `fk_registration_schedule1_idx` (`schedule_id`),
  ADD KEY `fk_registration_activity1_idx` (`activity_id`),
  ADD KEY `fk_registration_event1_idx` (`event_id`);

--
-- Indici per le tabelle `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`activity_id`,`member_email`),
  ADD KEY `fk_activity_has_member_member1_idx` (`member_email`),
  ADD KEY `fk_activity_has_member_activity1_idx` (`activity_id`);

--
-- Indici per le tabelle `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`,`center_manager_email`),
  ADD KEY `fk_room_center_manager1_idx` (`center_manager_email`);

--
-- Indici per le tabelle `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`id`,`activity_id`,`trainer_email`),
  ADD KEY `fk_schedule_activity1_idx` (`activity_id`),
  ADD KEY `fk_schedule_trainer1_idx` (`trainer_email`);

--
-- Indici per le tabelle `temp_alter_registration`
--
ALTER TABLE `temp_alter_registration`
  ADD PRIMARY KEY (`id`,`registration_id`,`center_manager_email`),
  ADD KEY `fk_temp_alert_registration_registration1_idx` (`registration_id`),
  ADD KEY `fk_temp_alert_registration_center_manager1_idx` (`center_manager_email`),
  ADD KEY `fk_temp_alert_registration_member1_idx` (`member_email`);

--
-- Indici per le tabelle `trainer`
--
ALTER TABLE `trainer`
  ADD PRIMARY KEY (`email`),
  ADD KEY `fk_trainer_center_manager1_idx` (`center_manager_email`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `activity`
--
ALTER TABLE `activity`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT per la tabella `activity_type`
--
ALTER TABLE `activity_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT per la tabella `csrc`
--
ALTER TABLE `csrc`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT per la tabella `event`
--
ALTER TABLE `event`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT per la tabella `level`
--
ALTER TABLE `level`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT per la tabella `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT per la tabella `registration`
--
ALTER TABLE `registration`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT per la tabella `review`
--
ALTER TABLE `review`
  MODIFY `activity_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT per la tabella `room`
--
ALTER TABLE `room`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT per la tabella `schedule`
--
ALTER TABLE `schedule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT per la tabella `temp_alter_registration`
--
ALTER TABLE `temp_alter_registration`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `activity`
--
ALTER TABLE `activity`
  ADD CONSTRAINT `fk_activity_center_manager1` FOREIGN KEY (`center_manager_email`) REFERENCES `center_manager` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_activity_level1` FOREIGN KEY (`level_id`) REFERENCES `level` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_activity_room1` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_activity_type_room1` FOREIGN KEY (`type_room_id`) REFERENCES `mydb`.`activity_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `activity_type`
--
ALTER TABLE `activity_type`
  ADD CONSTRAINT `fk_activity_type_center_manager` FOREIGN KEY (`center_manager_email`) REFERENCES `center_manager` (`email`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Limiti per la tabella `csrc`
--
ALTER TABLE `csrc`
  ADD CONSTRAINT `fk_csrc_member1` FOREIGN KEY (`member_email`) REFERENCES `member` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `fk_event_trainer1` FOREIGN KEY (`trainer_email`) REFERENCES `trainer` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `level`
--
ALTER TABLE `level`
  ADD CONSTRAINT `fk_level_center_manager1` FOREIGN KEY (`center_manager_email`) REFERENCES `center_manager` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `member`
--
ALTER TABLE `member`
  ADD CONSTRAINT `fk_member_center_manager1` FOREIGN KEY (`center_manager_email`) REFERENCES `center_manager` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `fk_payment_member1` FOREIGN KEY (`member_email`) REFERENCES `member` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `registration`
--
ALTER TABLE `registration`
  ADD CONSTRAINT `fk_registration_activity1` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_registration_event1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_registration_member1` FOREIGN KEY (`member_email`) REFERENCES `member` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_registration_schedule1` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `fk_activity_has_member_activity1` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_activity_has_member_member1` FOREIGN KEY (`member_email`) REFERENCES `member` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `fk_room_center_manager1` FOREIGN KEY (`center_manager_email`) REFERENCES `center_manager` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `fk_schedule_activity1` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_schedule_trainer1` FOREIGN KEY (`trainer_email`) REFERENCES `trainer` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `temp_alter_registration`
--
ALTER TABLE `temp_alter_registration`
  ADD CONSTRAINT `fk_temp_alert_registration_center_manager1` FOREIGN KEY (`center_manager_email`) REFERENCES `center_manager` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_temp_alert_registration_member1` FOREIGN KEY (`member_email`) REFERENCES `member` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_temp_alert_registration_registration1` FOREIGN KEY (`registration_id`) REFERENCES `registration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `trainer`
--
ALTER TABLE `trainer`
  ADD CONSTRAINT `fk_trainer_center_manager1` FOREIGN KEY (`center_manager_email`) REFERENCES `center_manager` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
