
/**
 * Write a description of class Ticket here.
 *
 * @author Katherine Wyers
 * @version 1.0
 */
public class Ticket extends SeatAllocation
{
    private Booking booking;
    
    /**
     * Constructor for objects of class Ticket
     */
    public Ticket(int id, Reservation reservation, Booking booking)
    {
        this.id = id;
        this.projection = reservation.getProjection();
        this.booking = booking;
        this.row = reservation.getRow();
        this.num = reservation.getNum();
        this.price = reservation.getPrice();
        reservation.setUsed();
    }
    
    /**
     * set the projection
     * @param Projection projection
     * @return void
     */
    public void setDetails(Projection projection, int row, int num)
    {
        this.projection = projection;
        this.row = row;
        this.num = num;
    }
    
    /**
     * get the booking
     * @return Booking booking
     */
    public Booking getBooking()
    {
        return this.booking;
    }
}
