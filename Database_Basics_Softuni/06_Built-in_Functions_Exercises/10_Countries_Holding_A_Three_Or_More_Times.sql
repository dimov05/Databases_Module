SELECT `country_name`, `iso_code` FROM `countries`
WHERE (CHAR_LENGTH(`country_name`) - CHAR_LENGTH(REPLACE(LOWER(`country_name`),'a',''))) >= 3
ORDER BY `iso_code`;