import java.util.concurrent.TimeUnit;
import java.io.*;
import java.util.*;
/**
 * Write a description of class CommandLine here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cli extends UserInterface
{
    /**
     * Constructor for objects of class CommandLine
     */
    public Cli(){};
    
    /**
     * runMainApplication
     * Take pageId as input and navigate to the new page
     * @param pageId
     * @return void
     */
    public void run()
    {
        int pageId = 1;
        this.displaySplashScreen();
        this.addDelayInSeconds(5);
        this.clearScreen(); 
        
        while(true)
        { 
            pageId = this.displayPage(pageId);
            if(pageId == 6&&this.getUserInputYN("Are you sure you want to quit the application? (Y/N)").equals("Y"))
            {
                break;// Quit request received
            }
            this.clearScreen(); 
        }
        this.clearScreen(); 
        System.out.println("Application Quit");            
    }

    /**
     * displayPage
     * Take the pageId and display the page
     * @param int pageId
     * @return void
     */
    private int displayPage(int pageId)
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
            default:
                return 1;// Return to the FILMS INDEX hompepage
        }
    }    
    
    /**
     * displaySplashScreen
     * @return void
     */
    private void displaySplashScreen()
    {
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("---------------ODEON - Fanatical About Films---------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
    }
    
    /**
     * displayFilmsIndexPage()
     * @return int
     * 
     */
    public int displayFilmsIndexPage()
    {
        this.clearScreen();
        Map <Integer, Film> optionToFilm = new HashMap<Integer, Film>();
        this.pageHeader(true, 1, 1, "films", "FILMS INDEX");
        int option = this.printListWithOptions(20, this.cinema.getFilmList(), optionToFilm);
        int input = this.getUserInputInteger(option);// Set max input as highest option number
        if(input>19)
        {
            return this.displayShowFilmPage(optionToFilm.get(input));
        }
        return input;
    }
    
    /**
     * displayShowFilmPage()
     * @param Film film
     * @return int
     * 
     */
    public int displayShowFilmPage(Film film)
    {
        this.clearScreen();
        this.pageHeader(true, 1, 1, "films", "DISPLAY FILM DETAILS");
        System.out.println("Title:      " + film.getTitle());
        System.out.println("Year:       " + film.getYear());
        System.out.println("Director:   " + film.getDirector());
        System.out.println("Language:   " + film.getLanguage());
        System.out.println("Subtitles:  " + film.getSubtitles());
        int input = this.getUserInputInteger(19);// Set max input as highest option number
        if(input>19)
        {
             return 1;// Invalid selection. Return to filmsIndex
        }
        return input;
    }
   
    /**
     * displayAddFilmPage()
     * @return int
     * 
     */
    public int displayAddFilmPage()
    {
        this.clearScreen();
        this.pageHeader(true, 1, 2, "films", "ADD FILM");
        System.out.println("");
        System.out.println("[20, Create New Film]  [21, Cancel]");
        int input = this.getUserInputInteger(21);
        if(input==20)
        {
            return this.enterNewFilmDetails();
        }
        if(input==21)
        {
            return 1;// return to FilmsIndex
        }
        return input;
    }
    
    /**
     * enterNewFilmDetails
     * @return int
     */
    private int enterNewFilmDetails()
    {
        this.clearScreen(); 
        this.pageHeader(false, 1, 2, "films", "ADD FILM > Enter New Film Details");
        String title = this.getUserInputString(255, "Enter the title");
        int year = this.getUserInputInteger(2020, "Enter the year");
        String director = this.getUserInputString(20, "Enter the director");
        String language = this.getUserInputString(20, "Enter the language");
        String subtitles = this.getUserInputString(3, "Enter the subtitles (2-letter acronym or N/A)");
        if(this.getUserInputYN("Save Film? (Y/N)").equals("Y"))
        {
            this.cinema.addFilm(title, year, director, language, subtitles);
        };
        return 1;// return to the FilmsIndex  
    }
    
    /**
     * displayShowsIndexPage()
     * @return int
     * 
     */
    public int displayShowsIndexPage()
    {
        this.clearScreen();
        Map <Integer, Show> optionToShow = new HashMap<Integer, Show>();
        this.pageHeader(true, 2, 1, "shows", "SHOWS INDEX");
        int option = this.printListWithOptions(20, this.cinema.getShowList(), optionToShow);
        int input = this.getUserInputInteger(option);// Set max input as highest option number
        if(input>19)
        {
            return this.displayShowShowPage(optionToShow.get(input));
        }
        return input;
    }
    
    /**
     * displayShowShowPage()
     * @param Show show
     * @return int
     * 
     */
    public int displayShowShowPage(Show show)
    {
        this.clearScreen();
        this.pageHeader(true, 2, 1, "shows", "DISPLAY SHOW DETAILS");
        System.out.println("***Film Details***");
        System.out.println("Film:       " + show.getFilm().getTitle());
        System.out.println("Year:       " + show.getFilm().getYear());
        System.out.println("Director:   " + show.getFilm().getDirector());
        System.out.println("Language:   " + show.getFilm().getLanguage());
        System.out.println("Subtitles:  " + show.getFilm().getSubtitles());
        System.out.println(" ");
        System.out.println("***Schedule Details***");
        System.out.println("Screen:     " + show.getScreen().getTitle());
        System.out.println("Date & Time:" + show.getDateTime());
        this.cinema.printSeatingGrid(this.cinema.getSeatingGrid(show));
        System.out.println("Regular Tickets (Row A-D): $" + show.getPriceRegular());
        System.out.println("Vip Tickets (Row E):       $" + show.getPriceVip());
        int input = this.getUserInputInteger(19);// Set max input as highest option number
        if(input>19)
        {
             return 2;// Invalid selection. Return to SHOWS INDEX
        }
        return input;
    }
   
    /**
     * displayAddShowPage()
     * @return int
     */
    public int displayAddShowPage()
    {
        this.clearScreen();
        this.pageHeader(true, 2, 2, "shows", "ADD SHOW");
        System.out.println("");
        System.out.println("[20, Create New Show]  [21, Cancel]");
        int input = this.getUserInputInteger(21);
        if(input==20)
        {
            return this.enterNewShowDetails();
        }
        if(input==21)
        {
            return 2;// return to SHOWS INDEX
        }
        return input;
    }
    
    /**
     * enterNewShowDetails
     * @return int
     */
    private int enterNewShowDetails()
    {
        this.clearScreen(); 
        Map <Integer, Film> optionToFilm = new HashMap<Integer, Film>();
        Map <Integer, Screen> optionToScreen = new HashMap<Integer, Screen>();
        this.pageHeader(false, 2, 2, "shows", "ADD SHOW > Enter New Show Details");
        System.out.println("*** SELECT FILM ***");
        int option = this.printListWithOptions(20, this.cinema.getFilmList(), optionToFilm);
        Film film = optionToFilm.get(this.getUserInputInteger(option, "Enter Film selection:"));
        System.out.println("*** SELECT SCREEN ***");
        option = this.printListWithOptions(option, this.cinema.getScreenList(), optionToScreen);
        Screen screen = optionToScreen.get(this.getUserInputInteger(option, "Enter Screen selection:"));
        int dd = this.getUserInputIntegerRange(1, 31, "Enter Date. For example: 22");
        int mm = this.getUserInputIntegerRange(1, 12, "Enter Month. For example: 6");
        int yyyy = this.getUserInputIntegerRange(2015, 2050, "Enter Year. For example 2017");
        int hh = this.getUserInputIntegerRange(0, 23, "Enter Hour in 24-hour clock. For example 19");
        int ii = this.getUserInputIntegerRange(0, 59, "Enter Minute. For example 30");
        Calendar date = new GregorianCalendar(yyyy, mm-1, dd, hh, ii);
        float priceRegular = this.getUserInputFloat("Enter Price for Regular Tickets. For example 8.50");
        float priceVip = this.getUserInputFloat("Enter Price for VIP Tickets. For example 12.50");
        if(this.getUserInputYN("Save Film? (Y/N)").equals("Y"))
        {
            this.cinema.addShow(date, screen, film, priceRegular, priceVip);
        };
        return 2;// return to the SHOWS INDEX 
    }
    
    /**
     * displayCustomersIndexPage()
     * @return int
     * 
     */
    public int displayCustomersIndexPage()
    {
        this.clearScreen();
        Map <Integer, Customer> optionToCustomer = new HashMap<Integer, Customer>();
        this.pageHeader(true, 3, 1, "customers", "CUSTOMERS INDEX");
        int option = this.printListWithOptions(20, this.cinema.getCustomerList(), optionToCustomer);
        int input = this.getUserInputInteger(option);// Set max input as highest option number
        if(input>19)
        {
            return this.displayShowCustomerPage(optionToCustomer.get(input));
        }
        return input;
    }
    
    /**
     * displayShowCustomerPage()
     * @param Customer customer
     * @return int
     * 
     */
    public int displayShowCustomerPage(Customer customer)
    {
        this.clearScreen();
        this.pageHeader(true, 3, 1, "customers", "DISPLAY CUSTOMER DETAILS");
        System.out.println("CustomerID: " + customer.getId());
        System.out.println("Name:       " + customer.getName());
        int input = this.getUserInputInteger(19);// Set max input as highest option number
        if(input>19)
        {
             return 3;// Invalid selection. Return to CUSTOMERS INDEX
        }
        return input;
    }
    
    /**
     * displayBookingsIndexPage()
     * @return int
     * 
     */
    public int displayBookingsIndexPage()
    {
        this.clearScreen();
        Map <Integer, Booking> optionToBooking = new HashMap<Integer, Booking>();
        this.pageHeader(true, 4, 1, "bookings", "BOOKINGS INDEX");
        int option = this.printListWithOptions(20, this.cinema.getBookingList(), optionToBooking);
        int input = this.getUserInputInteger(option);// Set max input as highest option number
        if(input>19)
        {
            return this.displayShowBookingPage(optionToBooking.get(input));
        }
        return input;
    }
    
    /**
     * displayShowBookingPage()
     * @param Booking booking
     * @return int
     * 
     */
    public int displayShowBookingPage(Booking booking)
    {
        this.clearScreen();
        this.pageHeader(true, 4, 1, "bookings", "DISPLAY BOOKING DETAILS");
        System.out.println("***Customer Details***");
        System.out.println("CustomerID:       " + booking.getCustomer().getId());
        System.out.println("Customer Name:    " + booking.getCustomer().getName());
        System.out.println(" ");
        System.out.println("***Tickets***");
       for(Ticket ticket : this.cinema.getTicketList(booking))
       {
           System.out.println(ticket.toString());
           if(this.cinema.getReviews().get(ticket.getId())!=null)
           {
               System.out.println("Customer Review:       " + this.cinema.getReviews().get(ticket.getId()).getReview());
               System.out.println("Customer Rating (1-5): " + this.cinema.getReviews().get(ticket.getId()).getRating());
               System.out.println("");
           }
       }    
       int input = this.getUserInputInteger(19);// Set max input as highest option number
       if(input>19)
       {
           return 4;// Invalid selection. Return to SHOWS INDEX
       }
       return input;
    }
   
    /**
     * displayAddBookingPage()
     * @return int
     */
    public int displayAddBookingPage()
    {
        this.clearScreen();
        this.pageHeader(true, 4, 2, "bookings", "ADD BOOKING");
        System.out.println("");
        System.out.println("[20, Create New Customer]  [21, Existing Customer]  [22, Cancel]");
        int input = this.getUserInputInteger(22);
        if(input==20||input==21)
        {
            return this.enterNewBookingDetails(input);
        }
        if(input==21)
        {
            return 4;// return to BOOKINGS INDEX
        }
        return input;
    }

    /**
     * enterNewBookingDetails
     * @param int customerType (20 == new customer, 21 == existing customer)
     * @return int
     */
    private int enterNewBookingDetails(int input)
    {
        this.clearScreen(); 
        Map <Integer, Show> optionToShow = new HashMap<Integer, Show>();
        this.pageHeader(false, 4, 2, "bookings", "ADD BOOKING > Enter New Booking Details");
        Customer customer = new Customer (this.cinema.getNextCustomerId());// create blank customer
        if(input==20)
        {
            customer = this.cinema.getNewCustomer(this.getUserInputString(30, "Enter Customer Name"));
        }
        else
        {
            try
            {
                customer = this.cinema.getCustomer(this.getUserInputInteger(Integer.MAX_VALUE, "Enter Customer MemberCard ID"));
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                this.getUserInputString(1, "Press enter to return to bookings index");
                return 4;// return to the BOOKINGS INDEX
            }
        }
        System.out.println("*** SELECT SHOW ***");
        int option = this.printListWithOptions(20, this.cinema.getShowList(), optionToShow);
        Show show = optionToShow.get(this.getUserInputInteger(option, "Enter Show selection:"));
        
        Booker booker = this.cinema.getNewBooker(show, customer);
        this.selectSeatsAndFinalizeBooking(booker);// recursive function
        return 4;// return to the BOOKINGS INDEX 
    }   
    
    /**
     * selectSeatsAndFinalizeBooking
     * @param Booker booker
     * @return void
     */
    private int selectSeatsAndFinalizeBooking(Booker booker)
    {
        this.clearScreen();
        this.pageHeader(false, 4, 2, "bookings", "ADD BOOKING > Select Seats");
        this.cinema.printSeatingGrid(booker.getSeatingGrid());
        System.out.println("Regular Tickets (Row A-D): $" + booker.getShow().getPriceRegular());
        System.out.println("Vip Tickets (Row E):       $" + booker.getShow().getPriceVip());
        booker.printCurrentBookingDetails();
        System.out.println("[20, Add Seat]  [21, Remove Seat] [22, Cash Payment ] [23, CreditCard Payment] [24, Cancel]");
        int input = this.getUserInputIntegerRange(20,24,"Please make a selection"); 
        boolean completed = false;
        switch(input)
        {
            case 20:
                this.addSeatToBooking(booker);
                break;
            case 21:
                this.removeSeatFromBooking(booker);
                break;
            case 22:
                this.processCashPayment(booker);
                completed = true;
                break;
            case 23:
                this.processCardPayment(booker);
                completed = true;
                break;
            default:
                completed = true;// Cancel booking
                break;
        }
        if(!completed) 
        {
           this.selectSeatsAndFinalizeBooking(booker); // call the function recursively
        }
        return 4;//Redirect to BOOKINGS INDEX
    }
    
    /**
     * addSeatToBooking
     * @param Booker booker
     * @return void
     */
    private void addSeatToBooking(Booker booker)
    {
        int seatRow = this.cinema.convertToRowNum(this.getUserInputString(1, "Enter row letter (A to E)"));
        int seatNum = this.getUserInputIntegerRange(1,10,"Enter seat number");
        booker.addReservation(seatRow, seatNum);
    }
    
    /**
     * removeSeatFromBooking
     * @param Booker booker
     * @return void
     */
    private void removeSeatFromBooking(Booker booker)
    {
        int seatRow = this.cinema.convertToRowNum(this.getUserInputString(1, "Enter row letter (A to E)"));
        int seatNum = this.getUserInputIntegerRange(1,10,"Enter seat number");
        booker.removeReservation(seatRow, seatNum);
    }
    
    /**
     * processCashPayment
     * @param Booker booker
     * @return void
     */
    private void processCashPayment(Booker booker)
    {
        if(this.getUserInputYN("Proceed with Cash Payment? (Y/N)").equals("Y"))
        {
            booker.finalizeCashPayment();
        };
    }
    
    /**
     * processCardPayment
     * @param Booker booker
     * @return void
     */
    private void processCardPayment(Booker booker)
    {
        if(this.getUserInputYN("Proceed with CreditCard Payment? (Y/N)").equals("Y"))
        {
            booker.finalizeCardPayment(this.getUserInputString(8, "Enter CreditCard Payment Reference Number"));
        };
    }
    
    /**
     * displayMoveTicketPage()
     * @return int pageId
     */
    public int displayMoveTicketPage()
    {
        this.clearScreen();
        this.pageHeader(true, 5, 3, "bookings", "MOVE TICKET");
        System.out.println("");
        System.out.println("[20, Reschedule Ticket]  [21, Cancel]");
        int input = this.getUserInputInteger(21, "Please make a selection");
        if(input==20)
        {
            return this.enterTicketIdToMove();
        }
        if(input==21)
        {
            return 4;// return to BOOKINGS INDEX
        }
        return input;
    }

    /**
     * enterTicketIdToMove
     * @return int
     */
    private int enterTicketIdToMove()
    {
        this.clearScreen(); 
        int input;
        this.pageHeader(false, 4, 2, "bookings", "MOVE TICKET > Select Ticket To Move");
        while(true)
        {
            input = this.getUserInputIntegerRange(0, Integer.MAX_VALUE, "Enter TicketId or Enter 0 to Cancel:");
            if(input == 0)
            {
                return 4;// Return to BOOKINGS INDEX
            }
            Ticket ticket = this.cinema.getTickets().get(input);
            if(ticket!=null)
            {
                return this.selectShowToMove(ticket);
            }
            System.out.println("TicketId not recognized");
        }
    }   

    /**
     * enterTicketIdToMove
     * @param Ticket ticket
     * @return int
     */
    private int selectShowToMove(Ticket ticket)
    {
        this.clearScreen(); 
        Map <Integer, Show> optionToShow = new HashMap<Integer, Show>();
        this.pageHeader(false, 4, 2, "bookings", "MOVE TICKET > Select New Show");
        System.out.println("*** SELECT SHOW ***");
        int option = this.printListWithOptions(20, this.cinema.getShowList(), optionToShow);
        Show show = optionToShow.get(this.getUserInputInteger(option, "Enter Show selection:"));
        
        Transferer transferer = this.cinema.getNewTransferer(show, ticket);
        this.selectSeatsAndFinalizeTransfer(transferer);// recursive function
        return 4;// return to the BOOKINGS INDEX 
    }  
    
    /**
     * selectSeatsAndFinalizeBooking
     * @param Booker booker
     * @return void
     */
    private int selectSeatsAndFinalizeTransfer(Transferer transferer)
    {
        this.clearScreen();
        this.pageHeader(false, 4, 2, "bookings", "MOVE TICKET > Tranfer ticket to a new Show and/or Seat");
        this.cinema.printSeatingGrid(transferer.getSeatingGridIgnoreTicket(transferer.getTicket()));
        System.out.println("***Transfer Surcharge***");
        System.out.println("Regular Tickets (Row A-D): $" + transferer.getTicketTransferSurcharge("regular"));
        System.out.println("Vip Tickets (Row E):       $" + transferer.getTicketTransferSurcharge("vip"));
        transferer.printCurrentTransferDetails();
        System.out.println("[20, Select Seat]  [21, Process Transfer] [22, Cancel]");
        int input = this.getUserInputIntegerRange(20,22,"Please make a selection"); 
        if(input == 21&&transferer.getSurcharge()>0)
        {
            System.out.println("[23, Cash Payment]  [24, Card Payment] [25, Cancel]");
            input = this.getUserInputIntegerRange(23,25,"Please make a selection"); 
        }
        boolean completed = false;
        switch(input)
        {
            case 20:
                this.setTransferReservation(transferer);
                break;
            case 21:
                this.processNoChargeTransfer(transferer);
                completed = true;
                break;
            case 23:
                this.processCashTransfer(transferer);
                completed = true;
                break;
            case 24:
                this.processCardTransfer(transferer);
                completed = true;
                break;
            default:
                completed = true;// Cancel booking
                break;
        }
        if(!completed) 
        {
           this.selectSeatsAndFinalizeTransfer(transferer); // call the function recursively
        }
        return 4;//Redirect to BOOKINGS INDEX
    }
    
    /**
     * addTransferReservation
     * @param Transferer transferer
     * @return void
     */
    private void setTransferReservation(Transferer transferer)
    {
        int seatRow = this.cinema.convertToRowNum(this.getUserInputString(1, "Enter row letter (A to E)"));
        int seatNum = this.getUserInputIntegerRange(1,10,"Enter seat number");
        transferer.setReservation(seatRow, seatNum);
    }
    
    /**
     * processNoChargeTransfer
     * @param Transferer transferer
     * @return void
     */
    private void processNoChargeTransfer(Transferer transferer)
    {
        if(this.getUserInputYN("Proceed with No-charge ticket transfer? (Y/N)").equals("Y"))
        {
            transferer.finalizeNoChargeTransfer(); 
        };
    }
    
    /**
     * processCashTransfer
     * @param Transferer transferer
     * @return void
     */
    private void processCashTransfer(Transferer transferer)
    {
        if(this.getUserInputYN("Proceed with Cash Payment for Ticket transfer? (Y/N)").equals("Y"))
        {
            transferer.finalizeCashPayment();
        };
    }
    
    /**
     * processCardPayment
     * @param Booker booker
     * @return void
     */
    private void processCardTransfer(Transferer transferer)
    {
        if(this.getUserInputYN("Proceed with CreditCard Payment? (Y/N)").equals("Y"))
        {
            transferer.finalizeCardPayment(this.getUserInputString(8, "Enter CreditCard Payment Reference Number"));
        };
    } 
    
    /**
     * enterNewFilmDetails
     * @return int
     */
    private int displayReviewAndRatePage()
    {
        this.clearScreen();
        this.pageHeader(true, 5, 3, "bookings", "REVIEW AND RATE");
        System.out.println("");
        System.out.println("[20, Review And Rate]  [21, Cancel]");
        int input = this.getUserInputInteger(21, "Please make a selection");
        if(input==20)
        {
            return this.enterTicketIdToReviewAndRate();
        }
        if(input==21)
        {
            return 4;// return to BOOKINGS INDEX
        }
        return input;  
    }

    /**
     * enterTicketIdToReviewAndRate
     * @return int
     */
    private int enterTicketIdToReviewAndRate()
    {
        this.clearScreen(); 
        int input;
        this.pageHeader(false, 4, 2, "bookings", "REVIEW AND RATE > Select Ticket To Review and Rate");
        while(true)
        {
            input = this.getUserInputIntegerRange(0, Integer.MAX_VALUE, "Enter TicketId or Enter 0 to Cancel:");
            if(input == 0)
            {
                return 4;// Return to BOOKINGS INDEX
            }
            Ticket ticket = this.cinema.getTickets().get(input);
            if(ticket!=null)
            {
                Calendar now = Calendar.getInstance();
                if(this.cinema.getReviews().get(ticket.getId())==null&&(now.compareTo(ticket.getShow().getDate())>=0))
                {
                    return this.enterReviewAndRating(ticket);
                }
                else if(this.cinema.getReviews().get(ticket.getId())!=null)
                {
                    System.out.println("Review already logged for that TicketId.");
                }
                else
                {
                    System.out.println("You cannot log a review until after the show. ");
                }
            }
            else
            {
                System.out.println("TicketId not recognized"); 
            }
        }
    }   

    /**
     * enterReviewAndRating
     * @param Ticket ticket
     * @return void
     */
    private int enterReviewAndRating(Ticket ticket)
    {
        this.clearScreen(); 
        int rating;
        String review;
        this.pageHeader(false, 4, 2, "bookings", "REVIEW AND RATE");
        System.out.println(ticket.getShow().toString());
        System.out.println("");
        review = this.getUserInputString(255, "Enter your Review");
        rating = this.getUserInputIntegerRange(1, 5, "Enter Your Rating (1 - 5)");
        if(this.getUserInputYN("Save Review and Rating? (Y/N)").equals("Y"))
        {
            this.cinema.addReview(ticket, review, rating);
        };
        return 4;
    }   
    
    /**
     * printListWithOptions
     * @param int optionCounter
     * @param List list
     * @return int optionCounter
     */
    private int printListWithOptions(int optionCounter, List list, Map optionToObject)
    {
        System.out.println("");
        System.out.println("Select");
        for(Object object : list)
        {
            optionToObject.put(optionCounter, object);
            System.out.print("[" + optionCounter + "]       ");
            System.out.println(object.toString());
            optionCounter++;
        }
        return optionCounter;
    }
    
    /**
     * 
     * pageHeader
     * @param int primaryPointer
     * @param int secondaryPointer
     * @param String banner
     * @return void
     */
    private void pageHeader(boolean includeFullHeader, int primaryPointer, int secondaryPointer, String category, String subtext)
    {
        if(includeFullHeader)
        {
            this.includeFullHeader();
            this.includePointer(primaryPointer);
            this.includeSecondaryNav(category);
            this.includePointer(secondaryPointer);
        }
        else
        {
            this.includeTitleBar();
        }

        this.includeBanner(category, subtext);   
    }
    

    /**
     * getUserInputInteger()
     * @param int maxValue
     * @return int input
     */
    public int getUserInputInteger(int maxValue)
    {
        Scanner scanner = new Scanner(System.in);
        int selection = 0;
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Please make your selection:");
            if(scanner.hasNextInt())
            {
                // Input is an integer
                selection = scanner.nextInt();
                if(selection==0||selection>maxValue)// Validate the input in range
                {
                    System.out.println("The number you entered is not in range 1 to " + maxValue);
                }
                else
                {
                    return selection;// input is valid 
                }  
            }
            else
            {
                // Input is not an integer
                scanner.next();// Remove input from scanner
                System.out.println("The input was not a number");// Display notification
            }
        }    
    }

        
    /**
     * acceptIntegerInput()
     * @param int maxValue
     * @param String question
     * @return int input
     */
    public int getUserInputInteger(int maxValue, String question)
    {
        Scanner scanner = new Scanner(System.in);
        int selection = 0;
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println(question + ":");
            if(scanner.hasNextInt())
            {
                // Input is an integer
                
                // Update the choice
                selection = scanner.nextInt();

                // Validate the input in range
                if(selection==0||selection>maxValue)
                {
                    System.out.println("The number you entered is not in range 1 to " + maxValue);
                }
                else
                {
                    // input is valid 
                    return selection;
                }            
            
            }
            else
            {
                // Input is not an integer
                
                // Remove input from scanner and display notification
                scanner.next();
                System.out.println("The input was not a number");
            }
            

        }    
    }

    

        
    /**
     * getUserInputIntegerRange()
     * @param int minValue
     * @param int maxValue
     * @param String question
     * @return int input
     */
    public int getUserInputIntegerRange(int minValue, int maxValue, String question)
    {
        Scanner scanner = new Scanner(System.in);
        int selection = -1;
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println(question + ":");
            if(scanner.hasNextInt())
            {
                // Input is an integer
                
                // Update the choice
                selection = scanner.nextInt();

                // Validate the input in range
                if(selection==-1||selection<minValue||selection>maxValue)
                {
                    System.out.println("The number you entered is not in range " + minValue + " to " + maxValue);
                }
                else
                {
                    // input is valid 
                    return selection;
                }            
            
            }
            else
            {
                // Input is not an integer
                
                // Remove input from scanner and display notification
                scanner.next();
                System.out.println("The input was not a number");
            }
            

        }    
    }
        
    /**
     * getUserInputDouble()
     * @param String question
     * @return float input
     */
    public float getUserInputFloat(String question)
    {
        Scanner scanner = new Scanner(System.in);
        float selection = 0;
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println(question + ":");
            if(scanner.hasNextFloat())
            {
                // Input is an float
                
                // Update the choice
                selection = scanner.nextFloat();

                // Validate the input in range
                if(selection==0)
                {
                    System.out.println("The number you entered is not valid");
                }
                else
                {
                    // input is valid 
                    return selection;
                }            
            
            }
            else
            {
                // Input is not a double
                
                // Remove input from scanner and display notification
                scanner.next();
                System.out.println("The input was not a number");
            }
        }    
    }
    
    
    /**
     * getUserInputString()
     * @param int maxLength
     * @return String input
     */
    public String getUserInputString(int maxLength)
    {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Please make your selection:");
            if(scanner.hasNextLine())
            {
                // Input is an integer
                
                // Update the choice
                input = scanner.nextLine();

                // Validate the input in range
                if(input=="")
                {
                    System.out.println("Input cannot be blank");
                }
                else if(input.length()>maxLength)
                {
                    System.out.println("Input too long. Input must be less than " + maxLength + " characters.");
                }
                else
                {
                    // input is valid 
                    return input;
                }            
            
            }
            else
            {
                // Input is not a string
                
                // Remove input from scanner and display notification
                scanner.next();
                System.out.println("The input is not valid");
            }
            

        }     
    }
    
    /**
     * getUserInputString()
     * @param int maxLength
     * @param String question
     * @return String input
     */
    public String getUserInputString(int maxLength, String question)
    {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println(question + ":");
            if(scanner.hasNextLine())
            {
                // Input is an integer
                
                // Update the choice
                input = scanner.nextLine();

                // Validate the input in range
                if(input=="")
                {
                    System.out.println("Input cannot be blank");
                }
                else if(input.length()>maxLength)
                {
                    System.out.println("Input too long. Input must be less than " + maxLength + " characters.");
                }
                else
                {
                    // input is valid 
                    return input;
                }            
            
            }
            else
            {
                // Input is not a string
                
                // Remove input from scanner and display notification
                scanner.next();
                System.out.println("The input is not valid");
            }
            

        }    
    }   
    
    /**
     * getUserInputYN()
     * @return String input
     */
    public String getUserInputYN()
    {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Enter Y or N:");
            if(scanner.hasNextLine())
            {
                // Input is an integer
                
                // Update the choice
                input = scanner.nextLine();

                // Validate the input in range
                if((input.equals("Y"))||(input.equals("y")))
                {
                    return "Y";
                }
                else if((input.equals("N"))||(input.equals("n")))
                {
                    return "N";
                }
                else
                {
                    System.out.println("Input must be either Y or N");
                }            
            
            }
            else
            {
                // Input is not a string
                
                // Remove input from scanner and display notification
                scanner.next();
                System.out.println("The input is not valid");
            }
            

        }     
    }
    
    /**
     * getUserInputYN()
     * @param String question
     * @return String input
     */
    public String getUserInputYN(String question)
    {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println(question + ":");
            if(scanner.hasNextLine())
            {
                // Input is an integer
                
                // Update the choice
                input = scanner.nextLine();

                // Validate the input in range
                if((input.equals("Y"))||(input.equals("y")))
                {
                    return "Y";
                }
                else if((input.equals("N"))||(input.equals("n")))
                {
                    return "N";
                }
                else
                {
                    System.out.println("Input must be either Y or N");
                }            
            
            }
            else
            {
                // Input is not a string
                
                // Remove input from scanner and display notification
                scanner.next();
                System.out.println("The input is not valid");
            }
            

        }     
    }
    
    /**
     * includeFullHeader()
     * @return void
     * 
     */
    public void includeFullHeader()
    {
        System.out.println("==========================ODEON CINEMA SYSTEM=========================");
        System.out.println("[1, Films]  [2, Shows]  [3, Cstmr]  [4, Bkngs]  [5, Reprt]  [6, Quit ]");
    }
    
    /**
     * includeTitleBar()
     * @return void
     * 
     */
    public void includeTitleBar()
    {
        System.out.println("==========================ODEON CINEMA SYSTEM=========================");
    }
    
    /**
     * includeBanner()
     * @param String category
     * @param String subtitle
     * @return void
     */
    private void includeBanner(String category, String subtext)
    {
        this.includeBannerMainText(category);
        this.includeBannerSubtext(subtext);
    }
    
    /**
     * includeBannerMainText()
     * print an ASCII art header
     * @param String category
     * @return void
     */
    private void includeBannerMainText(String category)
    {
        System.out.println("");// Margin Top
        switch(category)
        {
            // Text To ASCII Art Generator
            case "films":
                System.out.println("______ _____ _     ___  ___ _____ ");
                System.out.println("|  ___|_   _| |    |  \\/  |/  ___|");
                System.out.println("| |_    | | | |    | .  . |\\ `--. ");
                System.out.println("|  _|   | | | |    | |\\/| | `--. \\");
                System.out.println("| |    _| |_| |____| |  | |/\\__/ /");
                System.out.println("\\_|    \\___/\\_____/\\_|  |_/\\____/ ");
                break;
            case "shows":
                System.out.println(" _____ _   _ _____  _    _ _____");
                System.out.println("/  ___| | | |  _  || |  | /  ___|");
                System.out.println("\\ `--.| |_| | | | || |  | \\ `--. ");
                System.out.println(" `--. \\  _  | | | || |/\\| |`--. \\");
                System.out.println("/\\__/ / | | \\ \\_/ /\\  /\\  /\\__/ /");
                System.out.println("\\____/\\_| |_/\\___/  \\/  \\/\\____/");
                break;
            case "customers":
                System.out.println(" _____ _   _ _____ _____ ________  ___ ___________  _____"); 
                System.out.println("/  __ \\ | | /  ___|_   _|  _  |  \\/  ||  ___| ___ \\/  ___|");
                System.out.println("| /  \\/ | | \\ `--.  | | | | | | .  . || |__ | |_/ /\\ `--. ");
                System.out.println("| |   | | | |`--. \\ | | | | | | |\\/| ||  __||    /  `--. \\");
                System.out.println("| \\__/\\ |_| /\\__/ / | | \\ \\_/ / |  | || |___| |\\ \\ /\\__/ /");
                System.out.println(" \\____/\\___/\\____/  \\_/  \\___/\\_|  |_/\\____/\\_| \\_|\\____/");
                break;
            case "bookings":
                System.out.println("______  _____  _____ _   _______ _   _ _____  _____ "); 
                System.out.println("| ___ \\|  _  ||  _  | | / /_   _| \\ | |  __ \\/  ___|");
                System.out.println("| |_/ /| | | || | | | |/ /  | | |  \\| | |  \\/\\ `--. ");
                System.out.println("| ___ \\| | | || | | |    \\  | | | . ` | | __  `--. \\");
                System.out.println("| |_/ /\\ \\_/ /\\ \\_/ / |\\  \\_| |_| |\\  | |_\\ \\/\\__/ /");
                System.out.println("\\____/  \\___/  \\___/\\_| \\_/\\___/\\_| \\_/\\____/\\____/ ");
                break;
        }        
        System.out.println("");// Margin bottom   
    }
    
    /**
     * includeBannerSubtext
     * Display the subtext
     * @param String subtext
     * @return void
     */
    private void includeBannerSubtext(String subtext)
    {
        System.out.println(subtext);
    }

    /**
     * includeSecondaryNav()
     * @param String category
     * @return void
     */
    public void includeSecondaryNav(String category)
    {
        System.out.println("----------------------------------------------------------------------");
        switch(category)
        {
            case "films":
                System.out.println("[1,  Indx]  [10,  Add]");
                break;
            case "shows":
                System.out.println("[2,  Indx]  [11,  Add]");
                break;
            case "customers":
                System.out.println("[3,  Indx]");
                break;
            case "bookings":
                System.out.println("[4,  Indx]  [13,  Add]  [14, Move]  [15, Revw]");
                break;
            case "reports":
                System.out.println("[5,  Indx]");
                break;
        }
    }
    
    /**
     * includePointer()
     * @param int position
     * @return void
     * 
     */
    private void includePointer(int position)
    {
        for(int i=0;i<position-1;i++)
        {
            System.out.print("            ");// Print white space
        }
        System.out.println("    ^^^^");//Print pointer
    }

    /**
     * Clear the console screen 
     * Author: Dyndrilliac
     * URL: https://stackoverflow.com/questions/2979383/java-clear-the-console
     * Accessed 18-DEC-2017
     * @return void
     */
    public void clearScreen() 
    {   
        try
        {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        catch (final Exception e)
        {
            System.out.println("Error encountered when clearing the screen. " + e.getMessage());
        }  
   }  
}
