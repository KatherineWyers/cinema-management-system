import java.util.*;
/**
 * The Reporter creates the Reports
 * It gathers information from the 
 *
 * @author Katherine Wyers
 * @version 12-DEC-2017
 */
public class Reporter
{
    private Cinema cinema;
    
    /**
     * Constructor for objects of class Reporter
     */
    public Reporter(Cinema cinema)
    {
        this.cinema = cinema;
    }

    /**
     * getTicketReportList
     * Get a list of TicketReports, sorted in 
     * descending order by ticketCount. 
     *
     * @param int month 0: Jan, 1: Feb... 11: Dec
     * @param int year
     * @return List ordered list of TicketReports
     */
    public List getTicketReportList(int month, int year)
    {
        Map<Integer, TicketReport> ticketReportMap = new HashMap<Integer, TicketReport>(); // Integer is the FilmID
        // Iterate through all tickets. If the filmId is not set, create a new TicketReport, 
        // If the filmId has already been set, increment the count. If a rating exists, add the rating and increment the ratingcounter
        Iterator it = this.cinema.getTickets().values().iterator();
        while (it.hasNext())
        {
            Ticket ticket = (Ticket) (  it.next()  );
            
            int ticketMonth = ticket.getShow().getDate().get(Calendar.MONTH);
            int ticketYear = ticket.getShow().getDate().get(Calendar.YEAR);
            
            if(ticketMonth==month&&ticketYear==year)
            {
                // Check if the film has already been added to the ticketReportMap
                if(ticketReportMap.get(ticket.getShow().getFilm().getId())==null)
                {
                    TicketReport ticketReport = new TicketReport(ticket.getShow().getFilm(), month, year);// create new TicketReport
                    ticketReportMap.put(ticket.getShow().getFilm().getId(),ticketReport);// add to the map, with filmId as key
                }
                ticketReportMap.get(ticket.getShow().getFilm().getId()).incrementTicketCounter();// increment the ticket counter for the ticketReport
                if(this.cinema.getReviews().get(ticket.getId())!=null)
                {
                    ticketReportMap.get(ticket.getShow().getFilm().getId()).addRating(this.cinema.getReviews().get(ticket.getId()).getRating());/// add the rating to the ratingSum;
                }                
            }
        }
        ArrayList<TicketReport> ticketReportList = new ArrayList<TicketReport> (ticketReportMap.values());// Convert the map to a list
        Collections.sort(ticketReportList, new TicketCountComparator());// Sorts the array list using comparator
        return ticketReportList;// Return the sorted list
    }

    /**
     * getIncomeReportList
     * Get a list of IncomeReports, sorted in 
     * descending order by incomeSum. 
     *
     * @param int month 0: Jan, 1: Feb... 11: Dec
     * @param int year
     * @return List ordered list of TicketReports
     */
    public List getIncomeReportList(int month, int year)
    {
        Map<Integer, IncomeReport> incomeReportMap = new HashMap<Integer, IncomeReport>(); // Integer is the FilmID
        // Iterate through all tickets. If the filmId is not set, create a new IncomeReport, 
        Iterator it = this.cinema.getTickets().values().iterator();
        while (it.hasNext())
        {
            Ticket ticket = (Ticket) (  it.next()  );
            int ticketMonth = ticket.getShow().getDate().get(Calendar.MONTH);
            int ticketYear = ticket.getShow().getDate().get(Calendar.YEAR);
            
            if(ticketMonth==month&&ticketYear==year)
            {
                // Check if the film has already been added to the ticketReportMap
                if(incomeReportMap.get(ticket.getShow().getFilm().getId())==null)
                {
                    IncomeReport incomeReport = new IncomeReport(ticket.getShow().getFilm(), month, year);// create new IncomeReport
                    incomeReportMap.put(ticket.getShow().getFilm().getId(),incomeReport);// add to the map, with filmId as key
                }
                incomeReportMap.get(ticket.getShow().getFilm().getId()).addIncome(ticket.getPrice());/// add the rating to the ratingSum;
            }
        }
        ArrayList<IncomeReport> incomeReportList = new ArrayList<IncomeReport> (incomeReportMap.values());// Convert the map to a list
        Collections.sort(incomeReportList, new IncomeComparator());// Sorts the array list using comparator
        return incomeReportList;// Return the sorted list
    }
}
