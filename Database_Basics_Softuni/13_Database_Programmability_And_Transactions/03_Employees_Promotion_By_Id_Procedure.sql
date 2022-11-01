DELIMITER //
CREATE PROCEDURE usp_raise_salary_by_id(id INT)
BEGIN
	START TRANSACTION;
	IF((SELECT * FROM employees WHERE employee_id = id) = 0)
    THEN ROLLBACK;
    ELSE
    UPDATE `employees` AS e 
SET 
    e.`salary` = e.`salary` * 1.05
WHERE
    e.`employee_id` = id;
    END IF;
END//
DELIMITER ;

-- Test
CALL usp_raise_salary_by_id(178);
SELECT `salary` FROM employees
WHERE `employee_id` = 178;
