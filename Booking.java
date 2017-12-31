import java.util.List;
/**
 * Booking Class
 * 
 * Each booking is one purchase of one or 
 * many tickets. Each booking can have multiple 
 * payments if the there is a ticket-transfer 
 * that incurs a surcharge
 *
 * @author Katherine Wyers 
 * @version 1.0
 */
public class Booking
{
    private int id;
    private Customer customer;

    /**
     * Constructor for objects of class Booking
     * 
     * @param int id
     * @param Customer customer
     */
    public Booking(int id, Customer customer)
    {
        this.id = id;
        this.customer = customer;
    }
    
    /**
     * getId
     * 
     * @return int id
     */
    public int getId()
    {
        return this.id;
    }
    
    /**
     * getCustomer
     * 
     * Get the customer who made the booking
     * 
     * @return Customer customer
     */
    public Customer getCustomer()
    {
        return this.customer;
    }
    
    /**
     * toString
     * @return String
     */
    @Override
    public String toString()
    {
        return "Booking for Customer: " + this.customer.toString();
    }
}
