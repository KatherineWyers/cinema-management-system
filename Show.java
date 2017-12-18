import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Write a description of class Projection here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Show
{
    private int id;
    private Screen screen;
    private Film film;
    private Calendar date;
    private float priceRegular;
    private float priceVip;
    
    /**
     * Constructor for objects of class Projection
     */
    public Show(int id, Calendar date, Screen screen, Film film, float priceRegular, float priceVip)
    {
        this.id = id;
        this.date = date;
        this.screen = screen;
        this.film = film;
        this.priceRegular = priceRegular;
        this.priceVip = priceVip;
    }

    /**
     * get the id
     * @return int id
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * get the screen
     * @return Screen screen
     */
    public Screen getScreen()
    {
        return this.screen;
    }

    /**
     * get the film
     * @return Film film
     */
    public Film getFilm()
    {
        return this.film;
    }

    /**
     * get the date
     * @return Date date
     */
    public Calendar getDate()
    {
        return this.date;
    }
    

    /**
     * get the PriceVip
     * @return double priceVip
     */
    public double getPriceVip()
    {
        return this.priceVip;
    }
    

    /**
     * get the priceRegular
     * @return double priceRegular
     */
    public double getPriceRegular()
    {
        return this.priceRegular;
    }
}
