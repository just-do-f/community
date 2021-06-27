create table community.user
(
    id           int auto_increment
        primary key,
    name         varchar(30) null,
    account_id   varchar(50) null,
    token        varchar(50) null,
    gmt_create   bigint      null,
    gmt_modified bigint      null
);