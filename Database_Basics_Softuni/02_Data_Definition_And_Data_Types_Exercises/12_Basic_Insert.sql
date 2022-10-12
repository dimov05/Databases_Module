USE `soft_uni`;
CREATE TABLE `towns`(
`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`name` VARCHAR(45)
);
CREATE TABLE `addresses`(
`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`address_text` VARCHAR(45),
`town_id` INT,
CONSTRAINT fk_addresses_towns
FOREIGN KEY (`town_id`)
REFERENCES `towns`(`id`)
);
CREATE TABLE `departments` (
`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`name` VARCHAR(45)
);
CREATE TABLE `employees` (
`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`first_name` VARCHAR(30) NOT NULL,
`middle_name` VARCHAR(30),
`last_name` VARCHAR(30) ,
`job_title` VARCHAR(30) NOT NULL,
`department_id` INT,
CONSTRAINT fk_employees_departments
FOREIGN KEY (`department_id`)
REFERENCES `departments`(`id`),
`hire_date` DATE,
`salary` DECIMAL,
`address_id` INT,
CONSTRAINT fk_employees_addresses
FOREIGN KEY (`address_id`)
REFERENCES `addresses`(`id`)
);
INSERT INTO `towns`
VALUES
(1,'Sofia'),
(2,'Plovdiv'),
(3,'Varna'),
(4,'Burgas');
INSERT INTO `departments`
VALUES
(1,'Engineering'),
(2,'Sales'),
(3,'Marketing'),
(4,'Software Development'),
(5,'Quality Assurance');
INSERT INTO `employees` (`id`,`first_name`,`middle_name`,`last_name`,`job_title`,`department_id`,`hire_date`,`salary`)
VALUES
(1,'Ivan','Ivanov','Ivanov','.NET Developer',4,'2013-02-01',3500.00),
(2,'Petar','Petrov','Petrov','Senior Engineer',1,'2004-03-02',4000.00),
(3,'Maria','Petrova','Ivanova','Intern',5,'2016-08-28',525.25),
(4,'Georgi','Terziev','Ivanov','CEO',2,'2007-12-09',3000.00),
(5,'Peter','Pan','Pan','Intern',3,'2016-08-28',599.88);

SELECT * FROM towns
ORDER BY `name`;
SELECT * FROM departments
ORDER BY `name`;
SELECT * FROM employees
ORDER BY `salary` DESC;