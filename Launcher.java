
/**
 * Launch the application.
 *
 * @author Katherine Wyers
 * @version 1.0
 */
public class Launcher
{
    public static void main(String[] args)
    {
        /**
         * The default user interface is the Java-swing GUI. 
         * 
         * To launch the application using the command-line interface, comment out 
         * line 18, uncomment line 19 and compile. 
         */
        Gui app = new Gui();
        //Cli app = new Cli();
        app.run();
    }
}
