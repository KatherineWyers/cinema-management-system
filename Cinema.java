import java.util.*;
/**
 * Cinema Class
 * 
 * The main implementation class for the system.
 * The Cinema class stores all the objects for the 
 * application
 *
 * @author Katherine Wyers
 * @version 1.0
 */
public class Cinema
{
    private static int NEXT_UNUSED_SCREEN_ID = 1;
    private static int NEXT_UNUSED_FILM_ID = 1;
    private static int NEXT_UNUSED_PROJECTION_ID = 1;
    private static int NEXT_UNUSED_CUSTOMER_ID = 1;
    private static int NEXT_UNUSED_BOOKING_ID = 1;
    private static int NEXT_UNUSED_SEAT_ASSIGNMENT_ID = 1;
    private static int NEXT_UNUSED_TICKET_ID = 1;
    private static int NEXT_UNUSED_PAYMENT_ID = 1;
    
    private Map<Integer, Screen> screens; 
    private Map<Integer, Film> films; 
    private Map<Integer, Show> shows; 
    private Map<Integer, Customer> customers; 
    private Map<Integer, Booking> bookings; 
    private Map<Integer, Ticket> tickets;
    private Map<Integer, Payment> payments;
    private Map<Integer, Review> reviews;
    private Booker booker;
    private Transferer transferer;
    
    /**
     * Constructor for objects of class Cinema
     */
    public Cinema()
    {
        screens = new HashMap<Integer, Screen>();
        films = new HashMap<Integer, Film>();
        shows = new HashMap<Integer, Show>();
        customers = new HashMap<Integer, Customer>();
        bookings = new HashMap<Integer, Booking>();
        tickets = new HashMap<Integer, Ticket>();
        payments = new HashMap<Integer, Payment>();
        reviews = new HashMap<Integer, Review>();
    }
    
    /**
     * getNewBooker
     * 
     * Create a new Booker instance. This is called 
     * whenever the booking process is started. It
     * replaces any previous Booker object
     * 
     * @param Show show
     * @param Customer customer
     * @return Booker booker
     */
    public Booker getNewBooker(Show show, Customer customer)
    {
        booker = new Booker(this, show, customer);
        return this.booker;
    }
    
    /**
     * getNewTransferer
     * 
     * Create a new Transferer instance. This is called 
     * whenever the ticket-transfer process is started. It
     * replaces any previous Transfer object
     *
     *@param Show show
     *@param Ticket ticket
     * @return Transferer transferer
     */
    public Transferer getNewTransferer(Show show, Ticket ticket)
    {
        transferer = new Transferer(this, show, ticket);
        return this.transferer;
    }
    
    /**
     * getNextScreenId
     * 
     * Get the unused incremented screen id
     *
     * @return int nextScreenId
     */
    public int getNextScreenId()
    {
        return NEXT_UNUSED_SCREEN_ID++;
    }
    
    /**
     * addScreen
     * 
     * Add a new screen to the cinema
     *
     * @param  String title 
     * @return void
     */
    public void addScreen(String title)
    {
        Screen screen = new Screen(getNextScreenId(), title);
        this.screens.put(screen.getId(), screen);
    }

    /**
     * getScreenList
     * 
     * Get a list of the screens at the cinema
     * 
     * @return List screens
     */
    public List<Screen> getScreenList()
    {
        return new ArrayList<Screen> (this.screens.values());
    }
    
    /**
     * getNextFilmId
     * 
     * Get the unused incremented film id
     *
     * @return int nextFilmId
     */
    public int getNextFilmId()
    {
        return NEXT_UNUSED_FILM_ID++;
    }

    /**
     * addFilm
     * 
     * Add new film without subtitles
     *
     * @param  String title 
     * @param int year
     * @param String director
     * @param String language
     * @return void
     */
    public void addFilm(String title, int year, String director, String language)
    {
        Film film = new Film(getNextFilmId(), title, year, director, language);
        this.films.put(film.getId(), film);
    }

    /**
     * addFilm
     * 
     * Add new film with subtitles
     *
     * @param  String title
     * @param int year
     * @param String director
     * @param String language
     * @param String subtitles
     * @return void
     */
    public void addFilm(String title, int year, String director, String language, String subtitles)
    {
        Film film = new Film(getNextFilmId(), title, year, director, language, subtitles);
        this.films.put(film.getId(), film);
    }

    /**
     * getFilmList
     * 
     * Get a list of the films that have 
     * been added at the cinema
     * 
     * @return List films
     */
    public List<Film> getFilmList()
    {
        return new ArrayList<Film> (this.films.values());
    }
    
