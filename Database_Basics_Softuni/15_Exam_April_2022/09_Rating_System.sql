SELECT 
    m.`title`,
    (CASE
        WHEN i.rating <= 4 THEN 'poor'
        WHEN i.rating <= 7 THEN 'good'
        ELSE 'excellent'
    END) as `rating`,
    (CASE
        WHEN i.has_subtitles = 1 THEN 'english'
        ELSE '-'
    END) as `subtitles`,
    i.`budget` FROM movies as m
    JOIN `movies_additional_info` as i ON m.movie_info_id = i.id
    ORDER BY i.budget DESC;