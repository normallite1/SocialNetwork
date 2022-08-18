create sequence hibernate_sequence start 2 increment 1;

create table usr (
                     id int8 not null ,
                     activatore_code varchar(255),
                     active boolean not null,
                     email varchar(255),
                     password varchar(255) not null,
                     username varchar(30) not null,
                     primary key (id));

create table messages (
                         id int8 not null,
                         filename varchar(255),
                         data_time varchar(255),
                         message varchar(255),
                         tag varchar(30),
                         user_id int8,
                         primary key (id));

create table user_role (
                           user_id int8 not null,
                           roles varchar(255));

alter table if exists messages
    add constraint message_user_fk
    foreign key (user_id) references usr;

alter table if exists user_role
    add constraint user_role_user_fk
    foreign key (user_id) references usr