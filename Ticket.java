
/**
 * Ticket Class
 * The ticket, once the seat allocation has been paid 
 * for
 *
 * @author Katherine Wyers
 * @version 1.0
 */
public class Ticket extends SeatAllocation
{
    private Booking booking;
    
    /**
     * Constructor for objects of class Ticket
     * 
     * @param int id
     * @param Reservation reservation
     * @param Booking booking
     */
    public Ticket(int id, Reservation reservation, Booking booking)
    {
        this.id = id;
        this.show = reservation.getShow();
        this.booking = booking;
        this.row = reservation.getRow();
        this.num = reservation.getNum();
        this.price = reservation.getPrice();
    }
    
    /**
     * setDetails
     * 
     * set the show
     * 
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
     * setPrice
     * 
     * set the price
     * 
     * @param float price
     * @return void
     */
    public void setPrice(float price)
    {
        this.price = price;
    }
    
    
    /**
     * getBooking
     * 
     * get the booking
     * 
     * @return Booking booking
     */
    public Booking getBooking()
    {
        return this.booking;
    }
   
    
    /**
     * toString
     * 
     * Get a string with seat name, ticketID
     * datetime, price, film title and screen title
     * 
     * @return String
     */
    public String toString()
    {
        return  "Seat: " + this.convertToRowLetter(this.row) + this.num + ", TicketId:" + this.id + ", DateTime: " + this.show.getDateTime() + ", Price: $" + this.price + ", Film: " + this.show.getFilm().getTitle() + ", Screen: " + this.show.getScreen().getTitle();
    }
    
}
