import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
     * dateTime
     * @return String date as dd/mm/yyyy HH:mm
     */
    public String getDateTime()
    {
        int dd = this.date.get(Calendar.DAY_OF_MONTH);
        int mm = this.date.get(Calendar.MONTH); 
        mm++; // add 1 because months run 0 to 11
        int yyyy = this.date.get(Calendar.YEAR);
        int hh = this.date.get(Calendar.HOUR_OF_DAY);
        int ii = this.date.get(Calendar.MINUTE);
        return this.formatToTwoDigitString(dd) + "/" + this.formatToTwoDigitString(mm) + "/" + yyyy + " " + this.formatToTwoDigitString(hh) + ":" + this.formatToTwoDigitString(ii);
    }
    
    /**
     * formatToTwoDigitString
     * Add a leading zero to an int if its length is 1 digit
     * @param int value
     * @return String result
     */
    public String formatToTwoDigitString(int value)
    {
        if(value<10)
        {
            return "0" + value;
        }
        return Integer.toString(value);
    }
    
    /**
     * toString()
     * @return String 
     */
    @Override
    public String toString()
    {
        return this.film.getTitle().toUpperCase() + ", " + this.screen.getTitle() + ", Date: " + this.getDateTime();
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
     * @return Calendar date
     */
    public Calendar getDate()
    {
        return this.date;
    }
    

    /**
     * get the PriceVip
     * @return float priceVip
     */
    public float getPriceVip()
    {
        return this.priceVip;
    }
    

    /**
     * get the priceRegular
     * @return float priceRegular
     */
    public float getPriceRegular()
    {
        return this.priceRegular;
    }
}
