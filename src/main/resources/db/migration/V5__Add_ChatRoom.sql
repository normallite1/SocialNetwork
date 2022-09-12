create table chat_room (
                     id int8 not null ,
                     name varchar(255),
                     primary key (id));

create table chat_Messages (
                          id int8 not null,
                          content varchar(255),
                          sender varchar(255),
                          chat_room_id int8,
                          primary key (id));


alter table if exists chat_Messages
    add constraint chatMessage_user_fk
        foreign key (chat_room_id) references chat_room;
