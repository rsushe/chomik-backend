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
