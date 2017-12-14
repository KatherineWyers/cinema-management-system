import java.util.List;
import java.util.ArrayList;
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
    private List<Ticket> tickets = new ArrayList<Ticket>();
    
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
    

    /**
     * add the new tickets
     * @param List<Ticket> newTickets
     * @param void
     */
    public void addTickets(List<Ticket> newTickets)
    {
        //Iterator
    }
    

    /**
     * getSeatingGrid
     * @return boolean[][] seatingGrid
     */
    public boolean[][] getSeatingGrid()
    {
        boolean seatingGrid[][] = new boolean[5][10];
        
        //Set whole grid to empty
        for(int i=0;i<5;i++)
        {
            for(int j=0;j<10;j++)
            {
                seatingGrid[i][j] = false;
            }
        }
        
        // Update grid with booked tickets
        for(Ticket ticket : this.tickets)
        {
            seatingGrid[ticket.getRow()-1][ticket.getNum()-1] = true;
        }
        return seatingGrid;
    }
}
