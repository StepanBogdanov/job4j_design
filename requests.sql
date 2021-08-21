create table goods(
    id seial primary key,
    name varchar(255),
    price money,
    count int
);
insert into goods(name, price, count) values ('Bread', 45.0, 25);
update goods set price = 46.0;
delete from goods;
select * from goods;