--liquibase formatted sql

--changeset rsushe:create_payment_table:1
CREATE TABLE payment
(
    id             SERIAL PRIMARY KEY,
    transaction_id VARCHAR NOT NULL,
    order_id       VARCHAR NOT NULL,
    status         VARCHAR NOT NULL
)
