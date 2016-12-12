package dtos;

import java.io.Serializable;

public class FI_Flugzeug implements Serializable
{
    private String typ, hersteller;
    private short anzahlSitze;

    public FI_Flugzeug(String typ, String hersteller, short anzahlSitze)
    {
        this.typ = typ;
        this.hersteller = hersteller;
        this.anzahlSitze = anzahlSitze;
    }

    public String getTyp()
    {
        return typ;
    }

    public String getHersteller()
    {
        return hersteller;
    }

    public short getAnzahlSitze()
    {
        return anzahlSitze;
    }
}
