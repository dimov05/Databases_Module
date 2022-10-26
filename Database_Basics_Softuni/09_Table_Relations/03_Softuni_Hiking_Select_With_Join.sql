SELECT routes.`starting_point` AS `route_starting_point`,
 routes.`end_point` AS `route_ending_point`,
		routes.`leader_id`,
		concat(campers.`first_name`,' ', campers.`last_name`) AS `leader_name`
FROM `routes` JOIN `campers`
ON routes.`leader_id` = campers.`id`;