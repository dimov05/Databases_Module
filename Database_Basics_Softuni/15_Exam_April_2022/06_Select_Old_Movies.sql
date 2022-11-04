SELECT i.`id`,m.`title`,i.`runtime`,i.`budget`,i.`release_date`
FROM `movies_additional_info` as i
JOIN `movies` AS m ON m.movie_info_id = i.id
WHERE YEAR(release_date) BETWEEN 1996 AND 1999
ORDER BY i.`runtime`,i.`id`
LIMIT 20;