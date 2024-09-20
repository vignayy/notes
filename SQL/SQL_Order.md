### Order of Execution of a Query

#### Complete SELECT Query

```sql
SELECT DISTINCT column, AGG_FUNC(column_or_expression), …
FROM mytable
    JOIN another_table
      ON mytable.column = another_table.column
    WHERE constraint_expression
    GROUP BY column
    HAVING constraint_expression
    ORDER BY column ASC/DESC
    LIMIT count OFFSET COUNT;
```

`Each query begins with finding the data that we need in a database, and then filtering that data down into something that can be processed and understood as quickly as possible. Because each part of the query is executed sequentially, it's important to understand the order of execution so that you know what results are accessible where.`

#### Query Order of Execution

1. **FROM and JOINs**
    - The FROM clause, and subsequent JOINs are first executed to determine the total working set of data being queried.
    - This includes subqueries in this clause and can cause temporary tables to be created under the hood containing all
      the columns and rows of the tables being joined.

2. **WHERE**
    - Once the total working set of data is determined, the first-pass WHERE constraints are applied to the individual
      rows.
    - Rows that do not satisfy the constraint are discarded.
    - Each of the constraints can only access columns directly from the tables requested in the FROM clause.
    - Aliases in the SELECT part of the query are not accessible in most databases since they may include expressions
      dependent on parts of the query that have not yet executed.

3. **GROUP BY**
    - The remaining rows after the WHERE constraints are applied are then grouped based on common values in the column
      specified in the GROUP BY clause.
    - As a result of the grouping, there will only be as many rows as there are unique values in that column.
    - Implicitly, this means that you should only need to use this when you have aggregate functions in your query.

4. **HAVING**
    - If the query has a GROUP BY clause, then the constraints in the HAVING clause are applied to the grouped rows.
    - Grouped rows that don't satisfy the constraint are discarded.
    - Like the WHERE clause, aliases are also not accessible from this step in most databases.

5. **SELECT**
    - Any expressions in the SELECT part of the query are finally computed.

6. **DISTINCT**
    - Of the remaining rows, rows with duplicate values in the column marked as DISTINCT will be discarded.

7. **ORDER BY**
    - If an order is specified by the ORDER BY clause, the rows are then sorted by the specified data in either
      ascending or descending order.
    - Since all the expressions in the SELECT part of the query have been computed, you can reference aliases in this
      clause.

8. **LIMIT / OFFSET**
    - Finally, the rows that fall outside the range specified by the LIMIT and OFFSET are discarded, leaving the final
      set of rows to be returned from the query.

#### Conclusion

- Not every query needs to have all the parts listed above.
- A part of why SQL is so flexible is that it allows developers and data analysts to quickly manipulate data without
  having to write additional code, all just by using the above clauses.

---

Certainly! Understanding the order of execution in an SQL query is crucial for writing effective queries and debugging
them. Here’s a detailed explanation of each part of the execution process:

### 1. FROM and JOINs

**Role:**

- **Purpose:** This step is responsible for determining the initial dataset that you’re working with. It specifies which
  tables or subqueries provide the data.
- **Execution:**
    - **Tables and Subqueries:** The `FROM` clause identifies the tables or subqueries to retrieve data from.
    - **Joins:** If there are multiple tables involved, the `JOIN` clauses are used to combine rows from different
      tables based on related columns. This step creates a temporary result set that includes all the rows from the
      joined tables.
    - **Temporary Tables:** Some databases might create temporary tables or intermediate results as part of the join
      operation, though this is abstracted from the user.

### 2. WHERE

**Role:**

- **Purpose:** The `WHERE` clause filters the rows from the dataset obtained in the `FROM` and `JOIN` steps. It
  eliminates rows that do not meet the specified conditions.
- **Execution:**
    - **Filtering:** Each row is evaluated against the conditions specified in the `WHERE` clause.
    - **Direct Access:** Only columns from the `FROM` and `JOIN` clauses are accessible here. Aliases defined in
      the `SELECT` clause are not yet available for use.

### 3. GROUP BY

**Role:**

- **Purpose:** The `GROUP BY` clause groups the remaining rows into subsets that have the same values in specified
  columns. This is usually combined with aggregate functions to summarize data.
- **Execution:**
    - **Grouping:** Rows are grouped based on the column(s) listed in the `GROUP BY` clause.
    - **Aggregation:** Aggregate functions like `SUM`, `COUNT`, `AVG`, etc., operate on these groups to produce summary
      values.

### 4. HAVING

**Role:**

- **Purpose:** The `HAVING` clause filters the grouped data. It is similar to the `WHERE` clause but operates on groups
  rather than individual rows.
- **Execution:**
    - **Filtering Groups:** After grouping, the `HAVING` clause applies its conditions to the groups created by
      the `GROUP BY` clause.
    - **Access:** Aliases from the `SELECT` clause are not yet available at this stage.

### 5. SELECT

**Role:**

- **Purpose:** The `SELECT` clause specifies which columns or expressions should be included in the final result set.
- **Execution:**
    - **Expression Evaluation:** This step calculates the values for the columns and expressions listed in the `SELECT`
      clause.
    - **Aliases:** Aliases defined in this step can be used in subsequent steps like `ORDER BY`.

### 6. DISTINCT

**Role:**

- **Purpose:** The `DISTINCT` keyword ensures that duplicate rows are removed from the result set.
- **Execution:**
    - **Duplicate Removal:** Rows are compared, and duplicates are discarded, leaving only unique rows in the result
      set.

### 7. ORDER BY

**Role:**

- **Purpose:** The `ORDER BY` clause sorts the final result set based on specified columns or expressions.
- **Execution:**
    - **Sorting:** The result set is ordered according to the criteria defined in the `ORDER BY` clause.
    - **Access:** At this point, you can use column aliases and expressions from the `SELECT` clause for sorting.

### 8. LIMIT / OFFSET

**Role:**

- **Purpose:** The `LIMIT` and `OFFSET` clauses control the number of rows returned and can specify which rows to start
  from.
- **Execution:**
    - **Limiting:** The `LIMIT` clause restricts the number of rows in the result set to a specified number.
    - **Offset:** The `OFFSET` clause skips a specified number of rows before starting to return rows.

### Conclusion

This order of execution ensures that SQL queries are processed efficiently and logically. By understanding this
sequence, you can better predict how your queries will behave and troubleshoot issues. Not all queries will use every
clause, and the flexibility of SQL allows you to tailor your queries to meet your specific needs while adhering to this
logical processing order.