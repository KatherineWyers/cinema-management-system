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
            if(pageId == 6&&CliUserInputter.getUserInputYN("Are you sure you want to quit the application? (Y/N)").equals("Y"))
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
        CliNavMaker.pageHeader(true, 1, 1, "films", "FILMS INDEX");
        int option = this.printListWithOptions(20, this.cinema.getFilmList(), optionToFilm);
        int input = CliUserInputter.getUserInputInteger(option);// Set max input as highest option number
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
        CliNavMaker.pageHeader(true, 1, 1, "films", "DISPLAY FILM DETAILS");
        System.out.println("Title:      " + film.getTitle());
        System.out.println("Year:       " + film.getYear());
        System.out.println("Director:   " + film.getDirector());
        System.out.println("Language:   " + film.getLanguage());
        System.out.println("Subtitles:  " + film.getSubtitles());
        int input = CliUserInputter.getUserInputInteger(19);// Set max input as highest option number
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
        CliNavMaker.pageHeader(true, 1, 2, "films", "ADD FILM");
        System.out.println("");
        System.out.println("[20, Create New Film]  [21, Cancel]");
        int input = CliUserInputter.getUserInputInteger(21);
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
        CliNavMaker.pageHeader(false, 1, 2, "films", "ADD FILM > Enter New Film Details");
        String title = CliUserInputter.getUserInputString(255, "Enter the title");
        int year = CliUserInputter.getUserInputInteger(2020, "Enter the year");
        String director = CliUserInputter.getUserInputString(20, "Enter the director");
        String language = CliUserInputter.getUserInputString(20, "Enter the language");
        String subtitles = CliUserInputter.getUserInputString(3, "Enter the subtitles (2-letter acronym or N/A)");
        if(CliUserInputter.getUserInputYN("Save Film? (Y/N)").equals("Y"))
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
        CliNavMaker.pageHeader(true, 2, 1, "shows", "SHOWS INDEX");
        int option = this.printListWithOptions(20, this.cinema.getShowList(), optionToShow);
        int input = CliUserInputter.getUserInputInteger(option);// Set max input as highest option number
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
        CliNavMaker.pageHeader(true, 2, 1, "shows", "DISPLAY SHOW DETAILS");
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
        this.printSeatingGrid(this.cinema.getSeatingGrid(show));
        System.out.println("Regular Tickets (Row A-D): $" + show.getPriceRegular());
        System.out.println("Vip Tickets (Row E):       $" + show.getPriceVip());
        int input = CliUserInputter.getUserInputInteger(19);// Set max input as highest option number
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
        CliNavMaker.pageHeader(true, 2, 2, "shows", "ADD SHOW");
        System.out.println("");
        System.out.println("[20, Create New Show]  [21, Cancel]");
        int input = CliUserInputter.getUserInputInteger(21);
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
        CliNavMaker.pageHeader(false, 2, 2, "shows", "ADD SHOW > Enter New Show Details");
        System.out.println("*** SELECT FILM ***");
        int option = this.printListWithOptions(20, this.cinema.getFilmList(), optionToFilm);
        Film film = optionToFilm.get(CliUserInputter.getUserInputInteger(option, "Enter Film selection:"));
        System.out.println("*** SELECT SCREEN ***");
        option = this.printListWithOptions(option, this.cinema.getScreenList(), optionToScreen);
        Screen screen = optionToScreen.get(CliUserInputter.getUserInputInteger(option, "Enter Screen selection:"));
        int dd = CliUserInputter.getUserInputIntegerRange(1, 31, "Enter Date. For example: 22");
        int mm = CliUserInputter.getUserInputIntegerRange(1, 12, "Enter Month. For example: 6");
        int yyyy = CliUserInputter.getUserInputIntegerRange(2015, 2050, "Enter Year. For example 2017");
        int hh = CliUserInputter.getUserInputIntegerRange(0, 23, "Enter Hour in 24-hour clock. For example 19");
        int ii = CliUserInputter.getUserInputIntegerRange(0, 59, "Enter Minute. For example 30");
        Calendar date = new GregorianCalendar(yyyy, mm-1, dd, hh, ii);
        float priceRegular = CliUserInputter.getUserInputFloat("Enter Price for Regular Tickets. For example 8.50");
        float priceVip = CliUserInputter.getUserInputFloat("Enter Price for VIP Tickets. For example 12.50");
        if(CliUserInputter.getUserInputYN("Save Film? (Y/N)").equals("Y"))
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
        CliNavMaker.pageHeader(true, 3, 1, "customers", "CUSTOMERS INDEX");
        int option = this.printListWithOptions(20, this.cinema.getCustomerList(), optionToCustomer);
        int input = CliUserInputter.getUserInputInteger(option);// Set max input as highest option number
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
        CliNavMaker.pageHeader(true, 3, 1, "customers", "DISPLAY CUSTOMER DETAILS");
        System.out.println("CustomerID: " + customer.getId());
        System.out.println("Name:       " + customer.getName());
        int input = CliUserInputter.getUserInputInteger(19);// Set max input as highest option number
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
        CliNavMaker.pageHeader(true, 4, 1, "bookings", "BOOKINGS INDEX");
        int option = this.printListWithOptions(20, this.cinema.getBookingList(), optionToBooking);
        int input = CliUserInputter.getUserInputInteger(option);// Set max input as highest option number
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
        CliNavMaker.pageHeader(true, 4, 1, "bookings", "DISPLAY BOOKING DETAILS");
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
       int input = CliUserInputter.getUserInputInteger(19);// Set max input as highest option number
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
        CliNavMaker.pageHeader(true, 4, 2, "bookings", "ADD BOOKING");
        System.out.println("");
        System.out.println("[20, Create New Customer]  [21, Existing Customer]  [22, Cancel]");
        int input = CliUserInputter.getUserInputInteger(22);
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
        CliNavMaker.pageHeader(false, 4, 2, "bookings", "ADD BOOKING > Enter New Booking Details");
        Customer customer = new Customer (this.cinema.getNextCustomerId());// create blank customer
        if(input==20)
        {
            customer = this.cinema.getNewCustomer(CliUserInputter.getUserInputString(30, "Enter Customer Name"));
        }
        else
        {
            try
            {
                customer = this.cinema.getCustomer(CliUserInputter.getUserInputInteger(Integer.MAX_VALUE, "Enter Customer MemberCard ID"));
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                CliUserInputter.getUserInputString(1, "Press enter to return to bookings index");
                return 4;// return to the BOOKINGS INDEX
            }
        }
        System.out.println("*** SELECT SHOW ***");
        int option = this.printListWithOptions(20, this.cinema.getShowList(), optionToShow);
        Show show = optionToShow.get(CliUserInputter.getUserInputInteger(option, "Enter Show selection:"));
        
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
        CliNavMaker.pageHeader(false, 4, 2, "bookings", "ADD BOOKING > Select Seats");
        this.printSeatingGrid(booker.getSeatingGrid());
        System.out.println("Regular Tickets (Row A-D): $" + booker.getShow().getPriceRegular());
        System.out.println("Vip Tickets (Row E):       $" + booker.getShow().getPriceVip());
        this.printCurrentBookingDetails(booker);
        System.out.println("[20, Add Seat]  [21, Remove Seat] [22, Cash Payment ] [23, CreditCard Payment] [24, Cancel]");
        int input = CliUserInputter.getUserInputIntegerRange(20,24,"Please make a selection"); 
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
        int seatRow = this.cinema.convertToRowNum(CliUserInputter.getUserInputString(1, "Enter row letter (A to E)"));
        int seatNum = CliUserInputter.getUserInputIntegerRange(1,10,"Enter seat number");
        booker.addReservation(seatRow, seatNum);
    }
    
    /**
     * removeSeatFromBooking
     * @param Booker booker
     * @return void
     */
    private void removeSeatFromBooking(Booker booker)
    {
        int seatRow = this.cinema.convertToRowNum(CliUserInputter.getUserInputString(1, "Enter row letter (A to E)"));
        int seatNum = CliUserInputter.getUserInputIntegerRange(1,10,"Enter seat number");
        booker.removeReservation(seatRow, seatNum);
    }
    
    /**
     * processCashPayment
     * @param Booker booker
     * @return void
     */
    private void processCashPayment(Booker booker)
    {
        if(CliUserInputter.getUserInputYN("Proceed with Cash Payment? (Y/N)").equals("Y"))
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
        if(CliUserInputter.getUserInputYN("Proceed with CreditCard Payment? (Y/N)").equals("Y"))
        {
            booker.finalizeCardPayment(CliUserInputter.getUserInputString(8, "Enter CreditCard Payment Reference Number"));
        };
    }
    
    /**
     * displayMoveTicketPage()
     * @return int pageId
     */
    public int displayMoveTicketPage()
    {
        this.clearScreen();
        CliNavMaker.pageHeader(true, 5, 3, "bookings", "MOVE TICKET");
        System.out.println("");
        System.out.println("[20, Reschedule Ticket]  [21, Cancel]");
        int input = CliUserInputter.getUserInputInteger(21, "Please make a selection");
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
        CliNavMaker.pageHeader(false, 4, 2, "bookings", "MOVE TICKET > Select Ticket To Move");
        while(true)
        {
            input = CliUserInputter.getUserInputIntegerRange(0, Integer.MAX_VALUE, "Enter TicketId or Enter 0 to Cancel:");
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
        CliNavMaker.pageHeader(false, 4, 2, "bookings", "MOVE TICKET > Select New Show");
        System.out.println("*** SELECT SHOW ***");
        int option = this.printListWithOptions(20, this.cinema.getShowList(), optionToShow);
        Show show = optionToShow.get(CliUserInputter.getUserInputInteger(option, "Enter Show selection:"));
        
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
        CliNavMaker.pageHeader(false, 4, 2, "bookings", "MOVE TICKET > Tranfer ticket to a new Show and/or Seat");
        this.printSeatingGrid(transferer.getSeatingGridIgnoreTicket(transferer.getTicket()));
        System.out.println("***Transfer Surcharge***");
        System.out.println("Regular Tickets (Row A-D): $" + transferer.getTicketTransferSurcharge("regular"));
        System.out.println("Vip Tickets (Row E):       $" + transferer.getTicketTransferSurcharge("vip"));
        this.printCurrentTransferDetails(transferer);
        System.out.println("[20, Select Seat]  [21, Process Transfer] [22, Cancel]");
        int input = CliUserInputter.getUserInputIntegerRange(20,22,"Please make a selection"); 
        if(input == 21&&transferer.getSurcharge()>0)
        {
            System.out.println("[23, Cash Payment]  [24, Card Payment] [25, Cancel]");
            input = CliUserInputter.getUserInputIntegerRange(23,25,"Please make a selection"); 
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
        int seatRow = this.cinema.convertToRowNum(CliUserInputter.getUserInputString(1, "Enter row letter (A to E)"));
        int seatNum = CliUserInputter.getUserInputIntegerRange(1,10,"Enter seat number");
        transferer.setReservation(seatRow, seatNum);
    }
    
    /**
     * processNoChargeTransfer
     * @param Transferer transferer
     * @return void
     */
    private void processNoChargeTransfer(Transferer transferer)
    {
        if(CliUserInputter.getUserInputYN("Proceed with No-charge ticket transfer? (Y/N)").equals("Y"))
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
        if(CliUserInputter.getUserInputYN("Proceed with Cash Payment for Ticket transfer? (Y/N)").equals("Y"))
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
        if(CliUserInputter.getUserInputYN("Proceed with CreditCard Payment? (Y/N)").equals("Y"))
        {
            transferer.finalizeCardPayment(CliUserInputter.getUserInputString(8, "Enter CreditCard Payment Reference Number"));
        };
    } 
    
    /**
     * displayReviewAndRatePage
     * @return int
     */
    public int displayReviewAndRatePage()
    {
        this.clearScreen();
        CliNavMaker.pageHeader(true, 5, 3, "bookings", "REVIEW AND RATE");
        System.out.println("");
        System.out.println("[20, Review And Rate]  [21, Cancel]");
        int input = CliUserInputter.getUserInputInteger(21, "Please make a selection");
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
        CliNavMaker.pageHeader(false, 4, 2, "bookings", "REVIEW AND RATE > Select Ticket To Review and Rate");
        while(true)
        {
            input = CliUserInputter.getUserInputIntegerRange(0, Integer.MAX_VALUE, "Enter TicketId or Enter 0 to Cancel:");
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
        CliNavMaker.pageHeader(false, 4, 2, "bookings", "REVIEW AND RATE");
        System.out.println(ticket.getShow().toString());
        System.out.println("");
        review = CliUserInputter.getUserInputString(255, "Enter your Review");
        rating = CliUserInputter.getUserInputIntegerRange(1, 5, "Enter Your Rating (1 - 5)");
        if(CliUserInputter.getUserInputYN("Save Review and Rating? (Y/N)").equals("Y"))
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
     * displayReportsIndexPage()
     * @return int
     * 
     */
    public int displayReportsIndexPage()
    {
        this.clearScreen();
        CliNavMaker.pageHeader(true, 5, 1, "reports", "REPORTS INDEX");
        System.out.println("[16] View Tickets sold and Average Rating per month");
        System.out.println("[17] View Income generated per film, per month");
        int input = CliUserInputter.getUserInputInteger(17);// Set max input as highest option number
        return input;
    }
    
    /**
     * displayReportsTicketReportPage()
     * @return int
     * 
     */
    public int displayTicketReportsPage()
    {
        this.clearScreen();
        CliNavMaker.pageHeader(false, 5, 2, "reports", "REPORTS: Ticket Reports");
        System.out.println("--------------------------");
        int monthNum = this.selectMonth();
        int year = CliUserInputter.getUserInputIntegerRange(2010, 2050, "Enter the year");  
        return this.showTicketReportsList(monthNum, year);
    }
    
    /**
     * selectMonth
     * Take user input and return a zero-based month number
     * @return int monthNum
     */
    private int selectMonth()
    {
        System.out.println("Select a month");
        System.out.println("[1,  Jan]  [2,  Feb]  [3,  Mar]  [4,  Apr]  [5,  May]  [6,  Jun]");
        System.out.println("[7,  Jul]  [8,  Aug]  [9,  Sep]  [10, Oct]  [11, Nov]  [12, Dec]");
        int input = 0;
        int monthNum = 0;
        int year = 0;
        while(true)
        {
            input = CliUserInputter.getUserInputIntegerRange(1, 12, "Please select a month");
            return input - 1;// offset the input to zero-based month numbers
        }
    }
    
    /**
     * showTicketReportsList()
     * @param int monthNum /zero-based month num 0:Jan, 1: Feb... 11:Dec
     * @return int
     */
    public int showTicketReportsList(int monthNum, int year)
    {
        this.clearScreen();
        CliNavMaker.pageHeader(true, 5, 2, "reports", "REPORTS: INDEX");
        Reporter reporter = new Reporter(this.cinema);
        List<TicketReport> ticketReportList = reporter.getTicketReportList(monthNum, year);
        if(ticketReportList.size()==0)
        {
            this.printNoTicketsSold();
        }
        else
        {
            System.out.println("*** TICKET COUNT and AVERAGE RATING ***");
            for(TicketReport ticketReport : ticketReportList)
            {
                System.out.println(ticketReport.toString());
            }            
        }
        System.out.println("");
        System.out.println("[20, View Different Month]");
        int input = CliUserInputter.getUserInputInteger(20, "Please make a selection");// Set max input as highest option number
        if(input==20)
        {
           return 5;// Invalid selection. Return to SHOWS INDEX
        }
        return input;
    }
    
    /**
     * displayReportsIncomeReportPage()
     * @return int
     * 
     */
    public int displayIncomeReportsPage()
    {
        this.clearScreen();
        CliNavMaker.pageHeader(false, 5, 3, "reports", "REPORTS: Income Reports");
        System.out.println("--------------------------");
        int monthNum = this.selectMonth();
        int year = CliUserInputter.getUserInputIntegerRange(2010, 2050, "Enter the year");  
        return this.showIncomeReportsList(monthNum, year);
    }
    
    /**
     * showTicketReportsList()
     * @param int monthNum /zero-based month num 0:Jan, 1: Feb... 11:Dec
     * @return int
     */
    public int showIncomeReportsList(int monthNum, int year)
    {
        this.clearScreen();
        CliNavMaker.pageHeader(true, 5, 3, "reports", "REPORTS: INDEX");
        Reporter reporter = new Reporter(this.cinema);
        List<IncomeReport> incomeReportList = reporter.getIncomeReportList(monthNum, year);
        if(incomeReportList.size()==0)
        {
            this.printNoTicketsSold();
        }
        else
        {
            System.out.println("*** INCOME GENERATED PER FILM ***");
            for(IncomeReport incomeReport : incomeReportList)
            {
                System.out.println(incomeReport.toString());
            }            
        }
        System.out.println("");
        System.out.println("[20, View Different Month]");
        int input = CliUserInputter.getUserInputInteger(20, "Please make a selection");// Set max input as highest option number
        if(input==20)
        {
           return 5;// Invalid selection. Return to SHOWS INDEX
        }
        return input;
    }
    
    /**
     * printNoTicketsSold
     * @return void
     */
    private void printNoTicketsSold()
    {
            System.out.println("");
            System.out.println("**********************************");
            System.out.println("*** No Tickets sold that month ***");
            System.out.println("**********************************");
            System.out.println("");
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
            rowLetter = this.cinema.convertToRowLetter(i+1);
            
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
     * printCurrentBookingDetails
     * Get the seat reservations and the price of each
     * @param List reservations
     * @return void 
     */
    public void printCurrentBookingDetails(Booker booker)
    {
        
        System.out.println("");
        System.out.println("######CURRENT BOOKING######");
        System.out.println("#SEAT------PRICE----------#");
        for(Reservation reservation : booker.getReservations())
        {
            System.out.println("#" + cinema.convertToRowLetter(reservation.getRow()) + reservation.getNum() + ": $" + reservation.getPrice());
        }
        System.out.println("#-------------------------#");
        System.out.println("#TOTAL PRICE: $" + booker.getTotalPrice());
        System.out.println("###########################");
        System.out.println("");
    }
    
    /**
     * printCurrentTransferDetails
     * This only executes if a reservation has been set
     * Get the new seat reservation, and the surcharge for the upgrade
     * @return void 
     */
    public void printCurrentTransferDetails(Transferer transferer)
    {
        if(transferer.getReservation()!=null)
        {
            System.out.println("");
            System.out.println("######CURRENT TRANSFER######");
            System.out.println("#SEAT----------------------#");
            System.out.println("#" + cinema.convertToRowLetter(transferer.getReservation().getRow()) + transferer.getReservation().getNum());
            System.out.println("#-------------------------#");
            System.out.println("#TRANSFER PRICE: $" + transferer.getSurcharge());
            System.out.println("###########################");
            System.out.println("");
        }
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
