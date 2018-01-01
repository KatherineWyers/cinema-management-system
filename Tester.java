import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.List;

/**
 * Tester Class
 *
 * @author Katherine Wyers
 * @version DEC-2017
 */
public class Tester
{
    private Cinema cinema;
    
    /**
     * Constructor for Tester
     */
    public Tester(){};
    
    /**
     * runAllTests
     * 
     * Run the three groups of tests
     */
    public void runAllTests()
    {
        runUnitTests();
        runSystemTests();
        runIntegrationTests();
    }

    /**
     * setupTestEnvironment
     * 
     * Create test data for each test
     * 
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
     * runUnitTests
     * 
     * Run the unit tests
     * 
     * @return void
     */
    private void runUnitTests()
    {
        System.out.println("**************************");
        System.out.println("*** Running Unit Tests ***");
        System.out.println("**************************");
        
        System.out.println("Testing Cinema Methods");
        testGetNextTicketId();
        testGetNextBookingId();
        testGetNextPaymentId();
        testGetNextCustomerId();
        testGetNextScreenId();
        testGetNextFilmId();
        testGetNextShowId();
        testGetNewBooker();
        testGetNewTransferer();
        testAddScreen();
        testAddShow();
        testAddCustomer();
        testAddFilm();
        testGetNewCustomer();
        testCinemaConvertToRowLetter();
        testCinemaConvertToRowNum();
        testBookerAddTemporarySeatReservation();
        testIsExistReservation();
        testGetTotalPrice();
        testFormatToTwoDigitString();
        testSeatAllocationConvertToRowLetter();
        testAddIncome();
        testIncrementTicketCounter();
        testIncrementRatingCounter();
        testGetAverageRating();
    }


    /**
     * testGetNextTicketId()
     * 
     * Test that each value returned by 
     * getNextTicketId is incremented by 
     * one
     * 
     * @return void
     */
    private void testGetNextTicketId()
    {
        System.out.println("getNextTicketId");
        // setup
        setupTestEnvironment();
        
        // run
        int ticketId = this.cinema.getNextTicketId();
        compare(this.cinema.getNextTicketId(),ticketId+1);
        compare(this.cinema.getNextTicketId(),ticketId+2);
        System.out.println("");
    }

    /**
     * testGetNextBookingId()
     * 
     * Test that each value returned by 
     * getNextBookingId is incremented by 
     * one
     * 
     * @return void
     */
    private void testGetNextBookingId()
    {
        setupTestEnvironment();
        
        // run tests
        System.out.println("getNextBookingId");
        
        int bookingId = this.cinema.getNextBookingId();
        compare(this.cinema.getNextBookingId(),bookingId+1);
        compare(this.cinema.getNextBookingId(),bookingId+2);
        
        System.out.println("");
    }


    /**
     * testGetNextPaymentId()
     * 
     * Test that each value returned by 
     * getNextPaymentId is incremented by 
     * one
     * 
     * @return void
     */
    private void testGetNextPaymentId()
    {
        System.out.println("getNextPaymentId");
        // setup
        setupTestEnvironment();
        
        // run
        int paymentId = this.cinema.getNextPaymentId();
        compare(this.cinema.getNextPaymentId(),paymentId+1);
        compare(this.cinema.getNextPaymentId(),paymentId+2);
        System.out.println("");
    }

    /**
     * testGetNextCustomerId()
     * 
     * Test that each value returned by 
     * getNextCustomerId is incremented by 
     * one
     * 
     * @return void
     */
    private void testGetNextCustomerId()
    {
        System.out.println("getNextCustomerId");
        // setup
        setupTestEnvironment();
        
        // run
        int customerId = this.cinema.getNextCustomerId();
        compare(this.cinema.getNextCustomerId(),customerId+1);
        compare(this.cinema.getNextCustomerId(),customerId+2);
        System.out.println("");
    }


    /**
     * testGetNextScreenId()
     * 
     * Test that each value returned by 
     * getNextScreenId is incremented by 
     * one
     * 
     * @return void
     */
    private void testGetNextScreenId()
    {
        System.out.println("getNextScreenId");
        // setup
        setupTestEnvironment();
        
        // run
        int screenId = this.cinema.getNextScreenId();
        compare(this.cinema.getNextScreenId(),screenId+1);
        compare(this.cinema.getNextScreenId(),screenId+2);
        System.out.println("");
    }


    /**
     * testGetNextFilmId()
     * 
     * Test that each value returned by 
     * getNextFilmId is incremented by 
     * one
     * 
     * @return void
     */
    private void testGetNextFilmId()
    {
        System.out.println("getNextFilmId");
        // setup
        setupTestEnvironment();
        
        // run        
        int filmId = this.cinema.getNextFilmId();
        compare(this.cinema.getNextFilmId(),filmId+1);
        compare(this.cinema.getNextFilmId(),filmId+2);
        System.out.println("");
    }
    
    /**
     * testGetNextShowId()
     * 
     * Test that each value returned by 
     * getNextShowId is incremented by 
     * one
     * 
     * @return void
     */
    private void testGetNextShowId()
    {
        // setup
        System.out.println("getNextShowId");
        setupTestEnvironment();
        
        // run
        int showId = this.cinema.getNextShowId();
        compare(this.cinema.getNextShowId(),showId+1);
        compare(this.cinema.getNextShowId(),showId+2);
        System.out.println("");
    }
    

    /**
     * testGetNewBooker()
     * 
     * Test that the cinema getNewBooker 
     * removes the old booker and creates 
     * a new booker object
     * 
     * @return void
     */
    private void testGetNewBooker()
    { 
        System.out.println("getNewBooker");
        setupTestEnvironment();
        Show show = this.cinema.getShowList().get(0);
        Customer customer = this.cinema.getCustomerList().get(0); 
        Booker booker1 = this.cinema.getNewBooker(show, customer);
        Booker booker2 = this.cinema.getNewBooker(show, customer);
        compare(booker1==booker2, false);
        System.out.println("");
    }

