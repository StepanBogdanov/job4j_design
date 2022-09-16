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
