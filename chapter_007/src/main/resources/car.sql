create table transmission(
	id serial primary key,
	name varchar
);

create table car_body(
	id serial primary key,
	name varchar
);

create table engine(
	id serial primary key,
	name varchar
);

create table car(
	id serial primary key,
	name varchar,
	transmission_id integer references transmission(id),
	car_body_id integer references car_body(id),
	engine_id integer references engine(id)
);

insert into transmission (name) values ('transmission 1');
insert into transmission (name) values ('transmission 2');
insert into transmission (name) values ('transmission 3');
insert into transmission (name) values ('transmission 4');
insert into transmission (name) values ('transmission 5');

insert into car_body (name) values ('car_body 1');
insert into car_body (name) values ('car_body 2');
insert into car_body (name) values ('car_body 3');
insert into car_body (name) values ('car_body 4');
insert into car_body (name) values ('car_body 5');

insert into engine (name) values ('engine 1');
insert into engine (name) values ('engine 2');
insert into engine (name) values ('engine 3');
insert into engine (name) values ('engine 4');
insert into engine (name) values ('engine 5');

insert into car (name, transmission_id, car_body_id, engine_id)
values ('first car', 1, 2, 5);
insert into car (name, transmission_id, car_body_id, engine_id)
values ('second car', 2, 4, 3);
insert into car (name, transmission_id, car_body_id, engine_id)
values ('third car', 4, 1, 2);

select c.name, t.name, cr.name, e.name from car as c
inner join transmission as t on c.transmission_id = t.id
inner join car_body as cr on c.car_body_id = cr.id
inner join engine as e on c.engine_id = e.id;

select c.name, t.name from car as c
right outer join transmission as t on c.transmission_id = t.id
where c.id is null;

select c.name, cr.name from car as c
right outer join car_body as cr on c.car_body_id = cr.id
where c.id is null;

select c.name, e.name from car as c
right outer join engine as e on c.car_body_id = e.id
where c.id is null;