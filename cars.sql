create table cars(
	id serial primary key,
	name varchar(255),
	isNew boolean,
	color text,
	power int
);
insert into cars(name, isNew, color, power) values('car', true, 'black', 100);
update cars set name = 'Kia';
delete from cars;