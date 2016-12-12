package dtos;

import java.util.Date;

public class FI_Buchung
{
    private Date buchungDatum;
    private int buchungNR;

    public FI_Buchung(Date buchungDatum, int buchungNR)
    {
        this.buchungDatum = buchungDatum;
        this.buchungNR = buchungNR;
    }

    public Date getBuchungDatum()
    {
        return buchungDatum;
    }

    public int getBuchungNR()
    {
        return buchungNR;
    }
}
