SELECT
	SUM(`host_wizard`.`deposit_amount` - `guest_wizard`.`deposit_amount`) AS `sum_difference`
    FROM `wizzard_deposits` AS `host_wizard`,
		`wizzard_deposits` AS `guest_wizard`
WHERE `guest_wizard`.`id` - `host_wizard`.`id` = 1;
