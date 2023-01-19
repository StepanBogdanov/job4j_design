create or replace function statement_tax()
	returns trigger as
$$
	BEGIN
		update products
		set price = price + price * 0.2
		where id = select id from interested;
		return NEW;
	END;
$$
LANGUAGE 'plpgsql';