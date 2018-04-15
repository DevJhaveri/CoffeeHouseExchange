package Models;


import java.sql.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;


public class Entrepreneur {


    int id;
    double rating;
    String Description;
    String FirstName;
    String Lastname;

    int SSN;
    char sex;
    Date DOB;
    Date Join;
    String password;
    int IsLoggedIn;

    public int getIsLoggedIn() {
        return IsLoggedIn;
    }

    public void setIsLoggedIn(int isLoggedIn) {
        IsLoggedIn = isLoggedIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Entrepreneur(){

    }

    public Entrepreneur(int id, double rating, String description, String firstName, String lastname, int SSN, char sex, Date DOB, Date join, String password,int IsLoggedIn) {
        this.id = id;
        this.rating = rating;
        Description = description;
        FirstName = firstName;
        Lastname = lastname;
        this.SSN = SSN;
        this.sex = sex;
        this.DOB = DOB;
        Join = join;
        this.password = password;
        this.IsLoggedIn = IsLoggedIn;
    }

    public Entrepreneur(int id, double rating, String description, String firstName, String lastname, int SSN, char sex, Date DOB, Date join) {
        this.id = id;
        this.rating = rating;
        Description = description;
        FirstName = firstName;
        Lastname = lastname;
        this.SSN = SSN;
        this.sex = sex;
        this.DOB = DOB;
        Join = join;
    }


    public static ArrayList<Entrepreneur> Select(int idin){


        //String url = "jdbc:mysql://13.90.229.99/mydb?autoReconnect=true&useSSL=false";


        Statement stmt = null;
        Connection conn = null;
        ArrayList<Entrepreneur> list = new ArrayList<Entrepreneur>();
        try {
            String url = "jdbc:mysql://13.90.229.99/mydb?autoReconnect=true&useSSL=false";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,"willyadmin","admin1234");
            stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT  * FROM mydb.Entrepeneur where idEntrepeneur="+idin+";");
            while ( rs.next() ) {
                int idi = rs.getInt("idEntrepeneur");
                double rating =rs.getDouble("EntRating");
                String Description = rs.getString("EntDescrip");
                String FirstName = rs.getString("Ent_First_Name");
                String Lastname = rs.getString("Ent_Last_Name");
                int SSN = rs.getInt("Ent_SSN");
                String password = rs.getString("Ent_Password");
                int isLoggedIn = rs.getInt("IsLoggedIn");
                list.add(new Entrepreneur(idi,rating,Description,FirstName,Lastname,SSN,'M',null,null,password,isLoggedIn));
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

    public static ArrayList<Entrepreneur> Select(){


        //String url = "jdbc:mysql://13.90.229.99/mydb?autoReconnect=true&useSSL=false";


        Statement stmt = null;
        Connection conn = null;
        ArrayList<Entrepreneur> list = new ArrayList<Entrepreneur>();
        try {
            String url = "jdbc:mysql://13.90.229.99/mydb?autoReconnect=true&useSSL=false";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,"willyadmin","admin1234");
            stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM mydb.Entrepeneur;");
            while ( rs.next() ) {
                int id = rs.getInt("idEntrepeneur");
                double rating =rs.getDouble("EntRating");
                String Description = rs.getString("EntDescrip");
                String FirstName = rs.getString("Ent_First_Name");
                String Lastname = rs.getString("Ent_Last_Name");
                int SSN = rs.getInt("Ent_SSN");
                list.add(new Entrepreneur(id,rating,Description,FirstName,Lastname,SSN,'M',null,null));
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


    public static Boolean isLoggedin(int idin){


        //String url = "jdbc:mysql://13.90.229.99/mydb?autoReconnect=true&useSSL=false";


        Statement stmt = null;
        Connection conn = null;
        ArrayList<Entrepreneur> list = new ArrayList<Entrepreneur>();
        try {
            String url = "jdbc:mysql://13.90.229.99/mydb?autoReconnect=true&useSSL=false";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,"willyadmin","admin1234");
            stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT  * FROM mydb.Entrepeneur where idEntrepeneur="+idin+";");
            while ( rs.next() ) {
                int idi = rs.getInt("idEntrepeneur");
                double rating =rs.getDouble("EntRating");
                String Description = rs.getString("EntDescrip");
                String firstName = rs.getString("Ent_First_Name");
                String lastname = rs.getString("Ent_Last_Name");
                String Password = rs.getString("Ent_Password");
                int sSN = rs.getInt("Ent_SSN");

                int isLoggedIn = rs.getInt("IsLoggedIn");
                if(isLoggedIn==1)list.add(new Entrepreneur(idi,rating,Description,firstName,lastname,sSN,'M',null,null,Password,isLoggedIn));
            }

            conn.close();

            if(list.get(0)==null)return false;
            return true;


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public Date getJoin() {
        return Join;
    }

    public void setJoin(Date join) {
        Join = join;
    }


    /*
    public boolean Insert(ArrayList<Entrepreneur> list){


        String url = "jdbc:mysql://13.90.229.99/mydb?autoReconnect=true&useSSL=false";






        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(Const.DB_URL,Const.USER,Const.USER);

            stmt = conn.prepareStatement("INSERT INTO person (name, email) values (?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();
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




    }*/



}
