--liquibase formatted sql

--changeset rsushe:create_account_table:1
CREATE TABLE account
(
    id          VARCHAR PRIMARY KEY,
    name        VARCHAR NOT NULL,
    card_number VARCHAR NOT NULL,
    cvv         INT     NOT NULL,
    expire_at   DATE    NOT NULL,
    balance     BIGINT  NOT NULL
);

--changeset rsushe:add_column_account_to_transaction:2
ALTER TABLE transaction ADD COLUMN account_to VARCHAR NOT NULL REFERENCES account(id);
