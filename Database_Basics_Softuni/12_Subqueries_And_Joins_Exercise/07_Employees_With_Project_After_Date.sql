SELECT 
    ep.`employee_id`, e.`first_name`, p.`name`
FROM
    `employees_projects` AS ep
        JOIN
    `employees` AS e ON e.`employee_id` = ep.`employee_id`
		JOIN 
	`projects` AS p ON p.`project_id` = ep.`project_id`
    WHERE DATE(p.`start_date`) > '2002-08-13'
    AND p.`end_date` IS NULL
ORDER BY e.`first_name`,p.`name`
LIMIT 5;