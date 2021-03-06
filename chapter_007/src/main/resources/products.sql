select * from product as p
inner join type as t on t.id = p.type_id
where t.name = 'СЫР';

select * from product as p
where p.name like '%мороженное%';

select * from product as p
where extract(month from p.expired_date) = extract(month from now()) + 1;

select * from product as p
where p.price = (select max(price) from product);

select count(*) from product as p
inner join type as t on t.id = p.type_id
where t.name = 'МОЛОКО';

select * from product as p
inner join type as t on t.id = p.type_id
where t.name = 'МОЛОКО' or t.name = 'СЫР';

select type_id, count(type_id) from product as p
group by type_id
having count(type_id) < 10;

select p.name, t.name from product as p
inner join type as t on t.id = p.type_id;