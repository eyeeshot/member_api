insert into authority (authority_id, role)
values (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');

insert into users (id, email, nick_name, name, phone_number, password, authority_id)
values (1, 'admin@admin.com', 'admin', '관리자', '01011111111', '1111', 1),
       (2, 'user@user.com', 'user', '사용자', '01022222222', '2222', 2);