CREATE FUNCTION ufn_get_salary_level(salary DOUBLE(19,4))
RETURNS VARCHAR(10)
RETURN(
		CASE
			WHEN salary < 30000 THEN 'Low'
            WHEN salary <= 50000 THEN 'Average'
            ELSE 'High'
	END
);

SELECT ufn_get_salary_level(13500);
SELECT ufn_get_salary_level(43300);
SELECT ufn_get_salary_level(125500);