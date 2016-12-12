package dtos;

import java.io.Serializable;
import java.util.Date;

public class FI_Fluglinie implements Serializable
{
    private short linie, dauer, sitzeBelegt;
    private float preis;
    private Date abflugDatum;
    private String startFlughafenID, startStadt, startLand;
    private String zielFlughafenID, zielStadt, zielLand;

    public FI_Fluglinie(
            short linie, short dauer, short sitzeBelegt,
            float preis, Date abflugDatum, 
            String startFlughafenID, String startStadt, String startLand, 
            String zielFlughafenID, String zielStadt, String zielLand)
    {
        this.linie = linie;
        this.dauer = dauer;
        this.sitzeBelegt = sitzeBelegt;
        this.preis = preis;
        this.abflugDatum = abflugDatum;
        this.startFlughafenID = startFlughafenID;
        this.startStadt = startStadt;
        this.startLand = startLand;
        this.zielFlughafenID = zielFlughafenID;
        this.zielStadt = zielStadt;
        this.zielLand = zielLand;
    }

    public short getLinie()
    {
        return linie;
    }

    public short getDauer()
    {
        return dauer;
    }

    public short getSitzeBelegt()
    {
        return sitzeBelegt;
    }

    public float getPreis()
    {
        return preis;
    }

    public Date getAbflugDatum()
    {
        return abflugDatum;
    }

    public String getStartFlughafenID()
    {
        return startFlughafenID;
    }

    public String getStartStadt()
    {
        return startStadt;
    }

    public String getStartLand()
    {
        return startLand;
    }

    public String getZielFlughafenID()
    {
        return zielFlughafenID;
    }

    public String getZielStadt()
    {
        return zielStadt;
    }

    public String getZielLand()
    {
        return zielLand;
    }
}
