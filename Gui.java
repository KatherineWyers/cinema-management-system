
/**
 * Gui interface for the application
 *
 * @author Katherine Wyers
 * @version 1.0
 */
public class Gui extends UserInterface
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
