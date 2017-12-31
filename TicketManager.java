/**
 * Abstract class TicketManager
 * The abstract parent class for Booker and Transferer
 *
 * @author Katherine Wyers
 * @version DEC-2017
 */
public abstract class TicketManager
{
    protected Cinema cinema;
    protected Show show;
    protected Booking booking;
    
    /**
     * get Cinema
     * 
     * Get the cinema
     * 
     * @return Cinema cinema
     */
    public Cinema getCinema()
    {
        return this.cinema;
    }
    
    /**
     * getShow
     * 
     * Get the show
     * 
     * @return Show show
     */
    public Show getShow()
    {
        return this.show;
    }
    
    /**
     * isValidSeatSelection
     * 
     * Check whether the seat at the given row and num is available 
     * for this show
     * 
     * @param row int
     * @param num int
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
