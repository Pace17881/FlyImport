package dtos;

import java.io.Serializable;

public class FI_DTO implements Serializable
{
    private FI_Buchung FI_B;
    private FI_Fluggesellschaft FI_FG;
    private FI_Fluglinie FI_FL;
    private FI_Flugzeug FI_FZ;
    private FI_Passagier FI_P;

    public FI_DTO(FI_Buchung FI_B, FI_Fluggesellschaft FI_FG, FI_Fluglinie FI_FL, FI_Flugzeug FI_FZ, FI_Passagier FI_P)
    {
        this.FI_B = FI_B;
        this.FI_FG = FI_FG;
        this.FI_FL = FI_FL;
        this.FI_FZ = FI_FZ;
        this.FI_P = FI_P;
    }

    public FI_Buchung getFI_B()
    {
        return FI_B;
    }

    public FI_Fluggesellschaft getFI_FG()
    {
        return FI_FG;
    }

    public FI_Fluglinie getFI_FL()
    {
        return FI_FL;
    }

    public FI_Flugzeug getFI_FZ()
    {
        return FI_FZ;
    }

    public FI_Passagier getFI_P()
    {
        return FI_P;
    }
}
