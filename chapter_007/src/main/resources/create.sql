create database job4j;
create table roles(
	id serial primary key,
	role_name varchar
);
create table rules(
	id serial primary key,
	rule_desc varchar
);
create table state_of_items (
	id serial primary key,
	state_name varchar
);
create table category_of_items (
	id serial primary key,
	category_name varchar
);
create table roles_rules (
	id serial primary key,
	role_id integer references roles(id),
	rule_id integer references rules(id)
);
create table users (
	id serial primary key,
	name varchar,
	role_id integer references roles(id)
);
create table items (
	id serial primary key,
	name varchar,
	user_id integer references users(id),
	state_id integer references state_of_items(id),
	category_id integer references category_of_items(id)
);
create table comments_of_items (
	id serial primary key,
	comment varchar,
	item_id integer references items(id)
);
create table attaches_of_items (
	id serial primary key,
	attached varchar,
	item_id integer references items(id)
);
insert into roles (role_name) values ('user');
insert into roles (role_name) values ('admin');
insert into rules (rule_desc) values ('create item');
insert into rules (rule_desc) values ('delete item');
insert into rules (rule_desc) values ('change priority');
insert into state_of_items (state_name) values ('accepted');
insert into state_of_items (state_name) values ('under consideration');
insert into state_of_items (state_name) values ('closed');
insert into category_of_items (category_name) values ('item');
insert into category_of_items (category_name) values ('task');
insert into category_of_items (category_name) values ('complain');
insert into roles_rules (role_id, rule_id) values (1, 1);
insert into roles_rules (role_id, rule_id) values (1, 2);
insert into roles_rules (role_id, rule_id) values (2, 2);
insert into roles_rules (role_id, rule_id) values (2, 3);
insert into users (name, role_id) values ('user 1', 1);
insert into users (name, role_id) values ('user 2', 1);
insert into users (name, role_id) values ('admin 1', 2);
insert into items (name, user_id, state_id, category_id) values ('item 1', 1, 1, 1);
insert into items (name, user_id, state_id, category_id) values ('item 2', 2, 1, 1);
insert into items (name, user_id, state_id, category_id) values ('complain 1', 2, 2, 3);
insert into comments_of_items (comment, item_id) values ('new item', 1);
insert into comments_of_items (comment, item_id) values ('new item 2', 2);
insert into comments_of_items (comment, item_id) values ('new complain', 3);
insert into attaches_of_items (attached, item_id) values ('file', 2);
insert into attaches_of_items (attached, item_id) values ('text', 3);



