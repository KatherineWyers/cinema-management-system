
/**
 * Write a description of class CliNavMaker here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CliNavMaker
{
    /**
     * Constructor for objects of class CliNavGenerator
     */
    public CliNavMaker()
    {}
    
  
    /**
     * 
     * pageHeader
     * @param int primaryPointer
     * @param int secondaryPointer
     * @param String banner
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
     * @return void
     * 
     */
    public static void includeFullHeader()
    {
        System.out.println("==========================ODEON CINEMA SYSTEM=========================");
        System.out.println("[1, Films]  [2, Shows]  [3, Cstmr]  [4, Bkngs]  [5, Reprt]  [6, Quit ]");
    }
    
    /**
     * includeTitleBar()
     * @return void
     * 
     */
    public static void includeTitleBar()
    {
        System.out.println("==========================ODEON CINEMA SYSTEM=========================");
    }
    
    /**
     * includeBanner()
     * @param String category
     * @param String subtitle
     * @return void
     */
    public static void includeBanner(String category, String subtext)
    {
        CliNavMaker.includeBannerMainText(category);
        CliNavMaker.includeBannerSubtext(subtext);
    }
    
    /**
     * includeBannerMainText()
     * print an ASCII art header
     * @param String category
     * @return void
     */
    public static void includeBannerMainText(String category)
    {
        System.out.println("");// Margin Top
        switch(category)
        {
            // Text To ASCII Art Generator
            // http://patorjk.com/software/taag/
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
     * Display the subtext
     * @param String subtext
     * @return void
     */
    public static void includeBannerSubtext(String subtext)
    {
        System.out.println(subtext);
    }

    /**
     * includeSecondaryNav()
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
     * @param int position
     * @return void
     * 
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
