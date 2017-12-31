
/**
 * CliNavMaker Class
 * 
 * This class creates the navigation objects 
 * for the Command-Line Interface. 
 * It is used by the Cli class
 *
 * @author Katherine Wyeres
 * @version DEC-2017
 */
public class CliNavMaker
{
    /**
     * Constructor for objects of class CliNavGenerator
     */
    public CliNavMaker()
    {}
    
  
    /**
     * pageHeader
     * 
     * Select the navigation elements to be included in the menu
     * 
     * @param boolean includeFullHeader// if false, only display minimal navigation options
     * @param int primaryPointer// position of the pointer in the primary navigation panel
     * @param int secondaryPointer// position of the pointer in the secondary navigation panel
     * @param String category// title text
     * @param String subtext
     * @return void
     */
    public static void pageHeader(boolean includeFullHeader, int primaryPointer, int secondaryPointer, String category, String subtext)
    {
        if(includeFullHeader)
        {
            CliNavMaker.includeFullHeader();
            CliNavMaker.includePointer(primaryPointer);
            CliNavMaker.includeSecondaryNav(category);
            CliNavMaker.includePointer(secondaryPointer);
        }
        else
        {
            CliNavMaker.includeTitleBar();
        }

        CliNavMaker.includeBanner(category, subtext);   
    }
    
    
    /**
     * includeFullHeader()
     * 
     * Print the full primary navigation menu
     * 
     * @return void
     */
    public static void includeFullHeader()
    {
        System.out.println("==========================ODEON CINEMA SYSTEM=========================");
        System.out.println("[1, Films]  [2, Shows]  [3, Cstmr]  [4, Bkngs]  [5, Reprt]  [6, Quit ]");
    }
    
    /**
     * includeTitleBar()
     * 
     * Print the title bar for the application
     * 
     * @return void
     */
    public static void includeTitleBar()
    {
        System.out.println("==========================ODEON CINEMA SYSTEM=========================");
    }
    
    /**
     * includeBanner()
     * 
     * Include the main title text and subtext elements
     * 
     * @param String category
     * @param String subtext
     * @return void
     */
    public static void includeBanner(String category, String subtext)
    {
        CliNavMaker.includeBannerMainText(category);
        CliNavMaker.includeBannerSubtext(subtext);
    }
    
    /**
     * includeBannerMainText()
     * 
     * print an ASCII art header
     * ASCII generated using: Text To ASCII Art Generator
     * http://patorjk.com/software/taag/
     * 
     * @param String category
     * @return void
     */
    public static void includeBannerMainText(String category)
    {
        System.out.println("");// Margin Top
        switch(category)
        {

            case "films":
                System.out.println("______ _____ _     ___  ___ _____ ");
                System.out.println("|  ___|_   _| |    |  \\/  |/  ___|");
                System.out.println("| |_    | | | |    | .  . |\\ `--. ");
                System.out.println("|  _|   | | | |    | |\\/| | `--. \\");
                System.out.println("| |    _| |_| |____| |  | |/\\__/ /");
                System.out.println("\\_|    \\___/\\_____/\\_|  |_/\\____/ ");
                break;
            case "shows":
                System.out.println(" _____ _   _ _____  _    _ _____");
                System.out.println("/  ___| | | |  _  || |  | /  ___|");
                System.out.println("\\ `--.| |_| | | | || |  | \\ `--. ");
                System.out.println(" `--. \\  _  | | | || |/\\| |`--. \\");
                System.out.println("/\\__/ / | | \\ \\_/ /\\  /\\  /\\__/ /");
                System.out.println("\\____/\\_| |_/\\___/  \\/  \\/\\____/");
                break;
            case "customers":
                System.out.println(" _____ _   _ _____ _____ ________  ___ ___________  _____"); 
                System.out.println("/  __ \\ | | /  ___|_   _|  _  |  \\/  ||  ___| ___ \\/  ___|");
                System.out.println("| /  \\/ | | \\ `--.  | | | | | | .  . || |__ | |_/ /\\ `--. ");
                System.out.println("| |   | | | |`--. \\ | | | | | | |\\/| ||  __||    /  `--. \\");
                System.out.println("| \\__/\\ |_| /\\__/ / | | \\ \\_/ / |  | || |___| |\\ \\ /\\__/ /");
                System.out.println(" \\____/\\___/\\____/  \\_/  \\___/\\_|  |_/\\____/\\_| \\_|\\____/");
                break;
            case "bookings":
                System.out.println("______  _____  _____ _   _______ _   _ _____  _____ "); 
                System.out.println("| ___ \\|  _  ||  _  | | / /_   _| \\ | |  __ \\/  ___|");
                System.out.println("| |_/ /| | | || | | | |/ /  | | |  \\| | |  \\/\\ `--. ");
                System.out.println("| ___ \\| | | || | | |    \\  | | | . ` | | __  `--. \\");
                System.out.println("| |_/ /\\ \\_/ /\\ \\_/ / |\\  \\_| |_| |\\  | |_\\ \\/\\__/ /");
                System.out.println("\\____/  \\___/  \\___/\\_| \\_/\\___/\\_| \\_/\\____/\\____/ ");
                break;
            case "reports":
                System.out.println("______ ___________ ___________ _____ _____"); 
                System.out.println("| ___ \\  ___| ___ \\  _  | ___ \\_   _/  ___|");
                System.out.println("| |_/ / |__ | |_/ / | | | |_/ / | | \\ `--. ");
                System.out.println("|    /|  __||  __/| | | |    /  | |  `--. \\\\");
                System.out.println("| |\\ \\| |___| |   \\ \\_/ / |\\ \\  | | /\\__/ /");
                System.out.println("\\_| \\_\\____/\\_|    \\___/\\_| \\_| \\_/ \\____/");
                break;
        }        
        System.out.println("");// Margin bottom   
    }
    
    /**
     * includeBannerSubtext
     * 
     * Print the subtext
     * 
     * @param String subtext
     * @return void
     */
    public static void includeBannerSubtext(String subtext)
    {
        System.out.println(subtext);
    }

    /**
     * includeSecondaryNav()
     * 
     * Print the secondary navigation 
     * for the given category
     * 
     * @param String category
     * @return void
     */
    public static void includeSecondaryNav(String category)
    {
        System.out.println("----------------------------------------------------------------------");
        switch(category)
        {
            case "films":
                System.out.println("[1,  Indx]  [10,  Add]");
                break;
            case "shows":
                System.out.println("[2,  Indx]  [11,  Add]");
                break;
            case "customers":
                System.out.println("[3,  Indx]");
                break;
            case "bookings":
                System.out.println("[4,  Indx]  [13,  Add]  [14, Move]  [15, Revw]");
                break;
            case "reports":
                System.out.println("[5,  Indx]  [16, Tkts]  [17, Incm]");
                break;
        }
    }
    
    /**
     * includePointer()
     * 
     * Print the pointer in the give position
     * 
     * @param int position
     * @return void
     */
    public static void includePointer(int position)
    {
        for(int i=0;i<position-1;i++)
        {
            System.out.print("            ");// Print white space
        }
        System.out.println("    ^^^^");//Print pointer
    }
}
