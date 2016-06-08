CREATE TABLE `product` (
	`id` INTEGER AUTO_INCREMENT,
	`name` VARCHAR(255),
	`price` DOUBLE,
	PRIMARY KEY (`id`)
);

CREATE TABLE `purchase` (
	`id` INTEGER AUTO_INCREMENT,
	`quantity` INTEGER,
	`purchasedate` BIGINT,
	`product` INTEGER,
	PRIMARY KEY (`id`)
);

ALTER TABLE `Purchase` ADD CONSTRAINT `Purchase_fk0` FOREIGN KEY (`product`) REFERENCES `Product`(`id`);
