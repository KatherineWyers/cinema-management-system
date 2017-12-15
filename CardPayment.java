
/**
 * Write a description of class CardPayment here.
 *
 * @author Katherine Wyers
 * @version 1.0
 */
public class CardPayment extends Payment implements Referencable
{
    private final String referenceNumber;

    /**
     * Constructor for objects of class CardPayment
     */
    public CardPayment(int id, double amount, Booking booking, String referenceNumber)
    {
        super(id, amount, booking);
        this.referenceNumber = referenceNumber;
    }
    
    /**
     * getReferenceNumber
     * @return String referenceNumber
     */
    @Override
    public String getReferenceNumber()
    {
        return this.referenceNumber;
    }
}
