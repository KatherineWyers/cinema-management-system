import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * Write a description of class Booker here.
 *
 * @author Katherine Wyers
 * @version 1.0 
 */
public class Booker extends TicketManager
{
    private List<Reservation> reservations;
    
    /**
     * Constructor for objects of class Booker
     */
    public Booker(Cinema cinema, Show show, Customer customer)
    { 
        this.reservations = new ArrayList<Reservation>();
        this.show = show;
        this.cinema = cinema;
        this.booking = new Booking(this.cinema.getNextBookingId(), customer);
    }
    
    /**
     * get Booking booking
     * @return Booking booking
     */
    public Booking getBooking()
    {
        return this.booking;
    }    
    
    /**
     * Create a temporary seat reservation
     * 
     * @param int row
     * @param int num
     * @return void
     */
    public void addReservation(int row, int num)
    {
        if(!isValidSeatSelection(row, num))
        {
            System.out.println("The seat at " + this.cinema.convertToRowLetter(row) + num + " is unavailable");
        }
        else
        {
            Reservation s = new Reservation(show, row, num);
            this.reservations.add(s);
        }
    }

    /**
     * Remove a temporary seat reservation
     * if it exists. 
     * If it doesn't exist, do nothing
     * 
     * @param int row
     * @param int num
     * @param double price
     * @return void
     */
    public void removeReservation(int row, int num)
    {
        Iterator<Reservation> it = reservations.iterator();
        while (it.hasNext()) 
        {
            Reservation reservation = it.next();
            if(reservation.getRow() == row&&reservation.getNum()==num)
            {
                it.remove();
            }
        }
    }

    /**
     * Get the current booking price
     * @return void
     */
    public double getTotalPrice()
    {
        double totalPrice = 0.0;
        for(Reservation s : reservations)
        {
            totalPrice = totalPrice + s.getPrice();
        }
        return totalPrice;
    }
    
    /**
     * Get the proposed seating grid updated with the temporary seat reservations
     * @param Show p
     * @return boolean[][]
     */
    public boolean[][] getSeatingGrid()
    {   
        // initialise
        boolean[][] seatingGrid = this.cinema.getSeatingGrid(show).clone();

        // Update grid with booked tickets        
        for(Reservation reservation : reservations)
        {
            seatingGrid[reservation.getRow()-1][reservation.getNum()-1] = true;
        }
        return seatingGrid;
    }
    
    /**
     * printCurrentBookingDetails
     * Get the seat reservations and the price of each
     * @return void 
     */
    public void printCurrentBookingDetails()
    {
        System.out.println("###CURRENT BOOKING###");
        System.out.println("SEAT------PRICE------");
        for(Reservation s : reservations)
        {
            System.out.println(cinema.convertToRowLetter(s.getRow()) + s.getNum() + "--------" + s.getPrice());
        }
        System.out.println("---------------------");
        System.out.println("TOTAL PRICE: " + this.getTotalPrice());
        System.out.println("---------------------");
    }
    
    /**
     * getSeatReservations
     * Get the seat reservations and the price of each
     * @return List<SeatReservation> seatReservations 
     */
    public List<Reservation> getReservations()
    {
        return reservations;
    }
    
    /**
     * Finalize the current booking
     * Add seatReservations to the show and create the payment
     * @param int row
     * @param int num
     * @return boolean
     */
    public void finalizeCashPayment()
    {
        Payment payment = new Payment(this.cinema.getNextPaymentId(), this.getTotalPrice(), this.booking);
        this.cinema.addPayment(payment);      
        this.convertToTickets();
    }
    
    /**
     * Finalize the current booking
     * Add seatReservations to the show and create the payment
     * @param int row
     * @param int num
     * @return boolean
     */
    public void finalizeCardPayment(String referenceNumber)
    {
        Payment payment = new CardPayment(this.cinema.getNextPaymentId(), this.getTotalPrice(), this.booking, referenceNumber);
        this.cinema.addPayment(payment); 
        this.convertToTickets();
    }
    
    /**
     * Create a new Ticket for each seat reservation
     * Remove each Seat Reservation from the list
     * If the Booking is not already stored, store the booking
     * @return void
     */
    private void convertToTickets()
    {    
        if(this.cinema.getBookings().get(this.booking.getId()) == null)
        {
            // booking has not yet been stored to persistent storage
            this.cinema.addBooking(this.booking);
        }
        
        Iterator<Reservation> it = reservations.iterator();
        while (it.hasNext()) 
        {
            Reservation reservation = it.next();
            Ticket ticket = new Ticket(this.cinema.getNextTicketId(), reservation, booking);
            this.cinema.addTicket(ticket);
            it.remove();
        }   
    }
}
