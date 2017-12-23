
/**
 * Write a description of class Review here.
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
     * @return int rating
     */
    public int getRating()
    {
      return this.rating;   
    }
    
    /**
     * getReview
     * @return String review
     */
    public String getReview()
    {
      return this.review;   
    }
}
