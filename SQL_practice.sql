Problem 1:
SELECT name, salary
FROM employees
WHERE salary > 80000;

Problem 2:
SELECT *
FROM employees
WHERE age > 30
AND department IN ('CS')

Problem 3:
SELECT name, age
FROM employees
ORDER BY age DESC;

Problem 4:
SELECT DISTINCT department
FROM employees
WHERE salary BETWEEN 50000 AND 100000;

Problem 5:
SELECT name
FROM employees
WHERE department IN ('CS', 'Finance');

Problem 6:
SELECT name
FROM employees
WHERE name = 'A%';

Problem 7:
SELECT department, COUNT(*)
FROM employees
GROUP BY department;

Problem 8:
SELECT department, AVG(salary)
FROM employees
GROUP BY department;

Problem 9:
SELECT department, role, MAX(salary)
FROM employees
GROUP BY department, role;

Problem 10: x
SELECT department, COUNT(*)
FROM employees
HAVING COUNT(*) > 3;

correct:
SELECT department
FROM employees
GROUP BY department
HAVING COUNT(*) > 3;

Problem 11: x
SELECT department, AVG(salary)
FROM employees
WHERE age > 35
HAVING AVG(salary) > 90000;

correct:
SELECT department
FROM employees
WHERE age > 35
GROUP BY department
HAVING AVG(salary) > 90000;

Problem 12: x
SELECT role, MAX(AVG(salary))
FROM employees
GROUP BY role
LIMIT 2;

SELECT role, AVG(salary) AS avg_salary
FROM employees
GROUP BY role
ORDER BY avg_salary DESC
LIMIT 2;




Problem D
SELECT department, AVG(salary) AS avg_salary
FROM employees
WHERE age > 30
GROUP BY department;

Problem E
SELECT role, department, COUNT(*)
FROM employees
GROUP BY department
HAVING COUNT(*) > 1;

Problem F
SELECT department
FROM employees
GROUP BY department
HAVING MAX(salary) > 120000;

Problem G
SELECT department
FROM employees
WHERE age BETWEEN 25 AND 40
GROUP BY department
ORDER BY AVG(salary)DESC
LIMIT 3;

Problem H
Because salary is not aggregated nor grouped


A1
SELECT name
FROM employees
WHERE department = 'CS'
AND salary > AVG(salary)

A2
SELECT department
FROM employees
GROUP BY department
HAVING MIN(salary) > 60000

A3
SELECT name, department, salary
FROM employees
ORDER BY salary DESC
LIMIT 5

B1
SELECT department
FROM employees
WHERE age > 25
GROUP BY department
HAVING COUNT(name) > 3

B2
SELECT role
FROM employees
GROUP BY roles
HAVING AVG(salary) > (
	SELECT AVG(salary)
	FROM employees
);

C1
SELECT department, role, COUNT(*)
FROM employees
GROUP BY department, role
HAVING COUNT(*) > 1

C2
SELECT department
FROM employees
GROUP BY department
HAVING COUNT(DISTINCT role) >= 2

D1
SELECT name
FROM employees
WHERE department AS dep1 = (
	SELECT department
	FROM employee) AND
      salary > (
	SELECT AVG(salary)
	FROM employee
	WHERE department = dep1)

D2

SELECT salary
FROM (SELECT salary
	FROM employee
	WHERE salary < MAX(salary)
	)
WHERE salary = MAX(salary)
      

E1
SELECT employee
FROM employees
WHERE salary > (SELECT AVG(salary)
		FROM employee
		);

E2
SELECT department
FROM employee
GROUP BY department
HAVING AVG(salary) > (SELECT AVG(salary)
			FROM employee
			WHERE department = 'HR'
			);

E3
SELECT employee
FROM employees
WHERE salary = (SELECT MIN(salary)
		FROM employees
		);

F1
SELECT employee
FROM employees e
WHERE salary > (SELECT AVG(salary)
		FROM employees
		WHERE department = e.department
		)

F2
SELECT employee
FROM employees e
WHERE salary = (SELECT MAX(salary)
		FROM employees
		WHERE department = e.department
		)

F3
SELECT employee
FROM employee e
WHERE age > (SELECT AVG(age)
		FROM employees
		WHERE department = e.department
		)


