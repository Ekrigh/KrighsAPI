DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS addresses;

CREATE TABLE addresses
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    street     VARCHAR(100) NOT NULL,
    postalcode VARCHAR(5)   NOT NULL,
    city       VARCHAR(45)  NOT NULL
);

CREATE TABLE members
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    first_name    VARCHAR(45) NOT NULL,
    last_name     VARCHAR(45) NOT NULL,
    address_id    INT         NOT NULL,
    email         VARCHAR(64) NOT NULL,
    phone         VARCHAR(16),
    date_of_birth DATE        NOT NULL,
    FOREIGN KEY (address_id) REFERENCES addresses (id)
);