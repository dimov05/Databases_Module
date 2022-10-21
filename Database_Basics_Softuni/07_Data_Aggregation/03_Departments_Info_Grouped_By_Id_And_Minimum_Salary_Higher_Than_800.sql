SELECT `department_id`, ROUND(MIN(`salary`),2) AS `Average Salary`
FROM `employees`
GROUP BY `department_id` 
HAVING MIN(`salary`) > 800
ORDER BY `department_id`;