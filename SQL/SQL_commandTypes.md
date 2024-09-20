Types of SQL Commands:

### Summary:

- **DDL** (Data Definition Language) commands manage the structure of database objects (
  e.g., `CREATE`, `ALTER`, `DROP`, `TRUNCATE`).
- **DML** (Data Manipulation Language) commands manipulate data within tables (
  e.g., `SELECT`, `INSERT`, `UPDATE`, `DELETE`).
- **DCL** (Data Control Language) commands handle permissions and access control (e.g., `GRANT`, `REVOKE`).
- **TCL** (Transaction Control Language) commands manage transactions (e.g., `BEGIN`, `COMMIT`, `ROLLBACK`).
- **DQL** (Data Query Language) is essentially the `SELECT` statement used for querying data.

---

SQL commands are divided into five primary categories based on their function in database management. Each type of
command serves a specific purpose for interacting with relational databases. Here is a detailed explanation of the
different types of SQL commands along with their key commands:

### 1. **Data Definition Language (DDL)**

DDL commands deal with the structure of database objects like tables, indexes, and schemas. These commands define,
modify, or remove structures in a database.

#### Key DDL Commands:

- **CREATE**: Used to create a new table, database, index, or view.
  ```sql
  CREATE DATABASE my_database;
  CREATE TABLE employees (
      id INT PRIMARY KEY AUTO_INCREMENT,
      name VARCHAR(100),
      position VARCHAR(50),
      salary DECIMAL(10, 2)
  );
  ```

- **ALTER**: Used to modify an existing database object, such as adding, modifying, or deleting a column.
  ```sql
  ALTER TABLE employees ADD COLUMN hire_date DATE;
  ALTER TABLE employees MODIFY COLUMN salary DECIMAL(12, 2);
  ALTER TABLE employees DROP COLUMN hire_date;
  ```

- **DROP**: Used to delete an existing table, database, or view. Dropping a table or database removes all its data
  permanently.
  ```sql
  DROP TABLE employees;
  DROP DATABASE my_database;
  ```

- **TRUNCATE**: Used to delete all records from a table, but it keeps the table structure for future use. It is faster
  than `DELETE` because it does not log individual row deletions.
  ```sql
  TRUNCATE TABLE employees;
  ```

- **RENAME**: Used to rename database objects like tables.
  ```sql
  RENAME TABLE employees TO staff;
  ```

### 2. **Data Manipulation Language (DML)**

DML commands deal with the manipulation of data stored in database tables. These commands help in querying, inserting,
updating, and deleting records.

#### Key DML Commands:

- **SELECT**: Retrieves data from one or more tables. It can use filters like `WHERE`, sorting via `ORDER BY`, and
  grouping through `GROUP BY`.
  ```sql
  SELECT * FROM employees;  -- Select all columns
  SELECT name, salary FROM employees WHERE position = 'Manager';  -- Select specific columns
  ```

- **INSERT**: Adds new records (rows) into a table.
  ```sql
  INSERT INTO employees (name, position, salary) VALUES ('John Doe', 'Engineer', 60000.00);
  INSERT INTO employees (name, position, salary) VALUES 
      ('Alice', 'Manager', 80000.00),
      ('Bob', 'HR', 50000.00);
  ```

- **UPDATE**: Modifies existing records in a table. The `WHERE` clause is used to filter which records to update.
  ```sql
  UPDATE employees SET salary = 65000.00 WHERE name = 'John Doe';
  ```

- **DELETE**: Removes records from a table. It can delete specific records using the `WHERE` clause or delete all
  records without it.
  ```sql
  DELETE FROM employees WHERE id = 3;
  DELETE FROM employees;  -- Delete all records
  ```

### 3. **Data Control Language (DCL)**

DCL commands are related to rights, permissions, and controls of the database system. These commands are used to give or
revoke access privileges to users.

#### Key DCL Commands:

- **GRANT**: Provides access rights or privileges to a user for database objects.
  ```sql
  GRANT SELECT, INSERT ON my_database.* TO 'user1'@'localhost';
  GRANT ALL PRIVILEGES ON employees TO 'user1'@'localhost';
  ```

- **REVOKE**: Removes previously granted access rights or privileges.
  ```sql
  REVOKE INSERT ON my_database.* FROM 'user1'@'localhost';
  ```

- **DENY**: This command explicitly denies certain permissions to users. It is primarily used in Microsoft SQL Server
  and Oracle but not in MySQL.
  ```sql
  DENY INSERT ON employees TO 'user1';
  ```

### 4. **Transaction Control Language (TCL)**

TCL commands deal with the management of transactions in the database. A transaction is a sequence of one or more SQL
operations that are executed as a single unit of work.

#### Key TCL Commands:

- **BEGIN** or **START TRANSACTION**: Marks the beginning of a transaction.
  ```sql
  START TRANSACTION;
  ```

- **COMMIT**: Saves all the changes made during the transaction and makes them permanent in the database.
  ```sql
  COMMIT;
  ```

- **ROLLBACK**: Undoes the changes made during the transaction, effectively canceling them.
  ```sql
  ROLLBACK;
  ```

- **SAVEPOINT**: Sets a point within a transaction to which a rollback can be performed.
  ```sql
  SAVEPOINT save1;
  UPDATE employees SET salary = salary + 1000 WHERE id = 1;
  ROLLBACK TO save1;
  ```

- **SET TRANSACTION**: Used to specify the characteristics of a transaction, such as its isolation level.
  ```sql
  SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
  ```

### 5. **Data Query Language (DQL)**

DQL is focused on querying data from the database. The most important DQL command is `SELECT`. Though `SELECT` is part
of DML, it's often considered a standalone category because of its specific role in querying data.

#### Key DQL Commands:

- **SELECT**: The `SELECT` statement is used to fetch data from a database.
  ```sql
  SELECT name, salary FROM employees WHERE position = 'Manager';
  SELECT COUNT(*) FROM employees;  -- Count number of employees
  SELECT AVG(salary) FROM employees WHERE position = 'Engineer';  -- Calculate average salary of engineers
  ```

### Other Important Concepts:

- **Constraints**: Constraints enforce rules for the data in the database.
    - **NOT NULL**: Ensures that a column cannot have a NULL value.
    - **UNIQUE**: Ensures that all values in a column are unique.
    - **PRIMARY KEY**: Uniquely identifies each record in a table.
    - **FOREIGN KEY**: Ensures referential integrity between tables.
    - **CHECK**: Ensures that a condition must be met before data is inserted or updated.

  Example:
  ```sql
  CREATE TABLE employees (
      id INT PRIMARY KEY AUTO_INCREMENT,
      name VARCHAR(100) NOT NULL,
      salary DECIMAL(10, 2) CHECK (salary > 0),
      dept_id INT,
      FOREIGN KEY (dept_id) REFERENCES departments(dept_id)
  );
  ```

---

Types of SQL Commands:

1. DQL (Data Query Language) : Used to retrieve data from databases. (SELECT)
2. DDL (Data Definition Language) : Used to create, alter, and delete database objects
   like tables, indexes, etc. (CREATE, DROP, ALTER, RENAME, TRUNCATE)
3. DML (Data Manipulation Language): Used to modify the database. (INSERT,
   UPDATE, DELETE)
4. DCL (Data Control Language): Used to grant & revoke permissions. (GRANT,
   REVOKE)
5. TCL (Transaction Control Language): Used to manage transactions. (COMMIT,
   ROLLBACK, START TRANSACTIONS, SAVEPOINT)
