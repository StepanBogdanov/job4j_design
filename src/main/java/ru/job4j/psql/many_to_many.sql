create table actors(
    id serial primary key,
    name varchar(255)
);

create table movies(
    id serial primary key,
    name varchar(255)
);

create table actors_movies(
    id serial primary key,
    actor_id int references actors(id),
    movie_id int references movies(id)
);