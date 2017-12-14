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
    
    private HashMap<Integer, Screen> screens; 
    private HashMap<Integer, Film> films; 
    private HashMap<Integer, Projection> projections; 
    private HashMap<Integer, Customer> customers; 
    private HashMap<Integer, Booking> bookings; 
    //private HashMap<Integer, SeatAssignment> seatAssignments;
    
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
        //seatAssignments = new HashMap<Integer, SeatAssignment>();
        //payments = new HashMap<Integer, Payment>();
        //reviews = new HashMap<Integer, Review>();
        
        booker = new Booker(this);
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
     * get List of all Screens
     *
     * @return List screens
     */
    public List<Screen> getScreenList()
    {
        return new ArrayList<Screen> (screens.values());
    }
    
    /**
     * Get the screen with the specified id.
     * @param int screenId
     * @return Screen screen
     */
    public Screen getScreen(int screenId) 
    {
        return screens.get(screenId);
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
     * Get the film with the specified id.
     * @param int filmId
     * @return Film film
     */
    public Film getFilm(int filmId) 
    {
        return films.get(filmId);
    }
    
    /**
     * get List of all Films
     *
     * @return List films
     */
    public List<Film> getFilmList()
    {
        return new ArrayList<Film> (films.values());
    }
    
    /**
     * print all Films
     * @return void
     */
    public void printFilmList()
    {
        System.out.println("#############FILMS####################");
        Iterator it = films.values().iterator();
        while (it.hasNext())
        {
            Film film = (Film) (  it.next()  );
            System.out.println(film.toString());
        } 
        System.out.println("######################################"); 
        System.out.println();
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
     * Get the projection with the specified id.
     * @param int projectionId
     * @return Projection projection   
     */
    public Projection getProjection(int projectionId) 
    {
        return projections.get(projectionId);
    }
    
    // /**
     // * Get the list of seats ticketed for the projection with specified id.
     // * @param int projectionId
     // * @return List seats   
     // */
    // public List<Seat> getBookedSeats(Projection proj) 
    // {
        // return proj.getBookedSeats();
    // }
    
    
    
    /**
     * get List of all projections
     *
     * @return List projections
     */
    public List<Projection> getProjectionList()
    {
        return new ArrayList<Projection> (projections.values());
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
     * Get the customer with the specified id.
     * @param int customerId
     * @return Customer customer     
     */
    public Customer getCustomer(int customerId) 
    {
        return customers.get(customerId);
    }
    
    /**
     * get List of all Customers
     *
     * @return List customers
     */
    public List<Customer> getCustomerList()
    {
        return new ArrayList<Customer> (customers.values());
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
