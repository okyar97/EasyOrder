create database `201735038`;



create table `201735038`.costumer
(
    id   int auto_increment
        primary key,
    name varchar(50) null
);

INSERT INTO `201735038`.costumer (id, name)
VALUES (1, 'zeynep');
INSERT INTO `201735038`.costumer (id, name)
VALUES (2, 'aylin');
INSERT INTO `201735038`.costumer (id, name)
VALUES (3, 'ali');
INSERT INTO `201735038`.costumer (id, name)
VALUES (4, 'selin');

create table `201735038`.costumerorder
(
    costumerid int not null,
    orderid    int not null
);

create table `201735038`.menu_group
(
    id        int         not null
        primary key,
    groupname varchar(25) not null
);

INSERT INTO `201735038`.menu_group (id, groupname)
VALUES (1, 'ana yemek');
INSERT INTO `201735038`.menu_group (id, groupname)
VALUES (2, 'içecek');

create table `201735038`.menuitem
(
    id     int auto_increment
        primary key,
    name   varchar(100)     not null,
    price  double default 0 not null,
    typeid int              not null
);

INSERT INTO `201735038`.menuitem (id, name, price, typeid)
VALUES (1, 'hamburger', 30, 1);
INSERT INTO `201735038`.menuitem (id, name, price, typeid)
VALUES (2, 'makarna', 20, 1);
INSERT INTO `201735038`.menuitem (id, name, price, typeid)
VALUES (3, 'pizza', 25, 1);
INSERT INTO `201735038`.menuitem (id, name, price, typeid)
VALUES (4, 'sprite', 9.5, 2);
INSERT INTO `201735038`.menuitem (id, name, price, typeid)
VALUES (5, 'ayran', 7.25, 2);
INSERT INTO `201735038`.menuitem (id, name, price, typeid)
VALUES (6, 'kola', 10.5, 2);

create table `201735038`.`order`
(
    id         int auto_increment
        primary key,
    tableid    int null,
    costumerid int null,
    statusid   int null,
    time       datetime null
);

INSERT INTO `201735038`.`order` (id, tableid, costumerid, statusid, time)
VALUES (1, 1, 1, null, '2021-01-12 00:00:00');
INSERT INTO `201735038`.`order` (id, tableid, costumerid, statusid, time)
VALUES (2, 3, 4, null, '2021-07-12 21:09:13');
INSERT INTO `201735038`.`order` (id, tableid, costumerid, statusid, time)
VALUES (3, 3, 2, null, '2021-11-02 21:09:42');
INSERT INTO `201735038`.`order` (id, tableid, costumerid, statusid, time)
VALUES (4, 4, 2, null, '2021-03-28 21:10:05');

create table `201735038`.orderdetail
(
    menuitemid int not null,
    orderid    int not null,
    amount     int not null
);

INSERT INTO `201735038`.orderdetail (menuitemid, orderid, amount)
VALUES (1, 1, 2);
INSERT INTO `201735038`.orderdetail (menuitemid, orderid, amount)
VALUES (2, 2, 3);

create table `201735038`.`table`
(
    id       int auto_increment
        primary key,
    table_no int not null,
    size     int null
);

INSERT INTO `201735038`.`table` (id, table_no, size)
VALUES (2, 5, 10);
INSERT INTO `201735038`.`table` (id, table_no, size)
VALUES (3, 6, 12);
INSERT INTO `201735038`.`table` (id, table_no, size)
VALUES (4, 4, 2);