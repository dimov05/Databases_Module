CREATE FUNCTION ufn_is_word_comprised(set_of_letters VARCHAR(50), word VARCHAR(50))
RETURNS BIT
RETURN
		word REGEXP (concat('^[', set_of_letters, ']+$'));