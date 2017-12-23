import java.util.*;
/**
 * Write a description of class TicketReport here.
 *
 * @author Katherine Wyers
 * @version 23-DEC-2017
 */
public class TicketReport extends Report
{
    private int ticketCounter = 0;
    private int ratingCounter = 0;
    private long ratingSum = 0;
    
    /**
     * Constructor for objects of class TicketReport
     */
    public TicketReport(Film film, int month, int year)
    {
        this.film = film;
        this.month = month;
        this.year = year;
    }
    
    /**
     * incrementTicketCounter
     * @return void
     */
    public void incrementTicketCounter()
    {
        this.ticketCounter++;
    }
    
    /**
     * incrementRatingCounter
     * @return void
     */
    public void incrementRatingCounter()
    {
        this.ratingCounter++;
    }
    
    /**
     * incrementRatingCounter
     * @return void
     */
    public void addRating(int rating)
    {
        this.ratingSum = this.ratingSum + rating;
    } 
    
    /**
     * getTicketCounter
     * Get total count of tickets sold
     * @return int
     */
    public int getTicketCounter()
    {
        return this.ticketCounter;
    } 
    
    
    /**
     * getAverageRating
     * @return float averageRating
     */
    private String getAverageRating()
    {
        if(ratingCounter == 0)
        {
            return "N/A";
        }
        return ((float)ratingSum/(float)ratingCounter) + " (" + ratingCounter + " reviews)";
    }
    
    /**
     * toString()
     * @return String 
     */
    public String toString()
    {
        return "Film: " + this.film.getTitle() + ", " + "TicketsSold: " + this.ticketCounter + ", AverageRating " + this.getAverageRating();
    }
}
