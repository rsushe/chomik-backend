--liquibase formatted sql

--changeset maksgirya:sneakers_create_table:1
CREATE TABLE sneakers (
        id VARCHAR PRIMARY KEY,
        model VARCHAR NOT NULL,
        brand VARCHAR NOT NULL,
        size DOUBLE PRECISION NOT NULL,
        color VARCHAR,
        condition VARCHAR NOT NULL
);


--changeset maksgirya:advert_create_table:2
CREATE TABLE advert (
        id VARCHAR PRIMARY KEY,
        sneaker_id VARCHAR NOT NULL,
        seller_id VARCHAR NOT NULL,
        status DOUBLE PRECISION NOT NULL,
        price DOUBLE PRECISION NOT NULL,
        active BOOLEAN NOT NULL
);

ALTER TABLE advert ADD CONSTRAINT sneakers_fk FOREIGN KEY (sneaker_id) REFERENCES sneakers(id);
CREATE UNIQUE INDEX sneakers_seller_unq ON advert(sneaker_id, seller_id);