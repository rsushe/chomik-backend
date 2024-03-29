--liquibase formatted sql

--changeset maksgirya:order_create_table:1
CREATE TABLE IF NOT EXISTS order (
        id VARCHAR PRIMARY KEY,
        buyer_id VARCHAR NOT NULL,
        advert_id VARCHAR NOT NULL,
        status VARCHAR NOT NULL
);


--changeset maksgirya:advert_lock_create_table:1
CREATE TABLE IF NOT EXISTS advert_lock (
         id VARCHAR PRIMARY KEY,
         user_id VARCHAR NOT NULL,
         lock_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
         is_active BOOLEAN NOT NULL,
         locked_count INTEGER NOT NULL
);

--changeset maksgirya:order_count_field:3
ALTER TABLE "order" ADD COLUMN sneaker_count INTEGER NOT NULL DEFAULT 1;
