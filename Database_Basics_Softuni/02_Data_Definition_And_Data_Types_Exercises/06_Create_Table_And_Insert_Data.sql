CREATE TABLE `people` (
	`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(200) NOT NULL,
    `picture` BLOB,
    `height` DOUBLE,
	`weight` DOUBLE,
    `gender` CHAR(1) NOT NULL,
    `birthdate` DATE NOT NULL,
    `biography` TEXT
);
INSERT INTO `people` (`id`,`name`, `picture`,`height`,`weight`,`gender`,`birthdate`,`biography`)
VALUES
(1,'Georgi Georgiev',NULL,1.76,76.2,'m','1999-05-02','Text here and there'),
(2,'Petur Petrov',NULL,1.80,66.2,'m','1994-01-03',null),
(3,'Ivan Ivanov',NULL,1.79,86.2,'m','1979-09-04',null),
(4,'Stefka Georgieva',NULL,1.77,46.2,'f','1949-02-05',null),
(5,'Vanya Petrova',NULL,1.64,51.2,'f','1989-08-06',null);