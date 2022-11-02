DELIMITER $$
CREATE PROCEDURE usp_get_employees_from_town(town_name VARCHAR(30))
BEGIN
	SELECT `first_name`, `last_name` FROM `employees` AS e
    JOIN `addresses` as a ON e.`address_id` = a.`address_id`
    JOIN `towns` as t ON a.`town_id` = t.`town_id`
    WHERE t.`name` = `town_name`
    ORDER BY `first_name`,`last_name`, `employee_id`;
END $$
DELIMITER ;

CALL usp_get_employees_from_town('Sofia');