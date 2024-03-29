--liquibase formatted sql

--changeset rsushe:sneaker_count_table:1
CREATE TABLE IF NOT EXISTS sneaker_count(
    sneaker_id VARCHAR PRIMARY KEY,
    count INT NOT NULL
)
