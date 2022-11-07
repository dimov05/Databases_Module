DELIMITER $$
CREATE FUNCTION `udf_customer_products_count`(`name` VARCHAR(30))
RETURNS INT
BEGIN 
 DECLARE products_count INT;
 SET products_count := (
	SELECT COUNT(op.`product_id`) FROM orders_products as op
    JOIN `orders` as o ON o.id = op.order_id
    JOIN `customers` as c ON c.id = o.customer_id
    WHERE c.first_name = `name`);
	RETURN products_count;
END $$
DELIMITER ;


SELECT c.first_name,c.last_name, udf_customer_products_count('Shirley') as `total_products` FROM customers c
WHERE c.first_name = 'Shirley';

SELECT COUNT(op.`product_id`) FROM orders_products as op
    JOIN `orders` as o ON o.id = op.order_id
    JOIN `customers` as c ON c.id = o.customer_id
    WHERE c.first_name = 'Shirley';