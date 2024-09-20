**Joins** in SQL are used to combine rows from two or more tables based on a related column between them. By using
joins, you can retrieve data from multiple tables as if they were a single table.

Here's a detailed breakdown of different types of joins in SQL:

### 1. **INNER JOIN**

An **INNER JOIN** retrieves records that have matching values in both tables. It only returns the rows where there is a
match between the columns being joined.

#### Syntax:

```sql
SELECT column_name(s)
FROM table1
INNER JOIN table2
ON table1.common_column = table2.common_column;
```

#### Example:

Consider two tables: `employees` and `departments`.

`employees` table:
| id | name | dept_id |
| --- | ----- | ------- |
| 1 | John | 101 |
| 2 | Alice | 102 |
| 3 | Bob | 103 |

`departments` table:
| dept_id | dept_name |
| ------- | ----------- |
| 101 | HR |
| 102 | IT |
| 104 | Marketing |

Query using `INNER JOIN`:

```sql
SELECT employees.name, departments.dept_name
FROM employees
INNER JOIN departments ON employees.dept_id = departments.dept_id;
```

**Result:**
| name | dept_name |
| ----- | --------- |
| John | HR |
| Alice | IT |

- The row for `Bob` from the `employees` table is not returned because `dept_id = 103` does not exist in
  the `departments` table.

### 2. **LEFT JOIN (LEFT OUTER JOIN)**

A **LEFT JOIN** returns all records from the left (first) table, and the matched records from the right (second) table.
If there is no match, the result is NULL for the right table's columns.

#### Syntax:

```sql
SELECT column_name(s)
FROM table1
LEFT JOIN table2
ON table1.common_column = table2.common_column;
```

#### Example:

Using the same `employees` and `departments` tables, this query retrieves all employees, even those without a
department.

```sql
SELECT employees.name, departments.dept_name
FROM employees
LEFT JOIN departments ON employees.dept_id = departments.dept_id;
```

**Result:**
| name | dept_name |
| ----- | --------- |
| John | HR |
| Alice | IT |
| Bob | NULL |

- The row for `Bob` is returned, but since `dept_id = 103` has no match in `departments`, `dept_name` is `NULL`.

### 3. **RIGHT JOIN (RIGHT OUTER JOIN)**

A **RIGHT JOIN** is the opposite of a LEFT JOIN. It returns all records from the right (second) table and the matched
records from the left (first) table. If there is no match, the result is NULL for the left table's columns.

#### Syntax:

```sql
SELECT column_name(s)
FROM table1
RIGHT JOIN table2
ON table1.common_column = table2.common_column;
```

#### Example:

```sql
SELECT employees.name, departments.dept_name
FROM employees
RIGHT JOIN departments ON employees.dept_id = departments.dept_id;
```

**Result:**
| name | dept_name |
| ----- | --------- |
| John | HR |
| Alice | IT |
| NULL | Marketing |

- The row for the `Marketing` department (which has no employees) is returned, with `NULL` in the `name` column for
  employees.

### 4. **FULL JOIN (FULL OUTER JOIN)**

A **FULL JOIN** returns all records when there is a match in either left (first) or right (second) table. If there is no
match, the result is NULL from the table without a match. MySQL doesn't natively support `FULL JOIN`, but it can be
achieved using a `UNION` of `LEFT JOIN` and `RIGHT JOIN`.

#### Syntax:

```sql
SELECT column_name(s)
FROM table1
FULL JOIN table2
ON table1.common_column = table2.common_column;
```

#### Example:

To simulate `FULL JOIN` in MySQL:

```sql
SELECT employees.name, departments.dept_name
FROM employees
LEFT JOIN departments ON employees.dept_id = departments.dept_id
UNION
SELECT employees.name, departments.dept_name
FROM employees
RIGHT JOIN departments ON employees.dept_id = departments.dept_id;
```

**Result:**
| name | dept_name |
| ----- | --------- |
| John | HR |
| Alice | IT |
| Bob | NULL |
| NULL | Marketing |

