
/**
 * Transferer manages the rescheduling of Tickets to a new show
 * or a new seat
 *
 * @author Katherine Wyers
 * @version 1.0
 */
public class Transferer extends TicketManager
{
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
     * getTicket
     * @return Ticket ticket
     */
    public Ticket getTicket()
    {
        return this.ticket;
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
     * getTicketTransferSurcharge
     * If there is a price increase during the ticket transfer, a surcharge
     * is payable. If there is a price reduction, or the ticket price is the 
     * same, then there is no surcharge.
     * Note: the cinema does not offer refunds if a ticket is transferred 
     * and the new seat is cheaper than the value of the ticket. 
     * @param String ticketType
     * @return float
     */
    public float getTicketTransferSurcharge(String ticketType)
    {
        float ticketSurcharge = (float)0.0;
        if(ticketType.equals("vip")&&this.getShow().getPriceVip() > this.getTicket().getPrice())
        {
            ticketSurcharge = this.getShow().getPriceVip() - this.getTicket().getPrice();
        }
        else if(ticketType.equals("regular")&&this.getShow().getPriceRegular() > this.getTicket().getPrice())
        {
            ticketSurcharge = this.getShow().getPriceRegular() - this.getTicket().getPrice();
        }
        return ticketSurcharge;
    }
    
    /**
     * getSeatingGrid
     * Ignore one ticket. Get the proposed seating grid updated with 
     * the temporary seat reservations. One ticket will be ignored. 
     * This method is used when the seats are being 
     * selected for the ticket transfer
     * @param Ticket ignoreTicket
     * @return boolean[][]
     */
    public boolean[][] getSeatingGridIgnoreTicket(Ticket ignoreTicket)
    {   
        // initialise
        boolean[][] seatingGrid = this.cinema.getSeatingGrid(show).clone();

        if(reservation != null)
        {
            // If a reservation has been set, update the grid with the reservation        
            seatingGrid[reservation.getRow()-1][reservation.getNum()-1] = true;
        }
        
        seatingGrid[ticket.getRow()-1][ticket.getNum()-1] = false;// set seat for false for ticket being ignored

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
     * finalizeNoChargeTransfer()
     * Finalize the changing of the for the selected seat in the current show
     * @return void
     */
    public void finalizeNoChargeTransfer()
    {
        this.setNewDetailsForTicket();
    }
    
    /**
     * Finalize the changing of the for the selected seat in the current show
     * @return void
     */
    public void finalizeCashPayment()
    {
        this.setNewDetailsForTicket();
        
        // Only create a payment if the new Ticket is more expensive than the old ticket. 
        // The cinema does not offer refunds if the new Ticket is cheaper than the old ticket
        if(reservation.getPrice() > ticket.getPrice())
        {
            float surcharge = reservation.getPrice() - ticket.getPrice();
            Payment payment = new Payment(this.cinema.getNextPaymentId(), surcharge, this.booking);
            this.cinema.addPayment(payment);  
            ticket.setPrice(reservation.getPrice());// Update the ticket price
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
            float surcharge = reservation.getPrice() - ticket.getPrice();
            Payment payment = new CardPayment(this.cinema.getNextPaymentId(), surcharge, this.booking, referenceNumber);
            this.cinema.addPayment(payment);   
            ticket.setPrice(reservation.getPrice());// Update the ticket price
        }
    }
    
    /**
     * printCurrentTransferDetails
     * This only executes if a reservation has been set
     * Get the new seat reservation, and the surcharge for the upgrade
     * @return void 
     */
    public void printCurrentTransferDetails()
    {
        if(reservation!=null)
        {
            System.out.println("");
            System.out.println("######CURRENT TRANSFER######");
            System.out.println("#SEAT----------------------#");
            System.out.println("#" + cinema.convertToRowLetter(reservation.getRow()) + reservation.getNum());
            System.out.println("#-------------------------#");
            System.out.println("#TRANSFER PRICE: $" + this.getSurcharge());
            System.out.println("###########################");
            System.out.println("");
        }
    }
    
    /**
     * getSurcharge
     * if the new reservation is cheaper or the same price as 
     * the original ticket, set surcharge to 0.0.
     * No refunds are given
     * @return float surcharge
     */
    public float getSurcharge()
    {
        float surcharge = (float)0.0;
        if(reservation.getPrice() > ticket.getPrice())
        {
            surcharge = reservation.getPrice() - ticket.getPrice();
        }
        return surcharge;
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
