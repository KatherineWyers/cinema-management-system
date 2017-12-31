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
     * @param int id
     * @param String title
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
     * @return int id
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
     * @return String title
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
