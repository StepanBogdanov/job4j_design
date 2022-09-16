create or replace function tax()
	returns trigger as
$$
	BEGIN
		update products
		set price = price + price * 0.2
		where id = new.id;
		return NEW;
	END;
$$
LANGUAGE 'plpgsql';
