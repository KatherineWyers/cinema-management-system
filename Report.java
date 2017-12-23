
/**
 * Abstract class Report - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Report
{
    protected Film film;
    protected int month;//0: Jan, 1: Feb... 11: Dec
    protected int year;
    
    /**
     * getFilm
     * @return Film film
     */
    public Film getFilm()
    {
        return this.film;
    }
    
    /**
     * getMonthAsNum
     * 0: Jan, 1: Feb ... 11: Dec
     * @return int mm
     */
    public int getMonthAsNum()
    {
        return this.month;
    }
    
    /**
     * getYear
     * @return int year
     */
    public int getYear()
    {
        return this.year;
    }
}
