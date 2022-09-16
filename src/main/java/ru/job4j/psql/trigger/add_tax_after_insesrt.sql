create trigger add_tax_after_insert
after insert on products
referencing new table as interested
for each statement
execute procedure tax();
