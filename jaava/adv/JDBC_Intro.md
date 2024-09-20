### JDBC (Java Database Connectivity)

**JDBC** is an API in Java that enables communication between Java applications and a wide range of databases. It allows
Java programs to send queries and update statements to databases and retrieve the results.

#### Key Components of JDBC:

1. **JDBC Driver**: A software component that implements the JDBC API for a specific database. It acts as a bridge
   between Java applications and databases.
    - Types of JDBC Drivers:
        - **Type 1: JDBC-ODBC Bridge Driver**: Translates JDBC calls into ODBC (Open Database Connectivity) calls.
        - **Type 2: Native-API Driver**: Converts JDBC calls into native database API calls.
        - **Type 3: Network Protocol Driver**: Translates JDBC calls into a database-independent network protocol.
        - **Type 4: Thin Driver**: Directly converts JDBC calls into the database's native protocol without needing
          middle-tier software.

2. **Connection Interface**: This interface establishes a connection with the database. It is like a session between the
   Java application and the database.
    - Example:
      ```java
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_name", "username", "password");
      ```

3. **DriverManager**: Manages a list of database drivers and helps establish a connection between a database and the
   appropriate driver.

4. **Statement Interface**: Used to execute SQL queries and commands.
    - Types of Statements:
        - **Statement**: For simple SQL queries without parameters.
        - **PreparedStatement**: For queries with input parameters. It is more efficient and secure.
        - **CallableStatement**: Used to call stored procedures in the database.

5. **ResultSet Interface**: Represents the result set of a SQL query. It provides methods to iterate through the results
   and fetch data.
    - Example:
      ```java
      ResultSet rs = stmt.executeQuery("SELECT * FROM users");
      while (rs.next()) {
          System.out.println(rs.getString("name"));
      }
      ```

6. **SQLException**: Exception class for handling database errors.

#### Key Steps in JDBC Programming:

1. **Load the JDBC Driver**:
    - Example: `Class.forName("com.mysql.cj.jdbc.Driver");`

2. **Establish a Connection**:
    - Use the `DriverManager.getConnection()` method.

3. **Create a Statement**:
    - Example: `Statement stmt = con.createStatement();`

4. **Execute SQL Queries**:
    - Example: `ResultSet rs = stmt.executeQuery("SELECT * FROM users");`

5. **Process the Results**:
    - Use the `ResultSet` to iterate through the results.

6. **Close the Connection**:
    - Example: `con.close();`

#### Example Code:

```java
import java.sql.*;

public class JdbcExample {
   public static void main(String[] args) {
      try {
         // Step 1: Load the driver
         Class.forName("com.mysql.cj.jdbc.Driver");

         // Step 2: Establish connection
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "password");

         // Step 3: Create a statement
         Statement stmt = con.createStatement();

         // Step 4: Execute a query
         ResultSet rs = stmt.executeQuery("SELECT * FROM users");

         // Step 5: Process the result
         while (rs.next()) {
            System.out.println(rs.getString("name"));
         }

         // Step 6: Close the connection
         con.close();

      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
```

#### Advantages of JDBC:

- **Cross-Platform**: Works with any database that has a compatible JDBC driver.
- **Supports Multiple Databases**: MySQL, Oracle, PostgreSQL, etc.
- **Secure**: Uses PreparedStatement to prevent SQL injection.
- **Portability**: JDBC allows switching between databases without changing the core code.

JDBC is a critical component for database interaction in Java, allowing robust and secure database operations.