    /**
     * testGetNewTransferer()
     * 
     * Test that the cinema getNewTransferer method
     * abandons the old transferer and creates 
     * a new transferer object
     * 
     * @return void
     */
    private void testGetNewTransferer()
    {
        System.out.println("getNewTransferer");
        // setup 
        setupTestEnvironment();
        Show show = this.cinema.getShowList().get(0);
        Customer customer = this.cinema.getCustomerList().get(0); 
        Booker booker = this.cinema.getNewBooker(show, customer);
        booker.addReservation(1, 2); 
        booker.addReservation(2, 2); 
        booker.finalizeCardPayment("REF:12345657");
        Ticket ticket = this.cinema.getTicketList().get(0);   
        
        // run
        Transferer transferer1 = this.cinema.getNewTransferer(show, ticket);
        Transferer transferer2 = this.cinema.getNewTransferer(show, ticket);
        compare(transferer1==transferer2, false);
        System.out.println("");
    }
    

    /**
     * testAddScreen()
     * 
     * Test that the Cinema addScreen method
     * adds a screen to the screens map in the 
     * cinema class
     * 
     * @return void
     */
    private void testAddScreen()
    {
        System.out.println("addScreen");
        // setup
        setupTestEnvironment();
        
        // run tests
        int count = this.cinema.getScreenList().size();
        this.cinema.addScreen("Screen 3"); 
        compare(this.cinema.getScreenList().size(),count+1);
        this.cinema.addScreen("Screen 4"); 
        compare(this.cinema.getScreenList().size(),count+2);
        
        System.out.println("");
    }    
    
    /**
     * testAddShow()
     * 
     * Test that the addShow method in cinema class
     * creates a new show and adds it 
     * to the shows map
     * 
     * @return void
     */
    private void testAddShow()
    {
        System.out.println("addShow");
        // setup
        setupTestEnvironment();
        Screen screen = this.cinema.getScreenList().get(0);
        Film film = this.cinema.getFilmList().get(0);
        int count = this.cinema.getShowList().size();
        Calendar date1 = new GregorianCalendar(2017, 12, 20, 14, 00);
        Calendar date2 = new GregorianCalendar(2017, 12, 20, 19, 00);
        Calendar date3 = new GregorianCalendar(2017, 12, 21, 19, 00);
        
        // run
        this.cinema.addShow(date1, screen, film, (float)12.50, (float)15.00); 
        compare(this.cinema.getShowList().size(),count+1);
        this.cinema.addShow(date2, screen, film, (float)12.50, (float)15.00); 
        compare(this.cinema.getShowList().size(),count+2);
        this.cinema.addShow(date3, screen, film, (float)12.50, (float)15.00); 
        compare(this.cinema.getShowList().size(),count+3);
        System.out.println("");
    }    
    
     /**
     * testAddCustomer()
     * 
     * Test that the addCustomer method in cinema class
     * creates a new customer and adds it 
     * to the customers map
     * 
     * @return void
     */
    private void testAddCustomer()
    {
        System.out.println("addCustomer");
        // setup
        setupTestEnvironment();
        
        // run
        int count = this.cinema.getCustomerList().size();
        this.cinema.addCustomer(); 
        compare(this.cinema.getCustomerList().size(),count+1);
        this.cinema.addCustomer("John Malone"); 
        compare(this.cinema.getCustomerList().size(),count+2);
        System.out.println("");
    }   
    
    /**
     * testAddFilm()
     * 
     * Test that cinema addFilm method
     * creates a new film and adds it to 
     * the cinema films map
     * 
     * @return void
     */
    private void testAddFilm()
    {
        System.out.println("addFilm");
        // setup
        setupTestEnvironment();
        
        // run
        int count = this.cinema.getFilmList().size();
        this.cinema.addFilm("Random Movie 1", 1941, "Orson Welles", "EN"); 
        compare(this.cinema.getFilmList().size(),count+1);
        this.cinema.addFilm("Random Movie 2", 1941, "Orson Welles", "EN"); 
        compare(this.cinema.getFilmList().size(),count+2);
        System.out.println("");
    }
    
    /**
     * testGetNewCustomer()
     * 
     * Test that a new customer was created and returned 
     * correctly
     * 
     * @return void
     */
    private void testGetNewCustomer()
    {
        System.out.println("getNewCustomer");
        // setup
        setupTestEnvironment();
        
        // run
        Customer customer = this.cinema.getNewCustomer("Bob Reilly"); 
        compare(customer.getName(),"Bob Reilly");
        System.out.println("");
    }  
    
    /**
     * testCinemaConvertToRowLetter
     * 
     * Test that the converToRowLetter method 
     * correctly converts from row as integer 
     * to row as letter
     * 
     * @return void
     */
    private void testCinemaConvertToRowLetter()
    {
        System.out.println("Cinema: convertToRowLetter");
        
        // run
        compare(this.cinema.convertToRowLetter(0)," ");
        compare(this.cinema.convertToRowLetter(1),"A");
        compare(this.cinema.convertToRowLetter(2),"B");
        compare(this.cinema.convertToRowLetter(3),"C");
        compare(this.cinema.convertToRowLetter(4),"D");
        compare(this.cinema.convertToRowLetter(5),"E");
        compare(this.cinema.convertToRowLetter(6)," ");
        System.out.println("");
    }
    
