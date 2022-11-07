DELETE c FROM `customers` AS c
LEFT JOIN `orders` as o ON o.customer_id = c.id
WHERE o.customer_id IS NULL;