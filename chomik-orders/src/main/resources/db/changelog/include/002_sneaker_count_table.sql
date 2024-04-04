--liquibase formatted sql

--changeset rsushe:sneaker_count_table:1
CREATE TABLE IF NOT EXISTS sneaker_count(
    sneaker_id VARCHAR PRIMARY KEY,
    count INT NOT NULL
);

--changeset rsushe:rename_sneaker_count_column:1
ALTER TABLE sneaker_count RENAME COLUMN sneaker_id to advert_id;

--changeset rsushe:drop_sneaker_count_table:2;
DROP TABLE sneaker_count;