    /**
     * testCinemaConvertToRowNum
     * 
     * Test that the converToRowNum method 
     * correctly converts from row as letter (String) 
     * to row as integer
     * 
     * @return void
     */
    private void testCinemaConvertToRowNum()
    {
        System.out.println("Cinema: convertToRowNum");
        // run
        compare(this.cinema.convertToRowNum(""),-1);
        compare(this.cinema.convertToRowNum("a"),1);
        compare(this.cinema.convertToRowNum("A"),1);
        compare(this.cinema.convertToRowNum("b"),2);
        compare(this.cinema.convertToRowNum("B"),2);
        compare(this.cinema.convertToRowNum("c"),3);
        compare(this.cinema.convertToRowNum("C"),3);
        compare(this.cinema.convertToRowNum("d"),4);
        compare(this.cinema.convertToRowNum("D"),4);
        compare(this.cinema.convertToRowNum("e"),5);
        compare(this.cinema.convertToRowNum("E"),5);
        compare(this.cinema.convertToRowNum("f"),-1);
        compare(this.cinema.convertToRowNum("F"),-1);
        System.out.println("");
    }
    
    /**
     * testBookerAddTemporarySeatReservation()
     * 
     * Test that the Booker creates a new 
     * seat reservation when addSeatReservation 
     * is called
     * 
     * @return void
     */
    private void testBookerAddTemporarySeatReservation()
    {
        System.out.println("addSeatReservation");
        // setup
        setupTestEnvironment();
        Show show = this.cinema.getShowList().get(0);        
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show, customer);
        int count = booker.getReservations().size();        
        
