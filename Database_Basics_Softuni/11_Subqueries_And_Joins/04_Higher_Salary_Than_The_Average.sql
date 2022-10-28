SELECT 
COUNT(e.`employee_id`) AS `count`
FROM `employees` AS e
WHERE e.`salary` > (SELECT AVG(`salary`) FROM `employees`);

