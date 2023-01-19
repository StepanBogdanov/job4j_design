create trigger add_tax_before_insert
before insert
on products
for each row
execute procedure row_tax();

create trigger add_history
before insert on products
for each row
execute procedure log_prices();

create trigger add_tax_after_insert
after insert on products
referencing new table as interested
for each statement
execute procedure statement_tax();