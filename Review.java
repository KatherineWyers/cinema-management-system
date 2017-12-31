/**
 * Review Class
 * The Review object stores the review text 
 * submitted by the customer, together with 
 * the film rating from 1 to 5
 *
 * @author Katherine Wyers
 * @version 1.0 - Dec 2017
 */
public class Review
{
    private String review;
    private int rating;
    private Ticket ticket;
    
    /**
     * Constructor for objects of class Review
     * 
     * Validates the rating to the range 1 to 5, and throws an 
     * IllegalArgumentException if the rating is outside this 
     * range
     * 
     * @param ticket Ticket 
     * @param review String 
     * @param rating int 
     */
    public Review(Ticket ticket, String review, int rating) throws IllegalArgumentException
    {
        if (rating < 0 || rating > 5) {
          throw new IllegalArgumentException("Rating not in range [0..1]: " + rating);
        }
        this.ticket = ticket;
        this.review = review;
        this.rating = rating;
    }
    
    /**
     * getRating
     * 
     * Get the rating that the customer submitted
     * 
     * @return int
     */
    public int getRating()
    {
      return this.rating;   
    }
    
    /**
     * getReview
     * 
     * Get the review that the customer submitted
     * 
     * @return String
     */
    public String getReview()
    {
      return this.review;   
    }
}
