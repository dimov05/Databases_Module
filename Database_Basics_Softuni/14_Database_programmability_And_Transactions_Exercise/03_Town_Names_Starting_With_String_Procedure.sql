DELIMITER $$
CREATE PROCEDURE usp_get_towns_starting_with(start_string VARCHAR(30))
BEGIN
	SELECT `name` AS `town_name` FROM `towns`
    WHERE `name` LIKE concat(`start_string`,'%')
    ORDER BY `name`;
END $$
DELIMITER ;

CALL usp_get_towns_starting_with('b');