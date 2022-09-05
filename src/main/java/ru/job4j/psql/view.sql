 select s.name as student , a.name as author, b.name as title from students s
	join orders o on s.id = o.student_id
	join books b on b.id = o.book_id
	join authors a on a.id = b.author_id
	where s.name in (select s.name from students s
					join orders o on s.id = o.student_id
	   				join books b on b.id = o.book_id
	  				group by s.name having count(b.name) >= 3);