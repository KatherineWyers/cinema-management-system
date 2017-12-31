/**
 * Screen Class
 * Each individual Screen 
 *
 * @author Katherine Wyers
 * @version 1.0
 */
public class Screen
{
    private int id;
    private String title;

    /**
     * Constructor for objects of class Screen
     * 
     * @param id int 
     * @param title String 
     */
    public Screen(int id, String title)
    {
        this.id = id;
        this.title = title;
    }

    /**
     * getId
     *
     * Get the Screen Id number
     * 
     * @return int
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * getTitle
     *
     * Get the Title of the screen 
     * 
     * @return String
     */
    public String getTitle()
    {
        return this.title;
    }

    /**
     * toString
     *
     * Get a string with the screen title
     * 
     * @return String 
     */
    public String toString()
    {
        return "Screen: " + this.title;
    }
}
