DROP database IF exists ishop;
CREATE DATABASE ishop CHARACTER SET UTF8 COLLATE utf8_unicode_ci;
USE ishop;

CREATE TABLE user (
                    first_name VARCHAR(20) NOT NULL,
                    last_name VARCHAR(20) NOT NULL,
                    email VARCHAR(30),
                    password VARCHAR(30) NOT NULL,
                    subscribe BOOLEAN NOT NULL default 0,
                    CONSTRAINT email PRIMARY KEY(email)
);

INSERT INTO USER VALUES('Olha', 'Biletska', 'olha@example.com', 'password', TRUE);
INSERT INTO USER VALUES('Ivan', 'Ivanov', 'ivan@example.com', 'password', FALSE);
