create table if not exists items(
             id serial primary key,
             name varchar(100),
             description varchar(200),
             time_of_creation bigint);
);