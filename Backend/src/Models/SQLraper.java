package Models;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class SQLraper {
/*
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://13.90.229.99/mydb?autoReconnect=true&useSSL=false";

    //  Database credentials
    static final String USER = "willyadmin";
    static final String PASS = "admin1234";


    public static String showAll() {

        Connection conn = null;
        //Statement stmt = null;
        //PreparedStatement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);


                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM books");
                ResultSetMetaData metadata = rs.getMetaData();
                int cols = metadata.getColumnCount();
                System.out.println("\n-----------------------------"
                        + "--------------------------------");
                String toreturn;
                for (int i = 0; i < cols; i++) {
                    System.out.printf("%-20s\t",
                            metadata.getColumnName(i + 1).toUpperCase());
                }
                System.out.println("\n-----------------------------"
                        + "--------------------------------");
                while (rs.next()) {
                    for (int i = 0; i < cols; i++)
                        System.out.printf("%-20s\t", rs.getObject(i + 1));
                    System.out.println();
                }
                System.out.println("-------------------------------"
                        + "--------------------------------");





        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
