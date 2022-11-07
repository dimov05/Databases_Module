SELECT COUNT(c.id)  as `items_count`,
c.`name`, SUM(p.`quantity_in_stock`)  as `total_quantity` FROM `categories` as c
JOIN `products` as p ON p.category_id = c.id
GROUP BY c.id
ORDER BY `items_count` desc, `total_quantity`
LIMIT 5;