--liquibase formatted sql

--changeset rsushe:create_transaction_table:1
CREATE TABLE transaction
(
    id     VARCHAR PRIMARY KEY,
    charge INT     NOT NULL,
    status VARCHAR NOT NULL
);

--changeset rsushe:add_column_callback_url_to_transaction_table:2
ALTER TABLE transaction ADD COLUMN callback_url VARCHAR NOT NULL;

--changeset rsushe:add_column_token_to_transaction_table:3
ALTER TABLE transaction ADD COLUMN token VARCHAR NOT NULL;
