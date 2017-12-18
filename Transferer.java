
/**
 * Transferer manages the rescheduling of Tickets to a new show
 * or a new seat
 *
 * @author Katherine Wyers
 * @version 1.0
 */
public class Transferer extends TicketManager
{
    // instance variables - replace the example below with your own
    private Ticket ticket;
    
    // the rescheduler will only ever deal with one ticket-reschedule at a time
    private Reservation reservation;

    /**
     * Constructor for objects of class Rescheduler
     */
    public Transferer(Cinema cinema, Show show, Ticket ticket)
    {
        this.cinema = cinema;
        this.show = show;
        this.ticket = ticket;
        this.booking = ticket.getBooking();
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

        if(reservation != null)
        {
            // If a reservation has been set, update the grid with the reservation        
            seatingGrid[reservation.getRow()-1][reservation.getNum()-1] = true;
        }

        return seatingGrid;
    }  

    /**
     * Set the temporary seat reservation
     * Can be used to create a new reservation, or to change the current reservation
     * 
     * @param int row
     * @param int num
     * @return void
     */
    public void setReservation(int row, int num)
    {
        if(!this.isValidSeatSelection(row, num))
        {
            System.out.println("The seat at " + this.cinema.convertToRowLetter(row) + num + " is unavailable");
        }
        else
        {
            this.reservation = new Reservation(show, row, num);
        }
    }
    
    /**
     * Finalize the changing of the for the selected seat in the current show
     * This method will also be used if the transfer is free. 
     * @return void
     */
    public void finalizeCashPayment()
    {
        this.setNewDetailsForTicket();
        
        // Only create a payment if the new Ticket is more expensive than the old ticket. 
        // The cinema does not offer refunds if the new Ticket is cheaper than the old ticket
        if(reservation.getPrice() > ticket.getPrice())
        {
            double upgradeSurcharge = reservation.getPrice() - ticket.getPrice();
            Payment payment = new Payment(this.cinema.getNextPaymentId(), upgradeSurcharge, this.booking);
            this.cinema.addPayment(payment);   
        }
    }
    
    /**
     * Finalize the changing of the for the selected seat in the current show
     * @return void
     */
    public void finalizeCardPayment(String referenceNumber)
    {
        this.setNewDetailsForTicket();
        
        // Only create a payment if the new Ticket is more expensive than the old ticket. 
        // The cinema does not offer refunds if the new Ticket is cheaper than the old ticket
        if(reservation.getPrice() > ticket.getPrice())
        {
            double upgradeSurcharge = reservation.getPrice() - ticket.getPrice();
            Payment payment = new CardPayment(this.cinema.getNextPaymentId(), upgradeSurcharge, this.booking, referenceNumber);
            this.cinema.addPayment(payment);   
        }
    }
    
    /**
     * Change Ticket details
     * @return void
     */
    private void setNewDetailsForTicket()
    {
        ticket.setDetails(reservation.getShow(), reservation.getRow(), reservation.getNum());
    }

}
