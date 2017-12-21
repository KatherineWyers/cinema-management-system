
/**
 * Write a description of class Ticket here.
 *
 * @author Katherine Wyers
 * @version 1.0
 */
public class Ticket extends SeatAllocation
{
    private Booking booking;
    private Review review;
    
    /**
     * Constructor for objects of class Ticket
     */
    public Ticket(int id, Reservation reservation, Booking booking)
    {
        this.id = id;
        this.show = reservation.getShow();
        this.booking = booking;
        this.row = reservation.getRow();
        this.num = reservation.getNum();
        this.price = reservation.getPrice();
        reservation.setUsed();
    }
    
    /**
     * set the show
     * @param Show show
     * @return void
     */
    public void setDetails(Show show, int row, int num)
    {
        this.show = show;
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
    
    /**
     * toString
     * @return String
     * 
     */
    public String toString()
    {
        return  "Seat: " + this.convertToRowLetter(this.row) + this.num + ", DateTime: " + this.show.getDateTime() + ", Price: $" + this.price + ", Film: " + this.show.getFilm().getTitle() + ", Screen: " + this.show.getScreen().getTitle();
    }
    
}
