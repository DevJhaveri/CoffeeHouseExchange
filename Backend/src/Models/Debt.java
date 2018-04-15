package Models;

import java.sql.Date;

public class Debt extends Venture{
    double InterestRate;
    Date ExpiryDate;
    double Guarantee;

    public double getInterestRate() {
        return InterestRate;
    }

    public void setInterestRate(double interestRate) {
        InterestRate = interestRate;
    }

    public Date getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        ExpiryDate = expiryDate;
    }

    public double getGuarantee() {
        return Guarantee;
    }

    public void setGuarantee(double guarantee) {
        Guarantee = guarantee;
    }

    public Debt(int Id, int ent_id, int Max_Units, int Remain_Units, double Priceperunit, int isdebt, Date startdate, Date enddate, double interest, Date expiry, double guarantee){
        id = Id;
        Ent_id = ent_id;
        MaxUnits = Max_Units;
        RemainingUnits = Remain_Units;
        PricePerUnit = Priceperunit;
        isDebt = isdebt;
        StartDate = startdate;
        EndDate = enddate;
        InterestRate = interest;
        ExpiryDate =expiry;
        Guarantee = guarantee;
    }
}
