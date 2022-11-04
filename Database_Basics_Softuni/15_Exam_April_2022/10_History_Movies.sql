DELIMITER $$
CREATE FUNCTION udf_actor_history_movies_count(full_name VARCHAR(50))
RETURNS INT DETERMINISTIC
BEGIN 
	DECLARE movies_count INT;
    SET movies_count := (
	SELECT COUNT(g.name) as history_movies FROM actors as a 
    JOIN movies_actors as ma ON a.id = ma.actor_id
    JOIN genres_movies as gm ON ma.movie_id = gm.movie_id
    JOIN genres g on g.id = gm.genre_id
    WHERE CONCAT(a.`first_name`,' ',a.`last_name`) = full_name
		AND g.name = 'History'
        GROUP BY g.name);
	RETURN movies_count;
END $$
DELIMITER ;

SELECT udf_actor_history_movies_count('Stephan Lundberg');