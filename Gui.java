
/**
 * Gui interface for the application
 *
 * @author Katherine Wyers
 * @version 1.0
 */
public class Gui implements Runnable
{
    private Cinema cinema;
    /**
     * Constructor for objects of class Gui
     */
    public Gui()
    {
        cinema = new Cinema();
        
        // Set up the default data for the system
        setupSystem();
        
        // Set up the frame
        //setupFrame();
    }

    /**
     * Create the default data for the system
     *
     * @return void
     */
    @Override
    public void setupSystem()
    {
        cinema.addScreen("Screen 1");
        cinema.addScreen("Screen 2");        
        
        cinema.addFilm("Psycho", 1960, "Alfred Hitchcock", "EN");
        cinema.addFilm("2001: A Space Odyssey", 1968, "Stanley Kubrick", "EN", "NL");
        cinema.addFilm("Jurassic Park", 1993, "Steven Spielberg", "EN", "NL");
        cinema.addFilm("Taxi Driver", 1976, "Martin Scorcese", "NL");
        cinema.addFilm("Citizen Kane", 1941, "Orson Welles", "EN");
    }
    
    /**
     * Run the Application
     * @return void
     */
    @Override
    public void run()
    {
        System.out.println("Running the application from Gui...");
        System.out.println("Application-run complete");
    }
}
