
/**
 * Write a description of class Projection here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Projection
{
    private int id;
    private Screen screen;
    private Film film;
    private String date;
    private String slot;
    private float priceRegular;
    private float priceVip;
    
    /**
     * Constructor for objects of class Projection
     */
    public Projection(int id, String date, String slot, Screen screen, Film film, float priceRegular, float priceVip)
    {
        this.id = id;
        this.date = date;
        this.slot = slot;
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
     * @return String date
     */
    public String getDate()
    {
        return this.date;
    }
    

    /**
     * get the slot
     * @return String slot
     */
    public String getSlot()
    {
        return this.slot;
    }
}
