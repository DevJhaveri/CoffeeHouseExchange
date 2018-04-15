package Models;

import java.sql.Date;

public class Equity extends Venture {
    double PricePerShareInitial;
    double ExpectedReturn;
    double PPSNow;

    public double getPricePerShareInitial() {
        return PricePerShareInitial;
    }

    public void setPricePerShareInitial(double pricePerShareInitial) {
        PricePerShareInitial = pricePerShareInitial;
    }

    public double getExpectedReturn() {
        return ExpectedReturn;
    }

    public void setExpectedReturn(double expectedReturn) {
        ExpectedReturn = expectedReturn;
    }

    public double getPPSNow() {
        return PPSNow;
    }

    public void setPPSNow(double PPSNow) {
        this.PPSNow = PPSNow;
    }

    public Equity(int Id, int ent_id, int Max_Units, double Priceperunit, int isdebt, Date startdate, Date enddate, double Expectedreturn){
        id = Id;
        Ent_id = ent_id;
        MaxUnits = Max_Units;
        PricePerUnit = Priceperunit;
        isDebt = isdebt;
        StartDate = startdate;
        EndDate = enddate;
        PricePerShareInitial = Priceperunit;
        PPSNow = Priceperunit;
        ExpectedReturn = Expectedreturn;
    }
}
