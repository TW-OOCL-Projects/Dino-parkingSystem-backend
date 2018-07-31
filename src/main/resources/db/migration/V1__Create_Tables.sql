CREATE TABLE employee (
    id  BIGINT   NOT NULL  AUTO_INCREMENT  PRIMARY KEY,
    name    VARCHAR(50)     NOT NULL,
    email   VARCHAR(50)     NOT NULL,
    phone    VARCHAR(20)     NOT NULL,
    status  VARCHAR(20)     NOT NULL,
    authority   VARCHAR(20)     NOT NULL
);
CREATE TABLE parking_lot (
    id  BIGINT  NOT NULL  AUTO_INCREMENT  PRIMARY KEY,
    name    VARCHAR(20)     NOT NULL,
    `size`    INT     NOT NULL,
    status  VARCHAR(20)     NOT NULL,
    parking_boy_id  BIGINT,
--    FOREIGN KEY (parking_boy_id) REFERENCES (employee)
);
CREATE TABLE `order` (
    id  BIGINT  NOT NULL    AUTO_INCREMENT  PRIMARY KEY,
    type    VARCHAR(20)     NOT NULL,
    parking_boy_id  BIGINT,
    plate_number    VARCHAR(20)     NOT NULL,
    status  VARCHAR(20)     NOT NULL
);
CREATE TABLE receipt (
    id  BIGINT  NOT NULL    AUTO_INCREMENT  PRIMARY KEY,
    status  VARCHAR(20)     NOT NULL
);