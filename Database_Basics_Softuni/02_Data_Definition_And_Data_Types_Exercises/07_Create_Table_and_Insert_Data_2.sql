CREATE TABLE `users` (
	`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(30) NOT NULL,
    `password` VARCHAR(26) NOT NULL,
    `profile_picture` BLOB,
    `last_login_time` TIME,
    `is_deleted` BOOLEAN
);
INSERT INTO `users` (`id`, `username`, `password`, `profile_picture`,`last_login_time`,`is_deleted`)
VALUES
(1,'GeorgiGeorgiev','123456789',null,'20:15',true),
(2,'PeturPetrov','123456789',null,'19:44',false),
(3,'IvanIvanov','123456789',null,'19:12',true),
(4,'StefkaGeorgieva','123456789',null,'11:22',false),
(5,'VanyaPetrova','123456789',null,'15:44',true);