SELECT `id`, `content`, `rating`, `picture_url`, `published_at` FROM reviews
WHERE LEFT(`content`,2) = 'My'
	AND length(`content`) > 61
ORDER BY `rating` desc;