import java.util.Comparator;
/**
 * TicketCountComparator Class
 * Compare two TicketReport objects based 
 * on their ticket count
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
     * compare
     * 
     * Compare the two ticket reports based on their ticket 
     * count. Return either a positive or a negative integer, 
     * depending on which has the greater ticket count
     *
     * @param ticketReport1 TicketReport 
     * @param ticketReport2 TicketReport 
     * @return int
     */
    @Override
    public int compare(TicketReport ticketReport1, TicketReport ticketReport2)
    {
        return ticketReport2.getTicketCounter() - ticketReport1.getTicketCounter();
    }
}
