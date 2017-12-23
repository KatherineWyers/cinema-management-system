import java.util.*;
/**
 * Implementation of the Odeon Cinema
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
     * @return Booker booker
     */
    public Booker getNewBooker(Show show, Customer customer)
    {
        booker = new Booker(this, show, customer);
        return this.booker;
    }
    
    /**
     * getNewTransferer
     * @return Transferer transferer
     */
    public Transferer getNewTransferer(Show show, Ticket ticket)
    {
        transferer = new Transferer(this, show, ticket);
        return this.transferer;
    }
    
    /**
     * Get next unused screen id
     *
     * @return int nextScreenId
     */
    public int getNextScreenId()
    {
        return NEXT_UNUSED_SCREEN_ID++;
    }
    
    /**
     * Add new screen
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
     * Get a list of screens
     * @return List screens
     */
    public List<Screen> getScreenList()
    {
        return new ArrayList<Screen> (this.screens.values());
    }
    
    /**
     * Get next unused film id
     *
     * @return int nextFilmId
     */
    public int getNextFilmId()
    {
        return NEXT_UNUSED_FILM_ID++;
    }

    /**
     * Add new film without subtitles
     *
     * @param  String title 
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
     * Add new film with subtitles
     *
     * @param  String title 
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
     * Get a list of films
     * @return List films
     */
    public List<Film> getFilmList()
    {
        return new ArrayList<Film> (this.films.values());
    }
    
    /**
     * Get next unused screen id
     *
     * @return int nextScreenId
     */
    public int getNextShowId()
    {
        return NEXT_UNUSED_PROJECTION_ID++;
    }
    
    /**
     * Check whether the screen or the film is in use at a given time.  
     * @param Date date
     * @param String slot
     * @param int screenId
     * @param int filmId
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
     * Add new show
     *
     * @param  Date date 
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
     * Get a list of shows
     * @return List shows
     */
    public List<Show> getShowList()
    {
        return new ArrayList<Show> (this.shows.values());
    }
    
    /**
     * Get next unused customer id
     *
     * @return int nextCustomerId
     */
    public int getNextCustomerId()
    {
        return NEXT_UNUSED_CUSTOMER_ID++;
    }

    /**
     * Add new customer without name
     *
     * @param  String name 
     * @return void
     */
    public void addCustomer()
    {
        Customer customer = new Customer(getNextCustomerId());
        this.customers.put(customer.getId(), customer);
    }

    /**
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
     * Add new customer with name
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
     * Get customer by id
     *
     * @param  int customerId 
     * @return void
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
     * @param int customerId
     * @return boolean
     */
    public boolean isValidCustomerId(int customerId)
    {
        return customers.containsKey(customerId);
    }

    /**
     * Get a list of customers
     * @return List customers
     */
    public List<Customer> getCustomerList()
    {
        return new ArrayList<Customer> (this.customers.values());
    }
    
    /**
     * Get next unused ticket id
     *
     * @return int nextTicketId
     */
    public int getNextTicketId()
    {
        return NEXT_UNUSED_TICKET_ID++;
    }
    
    /**
     * Get Map of all Tickets
     * @return Map tickets
     */
    public Map<Integer, Ticket> getTickets()
    {
        return this.tickets;
    }

    /**
     * Get a list of tickets
     * @return List tickets
     */
    public List<Ticket> getTicketList()
    {
        return new ArrayList<Ticket> (this.tickets.values());
    }

    /**
     * Get a list of tickets for the booking
     * @param Booking booking
     * @return List tickets
     */
    public List<Ticket> getTicketList(Booking booking)
    {
        return new ArrayList<Ticket> (this.getTickets(booking).values());
    }
    
    /**
     * Get Map of all Tickets for the show
     * @param Show p
     * @return Map tickets
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
     * Get Map of all Tickets for the booking
     * @param Booking b
     * @return Map tickets
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
     * Add new add ticket
     *
     * @param  Ticket ticket
     * @return void
     */
    public void addTicket(Ticket ticket)
    {
        this.tickets.put(ticket.getId(), ticket);
    }
    
    /**
     * Get next unused booking id
     *
     * @return int nextBookingId
     */
    public int getNextBookingId()
    {
        return NEXT_UNUSED_BOOKING_ID++;
    }
    
    /**
     * Add new Booking
     * @param Booking booking
     * @return void
     */
    public void addBooking(Booking booking)
    {
        this.bookings.put(booking.getId(), booking);
    }

    /**
     * Get a list of bookings
     * @return List bookings
     */
    public List<Booking> getBookingList()
    {
        return new ArrayList<Booking> (this.bookings.values());
    }
    
    /**
     * Add new Review
     * The review cannot be overwritten. This method first 
     * checks to see if the review is null. If the review 
     * is not null, it does not log the review. 
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
     * Get Map of all Reivews
     * @return Map reviews
     */
    public Map<Integer, Review> getReviews()
    {
        return this.reviews;
    }
    
    /**
     * Get Map of all Bookings
     * @return Map bookings
     */
    public Map<Integer, Booking> getBookings()
    {
        return this.bookings;
    }
    
    /**
     * Get next unused payment id
     *
     * @return int nextPaymentId
     */
    public int getNextPaymentId()
    {
        return NEXT_UNUSED_PAYMENT_ID++;
    }
    
    /**
     * Add new Payment
     * @param Payment payment
     * @return void
     */
    public void addPayment(Payment payment)
    {
        this.payments.put(payment.getId(), payment);
    }
    
    /**
     * Get Map of all Cash Payments
     * @return Map cashPayments
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
     * Get Map of all Card Payments
     * @return Map cardPayments
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
     * printSeatingGrid
     * @param boolean[][] seatingGrid
     * @return void
     */
    public void printSeatingGrid(boolean[][] seatingGrid)
    {
        System.out.println("");
        System.out.println("###########SEATING GRID##########");
        System.out.println("");
        System.out.println("         [ S C R E E N ]        ");
        System.out.println("");
        
        for (int i = 0;i<seatingGrid.length;i++)
        {   
            String rowLetter;
            rowLetter = this.convertToRowLetter(i+1);
            
            // rowLetter with 1 column white space as padding
            System.out.print(rowLetter + " ");
            
            for (int j = 0; j < seatingGrid[i].length;j++)
            {
                System.out.print(seatingGrid[i][j] ? "[X]" : "[_]");
            }
            // end of the row
            System.out.println("");
        }
        // seat numbers
        System.out.println("   1  2  3  4  5  6  7  8  9 10 ");
        System.out.println("#################################");
        System.out.println("");
    }
    
    /**
     * Convert row int to row letter
     * @param int row at int
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
     * Convert row letter to row int
     * Letters outside A to E return -1
     * @param String
     * @return int row int
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
