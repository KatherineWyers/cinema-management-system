import java.util.*;
/**
 * TicketReport Class
 * Each instance is a monthly report of the 
 * ticket sales and average rating for one film
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
     * 
     * @param film Film 
     * @param month int 
     * @param year int 
     */
    public TicketReport(Film film, int month, int year)
    {
        this.film = film;
        this.month = month;
        this.year = year;
    }
    
    /**
     * incrementTicketCounter
     * 
     * Increment the ticket counter by 1
     */
    public void incrementTicketCounter()
    {
        this.ticketCounter++;
    }
    
    /**
     * addRating
     * 
     * Add the rating to the total sum of ratings. 
     * Increment the rating counter by 1
     * 
     * @param rating int
     */
    public void addRating(int rating)
    {
        this.ratingSum = this.ratingSum + rating;
        this.ratingCounter++;
    } 
    
    /**
     * getTicketCounter
     * 
     * Get total count of tickets sold
     * 
     * @return int
     */
    public int getTicketCounter()
    {
        return this.ticketCounter;
    } 
    
    /**
     * getRatingCounter
     * 
     * Get total count of ratings
     * 
     * @return int
     */
    public int getRatingCounter()
    {
        return this.ratingCounter;
    } 
    
    
    /**
     * getAverageRating
     * 
     * Sum of ratings divided by rating count
     * If rating count is zero, return string "N/A"
     * 
     * @return String
     */
    public String getAverageRating()
    {
        if(ratingCounter == 0)
        {
            return "N/A";
        }
        return ((float)ratingSum/(float)ratingCounter) + " (" + ratingCounter + " reviews)";
    }
    
    /**
     * toString()
     * 
     * Get a string with Film-title, tickets sold and average rating
     * 
     * @return String 
     */
    public String toString()
    {
        return "Film: " + this.film.getTitle() + ", " + "TicketsSold: " + this.ticketCounter + ", AverageRating " + this.getAverageRating();
    }
}
