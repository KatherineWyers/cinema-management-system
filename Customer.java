
/**
 * Customer Class
 * Each customer object represents one customer
 *
 * @author Katherine Wyers
 * @version DEC-2017
 */
public class Customer
{
    private static int nextUnusedId = 1;
    
    private int id;
    private String name;

    /**
     * Constructor for Customer without a name
     * 
     * Create a customer and complete the name as blank
     * 
     * @param id int 
     */
    public Customer(int id)
    {
        this.id = id;
        this.name = "";
    }
    
    /**
     * Constructor for Customer with a name
     * 
     * Create a customer with a name field
     * 
     * @param id int 
     * @param name String 
     */
    public Customer(int id, String name)
    {
        this.id = id;
        this.name = name;
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
        return Customer.nextUnusedId++;
    }
    
    /**
     * getId
     * 
     * Get the customerId number
     * 
     * @return int 
     */
    public int getId()
    {
        return this.id;
    }
    
    /**
     * getName
     * 
     * Get the customer name
     * 
     * @return String 
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * toString
     * 
     * Get the customer and and CustomerId as a string
     * 
     * @return String
     */
    @Override
    public String toString()
    {
        return this.name + "(CustomerID: " + this.id + ")"; 
    }
}
