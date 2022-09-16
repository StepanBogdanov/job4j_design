create trigger add_history
before insert on products
for each row
execute procedure log_prices();