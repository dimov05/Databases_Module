SELECT 
    CONCAT(a.`first_name`, ' ', a.`last_name`) AS `full_name`,
    CONCAT(REVERSE(a.`last_name`),
            (LENGTH(a.`last_name`)),
            '@cast.com') AS `email`,
    (2022 - YEAR(a.`birthdate`)) AS `age`,
    a.`height`
FROM
    actors AS a
        LEFT JOIN
    movies_actors AS m ON m.actor_id = a.id
WHERE
    m.movie_id IS NULL
ORDER BY height;