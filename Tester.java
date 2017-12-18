import java.util.Calendar;
import java.util.GregorianCalendar;

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
        testBooker();
        testTransferer();
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
     * testCinemaMethods()
     * @return void
     */
    public void testCinema()
    {
        System.out.println("Testing Cinema Methods");
        testAddScreen();
        testAddFilm();
        testIsValidShow();
        testAddShow();
        testAddCustomer();
        //testPrintSeatingGrid();
        testAddReview();
        System.out.print("");
    }
    
    /**
     * testBookerMethods()
     * @return void
     */
    public void testBooker()
    {
        System.out.println("Testing Booker Methods");
        testBookerIsValidSeatSelection();
        testBookerAddTemporarySeatReservation();
        testBookerRemoveTemporarySeatReservation();
        testBookerFinalizeCashPayment();
        testBookerFinalizeCardPayment();
        System.out.print("");
    }
    
    /**
     * testTransfererMethods()
     * @return void
     */
    public void testTransferer()
    {
        System.out.println("Testing Transferer Methods");
        testTransfererCashPaymentSamePrice();
        testTransfererCashPaymentLowerPrice();
        testTransfererCashPaymentHigherPrice();
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
     * testIsValidShow()
     * test whether both the screen and film are available before creating the show
     * @return void
     */
    private void testIsValidShow()
    {
        setupTestEnvironment();
       
        // run tests
        System.out.println("isValidShow");
        
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
        
        // All four arguments clash
        compare(this.cinema.isValidShow(date2, screen1, film1),false); 
        // No clashes because date is different
        compare(this.cinema.isValidShow(date3, screen1, film1),true);
        // No clashes becase slot is different
        compare(this.cinema.isValidShow(date4, screen1, film1),true);
        // Film clashes
        compare(this.cinema.isValidShow(date5, screen2, film1),false);
        // Screen clashes
        compare(this.cinema.isValidShow(date6, screen1, film2),false);        
   
        System.out.println("");
    }

    /**
     * testAddShow()
     * @return void
     */
    private void testAddShow()
    {
        setupTestEnvironment();
        
        // run tests
        System.out.println("addShow");
        
        Screen screen = this.cinema.getScreenList().get(0);
        Film film = this.cinema.getFilmList().get(0);
        int count = this.cinema.getShowList().size();
        
        
        Calendar date1 = new GregorianCalendar(2017, 12, 20, 14, 00);
        Calendar date2 = new GregorianCalendar(2017, 12, 20, 19, 00);
        Calendar date3 = new GregorianCalendar(2017, 12, 21, 19, 00);
        
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
        
        Show show = this.cinema.getShowList().get(0);
        this.cinema.printSeatingGrid(this.cinema.getSeatingGrid(show)); 

        System.out.println("");
    }
    
    /**
     * testBookerIsValidSeatSelection()
     * @return void
     */
    private void testBookerIsValidSeatSelection()
    {
        setupTestEnvironment();
        
        // run tests
        System.out.println("isValidSeatSelection");
        
        Show show = this.cinema.getShowList().get(0);        
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show, customer);
      
        compare(booker.isValidSeatSelection(5, 2),true); 
        booker.addReservation(5, 2); 
        compare(booker.isValidSeatSelection(5, 2),false);
        
        System.out.println("");
    }
    
    /**
     * testBookerAddTemporarySeatReservation()
     * @return void
     */
    private void testBookerAddTemporarySeatReservation()
    {
        setupTestEnvironment();
        
        // run tests
        System.out.println("addSeatReservation");
        
        Show show = this.cinema.getShowList().get(0);        
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show, customer);
    
        int count = booker.getReservations().size();
        booker.addReservation(1, 2); 
        compare(booker.getReservations().size(),count+1);
        booker.addReservation(2, 2); 
        compare(booker.getReservations().size(),count+2);
        
        System.out.println("");
    }
    
    /**
     * testBookerRemoveTemporarySeatReservation()
     * @return void
     */
    private void testBookerRemoveTemporarySeatReservation()
    {
        setupTestEnvironment();
        
        // run tests
        System.out.println("removeSeatReservation");
        
        Show show = this.cinema.getShowList().get(0);        
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show, customer);
    
        int count = booker.getReservations().size();
        booker.addReservation(1, 2); 
        booker.addReservation(2, 2); 
        compare(booker.getReservations().size(),count+2);
        booker.removeReservation(1, 2); 
        compare(booker.getReservations().size(),count+1);
        booker.removeReservation(2, 2); 
        compare(booker.getReservations().size(),count);
        
        System.out.println("");
    }
    
    /**
     * testBookerFinalizeCashBooking()
     * @return void
     */
    private void testBookerFinalizeCashPayment()
    {
        setupTestEnvironment();
        
        // run tests
        System.out.println("finalizeCashBooking");
        
        Show show = this.cinema.getShowList().get(0);
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show, customer);
        
        booker.addReservation(1, 2); 
        booker.addReservation(2, 2); 
        booker.addReservation(3, 2); 
        
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
        booker.addReservation(5, 2);
        
        booker.finalizeCashPayment();
        
        cashPaymentCountPost = this.cinema.getCashPayments().size();   
        compare((cashPaymentCountPost == (2 + cashPaymentCountPre)), true);
        
        System.out.println("");
    }
    
    /**
     * testBookerFinalizeCardBooking()
     * @return void
     */
    private void testBookerFinalizeCardPayment()
    {
        setupTestEnvironment();
        
        // run tests
        System.out.println("finalizeCardBooking");
        
        Show show = this.cinema.getShowList().get(0);
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show, customer);
        
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
     * @return void
     */
    private void testTransfererCashPaymentSamePrice()
    {
        setupTestEnvironment();
        
        // run tests
        System.out.println("process CashPayment when new ticket price is the same as old ticket price");
        
        // first, book some tickets
        Show show1 = this.cinema.getShowList().get(0);
        Show show2 = this.cinema.getShowList().get(1);
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show1, customer);
        
        booker.addReservation(1, 2); 
        
        booker.finalizeCashPayment();
       
        // get the first of those tickets
        Ticket ticket = this.cinema.getTicketList().get(0);
        
        // Transfer that ticket to a new show
        Transferer transferer = this.cinema.getNewTransferer(show2, ticket);
        int count = this.cinema.getCashPayments().size();
        transferer.setReservation(1, 3);
        transferer.finalizeCashPayment();
        
        // cash payment has not gone up, but the ticket details have changed
        compare(this.cinema.getCashPayments().size(),count);
        compare(ticket.getRow(),1);
        compare(ticket.getNum(),3);
        System.out.println("");
    }
    
    /**
     * testTransfererCashPaymentSamePrice()
     * @return void
     */
    private void testTransfererCashPaymentLowerPrice()
    {
        setupTestEnvironment();
        
        // run tests
        System.out.println("process CashPayment when new ticket price is lower than old ticket price");
        
        // first, book some tickets
        Show show1 = this.cinema.getShowList().get(0);
        Show show2 = this.cinema.getShowList().get(1);
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show1, customer);
        
        booker.addReservation(5, 2); 
        
        booker.finalizeCashPayment();
       
        // get the first of those tickets
        Ticket ticket = this.cinema.getTicketList().get(0);
        
        // Transfer that ticket to a new show
        Transferer transferer = this.cinema.getNewTransferer(show2, ticket);
        int count = this.cinema.getCashPayments().size();
        transferer.setReservation(1, 3);
        transferer.finalizeCashPayment();
        
        // cash payment has not gone up, but the ticket details have changed
        compare(this.cinema.getCashPayments().size(),count);
        compare(ticket.getRow(),1);
        compare(ticket.getNum(),3);
        System.out.println("");
    }
    
    /**
     * testTransfererCashPaymentHigherPrice()
     * @return void
     */
    private void testTransfererCashPaymentHigherPrice()
    {
        setupTestEnvironment();
        
        // run tests
        System.out.println("process CashPayment when new ticket price is higher than old ticket price");
        
        // first, book some tickets
        Show show1 = this.cinema.getShowList().get(0);
        Show show2 = this.cinema.getShowList().get(1);
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show1, customer);
        
        booker.addReservation(1, 2); 
        
        booker.finalizeCashPayment();
       
        // get the first of those tickets
        Ticket ticket = this.cinema.getTicketList().get(0);
        
        // Transfer that ticket to a new show
        Transferer transferer = this.cinema.getNewTransferer(show2, ticket);
        int count = this.cinema.getCashPayments().size();
        transferer.setReservation(5, 3);
        transferer.finalizeCashPayment();
        
        // cash payment has not gone up, but the ticket details have changed
        compare(this.cinema.getCashPayments().size(),count+1);
        compare(ticket.getRow(),5);
        compare(ticket.getNum(),3);
        System.out.println("");
    }
    
    /**
     * testBookerFinalizeCashBooking()
     * @return void
     */
    private void testAddReview()
    {
        setupTestEnvironment();
        
        // run tests
        System.out.println("addReview");
        
        Show show = this.cinema.getShowList().get(0);
        Customer customer = this.cinema.getCustomerList().get(0);
        Booker booker = this.cinema.getNewBooker(show, customer);
        
        booker.addReservation(1, 2); 
        booker.addReservation(2, 2); 
        booker.addReservation(3, 2);   
        booker.finalizeCashPayment();
        
        Ticket ticket = this.cinema.getTicketList().get(0);
        int reviewCount = this.cinema.getReviews().size(); 
        
        System.out.println("Print error saying the value was out of range");
        try
        {
            this.cinema.addReview(ticket, "This movie was terrible!", -1);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        compare((this.cinema.getReviews().size() == reviewCount), true);
        
        System.out.println("Print error saying the value was out of range");
        try
        {
            this.cinema.addReview(ticket, "This movie was incredibly good!", 6);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
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
