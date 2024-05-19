--liquibase formatted sql

--changeset rsushe:create_qrtz_log_table:1
create table qrtz_log(
    id bigserial primary key ,
    job_name text not null,
    job_group text not null ,
    trigger_fire_time timestamp with time zone not null ,
    job_finished_time timestamp with time zone,
    job_status text,
    host_name text,
    constraint unique_log unique (job_name, job_group, trigger_fire_time, host_name)
)
