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
    
    private Map<Integer, Screen> screens; 
    private Map<Integer, Film> films; 
    private Map<Integer, Projection> projections; 
    private Map<Integer, Customer> customers; 
    private Map<Integer, Booking> bookings; 
    private Map<Integer, Ticket> tickets;
    private Booker booker;
    
    /**
     * Constructor for objects of class Cinema
     */
    public Cinema()
    {
        screens = new HashMap<Integer, Screen>();
        films = new HashMap<Integer, Film>();
        projections = new HashMap<Integer, Projection>();
        customers = new HashMap<Integer, Customer>();
        bookings = new HashMap<Integer, Booking>();
        tickets = new HashMap<Integer, Ticket>();
        //payments = new HashMap<Integer, Payment>();
        //reviews = new HashMap<Integer, Review>();
    }
    
    /**
     * getNewBooker
     * @return void
     */
    public Booker getNewBooker(Projection projection, Customer customer)
    {
        booker = new Booker(this, projection, customer);
        return this.booker;
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
    public int getNextProjectionId()
    {
        return NEXT_UNUSED_PROJECTION_ID++;
    }
    
    /**
     * Check whether the screen or the film is in use at a given time.  
     * @param String date
     * @param String slot
     * @param int screenId
     * @param int filmId
     * @return boolean
     */
    public boolean isValidProjection(String date, String slot, Screen screen, Film film)
    {
        Iterator it = projections.values().iterator();
        while (it.hasNext())
        {
            Projection proj = (Projection) (  it.next()  );
            // Check if there is already a projection in that screen at that time
            if(proj.getScreen() == screen&&proj.getDate() == date&&proj.getSlot().equals(slot))
            {
                return false;
            }
            
            // Check if that film has already been scheduled to be screened at that time    
            if(proj.getFilm() == film&&proj.getDate() == date&&proj.getSlot().equals(slot))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Add new projection
     *
     * @param  String date 
     * @param String slot
     * @param int screenId
     * @param int filmId
     * @param float priceRegular
     * @param float priceVip
     * @return void
     */
    public void addProjection(String date, String slot, Screen screen, Film film, float priceRegular, float priceVip)
    {
        if(!isValidProjection(date, slot, screen, film))
        {
            System.out.println("The Projection could not be created. The Screen or the Film is already being used at that time");
            return;
        }
        Projection projection = new Projection(getNextProjectionId(), date, slot, screen, film, priceRegular, priceVip);
        this.projections.put(projection.getId(), projection);
    }

    /**
     * Get a list of projections
     * @return List projections
     */
    public List<Projection> getProjectionList()
    {
        return new ArrayList<Projection> (this.projections.values());
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
     * Get Map of all Tickets for the projection
     * @param Projection p
     * @return Map tickets
     */
    public Map<Integer, Ticket> getTickets(Projection projection)
    {
        Map<Integer, Ticket> projectionTickets = new HashMap<Integer, Ticket>();
        Iterator it = this.tickets.values().iterator();
        
        while (it.hasNext())
        {
            Ticket ticket = (Ticket) (  it.next()  );
            // Check if there is already a projection in that screen at that time
            if(ticket.getProjection() == projection)
            {
                projectionTickets.put(ticket.getId(), ticket);
            }
        }
        return projectionTickets;
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
            // Check if there is already a projection in that screen at that time
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
     * Get Map of all Bookings
     * @return Map bookings
     */
    public Map<Integer, Booking> getBookings()
    {
        return this.bookings;
    }
    
    /**
     * getSeatingGrid
     * @return boolean[][] seatingGrid
     */
    public boolean[][] getSeatingGrid(Projection projection)
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
        Iterator it = this.getTickets(projection).values().iterator();
        
        while (it.hasNext())
        {
            Ticket ticket = (Ticket) (  it.next()  );
            // Check if there is already a projection in that screen at that time
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
        System.out.println("###########SEATING GRID##########");
        System.out.println("");
        System.out.println("         [ S C R E E N ]        ");
        System.out.println("");
        
        for (int i = 0;i<seatingGrid.length;i++)
        {   
            String rowLetter;
            rowLetter = this.convertToRowLetter(i);
            
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
            case 0: 
                rowLetter = "A";
                break;
           case 1: 
                rowLetter = "B";
                break;
           case 2:
                rowLetter = "C";
                break;
           case 3:
                rowLetter = "D";
                break;
           case 4:
                rowLetter = "E";
                break;
           default:
                rowLetter = " ";
        }
        return rowLetter;
    }
}
