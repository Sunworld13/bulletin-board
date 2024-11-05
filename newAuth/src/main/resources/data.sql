DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

-- Создание таблицы пользователей

create table users (
                       id                    bigserial primary key,
                       username              varchar(30) not null unique,
                       password              varchar(80) not null,
                       email                 varchar(50) unique
);

-- Создание таблицы ролей
create table roles (
                       id                    serial primary key,
                       name                  varchar(50) not null
);

-- Создание таблицы связи пользователей и ролей
create table users_roles (
                             user_id               bigint not null,
                             role_id               int not null,
                             primary key (user_id, role_id),
                             foreign key (user_id) references users (id),
                             foreign key (role_id) references roles (id)
);

-- Вставка данных в таблицу ролей
insert into roles (name)
values ('ROLE_USER'), ('ROLE_ADMIN');

-- Вставка данных в таблицу пользователей
insert into users (username, password, email)
values
    ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
    ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com');

-- Связь пользователей с ролями
insert into users_roles (user_id, role_id)
values
    (1, 1),  -- user получает роль ROLE_USER
    (2, 2);  -- admin получает роль ROLE_ADMIN
