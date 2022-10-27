CREATE TABLE `teachers` (
	`teacher_id` INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT ,
    `name` VARCHAR(30) NOT NULL,
    `manager_id` INT UNSIGNED DEFAULT NULL
)AUTO_INCREMENT=101;
INSERT INTO `teachers` (`name`,`manager_id`)
VALUES
('John', NULL),
('Maya', 106),
('Silvia', 106),
('Ted', 105),
('Mark', 101),
('Greta', 101);
ALTER TABLE `teachers`
	ADD CONSTRAINT PRIMARY KEY (`teacher_id`),
    ADD CONSTRAINT `fk_teacher_manager_id`
		FOREIGN KEY (`manager_id`)
        REFERENCES `teachers`(`teacher_id`);