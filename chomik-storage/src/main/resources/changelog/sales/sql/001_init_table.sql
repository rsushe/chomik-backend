--liquibase formatted sql

--changeset maksgirya:sales_table_init:1
CREATE TABLE IF NOT EXISTS sales (
        id VARCHAR PRIMARY KEY,
        advert_id VARCHAR NOT NULL,
        old_price DOUBLE PRECISION NOT NULL,
        new_price DOUBLE PRECISION NOT NULL,
        sale_percent DOUBLE PRECISION NOT NULL
);