    /**
     * getFilms
     * 
     * Get a hashmap of all films, with 
     * FilmId as the hashmap key
     * 
     * @return Map films
     */
    public Map getFilms()
    {
        return films;
    }
    
    /**
     * getNextShowId
     * 
     * Get the unused incremented show id
     *
     * @return int nextShowId
     */
    public int getNextShowId()
    {
        return NEXT_UNUSED_PROJECTION_ID++;
    }
    
    /**
     * isValidShow
     * 
     * Check whether the screen or the film is already in use at a given time
     * If either are in use, return false
     * 
     * @param Calendar date
     * @param Screen screen
     * @param Film film
     * @return boolean
     */
    public boolean isValidShow(Calendar date, Screen screen, Film film)
    {
        Iterator it = this.shows.values().iterator();
        while (it.hasNext())
        {
            Show show = (Show) (  it.next()  );
            // Check if there is already a show in that screen at that time
            if(show.getScreen() == screen&&show.getDate().equals(date))
            {
                return false;
            }
            
            // Check if that film has already been scheduled to be screened at that time    
            if(show.getFilm() == film&&show.getDate().equals(date))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * addShow
     * 
     * Add a new film-screening to the cinema schedule
     * Call isValidShow to confirm that both the film 
     * and the screen are available
     *
     * @param  Calendar date 
     * @param Screen screen
     * @param Film film
     * @param float priceRegular
     * @param float priceVip
     * @return void
     */
    public void addShow(Calendar date, Screen screen, Film film, float priceRegular, float priceVip)
    {
        if(!isValidShow(date, screen, film))
        {
            System.out.println("The Show could not be created. The Screen or the Film is already being used at that time");
            return;
        }
        Show show = new Show(getNextShowId(), date, screen, film, priceRegular, priceVip);
        this.shows.put(show.getId(), show);
    }

    /**
     * getShowList
     * 
     * Get a list of shows that are scheduled at 
     * the cinema
     * 
     * @return List shows
     */
    public List<Show> getShowList()
    {
        return new ArrayList<Show> (this.shows.values());
    }
    
    /**
     * getNextCustomerId
     * 
     * Get the unused incremented customer id
     *
     * @return int nextCustomerId
     */
    public int getNextCustomerId()
    {
        return NEXT_UNUSED_CUSTOMER_ID++;
    }

    /**
     * addCustomer
     * 
     * Add new customer without name
     *
     * @return void
     */
    public void addCustomer()
    {
        Customer customer = new Customer(getNextCustomerId());
        this.customers.put(customer.getId(), customer);
    }

    /**
     * addCustomer
     * 
     * Add new customer with name
     *
     * @param  String name 
     * @return void
     */
    public void addCustomer(String name)
    {
        Customer customer = new Customer(getNextCustomerId(), name);
        this.customers.put(customer.getId(), customer);
    }

    /**
     * getNewCustomer
     * 
     * Add a new customer with name and 
     * return the new Customer 
     *
     * @param  String name 
     * @return Customer
     */
    public Customer getNewCustomer(String name)
    {
        Customer customer = new Customer(getNextCustomerId(), name);
        this.customers.put(customer.getId(), customer);
        return customer;
    }

    /**
     * getCustomer
     * 
     * Get customer by their id number
     * Throw an IllegalArgumentException if 
     * the customer id is not recognized
     *
     * @param  int customerId 
     * @return Customer
     */
    public Customer getCustomer(int customerId) throws IllegalArgumentException
    {  
        if(customers.get(customerId)==null)
        {
            throw new IllegalArgumentException("CustomerId not recognized");
        }
        return customers.get(customerId);
    }
    
    /**
     * isValidCustomerId
     * 
     * Check whether the customer id given is 
     * recognized
     * 
     * @param int customerId
     * @return boolean
     */
    public boolean isValidCustomerId(int customerId)
    {
        return customers.containsKey(customerId);
    }

    /**
     * getCustomerList
     * 
     * Get a list of all customers
     * 
     * @return List customers
     */
    public List<Customer> getCustomerList()
    {
        return new ArrayList<Customer> (this.customers.values());
    }
    
    /**
     * getNextTicketId
     * 
     * Get the unused incremented ticket id
     *
     * @return int nextTicketId
     */
    public int getNextTicketId()
    {
        return NEXT_UNUSED_TICKET_ID++;
    }
    
    /**
     * getTickets
     * 
     * Get a map of all Tickets
     * with TicketId as the hashmap key
     * 
     * @return Map tickets
     */
    public Map<Integer, Ticket> getTickets()
    {
        return this.tickets;
    }

    /**
     * getTicket
     * 
     * Get ticket by id number
     * Throw an IllegalArgumentException if 
     * the ticket id is not recognized
     *
     * @param  int ticketId 
     * @return Ticket
     */
    public Ticket getTicket(int ticketId) throws IllegalArgumentException
    {  
        if(tickets.get(ticketId)==null)
        {
            throw new IllegalArgumentException("TicketId not recognized");
        }
        return tickets.get(ticketId);
    }

    /**
     * getTicketList
     * 
     * Get a list of all tickets
     * 
     * @return List tickets
     */
    public List<Ticket> getTicketList()
    {
        return new ArrayList<Ticket> (this.tickets.values());
    }

    /**
     * getTicketList
     * 
     * Get a list of all tickets for the 
     * booking
     * 
     * @param Booking booking
     * @return List tickets
     */
    public List<Ticket> getTicketList(Booking booking)
    {
        return new ArrayList<Ticket> (this.getTickets(booking).values());
    }
    
    /**
     * getTickets
     * 
     * Get Map of all Tickets for the show
     * with TicketId as the map-key
     * 
     * @param Show show
     * @return Map<Integer, Ticket> tickets
     */
    public Map<Integer, Ticket> getTickets(Show show)
    {
        Map<Integer, Ticket> showTickets = new HashMap<Integer, Ticket>();
        Iterator it = this.tickets.values().iterator();
        
        while (it.hasNext())
        {
            Ticket ticket = (Ticket) (  it.next()  );
            // Check if there is already a show in that screen at that time
            if(ticket.getShow() == show)
            {
                showTickets.put(ticket.getId(), ticket);
            }
        }
        return showTickets;
    }
    
    /**
     * getTickets
     * 
     * Get Map of all Tickets for the booking
     * with ticketId as the map-key
     * 
     * @param Booking booking
     * @return Map<Integer, Ticket> tickets
     */
    public Map<Integer, Ticket> getTickets(Booking booking)
    {
        Map<Integer, Ticket> bookingTickets = new HashMap<Integer, Ticket>();
        Iterator it = this.tickets.values().iterator();
        while (it.hasNext())
        {
            Ticket ticket = (Ticket) (  it.next()  );
            // Check if there is already a show in that screen at that time
            if(ticket.getBooking() == booking)
            {
                bookingTickets.put(ticket.getId(), ticket);
            }
        }
        return bookingTickets;
    }

    /**
     * addTicket
     * 
     * Add a ticket to the ticket-map
     *
     * @param  Ticket ticket
     * @return void
     */
    public void addTicket(Ticket ticket)
    {
        this.tickets.put(ticket.getId(), ticket);
    }
    
    /**
     * getNextBookingId
     * 
     * Get the unused incremented booking id
     *
     * @return int nextBookingId
     */
    public int getNextBookingId()
    {
        return NEXT_UNUSED_BOOKING_ID++;
    }
    
    /**
     * addBooking
     * 
     * Add new Booking
     * 
     * @param Booking booking
     * @return void
     */
    public void addBooking(Booking booking)
    {
        this.bookings.put(booking.getId(), booking);
    }

    /**
     * getBookingList
     * 
     * Get a list of all bookings
     * 
     * @return List<Booking> bookings
     */
    public List<Booking> getBookingList()
    {
        return new ArrayList<Booking> (this.bookings.values());
    }
    
    /**
     * addReview
     * 
     * Add new Review
     * The review cannot be overwritten. This method first 
     * checks to see if the review is null. If the review 
     * is not null, it does not log the review
     * 
     * @param Ticket ticket
     * @param String review
     * @param int rating
     * @return void
     */
    public void addReview(Ticket ticket, String review, int rating)
    {
        if(reviews.get(ticket.getId())==null)
        {
            Review r = new Review(ticket, review, rating);
            this.reviews.put(ticket.getId(), r);
        }
    }
    
    /**
     * getReviews
     * 
     * Get Map of all Reviews
     * with the review's TicketId as 
     * the hashmap key
     * 
     * @return Map<Integer, Review> reviews
     */
    public Map<Integer, Review> getReviews()
    {
        return this.reviews;
    }
    
    /**
     * getBookings
     * 
     * Get Map of all Bookings
     * with the bookingId as the 
     * hashmap key
     * 
     * @return Map<Integer, Booking> bookings
     */
    public Map<Integer, Booking> getBookings()
    {
        return this.bookings;
    }
    
    /**
     * getNextPaymentId
     * 
     * Get the unused incremented payment id
     *
     * @return int nextPaymentId
     */
    
    public int getNextPaymentId()
    {
        return NEXT_UNUSED_PAYMENT_ID++;
    }
    
    /**
     * addPayment
     * 
     * Add a new Payment. This can be 
     * either a standard Payment (Cash) or 
     * a CardPayment
     * 
     * @param Payment payment
     * @return void
     */
    public void addPayment(Payment payment)
    {
        this.payments.put(payment.getId(), payment);
    }
    
    /**
     * getCashPayments
     * 
     * Get Map of all Cash Payments
     * with the paymentId as the map-key
     * 
     * @return Map<Integer, Payment> cashPayments
     */
    public Map<Integer, Payment> getCashPayments()
    {
        Map<Integer, Payment> cashPayments = new HashMap<Integer, Payment>();
        Iterator it = this.payments.values().iterator();
        while (it.hasNext())
        {
            Payment payment = (Payment) (  it.next()  );
            
            if(!Referencable.class.isAssignableFrom(payment.getClass()))
            {
                cashPayments.put(payment.getId(), payment);
            }
        }
        return cashPayments;
    }
    
    /**
     * getCardPayments
     * 
     * Get Map of all Card Payments
     * with the paymentId as the map-key
     * 
     * @return Map<Integer, Payment> cardPayments
     */
    public Map<Integer, Payment> getCardPayments()
    {
        Map<Integer, Payment> cardPayments = new HashMap<Integer, Payment>();
        Iterator it = this.payments.values().iterator();
        while (it.hasNext())
        {
            Payment payment = (Payment) (  it.next()  );
            
            if(Referencable.class.isAssignableFrom(payment.getClass()))
            {
                cardPayments.put(payment.getId(), payment);
            }
        }
        return cardPayments;
    }
    
    
    /**
     * getSeatingGrid
     * 
     * Get the seating grid for the show
     * True represents a seat that has been 
     * booked. False represents a seat that 
     * is available. 
     * 
     * @param Show show
     * @return boolean[][] seatingGrid
     */
    public boolean[][] getSeatingGrid(Show show)
    {
        boolean seatingGrid[][] = new boolean[5][10];
        
        //Set whole grid to empty
        for(int i=0;i<5;i++)
        {
            for(int j=0;j<10;j++)
            {
                seatingGrid[i][j] = false;
            }
        }
        
        // Update grid with booked tickets
        Iterator it = this.getTickets(show).values().iterator();
        
        while (it.hasNext())
        {
            Ticket ticket = (Ticket) (  it.next()  );
            // Check if there is already a show in that screen at that time
            seatingGrid[ticket.getRow()-1][ticket.getNum()-1] = true;
        }
        return seatingGrid;
    }
    
    /**
     * convertToRowLetter
     * 
     * Convert row int to row letter
     * Convert int 1-5 to A-E. 
     * If int out of range, return string with single white space
     * 
     * @param int row
     * @return String rowLetter
     */
    public String convertToRowLetter(int rowInt)
    {
        String rowLetter;
        // convert rows to letters
        switch(rowInt){
            case 1: 
                rowLetter = "A";
                break;
           case 2: 
                rowLetter = "B";
                break;
           case 3:
                rowLetter = "C";
                break;
           case 4:
                rowLetter = "D";
                break;
           case 5:
                rowLetter = "E";
                break;
           default:
                rowLetter = " ";
        }
        return rowLetter;
    }
    
    /**
     * convertToRowNum
     * 
     * Convert row letter to row int
     * Letters outside A to E return -1
     * 
     * @param String
     * @return int row
     */
    public int convertToRowNum(String rowLetter)
    {
        int rowInt;
        // convert letters to rows
        switch(rowLetter){
           case "A": 
                rowInt = 1;
                break;
           case "a": 
                rowInt = 1;
                break;
           case "B": 
                rowInt = 2;
                break;
           case "b": 
                rowInt = 2;
                break;
           case "C":
                rowInt = 3;
                break;
           case "c":
                rowInt = 3;
                break;
           case "D":
                rowInt = 4;
                break;
           case "d":
                rowInt = 4;
                break;
           case "E":
                rowInt = 5;
                break;
           case "e":
                rowInt = 5;
                break;
           default: 
                rowInt = -1;
                break;
        }
        return rowInt;
    }
}
