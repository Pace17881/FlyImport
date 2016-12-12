package dtos;

import java.util.ArrayList;


public class Report_DTO 
{
   private ArrayList<FI_DTO> errList;
   private ArrayList<FI_DTO> doppList;
   private ArrayList<FI_DTO> goodList;

    public Report_DTO(ArrayList<FI_DTO> errList, ArrayList<FI_DTO> doppList, ArrayList<FI_DTO> goodList)
    {
        this.errList = errList;
        this.doppList = doppList;
        this.goodList = goodList;
    }

    public ArrayList<FI_DTO> getErrList()
    {
        return errList;
    }

    public ArrayList<FI_DTO> getDoppList()
    {
        return doppList;
    }

    public ArrayList<FI_DTO> getGoodList()
    {
        return goodList;
    }
}
