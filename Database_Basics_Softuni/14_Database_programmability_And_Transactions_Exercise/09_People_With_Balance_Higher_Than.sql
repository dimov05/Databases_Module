DELIMITER $$
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(balance DECIMAL(19,4))
BEGIN
	SELECT h.`first_name`,h.`last_name` FROM `account_holders` as h
    JOIN
    (SELECT a.id,a.account_holder_id, SUM(a.balance) AS `total_balance` FROM `accounts` as a
    GROUP BY (a.account_holder_id)
    HAVING `total_balance` > balance) as a ON h.id = a.account_holder_id
    ORDER BY a.id;
END $$
DELIMITER ;

CALL usp_get_holders_with_balance_higher_than(7000);