        // run
        booker.addReservation(1, 1); 
        compare(booker.getReservations().size(),count+1);
        booker.addReservation(2, 2); 
        compare(booker.getReservations().size(),count+2);
        System.out.println("");
    }
    
    /**
     * testIsExistReservation()
     * 
     * Test that the isExistReservation correctly 
     * checks to see if a reservation has been set
     * 
     * @return void
     */
    private void testIsExistReservation()
    {
        System.out.println("isExistReservation");
        // setup
        setupTestEnvironment();
        Show show = this.cinema.getShowList().get(0);
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show, customer);
        
        // run
        compare(booker.getTotalPrice(),(float)0.0);
        booker.addReservation(1, 2); 
        compare(booker.getTotalPrice(),show.getPriceRegular());
        booker.addReservation(2, 2); 
        compare(booker.getTotalPrice(),(show.getPriceRegular()*2));
        booker.addReservation(5, 2); 
        compare(booker.getTotalPrice(),(show.getPriceRegular()*2+show.getPriceVip()));        
        compare(booker.isExistReservation(1,2),true);
        compare(booker.isExistReservation(2,2),true);
        compare(booker.isExistReservation(5,2),true);
        compare(booker.isExistReservation(5,3),false);
        compare(booker.isExistReservation(5,4),false);
        System.out.println("");
    }

    /**
     * testGetTotalPrice()
     * 
     * Test that the getTotalPrice value for 
     * increases correctly as new seat reservations 
     * are added
     * 
     * @return void
     */
    private void testGetTotalPrice()
    {
        System.out.println("getTotalPrice");
        // setup
        setupTestEnvironment();
        Show show = this.cinema.getShowList().get(0);
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show, customer);
        
        // run
        compare(booker.getTotalPrice(),(float)0.0);
        booker.addReservation(1, 2); 
        compare(booker.getTotalPrice(),show.getPriceRegular());
        booker.addReservation(2, 2); 
        compare(booker.getTotalPrice(),(show.getPriceRegular()*2));
        booker.addReservation(5, 2); 
        compare(booker.getTotalPrice(),(show.getPriceRegular()*2+show.getPriceVip())); 
        System.out.println("");
    }
    
    /**
     * testFormatToTwoDigitString()
     * 
     * Convert integer to two-digit string
     * 
     * @return void
     */
    private void testFormatToTwoDigitString()
    {
        System.out.println("formatToTwoDigitString");
        // setup
        setupTestEnvironment();
        Show show = this.cinema.getShowList().get(0);
        
        //run
        compare(show.formatToTwoDigitString(-1),"00");
        compare(show.formatToTwoDigitString(0),"00");
        compare(show.formatToTwoDigitString(1),"01");
        compare(show.formatToTwoDigitString(5),"05");
        compare(show.formatToTwoDigitString(9),"09");
        compare(show.formatToTwoDigitString(10),"10");
        compare(show.formatToTwoDigitString(11),"11");
        System.out.println("");
    }    
    
    /**
     * testSeatAllocationConvertToRowLetter()
     * 
     * Test the convertToRowLetter. This accepts 
     * and integer 1-5 and returns a capital letter 
     * A-E. Test that values outside this range 
     * return a string with a single white space
     * 
     * @return void
     */
    private void testSeatAllocationConvertToRowLetter()
    {
        System.out.println("SeatAllocation: convertToRowLetter");
        //setup
        setupTestEnvironment();
        Show show = this.cinema.getShowList().get(0);
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show, customer);
        booker.addReservation(1, 2); 
        booker.finalizeCashPayment();
        Ticket ticket = this.cinema.getTicketList().get(this.cinema.getTicketList().size()-1);
        Transferer transferer = this.cinema.getNewTransferer(show, ticket);
        transferer.setReservation(1, 3);
        
        // run
        compare(transferer.getReservation().convertToRowLetter(0)," ");
        compare(transferer.getReservation().convertToRowLetter(1),"A");
        compare(transferer.getReservation().convertToRowLetter(2),"B");
        compare(transferer.getReservation().convertToRowLetter(3),"C");
        compare(transferer.getReservation().convertToRowLetter(4),"D");
        compare(transferer.getReservation().convertToRowLetter(5),"E");
        compare(transferer.getReservation().convertToRowLetter(6)," ");
        System.out.println("");
    }    
    
    /**
     * testAddIncome
     * 
     * Test that the addIncome method in IncomeReport 
     * adds the correct value to the income sum
     * 
     * @return void
     */
    private void testAddIncome()
    {
        System.out.println("addIncome");
        //setup
        setupTestEnvironment();
        Film film = this.cinema.getFilmList().get(0);
        
        IncomeReport incomeReport = new IncomeReport(film, 1, 2017);
        compare(incomeReport.getIncomeSum(), (float)0.0);// initial value is 0.0
        incomeReport.addIncome((float)150.00);
        compare(incomeReport.getIncomeSum(), (float)150.0);// updated value has increase correctly
        incomeReport.addIncome((float)57.00);
        compare(incomeReport.getIncomeSum(), (float)207.0);// updated value has increase correctly
        System.out.println("");
    }   
    
    /**
     * testIncrementTicketCounter
     * 
     * Test that the ticket counter increments
     * correctly when the incrementTicketCounter 
     * is called
     * 
     * @return void
     */
    private void testIncrementTicketCounter()
    {
        System.out.println("incrementTicketCounter");
        //setup
        setupTestEnvironment();
        Film film = this.cinema.getFilmList().get(0);
        
        TicketReport ticketReport = new TicketReport(film, 1, 2017);
        compare(ticketReport.getTicketCounter(), 0);// initial value is 0
        ticketReport.incrementTicketCounter();
        compare(ticketReport.getTicketCounter(), 1);// updated value has increase correctly
        ticketReport.incrementTicketCounter();
        compare(ticketReport.getTicketCounter(), 2);// updated value has increase correctly
        System.out.println("");
    }     
    
    /**
     * testIncrementRatingCounter
     * 
     * Test that the rating counter increments
     * correctly when a new rating is added
     * is called
     * 
     * @return void
     */
    private void testIncrementRatingCounter()
    {
        System.out.println("incrementRatingCounter");
        //setup
        setupTestEnvironment();
        Film film = this.cinema.getFilmList().get(0);
        
        TicketReport ticketReport = new TicketReport(film, 1, 2017);
        compare(ticketReport.getRatingCounter(), 0);// initial value is 0
        ticketReport.addRating(5);
        compare(ticketReport.getRatingCounter(), 1);// updated value has increase correctly
        ticketReport.addRating(3);
        compare(ticketReport.getRatingCounter(), 2);// updated value has increase correctly
        System.out.println("");
    }      
    
    /**
     * testIncrementRatingCounter
     * 
     * Test that the rating counter increments
     * correctly when a new rating is added
     * is called
     * 
     * @return void
     */
    private void testGetAverageRating()
    {
        System.out.println("getAverageRating");
        //setup
        setupTestEnvironment();
        Film film = this.cinema.getFilmList().get(0);
        
        TicketReport ticketReport = new TicketReport(film, 1, 2017);
        compare(ticketReport.getAverageRating(), "N/A");// initial value is "N/A" before reviews are added
        ticketReport.addRating(5);
        compare(ticketReport.getAverageRating(), "5.0 (1 reviews)");// updated value has increase correctly
        ticketReport.addRating(3);
        compare(ticketReport.getAverageRating(), "4.0 (2 reviews)");// updated value has increase correctly
        System.out.println("");
    }   

    
    /**
     * SYSTEM TESTING
     */
    
    
    /**
     * runSystemTests()
     * 
     * Run the System tests
     * 
     * @return void
     */
    private void runSystemTests()
    {
        System.out.println("****************************");
        System.out.println("*** Running System Tests ***");
        System.out.println("****************************");
        testIsValidShow();
        testIsValidCustomerId();
        testAddReview();
        testGetTicketListBooking();
        testGetTicketsShow();
        testGetTicketsBooking();
        testAddBooking();
        testGetTicket();
        testBookerIsValidSeatSelection();
        testBookerRemoveTemporarySeatReservation();
    }
    
    /**
     * testIsValidShow()
     * 
     * test whether both the screen and film are available before creating the show
     * 
     * @return void
     */
    private void testIsValidShow()
    {
        System.out.println("isValidShow");
        // create dates, screens and films
        setupTestEnvironment();
        Screen screen1 = this.cinema.getScreenList().get(0);
        Screen screen2 = this.cinema.getScreenList().get(1);
        Film film1 = this.cinema.getFilmList().get(0);
        Film film2 = this.cinema.getFilmList().get(1);
        Calendar date1 = new GregorianCalendar(2017, 12, 20, 14, 00);
        Calendar date2 = new GregorianCalendar(2017, 12, 20, 14, 00);
        Calendar date3 = new GregorianCalendar(2017, 12, 21, 14, 00);
        Calendar date4 = new GregorianCalendar(2017, 12, 20, 19, 00);
        Calendar date5 = new GregorianCalendar(2017, 12, 20, 14, 00);
        Calendar date6 = new GregorianCalendar(2017, 12, 20, 14, 00);
        this.cinema.addShow(date1, screen1, film1, (float)12.50, (float)15.00);
        
        // run
        compare(this.cinema.isValidShow(date2, screen1, film1),false); // All four arguments clash
        compare(this.cinema.isValidShow(date3, screen1, film1),true);// No clashes because date is different
        compare(this.cinema.isValidShow(date4, screen1, film1),true);// No clashes becase slot is different
        compare(this.cinema.isValidShow(date5, screen2, film1),false);// Film clashes
        compare(this.cinema.isValidShow(date6, screen1, film2),false);   // Screen clashes     
        System.out.println("");
    }
    
    /**
     * testIsValidCustomerId()
     * 
     * Test the isValidCustomerId method
     * 
     * @return void
     */
    private void testIsValidCustomerId()
    {
        System.out.println("isValidCustomerId");
        // setup
        setupTestEnvironment();
        Customer customer = this.cinema.getCustomerList().get(0);
        
        //run
        compare(this.cinema.isValidCustomerId(customer.getId()),true);
        compare(this.cinema.isValidCustomerId(-1),false);
        compare(this.cinema.isValidCustomerId(0),false);
        System.out.println("");
    }

    /**
     * testAddReview()
     * 
     * Test that a new review gets added correctly 
     * 
     * @return void
     */
    private void testAddReview()
    {
        System.out.println("addReview");
        // setup
        setupTestEnvironment();
        Show show = this.cinema.getShowList().get(0);
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show, customer);
        booker.addReservation(1, 2); 
        booker.addReservation(2, 2); 
        booker.addReservation(3, 2);   
        booker.finalizeCashPayment();
        Ticket ticket = this.cinema.getTicketList().get(0);
        int reviewCount = this.cinema.getReviews().size(); 
        
        //run
        try
        {
            this.cinema.addReview(ticket, "This movie was terrible!", -1);
            compare(true, false);// test failed. Expected an exception
        } catch (IllegalArgumentException ex) {
            compare(false, false);// test passed. Expected and expection
        }
        compare((this.cinema.getReviews().size() == reviewCount), true);
        
        try
        {
            this.cinema.addReview(ticket, "This movie was incredibly good!", 6);
            compare(true, false);// test failed. Expected an exception
        } catch (IllegalArgumentException ex) {
            compare(false, false);// test passed. Expected and expection
        }
        compare((this.cinema.getReviews().size() == reviewCount), true);
        
        try
        {
            this.cinema.addReview(ticket, "This movie was average!", 3);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        compare((this.cinema.getReviews().size() == (reviewCount + 1)), true);
        
        try
        {
            this.cinema.addReview(ticket, "This movie was average!", 4);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        compare((this.cinema.getReviews().size() == (reviewCount + 1)), true);
        
        System.out.println("");
    }

    
    
    
    /**
     * testGetTicketListBooking()
     * 
     * Test that the elements in the list of tickets 
     * purchased for a booking, matches the number of 
     * tickets bought
     * 
     * @return void
     */
    private void testGetTicketListBooking()
    {
        System.out.println("getTicketListBooking");
        //setup
        setupTestEnvironment();
        Show show = this.cinema.getShowList().get(0);
        Customer customer = this.cinema.getCustomerList().get(0); 
        Booker booker = this.cinema.getNewBooker(show, customer);
        booker.addReservation(1, 2); 
        booker.addReservation(2, 2); 
        booker.finalizeCardPayment("REF:12345657"); 
        
        //run
        Booking booking = this.cinema.getBookingList().get(this.cinema.getBookingList().size()-1); // last made booking
        compare(this.cinema.getTicketList(booking).size(), 2);
        System.out.println("");
    }


    
    /**
     * testGetTicketsShow()
     * 
     * Test that the card payment process creates and stores 
     * new tickets, and that these are added to the hashmap 
     * returned by getTickets
     * 
     * @return void
     */
    private void testGetTicketsShow()
    {
        System.out.println("getTicketsShow");
        // setup
        setupTestEnvironment();
        Show show = this.cinema.getShowList().get(0);
        Customer customer = this.cinema.getCustomerList().get(0); 
        Booker booker = this.cinema.getNewBooker(show, customer);
        int ticketCount = this.cinema.getTickets(show).size();
        
        //run
        booker.addReservation(1, 2); 
        booker.addReservation(2, 2); 
        booker.finalizeCardPayment("REF:12345657"); 
        compare(this.cinema.getTickets(show).size(), ticketCount+2);
        System.out.println("");
    }    
    
    /**
     * testGetTicketsBooking()
     * 
     * Test that new tickets have been created for the 
     * booking, and that the list of tickets for that 
     * booking matches the number of tickets sold
     * 
     * @return void
     */
    private void testGetTicketsBooking()
    {
        System.out.println("getTicketsBooking");
        setupTestEnvironment();
        Show show = this.cinema.getShowList().get(0);
        Customer customer = this.cinema.getCustomerList().get(0); 
        Booker booker = this.cinema.getNewBooker(show, customer);
        booker.addReservation(1, 2); 
        booker.addReservation(2, 2); 
        booker.finalizeCardPayment("REF:12345657"); 
        Booking booking = this.cinema.getBookingList().get(this.cinema.getBookingList().size()-1); // last made booking
        
        //run
        compare(this.cinema.getTickets(booking).size(), 2);
        System.out.println("");
    }
    
    
    /**
     * testAddBooking()
     * 
     * Test that the booking process correctly 
     * creates a new booking. Test that the list 
     * of booking objects increases by 1 with 
     * every new booking
     * 
     * @return void
     */
    private void testAddBooking()
    {
        System.out.println("addBooking");
        //setup
        setupTestEnvironment();
        Show show = this.cinema.getShowList().get(0);
        Customer customer = this.cinema.getCustomerList().get(0); 
        //run
        Booker booker = this.cinema.getNewBooker(show, customer);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.finalizeCardPayment("REF:12345657"); 
        int bookingCount = this.cinema.getBookingList().size();
        booker = this.cinema.getNewBooker(show, customer);
        booker.addReservation(2, 1); 
        booker.addReservation(2, 2); 
        booker.finalizeCardPayment("REF:12345657"); 
        compare(this.cinema.getBookingList().size(), bookingCount+1);
        booker = this.cinema.getNewBooker(show, customer);
        booker.addReservation(3, 1); 
        booker.addReservation(3, 2); 
        booker.finalizeCardPayment("REF:12345657"); 
        compare(this.cinema.getBookingList().size(), bookingCount+2);
        System.out.println("");
    }
    
    /**
     * testGetTicket()
     * 
     * Test that the getTicket throws an exception 
     * if the ticketId given is not in recognized. 
     * Test that the getTicket correctly returns the 
     * requested ticket if the id is valid
     * 
     * @return void
     */
    private void testGetTicket()
    {
        System.out.println("getTicket");
        //setup 
        setupTestEnvironment();
        Show show = this.cinema.getShowList().get(0);
        Customer customer = this.cinema.getCustomerList().get(0); 
        Booker booker = this.cinema.getNewBooker(show, customer);
        booker.addReservation(1, 2); 
        booker.addReservation(2, 2); 
        booker.finalizeCardPayment("REF:12345657");  
        Ticket ticket1 = this.cinema.getTicketList().get(0);
        //run
        try
        {
            Ticket ticket2 = this.cinema.getTicket(ticket1.getId());
            compare(true,true);// test passed
        } catch (IllegalArgumentException ex) {
            compare(false,true);// test failed
        }
        
        try
        {
            Ticket ticket3 = this.cinema.getTicket(-1);
            compare(true,false);// test failed. Exception expected
        } catch (IllegalArgumentException ex) {
            compare(false,false);// test passed. Exception expected
        }
        
        try
        {
            Ticket ticket3 = this.cinema.getTicket(0);
            compare(true,false);// test failed. Exception expected
        } catch (IllegalArgumentException ex) {
            compare(false,false);// test passed. Exception expected
        }
        
        System.out.println("");
    }
    
    /**
     * testBookerIsValidSeatSelection()
     * 
     * Test that isValidSeatSelection only 
     * returns true if no seat reservation 
     * has been set by the TicketManager
     * 
     * @return void
     */
    private void testBookerIsValidSeatSelection()
    {
        System.out.println("isValidSeatSelection");
        //setup
        setupTestEnvironment();
        Show show = this.cinema.getShowList().get(0);        
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show, customer);
        
        // run
        compare(booker.isValidSeatSelection(5, 2),true); 
        booker.addReservation(5, 2); 
        compare(booker.isValidSeatSelection(5, 2),false);
        System.out.println("");
    }
    
    /**
     * testBookerRemoveTemporarySeatReservation()
     * 
     * Test that the addReservation method increases 
     * the seat reservation count by one, and the 
     * remove seat reservation method removes the 
     * seat reservation. 
     * 
     * @return void
     */
    private void testBookerRemoveTemporarySeatReservation()
    {
        System.out.println("removeSeatReservation");
        //setup
        setupTestEnvironment();
        Show show = this.cinema.getShowList().get(0);        
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show, customer);
        int count = booker.getReservations().size();
        
        // run
        booker.addReservation(1, 2); 
        booker.addReservation(2, 2); 
        compare(booker.getReservations().size(),count+2);
        booker.removeReservation(1, 2); 
        compare(booker.getReservations().size(),count+1);
        booker.removeReservation(3, 2); 
        compare(booker.getReservations().size(),count+1);// testing that the count only decreases with reservation that exists
        booker.removeReservation(2, 2); 
        compare(booker.getReservations().size(),count);
        System.out.println("");
    }    
    
    /**
     * INTEGRATION TESTING
     */
    
    
    
    /**
     * runIntegrationTests()
     * 
     * Run the integration test
     * 
     * @return void
     */
    private void runIntegrationTests()
    {
        
        System.out.println("*********************************");
        System.out.println("*** Running Integration Tests ***");
        System.out.println("*********************************");
        testBookingAndCashPaymentProcess();
        testBookingAndCardPaymentProcess();
        testTransfererCashPaymentSamePrice();
        testTransfererCashPaymentLowerPrice();
        testTransfererCashPaymentHigherPrice();
        testGetOrderedReportLists();
    }
    

    /**
     * testBookingAndCashPaymentProcess()
     * 
     * Create new booking and finalize 
     * payment by cash
     * 
     * @return void
     */
    private void testBookingAndCashPaymentProcess()
    {
        System.out.println("bookingAndCashPaymentProcess");
        // setup
        setupTestEnvironment();
        Show show = this.cinema.getShowList().get(0);
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show, customer);
        
        // run
        compare(booker.getTotalPrice(),(float)0.0);
        booker.addReservation(1, 2); 
        booker.addReservation(2, 2); 
        booker.addReservation(5, 2); 
        int reservationCount = booker.getReservations().size(); 
        int cashPaymentCountPre = this.cinema.getCashPayments().size();
        int ticketCountPre = this.cinema.getTickets(show).size();
        booker.finalizeCashPayment();
        
        int ticketCountPost = this.cinema.getTickets(show).size();   
        compare((ticketCountPost == (reservationCount + ticketCountPre)), true);
        
        int cashPaymentCountPost = this.cinema.getCashPayments().size();   
        compare((cashPaymentCountPost == (1 + cashPaymentCountPre)), true);
        
        // run the same test again to confirm that another new payment has been created
        booker = this.cinema.getNewBooker(show, customer);
        booker.addReservation(4, 2); 
        booker.addReservation(5, 5);
        booker.finalizeCashPayment();
        compare(booker.getReservations().size(),0);// test Booker.convertToTickets removes the reservations
        cashPaymentCountPost = this.cinema.getCashPayments().size();   
        compare((cashPaymentCountPost == (2 + cashPaymentCountPre)), true);
        System.out.println("");
    }
    
    /**
     * testBookingAndCardPaymentProcess()
     * 
     * Create a new booking and complete the 
     * payment process for card payment
     * 
     * @return void
     */
    private void testBookingAndCardPaymentProcess()
    {
        System.out.println("bookingAndCardPaymentProcess");
        setupTestEnvironment();
        Show show = this.cinema.getShowList().get(0);
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show, customer);
        
        // run
        booker.addReservation(1, 2); 
        booker.addReservation(2, 2); 
        int reservationCount = booker.getReservations().size(); 
        int cardPaymentCountPre = this.cinema.getCardPayments().size();
        int ticketCountPre = this.cinema.getTickets(show).size();    
        booker.finalizeCardPayment("REF:12345657");
        int ticketCountPost = this.cinema.getTickets(show).size();   
        compare((ticketCountPost == (reservationCount + ticketCountPre)), true);
        int cardPaymentCountPost = this.cinema.getCardPayments().size();   
        compare((cardPaymentCountPost == (1 + cardPaymentCountPre)), true);
        
        // run the same test again to confirm that another new payment has been created
        booker = this.cinema.getNewBooker(show, customer);
        booker.addReservation(4, 2); 
        booker.addReservation(5, 2);
        booker.finalizeCardPayment("REF:7654321");
        cardPaymentCountPost = this.cinema.getCardPayments().size();   
        compare((cardPaymentCountPost == (2 + cardPaymentCountPre)), true);
        System.out.println("");
    }
        

    
    /**
     * testTransfererCashPaymentSamePrice()
     * 
     * Test that a ticket transfers successfully 
     * when there is no transfer surcharge
     * 
     * @return void
     */
    private void testTransfererCashPaymentSamePrice()
    {
        System.out.println("transfer ticket with no surcharge");
        //setup
        setupTestEnvironment();
        Show show1 = this.cinema.getShowList().get(0);
        Show show2 = this.cinema.getShowList().get(1);
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show1, customer);
        booker.addReservation(1, 2); 
        booker.finalizeCashPayment();
        Ticket ticket = this.cinema.getTicketList().get(0);// get the first of those tickets
        Transferer transferer = this.cinema.getNewTransferer(show2, ticket);// Transfer that ticket to a new show
        int count = this.cinema.getCashPayments().size();
        transferer.setReservation(1, 3);
        
        //run 
        compare(transferer.getSurcharge(),(float)0.0);// test getSurcharge
        transferer.finalizeCashPayment();
        compare(this.cinema.getCashPayments().size(),count);// cash payment has not gone up, but the ticket details have changed
        compare(ticket.getRow(),1);
        compare(ticket.getNum(),3);
        System.out.println("");
    }
    
    /**
     * testTransfererCashPaymentLowerPrice()
     * 
     * Test that no surcharge is applied when the 
     * ticket is transfered to a lower-priced seat
     * 
     * @return void
     */
    private void testTransfererCashPaymentLowerPrice()
    {
        System.out.println("transfer ticket to lower price");
        //setup
        setupTestEnvironment();
        Show show1 = this.cinema.getShowList().get(0);
        Show show2 = this.cinema.getShowList().get(1);
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show1, customer);
        booker.addReservation(5, 2); 
        booker.finalizeCashPayment();
        // run 
        Ticket ticket = this.cinema.getTicketList().get(0);// get the first of those tickets
        Transferer transferer = this.cinema.getNewTransferer(show2, ticket);// Transfer that ticket to a new show
        int count = this.cinema.getCashPayments().size();
        transferer.setReservation(1, 3);
        compare(transferer.getSurcharge(),(float)0.0);// test getSurcharge
        transferer.finalizeCashPayment();
        compare(this.cinema.getCashPayments().size(),count);// cash payment has not gone up, but the ticket details have changed
        compare(ticket.getRow(),1);
        compare(ticket.getNum(),3);
        System.out.println("");
    }
    
    /**
     * testTransfererCashPaymentHigherPrice()
     * 
     * Test that the transfer of a ticket to a higher 
     * priced seat incurs a surcharge that is the difference 
     * between the two prices
     * 
     * @return void
     */
    private void testTransfererCashPaymentHigherPrice()
    {
        System.out.println("Transfer ticket to a higher priced seat");
        // setup
        setupTestEnvironment();
        Show show1 = this.cinema.getShowList().get(0);
        Show show2 = this.cinema.getShowList().get(1);
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show1, customer);
        booker.addReservation(1, 2); 
        booker.finalizeCashPayment();
        
        // run 
        Ticket ticket = this.cinema.getTicketList().get(0);// get the first of those tickets
        Transferer transferer = this.cinema.getNewTransferer(show2, ticket);// Transfer that ticket to a new show
        int count = this.cinema.getCashPayments().size();
        compare(transferer.getReservation()==null, true);// test set reservation
        transferer.setReservation(5, 3);
        compare(transferer.getReservation()==null, false);// test set reservation
        compare(transferer.getSurcharge(),(float)show2.getPriceVip()-(float)show1.getPriceRegular());// test getSurcharge
        transferer.finalizeCashPayment();
        compare(this.cinema.getCashPayments().size(),count+1);// cash payment has not gone up, but the ticket details have changed
        compare(ticket.getRow(),5);
        compare(ticket.getNum(),3);
        System.out.println("");
    }
    
    /**
     * testGetOrderedReportLists()
     * 
     * Test whether the report lists are ordered correctly
     * 
     * @return void
     */
    private void testGetOrderedReportLists()
    {
        System.out.println("getReportLists");
        // setup
        setupTestEnvironment();

        Screen screen = this.cinema.getScreenList().get(0);
        Film film1 = this.cinema.getFilmList().get(0);
        Film film2 = this.cinema.getFilmList().get(1);
        Film film3 = this.cinema.getFilmList().get(2);
        Calendar date1 = new GregorianCalendar(2017, 11, 01, 11, 00);
        Calendar date2 = new GregorianCalendar(2017, 11, 02, 11, 00);
        Calendar date3 = new GregorianCalendar(2017, 11, 03, 11, 00);
        Calendar date4 = new GregorianCalendar(2017, 11, 04, 11, 00);
        Calendar date5 = new GregorianCalendar(2017, 11, 05, 11, 00);
        Calendar date6 = new GregorianCalendar(2017, 11, 06, 11, 00);
        this.cinema.addShow(date1, screen, film1, (float)12.50, (float)14.00);
        this.cinema.addShow(date2, screen, film2, (float)12.50, (float)14.00);
        this.cinema.addShow(date3, screen, film3, (float)12.50, (float)14.00);
        this.cinema.addShow(date4, screen, film1, (float)12.50, (float)14.00);
        this.cinema.addShow(date5, screen, film2, (float)12.50, (float)14.00);
        this.cinema.addShow(date6, screen, film3, (float)12.50, (float)14.00);
        Show show1 = this.cinema.getShowList().get(this.cinema.getShowList().size()-6);
        Show show2 = this.cinema.getShowList().get(this.cinema.getShowList().size()-5);
        Show show3 = this.cinema.getShowList().get(this.cinema.getShowList().size()-4);
        Show show4 = this.cinema.getShowList().get(this.cinema.getShowList().size()-3);
        Show show5 = this.cinema.getShowList().get(this.cinema.getShowList().size()-2);
        Show show6 = this.cinema.getShowList().get(this.cinema.getShowList().size()-1);
        Customer customer = this.cinema.getCustomerList().get(0);
        
        Booker booker = this.cinema.getNewBooker(show1, customer);
        booker.addReservation(1, 2); 
        booker.addReservation(2, 2); 
        booker.addReservation(5, 2); 
        booker.addReservation(4, 2); 
        booker.addReservation(5, 3);
        booker.finalizeCashPayment();
        
        booker = this.cinema.getNewBooker(show2, customer);
        booker.addReservation(1, 2); 
        booker.addReservation(2, 2); 
        booker.addReservation(3, 2); 
        booker.addReservation(4, 2); 
        booker.addReservation(5, 2);
        booker.addReservation(5, 3);
        booker.addReservation(5, 4);
        booker.addReservation(5, 5);
        booker.addReservation(5, 6);
        booker.finalizeCashPayment();
        
        booker = this.cinema.getNewBooker(show3, customer);
        booker.addReservation(1, 2); 
        booker.addReservation(2, 2); 
        booker.addReservation(5, 2); 
        booker.addReservation(5, 3); 
        booker.addReservation(5, 4);
        booker.finalizeCashPayment();
        
        booker = this.cinema.getNewBooker(show4, customer);
        booker.addReservation(1, 2); 
        booker.addReservation(2, 2); 
        booker.addReservation(5, 2); 
        booker.addReservation(5, 3); 
        booker.addReservation(5, 4);
        booker.finalizeCashPayment();
        
        booker = this.cinema.getNewBooker(show5, customer);
        booker.addReservation(1, 2); 
        booker.addReservation(2, 2); 
        booker.addReservation(5, 2); 
        booker.addReservation(5, 3); 
        booker.addReservation(5, 4); 
        booker.addReservation(5, 5); 
        booker.addReservation(5, 6); 
        booker.addReservation(5, 7); 
        booker.addReservation(5, 8); 
        booker.addReservation(5, 9); 
        booker.addReservation(5, 10);
        booker.finalizeCashPayment();
        
        booker = this.cinema.getNewBooker(show6, customer);
        booker.addReservation(1, 2); 
        booker.addReservation(2, 2); 
        booker.addReservation(4, 2); 
        booker.addReservation(4, 3); 
        booker.addReservation(4, 4);
        booker.finalizeCashPayment();
        
        // run 
        Reporter reporter = new Reporter(this.cinema);
        List<TicketReport> ticketReportList = reporter.getTicketReportList(11,2017);
        List<IncomeReport> incomeReportList = reporter.getIncomeReportList(11,2017);
        
        boolean ordered = true;
        int lastInt = Integer.MAX_VALUE;// numbers should descend, so initialize to the highest possible value
        for(TicketReport tr : ticketReportList)
        {
            if(tr.getTicketCounter()>lastInt)
            {
                ordered=false;
            }
            lastInt = tr.getTicketCounter();
        }
        compare(ordered, true);
        
        ordered = true;
        float lastFloat = Float.MAX_VALUE;// numbers should descend, so initialize to the highest possible value
        for(IncomeReport tr : incomeReportList)
        {
            if(tr.getIncomeSum()>lastFloat)
            {
                ordered=false;
            }
            lastFloat = tr.getIncomeSum();
        }
        compare(ordered, true);
        
        System.out.println("");
    }
    
    /**
     * compare
     * 
     * compare two boolean values and call printResult
     * 
     * @param boolean result
     * @param boolean expected
     * @return void
     */
    private void compare(boolean result, boolean expected)
    {
        printResult(result==expected);
    }
    
    /**
     * compare
     * 
     * compare two int values and call printResult
     * 
     * @param int result
     * @param int expected
     * @return void
     */
    private void compare(int result, int expected)
    {
        printResult(result==expected);
    }
    
    /**
     * compare
     * 
     * compare two float values and call printResult
     * 
     * @param int result
     * @param int expected
     * @return void
     */
    private void compare(float result, float expected)
    {
        printResult(result==expected);
    }
    
    /**
     * compare
     * 
     * compare two Strings and call printResult
     * 
     * @param String result
     * @param String expected
     * @return void
     */
    private void compare(String result, String expected)
    {
        printResult(result.equals(expected));
    }
    
    /**
     * printResult
     * 
     * printResult. Print P if argument true, else print F
     * 
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
