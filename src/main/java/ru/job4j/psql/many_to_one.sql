create table category(
    id serial primary key,
    name varchar(255)
);

create table goods(
    id serial primary key,
    name varchar(255),
    category_id int references category(id)
);