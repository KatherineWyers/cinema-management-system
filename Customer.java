
/**
 * Write a description of class Customer here.
 *
 * @author Katherine Wyers
 * @version 1.0
 */
public class Customer
{
    private int id;
    private String name;

    /**
     * Constructor for objects of class Customer
     */
    public Customer(int id)
    {
        this.id = id;
        this.name = "";
    }
    
    public Customer(int id, String name)
    {
        this.id = id;
        this.name = name;
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
     * getName
     * @return String name
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * toString
     * @return String
     */
    @Override
    public String toString()
    {
        return this.name + "(CustomerID: " + this.id + ")"; 
    }
}
