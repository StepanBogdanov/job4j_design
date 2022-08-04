create table car_numbers(
    id serial primary key,
    region int,
    number varchar(255)
);

create table cars(
    id serial primary key,
    model varchar(255),
    color varchar(255),
    number_id int references car_numbers(id) unique
);
