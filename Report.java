/**
 * Abstract class Report
 * The abstract parent class for all report subclasses
 *
 * @author Katherine Wyers
 * @version DEC-2017
 */
public abstract class Report
{
    protected Film film;
    protected int month;//0: Jan, 1: Feb... 11: Dec
    protected int year;
    
    /**
     * getFilm
     * 
     * Get the film that the report is for
     * 
     * @return Film film
     */
    public Film getFilm()
    {
        return this.film;
    }
    
    /**
     * getMonthAsNum
     * 
     * 0: Jan, 1: Feb ... 11: Dec
     * 
     * @return int mm
     */
    public int getMonthAsNum()
    {
        return this.month;
    }
    
    /**
     * getYear
     * 
     * Get Year as int
     * 
     * @return int year
     */
    public int getYear()
    {
        return this.year;
    }
}
