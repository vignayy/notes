package jaava.adv;

import java.sql.*;      // 1 : import package

//      Need To install Drivers -> PostgreSQL Jar files...

public class JDBCIntro {
    public static void main(String[] args) throws Exception {

        /*
            import package
            Load and Register
            create connection
            create statement
            execute connection
            process the results
            close
         */

        String url = "jdbc:postgresql://localhost:5432/demo";     // 5432 : default port no. for postgers
        String uname = "postgres";
        String pass = "postgres";

//        String sqlQuery = "select sname from student where sid = 1";
//        String sqlQuery = "select * from student";
//        String sqlQuery = "insert into student values (6, 'Jerry', 90)";
//        String sqlQuery = "update student set marks = 75 where sid = 5";
        String sqlQuery = "delete from student where sid = 6";

//        Class.forName("org.postgresql.Driver");           // 2 : Load and Register -> which is now OPTIONAL (automated in newer versions)
        Connection con = DriverManager.getConnection(url, uname, pass);   // 3 : create connection
        System.out.println("Connection Established");

        Statement st = con.createStatement();               // 4 : create statement

        boolean status = st.execute(sqlQuery);
        System.out.println(status);

//        ResultSet rs = st.executeQuery(sqlQuery);           // 5 : execute connection
//        rs.next();
//        String name = rs.getString("sname");            // 6 : process the results
//        System.out.println("Name is" + name);
//        while (rs.next()) {
//            System.out.print(rs.getInt(1) + " - ");   // FETCHING
//            System.out.print(rs.getString(2) + " - ");
//            System.out.println(rs.getInt(3));
//        }


//        *** Prepared Statements ***
//        int sid = 102;
//        String sname = "Jasmine";
//        int marks = 52;
//        String sqlQuery = "insert into student values (?,?,?)";
//
//        Connection con = DriverManager.getConnection(url, uname, pass);
//        PreparedStatement st = con.prepareStatement(sql);
//        st.setInt(1, sid);
//        st.setString(2, sname);
//        st.setInt(3, marks);
//        st.execute();

        con.close();                                        // 7 : close connection
        System.out.println("Connection Closed");
    }
}
