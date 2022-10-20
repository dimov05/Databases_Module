SELECT `user_name`, SUBSTRING_INDEX(`email`,'@',-1) AS `Email Provider`
FROM `users`
ORDER BY `Email Provider`, `user_name`;