
/**
 * Film 
 *
 * @author Katherine Wyers
 * @version 1.0
 */
public class Film
{
    private int id;
    private String title;
    private int year;
    private String director;
    private String language;
    private String subtitles;

    /**
     * Constructor for objects of class Film
     */
    public Film(int id, String title, int year, String director, String language)
    {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.language = language;
        this.subtitles = "";
    }
    
    public Film(int id, String title, int year, String director, String language, String subtitles)
    {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.language = language;
        this.subtitles = subtitles;
    }
    
    /**
     * getId
     * @return int id
     */
    public int getId()
    {
        return this.id;
    }
    
    /**
     * getTitle
     * @return String title
     */
    public String getTitle()
    {
        return this.title;
    }
    
    /**
     * getDirector
     * @return String director
     */
    public String getDirector()
    {
        return this.director;
    }
    
    /**
     * getLanguage
     * @return String language
     */
    public String getLanguage()
    {
        return this.language;
    }
    
    /**
     * getSubtitles
     * @return String subtitles
     */
    public String getSubtitles()
    {
        return this.subtitles;
    }
    
    /**
     * toString
     * @return String
     */
    public String toString()
    {
        return this.id + ", " + this.title + "(" + this.year + "), Dir: " + this.director + ", Lang: " + this.language + ", Sub: " + this.subtitles;
    }
}
