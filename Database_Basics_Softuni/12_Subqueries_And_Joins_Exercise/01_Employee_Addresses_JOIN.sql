SELECT e.`employee_id`,e.`job_title`,a.`address_id`,a.`address_text`
FROM `employees` as e
JOIN `addresses` as a
WHERE a.`address_id` = e.`address_id`
ORDER BY a.`address_id`
LIMIT 5;