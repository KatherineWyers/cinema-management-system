import java.util.concurrent.TimeUnit;
import java.io.*;
import java.util.*;
/**
 * Write a description of interface UserInterface here.
 *
 * @author Katherine Wyers
 * @version 21DEC2017
 */
abstract public class UserInterface
{
    protected Cinema cinema;
    public UserInterface()
    {
        this.cinema = new Cinema();
        cinema = new Cinema();
        
        // Set up the default data for the system
        setupSystem();        
    }
    
    abstract void run();
    
    /**
     * addDelayInSeconds
     * Add a delay to the thread
     * @int delay
     * @return void
     */
    public void addDelayInSeconds(int delaySeconds)
    {
        // wait 5 seconds
        try
        {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ex) {
            System.out.println("Pause interrupted. " + ex.getMessage());
        }        
    }

    /**
     * Create the default data for the system
     *
     * @return void
     */
    public void setupSystem()
    {
        this.cinema.addScreen("Screen 1");
        this.cinema.addScreen("Screen 2");        
        
        this.cinema.addFilm("Psycho", 1960, "Alfred Hitchcock", "EN");
        this.cinema.addFilm("2001: A Space Odyssey", 1968, "Stanley Kubrick", "EN", "NL");
        this.cinema.addFilm("Jurassic Park", 1993, "Steven Spielberg", "EN", "NL");
        this.cinema.addFilm("Taxi Driver", 1976, "Martin Scorcese", "NL");
        this.cinema.addFilm("Citizen Kane", 1941, "Orson Welles", "EN");
        
        Screen screen = this.cinema.getScreenList().get(0);
        Film film = this.cinema.getFilmList().get(0);
        
        Calendar date1 = new GregorianCalendar(2017, 0, 01, 11, 00);
        Calendar date2 = new GregorianCalendar(2017, 0, 02, 11, 00);
        Calendar date3 = new GregorianCalendar(2017, 0, 03, 11, 00);
        
        this.cinema.addShow(date1, screen, film, (float)12.50, (float)15.00);
        this.cinema.addShow(date2, screen, film, (float)12.50, (float)15.00);
        this.cinema.addShow(date3, screen, film, (float)12.50, (float)15.00);
        
        this.cinema.addCustomer("John Malone"); 
        this.cinema.addCustomer("Sean Jones"); 
        this.cinema.addCustomer("Sarah O Hara"); 
        this.cinema.addCustomer("Clare Fisher"); 
        this.cinema.addCustomer("Tom Dickinson");
    }}
