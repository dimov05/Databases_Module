SELECT 
    ep.`employee_id`, e.`first_name`,
    IF(YEAR(p.`start_date`)>= 2005, NULL, p.`name`) as `project_name`
FROM
    `employees_projects` AS ep
        JOIN
    `employees` AS e ON e.`employee_id` = ep.`employee_id`
		LEFT JOIN 
	`projects` AS p ON p.`project_id` = ep.`project_id`
    
    WHERE ep.`employee_id` = 24
ORDER BY p.`name`;