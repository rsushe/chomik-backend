--liquibase formatted sql


--changeset maksgirya:user_address_create_table:1
CREATE TABLE IF NOT EXISTS user_address (
        id VARCHAR PRIMARY KEY,
        user_id VARCHAR NOT NULL,
        country VARCHAR NOT NULL,
        city VARCHAR NOT NULL,
        street VARCHAR NOT NULL,
        house VARCHAR NOT NULL,
        floor INT,
        flat VARCHAR,
        extra_info VARCHAR
);

--changeset maksgirya:shipment_create_table:2
CREATE TABLE IF NOT EXISTS shipment (
       id VARCHAR PRIMARY KEY,
       order_id VARCHAR NOT NULL,
       address_from VARCHAR NOT NULL,
       address_to VARCHAR NOT NULL,
       track_link VARCHAR,
       status VARCHAR NOT NULL
);


