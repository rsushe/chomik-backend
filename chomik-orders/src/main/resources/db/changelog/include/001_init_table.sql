--liquibase formatted sql

--changeset maksgirya:order_create_table:1
CREATE TABLE IF NOT EXISTS "order" (
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

--changeset rsushe:add_advert_id_column:4
ALTER TABLE advert_lock ADD COLUMN advert_id VARCHAR NOT NULL;

--changeset rsushe:rename_user_id_column:5
ALTER TABLE advert_lock RENAME COLUMN user_id to order_id;

--changeset rsushe:add_order_id_fk:6
ALTER TABLE advert_lock ADD CONSTRAINT order_id_fk FOREIGN KEY (order_id) REFERENCES "order" (id);
