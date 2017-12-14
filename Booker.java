import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class Booker here.
 *
 * @author Katherine Wyers
 * @version 1.0 
 */
public class Booker
{
    private List<SeatReservation> seatReservationList;
    private final Cinema cinema;
    private Projection projection;
    
    /**
     * Constructor for objects of class Booker
     */
    public Booker(Cinema cinema)
    {
        this.seatReservationList = new ArrayList<SeatReservation>();
        this.cinema = cinema;
    }
    
    /**
     * get Cinema owner
     * @return Cinema cinema
     */
    public Cinema getCinema()
    {
        return this.cinema;
    }
    
    /**
     * Get the proposed seating grid updated with the temporary seat reservations
     * @param Projection p
     * @return boolean[][]
     */
    public boolean[][] getProposedSeatingGrid(Projection p)
    {
        // reset if a new projection was selected
        resetOnNewProjection(p);
        
        // initialise
        boolean[][] proposedSeatingGrid = p.getSeatingGrid().clone();
        
        for(SeatReservation sr : seatReservationList)
        {
            proposedSeatingGrid[sr.getRow()-1][sr.getNum()-1] = true;
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
            SeatReservation s = new SeatReservation(row, num, price);
            this.seatReservationList.add(s);
        }
    }

    /**
     * Get the current booking price
     * @return void
     */
    public double getTotalPrice()
    {
        double totalPrice = 0.0;
        for(SeatReservation s : seatReservationList)
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
        for(SeatReservation s : seatReservationList)
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
    public List<SeatReservation> getSeatReservationList()
    {
        return seatReservationList;
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
            this.seatReservationList = new ArrayList<SeatReservation>();
        }
    }
}
