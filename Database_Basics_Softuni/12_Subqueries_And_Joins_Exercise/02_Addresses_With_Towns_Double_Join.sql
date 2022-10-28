SELECT 
    e.`first_name`,
    e.`last_name`,
    t.`name` AS `town`,
    a.`address_text`
FROM
    `employees` AS e
        JOIN
    `addresses` AS a ON a.`address_id` = e.`address_id`
        JOIN
    `towns` AS t ON t.`town_id` = a.`town_id`
ORDER BY e.`first_name` , e.`last_name`
LIMIT 5;