DROP DATABASE IF EXISTS `fsd`;
CREATE DATABASE `fsd` ;
USE `fsd`;

CREATE TABLE `users` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `username` varchar(30) NOT NULL,
                         `password` varchar(80) NOT NULL,
                         `age` int(8) DEFAULT NULL,
                         `registration_date` date DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6;