DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS schedule;
DROP TABLE IF EXISTS performance;
DROP TABLE IF EXISTS hall;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS seller;

CREATE TABLE customer
(
    customer_id      BIGINT              NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username         VARCHAR(15) UNIQUE  NOT NULL,
    password         VARCHAR(255)        NOT NULL,
    name             VARCHAR(50)         NOT NULL,
    email            VARCHAR(320) UNIQUE NOT NULL,
    birth_date       DATE                NOT NULL,
    phone            VARCHAR(15)         NOT NULL,
    address          VARCHAR(255)        NOT NULL,
    created_datetime DATETIME            NOT NULL DEFAULT now(),
    updated_datetime DATETIME            NOT NULL DEFAULT now() ON UPDATE now()
);

CREATE TABLE seller
(
    seller_id           BIGINT              NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username            VARCHAR(15) UNIQUE  NOT NULL,
    password            VARCHAR(255)        NOT NULL,
    name                VARCHAR(50)         NOT NULL,
    registration_number VARCHAR(15) UNIQUE  NOT NULL,
    store_name          VARCHAR(100)        NOT NULL,
    email               VARCHAR(320) UNIQUE NOT NULL,
    phone               VARCHAR(15)         NOT NULL,
    created_datetime    DATETIME            NOT NULL DEFAULT now(),
    updated_datetime    DATETIME            NOT NULL DEFAULT now() ON UPDATE now()
);

CREATE TABLE hall
(
    hall_id          BIGINT             NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name             VARCHAR(50) UNIQUE NOT NULL,
    address          VARCHAR(255)       NOT NULL,
    seats_count      INT                NOT NULL,
    created_datetime DATETIME           NOT NULL DEFAULT now(),
    updated_datetime DATETIME           NOT NULL DEFAULT now() ON UPDATE now()
);

CREATE TABLE performance
(
    performance_id   BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title            VARCHAR(100) NOT NULL,
    poster_url       VARCHAR(500) NOT NULL,
    start_date       DATE         NOT NULL,
    end_date         DATE         NOT NULL,
    duration         VARCHAR(10)  NOT NULL,
    age_rating       TINYINT      NOT NULL,
    price            INT          NOT NULL,
    category         VARCHAR(30)  NOT NULL,
    description      VARCHAR(5000),
    created_datetime DATETIME     NOT NULL DEFAULT now(),
    updated_datetime DATETIME     NOT NULL DEFAULT now() ON UPDATE now(),
    seller_id        BIGINT       NOT NULL,
    hall_id          BIGINT,
    FOREIGN KEY (seller_id) REFERENCES seller (seller_id) ON DELETE CASCADE,
    FOREIGN KEY (hall_id) REFERENCES hall (hall_id) ON DELETE SET NULL
);

CREATE TABLE schedule
(
    schedule_id      BIGINT   NOT NULL PRIMARY KEY AUTO_INCREMENT,
    start_datetime   DATETIME NOT NULL,
    sequence         TINYINT  NOT NULL,
    seats_count      INT      NOT NULL,
    performance_id   BIGINT   NOT NULL,
    created_datetime DATETIME NOT NULL DEFAULT now(),
    updated_datetime DATETIME NOT NULL DEFAULT now() ON UPDATE now(),
    FOREIGN KEY (performance_id) REFERENCES performance (performance_id) ON DELETE CASCADE
);

CREATE TABLE reservation
(
    reservation_id   BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    status           VARCHAR(10) NOT NULL,
    created_datetime DATETIME    NOT NULL DEFAULT now(),
    updated_datetime DATETIME    NOT NULL DEFAULT now() ON UPDATE now(),
    customer_id      BIGINT      NOT NULL,
    schedule_id      BIGINT      NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE,
    FOREIGN KEY (schedule_id) REFERENCES schedule (schedule_id) ON DELETE CASCADE
);