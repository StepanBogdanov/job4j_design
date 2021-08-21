create table families(
	id serial primary key,
	name varchar(255)
);

create table animals(
	id serial primary key,
	name varchar(255),
	family_id int references families(id)
);

insert into families(name) values ('cat');
insert into animals(name, family_id) values ('cheetah', 1);

select * from animals;
select * from families where id in (select id from animals);