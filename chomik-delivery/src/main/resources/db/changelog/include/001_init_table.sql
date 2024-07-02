--liquibase formatted sql


--changeset maksgirya:address_create_table:1
CREATE TABLE IF NOT EXISTS address (
       id VARCHAR PRIMARY KEY,
       country VARCHAR NOT NULL,
       city VARCHAR NOT NULL,
       street VARCHAR NOT NULL,
       house VARCHAR NOT NULL,
       floor INT,
       flat VARCHAR,
       extra_info VARCHAR
);

--changeset maksgirya:user_address_create_table:2
CREATE TABLE IF NOT EXISTS user_address (
        id VARCHAR PRIMARY KEY,
        user_id VARCHAR NOT NULL,
        address_id VARCHAR NOT NULL
);

--changeset maksgirya:shipment_create_table:3
CREATE TABLE IF NOT EXISTS shipment (
       id VARCHAR PRIMARY KEY,
       order_id VARCHAR NOT NULL,
       address_from VARCHAR NOT NULL,
       address_to VARCHAR NOT NULL,
       track_link VARCHAR,
       status VARCHAR NOT NULL
);

--changeset maksgirya:shipment_in-return_column:4
ALTER TABLE shipment ADD COLUMN in_return BOOLEAN;
UPDATE shipment SET in_return = FALSE WHERE shipment.in_return IS NULL ;
ALTER TABLE shipment ALTER COLUMN in_return SET NOT NULL ;
