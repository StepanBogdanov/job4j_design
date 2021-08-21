create table users(
	id serial primary key,
	name varschar(255)
);

create table os(
	id serial primary key,
	name varchar(255)
);

create table users_os(
	id serial primary key,
	user_id int references users(id),
	os_id int references os(id)
);

insert into users(name) values ('Ivan');
insert into users(name) values ('Maksim');

insert into os(name) values ('Windows');
insert into os(name) values ('MacOS');

insert into users_os(user_id, os_id) values(1, 1);
insert into users_os(user_id, os_id) values(1, 2);
insert into users_os(user_id, os_id) values(2, 2);