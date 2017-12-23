import java.util.Comparator;
/**
 * Write a description of class TicketCountComparator here.
 *
 * @author Katherine Wyers
 * 
 * Adapted from the online tutorial
 * 'How to sort objects using Comparator interface'
 * Created by: Java Made Easy
 * https://www.youtube.com/watch?v=u8D2fydghj4
 * Accessed 23-DEC-2017
 * 
 * @version 23-DEC-2017
 */
public class TicketCountComparator implements Comparator<TicketReport>
{

    /**
     * Constructor for objects of class TicketCountComparator
     */
    public TicketCountComparator()
    {}

    /**
     * An example of a method - replace this comment with your own
     *
     * @param TicketReport ticketReport1
     * @param TicketReport ticketReport2
     * @return int
     */
    @Override
    public int compare(TicketReport ticketReport1, TicketReport ticketReport2)
    {
        return ticketReport2.getTicketCounter() - ticketReport1.getTicketCounter();
    }
}
