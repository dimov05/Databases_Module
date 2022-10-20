SELECT `name` as `game`,
CASE 
WHEN HOUR(`start`) BETWEEN 0 and 11 THEN 'Morning'
WHEN HOUR(`start`) BETWEEN 12 and 17 THEN 'Afternoon'
ELSE 'Evening'
END AS `Part of the Day`,
CASE
WHEN `duration` <= 3 THEN 'Extra Short'
WHEN `duration` BETWEEN 4 and 6 THEN 'Short'
WHEN `duration` BETWEEN 7 and 10 THEN 'Long'
ELSE 'Extra Long'
END AS `Duration`
FROM `games`
ORDER BY `name`;
