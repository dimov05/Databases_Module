CREATE TABLE `mountains` (
`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`name` VARCHAR(30) NOT NULL
);
CREATE TABLE `peaks` (
    `id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `name` VARCHAR(30) NOT NULL,
    `mountain_id` INT,
    CONSTRAINT fk_peaks_mountains FOREIGN KEY (`mountain_id`)
        REFERENCES `mountains` (`id`)
        ON UPDATE CASCADE 
        ON DELETE CASCADE
);
