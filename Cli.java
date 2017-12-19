import java.util.concurrent.TimeUnit;
import java.io.*;
import java.util.*;
/**
 * Write a description of class CommandLine here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cli implements Runnable
{
    Cinema cinema;
    
    /**
     * Constructor for objects of class CommandLine
     */
    public Cli()
    {
        this.cinema = new Cinema();
        cinema = new Cinema();
        
        // Set up the default data for the system
        setupSystem();
    }

    /**
     * Create the default data for the system
     *
     * @return void
     */
    @Override
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
        
        Calendar date1 = new GregorianCalendar(2016, 12, 01, 11, 00);
        Calendar date2 = new GregorianCalendar(2016, 12, 02, 11, 00);
        Calendar date3 = new GregorianCalendar(2016, 12, 03, 11, 00);
        
        this.cinema.addShow(date1, screen, film, (float)12.50, (float)15.00);
        this.cinema.addShow(date2, screen, film, (float)12.50, (float)15.00);
        this.cinema.addShow(date3, screen, film, (float)12.50, (float)15.00);
        
        this.cinema.addCustomer("John Malone"); 
        this.cinema.addCustomer("Sean Jones"); 
        this.cinema.addCustomer("Sarah O Hara"); 
        this.cinema.addCustomer("Clare Fisher"); 
        this.cinema.addCustomer("Tom Dickinson");
    }
    
    /**
     * Run the Application
     * @return void
     */
    @Override
    public void run()
    {
        this.runSplashScreenSequence();
        this.filmsIndex();
    }
    
    /**
     * displaySplashScreen()
     * @return void
     * 
     */
    public void runSplashScreenSequence()
    {
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("---------------ODEON - Fanatical About Films---------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        // wait 5 seconds
        try
        {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ex) {
            System.out.println("Splash Screen pause interrupted. " + ex.getMessage());
        }
        // clear screen
        this.clear();
    }
    
    /**
     * filmsIndex()
     * @return void
     * 
     */
    public void filmsIndex()
    {
        // HashMap to relate UserInput to Film
        Map <Integer, Film> optionToFilm = new HashMap<Integer, Film>();
        this.includeHeader();
        // Point to Films
        this.includePointer(1);
        this.includeSecondaryNavFilms();
        // Point to Index
        this.includePointer(1);
        this.includeTitle("films");
        
        int option = 20;
        System.out.println("");
        System.out.println("Select");

        for(Film film : this.cinema.getFilmList())
        {
            optionToFilm.put(option, film);
            System.out.print("[" + option + "]       ");
            System.out.println(film.toString());
            option++;
        }

        // Set max input as highest option number
        int input = this.getUserInputInteger(option);
        
        //Clear the screen
        this.clear();
            
        if(input<10)
        {
            // Primary Navigation
            this.primaryNav(input);
        }
        else if(input>=10&&input<20)
        {
            // Secondary Navigation
            this.secondaryNavFilms(input);
        }
        else
        {
            this.filmsShow(optionToFilm.get(input));
        }
        
    }
    
    /**
     * filmsShow()
     * @param Film film
     * @return void
     * 
     */
    public void filmsShow(Film film)
    {
        this.includeHeader();
        // Point to Films
        this.includePointer(1);
        this.includeSecondaryNavFilms();
        // Point to Index
        this.includePointer(1);
        this.includeTitle("films");
        this.includeTitle("SHOW FILM");
 
        System.out.println("Title:      " + film.getTitle());
        System.out.println("Year:       " + film.getYear());
        System.out.println("Director:   " + film.getDirector());
        System.out.println("Language:   " + film.getLanguage());
        System.out.println("Subtitles:  " + film.getSubtitles());

        // Set max input as highest option number
        int input = this.getUserInputInteger(19);
        
        //Clear the screen
        this.clear();
        
        if(input<10)
        {
            // Primary Navigation
            this.primaryNav(input);
        }
        else if(input>=10&&input<20)
        {
            // Secondary Navigation
            this.secondaryNavFilms(input);
        }
        else
        {
            // Tertiary Nav
            System.out.println("Tertiary Nav");
        }
        
    }
    
    /**
     * filmsCreate()
     * @return void
     * 
     */
    public void filmsCreate()
    {
        int input;
        
        this.includeHeader();
        // Point to Films
        this.includePointer(1);
        this.includeSecondaryNavFilms();
        // Point to Index
        this.includePointer(2);
        this.includeTitle("films");
        System.out.println("ADD FILM");  
        System.out.println("");
        System.out.println("[20, Create New Film]  [21, Cancel]");

        input = this.getUserInputInteger(21);

        //Clear the screen
        this.clear();
        
        if(input<10)
        {
            // Primary Navigation
            this.primaryNav(input);
        }
        
        else if(input>=10&&input<20)
        {
            // Secondary Navigation
            this.secondaryNavFilms(input);
        }
        
        else if(input == 20)
        {
            String title;
            int year;
            String director;
            String language;
            String subtitles;
            
            this.includeTitleBar();
            this.includeTitle("films");
            System.out.println("ADD FILM");  
            System.out.println("");
            
            System.out.println("Create New Film");
            title = this.getUserInputString(255, "Enter the title:");
            year = this.getUserInputInteger(2020, "Enter the year:");
            director = this.getUserInputString(20, "Enter the director:");
            language = this.getUserInputString(20, "Enter the language:");
            subtitles = this.getUserInputString(3, "Enter the subtitles (2-letter acronym or N/A):");
            
            System.out.println("Save Film? (Y/N):");
            if(this.getUserInputYN().equals("Y"))
            {
                this.cinema.addFilm(title, year, director, language, subtitles);
            };
            
            //Clear the screen
            this.clear();
            
            // redirect to filmsIndex
            this.filmsIndex();            
        }
        else
        {
            this.filmsIndex();
        }
        

    }
    
    /**
     * ShowsIndex()
     * @return void
     * 
     */
    public void showsIndex()
    {
        // HashMap to relate UserInput to Film
        Map <Integer, Show> optionToShow = new HashMap<Integer, Show>();
        this.includeHeader();
        // Point to Films
        this.includePointer(2);
        this.includeSecondaryNavShows();
        // Point to Index
        this.includePointer(1);
        this.includeTitle("shows");
        
        int option = 20;
        System.out.println("");
        System.out.println("Select");

        for(Show show : this.cinema.getShowList())
        {
            optionToShow.put(option, show);
            System.out.print("[" + option + "]       ");
            System.out.println(show.toString());
            option++;
        }

        // Set max input as highest option number
        int input = this.getUserInputInteger(option);
        
        //Clear the screen
        this.clear();
            
        if(input<10)
        {
            // Primary Navigation
            this.primaryNav(input);
        }
        else if(input>=10&&input<20)
        {
            // Secondary Navigation
            // this.secondaryNavFilms(input);
        }
        else
        {
            this.showsShow(optionToShow.get(input));
        }
        
    }
    
    /**
     * showsShow()
     * @param Show show
     * @return void
     * 
     */
    public void showsShow(Show show)
    {
        this.includeHeader();
        // Point to Films
        this.includePointer(1);
        this.includeSecondaryNavShows();
        // Point to Index
        this.includePointer(1);
        this.includeTitle("shows");
        System.out.println("DISPLAY SHOW");
        System.out.println(" ");
        
        System.out.println("***Film Details***");
        System.out.println("Film:       " + show.getFilm().getTitle());
        System.out.println("Year:       " + show.getFilm().getYear());
        System.out.println("Director:   " + show.getFilm().getDirector());
        System.out.println("Language:   " + show.getFilm().getLanguage());
        System.out.println("Subtitles:  " + show.getFilm().getSubtitles());
        System.out.println(" ");
        System.out.println("***Schedule Details***");
        System.out.println("Screen:     " + show.getScreen().getTitle());
        System.out.println("Date & Time:" + show.getDateTime());
        

        // Set max input as highest option number
        int input = this.getUserInputInteger(19);
        
        //Clear the screen
        this.clear();
        
        if(input<10)
        {
            // Primary Navigation
            this.primaryNav(input);
        }
        else if(input>=10&&input<20)
        {
            // Secondary Navigation
            //this.secondaryNavFilms(input);
        }
        else
        {
            // Tertiary Nav
            //System.out.println("Tertiary Nav");
        }
        
    }
    
    /**
     * acceptIntegerInput()
     * @param int maxValue
     * @return int input
     */
    public int getUserInputInteger(int maxValue)
    {
        Scanner scanner = new Scanner(System.in);
        int selection = 0;
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Please make your selection:");
            if(scanner.hasNextInt())
            {
                // Input is an integer
                
                // Update the choice
                selection = scanner.nextInt();

                // Validate the input in range
                if(selection==0||selection>maxValue)
                {
                    System.out.println("The number you entered is not in range 1 to " + maxValue);
                }
                else
                {
                    // input is valid 
                    return selection;
                }            
            
            }
            else
            {
                // Input is not an integer
                
                // Remove input from scanner and display notification
                scanner.next();
                System.out.println("The input was not a number");
            }
            

        }    
    }

        
    /**
     * acceptIntegerInput()
     * @param int maxValue
     * @param String question
     * @return int input
     */
    public int getUserInputInteger(int maxValue, String question)
    {
        Scanner scanner = new Scanner(System.in);
        int selection = 0;
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println(question + ":");
            if(scanner.hasNextInt())
            {
                // Input is an integer
                
                // Update the choice
                selection = scanner.nextInt();

                // Validate the input in range
                if(selection==0||selection>maxValue)
                {
                    System.out.println("The number you entered is not in range 1 to " + maxValue);
                }
                else
                {
                    // input is valid 
                    return selection;
                }            
            
            }
            else
            {
                // Input is not an integer
                
                // Remove input from scanner and display notification
                scanner.next();
                System.out.println("The input was not a number");
            }
            

        }    
    }
    
    
    /**
     * getUserInputString()
     * @param int maxLength
     * @return String input
     */
    public String getUserInputString(int maxLength)
    {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Please make your selection:");
            if(scanner.hasNextLine())
            {
                // Input is an integer
                
                // Update the choice
                input = scanner.nextLine();

                // Validate the input in range
                if(input=="")
                {
                    System.out.println("Input cannot be blank");
                }
                else if(input.length()>maxLength)
                {
                    System.out.println("Input too long. Input must be less than " + maxLength + " characters.");
                }
                else
                {
                    // input is valid 
                    return input;
                }            
            
            }
            else
            {
                // Input is not a string
                
                // Remove input from scanner and display notification
                scanner.next();
                System.out.println("The input is not valid");
            }
            

        }     
    }
    
    /**
     * getUserInputString()
     * @param int maxLength
     * @param String question
     * @return String input
     */
    public String getUserInputString(int maxLength, String question)
    {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println(question + ":");
            if(scanner.hasNextLine())
            {
                // Input is an integer
                
                // Update the choice
                input = scanner.nextLine();

                // Validate the input in range
                if(input=="")
                {
                    System.out.println("Input cannot be blank");
                }
                else if(input.length()>maxLength)
                {
                    System.out.println("Input too long. Input must be less than " + maxLength + " characters.");
                }
                else
                {
                    // input is valid 
                    return input;
                }            
            
            }
            else
            {
                // Input is not a string
                
                // Remove input from scanner and display notification
                scanner.next();
                System.out.println("The input is not valid");
            }
            

        }    
    }   
    
    /**
     * getUserInputYN()
     * @param String yn
     * @return String input
     */
    public String getUserInputYN()
    {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Enter Y or N:");
            if(scanner.hasNextLine())
            {
                // Input is an integer
                
                // Update the choice
                input = scanner.nextLine();

                // Validate the input in range
                if((input.equals("Y"))||(input.equals("y")))
                {
                    return "Y";
                }
                else if((input.equals("N"))||(input.equals("n")))
                {
                    return "N";
                }
                else
                {
                    System.out.println("Input must be either Y or N");
                }            
            
            }
            else
            {
                // Input is not a string
                
                // Remove input from scanner and display notification
                scanner.next();
                System.out.println("The input is not valid");
            }
            

        }     
    }
    
    
    /**
     * includeTitleBar()
     * @return void
     * 
     */
    public void includeTitleBar()
    {
        System.out.println("==========================ODEON CINEMA SYSTEM=========================");
    }
    
    
    /**
     * includeHeader()
     * @return void
     * 
     */
    public void includeHeader()
    {
        System.out.println("==========================ODEON CINEMA SYSTEM=========================");
        System.out.println("[1, Films]  [2, Shows]  [3, Cstmr]  [4, Bkngs]  [5, Reprt]  [6, Quit ]");
    }
    
    /**
     * includeTitle()
     * print an ASCII art header
     * @param String title
     * @return void
     */
    public void includeTitle(String title)
    {
        // Margin Top
        System.out.println("");
        
        switch(title)
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
               
        }
        
        
        
        
        // Margin bottom
        System.out.println("");
            
    }
    
    /**
     * includeSecondaryNavFilms()
     * @return void
     * 
     */
    public void includeSecondaryNavFilms()
    {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("[10, Indx]  [11,  Add]");
    }
    
    /**
     * includeSecondaryNavShows()
     * @return void
     * 
     */
    public void includeSecondaryNavShows()
    {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("[10, Indx]  [11,  Add]");
    }
    
    
    /**
     * includePointer()
     * @param int position
     * @return void
     * 
     */
    public void includePointer(int position)
    {
        // Print white space
        for(int i=0;i<position-1;i++)
        {
            System.out.print("            ");
        }
        
        //Print pointer
        System.out.println("    ^^^^");
    }
    
    
    /**
     * primaryNav()
     * @param int input
     * @return void
     * 
     */
    public void primaryNav(int input)
    {
        switch(input)
        {
            case 1:
                this.filmsIndex();
                break;
            case 2:
                this.showsIndex();
                break;
            case 3:
                System.out.println("Customers Index");
                break;
            case 4:
                System.out.println("Bookings Index");
                break;
            case 5:
                System.out.println("Reports Index");
                break;
            case 6:
                System.out.println("Application Quit");
                break;
        }
    }
    
    
    /**
     * secondaryNavFilms()
     * @param int input
     * @return void
     * 
     */
    public void secondaryNavFilms(int input)
    {
        switch(input)
        {
            case 10:
                this.filmsIndex();
                break;
            case 11:
                this.filmsCreate();
                break;
        }
    }
    
    
    /**
     * secondaryNavShows()
     * @param int input
     * @return void
     * 
     */
    public void secondaryNavShows(int input)
    {
        switch(input)
        {
            case 10:
                this.showsIndex();
                break;
            case 11:
                System.out.println("Add new Show");
                break;
        }
    }
    
    /**
     * Clear the console screen 
     * Author: Dyndrilliac
     * URL: https://stackoverflow.com/questions/2979383/java-clear-the-console
     * Accessed 18-DEC-2017
     * @return void
     */
    public void clear() 
    {   
        try
        {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        catch (final Exception e)
        {
            System.out.println("Error encountered when clearing the screen. " + e.getMessage());
        }  
   }  
}
