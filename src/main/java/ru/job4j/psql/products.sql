create table categories(
	id serial primary key,
	name text
);

create table product(
	id serial primary key,
	name text,
	category_id int references categories(id),
	price int,
	presence boolean
);

insert into categories(name) values ('fruit');
insert into categories(name) values ('vegetables');
insert into categories(name) values ('meat');
insert into categories(name) values ('cereals');

insert into product(name, category_id, price, presence) values ('apples', 1, 130, true);
insert into product(name, category_id, price, presence) values ('bananas', 1, 60, true);
insert into product(name, category_id, price, presence) values ('peaches', 1, 200, false);

insert into product(name, category_id, price, presence) values ('tomatoes', 2, 150, true);
insert into product(name, category_id, price, presence) values ('cucumbers', 2, 80, true);

insert into product(name, category_id, price, presence) values ('beaf', 3, 580, false);
insert into product(name, category_id, price, presence) values ('pork', 3, 270, true);
insert into product(name, category_id, price, presence) values ('chicken', 3, 200, true);

insert into product(name, category_id, price, presence) values ('rice', 4, 70, true);
insert into product(name, category_id, price, presence) values ('buckwheat', 1, 120, true);

select p.name, p.price, p.presence, c.name from product p join categories c on p.category_id=c.id;
select p.name Название, p.price Цена, c.name as "Тип продукта" from product p join categories c
on p.category_id=c.id where p.presence=false;
select p.name Название, p.price Цена, p.presence Наличие, c.name as "Тип продукта" from product p join categories c
on p.category_id=c.id where p.price>200;