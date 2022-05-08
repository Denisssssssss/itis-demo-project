insert into account
values (1, '2022-02-15 00:00:00.000000', 'firstName', 'lastName', 'username2',
        '$2a$10$n3XgiQrW.4nPK4OdYFnvRuAKsrQGBEDR11bjYsAmNmqtqVKX3XGXC');

insert into account
values (2, '2022-02-15 00:00:00.000000', 'firstName', 'lastName', 'username2',
        '$2a$10$n3XgiQrW.4nPK4OdYFnvRuAKsrQGBEDR11bjYsAmNmqtqVKX3XGXC');

insert into hotel (id, created_at, check_in, departure, name, email, phone_number, owner_id)
values (1, '2022-02-15 00:00:00.000000', '00:00:00', '23:59:59', 'hotel1', 'admin@admin.com', 123, 1);

insert into hotel (id, created_at, check_in, departure, name, email, phone_number, owner_id)
values (2, '2022-02-15 00:00:00.000000', '00:00:00', '23:59:59', 'hotel2', 'admin@admin.com', 123, 2);

insert into facility (id, name, price)
values (1, 'JACUZZI', 123);

insert into facility (id, name, price)
values (2, 'MINIBAR', 123);

insert into apartment
values (1, '2022-02-15 00:00:00.000000', 2, 1, 6, false, 1);

insert into apartment
values (2, '2022-02-15 00:00:00.000000', 2, 1, 6, false, 2);

insert into booking (check_in, departure, code, user_id, apartment_id)
values ('2021-12-22 01:00:00.000000', '2021-12-22 02:00:00.000000', 4187, 1, 1);