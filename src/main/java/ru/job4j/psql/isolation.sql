create table cars(
	id serial primary key,
	model text,
    producer text,
    price int
);

insert into cars(model, producer, price) values ('model1', 'producer1', 1000), ('model2', 'producer1', 1200),
('model3', 'producer2', 1100);

/* Transaction1 */

begin transaction isolation level seializable;
select sum(price) from cars;
update cars set price = 1500 where model = 'model1';
commit;

/* Transaction2 */

begin transaction isolation level seializable;
select sum(price) from cars;
update cars set price = 1300 where model = 'model2';
commit;