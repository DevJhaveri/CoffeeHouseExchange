package Analytics;
import java.util.Scanner;

public class RatingAlgorithm {
    /*
    invRatingFeedback [0]=amount$ [1]=rating





     */


    public static double entRating(double[][] invRatingFeedback, double[][] equityReturnExpected, double[] bigCompanyFeedback, double[] currentProjectInterest, double[][] debtReturnExpected) {
        // Equity side
        // Investor feedback - weighted
        double totalInvRating = 0;
        for(int i=0; i<invRatingFeedback.length; i++) {
            totalInvRating+=invRatingFeedback[i][0];
        }
        double weightedFeedback = 0;
        for(int i=0; i<invRatingFeedback.length; i++) {
            weightedFeedback+=invRatingFeedback[i][1]*(invRatingFeedback[i][0]/totalInvRating);
        }
        // Equity return rating
        double totalReturn = 0;
        double totalExpected = 0;
        for(int i=0; i<equityReturnExpected.length; i++) {
            totalReturn+=equityReturnExpected[i][0];
            totalExpected+=equityReturnExpected[i][1];
        }
        double eqReturnRating = totalReturn/totalExpected;
        // Big company feedback
        double bigCompAveFeedback = 0;
        for(int i=0; i<bigCompanyFeedback.length; i++) {
            bigCompAveFeedback+=bigCompanyFeedback[i];
        }
        bigCompAveFeedback = bigCompAveFeedback/bigCompanyFeedback.length;
        // Money raised by current projects
        double currentProjAmt = 0;
        for(int i=0; i<currentProjectInterest.length; i++) {
            currentProjAmt+=currentProjectInterest[i];
        }
        double alpha = 0.0005;
        currentProjAmt = ((1/(1+Math.pow(Math.E,-alpha*(currentProjAmt))))-0.5)*20;

        // Debt
        double debtReneg = 0;
        double debtFullyPaid = 0;
        double totalDebtIssued = 0;
        for(int i=0; i<debtReturnExpected.length; i++) {
            double tmpDebtReneg = debtReturnExpected[i][1]-debtReturnExpected[i][0];
            totalDebtIssued+=debtReturnExpected[i][1];
            if(tmpDebtReneg==0) {
                debtFullyPaid+=debtReturnExpected[i][1];
            }
            else {
                debtReneg+=tmpDebtReneg;
            }
        }
//		System.out.println(debtReneg);
        double alpha1 = 0.00036;
        double alpha2 = 1-((1/(1+Math.pow(Math.E,-alpha1*(debtFullyPaid)))));
//		System.out.println(alpha2);
        double debtRating = 10-(((1/(1+Math.pow(Math.E,-alpha2*(debtReneg)))))-0.5)*20;

//		System.out.println(weightedFeedback+" "+eqReturnRating+" "+bigCompAveFeedback+" "+currentProjAmt+" "+debtRating);

        double finalRating = 0;
        int numRatings = 0;
        if(invRatingFeedback.length!=0) {
            finalRating+=weightedFeedback;
            numRatings++;
        }
        if(equityReturnExpected.length!=0) {
            finalRating+=eqReturnRating*10;
            numRatings++;
        }
        if(bigCompanyFeedback.length!=0) {
            finalRating+=2*bigCompAveFeedback;
            numRatings+=2;
        }
        if(currentProjectInterest.length!=0) {
            finalRating+=currentProjAmt;
            numRatings++;
        }
        if(debtReturnExpected.length!=0) {
            finalRating+=2*debtRating;
            numRatings+=2;
        }

        return finalRating/numRatings;
    }

    public static double indRating(double venEq, double loan, double age) {
        // weight of age: 5, weight of venEq: 1, weight of loan: 0.7
        return venEq+0.7*loan+5*age;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // Input A through System.in and
        // Scanner
        while (true) {
            System.out.print("Please enter type of rating to calculate: ");
            String type = in.nextLine();
            if (type.equals("Entrepreneur")) {
//				System.out.print("Please enter type of rating to calculate: ");
                double[][] invRatingFeedback = new double[10][2];
                invRatingFeedback[0][0]=8000;
                invRatingFeedback[0][1]=5;
                invRatingFeedback[1][0]=10000;
                invRatingFeedback[1][1]=3;
                invRatingFeedback[2][0]=9000;
                invRatingFeedback[2][1]=3;
                invRatingFeedback[3][0]=80000;
                invRatingFeedback[3][1]=9;
                invRatingFeedback[4][0]=7070;
                invRatingFeedback[4][1]=2;
                invRatingFeedback[5][0]=15000;
                invRatingFeedback[5][1]=1;
                invRatingFeedback[6][0]=20;
                invRatingFeedback[6][1]=10;

                double[][] equityReturnExpected = new double[5][2];
                equityReturnExpected[0][0] = 3241;
                equityReturnExpected[0][1] = 3000;
                equityReturnExpected[1][0] = 2200;
                equityReturnExpected[1][1] = 30000;
                equityReturnExpected[2][0] = 5517;
                equityReturnExpected[2][1] = 6000;
                equityReturnExpected[3][0] = 886;
                equityReturnExpected[3][1] = 1200;
                equityReturnExpected[4][0] = 3033;
                equityReturnExpected[4][1] = 2700;

                double[] bigCompanyFeedback = new double[3];
                bigCompanyFeedback[0] = 6;
                bigCompanyFeedback[1] = 7;
                bigCompanyFeedback[2] = 5;

                double[] currentProjectInterest = new double[2];
                currentProjectInterest[0] = 1235;
                currentProjectInterest[1] = 612;

                double[][] debtReturnExpected = new double[10][2];
                debtReturnExpected[0][0] = 3000;
                debtReturnExpected[0][1] = 3000;
                debtReturnExpected[1][0] = 3000;
                debtReturnExpected[1][1] = 3000;
                debtReturnExpected[2][0] = 6000;
                debtReturnExpected[2][1] = 6000;
                debtReturnExpected[3][0] = 1200;
                debtReturnExpected[3][1] = 1200;
                debtReturnExpected[4][0] = 100;
                debtReturnExpected[4][1] = 100;
                debtReturnExpected[5][0] = 7000;
                debtReturnExpected[5][1] = 7000;
                debtReturnExpected[6][0] = 700;
                debtReturnExpected[6][1] = 700;
                debtReturnExpected[7][0] = 700;
                debtReturnExpected[7][1] = 700;
                debtReturnExpected[8][0] = 700;
                debtReturnExpected[8][1] = 700;
                debtReturnExpected[9][0] = 2000;
                debtReturnExpected[9][1] = 2700;


                System.out.println(entRating(invRatingFeedback, equityReturnExpected, bigCompanyFeedback, currentProjectInterest, debtReturnExpected));
            }
            else if (type.equals("Individual")) {
                System.out.print("Please enter amount of money invested in venture equity: ");
                double venEq = in.nextDouble();
                System.out.print("Please enter amount of money invested in loans: ");
                double loan = in.nextDouble();
                System.out.print("Please enter age of account (in years): ");
                double age = in.nextDouble();
                System.out.println("Individual Rating is: "+indRating(venEq, loan, age));
            }
            else {
                System.out.println("Error: Incompatable type, usage limited to \"Entrepreneur\" or \"Individual\"");
            }
        }

    }
}