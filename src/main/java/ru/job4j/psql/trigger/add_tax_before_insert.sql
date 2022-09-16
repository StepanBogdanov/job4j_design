create trigger add_tax_before_insert
before insert
on products
for each row
execute procedure tax();