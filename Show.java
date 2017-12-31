import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Show class
 * The scheduling details about a specific screening of the 
 * film in a screen
 *
 * @author Katherine Wyers
 * @version DEC-2017
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
     * Constructor for objects of class Show
     * 
     * @param id int 
     * @param date Calendar 
     * @param screen Screen 
     * @param film Film 
     * @param priceRegular float 
     * @param priceVip float 
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
     * getDateTime
     * 
     * Get a string of the datetime, in the 
     * format dd/mm/yyyy HH:mm
     * 
     * @return String
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
     * 
     * Add a leading zero to an int if its length is 1 digit
     * Numbers below zero return "00"
     * 
     * @param value int 
     * @return String
     */
    public String formatToTwoDigitString(int value)
    {
        if(value<0)
        {
            return "00";
        }
        
        if(value<10)
        {
            return "0" + value;
        }
        return Integer.toString(value);
    }
    
    /**
     * toString()
     * 
     * Get a string of the show, with 
     * film-title, screen-title and datetime. 
     * 
     * @return String 
     */
    @Override
    public String toString()
    {
        return this.film.getTitle().toUpperCase() + ", " + this.screen.getTitle() + ", Date: " + this.getDateTime();
    }

    /**
     * getId
     * 
     * Get the id of the show
     * 
     * @return int
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * getScreen
     * 
     * Get the screen
     * 
     * @return Screen 
     */
    public Screen getScreen()
    {
        return this.screen;
    }

    /**
     * getFilm 
     * 
     * get the film
     * 
     * @return Film 
     */
    public Film getFilm()
    {
        return this.film;
    }

    /**
     * getDate
     * 
     * get the Calendar date object
     * 
     * @return Calendar 
     */
    public Calendar getDate()
    {
        return this.date;
    }
    

    /**
     * get the PriceVip
     * 
     * Get the price for a VIP 
     * seat at the show
     * 
     * @return float 
     */
    public float getPriceVip()
    {
        return this.priceVip;
    }
    

    /**
     * get the priceRegular
     * 
     * Get the price for a Regular 
     * seat at the show
     * 
     * @return float 
     */
    public float getPriceRegular()
    {
        return this.priceRegular;
    }
}
