SELECT `town_id`, `name` FROM `towns`
WHERE SUBSTRING(`name`,1,1) NOT IN ('R','D','B')
ORDER BY `name`;