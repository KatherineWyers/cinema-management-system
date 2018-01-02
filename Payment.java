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
    private static int nextUnusedId = 1;
    
    protected final int id;
    protected final float amount;
    protected final Booking booking;
    
    /**
     * Constructor for the Payment
     * 
     * @param id int 
     * @param amount float 
     * @param booking Booking 
     */
    public Payment (int id, float amount, Booking booking)
    {
        this.id = id;
        this.amount = amount;
        this.booking = booking;
    }
    
    /**
     * getNextId
     * 
     * Get the unused incremented id
     *
     * @return int 
     */
    public static int getNextId()
    {
        return Payment.nextUnusedId++;
    }
    
    /**
     * getId
     * 
     * Get the id for the payment
     * 
     * @return int
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
     * @return float
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
     * @return Booking
     */
    public Booking getBooking()
    {
        return this.booking;
    }
}

