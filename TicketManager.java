/**
 * Abstract class TicketManager - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class TicketManager
{
    protected Cinema cinema;
    protected Projection projection;
    protected Booking booking;
    
    /**
     * get Cinema
     * @return Cinema cinema
     */
    public Cinema getCinema()
    {
        return this.cinema;
    }
    
    /**
     * get Projection
     * @return Projection projection
     */
    public Projection getProjection()
    {
        return this.projection;
    }
    
    /**
     * Check whether the seat at the given row and num is available 
     * @param int row
     * @param int num
     * @return boolean
     */
    public boolean isValidSeatSelection(int row, int num)
    {
        return(!this.getSeatingGrid()[row-1][num-1]);
    }
    
    abstract public void finalizeCashPayment();
    abstract public void finalizeCardPayment(String referenceNumber);
    abstract public boolean[][] getSeatingGrid();
}
