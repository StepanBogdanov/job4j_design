create procedure delete_data(d_id integer)
language 'plpgsql'
as $$
    BEGIN
    delete from products where id = d_id;
    END
$$;

create function f_delete_data()
returns void
language 'pspgsql'
as $$
    BEGIN
    delete from products where count = 0;
    END
$$;