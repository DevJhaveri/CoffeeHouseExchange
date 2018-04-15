import Analytics.RatingAlgorithm;
import Models.Entrepreneur;
import Models.Individual;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Builder {

    private static class Block{
        public  double[][] invRatingFeedback;
        public  double[][]  equityReturnExpected;
        public double[] bigCompanyFeedback;

        public double[] currentProjectInterest;

        public double[][] getInvRatingFeedback() {
            return invRatingFeedback;
        }

        public void setInvRatingFeedback(double[][] invRatingFeedback) {
            this.invRatingFeedback = invRatingFeedback;
        }

        public double[][] getEquityReturnExpected() {
            return equityReturnExpected;
        }

        public void setEquityReturnExpected(double[][] equityReturnExpected) {
            this.equityReturnExpected = equityReturnExpected;
        }

        public double[] getBigCompanyFeedback() {
            return bigCompanyFeedback;
        }

        public void setBigCompanyFeedback(double[] bigCompanyFeedback) {
            this.bigCompanyFeedback = bigCompanyFeedback;
        }

        public double[] getCurrentProjectInterest() {
            return currentProjectInterest;
        }

        public void setCurrentProjectInterest(double[] currentProjectInterest) {
            this.currentProjectInterest = currentProjectInterest;
        }

        public double[][] getDebtReturnExpected() {
            return debtReturnExpected;
        }

        public void setDebtReturnExpected(double[][] debtReturnExpected) {
            this.debtReturnExpected = debtReturnExpected;
        }

        public Block() {

        }

        public double[][] debtReturnExpected;

        public Block(double[][] invRatingFeedback, double[][] equityReturnExpected, double[] bigCompanyFeedback, double[] currentProjectInterest, double[][] debtReturnExpected) {
            this.invRatingFeedback = invRatingFeedback;
            this.equityReturnExpected = equityReturnExpected;
            this.bigCompanyFeedback = bigCompanyFeedback;
            this.currentProjectInterest = currentProjectInterest;
            this.debtReturnExpected = debtReturnExpected;
        }




    }





    public static void fillBlocks(ArrayList<Entrepreneur> entList){



        Block b = null;
        Statement stmt = null;
        Connection conn = null;
        ArrayList<Individual> list = new ArrayList<Individual>();
        try {



            //fill invratingfeedback
            b = new Block();
            //b.setInvRatingFeedback(new double[entList.size()][2]);


            for (Entrepreneur e : entList
                 ) {

                String url = "jdbc:mysql://13.90.229.99/mydb?autoReconnect=true&useSSL=false";
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(url,"willyadmin","admin1234");
                stmt = conn.createStatement();
                ResultSet rs;

                ArrayList<Double> invrating = new ArrayList<Double>();
                ArrayList<Double> invgiverating = new ArrayList<Double>();


                rs = stmt.executeQuery("SELECT Ind_Rating, IndReview FROM mydb.Venture INNER JOIN mydb.IndRelationship INNER JOIN Individual WHERE Entrepeneur_idEntrepeneur = "+e.getId()+";");
                while ( rs.next() ) {
                    invrating.add(rs.getDouble("Ind_Rating"));
                    invgiverating.add(rs.getDouble("IndReview"));

                }

                b.setInvRatingFeedback(new double[invrating.size()][2]);
                for(int i=0 ; i<invrating.size();i++){
                    b.getInvRatingFeedback()[i][0]=invrating.get(i);
                    b.getInvRatingFeedback()[i][1]=invgiverating.get(i);
                }


                ArrayList<Double> actualretequ = new ArrayList<Double>();
                ArrayList<Double> expectedretequ = new ArrayList<Double>();

                rs = stmt.executeQuery("SELECT distinct POW((`PPS Now`/`PPS Initial`), (1/(TIMESTAMPDIFF(DAY, now(), EndDate)/-365))) - 1 AS AvgReturn, ExpectedReturn FROM mydb.Venture INNER JOIN EqVenture WHERE Entrepeneur_idEntrepeneur ="+e.getId()+";");
                while ( rs.next() ) {
                    actualretequ.add(rs.getDouble("AvgReturn"));
                    expectedretequ.add(rs.getDouble("ExpectedReturn"));

                }

                b.setEquityReturnExpected(new double[actualretequ.size()][2]);
                for(int i=0 ; i<actualretequ.size();i++){
                    b.getEquityReturnExpected()[i][0]=actualretequ.get(i);
                    b.getEquityReturnExpected()[i][1]=expectedretequ.get(i);
                }









                ArrayList<Double> comprev = new ArrayList<Double>();

                rs = stmt.executeQuery("SELECT CorpReview FROM CorpRelationship INNER JOIN Venture WHERE Entrepeneur_idEntrepeneur = "+e.getId()+";");
                while ( rs.next() ) {
                    comprev.add(rs.getDouble("CorpReview"));

                }

                b.setBigCompanyFeedback(new double[comprev.size()]);
                for(int i=0 ; i<comprev.size();i++){
                    b.getBigCompanyFeedback()[i]=comprev.get(i);
                }




                ArrayList<Double> projint = new ArrayList<Double>();

                rs = stmt.executeQuery("SELECT sum(MonetaryValue) AS Interest FROM Venture INNER JOIN IndRelationship WHERE Entrepeneur_idEntrepeneur =" +e.getId()+";");
                while ( rs.next() ) {
                    projint.add(rs.getDouble("Interest"));

                }

                b.setCurrentProjectInterest(new double[projint.size()]);
                for(int i=0 ; i<projint.size();i++){
                    b.getCurrentProjectInterest()[i]=projint.get(i);
                }



                ArrayList<Double> debtreturnact = new ArrayList<Double>();
                ArrayList<Double> debtreturnexpec = new ArrayList<Double>();

                rs = stmt.executeQuery("SELECT DISTINCT PercentReturned, ((MaxUnits - RemainUnits) * PricePerUnit) * (1 + InterestRate) AS V1 FROM DebtVenture INNER JOIN Venture WHERE Entrepeneur_idEntrepeneur ="+e.getId()+";");
                while ( rs.next() ) {
                    debtreturnact.add(rs.getDouble("PercentReturned"));
                    debtreturnexpec.add(rs.getDouble("V1"));

                }

                b.setDebtReturnExpected(new double[debtreturnact.size()][2]);
                for(int i=0 ; i<debtreturnact.size();i++){
                    b.getDebtReturnExpected()[i][0]=debtreturnact.get(i);
                    b.getDebtReturnExpected()[i][1]=debtreturnexpec.get(i);
                }
                //conn.close();


                double entRating = RatingAlgorithm.entRating(b.getInvRatingFeedback(),b.getEquityReturnExpected(),b.getBigCompanyFeedback(),b.getCurrentProjectInterest(),b.getDebtReturnExpected());


                //PreparedStatement stmtp = null;
                stmt = conn.createStatement();
                stmt.executeUpdate("UPDATE Entrepeneur SET EntRating = "+entRating+" WHERE idEntrepeneur = "+e.getId()+";");
                conn.close();


                        //conn = DriverManager.getConnection(url, "willyadmin", "admin1234");
                //stmtp = conn.prepareStatement("UPDATE Entrepeneur SET Ent_Rating = "+e.getId()+" WHERE idEntrepeneur = ?;");
               // stmtp.setDouble(1, entRating);

               // stmtp.executeUpdate();









            }




            conn.close();

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
    }


    private static Logger log = Logger.getLogger(Builder.class.getName());

    public static class Keeper extends Thread {
        public void start() {


            ArrayList<Entrepreneur> eList= new ArrayList<Entrepreneur>();
            eList = Entrepreneur.Select();

            fillBlocks(eList);




            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
