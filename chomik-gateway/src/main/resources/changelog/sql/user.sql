--liquibase formatted sql

--changeset rsushe:user_tables_definition
create table "user"
(
    id                varchar primary key,
    name              varchar                     not null,
    password          varchar                     not null,
    email             varchar                     null,
    phone_number      varchar                     null,
    deleted           boolean                     not null,
    rating            double precision            null,
    registration_date timestamp without time zone not null,
    user_type         varchar                     not null
);
