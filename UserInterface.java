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
    abstract int displayFilmsIndexPage();
    abstract int displayShowsIndexPage();
    abstract int displayCustomersIndexPage();
    abstract int displayBookingsIndexPage();
    abstract int displayReportsIndexPage();
    abstract int displayAddFilmPage();
    abstract int displayAddShowPage();
    abstract int displayAddBookingPage();
    abstract int displayMoveTicketPage();
    abstract int displayReviewAndRatePage();
    abstract int displayTicketReportsPage();
    abstract int displayIncomeReportsPage();
    
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
     * displayPage
     * Take the pageId and display the page
     * @param int pageId
     * @return void
     */
    protected int displayPage(int pageId)
    {
        switch(pageId)
        {
            case 1:
                return this.displayFilmsIndexPage();
            case 2: 
                return this.displayShowsIndexPage();
            case 3: 
                return this.displayCustomersIndexPage();
            case 4:
                return this.displayBookingsIndexPage();
            case 5:
                return this.displayReportsIndexPage();
            case 10:
                return this.displayAddFilmPage();
            case 11:
                return this.displayAddShowPage();
            case 13:
                return this.displayAddBookingPage();
            case 14:
                return this.displayMoveTicketPage();
            case 15:
                return this.displayReviewAndRatePage();
            case 16:
                return this.displayTicketReportsPage();
            case 17:
                return this.displayIncomeReportsPage();
            default:
                return 1;// Return to the FILMS INDEX hompepage
        }
    }    

    /**
     * Create the default data for the system
     *
     * @return void
     */
    public void setupSystem()
    {    
        // Six Screens
        this.cinema.addScreen("Screen 1");
        this.cinema.addScreen("Screen 2");   
        this.cinema.addScreen("Screen 3");    
        this.cinema.addScreen("Screen 4");    
        this.cinema.addScreen("Screen 5");    
        this.cinema.addScreen("Screen 6");    
        Screen screen1 = this.cinema.getScreenList().get(0);
        Screen screen2 = this.cinema.getScreenList().get(1);
        Screen screen3 = this.cinema.getScreenList().get(2);
        Screen screen4 = this.cinema.getScreenList().get(3);
        Screen screen5 = this.cinema.getScreenList().get(4);
        Screen screen6 = this.cinema.getScreenList().get(5); 
        
        // 10 Films
        this.cinema.addFilm("Psycho", 1960, "Alfred Hitchcock", "EN");
        this.cinema.addFilm("2001: A Space Odyssey", 1968, "Stanley Kubrick", "EN", "NL");
        this.cinema.addFilm("Jurassic Park", 1993, "Steven Spielberg", "EN", "NL");
        this.cinema.addFilm("Taxi Driver", 1976, "Martin Scorcese", "NL");
        this.cinema.addFilm("Citizen Kane", 1941, "Orson Welles", "EN");
        this.cinema.addFilm("The Godfather", 1972, "Francis Ford Coppola", "EN");
        this.cinema.addFilm("Cinema Paradiso", 1988, "Guiseppe Tornatore", "EN", "NL");
        this.cinema.addFilm("To Kill A Mockingbird", 1962, "Robert Mulligan", "EN", "NL");
        this.cinema.addFilm("Annie Hall", 1977, "Woody Allen", "EN");
        this.cinema.addFilm("Withnail & I", 1987, "Bruce Robinson", "EN");
        
        Film film1 = this.cinema.getFilmList().get(0);
        Film film2 = this.cinema.getFilmList().get(1);
        Film film3 = this.cinema.getFilmList().get(2);
        Film film4 = this.cinema.getFilmList().get(3);
        Film film5 = this.cinema.getFilmList().get(4);
        Film film6 = this.cinema.getFilmList().get(5);
        Film film7 = this.cinema.getFilmList().get(6);
        Film film8 = this.cinema.getFilmList().get(7);
        Film film9 = this.cinema.getFilmList().get(8);
        Film film10 = this.cinema.getFilmList().get(9);
        
        // Create dates
        Calendar date1 = new GregorianCalendar(2017, 1, 1, 11, 00);
        Calendar date2 = new GregorianCalendar(2017, 0, 2, 11, 00);
        Calendar date3 = new GregorianCalendar(2017, 0, 3, 11, 00);
        Calendar date4 = new GregorianCalendar(2017, 0, 4, 11, 00);
        Calendar date5 = new GregorianCalendar(2017, 0, 5, 11, 00);
        Calendar date6 = new GregorianCalendar(2017, 0, 6, 11, 00);
        Calendar date7 = new GregorianCalendar(2017, 0, 7, 11, 00);
        Calendar date8 = new GregorianCalendar(2017, 0, 8, 11, 00);
        Calendar date9 = new GregorianCalendar(2017, 0, 9, 11, 00);
        Calendar date10 = new GregorianCalendar(2017, 0, 10, 11, 00);
        Calendar date11 = new GregorianCalendar(2017, 0, 11, 11, 00);
        Calendar date12 = new GregorianCalendar(2017, 0, 12, 11, 00);
        Calendar date13 = new GregorianCalendar(2017, 0, 13, 11, 00);
        Calendar date14 = new GregorianCalendar(2017, 0, 14, 11, 00);
        Calendar date15 = new GregorianCalendar(2017, 0, 15, 11, 00);
        Calendar date16 = new GregorianCalendar(2017, 0, 16, 11, 00);
        Calendar date17 = new GregorianCalendar(2017, 0, 17, 11, 00);
        Calendar date18 = new GregorianCalendar(2017, 0, 18, 11, 00);
        Calendar date19 = new GregorianCalendar(2017, 0, 19, 11, 00);
        Calendar date20 = new GregorianCalendar(2017, 0, 20, 11, 00);
       
        // Create Shows
       
        this.cinema.addShow(date1, screen1, film1, (float)12.50, (float)15.00);
        this.cinema.addShow(date2, screen2, film2, (float)12.50, (float)15.00);
        this.cinema.addShow(date3, screen3, film3, (float)12.50, (float)15.00);
        this.cinema.addShow(date4, screen4, film4, (float)12.50, (float)15.00);
        this.cinema.addShow(date5, screen5, film5, (float)12.50, (float)15.00);
        this.cinema.addShow(date6, screen6, film6, (float)12.50, (float)15.00);
        this.cinema.addShow(date7, screen1, film7, (float)12.50, (float)15.00);
        this.cinema.addShow(date8, screen2, film8, (float)12.50, (float)15.00);
        this.cinema.addShow(date9, screen3, film9, (float)12.50, (float)15.00);
        this.cinema.addShow(date10, screen4, film10, (float)12.50, (float)15.00);
        this.cinema.addShow(date11, screen5, film1, (float)12.50, (float)15.00);
        this.cinema.addShow(date12, screen6, film2, (float)12.50, (float)15.00);
        this.cinema.addShow(date13, screen1, film3, (float)12.50, (float)15.00);
        this.cinema.addShow(date14, screen2, film4, (float)12.50, (float)15.00);
        this.cinema.addShow(date15, screen3, film5, (float)12.50, (float)15.00);
        this.cinema.addShow(date16, screen4, film6, (float)12.50, (float)15.00);
        this.cinema.addShow(date17, screen5, film7, (float)12.50, (float)15.00);
        this.cinema.addShow(date18, screen6, film8, (float)12.50, (float)15.00);
        this.cinema.addShow(date19, screen1, film9, (float)12.50, (float)15.00);
        this.cinema.addShow(date20, screen2, film10, (float)12.50, (float)15.00);
        Show show1 = this.cinema.getShowList().get(0);
        Show show2 = this.cinema.getShowList().get(1);
        Show show3 = this.cinema.getShowList().get(2);
        Show show4 = this.cinema.getShowList().get(3);
        Show show5 = this.cinema.getShowList().get(4);
        Show show6 = this.cinema.getShowList().get(5);
        Show show7 = this.cinema.getShowList().get(6);
        Show show8 = this.cinema.getShowList().get(7);
        Show show9 = this.cinema.getShowList().get(8);
        Show show10 = this.cinema.getShowList().get(9);
        Show show11 = this.cinema.getShowList().get(10);
        Show show12 = this.cinema.getShowList().get(11);
        Show show13 = this.cinema.getShowList().get(12);
        Show show14 = this.cinema.getShowList().get(13);
        Show show15 = this.cinema.getShowList().get(14);
        Show show16 = this.cinema.getShowList().get(15);
        Show show17 = this.cinema.getShowList().get(16);
        Show show18 = this.cinema.getShowList().get(17);
        Show show19 = this.cinema.getShowList().get(18);
        Show show20 = this.cinema.getShowList().get(19);

        // Create 10 Customers
        this.cinema.addCustomer("John Malone"); 
        this.cinema.addCustomer("Sean Jones"); 
        this.cinema.addCustomer("Sarah O Hara"); 
        this.cinema.addCustomer("Clare Fisher"); 
        this.cinema.addCustomer("Tom Dickinson");
        this.cinema.addCustomer("John Sweeney"); 
        this.cinema.addCustomer("Clare Farrell"); 
        this.cinema.addCustomer("Ben Hayes"); 
        this.cinema.addCustomer("Nigel Grace");
        this.cinema.addCustomer("Martin Short");
        Customer customer1 = this.cinema.getCustomerList().get(0);
        Customer customer2 = this.cinema.getCustomerList().get(1);
        Customer customer3 = this.cinema.getCustomerList().get(2);
        Customer customer4 = this.cinema.getCustomerList().get(3);
        Customer customer5 = this.cinema.getCustomerList().get(4);
        Customer customer6 = this.cinema.getCustomerList().get(5);
        Customer customer7 = this.cinema.getCustomerList().get(6);
        Customer customer8 = this.cinema.getCustomerList().get(7);
        Customer customer9 = this.cinema.getCustomerList().get(8);
        Customer customer10 = this.cinema.getCustomerList().get(9);
        
        // Create 20 bookings
        Booker booker = this.cinema.getNewBooker(show1, customer1);
        booker.addReservation(1, 2); 
        booker.addReservation(2, 2); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show2, customer2);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show3, customer3);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show4, customer4);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show5, customer5);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show6, customer6);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show7, customer7);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show8, customer8);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show9, customer9);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show10, customer10);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show11, customer1);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show12, customer2);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show13, customer3);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show14, customer4);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show15, customer5);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show16, customer6);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show17, customer7);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show18, customer8);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show19, customer9);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1);  
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show20, customer10);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.finalizeCardPayment("REF:12345657");
        
        // Add 100 reviews
        this.cinema.addReview(this.cinema.getTicketList().get(0), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(1), "Review Comments", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(2), "Review Comments", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(3), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(4), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(5), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(6), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(7), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(8), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(9), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(10), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(11), "Review Comments", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(12), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(13), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(14), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(15), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(16), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(17), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(18), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(19), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(20), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(21), "Review Comments", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(22), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(23), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(24), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(25), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(26), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(27), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(28), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(29), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(30), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(31), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(32), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(33), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(34), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(35), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(36), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(37), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(38), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(39), "Review Comments", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(40), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(41), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(42), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(43), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(44), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(45), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(46), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(47), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(48), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(49), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(50), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(51), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(52), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(53), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(54), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(55), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(56), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(57), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(58), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(59), "Review Comments", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(60), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(61), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(62), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(63), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(64), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(65), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(66), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(67), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(68), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(69), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(70), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(71), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(72), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(73), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(74), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(75), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(76), "Review Comments", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(77), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(78), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(79), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(80), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(81), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(82), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(83), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(84), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(85), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(86), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(87), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(88), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(89), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(90), "Review Comments", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(91), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(92), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(93), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(94), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(95), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(96), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(97), "Review Comments", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(98), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(99), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(100), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(101), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(102), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(103), "Review Comments", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(104), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(105), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(106), "Review Comments", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(107), "Review Comments", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(108), "Review Comments", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(109), "Review Comments", 4);
    }}
