CREATE FUNCTION ufn_get_salary_level(salary DOUBLE(19,4))
RETURNS VARCHAR(10)
RETURN(
		CASE
			WHEN salary < 30000 THEN 'Low'
            WHEN salary <= 50000 THEN 'Average'
            ELSE 'High'
	END
);
DELIMITER $$
CREATE PROCEDURE usp_get_employees_by_salary_level(salary_level VARCHAR(30))
BEGIN
	SELECT `first_name`, `last_name` FROM employees AS e
    WHERE ufn_get_salary_level(e.`salary`) = salary_level
    ORDER BY `first_name` DESC,`last_name` DESC;
END $$
DELIMITER ;

CALL usp_get_employees_by_salary_level('High');