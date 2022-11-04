SELECT c.`name`, COUNT(m.title) as movies_count
FROM movies as m
JOIN countries as c ON c.id = m.country_id
GROUP BY m.country_id
HAVING movies_count >= 7
ORDER BY c.`name` DESC;