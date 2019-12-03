DROP database IF exists ishop;
CREATE DATABASE ishop CHARACTER SET UTF8 COLLATE utf8_unicode_ci;
USE ishop;

CREATE TABLE user (
                    first_name VARCHAR(20) NOT NULL,
                    last_name VARCHAR(20) NOT NULL,
                    email VARCHAR(30),
                    password VARCHAR(30) NOT NULL,
                    subscribe BOOLEAN NOT NULL default 0,
                    CONSTRAINT email PRIMARY KEY(email),
                    role VARCHAR(20) NOT NULL
);

CREATE TABLE category (
                          category_id INTEGER PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(40) NOT NULL,
                          description VARCHAR(120) NOT NULL
);

CREATE TABLE manufacturer (
                              manufacturer_id INTEGER PRIMARY KEY AUTO_INCREMENT,
                              name VARCHAR(40) NOT NULL,
                              description VARCHAR(120) NOT NULL
);

CREATE TABLE products (
                          product_id INTEGER PRIMARY KEY AUTO_INCREMENT,
                          description VARCHAR(120) NOT NULL,
                          category_id INTEGER NOT NULL,
                          manufacturer_id INTEGER NOT NULL
);
ALTER TABLE products
    ADD CONSTRAINT category
        FOREIGN KEY(category_id)
            REFERENCES category(category_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;
ALTER TABLE products
    ADD CONSTRAINT productManufacturer
        FOREIGN KEY(manufacturer_id)
            REFERENCES manufacturer(manufacturer_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

CREATE TABLE price (
					product_id INTEGER NOT NULL,
                    list_price FLOAT NOT NULL,
                    min_price FLOAT NOT NULL,
                    start_date DATE NOT NULL,
                    end_date DATE NOT NULL
);
ALTER TABLE price
    ADD CONSTRAINT productPrice
        FOREIGN KEY(product_id)
            REFERENCES products(product_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

CREATE TABLE orders (
					order_id INTEGER PRIMARY KEY AUTO_INCREMENT,
                    order_date DATE NOT NULL,
                    user_email VARCHAR(30) NOT NULL,
                    ship_date DATE NOT NULL,
                    total FLOAT NOT NULL,
                    status VARCHAR(30) NOT NULL,
                    status_details  VARCHAR(120) NOT NULL
);
ALTER TABLE orders
    ADD CONSTRAINT userOrder
        FOREIGN KEY(user_email)
            REFERENCES user(email)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

CREATE TABLE items (
					item_id INTEGER PRIMARY KEY AUTO_INCREMENT,
					order_id INTEGER NOT NULL,
                    product_id INTEGER NOT NULL,
                    actual_price FLOAT NOT NULL,
                    quantity INTEGER DEFAULT NULL,
                    total FLOAT NOT NULL
);
ALTER TABLE items
    ADD CONSTRAINT orderItem
        FOREIGN KEY(order_id)
            REFERENCES orders(order_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;
ALTER TABLE items
    ADD CONSTRAINT orderItemProduct
        FOREIGN KEY(product_id)
            REFERENCES products(product_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

INSERT INTO USER VALUES('Olha', 'Biletska', 'olha@example.com', 'password', TRUE,'admin');
INSERT INTO USER VALUES('Ivan', 'Ivanov', 'ivan@example.com', 'password', FALSE, 'user');

INSERT INTO category (category_id, name, description) VALUES
(1, 'laptop', 'Ноутбуки'),
(2, 'tablet', 'Планшеты'),
(3, 'smartphone', 'Смартфоны');

INSERT INTO manufacturer (manufacturer_id, name, description) VALUES
(1, 'Asus', 'AsusTek Computer Inc.'),
(2, 'Lenovo', 'Lenovo Group Ltd.'),
(3, 'Dell', 'Dell Computer Corporation'),
(4, 'Acer', 'Acer Computer International, Ltd'),
(5, 'Xiaomi', 'Xiaomi Corporation'),
(6, 'HP', 'Hewlett-Packard Company'),
(7, 'Samsung', 'Samsung Electronics Co., Ltd.'),
(8, 'Impression', 'Impression Electronics by Navigator Ltd., Ukraine company'),
(9, 'Huawei', 'Huawei Technologies Co. Ltd.'),
(10, 'Pixus', 'Pixus, Ukraine company');

 INSERT INTO products (product_id, description, category_id, manufacturer_id) VALUES
(1, 'Ноутбук Acer Aspire 5 A515-52G-5527 (NX.H5LEU.010)', 1, 4),
(2, 'Ноутбук Xiaomi Mi Notebook Air 13.3 Grey (JYU4063GL)', 1, 5),
(3, 'Ноутбук HP ProBook 440 G6 (5PQ09EA)', 1, 6),
(4, 'Ноутбук Asus VivoBook Pro 15 N580GD', 1, 1),
(5, 'Ноутбук Dell G5 5587 (55G5i78S1H1G15i-LBK)', 1, 3),
(6, 'Ноутбук HP 250 G7 (6MQ28EA)', 1, 6),
(7, 'Ноутбук Dell G5 5587 (55G5i916S2H1G16-WBK)', 1, 3),
(8, 'Ноутбук Asus ZenBook Flip UX362FA (UX362FA-EL001T)', 1, 1),
(9, 'Ноутбук Dell Inspiron 3582 (I35P54S1DIW-73B)', 1, 3),
(10, 'Ноутбук Asus VivoBook 17 X705UF (X705UF-GC020T)', 1, 1),
(11, 'Ноутбук Dell Inspiron 3573 (I35C45DIL-70)', 1, 3),
(12, 'Ноутбук Asus VivoBook Pro N705UN (N705UN-GC051)', 1, 1),

(13, 'Планшетный ПК Lenovo TAB E10 TB-X104F (ZA470000UA)', 2, 2),
(14, 'Планшетный ПК Samsung T510 Galaxy Tab A 10.1 32GB Black (SM-T510NZKDSEK)', 2, 7),
(15, 'Планшетный ПК Lenovo TAB 7 Essential TB-7304I (ZA310144UA)', 2, 2),
(16, 'Планшетный ПК Impression ImPad P104 Android 8.1 Black', 2, 8),
(17, 'Планшетный ПК Lenovo TAB4 7 Essential TB-7304I (ZA310064UA)', 2, 2),
(18, 'Планшетный ПК Impression ImPad M102 16GB 3G Dual Sim Black', 2, 8),
(19, 'Планшетный ПК Asus ZenPad 3S 10 Gray 32GB (Z500KL-1A014A)', 2, 1),
(20, 'Планшетный ПК Pixus Vision 3GB RAM 32GB 4G Dual Sim Black', 2, 10),
(21, 'Планшетный ПК Huawei MediaPad M5 lite 10 LTE Gray (BAH2-L09C)', 2, 9),
(22, 'Планшетный ПК Huawei MediaPad T5 10 2GB RAM 16GB LTE Black (AGS2-L09A)', 2, 9),
(23, 'Планшетный ПК Lenovo IdeaPad Miix 320 (80XF0076RA)', 2, 2),
(24, 'Планшетный ПК Impression ImPad P701 Android 8.1 Black', 2, 8),

(25, 'Мобильный телефон Samsung A505F Galaxy A50/64 Duos Black (SM-A505FZKUSEK)', 3, 7),
(26, 'Мобильный телефон Samsung A105F Galaxy A10 Duos Blue (SM-A105FZBGSEK)', 3, 7),
(27, 'Мобильный телефон Samsung A105F Galaxy A10 Duos Black (SM-A105FZKGSEK)', 3, 7),
(28, 'Мобильный телефон Xiaomi Redmi 6A 2/16GB Black', 3, 5),
(29, 'Мобильный телефон Samsung A505F Galaxy A50/128 Duos White (SM-A505FZWQSEK)', 3, 7),
(30, 'Мобильный телефон Xiaomi Redmi Go 1/8GB Black', 3, 5),
(31, 'Мобильный телефон Samsung J120H/DS Galaxy J1 2016 Duos Black (SM-J120HZKDSEK)', 3, 7),
(32, 'Мобильный телефон Xiaomi Redmi Note 7 4/64GB Blue', 3, 5),
(33, 'Мобильный телефон Samsung A605F Galaxy A6+ 2018 Duos Gold (SM-A605FZDNSEK)', 3, 7),
(34, 'Мобильный телефон Samsung J250F Galaxy J2 2018 Duos Silver (SM-J250FZSDSEK)', 3, 7),
(35, 'Мобильный телефон Samsung A305F Galaxy A30/32 Duos Red (SM-A305FZRUSEK)', 3, 7),
(36, 'Мобильный телефон Xiaomi Redmi Note 5 4/64GB Gold EU', 3, 5);

INSERT INTO price (product_id, list_price, min_price, start_date, end_date) VALUES
(1, 15999, 15200, "2019-01-01", "2020-07-01"),
(2, 19699, 19050, "2019-01-01", "2020-11-01"),
(3, 25999, 24999, "2019-01-01", "2020-11-01"),
(4, 28325, 28000, "2019-01-01", "2020-11-01"),
(5, 32499, 31000, "2019-01-01", "2020-11-01"),
(6, 10999, 10100, "2019-01-01", "2020-11-01"),
(7, 56599, 55000, "2019-01-01", "2020-11-01"),
(8, 33599, 31000, "2019-01-01", "2020-11-01"),
(9, 9999, 9000, "2019-01-01", "2020-11-01"),
(10, 30699, 30000, "2019-01-01", "2020-11-01"),
(11, 6199, 6100, "2019-01-01", "2020-11-01"),
(12, 30390, 30000, "2019-01-01", "2020-11-01"),

(13, 4199, 4000, "2019-01-01", "2020-11-01"),
(14, 6999, 6150, "2019-01-01", "2020-11-01"),
(15, 3297, 3100, "2019-01-01", "2020-11-01"),
(16, 2599, 2500, "2019-01-01", "2020-11-01"),
(17, 2999, 2760, "2019-01-01", "2020-11-01"),
(18, 2151, 2100, "2019-01-01", "2020-11-01"),
(19, 8999, 8500, "2019-01-01", "2020-11-01"),
(20, 4299, 4000, "2019-01-01", "2020-11-01"),
(21, 8999, 8800, "2019-01-01", "2020-11-01"),
(22, 6499, 6410, "2019-01-01", "2020-11-01"),
(23, 9214, 8800, "2019-01-01", "2020-11-01"),
(24, 1759, 1710, "2019-01-01", "2020-11-01"),

(25, 8499, 8000, "2019-01-01", "2020-11-01"),
(26, 3699, 3100, "2019-01-01", "2020-11-01"),
(27, 3699, 3099, "2019-01-01", "2020-11-01"),
(28, 2799, 2399, "2019-01-01", "2020-11-01"),
(29, 9999, 9600, "2019-01-01", "2020-11-01"),
(30, 1999, 1400, "2019-01-01", "2020-11-01"),
(31, 2499, 2150, "2019-01-01", "2020-11-01"),
(32, 5999, 5400, "2019-01-01", "2020-11-01"),
(33, 6999, 6300, "2019-01-01", "2020-11-01"),
(34, 2999, 2300, "2019-01-01", "2020-11-01"),
(35, 5999, 5400, "2019-01-01", "2020-11-01"),
(36, 4499, 4250, "2019-01-01", "2020-11-01");