
/**
 * MultiReservationable Interface
 * An interface for the TicketManager subclasses 
 * that have multiple Reservation objects
 *
 * @author Katherine Wyers
 * @version JAN-2018
 */
public interface MultiReservationable
{
    /**
     * isExistReservation
     * 
     * Check whether the object has a Reservation object
     * with the specified row and num
     *
     * @param row int
     * @param num int
     * @return boolean
     */
    boolean isExistReservation(int row, int num);
}
