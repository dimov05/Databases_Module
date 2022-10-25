SELECT `department_id`, 
CASE
	WHEN `department_id` = 1 THEN AVG(`salary`) + 5000
    ELSE AVG(`salary`)
    END AS `avg_salary`
FROM `employees`
WHERE `salary` > 30000 AND `manager_id` != 42
GROUP BY `department_id`
ORDER BY `department_id`;