package Models;

import java.sql.*;
import java.util.ArrayList;

public class Venture {
    int id;
    int Ent_id;
    int isDebt;
    int MaxUnits;

    public Venture(int id, int ent_id, int isDebt, int maxUnits, int remainingUnits, double pricePerUnit, String ventureDesc, Date startDate, Date endDate, double ventureRating) {
        this.id = id;
        Ent_id = ent_id;
        this.isDebt = isDebt;
        MaxUnits = maxUnits;
        RemainingUnits = remainingUnits;
        PricePerUnit = pricePerUnit;
        VentureDesc = ventureDesc;
        StartDate = startDate;
        EndDate = endDate;
        VentureRating = ventureRating;
    }

    int RemainingUnits;
    double PricePerUnit;
    String VentureDesc;
    Date StartDate;
    Date EndDate;
    double VentureRating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnt_id() {
        return Ent_id;
    }

    public void setEnt_id(int ent_id) {
        Ent_id = ent_id;
    }

    public int getIsDebt() {
        return isDebt;
    }

    public void setIsDebt(int isDebt) {
        this.isDebt = isDebt;
    }

    public int getMaxUnits() {
        return MaxUnits;
    }

    public void setMaxUnits(int maxUnits) {
        MaxUnits = maxUnits;
    }

    public int getRemainingUnits() {
        return RemainingUnits;
    }

    public void setRemainingUnits(int remainingUnits) {
        RemainingUnits = remainingUnits;
    }

    public double getPricePerUnit() {
        return PricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        PricePerUnit = pricePerUnit;
    }

    public String getVentureDesc() {
        return VentureDesc;
    }

    public void setVentureDesc(String ventureDesc) {
        VentureDesc = ventureDesc;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public double getVentureRating() {
        return VentureRating;
    }

    public void setVentureRating(double ventureRating) {
        VentureRating = ventureRating;
    }

    public Venture(int Id, int ent_id, int Max_Units, double Priceperunit, int isdebt, Date startdate, Date enddate) {
        id = Id;
        Ent_id = ent_id;
        MaxUnits = Max_Units;
        PricePerUnit = Priceperunit;
        isDebt = isdebt;
        StartDate = startdate;
        EndDate = enddate;
    }

    public Venture() {

    }

    public static ArrayList<Venture> SelectFromEnt(int entid) {
        Statement stmt = null;
        Connection conn = null;
        ArrayList<Venture> list = new ArrayList<Venture>();
        try {
            String url = "jdbc:mysql://13.90.229.99/mydb?autoReconnect=true&useSSL=false";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, "willyadmin", "admin1234");
            stmt = conn.createStatement();
            ResultSet rs;

            ArrayList<Venture> temp = new ArrayList<Venture>();
            rs = stmt.executeQuery("SELECT  * FROM mydb.Venture where Entrepeneur_idEntrepeneur=" + entid + ";");
            while (rs.next()) {
                int id = rs.getInt("idVenture");
                int Ent_id = rs.getInt("Entrepeneur_idEntrepeneur");
                int isDebt = rs.getInt("isDebt");
                int MaxUnits = rs.getInt("MaxUnits");
                int RemainUnits = rs.getInt("RemainUnits");
                String Description = rs.getString("VentureDesc");
                double PricePerUnit = rs.getDouble("PricePerUnit");
                Date StartDate = rs.getDate("StartDate");
                Date EndDate = rs.getDate("EndDate");
                double Rating = rs.getDouble("VentureRating");
                temp.add(new Venture( id,  Ent_id,  isDebt,  MaxUnits,  RemainUnits,  PricePerUnit,Description,  StartDate,  EndDate,  Rating));

            }
            for (Venture t:temp
                 ) {




                int id = t.id;
                int Ent_id = t.Ent_id;
                int isDebt = t.isDebt;
                int MaxUnits = t.MaxUnits;
                int RemainUnits = t.RemainingUnits;
                String Description = t.VentureDesc;
                double PricePerUnit = t.PricePerUnit;
                Date StartDate = t.StartDate;
                Date EndDate = t.EndDate;
                double Rating = t.VentureRating;


                ResultSet rs2;
                if (isDebt == 1) {
                    rs2 = stmt.executeQuery("SELECT * FROM mydb.DebtVenture where Venture_idVenture=" + id + ";");
                    while (rs2.next()) {
                        double interest = rs2.getDouble("InterestRate");
                        double Guarantee = rs2.getDouble("Guarantee");
                       Date Expiry = rs2.getDate("ExpiryDate");
                        Debt item = new Debt(id, Ent_id, MaxUnits, RemainUnits, PricePerUnit, 1, StartDate, EndDate, interest, Expiry, Guarantee);
                        item.setVentureDesc(Description);
                        item.setVentureRating(Rating);
                        list.add(item);
                    }
                } else {
                    rs2 = stmt.executeQuery("SELECT * FROM mydb.EqVenture where Venture_idVenture=" + id + ";");
                    while (rs2.next()) {
                        double ExpectedRet = rs2.getDouble("ExpectedReturn");
                        Equity item = new Equity(id, Ent_id, MaxUnits, PricePerUnit, 0, StartDate, EndDate, ExpectedRet);
                        item.setVentureDesc(Description);
                        item.setVentureRating(Rating);
                        list.add(item);
                    }
                }
            }
        conn.close();
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                // log this error
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                // log this error
            }
        }

        return null;

    }




/*


    public static void Insert(Venture ven) {


        //String url = "jdbc:mysql://13.90.229.99/mydb?autoReconnect=true&useSSL=false";
// public Entrepreneur(int id, double rating, String description, String firstName, String lastname, int SSN, char sex, Date DOB, Date join, String password,int IsLoggedIn)

        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String url = "jdbc:mysql://13.90.229.99/mydb?autoReconnect=true&useSSL=false";
            Class.forName("com.mysql.jdbc.Driver");


            try {
                conn = DriverManager.getConnection(url, "willyadmin", "admin1234");
                stmt = conn.prepareStatement("INSERT INTO mydb.Entrepeneur (EntRating, EntDescript,Ent_First_Name,Ent_Last_Name,EntSSN) values (?, ?)");
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (Exception e) {
                    // log this error
                }
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    // log this error
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/


}
