SELECT COUNT(`salary`) FROM `employees`
WHERE ISNULL(`manager_id`);