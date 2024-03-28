--liquibase formatted sql

--changeset maksgirya:order_create_table:1
CREATE TABLE IF NOT EXISTS order (
        id VARCHAR PRIMARY KEY,
        buyer_id VARCHAR NOT NULL,
        advert_id VARCHAR NOT NULL,
        status VARCHAR NOT NULL
);


-- --changeset maksgirya:sneakers_create_table:1
-- CREATE TABLE IF NOT EXISTS advert_lock (
--          id VARCHAR PRIMARY KEY,
--          user_id VARCHAR NOT NULL,
--          lock_time VARCHAR NOT NULL,
--          status VARCHAR NOT NULL
-- );
