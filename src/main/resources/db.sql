create
database zeynep;
use
zeynep;



CREATE TABLE `costumer`
(
    `id`   int NOT NULL AUTO_INCREMENT,
    `name` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
)



CREATE TABLE `table`
(
    `id`       int NOT NULL AUTO_INCREMENT,
    `table_no` int NOT NULL,
    `size`     int DEFAULT NULL,
    PRIMARY KEY (`id`)
)


CREATE TABLE `menuitem`
(
    `id`     int          NOT NULL AUTO_INCREMENT,
    `name`   varchar(100) NOT NULL,
    `price`  double       NOT NULL DEFAULT '0',
    `typeid` int          NOT NULL,
    PRIMARY KEY (`id`)
)



CREATE TABLE `costumerorder`
(
    `costumerid` int NOT NULL,
    `orderid`    int NOT NULL
)

CREATE TABLE `order`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `tableid`    int      DEFAULT NULL,
    `costumerid` int      DEFAULT NULL,
    `statusid`   int      DEFAULT NULL,
    `time`       datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
)

CREATE TABLE `orderdetail`
(
    `menuitemid` int NOT NULL,
    `orderid`    int NOT NULL,
    `amount`     int NOT NULL
)

