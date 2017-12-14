/**
 * Simple Date Class - no error checking.
 * 
 * @author Mick Wood
 * @version Version 1.0 - 31/10/02 Amended by A.Marczyk 10/10/2003
 * 
 */
public class Date
{
    /** Fields of a Date - just the day, month and year*/
    private int day;
    private int month;
    private int year;
    
    /**
     * Parameterless constructor for objects of class Date
     * Date set to 1/1/2000 */
    public Date()
    {
        day = 1;
        month = 1;
        year = 2000;
    }
    
    /**
     * Constructor for objects of class Date
     * @param d - the day part of the date (1 - 31, depending on the month).
     * @param m - the month part of the date (1 - 12).
     * @param y - the year part of the date.*/
    public Date(int d, int m, int y)
    {
        day = d;
        month = m;
        year = y;
    }

    /**
     * method to set the Date
     * @param d - the day part of the date (1 - 31, depending on the month).
     * @param m - the month part of the date (1 - 12).
     * @param y - the year part of the date.
     * Note: No error checking in this version ! */
    public void setDate(int d, int m, int y)
    {
        day = d;
        month = m;
        year = y;
    }
    
     /**
     * method to compare two dates
     * @param d.
     * Note: No error checking in this version ! */
    public boolean isSameday (Date d)
    {
        boolean yes;
        
        if (d.day == this.day && d.month == this.month && d.year == this.year)
            yes = true;
        else 
            yes = false;
            
        return yes;
    }
    
    /**
     * @return the date as a String, format "09/11/2002" */
    public String toString ()
    {
        return as2Digits(day) + "/" + as2Digits(month) + "/" + year;
    }
    
    /** Internal method to add a leading zero if necessary. */
    private String as2Digits (int i)
    {
        if (i <10) {return "0" + i;}
        else {return "" + i;}
    }
}

