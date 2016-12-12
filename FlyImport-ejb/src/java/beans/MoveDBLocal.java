package beans;

import dtos.*;
import java.util.ArrayList;
import javax.ejb.Local;

@Local
public interface MoveDBLocal
{

    Report_DTO persistData(ArrayList<FI_DTO> list);
    
}
