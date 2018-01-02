import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * The Booker is the ticket-manager that 
 * handles the booking process. 
 * It manages seat reservation for new 
 * tickets, and processes the payments 
 * for these new tickets
 *
 * @author Katherine Wyers
 * @version 1.0 
 */
public class Booker extends TicketManager implements MultiReservationable
{
    private List<Reservation> reservations;
    
    /**
     * Constructor for objects of class Booker
     * 
     * @param cinema Cinema
     * @param show Show
     * @param customer Customer
     */
    public Booker(Cinema cinema, Show show, Customer customer)
    { 
        this.reservations = new ArrayList<Reservation>();
        this.show = show;
        this.cinema = cinema;
        this.booking = new Booking(Booking.getNextId(), customer);
    }
    
    /**
     * get Booking
     * 
     * @return Booking booking
     */
    public Booking getBooking()
    {
        return this.booking;
    }    
    
    /**
     * addReservation
     * 
     * Create a temporary seat reservation
     * Seats are temporarily reserved during the 
     * booking process. These temporary reservations 
     * are stored in an ArrayList.
     * 
     * @param row int
     * @param num int
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
     * removeReservation
     * 
     * Remove the selected reservation from the 
     * temporary reservations ArrayList.
     * If the row/num combination is not found 
     * in the reservations ArrayList, do nothing
     * 
     * @param row int
     * @param num int
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
     * getTotalPrice
     * 
     * Get the current price of the booking
     * This value adjusts as reservations are 
     * added or removed
     * 
     * @return float
     */
    public float getTotalPrice()
    {
        float totalPrice = (float)0.0;
        for(Reservation s : reservations)
        {
            totalPrice = totalPrice + s.getPrice();
        }
        return totalPrice;
    }
    
    /**
     * getSeatingGrid
     * 
     * Get the proposed seating grid updated with the temporary seat reservations
     * 
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
     * getReservations
     * 
     * Get the seat reservations and the price of each
     * 
     * @return List seatReservations 
     */
    public List<Reservation> getReservations()
    {
        return reservations;
    }
    
    /**
     * isExistReservation
     * 
     * Check whether there is a 
     * reservation at that seat
     * 
     * @param row int
     * @param num int
     * @return boolean
     */
    public boolean isExistReservation(int row, int num)
    {
        for(Reservation r : this.getReservations())
        {
            if(r.getShow()==this.show&&r.getRow()==row&&r.getNum()==num)
            {
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * finalizeCashPayment
     * 
     * Finalize the current booking by cash
     * Add seatReservations to the show
     * convert all temporary reservations to 
     * tickets and record the payment
     */
    public void finalizeCashPayment()
    {
        Payment payment = new Payment(Payment.getNextId(), this.getTotalPrice(), this.booking);
        this.cinema.addPayment(payment);      
        this.convertToTickets();
    }
    
    /**
     * finalizeCardPayment
     * 
     * Finalize the current booking
     * Add seatReservations to the show and create the payment
     * 
     * @param referenceNumber String
     */
    public void finalizeCardPayment(String referenceNumber)
    {
        Payment payment = new CardPayment(Payment.getNextId(), this.getTotalPrice(), this.booking, referenceNumber);
        this.cinema.addPayment(payment); 
        this.convertToTickets();
    }
    
    /**
     * convertToTickets
     * 
     * Create a new Ticket for each seat reservation
     * Remove each Seat Reservation from the list
     * If the Booking is not already stored, store the booking
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
            Ticket ticket = new Ticket(Ticket.getNextId(), reservation, booking);
            this.cinema.addTicket(ticket);
            it.remove();
        }   
    }
}
