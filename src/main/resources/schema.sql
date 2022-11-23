DROP TABLE users IF EXISTS;

create table users (
    id bigint not null AUTO_INCREMENT,
    email varchar(50) not null,
    nick_name varchar(50) not null,
    name varchar(50) not null,
    phone_number varchar(11) not null,
    password varchar(255) not null,
    authority_id bigint not null,
    primary key (email)
);

create table authority (
   authority_id bigint not null AUTO_INCREMENT,
   role varchar(50) not null,
   primary key (authority_id)
);