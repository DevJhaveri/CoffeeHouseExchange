package Models;

import java.sql.*;
import java.util.ArrayList;

public class Individual {

         int id;
        String Firstname;
        String Lastnamel;
        String Email;
    String SSN;
         double rating;
    double Balance;

    public  int getId() {
        return id;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastnamel() {
        return Lastnamel;
    }

    public void setLastnamel(String lastnamel) {
        Lastnamel = lastnamel;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public  String getSSN() {
        return SSN;
    }

    public  void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    //static date DOB;
        //static date Join;


    public Individual(int id,String firstname, String lastnamel, String email,String SSN, double rating, double balance) {
        Firstname = firstname;
        Lastnamel = lastnamel;
        Email = email;
        this.rating = rating;
        Balance = balance;
        this.id = id;
        this.SSN = SSN;
    }

    public Individual() {
    }


    public static ArrayList<Individual> Select(int idin){


        //String url = "jdbc:mysql://13.90.229.99/mydb?autoReconnect=true&useSSL=false";


        Statement stmt = null;
        Connection conn = null;
        ArrayList<Individual> list = new ArrayList<Individual>();
        try {
            String url = "jdbc:mysql://13.90.229.99/mydb?autoReconnect=true&useSSL=false";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,"willyadmin","admin1234");
            stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT  * FROM mydb.Individual where idIndividual="+idin+";");
            while ( rs.next() ) {
                 int id=rs.getInt("idIndividual");
                String Firstname = rs.getString("Ind_First_Name");
                String Lastnamel = rs.getString("Ind_Last_Name");
                String Email = rs.getString("Ind_Email");
                 String SSN = rs.getString("Idn_SSN");
                double rating = rs.getDouble("Ind_Rating");
               double Balance = rs.getDouble("Ind_Balance");

                list.add(new Individual(id,Firstname,Lastnamel,Email,SSN,rating,Balance));
            }
            conn.close();
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) { stmt.close(); }
            }
            catch (Exception e) {
                // log this error
            }
            try {
                if (conn != null) { conn.close(); }
            }
            catch (Exception e) {
                // log this error
            }
        }

        return null;

    }

    public static ArrayList<Individual> Select(){


        //String url = "jdbc:mysql://13.90.229.99/mydb?autoReconnect=true&useSSL=false";


        Statement stmt = null;
        Connection conn = null;
        ArrayList<Individual> list = new ArrayList<Individual>();
        try {
            String url = "jdbc:mysql://13.90.229.99/mydb?autoReconnect=true&useSSL=false";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,"willyadmin","admin1234");
            stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM mydb.Individual;");
            while ( rs.next() ) {
                int id=rs.getInt("idIndividual");
                String Firstname = rs.getString("Ind_First_Name");
                String Lastnamel = rs.getString("Ind_Last_Name");
                String Email = rs.getString("Ind_Email");
                String SSN = rs.getString("Ind_SSN");
                double rating = rs.getDouble("Ind_Rating");
                double Balance = rs.getDouble("Ind_Balance");

                list.add(new Individual(id,Firstname,Lastnamel,Email,SSN,rating,Balance));
            }
            conn.close();
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) { stmt.close(); }
            }
            catch (Exception e) {
                // log this error
            }
            try {
                if (conn != null) { conn.close(); }
            }
            catch (Exception e) {
                // log this error
            }
        }

        return null;

    }

}