- It returns all rows from both tables, with `NULL` where no match is found.

### 5. **CROSS JOIN**

A **CROSS JOIN** returns the Cartesian product of the two tables. Each row from the first table is combined with every
row from the second table. There are no conditions in this type of join, which means every possible combination of rows
from both tables will be in the result set.

#### Syntax:

```sql
SELECT column_name(s)
FROM table1
CROSS JOIN table2;
```

#### Example:

```sql
SELECT employees.name, departments.dept_name
FROM employees
CROSS JOIN departments;
```

**Result:**
| name | dept_name |
| ----- | --------- |
| John | HR |
| John | IT |
| John | Marketing |
| Alice | HR |
| Alice | IT |
| Alice | Marketing |
| Bob | HR |
| Bob | IT |
| Bob | Marketing |

- The result shows all possible combinations of rows between the two tables.

### 6. **SELF JOIN**

A **SELF JOIN** is a join in which a table is joined with itself. This is useful when you need to compare rows within
the same table, like finding hierarchical relationships.

#### Syntax:

```sql
SELECT a.column_name, b.column_name
FROM table a
JOIN table b
ON a.common_column = b.common_column;
```

#### Example:

Consider a table `employees` with a `manager_id` that references the `id` of another employee who is the manager.

`employees` table:
| id | name | manager_id |
| --- | ------ | ---------- |
| 1 | John | NULL |
| 2 | Alice | 1 |
| 3 | Bob | 1 |
| 4 | Charlie| 2 |

To retrieve employees and their managers:

```sql
SELECT e1.name AS employee, e2.name AS manager
FROM employees e1
LEFT JOIN employees e2 ON e1.manager_id = e2.id;
```

**Result:**
| employee | manager |
| -------- | ------- |
| John | NULL |
| Alice | John |
| Bob | John |
| Charlie | Alice |

- Here, the table is joined with itself to find each employee’s manager.

### 7. **NATURAL JOIN**

A **NATURAL JOIN** automatically joins tables based on columns with the same name and compatible data types in both
tables. It simplifies the join but should be used with caution since it may produce unexpected results if multiple
columns have the same name.

#### Syntax:

```sql
SELECT column_name(s)
FROM table1
NATURAL JOIN table2;
```

#### Example:

Assume the `employees` and `departments` tables have a common column `dept_id`.

```sql
SELECT * FROM employees
NATURAL JOIN departments;
```

- It automatically joins based on the `dept_id` column (no need to explicitly mention it).

### 8. **EQUI JOIN**

An **EQUI JOIN** is a type of join that uses an equality condition (`=`) to join tables. It is essentially
an `INNER JOIN` but explicitly uses the `=` operator.

#### Syntax:

```sql
SELECT column_name(s)
FROM table1, table2
WHERE table1.common_column = table2.common_column;
```

#### Example:

```sql
SELECT employees.name, departments.dept_name
FROM employees, departments
WHERE employees.dept_id = departments.dept_id;
```

- This performs the same function as an `INNER JOIN`, but the `WHERE` clause is used to specify the join condition.

### Summary of SQL Joins:

- **INNER JOIN**: Returns rows with matching values in both tables.
- **LEFT JOIN**: Returns all rows from the left table, with NULLs for no matches in the right table.
- **RIGHT JOIN**: Returns all rows from the right table, with NULLs for no matches in the left table.
- **FULL JOIN**: Returns all rows when there is a match in either table (can be simulated with `LEFT JOIN`
  and `RIGHT JOIN`).
- **CROSS JOIN**: Returns the Cartesian product of both tables (all possible combinations).
- **SELF JOIN**: Joins a table to itself.
- **NATURAL JOIN**: Automatically joins tables based on columns with the same name.
- **EQUI JOIN**: Uses equality (`=`) to join tables, typically used in `INNER JOIN`.

Joins are fundamental for retrieving and combining data from multiple tables in relational databases.l̥