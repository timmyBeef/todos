create sequence ilist_sequence start with 5 increment by 1;
create sequence item_sequence start with 5 increment by 1;
create sequence user_sequence start with 5 increment by 1;
create table ilist
(
    id          bigint                      not null,
    create_time TIMESTAMP WITHOUT TIME ZONE not null,
    due_date    TIMESTAMP WITHOUT TIME ZONE,
    name        TEXT,
    update_time TIMESTAMP WITHOUT TIME ZONE,
    user_id     bigint                      not null,
    primary key (id)
);
create table item
(
    id          bigint                      not null,
    create_time TIMESTAMP WITHOUT TIME ZONE not null,
    deadline    TIMESTAMP WITHOUT TIME ZONE,
    description TEXT,
    order_num   integer,
    update_time TIMESTAMP WITHOUT TIME ZONE,
    ilist_id    bigint                      not null,
    primary key (id)
);
create table users
(
    id               bigint       not null,
    create_time      TIMESTAMP WITHOUT TIME ZONE,
    encoded_password varchar(255) not null,
    update_time      TIMESTAMP WITHOUT TIME ZONE,
    user_name        varchar(255) not null,
    primary key (id)
);
alter table users
    add constraint user_name_unique unique (user_name);
alter table ilist
    add constraint user_list_fk foreign key (user_id) references users;
alter table item
    add constraint ilist_item_fk foreign key (ilist_id) references ilist;
