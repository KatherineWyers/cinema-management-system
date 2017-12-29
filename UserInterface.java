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
        Calendar date1 = new GregorianCalendar(2017, 11, 1, 11, 00);
        Calendar date2 = new GregorianCalendar(2017, 11, 2, 15, 00);
        Calendar date3 = new GregorianCalendar(2017, 11, 3, 19, 00);
        Calendar date4 = new GregorianCalendar(2017, 11, 4, 22, 00);
        Calendar date5 = new GregorianCalendar(2017, 11, 5, 11, 00);
        Calendar date6 = new GregorianCalendar(2017, 11, 6, 15, 00);
        Calendar date7 = new GregorianCalendar(2017, 11, 7, 19, 00);
        Calendar date8 = new GregorianCalendar(2017, 11, 8, 22, 00);
        Calendar date9 = new GregorianCalendar(2017, 11, 9, 11, 00);
        Calendar date10 = new GregorianCalendar(2017, 11, 10, 15, 00);
        Calendar date11 = new GregorianCalendar(2017, 11, 11, 19, 00);
        Calendar date12 = new GregorianCalendar(2017, 11, 12, 22, 00);
        Calendar date13 = new GregorianCalendar(2018, 1, 13, 11, 00);
        Calendar date14 = new GregorianCalendar(2018, 1, 14, 15, 00);
        Calendar date15 = new GregorianCalendar(2018, 1, 15, 19, 00);
        Calendar date16 = new GregorianCalendar(2018, 1, 16, 22, 00);
        Calendar date17 = new GregorianCalendar(2018, 1, 17, 11, 00);
        Calendar date18 = new GregorianCalendar(2018, 1, 18, 15, 00);
        Calendar date19 = new GregorianCalendar(2018, 1, 19, 19, 00);
        Calendar date20 = new GregorianCalendar(2018, 1, 20, 22, 00);
       
        // Create Shows
       
        this.cinema.addShow(date1, screen1, film1, (float)12.50, (float)15.00);
        this.cinema.addShow(date2, screen2, film2, (float)9.50, (float)12.00);
        this.cinema.addShow(date3, screen3, film3, (float)10, (float)13.00);
        this.cinema.addShow(date4, screen4, film4, (float)11.50, (float)12.00);
        this.cinema.addShow(date5, screen5, film5, (float)12.50, (float)15.00);
        this.cinema.addShow(date6, screen6, film6, (float)12.50, (float)15.00);
        this.cinema.addShow(date7, screen1, film7, (float)12.50, (float)15.00);
        this.cinema.addShow(date8, screen2, film8, (float)12.50, (float)15.00);
        this.cinema.addShow(date9, screen3, film9, (float)12.50, (float)15.00);
        this.cinema.addShow(date10, screen4, film10, (float)12.50, (float)15.00);
        this.cinema.addShow(date11, screen5, film1, (float)12.50, (float)15.00);
        this.cinema.addShow(date12, screen6, film2, (float)9.50, (float)12.00);
        this.cinema.addShow(date13, screen1, film3, (float)12.50, (float)15.00);
        this.cinema.addShow(date14, screen2, film4, (float)9.50, (float)12.00);
        this.cinema.addShow(date15, screen3, film5, (float)12.50, (float)15.00);
        this.cinema.addShow(date16, screen4, film6, (float)9.50, (float)12.00);
        this.cinema.addShow(date17, screen5, film7, (float)12.50, (float)15.00);
        this.cinema.addShow(date18, screen6, film8, (float)9.50, (float)12.00);
        this.cinema.addShow(date19, screen1, film9, (float)12.50, (float)15.00);
        this.cinema.addShow(date20, screen2, film10, (float)9.50, (float)12.00);
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
        booker.addReservation(2, 1); 
        booker.addReservation(2, 2); 
        booker.addReservation(2, 4); 
        booker.addReservation(2, 5); 
        booker.addReservation(3, 7); 
        booker.addReservation(3, 8); 
        booker.addReservation(3, 9); 
        booker.addReservation(3, 10);  
        booker.addReservation(4, 2); 
        booker.addReservation(4, 3); 
        booker.addReservation(5, 8); 
        booker.addReservation(5, 9); 
        booker.addReservation(5, 10);
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show3, customer3);
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(2, 2); 
        booker.addReservation(2, 3); 
        booker.addReservation(2, 7); 
        booker.addReservation(2, 8); 
        booker.addReservation(2, 9); 
        booker.addReservation(3, 1); 
        booker.addReservation(3, 2); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show4, customer4);
        booker.addReservation(3, 1); 
        booker.addReservation(3, 2); 
        booker.addReservation(3, 3); 
        booker.addReservation(4, 1); 
        booker.addReservation(5, 8); 
        booker.addReservation(5, 9); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show5, customer5);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4);
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.addReservation(2, 2); 
        booker.addReservation(2, 3); 
        booker.addReservation(2, 6); 
        booker.addReservation(2, 7); 
        booker.addReservation(3, 5); 
        booker.addReservation(3, 6); 
        booker.addReservation(3, 7); 
        booker.addReservation(3, 8); 
        booker.addReservation(3, 9); 
        booker.addReservation(4, 2); 
        booker.addReservation(4, 3); 
        booker.addReservation(5, 6); 
        booker.addReservation(5, 7); 
        booker.addReservation(5, 8); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show6, customer6);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(2, 2); 
        booker.addReservation(2, 3); 
        booker.addReservation(2, 4); 
        booker.addReservation(2, 5); 
        booker.addReservation(2, 8); 
        booker.addReservation(2, 9); 
        booker.addReservation(2, 10); 
        booker.addReservation(3, 1); 
        booker.addReservation(3, 6); 
        booker.addReservation(3, 7); 
        booker.addReservation(3, 8); 
        booker.addReservation(4, 3); 
        booker.addReservation(4, 4); 
        booker.addReservation(5, 1); 
        booker.addReservation(5, 2); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show7, customer7);
        booker.addReservation(2, 1); 
        booker.addReservation(2, 2); 
        booker.addReservation(2, 3); 
        booker.addReservation(3, 5); 
        booker.addReservation(3, 6); 
        booker.addReservation(4, 2); 
        booker.addReservation(4, 3); 
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
        booker.addReservation(2, 2); 
        booker.addReservation(2, 3); 
        booker.addReservation(2, 4); 
        booker.addReservation(2, 8); 
        booker.addReservation(2, 9); 
        booker.addReservation(2, 10); 
        booker.addReservation(3, 1); 
        booker.addReservation(3, 2); 
        booker.addReservation(3, 4); 
        booker.addReservation(3, 5); 
        booker.addReservation(3, 6); 
        booker.addReservation(3, 7); 
        booker.addReservation(4, 1); 
        booker.addReservation(4, 2); 
        booker.addReservation(4, 3); 
        booker.addReservation(4, 4); 
        booker.addReservation(4, 9); 
        booker.addReservation(4, 10); 
        booker.addReservation(5, 2); 
        booker.addReservation(5, 3); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show9, customer9);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5);
        booker.addReservation(2, 4); 
        booker.addReservation(2, 5);
        booker.addReservation(2, 6);
        booker.addReservation(2, 1);
        booker.addReservation(2, 2);
        booker.addReservation(2, 8);
        booker.addReservation(2, 9);
        booker.addReservation(2, 10);
        booker.addReservation(3, 2);
        booker.addReservation(3, 3);
        booker.addReservation(3, 8);
        booker.addReservation(3, 9);
        booker.addReservation(4, 1);
        booker.addReservation(4, 2);
        booker.addReservation(4, 3);
        booker.addReservation(4, 5);
        booker.addReservation(4, 6);
        booker.addReservation(4, 7);
        booker.addReservation(5, 1);
        booker.addReservation(5, 3);
        booker.addReservation(5, 7);
        booker.addReservation(5, 8);
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show10, customer10);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 2); 
        booker.addReservation(2, 3); 
        booker.addReservation(2, 4); 
        booker.addReservation(2, 6); 
        booker.addReservation(2, 7); 
        booker.addReservation(2, 9); 
        booker.addReservation(2, 10); 
        booker.addReservation(3, 2); 
        booker.addReservation(3, 3); 
        booker.addReservation(3, 5); 
        booker.addReservation(3, 6); 
        booker.addReservation(3, 7); 
        booker.addReservation(4, 1); 
        booker.addReservation(4, 2); 
        booker.addReservation(4, 3); 
        booker.addReservation(4, 5); 
        booker.addReservation(4, 6); 
        booker.addReservation(4, 8); 
        booker.addReservation(4, 9); 
        booker.addReservation(5, 2); 
        booker.addReservation(5, 3); 
        booker.addReservation(5, 6); 
        booker.addReservation(5, 7); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show11, customer1);
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(2, 1);  
        booker.addReservation(2, 2);  
        booker.addReservation(2, 3);  
        booker.addReservation(2, 7);  
        booker.addReservation(2, 8);  
        booker.addReservation(3, 4);  
        booker.addReservation(3, 5);  
        booker.addReservation(3, 6);  
        booker.addReservation(3, 8);  
        booker.addReservation(3, 9);  
        booker.addReservation(3, 10);  
        booker.addReservation(4, 1);  
        booker.addReservation(4, 2);  
        booker.addReservation(4, 3);  
        booker.addReservation(5, 5);  
        booker.addReservation(5, 6);  
        booker.addReservation(5, 7);  
        booker.addReservation(5, 8);  
        booker.addReservation(5, 9);  
        booker.addReservation(5, 1);  
        booker.addReservation(5, 2); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show12, customer2);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(2, 1); 
        booker.addReservation(2, 2); 
        booker.addReservation(2, 3); 
        booker.addReservation(2, 6); 
        booker.addReservation(2, 7); 
        booker.addReservation(2, 8); 
        booker.addReservation(2, 9); 
        booker.addReservation(2, 10); 
        booker.addReservation(3, 4); 
        booker.addReservation(3, 5); 
        booker.addReservation(3, 6); 
        booker.addReservation(3, 7); 
        booker.addReservation(3, 8); 
        booker.addReservation(4, 2); 
        booker.addReservation(4, 3); 
        booker.addReservation(4, 5); 
        booker.addReservation(4, 6); 
        booker.addReservation(4, 7); 
        booker.addReservation(5, 4); 
        booker.addReservation(5, 5); 
        booker.addReservation(5, 6); 
        booker.addReservation(5, 10); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show13, customer3);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(2, 1);  
        booker.addReservation(2, 2);  
        booker.addReservation(2, 3);  
        booker.addReservation(2, 6);  
        booker.addReservation(2, 7);  
        booker.addReservation(2, 8);  
        booker.addReservation(2, 10);  
        booker.addReservation(3, 2);   
        booker.addReservation(3, 3);  
        booker.addReservation(3, 5);  
        booker.addReservation(3, 6);  
        booker.addReservation(3, 7);  
        booker.addReservation(4, 4); 
        booker.addReservation(4, 5); 
        booker.addReservation(4, 6); 
        booker.addReservation(4, 7); 
        booker.addReservation(4, 9); 
        booker.addReservation(4, 10); 
        booker.addReservation(5, 1);
        booker.addReservation(5, 2);
        booker.addReservation(5, 6);
        booker.addReservation(5, 7);
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show14, customer4);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(2, 2); 
        booker.addReservation(2, 3); 
        booker.addReservation(2, 4); 
        booker.addReservation(2, 6); 
        booker.addReservation(2, 7); 
        booker.addReservation(2, 8); 
        booker.addReservation(3, 3); 
        booker.addReservation(3, 4);
        booker.addReservation(3, 9);
        booker.addReservation(3, 10);
        booker.addReservation(4, 1);
        booker.addReservation(4, 2);
        booker.addReservation(4, 3);
        booker.addReservation(4, 4);
        booker.addReservation(4, 7);
        booker.addReservation(4, 8);
        booker.addReservation(5, 2);
        booker.addReservation(5, 3);
        booker.addReservation(5, 5);
        booker.addReservation(5, 6);
        booker.addReservation(5, 9);
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show15, customer5);
        booker.addReservation(1, 1);
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 8); 
        booker.addReservation(2, 2);  
        booker.addReservation(2, 3); 
        booker.addReservation(2, 6); 
        booker.addReservation(2, 7); 
        booker.addReservation(2, 8); 
        booker.addReservation(2, 9); 
        booker.addReservation(3, 1);
        booker.addReservation(3, 2);
        booker.addReservation(3, 4);
        booker.addReservation(3, 5);
        booker.addReservation(3, 8);
        booker.addReservation(3, 9);
        booker.addReservation(3, 10);
        booker.addReservation(4, 1);
        booker.addReservation(4, 2);
        booker.addReservation(4, 5);
        booker.addReservation(4, 6);
        booker.addReservation(5, 3);
        booker.addReservation(5, 4);
        booker.addReservation(5, 6);
        booker.addReservation(5, 7);
        booker.addReservation(5, 8);
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show16, customer6);
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7);  
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.addReservation(2, 2); 
        booker.addReservation(2, 4); 
        booker.addReservation(2, 5); 
        booker.addReservation(2, 6); 
        booker.addReservation(3, 2); 
        booker.addReservation(3, 3);
        booker.addReservation(3, 4);
        booker.addReservation(3, 5);
        booker.addReservation(4, 3);
        booker.addReservation(4, 4);
        booker.addReservation(4, 7);
        booker.addReservation(4, 8);
        booker.addReservation(4, 9);
        booker.addReservation(4, 10);
        booker.addReservation(5, 1);
        booker.addReservation(5, 2);
        booker.addReservation(5, 6);
        booker.addReservation(5, 7);
        booker.addReservation(5, 8);
        booker.addReservation(5, 9);
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show17, customer7);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6);
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1);  
        booker.addReservation(2, 2);  
        booker.addReservation(2, 3);  
        booker.addReservation(2, 4);  
        booker.addReservation(3, 2); 
        booker.addReservation(3, 3); 
        booker.addReservation(3, 6); 
        booker.addReservation(3, 7); 
        booker.addReservation(3, 8); 
        booker.addReservation(3, 9); 
        booker.addReservation(4, 3); 
        booker.addReservation(4, 4); 
        booker.addReservation(4, 6); 
        booker.addReservation(4, 7); 
        booker.addReservation(4, 8); 
        booker.addReservation(5, 1); 
        booker.addReservation(5, 2); 
        booker.addReservation(5, 3); 
        booker.addReservation(5, 8); 
        booker.addReservation(5, 9); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show18, customer8);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 6); 
        booker.addReservation(1, 7); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1); 
        booker.addReservation(2, 2); 
        booker.addReservation(2, 5); 
        booker.addReservation(2, 6); 
        booker.addReservation(2, 7); 
        booker.addReservation(3, 4); 
        booker.addReservation(3, 5); 
        booker.addReservation(3, 6); 
        booker.addReservation(3, 8); 
        booker.addReservation(3, 9); 
        booker.addReservation(3, 10); 
        booker.addReservation(4, 1); 
        booker.addReservation(4, 2); 
        booker.addReservation(4, 3); 
        booker.addReservation(4, 5); 
        booker.addReservation(4, 6); 
        booker.addReservation(5, 3); 
        booker.addReservation(5, 4); 
        booker.addReservation(5, 8); 
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show19, customer9);
        booker.addReservation(1, 1); 
        booker.addReservation(1, 2); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5);
        booker.addReservation(2, 2); 
        booker.addReservation(2, 3); 
        booker.addReservation(2, 6); 
        booker.addReservation(2, 7); 
        booker.addReservation(3, 1);  
        booker.addReservation(3, 3);  
        booker.addReservation(3, 4);  
        booker.addReservation(3, 8);  
        booker.addReservation(4, 1);  
        booker.addReservation(4, 2);  
        booker.addReservation(4, 3);  
        booker.addReservation(5, 4);  
        booker.addReservation(5, 5);  
        booker.addReservation(5, 7);  
        booker.addReservation(5, 8);  
        booker.addReservation(5, 10);  
        booker.finalizeCardPayment("REF:12345657");
        
        booker = this.cinema.getNewBooker(show20, customer10);
        booker.addReservation(1, 2); 
        booker.addReservation(1, 3); 
        booker.addReservation(1, 4); 
        booker.addReservation(1, 5); 
        booker.addReservation(1, 8); 
        booker.addReservation(1, 9); 
        booker.addReservation(1, 10); 
        booker.addReservation(2, 1);  
        booker.addReservation(2, 2);  
        booker.addReservation(2, 5);  
        booker.addReservation(2, 6);  
        booker.addReservation(3, 2);  
        booker.addReservation(3, 3);  
        booker.addReservation(3, 7);  
        booker.addReservation(3, 8);  
        booker.addReservation(3, 9);  
        booker.addReservation(4, 3);  
        booker.addReservation(4, 4);  
        booker.addReservation(4, 7);  
        booker.addReservation(5, 1);  
        booker.addReservation(5, 2);  
        booker.addReservation(5, 8);  
        booker.addReservation(5, 9); 
        booker.finalizeCardPayment("REF:12345657");
        
        // Add 100 reviews
        this.cinema.addReview(this.cinema.getTicketList().get(0), "Rubbish", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(1), "Boring", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(2), "Too long", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(4), "Not bad. ", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(5), "It was ok", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(6), "I liked the dinosaurs", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(15), "Loads of action", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(16), "Awesome", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(22), "Brilliant", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(23), "Best movie ever", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(24), "Lame", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(25), "Nonsense", 1);        
        this.cinema.addReview(this.cinema.getTicketList().get(27), "Too complicated", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(28), "Worst movie I've see", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(36), "For the fans", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(37), "You'll laugh!", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(38), "Great spaceships", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(39), "Going again", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(41), "My favourite movie", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(43), "So good!", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(50), "Rubbish", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(52), "Boring", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(55), "Too long", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(57), "Not bad. ", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(58), "It was ok", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(59), "I liked the dinosaurs", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(60), "Loads of action", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(63), "Awesome", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(64), "Brilliant", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(65), "Best movie ever", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(70), "Lame", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(71), "Nonsense", 1);        
        this.cinema.addReview(this.cinema.getTicketList().get(74), "Too complicated", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(76), "Worst movie I've see", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(79), "For the fans", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(80), "You'll laugh!", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(83), "Great spaceships", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(84), "Going again", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(85), "My favourite movie", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(99), "So good!", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(105), "Lame", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(107), "Nonsense", 1);        
        this.cinema.addReview(this.cinema.getTicketList().get(116), "Too complicated", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(118), "Worst movie I've see", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(119), "For the fans", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(135), "You'll laugh!", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(136), "Great spaceships", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(139), "Going again", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(140), "My favourite movie", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(142), "So good!", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(143), "Rubbish", 1);
        this.cinema.addReview(this.cinema.getTicketList().get(146), "Boring", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(148), "Too long", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(149), "Not bad. ", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(160), "It was ok", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(165), "I liked the dinosaurs", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(166), "Loads of action", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(170), "Awesome", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(172), "Brilliant", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(175), "Best movie ever", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(176), "Lame", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(178), "Nonsense", 1);        
        this.cinema.addReview(this.cinema.getTicketList().get(179), "Too complicated", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(190), "Worst movie I've see", 2);
        this.cinema.addReview(this.cinema.getTicketList().get(196), "For the fans", 4);
        this.cinema.addReview(this.cinema.getTicketList().get(200), "You'll laugh!", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(212), "Great spaceships", 3);
        this.cinema.addReview(this.cinema.getTicketList().get(215), "Going again", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(216), "My favourite movie", 5);
        this.cinema.addReview(this.cinema.getTicketList().get(217), "So good!", 5);
    }
}
