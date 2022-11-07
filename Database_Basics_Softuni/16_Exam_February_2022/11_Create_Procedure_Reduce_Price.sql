DELIMITER $$
CREATE PROCEDURE `udp_reduce_price`(category_name VARCHAR(50))
BEGIN
	UPDATE products as p
    JOIN `reviews` as r ON r.id = p.review_id
    JOIN `categories` as c ON c.`id` = p.category_id
    SET p.`price` = p.`price` * 0.7
    WHERE r.`rating` < 4
		AND c.`name` = category_name;
END $$
DELIMITER ;

USE udp_reduce_price ('Phones and tablets');