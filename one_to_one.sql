create table gos_nomer(
	id serial primary key,
	number varchar(255),
	region int
);

create table cars(
	id serial primary key,
	model varchar(255),
	VIN varchar(255)
)

create table gos_nomers_cars(
	id serial primary key,
	gos_nomer_id int references gos_nomer(id) unique,
	cars_id int references cars(id) unique
)