DELETE m FROM countries AS m
	LEFT JOIN movies AS m2 ON m2.country_id = m.id
    WHERE m2.country_id IS NULL;