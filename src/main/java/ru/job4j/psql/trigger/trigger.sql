create or replace function row_tax()
	returns trigger as
$$
	BEGIN
		new.price = new.price + new.price * 0.2;
		return NEW;
	END;
$$
LANGUAGE 'plpgsql';

create trigger add_tax_before_insert
before insert
on products
for each row
execute procedure row_tax();

create or replace function log_prices()
	returns trigger as
$$
	BEGIN
		insert into history_of_price (name, price, date)
		values (NEW.name, NEW.price, now());
		return NEW;
	END
$$
LANGUAGE 'plpgsql'

create trigger add_history
before insert on products
for each row
execute procedure log_prices();

create or replace function statement_tax()
	returns trigger as
$$
	BEGIN
		update products
		set price = price + price * 0.2
		where id = (select id from interested);
		return NEW;
	END;
$$
LANGUAGE 'plpgsql';

create trigger add_tax_after_insert
after insert on products
referencing new table as interested
for each statement
execute procedure statement_tax();