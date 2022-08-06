create table roles(
    id serial primary key,
    name text
);

create table users(
    id serial primary key,
    name text,
    role_id int references roles(id)
);

create table rules(
    id serial primary key,
    rule text
);

create table role_rules(
    id serial primary key,
    role_id int references roles(id),
    rule_id int references rules(id)
);

create table categories(
    id serial primary key,
    category int
);

create table states(
    id serial primary key,
    state text
);

create table items(
    id serial primary key,
    name text,
    user_id int references users(id),
    category_id int references categories(id),
    state_id int references states(id)
);

create table user_items(
    id serial primary key,
    user_id int references users(id),
    item_id int references items(id)
);

create table comments(
    id serial primary key,
    comment text,
    item_id int references items(id)
);

create table attachs(
    id serial primary key,
    path text,
    item_id int references items(id)
);