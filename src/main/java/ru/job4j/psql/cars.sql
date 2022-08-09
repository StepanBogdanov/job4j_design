create table car_bodies(
	id serial primary key,
	name text
);
create table car_engines(
	id serial primary key,
	name text
);
create table car_transmissions(
	id serial primary key,
	name text
);
create table cars(
	id serial primary key,
	name text,
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);
insert into car_bodies(name) values ('body1'), ('body2'), ('body3'), ('body4');
insert into car_engines(name) values ('engine1'), ('engine2'), ('engine3'), ('engine4');
insert into car_transmissions(name) values ('transmission1'), ('transmission2'), ('transmission3');
insert into cars(name, body_id, engine_id, transmission_id) values ('car1', 1, 1, 1), ('car2', 1, 1, 2);
insert into cars(name, body_id, engine_id, transmission_id) values ('car3', 1, 2, 1), ('car4', 1, 2, 2);
insert into cars(name, body_id, engine_id, transmission_id) values ('car5', 1, 4, 1), ('car6', 1, 4, 2);
insert into cars(name, body_id, engine_id, transmission_id) values ('car7', 2, 1, 2), ('car8', 4, 2, 1);
insert into cars(name, body_id, engine_id, transmission_id) values ('car9', 4, 4, null), ('car10', 2, 2, 1);

select c.id, c.name as "car_name", b.name as "body_name", e.name as "engine_name", t.name as "transmission_name"
from cars c left join car_bodies b on c.body_id = b.id
left join car_engines e on c.engine_id = e.id
left join car_transmissions t on c.transmission_id = t.id;

select b.name as body_name
from car_bodies b left join cars c
on c.body_id = b.id
where c.name is null;

select e.name as engine_name
from car_engines e left join cars c
on c.engine_id = e.id
where c.name is null;

select t.name as transmission_name
from car_transmissions t left join cars c
on c.transmission_id = t.id
where c.name is null;