G1
SELECT department
FROM employees
GROUP BY department
HAVING AVG(salary) > 80000 AND COUNT(*) >= 4;

G2
SELECT role
FROM employees
GROUP BY role
HAVING MAX(salary) > 2 * MIN(salary);

H1
SELECT DISTINCT MAX(salary)
FROM employees
WHERE salary < (SELECT DISTINCT MAX(salary)
		FROM employee
		WHERE salary < (SELECT DISTINCT MAX(salary)
				FROM employee
				)
		)

H2
SELECT department
FROM employees
GROUP BY department
HAVING SUM(salary) > (SELECT AVG(salary)
			FROM employees
			GROUP BY department

???

H3
It returns the number of employees for each department whose salaries are higher than the company's average salary'


R1
SELECT department, AVG(salary)
FROM employees
GROUP BY department

R2
SELECT AVG(avg_department)
FROM (SELECT department, AVG(salary) AS avg_department
	FROM employees
	GROUP BY department
	) 

R3
SELECT department
FROM employees
GROUP BY department
HAVING AVG(salary) > (SELECT AVG(avg_department)
			FROM (SELECT department, AVG(salary) AS avg_department
				FROM employees
				GROUP BY department
				)
			)

			



JOIN

Q1
SELECT u.id, u.name, COALESCE(COUNT(o.id), 0)
FROM users u
LEFT JOIN orders o
   ON u.id = o.user_id
GROUP BY u.id, u.name

Q2
SELECT u.id, u.name
FROM users u
LEFT JOIN orders o
   ON u.id = o.user_id
GROUP BY u.id, u.name
HAVING COUNT(o.id) = 0;

Q3
SELECT o.id AS order_id,
       COALESCE(u.name, 'missing name') AS user_name,
       o.total
FROM orders o
LEFT JOIN users u
  ON o.user_id = u.id;

Q4
SELECT u.id, u.name, COALESCE(SUM(o.total), 0)
FROM users u
LEFT JOIN orders o
  ON u.id = o.user_id
GROUP BY u.id, u.name;

Q5
SELECT u.id, u.name
FROM users u
INNER JOIN orders o
  ON u.id = o.user_id
GROUP BY u.id, u.name
HAVING COUNT(o.id) >= 2

Q6
SELECT u.id, u.name, AVG(o.total) AS avg_order
FROM users u
LEFT JOIN orders o
  ON u.id = o.user_id
GROUP BY u.id, u.name;

Q7
SELECT p.id, p.name, SUM(oi.quantity * oi,price) AS p_rev
FROM order_items oi
INNER JOIN products p
  ON oi.product_id = p.id
GROUP BY p.id, p.name;

Q8
SELECT p.id, p.name
FROM product p
LEFT JOIN order_items oi
  ON p.id = oi.product_id
GROUP BY p.id, p.name
HAVING COUNT(oi.order_id) = 0;

Q9
SELECT u.id, u.name
FROM users u
JOIN orders o
  ON u.id = o.user_id
GROUP BY u.id, u.name
HAVING SUM(o.total) > (
  SELECT AVG(user_total)
  FROM (
    SELECT user_id, SUM(total) AS user_total
    FROM orders
    GROUP BY user_id
  ) t
);

Q10
SELECT u.id, u.name
FROM users u
JOIN orders o
  ON u.id = o.user_id
GROUP BY u.id, u.name
HAVING COUNT(o.id) > (
  SELECT COUNT(o2.id) * 1.0 / COUNT(DISTINCT u2.id)
  FROM users u2
  LEFT JOIN orders o2
    ON u2.id = o2.user_id
);

Q11
SELECT u.id,
       u.name,
       COALESCE(t.user_revenue, 0) AS total_revenue
FROM users u
LEFT JOIN (
  SELECT o.user_id,
         SUM(oi.quantity * oi.price) AS user_revenue
  FROM orders o
  JOIN order_items oi
    ON o.id = oi.order_id
  GROUP BY o.user_id
) t
  ON u.id = t.user_id;


DAY3 Q1
SELECT u.id, u.name
FROM users u
WHERE u.id IN (SELECT o.user_id
               FROM orders o
);

Q2
SELELCT u.id, u.name
FROM users u
WHERE u.id NOT IN (SELECT o.user_id
			       FROM orders o
);
