SELECT `town_id`, `name` FROM `towns`
WHERE SUBSTRING(`name`,1,1) IN ('M','K','B','E')
ORDER BY `name`;