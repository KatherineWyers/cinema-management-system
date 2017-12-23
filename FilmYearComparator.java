import java.util.Comparator;
/**
 * Write a description of class TicketCountComparator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FilmYearComparator implements Comparator<Film>
{

    /**
     * Constructor for objects of class TicketCountComparator
     */
    public FilmYearComparator()
    {}

    /**
     * An example of a method - replace this comment with your own
     * Descending order
     *
     * @param TicketReport ticketReport1
     * @param TicketReport ticketReport2
     * @return int
     */
    @Override
    public int compare(Film film1, Film film2)
    {
        return film2.getYear() - film1.getYear();
    }
}
