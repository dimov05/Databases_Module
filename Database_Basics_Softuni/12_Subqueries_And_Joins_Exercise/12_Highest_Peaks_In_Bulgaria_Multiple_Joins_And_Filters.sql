SELECT 
    mc.`country_code`,
    m.`mountain_range`,
    p.`peak_name`,
    p.`elevation`
FROM
    `mountains_countries` AS mc
        JOIN
    `mountains` AS m ON m.`id` = mc.`mountain_id`
        JOIN
    `peaks` AS p ON p.`mountain_id` = mc.`mountain_id`
WHERE
    mc.`country_code` = 'BG'
        AND p.`elevation` > 2835
ORDER BY p.`elevation` DESC;