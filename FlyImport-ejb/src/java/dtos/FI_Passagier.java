package dtos;

import java.io.Serializable;

public class FI_Passagier implements Serializable
{
    private int passagierNR;
    private String anrede, name, plz, ort, strasse, land;

    public FI_Passagier(int passagierNR, String anrede, String name, String plz, String ort, String strasse, String land)
    {
        this.passagierNR = passagierNR;
        this.anrede = anrede;
        this.name = name;
        this.plz = plz;
        this.ort = ort;
        this.strasse = strasse;
        this.land = land;
    }

    public int getPassagierNR()
    {
        return passagierNR;
    }

    public String getAnrede()
    {
        return anrede;
    }

    public String getName()
    {
        return name;
    }

    public String getPlz()
    {
        return plz;
    }

    public String getOrt()
    {
        return ort;
    }

    public String getStrasse()
    {
        return strasse;
    }

    public String getLand()
    {
        return land;
    }
}
