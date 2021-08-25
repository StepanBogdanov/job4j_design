create table role(
	id serial primary key,
	name text
);

create table users(
	id serial primary key,
	name text,
	role_id int references role(id)
);

create table rules(
	id serial primary key,
	rule text
);

create table role_rules(
	id serial primary key,
	role_id int references role(id),
	rule_id int references rules(id)
);

create table categories(
	id serial primary key,
	category text
);

create table states(
	id serial primary key,
	state text
);

create table items(
	id serial primary key,
	item text,
	user_id int references users(id),
	category_id int references categories(id),
	state_id int references states(id)
);

create table comments(
	id serial primary key,
	comment text,
	item_id int references items(id)
);

create table attachs(
	id serial primary key,
	name text,
	item_id int references items(id)
);

