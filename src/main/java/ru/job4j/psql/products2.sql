create table type(
	id serial primary key,
	name text
);

create table product(
	id serial primary key,
	name text,
	type_id int references type(id),
	expired_date date,
	price int
);

insert into type(name) values ('Сыр'), ('Мясо'), ('Рыба'), ('Колбасы'), ('Молоко');

insert into product(name, type_id, expired_date, price) values ('Сыр Голландский', 1, '2022.09.10', 880);
insert into product(name, type_id, expired_date, price) values ('Сыр Белебеевский', 1, '2022.11.15', 600);
insert into product(name, type_id, expired_date, price) values ('Сыр плавленный', 1, '2022.08.05', 160);
insert into product(name, type_id, expired_date, price) values ('Сыр творожный', 1, '2023.05.10', 120);
insert into product(name, type_id, expired_date, price) values ('Сыр Маасдам', 1, '2022.12.23', 900);

insert into product(name, type_id, expired_date, price) values ('Свинина окорок', 2, '2022.11.10', 270);
insert into product(name, type_id, expired_date, price) values ('Шейка свиная', 2, '2022.10.15', 430);
insert into product(name, type_id, expired_date, price) values ('Карбонад свиной', 2, '2022.12.21', 320);
insert into product(name, type_id, expired_date, price) values ('Говядина', 2, '2022.09.18', 580);
insert into product(name, type_id, expired_date, price) values ('Рулька свиная на кости', 2, '2022.09.02', 130);
insert into product(name, type_id, expired_date, price) values ('Гуляш из говядины', 2, '2022.11.19', 700);
insert into product(name, type_id, expired_date, price) values ('Филе индейки', 2, '2022.07.10', 210);
insert into product(name, type_id, expired_date, price) values ('Грудка куриная', 2, '2022.10.19', 215);
insert into product(name, type_id, expired_date, price) values ('Крыло цыпленка-бройлера', 2, '2022.11.05', 205);
insert into product(name, type_id, expired_date, price) values ('Тушка цыпленка-бройлера', 2, '2022.12.10', 160);

insert into product(name, type_id, expired_date, price) values ('Минтай', 3, '2022.09.10', 160);
insert into product(name, type_id, expired_date, price) values ('Стейк трески', 3, '2022.10.21', 365);
insert into product(name, type_id, expired_date, price) values ('Скумбрия', 3, '2022.10.10', 200);
insert into product(name, type_id, expired_date, price) values ('Сельдь атлантическая', 3, '2022.09.17', 130);

insert into product(name, type_id, expired_date, price) values ('Сервелат Финский', 4, '2023.09.10', 140);
insert into product(name, type_id, expired_date, price) values ('Колбаса докторская', 4, '2022.12.12', 300);
insert into product(name, type_id, expired_date, price) values ('Колбаса чесночная', 4, '2022.10.08', 210);
insert into product(name, type_id, expired_date, price) values ('Колбаса краковская', 4, '2023.09.16', 630);
insert into product(name, type_id, expired_date, price) values ('Ветчина юрьевская', 4, '2022.11.10', 450);
insert into product(name, type_id, expired_date, price) values ('Колбаса молочная', 4, '2022.12.28', 320);

insert into product(name, type_id, expired_date, price) values ('Молоко топленое', 5, '2022.08.10', 40);
insert into product(name, type_id, expired_date, price) values ('Молоко пастеризованное', 5, '2022.08.15', 60);
insert into product(name, type_id, expired_date, price) values ('Напиток овсяный', 5, '2022.08.07', 90);
insert into product(name, type_id, expired_date, price) values ('Сливки', 5, '2022.09.01', 90);
insert into product(name, type_id, expired_date, price) values ('Молоко цельное', 5, '2022.08.10', 85);
insert into product(name, type_id, expired_date, price) values ('Мороженое пломбир', 5, '2022.11.16', 80);

select t.name, p.name, p.expired_date, p.price
from type t join product p on t.id = p.type_id
where t.name = 'Сыр';

select t.name, p.name, p.expired_date, p.price
from type t join product p on t.id = p.type_id
where p.name like '%Мороженое%';

select t.name Тип, p.name Наименование, p.expired_date as "Срок годности", p.price Цена
from type t join product p on t.id = p.type_id
where expired_date < current_date;

select t.name Тип, p.name Наименование, p.expired_date as "Срок годности", p.price Цена
from type t join product p on t.id = p.type_id
where p.price = (select max(p.price) from product p);

select t.name, count(p.name)
from type t join product p on t.id = p.type_id
group by t.name;

select t.name Тип, p.name Наименование, p.expired_date as "Срок годности", p.price Цена
from type t join product p on t.id = p.type_id
where t.name = 'Сыр' or t.name = 'Молоко';

select t.name Тип, count(p.name) Остаток
from type t join product p on t.id = p.type_id
group by t.name
having count(p.name) < 10;
