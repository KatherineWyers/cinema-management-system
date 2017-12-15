
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
        
        Screen screen = this.cinema.getScreenList().get(0);
        Film film = this.cinema.getFilmList().get(0);
        this.cinema.addProjection("01/12/2016", "Afternoon", screen, film, (float)12.50, (float)15.00);
        this.cinema.addProjection("02/12/2016", "Afternoon", screen, film, (float)12.50, (float)15.00);
        this.cinema.addProjection("03/12/2016", "Afternoon", screen, film, (float)12.50, (float)15.00);
        
        this.cinema.addCustomer("John Malone"); 
        this.cinema.addCustomer("Sean Jones"); 
        this.cinema.addCustomer("Sarah O Hara"); 
        this.cinema.addCustomer("Clare Fisher"); 
        this.cinema.addCustomer("Tom Dickinson");
        
        
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
        //testPrintSeatingGrid();
        testIsValidSeatReservation();
        testAddTemporarySeatReservation();
        testFinalizeCashBooking();
        testFinalizeCardBooking();
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
        this.cinema.addProjection("20/12/2017", "Afternoon", screen1, film1, (float)12.50, (float)15.00);
        
        // All four arguments clash
        compare(this.cinema.isValidProjection("20/12/2017", "Afternoon", screen1, film1),false); 
        // No clashes because date is different
        compare(this.cinema.isValidProjection("21/12/2017", "Afternoon", screen1, film1),true);
        // No clashes becase slot is different
        compare(this.cinema.isValidProjection("20/12/2017", "Night1", screen1, film1),true);
        // Film clashes
        compare(this.cinema.isValidProjection("20/12/2017", "Afternoon", screen2, film1),false);
        // Screen clashes
        compare(this.cinema.isValidProjection("20/12/2017", "Afternoon", screen1, film2),false);        
   
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
        
        this.cinema.addProjection("20/12/2017", "Afternoon", screen, film, (float)12.50, (float)15.00); 
        compare(this.cinema.getProjectionList().size(),count+1);
        this.cinema.addProjection("20/12/2017", "Night1", screen, film, (float)12.50, (float)15.00); 
        compare(this.cinema.getProjectionList().size(),count+2);
        this.cinema.addProjection("21/12/2017", "Night1", screen, film, (float)12.50, (float)15.00); 
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
     * testPrintSeatingGrid()
     * @return void
     */
    private void testPrintSeatingGrid()
    {
        setupTestEnvironment();
        
        //run tests
        System.out.println("printSeatingGrid");
        
        Projection projection = this.cinema.getProjectionList().get(0);
        this.cinema.printSeatingGrid(this.cinema.getSeatingGrid(projection)); 

        System.out.println("");
    }
    
    /**
     * testIsValidSeatReservation()
     * @return void
     */
    private void testIsValidSeatReservation()
    {
        setupTestEnvironment();
        
        // run tests
        System.out.println("isValidSeatReservation");
        
        Projection projection = this.cinema.getProjectionList().get(0);        
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = new Booker(this.cinema, customer);
      
        compare(booker.isValidSeatReservation(projection, 5, 2),true); 
        booker.addSeatReservation(projection, 5, 2); 
        compare(booker.isValidSeatReservation(projection, 5, 2),false);
        
        System.out.println("");
    }
    
    /**
     * testAddTemporarySeatReservation()
     * @return void
     */
    private void testAddTemporarySeatReservation()
    {
        setupTestEnvironment();
        
        // run tests
        System.out.println("addSeatReservation");
        
        Projection projection = this.cinema.getProjectionList().get(0);        
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = new Booker(this.cinema, customer);
    
        int count = booker.getSeatReservations().size();
        booker.addSeatReservation(projection, 1, 2); 
        compare(booker.getSeatReservations().size(),count+1);
        booker.addSeatReservation(projection, 2, 2); 
        compare(booker.getSeatReservations().size(),count+2);
        
        System.out.println("");
    }
    
    /**
     * testFinalizeCashBooking()
     * @return void
     */
    private void testFinalizeCashBooking()
    {
        setupTestEnvironment();
        
        // run tests
        System.out.println("finalizeBooking");
        
        Projection projection = this.cinema.getProjectionList().get(0);
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = new Booker(this.cinema, customer);
        
        booker.addSeatReservation(projection, 1, 2); 
        booker.addSeatReservation(projection, 2, 2); 
        
        int reservationCount = booker.getSeatReservations().size(); 
        int ticketCountPre = this.cinema.getTickets(projection).size();    
        
        booker.finalizeCashBooking();
        int ticketCountPost = this.cinema.getTickets(projection).size();   
        compare((ticketCountPost == (reservationCount + ticketCountPre)), true);
        
        System.out.println("");
    }
    
    /**
     * testFinalizeCardBooking()
     * @return void
     */
    private void testFinalizeCardBooking()
    {
        setupTestEnvironment();
        
        // run tests
        System.out.println("finalizeBooking");
        
        Projection projection = this.cinema.getProjectionList().get(0);
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = new Booker(this.cinema, customer);
        
        booker.addSeatReservation(projection, 1, 2); 
        booker.addSeatReservation(projection, 2, 2); 
        
        int reservationCount = booker.getSeatReservations().size(); 
        int ticketCountPre = this.cinema.getTickets(projection).size();    
        
        booker.finalizeCardBooking("REF:12345657");
        
        int ticketCountPost = this.cinema.getTickets(projection).size();   
        
        compare((ticketCountPost == (reservationCount + ticketCountPre)), true);
        
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
