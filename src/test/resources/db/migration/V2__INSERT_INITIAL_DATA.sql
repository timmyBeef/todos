insert into users (create_time, encoded_password, user_name, id)
values ('2021-06-22T13:57:34.920Z', '$2a$10$L5rUMjVqkojrPaO80SC.VOPxPRd7eoNrkWBrFo3lDy1lDocc8RMmK', 'tim', 1);
insert into users (create_time, encoded_password, user_name, id)
values ('2021-06-22T13:57:34.920Z', '$2a$10$L5rUMjVqkojrPaO80SC.VOPxPRd7eoNrkWBrFo3lDy1lDocc8RMmK', 'jean', 2);

insert into ilist (create_time, due_date, name, update_time, user_id, id)
values ('2021-06-22T13:57:34.920Z', '2021-06-22T13:57:34.920Z', 'my tasks', '2021-06-22T13:57:34.920Z', 1, 1);
insert into item (create_time, deadline, description, ilist_id, order_num, update_time, id)
values ('2021-06-22T13:57:34.920Z', '2021-06-22T13:57:34.920Z', 'item1 desc', 1, 0, '2021-06-22T13:57:34.920Z', 1);
insert into item (create_time, deadline, description, ilist_id, order_num, update_time, id)
values ('2021-06-22T13:57:34.920Z', '2021-06-22T13:57:34.920Z', 'item2 desc', 1, 0, '2021-06-22T13:57:34.920Z', 2);
insert into item (create_time, deadline, description, ilist_id, order_num, update_time, id)
values ('2021-06-22T13:57:34.920Z', '2021-06-22T13:57:34.920Z', 'item3 desc', 1, 0, '2021-06-22T13:57:34.920Z', 3);

