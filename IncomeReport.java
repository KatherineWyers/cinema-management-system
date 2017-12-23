
/**
 * Write a description of class IncomeReport here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class IncomeReport extends Report
{
    /**
     * Constructor for objects of class IncomeReport
     */
    public IncomeReport(Film film, int month, int year)
    {
        this.film = film;
        this.month = month;
        this.year = year;
    }
    
    /**
     * getIncome
     * @return float income
     */
    public float getIncome()
    {
        return (float)1.0;
    }
}
