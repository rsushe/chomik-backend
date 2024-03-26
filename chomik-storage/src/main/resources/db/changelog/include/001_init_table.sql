--liquibase formatted sql

--changeset maksgirya:sneakers_create_table:1
CREATE TABLE sneakers (
      id VARCHAR PRIMARY KEY,
      model VARCHAR NOT NULL,
      brand VARCHAR NOT NULL,
      size DOUBLE PRECISION NOT NULL,
      color VARCHAR,
      condition VARCHAR NOT NULL
);