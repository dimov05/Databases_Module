CREATE SCHEMA `Movies`;
CREATE TABLE `directors` (
	`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `director_name` VARCHAR(30) NOT NULL,
    `notes` TEXT 
);
CREATE TABLE `genres` (
	`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `genre_name` VARCHAR(30) NOT NULL,
    `notes` TEXT 
);
CREATE TABLE `categories` (
	`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `category_name` VARCHAR(30) NOT NULL,
    `notes` TEXT 
);
CREATE TABLE `movies` (
	`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(30) NOT NULL,
    `director_id` INT NOT NULL,
    `copyright_year` YEAR NOT NULL,
    `length` DOUBLE NOT NULL,
    `genre_id` INT NOT NULL,
    `category_id`INT NOT NULL,
    `rating` INT,
    `notes` TEXT
);
INSERT INTO `directors` (`id`, `director_name`, `notes`)
VALUES
(1,'Georgi Georgiev',null),
(2,'Georgi Petrov',null),
(3,'Georgi Ivanov',null),
(4,'Georgi Stoqnov',null),
(5,'Georgi Kalaydzhiev',null);
INSERT INTO `genres` (`id`, `genre_name`, `notes`)
VALUES
(1,'Crimi',null),
(2,'Romance',null),
(3,'Triller',null),
(4,'Comedy',null),
(5,'Drama',null);
INSERT INTO `categories` (`id`, `category_name`, `notes`)
VALUES
(1,'First',null),
(2,'Second',null),
(3,'Third',null),
(4,'Fourth',null),
(5,'Fifth',null);
INSERT INTO `movies` (`id`, `title`,`director_id`,`copyright_year`,`length`,`genre_id`, `category_id`, `rating`, `notes`)
VALUES
(1,'Wrong Turn 1',5,'2005',55,4,1,5,null),
(2,'Wrong Turn 2',3,'2009',66,5,5,4,null),
(3,'Wrong Turn 3',4,'2003',77,2,2,7,null),
(4,'Wrong Turn 4',2,'2025',88,1,3,2,null),
(5,'Wrong Turn 5',1,'2015',99,3,4,5,null);