### Prepared Statements in JDBC

**PreparedStatement** is an interface in the Java **JDBC API** that allows you to execute parameterized SQL queries. It
is an enhancement over the regular **Statement** interface and offers several advantages, such as improved performance
and security (mainly against SQL injection attacks).

### Key Features of Prepared Statements:

1. **Parameterized Queries**: Instead of embedding values directly into the SQL query, placeholders (`?`) are used.
   These placeholders are then replaced with actual values at runtime.

2. **Security (Prevents SQL Injection)**: Since the values are passed separately from the SQL command, it reduces the
   risk of SQL injection attacks.

3. **Performance Optimization**: Prepared SQL queries are precompiled by the database, so they can be reused efficiently
   with different parameters without recompilation.

4. **Better Code Readability and Maintenance**: Using placeholders makes the code cleaner and easier to maintain,
   especially when dealing with complex or frequently repeated queries.

### How Prepared Statements Work:

1. A SQL query is defined with placeholders (`?`).
2. The query is precompiled when the `PreparedStatement` object is created.
3. Values for the placeholders are set using methods like `setString()`, `setInt()`, etc.
4. The prepared statement is then executed using either `executeQuery()` for **SELECT** statements or `executeUpdate()`
   for **INSERT**, **UPDATE**, or **DELETE** statements.

### Syntax of Prepared Statement:

```java
PreparedStatement pstmt = con.prepareStatement("SQL Query with ? placeholders");
pstmt.

setXXX(index, value); // Replace placeholders with actual values
pstmt.

execute();  // Executes the query
```

### Example:

Suppose we have a `users` table with `id`, `name`, `email`, and `age` columns.

```sql
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    email VARCHAR(50),
    age INT
);
```

### **1. Inserting Data Using PreparedStatement:**

Here’s an example of using a `PreparedStatement` to insert data into the `users` table:

```java
// Step 1: Establish the connection
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "password");

// Step 2: Create a PreparedStatement with an INSERT query
String insertSQL = "INSERT INTO users (name, email, age) VALUES (?, ?, ?)";
PreparedStatement pstmt = con.prepareStatement(insertSQL);

// Step 3: Set the values for the placeholders
pstmt.

setString(1,"John Doe");
pstmt.

setString(2,"john.doe@example.com");
pstmt.

setInt(3,30);

// Step 4: Execute the query
int rowsAffected = pstmt.executeUpdate();

if(rowsAffected >0){
        System.out.

println("A new user was inserted successfully!");
}

// Step 5: Close the resources
        pstmt.

close();
con.

close();
```

### **2. Selecting Data Using PreparedStatement:**

Here’s an example of retrieving data using a `PreparedStatement` with a **SELECT** query:

```java
// Step 1: Establish the connection
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "password");

// Step 2: Create a PreparedStatement with a SELECT query
String selectSQL = "SELECT * FROM users WHERE age > ?";
PreparedStatement pstmt = con.prepareStatement(selectSQL);

// Step 3: Set the value for the placeholder
pstmt.

setInt(1,25);

// Step 4: Execute the query
ResultSet rs = pstmt.executeQuery();

// Step 5: Process the ResultSet
while(rs.

next()){
int id = rs.getInt("id");
String name = rs.getString("name");
String email = rs.getString("email");
int age = rs.getInt("age");

    System.out.

println("ID: "+id +", Name: "+name+", Email: "+email+", Age: "+age);
}

// Step 6: Close the resources
        rs.

close();
pstmt.

close();
con.

close();
```

### **3. Updating Data Using PreparedStatement:**

Here’s an example of using a `PreparedStatement` to update records in the `users` table:

```java
// Step 1: Establish the connection
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "password");

// Step 2: Create a PreparedStatement with an UPDATE query
String updateSQL = "UPDATE users SET email = ? WHERE id = ?";
PreparedStatement pstmt = con.prepareStatement(updateSQL);

// Step 3: Set the values for the placeholders
pstmt.

setString(1,"new.email@example.com");
pstmt.

setInt(2,1);  // Assuming we want to update the user with id = 1

// Step 4: Execute the query
int rowsAffected = pstmt.executeUpdate();

if(rowsAffected >0){
        System.out.

println("User email was updated successfully!");
}

// Step 5: Close the resources
        pstmt.

close();
con.

close();
```

### **4. Deleting Data Using PreparedStatement:**

Here’s an example of deleting records from the `users` table using a `PreparedStatement`:

```java
// Step 1: Establish the connection
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "password");

// Step 2: Create a PreparedStatement with a DELETE query
String deleteSQL = "DELETE FROM users WHERE id = ?";
PreparedStatement pstmt = con.prepareStatement(deleteSQL);

// Step 3: Set the value for the placeholder
pstmt.

setInt(1,1);  // Assuming we want to delete the user with id = 1

// Step 4: Execute the query
int rowsAffected = pstmt.executeUpdate();

if(rowsAffected >0){
        System.out.

println("User was deleted successfully!");
}

// Step 5: Close the resources
        pstmt.

close();
con.

close();
```

### Key Methods in `PreparedStatement`:

1. **`setString(int parameterIndex, String value)`**: Sets a string value at the specified placeholder index.
2. **`setInt(int parameterIndex, int value)`**: Sets an integer value at the specified placeholder index.
3. **`setDouble(int parameterIndex, double value)`**: Sets a double value at the specified placeholder index.
4. **`setDate(int parameterIndex, Date value)`**: Sets a date value at the specified placeholder index.
5. **`executeQuery()`**: Used for executing SELECT queries, returns a `ResultSet` object.
6. **`executeUpdate()`**: Used for executing INSERT, UPDATE, or DELETE queries, returns an integer indicating the number
   of rows affected.
7. **`execute()`**: Used for executing any kind of SQL query, returns a boolean indicating whether the query returns a
   result set (true for SELECT queries).

### Advantages of Using Prepared Statements:

1. **Prevents SQL Injection**: Since the values are passed separately from the SQL query, attackers cannot inject
   malicious SQL code.

   **Example of SQL Injection Prevention:**
    - If you use a regular `Statement` with concatenated SQL strings, an attacker might input something like:
      ```java
      String sql = "SELECT * FROM users WHERE name = '" + userInput + "'";
      ```
      If `userInput` is `"'; DELETE FROM users; --"`, this could delete all records in the table.
    - With `PreparedStatement`, the input is treated as a literal value, not executable SQL.

2. **Performance**: Since prepared statements are precompiled, they can be executed multiple times with different
   parameters without the overhead of compiling the SQL statement each time.

3. **Code Maintainability**: Using placeholders makes the code cleaner and easier to maintain, especially when dealing
   with complex queries.

4. **Type Safety**: Prepared statements enforce type checking (e.g., you cannot mistakenly pass a string where an
   integer is expected).

### Summary:

- **PreparedStatement** is a powerful and secure way to execute parameterized SQL queries in JDBC.
- It offers performance benefits due to query precompilation and helps prevent SQL injection attacks.
- It's recommended to use `PreparedStatement` over `Statement` for any queries that involve user input or are executed
  frequently with varying parameters.