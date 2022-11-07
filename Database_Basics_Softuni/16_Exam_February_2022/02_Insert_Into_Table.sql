INSERT INTO `reviews` (`content`,`rating`, `picture_url`, `published_at`)
(SELECT LEFT(`description`,15), (`price`/8), REVERSE(`name`), DATE('2010-10-10')
FROM products
WHERE `id` >= 5);