SELECT e.`employee_id`,e.`first_name`
FROM `employees` as e
LEFT JOIN `employees_projects` as ep
	ON e.`employee_id` = ep.`employee_id`
    WHERE ep.`project_id` IS NULL
ORDER BY e.`employee_id` DESC
LIMIT 3;