SELECT CONCAT(c.`first_name`,' ',c.`last_name`) as `full_name`,
c.`address`, o.`order_datetime` FROM `customers` as c
JOIN `orders` as o ON o.customer_id = c.id
WHERE YEAR(o.order_datetime) <= 2018
ORDER BY `full_name` desc;