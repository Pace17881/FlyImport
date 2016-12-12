package dtos;

import java.io.Serializable;

public class FI_Fluggesellschaft implements Serializable
{
    private String gesellschaftID, gesellschaftName;

    public FI_Fluggesellschaft(String gesellschaftID, String gesellschaftName)
    {
        this.gesellschaftID = gesellschaftID;
        this.gesellschaftName = gesellschaftName;
    }
    
    public String getGesellschaftID()
    {
        return gesellschaftID;
    }

    public String getGesellschaftName()
    {
        return gesellschaftName;
    }

}
