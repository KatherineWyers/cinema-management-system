import java.util.List;
/**
 * Write a description of class Booking here.
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
     */
    public Booking(int id, Customer customer)
    {
        this.id = id;
        this.customer = customer;
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
     * getCustomer
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
