
/**
 * Film Class
 * 
 * Each film object represents one copy of the 
 * film. If the cinema has two copies of the same 
 * film, then each copy will have their own Film instance
 *
 * @author Katherine Wyers
 * @version DEC-2017
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
     * Constructor for Film objects without subtitles
     * 
     * Create the Film object and complete the subtitle variable as "N/A"
     * 
     * @param int id
     * @param String title
     * @param int year
     * @param String director
     * @param String language
     */
    public Film(int id, String title, int year, String director, String language)
    {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.language = language;
        this.subtitles = "N/A";
    }
    
    /**
     * Constructor for Film objects with subtitles
     * 
     * Create the Film object with subtitles
     * 
     * @param int id
     * @param String title
     * @param String director
     * @param String language
     * @param String subtitles
     */
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
     * 
     * Get the FilmId
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
     * Get the film title
     * 
     * @return String title
     */
    public String getTitle()
    {
        return this.title;
    }
    
    /**
     * getYear
     * 
     * Get the year that the film was made
     * 
     * @return int year
     */
    public int getYear()
    {
        return this.year;
    }
    
    /**
     * getDirector
     * 
     * Get the name of the director of the film
     * 
     * @return String director
     */
    public String getDirector()
    {
        return this.director;
    }
    
    /**
     * getLanguage
     * 
     * Get the speaking language that the film uses
     * 
     * @return String language
     */
    public String getLanguage()
    {
        return this.language;
    }
    
    /**
     * getSubtitles
     * 
     * Get the subtitles that are displayed for the film
     * 
     * @return String subtitles
     */
    public String getSubtitles()
    {
        return this.subtitles;
    }
    
    /**
     * toString
     * 
     * Get a string with the film title, director, language and subtitle
     * 
     * @return String
     */
    public String toString()
    {
        return this.title + "(" + this.year + "), Dir: " + this.director + ", Lang: " + this.language + ", Sub: " + this.subtitles;
    }
}
