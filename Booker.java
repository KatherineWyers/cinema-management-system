import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * Write a description of class Booker here.
 *
 * @author Katherine Wyers
 * @version 1.0 
 */
public class Booker
{
    private List<SeatReservation> seatReservations;
    private final Cinema cinema;
    private Projection projection;
    private Booking booking;
    
    /**
     * Constructor for objects of class Booker
     */
    public Booker(Cinema cinema, Customer customer)
    {
        this.seatReservations = new ArrayList<SeatReservation>();
        this.cinema = cinema;
        this.booking = new Booking(this.cinema.getNextBookingId(), customer);
    }
    
    /**
     * get Cinema
     * @return Cinema cinema
     */
    public Cinema getCinema()
    {
        return this.cinema;
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
     * Get the proposed seating grid updated with the temporary seat reservations
     * @param Projection p
     * @return boolean[][]
     */
    public boolean[][] getProposedSeatingGrid(Projection projection)
    {
        // reset if a new projection was selected
        resetOnNewProjection(projection);
        
        // initialise
        boolean[][] proposedSeatingGrid = this.cinema.getSeatingGrid(projection).clone();

        // Update grid with booked tickets        
        for(SeatReservation seatReservation : seatReservations)
        {
            proposedSeatingGrid[seatReservation.getRow()-1][seatReservation.getNum()-1] = true;
        }
        return proposedSeatingGrid;
    }

    /**
     * Create a temporary seat reservation
     * 
     * @param int row
     * @param int num
     * @param double price
     * @return void
     */
    public void addSeatReservation(Projection p, int row, int num)
    {
        // reset if a new projection was selected
        resetOnNewProjection(p);
        
        if(!isValidSeatReservation(this.projection, row, num))
        {
            System.out.println("The seat at " + cinema.convertToRowLetter(row) + num + " is unavailable");
        }
        else
        {
            double price = (row == 5) ? this.projection.getPriceVip() : this.projection.getPriceRegular();
            SeatReservation s = new SeatReservation(projection, row, num, price);
            this.seatReservations.add(s);
        }
    }

    /**
     * Get the current booking price
     * @return void
     */
    public double getTotalPrice()
    {
        double totalPrice = 0.0;
        for(SeatReservation s : seatReservations)
        {
            totalPrice = totalPrice + s.getPrice();
        }
        return totalPrice;
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
        for(SeatReservation s : seatReservations)
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
    public List<SeatReservation> getSeatReservations()
    {
        return seatReservations;
    }
    
    /**
     * Check whether the seat at the given row and num is available 
     * @param int row
     * @param int num
     * @return boolean
     */
    public boolean isValidSeatReservation(Projection p, int row, int num)
    {
        // reset if a new projection was selected
        resetOnNewProjection(p);
        
        return(!this.getProposedSeatingGrid(this.projection)[row-1][num-1]);
    }
    
    /**
     * Finalize the current booking
     * Add seatReservations to the projection and create the payment
     * @param int row
     * @param int num
     * @return boolean
     */
    public void finalizeCashBooking()
    {
        System.out.println("Need to create the cash booking feature");
        this.convertToTickets();
    }
    
    /**
     * Finalize the current booking
     * Add seatReservations to the projection and create the payment
     * @param int row
     * @param int num
     * @return boolean
     */
    public void finalizeCardBooking(String ccReference)
    {
        System.out.println("Need to create the credit card booking feature");
        this.convertToTickets();
        System.out.println(this.cinema.getTickets(booking));
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
        
        Iterator<SeatReservation> it = seatReservations.iterator();
        while (it.hasNext()) 
        {
            SeatReservation seatReservation = it.next();
            Ticket ticket = new Ticket(this.cinema.getNextTicketId(), seatReservation, booking);
            this.cinema.addTicket(ticket);
            it.remove();
        }   
    }
    
    
    
    /**
     * resetOnNewProjection()
     * switch the projection and clear the seatReservationList
     * @param Projection p
     * @return void
     */
    private void resetOnNewProjection(Projection p)
    {
        if(!p.equals(this.projection))
        {
            this.projection = p;
            this.seatReservations = new ArrayList<SeatReservation>();
        }
    }
}
