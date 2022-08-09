create table departments(
	id serial primary key,
	name text
);
create table employees(
	id serial primary key,
	name text,
	department_id int references departments(id)
);
insert into departments(name) values ('department1'), ('department2'), ('department3');
insert into employees(name, department_id) values ('Emp1', 1), ('Emp2', 1), ('Emp3', 1), ('Emp4', 2);

select d.name, e.name
from departments d left join employees e
on d.id = e.department_id;
select d.name, e.name
from departments d right join employees e
on d.id = e.department_id;
select d.name, e.name
from departments d full join employees e
on d.id = e.department_id;
select d.name, e.name
from departments d cross join employees e;

select d.name, e.name
from departments d left join employees e
on d.id = e.department_id
where e.name is null;

select d.name, e.name
from departments d left join employees e
on d.id = e.department_id;
select d.name, e.name
from employees e right join departments d
on d.id = e.department_id;

create table teens(
	id serial primary key,
	name text,
	gender boolean
);
insert into teens(name, gender) values ('Teen1', true), ('Teen2', true), ('Teen3', true);
insert into teens(name, gender) values ('Teen4', false), ('Teen5', false);
select t1.name, t2.name from teens t1 cross join teens t2 where t1.gender != t2.gender;

