select p.name, c.name from person as p
inner join company as c on p.company_id = c.id
where c.id <> 5;

select c.name, count(c.name) from company as c
inner join person as p on p.company_id = c.id
group by c.name
order by count(c.name) DESC
limit 1;