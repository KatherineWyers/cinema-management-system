
/**
 * Write a description of class Tester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tester
{
    Cinema cinema;
    
    /**
     * Constructor for Tester
     */
    public Tester(){};
    
    public void testAll()
    {
        testCinema();
    }

    /**
     * Create test data for each test
     * @return void
     */
    private void setupTestEnvironment()
    {
        //reset test data in cinema object
        this.cinema = new Cinema();
        
        this.cinema.addScreen("Screen 1");
        this.cinema.addScreen("Screen 2");        
        
        this.cinema.addFilm("Psycho", 1960, "Alfred Hitchcock", "EN");
        this.cinema.addFilm("2001: A Space Odyssey", 1968, "Stanley Kubrick", "EN", "NL");
        this.cinema.addFilm("Jurassic Park", 1993, "Steven Spielberg", "EN", "NL");
        this.cinema.addFilm("Taxi Driver", 1976, "Martin Scorcese", "NL");
        this.cinema.addFilm("Citizen Kane", 1941, "Orson Welles", "EN");   
    }
    
    /**
     * testCinemaMethods()
     * @return void
     */
    public void testCinema()
    {
        System.out.println("Testing Cinema Methods");
        testAddScreen();
        testAddFilm();
        testIsValidProjection();
        testAddProjection();
        testAddCustomer();
        System.out.print("");
    }

    /**
     * testAddScreen()
     * @return void
     */
    private void testAddScreen()
    {
        setupTestEnvironment();
        
        // run tests
        System.out.println("addScreen");
        
        int count = this.cinema.getScreenList().size();
        this.cinema.addScreen("Screen 3"); 
        compare(this.cinema.getScreenList().size(),count+1);
        this.cinema.addScreen("Screen 4"); 
        compare(this.cinema.getScreenList().size(),count+2);
        
        System.out.println("");
    }

    /**
     * testAddFilm()
     * @return void
     */
    private void testAddFilm()
    {
        setupTestEnvironment();
        
        // run tests
        System.out.println("addFilm");
        
        int count = this.cinema.getFilmList().size();
        this.cinema.addFilm("Random Movie 1", 1941, "Orson Welles", "EN"); 
        compare(this.cinema.getFilmList().size(),count+1);
        this.cinema.addFilm("Random Movie 2", 1941, "Orson Welles", "EN"); 
        compare(this.cinema.getFilmList().size(),count+2);
        
        System.out.println("");
    }
    
    /**
     * testIsValidProjection()
     * test whether both the screen and film are available before creating the projection
     * @return void
     */
    private void testIsValidProjection()
    {
        setupTestEnvironment();
       
        // run tests
        System.out.println("isValidProjection");
        
        Screen screen1 = this.cinema.getScreenList().get(0);
        Screen screen2 = this.cinema.getScreenList().get(1);
        Film film1 = this.cinema.getFilmList().get(0);
        Film film2 = this.cinema.getFilmList().get(1);
        this.cinema.addProjection("20/12/2017", "Afternoon", screen1.getId(), film1.getId(), (float)12.50, (float)15.00);
        
        // All four arguments clash
        compare(this.cinema.isValidProjection("20/12/2017", "Afternoon", screen1.getId(), film1.getId()),false); 
        // No clashes because date is different
        compare(this.cinema.isValidProjection("21/12/2017", "Afternoon", screen1.getId(), film1.getId()),true);
        // No clashes becase slot is different
        compare(this.cinema.isValidProjection("20/12/2017", "Night1", screen1.getId(), film1.getId()),true);
        // Film clashes
        compare(this.cinema.isValidProjection("20/12/2017", "Afternoon", screen2.getId(), film1.getId()),false);
        // Screen clashes
        compare(this.cinema.isValidProjection("20/12/2017", "Afternoon", screen1.getId(), film2.getId()),false);        
   
        System.out.println("");
    }

    /**
     * testAddProjection()
     * @return void
     */
    private void testAddProjection()
    {
        setupTestEnvironment();
        
        // run tests
        System.out.println("addProjection");
        
        Screen screen = this.cinema.getScreenList().get(0);
        Film film = this.cinema.getFilmList().get(0);
        int count = this.cinema.getProjectionList().size();
        
        this.cinema.addProjection("20/12/2017", "Afternoon", screen.getId(), film.getId(), (float)12.50, (float)15.00); 
        compare(this.cinema.getProjectionList().size(),count+1);
        this.cinema.addProjection("20/12/2017", "Night1", screen.getId(), film.getId(), (float)12.50, (float)15.00); 
        compare(this.cinema.getProjectionList().size(),count+2);
        this.cinema.addProjection("21/12/2017", "Night1", screen.getId(), film.getId(), (float)12.50, (float)15.00); 
        compare(this.cinema.getProjectionList().size(),count+3);
        
        System.out.println("");
    }

    /**
     * testAddCustomer()
     * @return void
     */
    private void testAddCustomer()
    {
        setupTestEnvironment();
        
        // run tests
        System.out.println("addCustomer");
        
        int count = this.cinema.getCustomerList().size();
        this.cinema.addCustomer(); 
        compare(this.cinema.getCustomerList().size(),count+1);
        this.cinema.addCustomer("John Malone"); 
        compare(this.cinema.getCustomerList().size(),count+2);
        
        System.out.println("");
    }
    
    /**
     * compare two boolean values and call printResult
     * @param boolean result
     * @param boolean expected
     * @return void
     */
    private void compare(boolean result, boolean expected)
    {
        printResult(result==expected);
    }
    
    /**
     * compare two int values and call printResult
     * @param int result
     * @param int expected
     * @return void
     */
    private void compare(int result, int expected)
    {
        printResult(result==expected);
    }
    
    /**
     * compare two Strings and call printResult
     * @param String result
     * @param String expected
     * @return void
     */
    private void compare(String result, String expected)
    {
        printResult(result.equals(expected));
    }
    
    /**
     * printResult. Print P if argument true, else print F
     * @param boolean result
     * @return void
     */
    private void printResult(boolean result)
    {
        if(result)
        {
            System.out.print("P");
        } else {
            System.out.print("F");
        }
    }
    
}
