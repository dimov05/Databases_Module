SELECT COUNT(`id`) AS `Appetizers count`
FROM `products`
WHERE `category_id` = 2
	AND `price` > 8;