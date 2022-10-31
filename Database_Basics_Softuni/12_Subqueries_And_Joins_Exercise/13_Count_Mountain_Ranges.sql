SELECT 
    mc.`country_code`,
    COUNT(mc.`mountain_id`) AS `mountain_range`
FROM
    `mountains_countries` AS mc
WHERE
    mc.country_code IN ('BG' , 'RU', 'US')
GROUP BY mc.country_code
ORDER BY `mountain_range` DESC;
