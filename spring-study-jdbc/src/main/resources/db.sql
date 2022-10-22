CREATE TABLE IF NOT EXISTS `account`
(
    `id`      INT UNSIGNED       NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(40) UNIQUE NOT NULL,
    `balance` INT UNSIGNED       NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO account(`name`, `balance`)
VALUES ('javaboy', 1000);
INSERT INTO account(`name`, `balance`)
VALUES ('itboyhub', 1000);