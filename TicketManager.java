/**
 * Abstract class TicketManager - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class TicketManager
{
    protected Cinema cinema;
    protected Show show;
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
     * get Show
     * @return Show show
     */
    public Show getShow()
    {
        return this.show;
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
