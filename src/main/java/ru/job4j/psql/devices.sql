create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Smartphone', 25000);
insert into devices(name, price) values ('Laptop', 50000);
insert into devices(name, price) values ('Watch', 5000);
insert into devices(name, price) values ('Headphones', 3000);
insert into devices(name, price) values ('Desktop', 70000);

insert into people(name) values ('Person1');
insert into people(name) values ('Person2');
insert into people(name) values ('Person3');

insert into devices_people(device_id, people_id) values (1, 1), (2, 1), (4, 1);
insert into devices_people(device_id, people_id) values (1, 2), (5, 2);
insert into devices_people(device_id, people_id) values (1, 3), (2, 3), (3, 3);

select avg(price) from devices;

select p.name, avg(d.price)
from people p join devices_people as dp on p.id = dp.people_id
join devices d on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price)
from people p join devices_people as dp on p.id = dp.people_id
join devices d on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;
