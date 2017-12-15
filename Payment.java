
/**
 * Payment class
 * This class is for all cash payments. 
 * Credit card payments inherit from this class
 *
 * @author Katherine Wyers
 * @version 1.0
 */
public class Payment
{
    protected final int id;
    protected final double amount;
    protected final Booking booking;
    
    /**
     * Constructor for the Payment
     */
    public Payment (int id, double amount, Booking booking)
    {
        this.id = id;
        this.amount = amount;
        this.booking = booking;
    }
    
    /**
     * getId
     * @return int id
     */
    public int getId()
    {
        return this.id;
    }
    
    /**
     * getAmount
     * @return double amount
     */
    public double getAmount()
    {
        return this.amount;
    }
    
    /**
     * getBooking
     * @return int amount
     */
    public Booking getBooking()
    {
        return this.booking;
    }
}

