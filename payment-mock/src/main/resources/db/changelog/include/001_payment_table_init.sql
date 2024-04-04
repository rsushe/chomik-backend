--liquibase formatted sql

--changeset rsushe:create_transaction_table:1
CREATE TABLE transaction
(
    id     VARCHAR PRIMARY KEY,
    charge INT     NOT NULL,
    status VARCHAR NOT NULL
);
