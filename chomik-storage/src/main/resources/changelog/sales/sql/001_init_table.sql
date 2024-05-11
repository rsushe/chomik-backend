--liquibase formatted sql

--changeset maksgirya:sales_table_init:1
CREATE TABLE IF NOT EXISTS sales (
        id VARCHAR PRIMARY KEY,
        advert_id VARCHAR NOT NULL,
        old_price DOUBLE PRECISION NOT NULL CHECK (old_price >= 1),
        new_price DOUBLE PRECISION NOT NULL CHECK (old_price >= 1),
        sale_percent DOUBLE PRECISION NOT NULL
);

