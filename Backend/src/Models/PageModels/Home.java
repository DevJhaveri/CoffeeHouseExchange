package Models.PageModels;

import Models.Entrepreneur;

import java.sql.*;
import java.util.ArrayList;

public class Home {


        double Ent_Rating;
        double VentureRating;
        double PricePerUnit;
        int total;
        int raised;

        public double getEnt_Rating() {
            return Ent_Rating;
        }

        public void setEnt_Rating(double ent_Rating) {
            Ent_Rating = ent_Rating;
        }

        public double getVentureRating() {
            return VentureRating;
        }

        public void setVentureRating(double ventureRating) {
            VentureRating = ventureRating;
        }

        public double getPricePerUnit() {
            return PricePerUnit;
        }

        public void setPricePerUnit(double pricePerUnit) {
            PricePerUnit = pricePerUnit;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getRaised() {
            return raised;
        }

        public void setRaised(int raised) {
            this.raised = raised;
        }

        public double getExpectedReturn() {
            return ExpectedReturn;
        }

        public void setExpectedReturn(double expectedReturn) {
            ExpectedReturn = expectedReturn;
        }

        public Date getStartDate() {
            return StartDate;
        }

        public void setStartDate(Date startDate) {
            StartDate = startDate;
        }

        double ExpectedReturn;
        Date StartDate;

        public Home( ) {
        }

        public Home(double ent_Rating, double ventureRating, double pricePerUnit, int total, int raised, double expectedReturn, Date startDate) {

            Ent_Rating = ent_Rating;
            VentureRating = ventureRating;
            PricePerUnit = pricePerUnit;
            this.total = total;
            this.raised = raised;
            ExpectedReturn = expectedReturn;
            StartDate = startDate;
        }





    public static ArrayList<Home> fillBlock() {

        Statement stmt = null;
        Connection conn = null;
        ArrayList<Home> list = new ArrayList<Home>();
        try {
            String url = "jdbc:mysql://13.90.229.99/mydb?autoReconnect=true&useSSL=false";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, "willyadmin", "admin1234");
            stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT DISTINCT Ent_Rating, VentureRating, `PricePerUnit`, PricePerUnit * MaxUnits AS total, PricePerUnit * (MaxUnits - RemainUnits) AS raised, ExpectedReturn, StartDate FROM EqVenture INNER JOIN Venture INNER JOIN Entrepeneur WHERE idVenture = 1; select Ent_Rating, VentureRating, `PricePerUnit`, PricePerUnit * MaxUnits AS total, PricePerUnit * (MaxUnits - RemainUnits) AS raised, ExpectedReturn, StartDate FROM EqVenture INNER JOIN Venture INNER JOIN Entrepeneur  group by `VentureRating` order by `VentureRating` LIMIT 3;");
            while (rs.next()) {
                Home block = new Home();
                block.setEnt_Rating(rs.getDouble("Ent_Rating"));
                block.setVentureRating(rs.getDouble("VentureRating"));
                block.setPricePerUnit(rs.getDouble("PricePerUnit"));
                block.setTotal(rs.getInt("total"));
                block.setRaised(rs.getInt("raised"));
                block.setExpectedReturn(rs.getDouble("ExpectedReturn"));
                block.setStartDate(rs.getDate("StartDate"));
                list.add(block);

            }



        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return null;
    }




}
