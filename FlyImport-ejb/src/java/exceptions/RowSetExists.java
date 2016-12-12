package exceptions;

public class RowSetExists extends Exception
{

    public RowSetExists()
    {
    }

    public RowSetExists(String message)
    {
        super(message);
    }
    
}
