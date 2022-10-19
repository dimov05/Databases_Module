SELECT `first_name`, `last_name` FROM `employees`
WHERE INSTR(`last_name`,'ei')
ORDER BY `employee_id`;