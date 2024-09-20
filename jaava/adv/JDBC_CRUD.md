### Performing CRUD Operations Using JDBC

CRUD stands for **Create, Read, Update, and Delete**—the four basic operations for managing data in databases. Here's a
step-by-step guide on how to perform each of these operations using JDBC in Java:

#### Prerequisites:

- **JDBC Driver** for the database you are using (e.g., MySQL).
- A **database** and **table** created in the database for performing CRUD operations.

#### Setup Example (MySQL):

- Database: `testdb`
- Table: `users` with columns `id`, `name`, `email`, and `age`.

```sql
CREATE DATABASE testdb;

USE testdb;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    email VARCHAR(50),
    age INT
);
```

### Steps for CRUD Operations

#### 1. **Load the JDBC Driver**

```java
Class.forName("com.mysql.cj.jdbc.Driver");
```

This step loads the MySQL JDBC driver, making it ready for use.

#### 2. **Establish the Database Connection**

```java
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "password");
```

This step connects to the `testdb` database using the `getConnection()` method.

### **Create (Insert Data)**

To insert a new record into the database, you can use either a **Statement** or a **PreparedStatement**. Using
a `PreparedStatement` is recommended for security and efficiency.

#### Example of Inserting Data:

```java
String insertQuery = "INSERT INTO users (name, email, age) VALUES (?, ?, ?)";
PreparedStatement pstmt = con.prepareStatement(insertQuery);
pstmt.

setString(1,"John Doe");
pstmt.

setString(2,"john.doe@example.com");
pstmt.

setInt(3,30);

int rowsAffected = pstmt.executeUpdate(); // Returns number of rows affected
if(rowsAffected >0){
        System.out.

println("Data inserted successfully.");
}
```

- Here, `?` is a placeholder for dynamic values, which are set using `pstmt.setXXX()` methods.
- The `executeUpdate()` method executes the insert statement and returns the number of rows affected.

### **Read (Retrieve Data)**

To read data from the database, use the **SELECT** SQL query and execute it with a `Statement` or `PreparedStatement`.

#### Example of Retrieving Data:

```java
String selectQuery = "SELECT * FROM users";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(selectQuery);

while(rs.

next()){
int id = rs.getInt("id");
String name = rs.getString("name");
String email = rs.getString("email");
int age = rs.getInt("age");
    System.out.

println("ID: "+id +", Name: "+name+", Email: "+email+", Age: "+age);
}
```

- The `executeQuery()` method is used for `SELECT` queries and returns a `ResultSet` object containing the result.
- The `ResultSet` object allows you to iterate through the retrieved rows and fetch individual columns using methods
  like `getInt()`, `getString()`, etc.

### **Update (Modify Data)**

To update existing records in the database, use the **UPDATE** SQL query. Again, it's best to use `PreparedStatement`
for parameterized queries.

#### Example of Updating Data:

```java
String updateQuery = "UPDATE users SET name = ?, email = ?, age = ? WHERE id = ?";
PreparedStatement pstmt = con.prepareStatement(updateQuery);
pstmt.

setString(1,"Jane Doe");
pstmt.

setString(2,"jane.doe@example.com");
pstmt.

setInt(3,28);
pstmt.

setInt(4,1); // Assuming we want to update the user with ID = 1

int rowsAffected = pstmt.executeUpdate();
if(rowsAffected >0){
        System.out.

println("Data updated successfully.");
}
```

- The `executeUpdate()` method is used for the `UPDATE` query and returns the number of affected rows.

### **Delete (Remove Data)**

To delete records from the database, use the **DELETE** SQL query.

#### Example of Deleting Data:

```java
String deleteQuery = "DELETE FROM users WHERE id = ?";
PreparedStatement pstmt = con.prepareStatement(deleteQuery);
pstmt.

setInt(1,1); // Assuming we want to delete the user with ID = 1

int rowsAffected = pstmt.executeUpdate();
if(rowsAffected >0){
        System.out.

println("Data deleted successfully.");
}
```

- Just like with the `UPDATE` operation, `executeUpdate()` is used for `DELETE` queries, and it returns the number of
  rows affected.

### **Closing the Resources**

It's essential to close all database resources (ResultSet, Statement, and Connection) to prevent memory leaks.

#### Example of Closing Resources:

```java
rs.close();      // Close the ResultSet
stmt.

close();    // Close the Statement
con.

close();     // Close the Connection
```

### Full Example of CRUD Operations:

```java
import java.sql.*;

public class JdbcCRUD {
    public static void main(String[] args) {
        try {
            // 1. Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establish the connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "password");

            // --- Create (Insert) ---
            String insertQuery = "INSERT INTO users (name, email, age) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(insertQuery);
            pstmt.setString(1, "John Doe");
            pstmt.setString(2, "john.doe@example.com");
            pstmt.setInt(3, 30);
            pstmt.executeUpdate();

            // --- Read (Select) ---
            String selectQuery = "SELECT * FROM users";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") +
                        ", Email: " + rs.getString("email") + ", Age: " + rs.getInt("age"));
            }

            // --- Update ---
            String updateQuery = "UPDATE users SET name = ?, email = ?, age = ? WHERE id = ?";
            pstmt = con.prepareStatement(updateQuery);
            pstmt.setString(1, "Jane Doe");
            pstmt.setString(2, "jane.doe@example.com");
            pstmt.setInt(3, 28);
            pstmt.setInt(4, 1);
            pstmt.executeUpdate();

            // --- Delete ---
            String deleteQuery = "DELETE FROM users WHERE id = ?";
            pstmt = con.prepareStatement(deleteQuery);
            pstmt.setInt(1, 1);
            pstmt.executeUpdate();

            // 3. Close the resources
            rs.close();
            stmt.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### Summary of JDBC CRUD Operations:

1. **Create**: Insert data using `INSERT` queries.
2. **Read**: Retrieve data using `SELECT` queries.
3. **Update**: Modify existing data using `UPDATE` queries.
4. **Delete**: Remove data using `DELETE` queries.

JDBC offers powerful capabilities to connect Java applications to databases and execute CRUD operations efficiently.