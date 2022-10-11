ALTER TABLE `products`
ADD INDEX `fk_categories_ind` (`category_id` ASC) INVISIBLE;
ALTER TABLE `products`
ADD CONSTRAINT `fk_category_id`
	FOREIGN KEY (`category_id`)
    REFERENCES `categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE;