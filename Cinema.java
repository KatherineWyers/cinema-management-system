import java.util.*;
/**
 * Implementation of the Odeon Cinema
 *
 * @author Katherine Wyers
 * @version 1.0
 */
public class Cinema
{
    private static int NEXT_UNUSED_SCREEN_ID = 1;
    private static int NEXT_UNUSED_FILM_ID = 1;
    
    private HashMap<Integer, Screen> screens; 
    private HashMap<Integer, Film> films; 
    
    /**
     * Constructor for objects of class Cinema
     */
    public Cinema()
    {
        screens = new HashMap<Integer, Screen>();
        films = new HashMap<Integer, Film>();
        //projections = new HashMap<Integer, Projection>();
        //customers = new HashMap<Integer, Customer>();
        //bookings = new HashMap<Integer, Booking>();
        //payments = new HashMap<Integer, Payment>();
        //seat-assignments = new HashMap<Integer, SeatAssignment>();
        //reviews = new HashMap<Integer, Review>();
    }
    
    /**
     * Get next unused screen id
     *
     * @return int nextScreenId
     */
    public int getNextScreenId()
    {
        return NEXT_UNUSED_SCREEN_ID++;
    }
    
    /**
     * Add new screen
     *
     * @param  String title 
     * @return void
     */
    public void addScreen(String title)
    {
        Screen screen = new Screen(getNextScreenId(), title);
        this.screens.put(screen.getId(), screen);
    }
    
    /**
     * get List of all Screens
     *
     * @return List screens
     */
    public List<Screen> getScreenList()
    {
        return new ArrayList<Screen> (screens.values());
    }
    
    /**
     * Get next unused film id
     *
     * @return int nextFilmId
     */
    public int getNextFilmId()
    {
        return NEXT_UNUSED_FILM_ID++;
    }

    /**
     * Add new film
     *
     * @param  String title 
     * @param String director
     * @param String language
     * @return void
     */
    public void addFilm(String title, int year, String director, String language)
    {
        Film film = new Film(getNextFilmId(), title, year, director, language);
        this.films.put(film.getId(), film);
    }

    /**
     * Add new film
     *
     * @param  String title 
     * @param String director
     * @param String language
     * @param String subtitles
     * @return void
     */
    public void addFilm(String title, int year, String director, String language, String subtitles)
    {
        Film film = new Film(getNextFilmId(), title, year, director, language, subtitles);
        this.films.put(film.getId(), film);
    }
    
    /**
     * get List of all Films
     *
     * @return List films
     */
    public List<Film> getFilmList()
    {
        return new ArrayList<Film> (films.values());
    }
    
    /**
     * print all Films
     * @return void
     */
    public void printFilmList()
    {
        System.out.println("#############FILMS####################");
        Iterator it = films.values().iterator();
        while (it.hasNext())
        {
            Film film = (Film) (  it.next()  );
            System.out.println(film.toString());
        } 
        System.out.println("######################################"); 
        System.out.println();
    }
}
