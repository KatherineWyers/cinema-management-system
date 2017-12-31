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
    protected final float amount;
    protected final Booking booking;
    
    /**
     * Constructor for the Payment
     * 
     * @param int id
     * @param float amount
     * @param Booking booking
     */
    public Payment (int id, float amount, Booking booking)
    {
        this.id = id;
        this.amount = amount;
        this.booking = booking;
    }
    
    /**
     * getId
     * 
     * Get the id for the payment
     * 
     * @return int id
     */
    public int getId()
    {
        return this.id;
    }
    
    /**
     * getAmount
     * 
     * Get the amount of the payment
     * 
     * @return float amount
     */
    public float getAmount()
    {
        return this.amount;
    }
    
    /**
     * getBooking
     * 
     * Get the booking that the payment was for
     * 
     * @return Booking booking
     */
    public Booking getBooking()
    {
        return this.booking;
    }
}

