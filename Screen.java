
/**
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
     */
    public Screen(int id, String title)
    {
        this.id = id;
        this.title = title;
    }

    /**
     * getId
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
     * @return String title
     */
    public String getTitle()
    {
        return this.title;
    }

    /**
     * toString
     *
     * @return String 
     */
    public String toString()
    {
        return "Screen: " + this.title;
    }
}
