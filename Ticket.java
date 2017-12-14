
/**
 * Write a description of class Ticket here.
 *
 * @author Katherine Wyers
 * @version 1.0
 */
public class Ticket
{
    private int id;
    private Projection projection;
    private Booking booking;
    private int row;
    private int num;
    
    /**
     * Constructor for objects of class SeatAssignment
     */
    public Ticket(int id, Projection projection, Booking booking, int row, int num)
    {
        this.id = id;
        this.projection = projection;
        this.booking = booking;
        this.row = row;
        this.num = num;
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
     * get the projection
     * @return Projection projection
     */
    public Projection getProjection()
    {
        return this.projection;
    }
    /**
     * get the booking
     * @return Booking booking
     */
    public Booking getBooking()
    {
        return this.booking;
    }
    
    /**
     * get the row
     * @return String row
     */
    public int getRow()
    {
        return this.row;
    }
    

    /**
     * get the number
     * @return int number
     */
    public int getNum()
    {
        return this.num;
    }
    

    /**
     * get the type
     * @return String type
     */
    public String getType()
    {
        // All seats in row E are Vip seats. All others are regular
        if(this.row == 5)
        {
            return "Vip";
        }
        return "Regular";
    }
}
