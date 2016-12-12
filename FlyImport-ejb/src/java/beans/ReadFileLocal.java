package beans;

import javax.ejb.Local;
import dtos.*;
import java.io.*;
import java.util.*;

@Local
public interface ReadFileLocal
{
    ArrayList<FI_DTO> readIn(File inFile);
}
