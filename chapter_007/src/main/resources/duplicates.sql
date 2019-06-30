delete from cities where id not in (
	select id from
		(select max(id) as id
		from cities
		group by name
		having count(id) > 1

		union select max(id) as id
		from cities
		group by name
		having count(id) = 1) as dubl
);