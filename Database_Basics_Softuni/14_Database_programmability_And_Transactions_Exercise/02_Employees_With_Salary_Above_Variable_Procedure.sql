DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above(min_salary DOUBLE(19,4))
BEGIN
	SELECT `first_name`, `last_name` FROM `employees`
    WHERE `salary` >= min_salary
    ORDER BY `first_name`, `last_name`, `employee_id`;
END $$
DELIMITER ;

CALL usp_get_employees_salary_above(45000);

DROP PROCEDURE IF EXISTS usp_get_employees_salary_above;