import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Gui interface for the application
 *
 * @author Katherine Wyers
 * @version 1.0
 * 
 * Adapted from: H. Gan 2015
 */
public class Gui extends UserInterface
{
    private JFrame frame;
    private int frameWidth;
    private int frameHeight;
    private JList<Object> indexList;
    private DefaultListModel<Object> listModel;
    
    private JPanel northPanel;
    private JPanel centerPanel;
    private JPanel eastPanel;
    private JPanel southPanel;
    
    private Map<String, Object> formData = new HashMap<String, Object>();
    
    /**
     * Constructor for objects of class Gui
     */
    public Gui()
    {
        this.setupFrame();
    }
    
    /**
     * setupFrame
     * @return void
     */
    private void setupFrame()
    {
        this.frameWidth = 1024;
        this.frameHeight = 768;
        this.indexList = new JList<Object>();
        this.listModel = new DefaultListModel<Object>();
        
        this.northPanel = new JPanel(new BorderLayout());
        this.centerPanel = new JPanel();
        this.eastPanel = new JPanel();
        this.southPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));
        
        this.frame = new JFrame("Odeon Cinema System");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(frameWidth,frameHeight);
        //this.frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * run
     * @return void
     */
    public void run()
    {
        this.displayFilmsIndexPage();
        this.frame.pack();
        this.frame.setVisible(true); 
        // Application will quit when user clicks 'X' in the application interface
    }
    
    /**
     * displayFilmsIndexPage()
     * @return void
     */
    private void displayFilmsIndexPage()
    {    
        this.clearPanels();
        this.northPanel.add(this.getPrimaryNavPanel("films"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("films", 1), BorderLayout.SOUTH);
        this.refreshList(this.cinema.getFilmList());// Load the index list and display it
        this.indexList = new JList<Object>(listModel);
        this.centerPanel.add(this.indexList);
        this.indexList.addMouseListener(filmsIndexListMouseListener);// Event listener. When the list is clicked, it updates the eastPanel
        this.updateEastPanelFilmsIndex(this.cinema.getFilmList().get(0));// Display the first film in the list by default
        this.showPanels();
    }
      
    /**
     * updateEastPanelFilmsIndex
     * @param Film film
     * @return void
     */
    private void updateEastPanelFilmsIndex(Film film)
    {   
        this.clearEastPanel();
        if(film==null)
        {
            throw new IllegalArgumentException("Film Id is not recognized");
        }
        
        // The following is adapted from:
        // https://stackoverflow.com/questions/11735237/swing-aligning-jpanels-in-a-boxlayout
        // Author: Guillaume Polet
        // Accessed 26-DEC-2017
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        ArrayList<JComponent> componentList = new ArrayList<JComponent>();
        componentList.add(new JLabel("Title: " + film.getTitle()));
        componentList.add(new JLabel("Director: " + film.getDirector()));
        componentList.add(new JLabel("Year: " + film.getYear()));
        componentList.add(new JLabel("Language: " + film.getLanguage()));
        componentList.add(new JLabel("Subtitles: " + film.getSubtitles()));   
        for(JComponent component : componentList)
        {  
            JComponent insidePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            insidePanel.add(component);  
            panel.add(insidePanel);
        }
        eastPanel.add(panel);  
        this.refreshEastPanel();
    }
    
    
    /**
     * displayAddFilmPage()
     * @return void
     */
    private void displayAddFilmPage()
    {    
        this.clearPanels();
        this.northPanel.add(this.getPrimaryNavPanel("films"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("films", 2), BorderLayout.SOUTH);
        this.addAddFilmFormToCenterPanel();
        this.showPanels();
    }
    
    /**
     * addAddFilmFormToCenterPanel()
     * @return void
     */
    private void addAddFilmFormToCenterPanel()
    {
        JPanel panel = new JPanel();   
        panel.setLayout(new GridLayout(6,2,5,5));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel = this.addTextFieldToPanel(20, "title", "Title", panel);
        panel = this.addTextFieldToPanel(20, "director", "Director", panel);
        panel = this.addTextFieldToPanel(4, "year", "Year (eg 2017)", panel);
        panel = this.addTextFieldToPanel(20, "language", "Language (eg English)", panel);
        panel = this.addTextFieldToPanel(4, "subtitles", "Subtitles (eg NL)", panel);
        panel.add(new JLabel(""));//blank label so submit button moves to second column
        JButton submit = new JButton("Submit");
        submit.addActionListener(addFilmActionListener);
        panel.add(submit);
        this.centerPanel.add(panel);
    }
    
    /**
     * displayShowsIndexPage()
     * @return void
     */
    private void displayShowsIndexPage()
    {    
        this.clearPanels();
        this.northPanel.add(this.getPrimaryNavPanel("shows"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("shows", 1), BorderLayout.SOUTH);
        this.refreshList(this.cinema.getShowList());// Load the index list and display it
        this.indexList = new JList<Object>(listModel);
        this.centerPanel.add(this.indexList);
        this.indexList.addMouseListener(showsIndexListMouseListener);// Event listener. When the list is clicked, it updates the eastPanel
        this.updateEastPanelShowsIndex(this.cinema.getShowList().get(0));// preselect the first show
        this.showPanels();
    }
    
    /**
     * updateEastPanelShowsIndex
     * @param Show show
     * @return void
     */
    private void updateEastPanelShowsIndex(Show show)
    {   
        this.clearEastPanel();
        if(show==null)
        {
            throw new IllegalArgumentException("Show Id is not recognized");
        }
        
        // The following is adapted from:
        // https://stackoverflow.com/questions/11735237/swing-aligning-jpanels-in-a-boxlayout
        // Author: Guillaume Polet
        // Accessed 26-DEC-2017
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        ArrayList<JComponent> componentList = new ArrayList<JComponent>();
        componentList.add(new JLabel("Film: " + show.getFilm().getTitle()));
        componentList.add(new JLabel("DateTime: " + show.getDateTime()));
        componentList.add(new JLabel("Screen: " + show.getScreen().getTitle()));    
        for(JComponent component : componentList)
        {  
            JComponent insidePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            insidePanel.add(component);  
            panel.add(insidePanel);
        }
        JComponent insidePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        insidePanel.add(new JLabel("SEATING GRID"));
        panel.add(insidePanel);
        insidePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        insidePanel.add(this.getSeatingGridPanel(this.cinema.getSeatingGrid(show)));
        panel.add(insidePanel);
        eastPanel.add(panel);     
        this.refreshEastPanel();  
    }
    
    /**
     * displayAddShowPage()
     * Reset the formData Map to blank
     * Call the first page
     * @return void
     */
    private void displayAddShowPage()
    {
        this.formData = new HashMap<String, Object>();
        this.displayAddShowSelectFilm();
    } 
    
    /**
     * displayAddShowSelectFilm()
     * @return void
     */
    private void displayAddShowSelectFilm()
    {    
        this.clearPanels();
        this.northPanel.add(this.getPrimaryNavPanel("shows"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("shows", 2), BorderLayout.SOUTH);
        this.refreshList(this.cinema.getFilmList());
        this.indexList = new JList<Object>(listModel);
        this.centerPanel.add(this.indexList);
        this.indexList.addMouseListener(addShowSelectFilmListMouseListener);
        this.updateEastPanelAddShowSelectFilm(this.cinema.getFilmList().get(0));// preselect the first film
        this.showPanels();
    }
    
    /**
     * updateEastPanelAddShowSelectFilm
     * @param Film film
     * @return void
     */
    private void updateEastPanelAddShowSelectFilm(Film film)
    {   
        this.clearEastPanel();
        if(film==null)
        {
            throw new IllegalArgumentException("Film is not recognized");
        }
        eastPanel.add(new JLabel("<html>Title: " + film.getTitle() + "<br />Director: " + film.getDirector() + "<br />Year: " + film.getYear() + "<br /></html>"));
        this.formData.put("tempFilm", film);// place film in temporary HashMap location, until 'Select Film for Show' has been clicked. 
        JButton addFilmButton = new JButton("Select Film for Show");
        addFilmButton.addActionListener(addShowActionListener);
        this.eastPanel.add(addFilmButton);
        this.refreshEastPanel();
    }
    
    /**
     * displayAddShowSelectScreen()
     * @return void
     */
    private void displayAddShowSelectScreen()
    {    
        this.clearPanels();
        this.northPanel.add(this.getPrimaryNavPanel("shows"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("shows", 2), BorderLayout.SOUTH);
        this.refreshList(this.cinema.getScreenList());
        this.indexList = new JList<Object>(listModel);
        this.centerPanel.add(this.indexList);
        this.indexList.addMouseListener(addShowSelectScreenListMouseListener);
        this.updateEastPanelAddShowSelectScreen(this.cinema.getScreenList().get(0));// preselect the first film
        this.showPanels();
    }  
    
    /**
     * updateEastPanelAddShowSelectScreen
     * @param Screen screen
     * @return void
     */
    private void updateEastPanelAddShowSelectScreen(Screen screen)
    {   
        this.clearEastPanel();
        if(screen==null)
        {
            throw new IllegalArgumentException("Screen Id is not recognized");
        }
        eastPanel.add(new JLabel("<html>Title: " + screen.getTitle()));
        formData.put("tempScreen", screen);// place screen in temporary HashMap location, until 'Select Screen for Show' has been clicked. 
        JButton addScreenButton = new JButton("Select Screen for Show");
        addScreenButton.addActionListener(addShowActionListener);
        this.eastPanel.add(addScreenButton);
        this.refreshEastPanel();
    }
    
    /**
     * displayAddShowDetails()
     * @return void
     */
    private void displayAddShowEnterDetails()
    {
        this.clearPanels();
        this.northPanel.add(this.getPrimaryNavPanel("shows"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("shows", 2), BorderLayout.SOUTH);
        this.addAddShowEnterDetailsFormToCenterPanel();
        this.showPanels();
    }   
    
    /**
     * addAddShowDetailsFormToCenterPanel()
     * @return void
     */
    private void addAddShowEnterDetailsFormToCenterPanel()
    {
        JPanel panel = new JPanel();   
        panel.setLayout(new GridLayout(8,2,5,5));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel = this.addTextFieldToPanel(4, "year", "Year (eg: 2017)", panel);
        panel = this.addTextFieldToPanel(2, "month", "MonthNo. (eg: 6)", panel);
        panel = this.addTextFieldToPanel(2, "date", "Date (eg 25)", panel);
        panel = this.addTextFieldToPanel(20, "hour", "Hour (0-23)", panel);
        panel = this.addTextFieldToPanel(20, "minute", "Minute (0-59)", panel);
        panel = this.addTextFieldToPanel(20, "priceRegular", "Price Regular (eg 8.50)", panel);
        panel = this.addTextFieldToPanel(20, "priceVip", "Price VIP (eg 10.75)", panel);
        this.formData.put("detailsEntered", "true");
        panel.add(new JLabel(""));//blank label so submit button moves to second column
        JButton submit = new JButton("Submit");
        submit.addActionListener(addShowActionListener);
        panel.add(submit);
        this.centerPanel.add(panel);
    }
    
    /**
     * displayCustomersIndexPage()
     * @return void
     */
    private void displayCustomersIndexPage()
    {    
        this.clearPanels();
        this.northPanel.add(this.getPrimaryNavPanel("customers"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("customers", 1), BorderLayout.SOUTH);
        this.refreshList(this.cinema.getCustomerList());// Load the index list and display it
        this.indexList = new JList<Object>(listModel);
        this.centerPanel.add(this.indexList);
        this.indexList.addMouseListener(customersIndexListMouseListener);// Event listener. When the list is clicked, it updates the eastPanel
        this.updateEastPanelCustomersIndex(this.cinema.getCustomerList().get(0));// Display the first film in the list by default
        this.showPanels();
    }
      
    /**
     * updateEastPanelCustomerIndex
     * @param Customer customer
     * @return void
     */
    private void updateEastPanelCustomersIndex(Customer customer)
    {   
        this.clearEastPanel();
        if(customer==null)
        {
            throw new IllegalArgumentException("Customer Id is not recognized");
        }
        
        // The following is adapted from:
        // https://stackoverflow.com/questions/11735237/swing-aligning-jpanels-in-a-boxlayout
        // Author: Guillaume Polet
        // Accessed 26-DEC-2017
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        ArrayList<JComponent> componentList = new ArrayList<JComponent>();
        componentList.add(new JLabel("CustomerName: " + customer.getName()));
        componentList.add(new JLabel("CustomerId: " + customer.getId())); 
        for(JComponent component : componentList)
        {  
            JComponent insidePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            insidePanel.add(component);  
            panel.add(insidePanel);
        }
        eastPanel.add(panel);  
        this.refreshEastPanel();
    }
    
    /**
     * displayBookingsIndexPage()
     * @return void
     */
    private void displayBookingsIndexPage()
    {    
        this.clearPanels();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 1), BorderLayout.SOUTH);
        this.refreshList(this.cinema.getBookingList());// Load the index list and display it
        this.indexList = new JList<Object>(listModel);
        this.centerPanel.add(this.indexList);
        this.indexList.addMouseListener(bookingsIndexListMouseListener);// Event listener. When the list is clicked, it updates the eastPanel
        this.updateEastPanelBookingsIndex(this.cinema.getBookingList().get(0));// Display the first film in the list by default
        this.showPanels();
    }
      
    /**
     * updateEastPanelFilmsIndex
     * @param Film film
     * @return void
     */
    private void updateEastPanelBookingsIndex(Booking booking)
    {   
        this.clearEastPanel();
        if(booking==null)
        {
            throw new IllegalArgumentException("Booking Id is not recognized");
        }
        
        // The following is adapted from:
        // https://stackoverflow.com/questions/11735237/swing-aligning-jpanels-in-a-boxlayout
        // Author: Guillaume Polet
        // Accessed 26-DEC-2017
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        ArrayList<JComponent> componentList = new ArrayList<JComponent>();
        componentList.add(new JLabel("CustomerName: " + booking.getCustomer().getId()));
        componentList.add(new JLabel("CustomerId: " + booking.getCustomer().getId())); 
       for(Ticket ticket : this.cinema.getTicketList(booking))
       {
           componentList.add(new JLabel("Ticket: " + ticket.toString())); 
           if(this.cinema.getReviews().get(ticket.getId())!=null)
           {
               componentList.add(new JLabel("+++Customer Review:       " + this.cinema.getReviews().get(ticket.getId()).getReview()));
               componentList.add(new JLabel("+++Customer Rating (1-5): " + this.cinema.getReviews().get(ticket.getId()).getRating()));
           }
       }    
        
        for(JComponent component : componentList)
        {  
            JComponent insidePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            insidePanel.add(component);  
            panel.add(insidePanel);
        }
        eastPanel.add(panel);  
        this.refreshEastPanel();
    }
    
    /**
     * displayAddBookingPage()
     * Reset the formData Map to blank
     * Call the first page
     * @return void
     */
    private void displayAddBookingPage()
    {
        this.formData = new HashMap<String, Object>();// reset the formData Map
        this.displayAddBookingSelectCustomerType();
    } 
    
    /**
     * displayAddShowSelectFilm()
     * @return void
     */
    private void displayAddBookingSelectCustomerType()
    {    
        this.clearPanels();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 2), BorderLayout.SOUTH);
        JButton newCustomerBtn = new JButton("New Customer");
        newCustomerBtn.putClientProperty("customerType", "newCustomer");
        newCustomerBtn.addActionListener(addBookingSelectCustomerTypeActionListener);
        JButton existingCustomerBtn = new JButton("Existing Customer");
        existingCustomerBtn.putClientProperty("customerType", "existingCustomer");
        existingCustomerBtn.addActionListener(addBookingSelectCustomerTypeActionListener);
        this.centerPanel.add(newCustomerBtn);
        this.centerPanel.add(existingCustomerBtn);
        this.showPanels();
    }
    
    /**
     * displayAddBookingNewCustomer()
     * @return void
     */
    private void displayAddBookingEnterCustomerId()
    {
        this.clearPanels();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 2), BorderLayout.SOUTH);
        JPanel panel = new JPanel();   
        panel.setLayout(new GridLayout(2,2,5,5));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel = this.addTextFieldToPanel(20, "customerId", "Customer Id", panel);
        panel.add(new JLabel(""));//blank label so submit button moves to second column
        JButton submit = new JButton("Submit");
        submit.addActionListener(addBookingSetExistingCustomerActionListener);
        panel.add(submit);
        this.centerPanel.add(panel);
        this.showPanels();
    }   
    
    /**
     * displayAddBookingNewCustomer()
     * @return void
     */
    private void displayAddBookingNewCustomer()
    {
        this.clearPanels();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 2), BorderLayout.SOUTH);
        JPanel panel = new JPanel();   
        panel.setLayout(new GridLayout(2,2,5,5));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel = this.addTextFieldToPanel(20, "customerName", "Customer Name", panel);
        panel.add(new JLabel(""));//blank label so submit button moves to second column
        JButton submit = new JButton("Submit");
        submit.addActionListener(addBookingSetNewCustomerActionListener);
        panel.add(submit);
        this.centerPanel.add(panel);
        this.showPanels();
    }   
    
    /**
     * displayAddBookingSelectShow()
     * @return void
     */
    private void displayAddBookingSelectShow()
    {    
        this.clearPanels();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 2), BorderLayout.SOUTH);
        this.refreshList(this.cinema.getShowList());
        this.indexList = new JList<Object>(listModel);
        this.centerPanel.add(this.indexList);
        this.indexList.addMouseListener(addBookingSelectShowListMouseListener);
        this.updateEastPanelAddBookingSelectShow(this.cinema.getShowList().get(0));// preselect the first film
        this.showPanels();
    }  
    
    /**
     * updateEastPanelAddShowSelectScreen
     * @param Screen screen
     * @return void
     */
    private void updateEastPanelAddBookingSelectShow(Show show)
    {   
        this.clearEastPanel();
        if(show==null)
        {
            throw new IllegalArgumentException("Show Id is not recognized");
        }
        eastPanel.add(new JLabel("<html>Film: " + show.getFilm().getTitle() + ", <br />Screen: " + show.getScreen().getTitle() + ", <br />DateTime: " + show.getDateTime()));
        formData.put("tempShow", show);// place screen in temporary HashMap location, until 'Select Screen for Show' has been clicked. 
        JButton addShowButton = new JButton("Select Show for Booking");
        addShowButton.addActionListener(addBookingActionListener);
        this.eastPanel.add(addShowButton);
        this.refreshEastPanel();
    }
    
    /**
     * displayAddBookingSelectSeats()
     * @param Booker booker
     * @return void
     */
    private void displayAddBookingSelectSeats(Booker booker)
    {    
        this.clearPanels();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 2), BorderLayout.SOUTH);
        this.centerPanel.add(this.getSeatingGridButtons(booker.getSeatingGrid(), "booker"));
        JButton cashPaymentBtn = new JButton("Cash Payment");
        cashPaymentBtn.putClientProperty("paymentType", "cash");
        cashPaymentBtn.addActionListener(addBookingSelectPaymentTypeActionListener);
        JButton cardPaymentBtn = new JButton("Card Payment");
        cardPaymentBtn.putClientProperty("paymentType", "card");
        cardPaymentBtn.addActionListener(addBookingSelectPaymentTypeActionListener);
        this.southPanel.add(cashPaymentBtn);
        this.southPanel.add(cardPaymentBtn);
        this.showPanels();
    }
    
    /**
     * displayBookingsMoveTicketPage()
     * @return void
     */
    private void displayBookingsMoveTicketPage()
    {
        this.formData = new HashMap<String, Object>();// reset the formData Map
        this.clearPanels();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 3), BorderLayout.SOUTH);
        JPanel panel = new JPanel();   
        panel.setLayout(new GridLayout(2,2,5,5));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel = this.addTextFieldToPanel(20, "ticketId", "Ticket Id", panel);
        panel.add(new JLabel(""));//blank label so submit button moves to second column
        JButton submit = new JButton("Submit");
        submit.addActionListener(bookingsMoveTicketActionListener);
        panel.add(submit);
        this.centerPanel.add(panel);
        this.showPanels();
    }   
    
    /**
     * displayBookingsMoveTicketSelectShow()
     * @return void
     */
    private void displayBookingsMoveTicketSelectShow()
    {    
        this.clearPanels();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 3), BorderLayout.SOUTH);
        this.refreshList(this.cinema.getShowList());
        this.indexList = new JList<Object>(listModel);
        this.centerPanel.add(this.indexList);
        this.indexList.addMouseListener(bookingsMoveTicketSelectShowListMouseListener);
        this.updateEastPanelBookingsMoveTicketSelectShow(this.cinema.getShowList().get(0));// preselect the first film
        this.showPanels();
    }  
    
    /**
     * updateEastPanelAddShowSelectScreen
     * @param Screen screen
     * @return void
     */
    private void updateEastPanelBookingsMoveTicketSelectShow(Show show)
    {   
        this.clearEastPanel();
        if(show==null)
        {
            throw new IllegalArgumentException("Show Id is not recognized");
        }
        eastPanel.add(new JLabel("<html>Film: " + show.getFilm().getTitle() + ", <br />Screen: " + show.getScreen().getTitle() + ", <br />DateTime: " + show.getDateTime()));
        formData.put("tempShow", show);// place screen in temporary HashMap location, until 'Select Screen for Show' has been clicked. 
        JButton addShowButton = new JButton("Select Show for Transfer");
        addShowButton.addActionListener(bookingsMoveTicketActionListener);
        this.eastPanel.add(addShowButton);
        this.refreshEastPanel();
    }
    
    /**
     * displayBookingsMoveTicketSelectSeat()
     * @param Transferer transferer
     * @return void
     */
    private void displayBookingsMoveTicketSelectSeat(Transferer transferer)
    {    
        this.clearPanels();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 3), BorderLayout.SOUTH);
        this.centerPanel.add(this.getSeatingGridButtons(transferer.getSeatingGridIgnoreTicket(transferer.getTicket()), "transferer"));
        JButton processTransferBtn = new JButton("Process Transfer");
        processTransferBtn.addActionListener(bookingsMoveTicketSelectPaymentTypeActionListener);
        this.southPanel.add(processTransferBtn);
        this.showPanels();
    }
    
    /**
     * displayBookingsReviewAndRatePage()
     * @return void
     */
    private void displayBookingsReviewAndRatePage()
    {
        this.formData = new HashMap<String, Object>();// reset the formData Map
        this.clearPanels();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 4), BorderLayout.SOUTH);
        JPanel panel = new JPanel();   
        panel.setLayout(new GridLayout(4,2,5,5));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel = this.addTextFieldToPanel(20, "ticketId", "Ticket Id", panel);
        panel = this.addTextFieldToPanel(20, "review", "Review", panel);
        panel = this.addTextFieldToPanel(20, "rating", "Rating", panel);
        panel.add(new JLabel(""));//blank label so submit button moves to second column
        JButton submit = new JButton("Submit");
        submit.addActionListener(bookingsReviewAndRateActionListener);
        panel.add(submit);
        this.centerPanel.add(panel);
        this.showPanels();
    }   
    
    /**
     * displayReportsPage
     * Display the default reports page
     * @return void
     */
    private void displayReportsPage()
    {
        this.displayReportsTicketsAndRatingsPage();
    }
    
    /**
     * this.displayReportsTicketsAndReviewPage()
     * Set the default values to the current year and month
     * and call the displayReportsTicketsAndReviewForSelectedMonth()
     * @return void
     */
    private void displayReportsTicketsAndRatingsPage()
    {
        Calendar calendar = Calendar.getInstance();
        this.displayReportsTicketsAndRatingsForSelectedMonth(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));// default variables to the current month and year
    }
    
    /**
     * displayReportsTicketsAndReviewForSelectedMonth()
     * @param int month// zero-based [0: Jan, 1: Feb, 11: Dec]
     * @param int year
     * @return void
     */
    private void displayReportsTicketsAndRatingsForSelectedMonth(int month, int year)
    {    
        this.clearPanels();
        this.northPanel.add(this.getPrimaryNavPanel("reports"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("reports", 1), BorderLayout.SOUTH);
        this.centerPanel.add(this.getMonthYearMenuPanel(month, year));
        // Reports gets added to centerPanel here
        this.centerPanel.add(this.getTicketsAndRatingsReportPanel(month, year));
        this.showPanels();
    }
    
    /**
     * getYearMonthMenuPanel
     * get a JPanel with JTextField for year and 12 buttons for the months 
     * The value for year is passed to the ActionListener using the formData map
     * The value for month is passed to the ActionListener using the getClientProperty
     * @int month// currently selected month , zero-based [0: Jan, 1: Feb, 11: Dec]
     * @int year// currently selected year
     */
    private JPanel getMonthYearMenuPanel(int month, int year)
    {
        
        JPanel panel = new JPanel();   
        panel.setLayout(new GridLayout(2,7,5,5));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel = this.addTextFieldToPanelWithDefaultText(20, "year", "Year (eg 2017)", Integer.toString(year), panel);
        JButton updateYearBtn = new JButton("Update Year");
        updateYearBtn.putClientProperty("month", month);
        updateYearBtn.addActionListener(reportsTicketsAndRatingsSetYearMonthActionListener);
        panel.add(updateYearBtn);
        JButton janBtn = new JButton("Jan");
        janBtn.putClientProperty("month", (int)0);
        janBtn.addActionListener(reportsTicketsAndRatingsSetYearMonthActionListener);
        panel.add(janBtn);
        JButton febBtn = new JButton("Feb");
        febBtn.putClientProperty("month", (int)1);
        febBtn.addActionListener(reportsTicketsAndRatingsSetYearMonthActionListener);
        panel.add(febBtn);
        JButton marBtn = new JButton("Mar");
        marBtn.putClientProperty("month", (int)2);
        marBtn.addActionListener(reportsTicketsAndRatingsSetYearMonthActionListener);
        panel.add(marBtn);
        JButton aprBtn = new JButton("Apr");
        aprBtn.putClientProperty("month", (int)3);
        aprBtn.addActionListener(reportsTicketsAndRatingsSetYearMonthActionListener);
        panel.add(aprBtn);
        JButton mayBtn = new JButton("May");
        mayBtn.putClientProperty("month", (int)4);
        mayBtn.addActionListener(reportsTicketsAndRatingsSetYearMonthActionListener);
        panel.add(mayBtn);
        JButton junBtn = new JButton("Jun");
        junBtn.putClientProperty("month", (int)5);
        junBtn.addActionListener(reportsTicketsAndRatingsSetYearMonthActionListener);
        panel.add(junBtn);
        JButton julBtn = new JButton("Jul");
        julBtn.putClientProperty("month", (int)6);
        julBtn.addActionListener(reportsTicketsAndRatingsSetYearMonthActionListener);
        panel.add(julBtn);
        JButton augBtn = new JButton("Aug");
        augBtn.putClientProperty("month", (int)7);
        augBtn.addActionListener(reportsTicketsAndRatingsSetYearMonthActionListener);
        panel.add(augBtn);
        JButton sepBtn = new JButton("Sep");
        sepBtn.putClientProperty("month", (int)8);
        sepBtn.addActionListener(reportsTicketsAndRatingsSetYearMonthActionListener);
        panel.add(sepBtn);
        JButton octBtn = new JButton("Oct");
        octBtn.putClientProperty("month", (int)9);
        octBtn.addActionListener(reportsTicketsAndRatingsSetYearMonthActionListener);
        panel.add(octBtn);
        JButton novBtn = new JButton("Nov");
        novBtn.putClientProperty("month", (int)10);
        novBtn.addActionListener(reportsTicketsAndRatingsSetYearMonthActionListener);
        panel.add(novBtn);
        JButton decBtn = new JButton("Dec");
        decBtn.putClientProperty("month", (int)11);
        decBtn.addActionListener(reportsTicketsAndRatingsSetYearMonthActionListener);
        panel.add(decBtn);
        
        switch(month)
        {
            case 0:
                janBtn = this.setButtonColorActive(janBtn);
                break;
            case 1:
                febBtn = this.setButtonColorActive(febBtn);
                break;
            case 2:
                marBtn = this.setButtonColorActive(marBtn);
                break;
            case 3:
                aprBtn = this.setButtonColorActive(aprBtn);
                break;
            case 4:
                mayBtn = this.setButtonColorActive(mayBtn);
                break;
            case 5:
                junBtn = this.setButtonColorActive(junBtn);
                break;
            case 6:
                julBtn = this.setButtonColorActive(julBtn);
                break;
            case 7:
                augBtn = this.setButtonColorActive(augBtn);
                break;
            case 8:
                sepBtn = this.setButtonColorActive(sepBtn);
                break;
            case 9:
                octBtn = this.setButtonColorActive(octBtn);
                break;
            case 10:
                novBtn = this.setButtonColorActive(novBtn);
                break;
            case 11:
                decBtn = this.setButtonColorActive(decBtn);
                break;
        }
        return panel;
    }
    
    /**
     * getTicketsAndRatingsReportPanel
     * @int month // zero-based [0: Jan, 1: Feb, 11: Dec]
     * @int year
     * @return JPanel
     */
    private JPanel getTicketsAndRatingsReportPanel(int month, int year)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1,5,5));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        Reporter reporter = new Reporter(this.cinema);
        System.out.println("Passing values to reporter: " + month + " " + year);
        List<TicketReport> ticketReportList = reporter.getTicketReportList(month, year);
        panel.add(new Label("REPORT - Tickets Sold and Average Rating"));// heading
        panel.add(new Label("YEAR: " + year + ", MONTH: " + ((int)month+1)));// heading
        panel.add(new Label(""));// add a margin
        if(ticketReportList.size()==0)
        {
            panel.add(new Label("No Tickets Sold"));
        }
        else
        {
            for(TicketReport ticketReport : ticketReportList)
            {
                panel.add(new Label(ticketReport.toString()));
            }   
        }
        return panel;
    }
    
    /**
     * addTextFieldToPanel
     * @param int maxLength
     * @param String title
     * @param String label
     * @return JPanel panel
     */
    private JPanel addTextFieldToPanel(int maxLength, String title, String label, JPanel panel)
    {
        JTextField textField = new JTextField(maxLength);
        this.formData.put(title, textField);
        panel.add(new JLabel(label));
        panel.add(textField);
        return panel;
    }
    
    /**
     * addTextFieldToPanel
     * @param int maxLength
     * @param String title
     * @param String label
     * @param String defaultText
     * @return JPanel panel
     */
    private JPanel addTextFieldToPanelWithDefaultText(int maxLength, String title, String label, String defaultText, JPanel panel)
    {
        JTextField textField = new JTextField(defaultText);
        textField.setColumns(maxLength);
        this.formData.put(title, textField);
        panel.add(new JLabel(label));
        panel.add(textField);
        return panel;
    }
    
    /**
     * clearPanels
     * Call removeAll on all panels
     * @return void
     */
    private void clearPanels()
    {
        this.northPanel.removeAll();
        this.centerPanel.removeAll();
        this.eastPanel.removeAll();
        this.southPanel.removeAll();
    }
    
    /**
     * clearEastPanel
     * Call removeAll on eastPanel
     * @return void
     */
    private void clearEastPanel()
    {
        this.eastPanel.removeAll();
    }
    
    /**
     * refreshCenterPanel
     * Call revalidate and repaint on eastPanel
     * @return void
     */
    private void refreshCenterPanel()
    {
        this.centerPanel.revalidate();
        this.centerPanel.repaint();
    }
    
    /**
     * refreshEastPanel
     * Call revalidate and repaint on eastPanel
     * @return void
     */
    private void refreshEastPanel()
    {
        this.eastPanel.revalidate();
        this.eastPanel.repaint();
    }
    
    /**
     * showPanels
     * Call revalidate and repaint on all panels
     * @return void
     */
    private void showPanels()
    {   
        this.frame.getContentPane().add(northPanel, BorderLayout.NORTH);
        this.frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        this.frame.getContentPane().add(eastPanel, BorderLayout.EAST);
        this.frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
        this.northPanel.revalidate();
        this.northPanel.repaint();
        this.centerPanel.revalidate();
        this.centerPanel.repaint();
        this.eastPanel.revalidate();
        this.eastPanel.repaint();
        this.southPanel.revalidate();
        this.southPanel.repaint();
    }

    /**
     * refreshList
     * Refresh the list model
     * refreshes the list with data from the system.
     * RuntimeException is caught as it is thrown if the List Model is too small
     * 
     * Adapted from: CCS course materials
     * Date: 26-DEC-2017
     * @param DefaultListModel listModel
     * @param List list
     * @return void
     */
    public void refreshList(List list) 
    {
        try
        {
            this.listModel.clear();
            int i=0;
            for(Object object : list)
            {
                this.listModel.add(i, object); 
                i++;
            }
        }
        catch (ArrayIndexOutOfBoundsException  e)
        {
            e.printStackTrace();
        } 
    }
    
    /**
     * getPrimaryNavPanel
     * @return JPanel
     */
    private JPanel getPrimaryNavPanel(String category)
    {
        // Primary Nav Panel
        JPanel panel = new JPanel();
        JButton filmsNavBtn = new JButton("Films");
        filmsNavBtn.putClientProperty("page", "filmsIndex");
        JButton showsNavBtn = new JButton("Shows");
        showsNavBtn.putClientProperty("page", "showsIndex");
        JButton customersNavBtn = new JButton("Customers");
        customersNavBtn.putClientProperty("page", "customersIndex");
        JButton bookingsNavBtn = new JButton("Bookings");
        bookingsNavBtn.putClientProperty("page", "bookingsIndex");
        JButton reportsNavBtn = new JButton("Reports");
        reportsNavBtn.putClientProperty("page", "reportsIndex");
        
        filmsNavBtn.addActionListener(NavButtonActionListener);
        showsNavBtn.addActionListener(NavButtonActionListener);
        customersNavBtn.addActionListener(NavButtonActionListener);
        bookingsNavBtn.addActionListener(NavButtonActionListener);
        reportsNavBtn.addActionListener(NavButtonActionListener);
        
        switch(category)
        {
            case "films":
                filmsNavBtn = this.setButtonColorActive(filmsNavBtn);
                break;
            case "shows":
                showsNavBtn = this.setButtonColorActive(showsNavBtn);
                break;
            case "customers":
                customersNavBtn = this.setButtonColorActive(customersNavBtn);
                break;
            case "bookings":
                bookingsNavBtn = this.setButtonColorActive(bookingsNavBtn);
                break;
            case "reports":
                reportsNavBtn = this.setButtonColorActive(reportsNavBtn);
                break;
        }
        
        panel.add(filmsNavBtn);
        panel.add(showsNavBtn);
        panel.add(customersNavBtn);
        panel.add(bookingsNavBtn);
        panel.add(reportsNavBtn);
        
        return panel;
    }   
    
    
    /**
     * getSecondaryNavPanel
     * @param String category
     * @param String subcategory
     * @return JPanel
     */
    private JPanel getSecondaryNavPanel(String category, int subPointer)
    {
        JPanel panel = new JPanel();
        // Set default label
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");
        
        // set the labels for the buttons. Only add the required buttons.
        switch(category)
        {
            case "films":
                button1.setText("Index");
                button1.putClientProperty("page", "filmsIndex");
                button2.setText("Add");
                button2.putClientProperty("page", "addFilm");
                panel.add(button1);
                panel.add(button2);
                button1.addActionListener(NavButtonActionListener);
                button2.addActionListener(NavButtonActionListener);
                break;
            case "shows":
                button1.setText("Index");
                button1.putClientProperty("page", "showsIndex");
                button2.setText("Add");
                button2.putClientProperty("page", "addShow");
                panel.add(button1);
                panel.add(button2);
                button1.addActionListener(NavButtonActionListener);
                button2.addActionListener(NavButtonActionListener);
                break;
            case "customers":
                button1.setText("Index");
                button1.putClientProperty("page", "customersIndex");
                panel.add(button1);
                button1.addActionListener(NavButtonActionListener);
                break;
            case "bookings":
                button1.setText("Index");
                button1.putClientProperty("page", "bookingsIndex");
                button2.setText("Add");
                button2.putClientProperty("page", "addBooking");
                button3.setText("Move Ticket");
                button3.putClientProperty("page", "bookingsMoveTicket");
                button4.setText("Review and Rate");
                button4.putClientProperty("page", "bookingsReviewAndRate");
                panel.add(button1);
                panel.add(button2);
                panel.add(button3);
                panel.add(button4);
                button1.addActionListener(NavButtonActionListener);
                button2.addActionListener(NavButtonActionListener);
                button3.addActionListener(NavButtonActionListener);
                button4.addActionListener(NavButtonActionListener);
                break;
            case "reports":
                button1.setText("Tickets&Ratings");
                button1.putClientProperty("page", "reportsTicketsAndRatings");
                panel.add(button1);
                button1.addActionListener(NavButtonActionListener);
                button2.setText("Income");
                button2.putClientProperty("page", "reportsIncome");
                panel.add(button2);
                button2.addActionListener(NavButtonActionListener);
                break;
        }

        // highlight the button that is currently active
        switch(subPointer)
        {
            case 1:
                button1 = this.setButtonColorActive(button1);
                break;
            case 2:
                button2 = this.setButtonColorActive(button2);
                break;
            case 3:
                button3 = this.setButtonColorActive(button3);
                break;
            case 4:
                button4 = this.setButtonColorActive(button4);
                break;
        }
        return panel;
    } 
    
    /**
     * setButtonColorActive
     * Set the background color of the button
     * so show that it is for the current page
     * 
     * Adapted from code at 
     * https://stackoverflow.com/questions/1065691
     * /how-to-set-the-background-color-of-a-jbutton-on-the-mac-os
     * 
     * Author: codethulu
     * Accessed 26-DEC-2017
     * 
     * @return JButton
     */
    private JButton setButtonColorActive(JButton button)
   {
        button.setBackground(Color.white);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }
    
    /**
     * setButtonColorActive
     * Set the background color of the button
     * so show that it is for the current page
     * 
     * Adapted from code at 
     * https://stackoverflow.com/questions/1065691
     * /how-to-set-the-background-color-of-a-jbutton-on-the-mac-os
     * 
     * Author: codethulu
     * Accessed 26-DEC-2017
     * 
     * @return JButton
     */
    private JButton setButtonColorAccent(JButton button)
   {
        button.setBackground(Color.cyan);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }
    
    /**
     * setButtonColorActive
     * Set the background color of the button
     * so show that it is for the current page
     * 
     * Adapted from code at 
     * https://stackoverflow.com/questions/1065691
     * /how-to-set-the-background-color-of-a-jbutton-on-the-mac-os
     * 
     * Author: codethulu
     * Accessed 26-DEC-2017
     * 
     * @return JButton
     */
    private JButton setButtonColorDull(JButton button)
   {
        button.setBackground(Color.lightGray);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }
    
    // All MouseListeners and ActionListeners
    // adapted from: CCS course materials
    // Date: 26-DEC-2017

    /**
     * Method mouseClicked on JList selection
     * If the mouse is clicked once at an item in the JList, the eastPanel is 
     * updated with the details for the list
     * @param e object holding mouse event information
     */    
    
    MouseListener filmsIndexListMouseListener = new MouseAdapter()
    {
        public void mouseClicked(MouseEvent e)
        {            
            if (e.getClickCount() == 1) 
            {
                int index = indexList.locationToIndex(e.getPoint());
                Film film = (Film) listModel.elementAt(index);
                updateEastPanelFilmsIndex(film);
            }
        }
    };    
    
    MouseListener showsIndexListMouseListener = new MouseAdapter()
    {
        public void mouseClicked(MouseEvent e)
        {            
            if (e.getClickCount() == 1) 
            {
                int index = indexList.locationToIndex(e.getPoint());
                Show show = (Show) listModel.elementAt(index);
                updateEastPanelShowsIndex(show);
            }
        }
    };    
    
    MouseListener addShowSelectFilmListMouseListener = new MouseAdapter()
    {
        public void mouseClicked(MouseEvent e)
        {            
            if (e.getClickCount() == 1) 
            {
                int index = indexList.locationToIndex(e.getPoint());
                Film film = (Film) listModel.elementAt(index);
                updateEastPanelAddShowSelectFilm(film);
            }
        }
    };
    
    MouseListener addShowSelectScreenListMouseListener = new MouseAdapter()
    {
        public void mouseClicked(MouseEvent e)
        {            
            if (e.getClickCount() == 1) 
            {
                int index = indexList.locationToIndex(e.getPoint());
                Screen screen = (Screen) listModel.elementAt(index);
                updateEastPanelAddShowSelectScreen(screen);
            }
        }
    };
    
    MouseListener customersIndexListMouseListener = new MouseAdapter()
    {
        public void mouseClicked(MouseEvent e)
        {            
            if (e.getClickCount() == 1) 
            {
                int index = indexList.locationToIndex(e.getPoint());
                Customer customer = (Customer) listModel.elementAt(index);
                updateEastPanelCustomersIndex(customer);
            }
        }
    };
    
    MouseListener bookingsIndexListMouseListener = new MouseAdapter()
    {
        public void mouseClicked(MouseEvent e)
        {            
            if (e.getClickCount() == 1) 
            {
                int index = indexList.locationToIndex(e.getPoint());
                Booking booking = (Booking) listModel.elementAt(index);
                updateEastPanelBookingsIndex(booking);
            }
        }
    };    
    
    MouseListener addBookingSelectShowListMouseListener = new MouseAdapter()
    {
        public void mouseClicked(MouseEvent e)
        {            
            if (e.getClickCount() == 1) 
            {
                int index = indexList.locationToIndex(e.getPoint());
                Show show = (Show) listModel.elementAt(index);
                updateEastPanelAddBookingSelectShow(show);
            }
        }
    };   
    
    MouseListener bookingsMoveTicketSelectShowListMouseListener = new MouseAdapter()
    {
        public void mouseClicked(MouseEvent e)
        {            
            if (e.getClickCount() == 1) 
            {
                int index = indexList.locationToIndex(e.getPoint());
                Show show = (Show) listModel.elementAt(index);
                updateEastPanelBookingsMoveTicketSelectShow(show);
            }
        }
    }; 
   
    
    ActionListener NavButtonActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {      
            // Use of the getClientProperty to identify the button where the event originated
            // and redirect the user to that page. 
            // Code adapted from:
            // https://stackoverflow.com/questions/11037622/pass-variables-to-actionlistener-in-java
            // Author: Robin
            // Accessed: 26-DEC-2017
            
            String pageRequested = (String)((JButton)e.getSource()).getClientProperty("page");
            switch(pageRequested)
            {
                case "filmsIndex":
                    displayFilmsIndexPage();
                    break;
                case "addFilm":
                    displayAddFilmPage();
                    break;
                case "showsIndex":
                    displayShowsIndexPage();
                    break;
                case "addShow":
                    displayAddShowPage();
                    break;
                case "customersIndex":
                    displayCustomersIndexPage();
                    break;
                case "bookingsIndex":
                    displayBookingsIndexPage();
                    break;
                case "addBooking":
                    displayAddBookingPage();
                    break;
                case "bookingsMoveTicket":
                    displayBookingsMoveTicketPage();
                    break;
                case "bookingsReviewAndRate":
                    displayBookingsReviewAndRatePage();
                    break;
                case "reportsIndex":
                    displayReportsPage();
                    break;
                case "reportsTicketsAndRatings":
                    displayReportsTicketsAndRatingsPage();
                    break;
                // case "reportsIncome":
                    // displayReportsIncomePage();
                    // break;
            }
        }
    };
    
    ActionListener addFilmActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {      
            JTextField title = (JTextField)formData.get("title");
            JTextField director = (JTextField)formData.get("director");
            JTextField language = (JTextField)formData.get("language");
            JTextField subtitles = (JTextField)formData.get("subtitles");
            JTextField yearTextField= (JTextField)formData.get("year");
            int yearInt = 0;
            
            if(!isLengthInRange(title.getText(),3,20))
            {
                return;
            }
            
            if(!isLengthInRange(director.getText(),3,20))
            {
                return;
            }
            
            if(!isLengthInRange(language.getText(),3,20))
            {
                return;
            }
            
            if(!isLengthInRange(subtitles.getText(),1,2))
            {
                return;
            }

            try
            {
                yearInt = Integer.parseInt(yearTextField.getText());
            } catch (NumberFormatException ex) {
                return;
            }
            cinema.addFilm(title.getText(), yearInt, director.getText(), language.getText(), subtitles.getText());
            displayFilmsIndexPage();
            return;
        }
    };
    
    ActionListener addShowActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {      
            Film film;
            Screen screen;
            if(formData.get("tempFilm")!=null)//formData stores a temporary film when eastPanel loads. Move it to "film" key to confirm
            {
                formData.put("film", formData.get("tempFilm"));
                formData.remove("tempFilm");
                film = (Film)formData.get("film");
            }
            else if(formData.get("film")!=null)
            {
                film = (Film)formData.get("film");
            }
            else
            {
                displayAddShowSelectFilm();
                return;
            }
            
            if(formData.get("tempScreen")!=null)
            {
                formData.put("screen", formData.get("tempScreen"));
                formData.remove("tempScreen");
                screen = (Screen)formData.get("screen");
            }
            else if(formData.get("screen")!=null)
            {
                screen = (Screen)formData.get("screen");
            }
            else
            {
                displayAddShowSelectScreen();
                return;
            }
            
            int yearInt = 0;
            int monthInt = 0;
            int dateInt = 0;
            int hourInt = 0;
            int minuteInt = 0;
            float priceRegularFloat = (float)0.0;
            float priceVipFloat = (float)0.0;
            Calendar date;
            
            if(formData.get("detailsEntered")!=null)
            {
                try
                {
                    JTextField yearTextField = (JTextField)formData.get("year");
                    yearInt = Integer.parseInt(yearTextField.getText());
                    JTextField monthTextField = (JTextField)formData.get("month");
                    monthInt = Integer.parseInt(monthTextField.getText());
                    JTextField dateTextField = (JTextField)formData.get("date");
                    dateInt = Integer.parseInt(dateTextField.getText());
                    JTextField hourTextField = (JTextField)formData.get("hour");
                    hourInt = Integer.parseInt(hourTextField.getText());
                    JTextField minuteTextField = (JTextField)formData.get("minute");
                    minuteInt = Integer.parseInt(minuteTextField.getText());
                    JTextField priceRegularTextField = (JTextField)formData.get("priceRegular");
                    priceRegularFloat = Float.parseFloat(priceRegularTextField.getText());
                    JTextField priceVipTextField = (JTextField)formData.get("priceVip");
                    priceVipFloat = Float.parseFloat(priceVipTextField.getText());   
                } catch (Exception ex) {
                    return;
                }
                
                if(yearInt>2050||yearInt<2000||monthInt>12||monthInt<1||dateInt>31||dateInt<1||hourInt>23||hourInt<0||minuteInt>59||minuteInt<0||priceRegularFloat>500.0||priceRegularFloat<0.0||priceVipFloat>500.0||priceVipFloat<0.0)
                {
                    return;
                }
                screen = (Screen)formData.get("screen");
            }
            else
            {
                displayAddShowEnterDetails();
                return;
            }
            date = new GregorianCalendar(yearInt, monthInt, dateInt, hourInt, minuteInt);// set the date with the user input
            cinema.addShow(date, screen, film, (float)priceRegularFloat, (float)priceVipFloat);// add the new show to the database
            formData = new HashMap<String, Object>();// reset the formData storage. 
            displayShowsIndexPage();// redirect to the shows index
            return;
        }
    };
    

    
    ActionListener addBookingSelectCustomerTypeActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            String customerType = (String)((JButton)e.getSource()).getClientProperty("customerType");
            switch(customerType)
            {
                case "existingCustomer":
                    displayAddBookingEnterCustomerId();
                    return;
                case "newCustomer":
                    displayAddBookingNewCustomer();
                    break;
                default:
                    displayAddBookingSelectCustomerType();
                    return;
            }  
        }
    };
    
    ActionListener addBookingSetNewCustomerActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            if(formData.get("customerName")==null)
            {
                displayAddBookingSelectCustomerType();
                return;
            }
            JTextField customerNameTextField = (JTextField)formData.get("customerName");
            Customer customer = cinema.getNewCustomer(customerNameTextField.getText());
            formData.put("customer", customer);
            displayAddBookingSelectShow();
            return;
        }
    };
    
    
    ActionListener addBookingSetExistingCustomerActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            if(formData.get("customerId")==null)
            {
                displayAddBookingSelectCustomerType();
                return;
            }
            JTextField customerIdTextField = (JTextField)formData.get("customerId");
            int customerId;
            try
            {
                customerId = Integer.parseInt(customerIdTextField.getText());
            } catch (NumberFormatException ex) {
                JFrame popupFrame = new JFrame("Checkout");
                JOptionPane.showMessageDialog(popupFrame, "CustomerId entered is not a number.");
                displayAddBookingSelectCustomerType();
                return;
            }
            Customer customer;
            try
            {
                customer = cinema.getCustomer(customerId);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
                displayAddBookingSelectCustomerType();
                return;
            }
            formData.put("customer", customer);
            displayAddBookingSelectShow();
            return;
        }
    };
    
    ActionListener addBookingSelectPaymentTypeActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            if(formData.get("booker")==null)
            {
                JOptionPane.showMessageDialog(frame, "Booking Cancelled");
                displayBookingsIndexPage();
                return;
            }
            Booker booker = (Booker)formData.get("booker");
            String paymentType = (String)((JButton)e.getSource()).getClientProperty("paymentType");
            int reply = 0;
            switch(paymentType)
            {
                case "cash":
                    reply = JOptionPane.showConfirmDialog(null, "Proceed with Cash Payment?", "Checkout", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        formData.put("seatSelectionComplete", "true");
                        booker.finalizeCashPayment();
                        JOptionPane.showMessageDialog(frame, "Cash Payment Accepted.\nBooking Process Completed Successfully.");
                        displayBookingsIndexPage();
                        return;
                    }
                    displayAddBookingSelectSeats(booker);
                    return;
                case "card":
                    reply = JOptionPane.showConfirmDialog(null, "Proceed with Card Payment?", "Checkout", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        formData.put("seatSelectionComplete", "true");
                        JFrame popupFrame = new JFrame("Checkout");
                        String refNum = JOptionPane.showInputDialog(popupFrame, "Enter Card-payment Reference Number");
                        booker.finalizeCardPayment(refNum);
                        JOptionPane.showMessageDialog(popupFrame, "Cash Payment Accepted.\nBooking Process Completed Successfully."); 
                        displayBookingsIndexPage();
                        return;
                    }
            }
            displayAddBookingSelectSeats(booker);// only reached if 'Proceed with payment' was answered 'No'
            return;
        }
    };
    
    ActionListener addBookingActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {      
            Customer customer;
            Show show;
            Booker booker;
            
            //before making the booker, we need a show and a customer
            if(formData.get("customer")==null)
            {
                displayAddBookingSelectCustomerType();
                return;
            }
            customer = (Customer)formData.get("customer");// customer has been set
            
            if(formData.get("tempShow")!=null)//formData stores a temporary show when eastPanel loads. Move it to "show" key to confirm
            {
                formData.put("show", formData.get("tempShow"));
                formData.remove("tempShow");
                show = (Show)formData.get("show");
            }
            else if(formData.get("show")!=null)
            {
                show = (Show)formData.get("show");
            }
            else
            {
                displayAddBookingSelectShow();
                return;
            }
            
            // Ensure that the booker is only created in the first pass
            if(formData.get("booker")==null)
            { 
                booker = cinema.getNewBooker(show, customer);  
                formData.put("booker", booker);
            }
            else
            {
                booker = (Booker)formData.get("booker");
            }
            
            if(formData.get("seatSelectionComplete")==null)
            {
                String seatSelected = (String)((JButton)e.getSource()).getClientProperty("seatSelected");
                int row = 0;
                int num = 0;
                if(seatSelected!=null)
                {
                    switch(seatSelected.substring(0, 1))
                    {
                        case "A":
                            row = 1;
                            break;
                        case "B":
                            row = 2;
                            break;
                        case "C":
                            row = 3;
                            break;
                        case "D":
                            row = 4;
                            break;
                        case "E":
                            row = 5;
                            break;
                    }
                    
                    num = Integer.parseInt(seatSelected.substring(1));// No need for try/catch because the input only comes from seatingGrid buttons
                    
                    if(booker.isExistReservation(row, num))
                    {
                        booker.removeReservation(row, num);
                    }
                    else
                    {
                        booker.addReservation(row, num);    
                    }
                }
                displayAddBookingSelectSeats(booker);// acts as a loop until the seat selection process is complete
                return;
            }
            
            if(formData.get("proceedWithPayment")==null)
            {
                formData.remove("seatSelectionComplete");
                displayAddBookingSelectSeats(booker);// seat selection form has the cash/card option buttons
                return;
            }
        }
    };
    

    ActionListener bookingsMoveTicketActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            Transferer transferer;
            Ticket ticket;
            Show show;
            
            if(formData.get("ticketId")==null)
            {
                displayBookingsMoveTicketPage();
                return;
            }
            JTextField ticketIdTextField = (JTextField)formData.get("ticketId");
            int ticketId = 0;
            try
            {
                ticketId = Integer.parseInt(ticketIdTextField.getText());
            } catch (NumberFormatException ex) {
                JFrame popupFrame = new JFrame("Invalid Input");
                JOptionPane.showMessageDialog(popupFrame, "TicketId entered is not a number.");
                displayBookingsMoveTicketPage();
                return;
            }
            
            try
            {
                ticket = cinema.getTicket(ticketId);
            } catch (IllegalArgumentException ex) {
                JFrame popupFrame = new JFrame("Invalid Input");
                JOptionPane.showMessageDialog(popupFrame, ex.getMessage());
                displayBookingsMoveTicketPage();
                return;
            }
            formData.put("ticket", ticket);
            
            if(formData.get("tempShow")!=null)//formData stores a temporary show when eastPanel loads. Move it to "show" key to confirm
            {
                formData.put("show", formData.get("tempShow"));
                formData.remove("tempShow");
                show = (Show)formData.get("show");
            }
            else if(formData.get("show")!=null)
            {
                show = (Show)formData.get("show");
            }
            else
            {
                displayBookingsMoveTicketSelectShow();
                return;
            }
            
            // Ensure that the transferer is only created in the first pass
            if(formData.get("transferer")==null)
            { 
                transferer = cinema.getNewTransferer(show, ticket);  
                formData.put("transferer", transferer);
            }
            else
            {
                transferer = (Transferer)formData.get("transferer");
            }

            if(formData.get("seatSelectionComplete")==null)
            {
                String seatSelected = (String)((JButton)e.getSource()).getClientProperty("seatSelected");
                int row = 0;
                int num = 0;
                if(seatSelected!=null)
                {
                    switch(seatSelected.substring(0, 1))
                    {
                        case "A":
                            row = 1;
                            break;
                        case "B":
                            row = 2;
                            break;
                        case "C":
                            row = 3;
                            break;
                        case "D":
                            row = 4;
                            break;
                        case "E":
                            row = 5;
                            break;
                    }
                    
                    num = Integer.parseInt(seatSelected.substring(1));// No need for try/catch because the input only comes from seatingGrid buttons
                    
                    transferer.setReservation(row, num);// when a new reservation is set, transferer automatically overrides the old reservation
                }
                displayBookingsMoveTicketSelectSeat(transferer);
                return;
            }
            if(formData.get("proceedWithPayment")==null)
            {
                formData.remove("seatSelectionComplete");
                displayBookingsMoveTicketSelectSeat(transferer);// seat selection form has the cash/card option buttons
                return;
            }
        }
    };
    
    ActionListener bookingsMoveTicketSelectPaymentTypeActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            if(formData.get("transferer")==null)
            {
                JOptionPane.showMessageDialog(frame, "Transfer Cancelled");
                displayBookingsIndexPage();
                return;
            }
            Transferer transferer = (Transferer)formData.get("transferer");
            String paymentType = "";
            if(transferer.getSurcharge()>0)
            {
                Object[] options = {"Cash",
                "Card"};
                JFrame popupFrame = new JFrame("Checkout");
                int reply = JOptionPane.showOptionDialog(frame,"Select Payment Method","Checkout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]); //default button title
                
                if (reply == JOptionPane.YES_OPTION) {
                    formData.put("seatSelectionComplete", "true");
                    transferer.finalizeCashPayment();
                    JOptionPane.showMessageDialog(frame, "Cash Payment Accepted.\nTranfer Process Completed Successfully.");
                    displayBookingsIndexPage();
                    return;
                }
                else
                {
                    formData.put("seatSelectionComplete", "true");
                    String refNum = JOptionPane.showInputDialog(popupFrame, "Enter Card-payment Reference Number");
                    transferer.finalizeCardPayment(refNum);
                    JOptionPane.showMessageDialog(popupFrame, "Cash Payment Accepted.\nTransfer Process Completed Successfully."); 
                    displayBookingsIndexPage();
                    return;
                }
            }
            else
            {
                transferer.finalizeNoChargeTransfer();
                JFrame popupFrame = new JFrame("Checkout");
                JOptionPane.showMessageDialog(popupFrame, "Transfer Process Completed Successfully."); 
                displayBookingsIndexPage();
                return;
            }
        }
    };
    
    ActionListener bookingsReviewAndRateActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            String review;
            int rating;
            Ticket ticket;
            int ticketId;
            
            if(formData.get("ticketId")==null||formData.get("review")==null||formData.get("rating")==null)
            {
                JFrame popupFrame = new JFrame("Invalid Input");
                JOptionPane.showMessageDialog(popupFrame, "All fields must be completed.");
                displayBookingsReviewAndRatePage();
                return;
            }
            JTextField ticketIdTextField = (JTextField)formData.get("ticketId");
            
            try
            {
                ticketId = Integer.parseInt(ticketIdTextField.getText());
            } catch (NumberFormatException ex) {
                JFrame popupFrame = new JFrame("Invalid Input");
                JOptionPane.showMessageDialog(popupFrame, "TicketId entered is not a number.");
                displayBookingsReviewAndRatePage();
                return;
            }
            
            try
            {
                ticket = cinema.getTicket(ticketId);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
                displayBookingsReviewAndRatePage();
                return;
            }
            
            JTextField reviewTextField = (JTextField)formData.get("review");
            if(reviewTextField.getText().length()>255)
            {
                JFrame popupFrame = new JFrame("Invalid Input");
                JOptionPane.showMessageDialog(popupFrame, "Review must be less than 255 characters.");
                displayBookingsReviewAndRatePage();
                return;
            }
            review = reviewTextField.getText();
            
            JTextField ratingTextField = (JTextField)formData.get("rating");
            try
            {
                rating = Integer.parseInt(ratingTextField.getText());
            } catch (NumberFormatException ex) {
                JFrame popupFrame = new JFrame("Invalid Input");
                JOptionPane.showMessageDialog(popupFrame, "Rating entered is not a number.");
                displayBookingsReviewAndRatePage();
                return;
            }
            
            if(rating<1||rating>5)
            {
                JFrame popupFrame = new JFrame("Invalid Input");
                JOptionPane.showMessageDialog(popupFrame, "Rating must be between 1 and 5.");
                displayBookingsReviewAndRatePage();
                return;
            }
            
            cinema.addReview(ticket, review, rating);
            JFrame popupFrame = new JFrame("Review and Rating");
            JOptionPane.showMessageDialog(popupFrame, "Review and Rating added successfully.");
            displayBookingsReviewAndRatePage();
            return;
        }
    };
    
    ActionListener reportsTicketsAndRatingsSetYearMonthActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            if(formData.get("year")==null)
            {
                displayReportsTicketsAndRatingsPage();
                return;
            }
            System.out.println("reportsTicketsAndRatingsSetYearMonthActionListener reached...");
            if(((JButton)e.getSource()).getClientProperty("month")==null)
            {
                displayReportsTicketsAndRatingsPage();
                return;
            }
            int year = 0;
            int month = (Integer)((JButton)e.getSource()).getClientProperty("month");
            
            System.out.println("reportsTicketsAndRatingsSetYearMonthActionListener month set...");
            
            JTextField yearTextField = (JTextField)formData.get("year");
            try
            {
                year = Integer.parseInt(yearTextField.getText());
            } catch (NumberFormatException ex) {
                JFrame popupFrame = new JFrame("Invalid Input");
                JOptionPane.showMessageDialog(popupFrame, "Year entered is not a number.");
                displayReportsTicketsAndRatingsPage();
                return;
            }
            
            if(year<2000|year>2050)
            {
                JFrame popupFrame = new JFrame("Invalid Input");
                JOptionPane.showMessageDialog(popupFrame, "Year must be between 2000 and 2050.");
                displayReportsTicketsAndRatingsPage();
                return;
            } 
            
            System.out.println("reportsTicketsAndRatingsSetYearMonthActionListener month and year set..." + year + " " + month);
            displayReportsTicketsAndRatingsForSelectedMonth(month,year);
            return;
        }
    };
    
    /**
     * isLengthInRange
     * @param String 
     * @param int minLength
     * @param int maxLength
     * @return boolean
     */
    private boolean isLengthInRange(String string, int minLength, int maxLength)
    {
        return(string.length()>=minLength&&string.length()<=maxLength);
    }
   
    /**
     * getSeatingGridPanel
     * 
     * Create a JPanel version of the seatingGrid
     * @param boolean[][] seatingGrid
     * @return JPanel seatingGridPanel
     */
    public JPanel getSeatingGridPanel(boolean[][] seatingGrid)
    {
        JPanel panel = new JPanel();
        List<JLabel> gridList = new ArrayList<JLabel>();
        panel.setLayout(new GridLayout(7,11,5,5));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        gridList.add(new JLabel(""));
        gridList.add(new JLabel("["));
        gridList.add(new JLabel("["));
        gridList.add(new JLabel("S"));
        gridList.add(new JLabel("C"));
        gridList.add(new JLabel("R"));
        gridList.add(new JLabel("E"));
        gridList.add(new JLabel("E"));
        gridList.add(new JLabel("N"));
        gridList.add(new JLabel("]"));
        gridList.add(new JLabel("]"));

        for (int i = 0;i<seatingGrid.length;i++)
        {   
            String rowLetter;
            rowLetter = this.cinema.convertToRowLetter(i+1);
            
            // rowLetter
            gridList.add(new JLabel(rowLetter));
            
            for (int j = 0; j < seatingGrid[i].length;j++)
            {
                String seat = "[_]";
                if(seatingGrid[i][j])
                {
                    seat = "[X]";
                }
                gridList.add(new JLabel(seat));
            }
        }
        // seat numbers
        gridList.add(new JLabel(""));
        gridList.add(new JLabel("1"));
        gridList.add(new JLabel("2"));
        gridList.add(new JLabel("3"));
        gridList.add(new JLabel("4"));
        gridList.add(new JLabel("5"));
        gridList.add(new JLabel("6"));
        gridList.add(new JLabel("7"));
        gridList.add(new JLabel("8"));
        gridList.add(new JLabel("9"));
        gridList.add(new JLabel("10"));
        
        for(JLabel label : gridList)
        {
            panel.add(label);
        }
        return panel;
    }
   
    /**
     * getSeatingGridPanel
     * 
     * Create a JPanel version of the seatingGrid
     * Accepts a String param booker or transferer so it 
     * can be used form both
     * @param boolean[][] seatingGrid
     * @param String {booker, transferer}
     * @return JPanel seatingGridPanel
     */
    public JPanel getSeatingGridButtons(boolean[][] seatingGrid, String ticketManager)
    {
        JPanel panel = new JPanel();
        List<JButton> gridList = new ArrayList<JButton>();
        panel.setLayout(new GridLayout(0,11,5,5));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        JButton screen0 = new JButton("");
        screen0 = setButtonColorAccent(screen0);
        gridList.add(screen0);
        JButton screen1 = new JButton("[");
        screen1 = setButtonColorAccent(screen1);
        gridList.add(screen1);
        JButton screen2 = new JButton("[");
        screen2 = setButtonColorAccent(screen2);
        gridList.add(screen2);
        JButton screen3 = new JButton("S");
        screen3 = setButtonColorAccent(screen3);
        gridList.add(screen3);
        JButton screen4 = new JButton("C");
        screen4 = setButtonColorAccent(screen4);
        gridList.add(screen4);
        JButton screen5 = new JButton("R");
        screen5 = setButtonColorAccent(screen5);
        gridList.add(screen5);
        JButton screen6 = new JButton("E");
        screen6 = setButtonColorAccent(screen6);
        gridList.add(screen6);
        JButton screen7 = new JButton("E");
        screen7 = setButtonColorAccent(screen7);
        gridList.add(screen7);
        JButton screen8 = new JButton("N");
        screen8 = setButtonColorAccent(screen8);
        gridList.add(screen8);
        JButton screen9 = new JButton("]");
        screen9 = setButtonColorAccent(screen9);
        gridList.add(screen9);
        JButton screen10 = new JButton("]");
        screen10 = setButtonColorAccent(screen10);
        gridList.add(screen10);
        
        // row A
        JButton a = new JButton("A");
        a = setButtonColorDull(a);
        gridList.add(a);
        JButton a1 = new JButton("[_]");
        if(seatingGrid[0][0]){a1.setLabel("[X]");};
        a1.putClientProperty("seatSelected", "A1");
        gridList.add(a1);
        JButton a2 = new JButton("[_]");
        if(seatingGrid[0][1]){a2.setLabel("[X]");};
        a2.putClientProperty("seatSelected", "A2");
        gridList.add(a2);
        JButton a3 = new JButton("[_]");
        if(seatingGrid[0][2]){a3.setLabel("[X]");};
        a3.putClientProperty("seatSelected", "A3");
        gridList.add(a3);
        JButton a4 = new JButton("[_]");
        if(seatingGrid[0][3]){a4.setLabel("[X]");};
        a4.putClientProperty("seatSelected", "A4");
        gridList.add(a4);
        JButton a5 = new JButton("[_]");
        if(seatingGrid[0][4]){a5.setLabel("[X]");};
        a5.putClientProperty("seatSelected", "A5");
        gridList.add(a5);
        JButton a6 = new JButton("[_]");
        if(seatingGrid[0][5]){a6.setLabel("[X]");};
        a6.putClientProperty("seatSelected", "A6");
        gridList.add(a6);
        JButton a7 = new JButton("[_]");
        if(seatingGrid[0][6]){a7.setLabel("[X]");};
        a7.putClientProperty("seatSelected", "A7");
        gridList.add(a7);
        JButton a8 = new JButton("[_]");
        if(seatingGrid[0][7]){a8.setLabel("[X]");};
        a8.putClientProperty("seatSelected", "A8");
        gridList.add(a8);
        JButton a9 = new JButton("[_]");
        if(seatingGrid[0][8]){a9.setLabel("[X]");};
        a9.putClientProperty("seatSelected", "A9");
        gridList.add(a9);
        JButton a10 = new JButton("[_]");
        if(seatingGrid[0][9]){a10.setLabel("[X]");};
        a10.putClientProperty("seatSelected", "A10");
        gridList.add(a10);
        
        // row B
        JButton b = new JButton("B");
        b = setButtonColorDull(b);
        gridList.add(b);
        JButton b1 = new JButton("[_]");
        if(seatingGrid[1][0]){b1.setLabel("[X]");};
        b1.putClientProperty("seatSelected", "B1");
        gridList.add(b1);
        JButton b2 = new JButton("[_]");
        if(seatingGrid[1][1]){b2.setLabel("[X]");};
        b2.putClientProperty("seatSelected", "B2");
        gridList.add(b2);
        JButton b3 = new JButton("[_]");
        if(seatingGrid[1][2]){b3.setLabel("[X]");};
        b3.putClientProperty("seatSelected", "B3");
        gridList.add(b3);
        JButton b4 = new JButton("[_]");
        if(seatingGrid[1][3]){b4.setLabel("[X]");};
        b4.putClientProperty("seatSelected", "B4");
        gridList.add(b4);
        JButton b5 = new JButton("[_]");
        if(seatingGrid[1][4]){b5.setLabel("[X]");};
        b5.putClientProperty("seatSelected", "B5");
        gridList.add(b5);
        JButton b6 = new JButton("[_]");
        if(seatingGrid[1][5]){b6.setLabel("[X]");};
        b6.putClientProperty("seatSelected", "B6");
        gridList.add(b6);
        JButton b7 = new JButton("[_]");
        if(seatingGrid[1][6]){b7.setLabel("[X]");};
        b7.putClientProperty("seatSelected", "B7");
        gridList.add(b7);
        JButton b8 = new JButton("[_]");
        if(seatingGrid[1][7]){b8.setLabel("[X]");};
        b8.putClientProperty("seatSelected", "B8");
        gridList.add(b8);
        JButton b9 = new JButton("[_]");
        if(seatingGrid[1][8]){b9.setLabel("[X]");};
        b9.putClientProperty("seatSelected", "B9");
        gridList.add(b9);
        JButton b10 = new JButton("[_]");
        if(seatingGrid[1][9]){b10.setLabel("[X]");};
        b10.putClientProperty("seatSelected", "B10");
        gridList.add(b10);
        
        // row C
        JButton c = new JButton("C");
        c = setButtonColorDull(c);
        gridList.add(c);
        JButton c1 = new JButton("[_]");
        if(seatingGrid[2][0]){c1.setLabel("[X]");};
        c1.putClientProperty("seatSelected", "C1");
        gridList.add(c1);
        JButton c2 = new JButton("[_]");
        if(seatingGrid[2][1]){c2.setLabel("[X]");};
        c2.putClientProperty("seatSelected", "C2");
        gridList.add(c2);
        JButton c3 = new JButton("[_]");
        if(seatingGrid[2][2]){c3.setLabel("[X]");};
        c3.putClientProperty("seatSelected", "C3");
        gridList.add(c3);
        JButton c4 = new JButton("[_]");
        if(seatingGrid[2][3]){c4.setLabel("[X]");};
        c4.putClientProperty("seatSelected", "C4");
        gridList.add(c4);
        JButton c5 = new JButton("[_]");
        if(seatingGrid[2][4]){c5.setLabel("[X]");};
        c5.putClientProperty("seatSelected", "C5");
        gridList.add(c5);
        JButton c6 = new JButton("[_]");
        if(seatingGrid[2][5]){c6.setLabel("[X]");};
        c6.putClientProperty("seatSelected", "C6");
        gridList.add(c6);
        JButton c7 = new JButton("[_]");
        if(seatingGrid[2][6]){c7.setLabel("[X]");};
        c7.putClientProperty("seatSelected", "C7");
        gridList.add(c7);
        JButton c8 = new JButton("[_]");
        if(seatingGrid[2][7]){c8.setLabel("[X]");};
        c8.putClientProperty("seatSelected", "C8");
        gridList.add(c8);
        JButton c9 = new JButton("[_]");
        if(seatingGrid[2][8]){c9.setLabel("[X]");};
        c9.putClientProperty("seatSelected", "C9");
        gridList.add(c9);
        JButton c10 = new JButton("[_]");
        if(seatingGrid[2][9]){c10.setLabel("[X]");};
        c10.putClientProperty("seatSelected", "C10");
        gridList.add(c10);
        
        // row D
        JButton d = new JButton("D");
        d = setButtonColorDull(d);
        gridList.add(d);
        JButton d1 = new JButton("[_]");
        if(seatingGrid[3][0]){d1.setLabel("[X]");};
        d1.putClientProperty("seatSelected", "D1");
        gridList.add(d1);
        JButton d2 = new JButton("[_]");
        if(seatingGrid[3][1]){d2.setLabel("[X]");};
        d2.putClientProperty("seatSelected", "D2");
        gridList.add(d2);
        JButton d3 = new JButton("[_]");
        if(seatingGrid[3][2]){d3.setLabel("[X]");};
        d3.putClientProperty("seatSelected", "D3");
        gridList.add(d3);
        JButton d4 = new JButton("[_]");
        if(seatingGrid[3][3]){d4.setLabel("[X]");};
        d4.putClientProperty("seatSelected", "D4");
        gridList.add(d4);
        JButton d5 = new JButton("[_]");
        if(seatingGrid[3][4]){d5.setLabel("[X]");};
        d5.putClientProperty("seatSelected", "D5");
        gridList.add(d5);
        JButton d6 = new JButton("[_]");
        if(seatingGrid[3][5]){d6.setLabel("[X]");};
        d6.putClientProperty("seatSelected", "D6");
        gridList.add(d6);
        JButton d7 = new JButton("[_]");
        if(seatingGrid[3][6]){d7.setLabel("[X]");};
        d7.putClientProperty("seatSelected", "D7");
        gridList.add(d7);
        JButton d8 = new JButton("[_]");
        if(seatingGrid[3][7]){d8.setLabel("[X]");};
        d8.putClientProperty("seatSelected", "D8");
        gridList.add(d8);
        JButton d9 = new JButton("[_]");
        if(seatingGrid[3][8]){d9.setLabel("[X]");};
        d9.putClientProperty("seatSelected", "D9");
        gridList.add(d9);
        JButton d10 = new JButton("[_]");
        if(seatingGrid[3][9]){d10.setLabel("[X]");};
        d10.putClientProperty("seatSelected", "D10");
        gridList.add(d10);
        
        // row E
        JButton e = new JButton("E");
        e = setButtonColorDull(e);
        gridList.add(e);
        JButton e1 = new JButton("[_]");
        if(seatingGrid[4][0]){e1.setLabel("[X]");};
        e1.putClientProperty("seatSelected", "E1");
        gridList.add(e1);
        JButton e2 = new JButton("[_]");
        if(seatingGrid[4][1]){e2.setLabel("[X]");};
        e2.putClientProperty("seatSelected", "E2");
        gridList.add(e2);
        JButton e3 = new JButton("[_]");
        if(seatingGrid[4][2]){e3.setLabel("[X]");};
        e3.putClientProperty("seatSelected", "E3");
        gridList.add(e3);
        JButton e4 = new JButton("[_]");
        if(seatingGrid[4][3]){e4.setLabel("[X]");};
        e4.putClientProperty("seatSelected", "E4");
        gridList.add(e4);
        JButton e5 = new JButton("[_]");
        if(seatingGrid[4][4]){e5.setLabel("[X]");};
        e5.putClientProperty("seatSelected", "E5");
        gridList.add(e5);
        JButton e6 = new JButton("[_]");
        if(seatingGrid[4][5]){e6.setLabel("[X]");};
        e6.putClientProperty("seatSelected", "E6");
        gridList.add(e6);
        JButton e7 = new JButton("[_]");
        if(seatingGrid[4][6]){e7.setLabel("[X]");};
        e7.putClientProperty("seatSelected", "E7");
        gridList.add(e7);
        JButton e8 = new JButton("[_]");
        if(seatingGrid[4][7]){e8.setLabel("[X]");};
        e8.putClientProperty("seatSelected", "E8");
        gridList.add(e8);
        JButton e9 = new JButton("[_]");
        if(seatingGrid[4][8]){e9.setLabel("[X]");};
        e9.putClientProperty("seatSelected", "E9");
        gridList.add(e9);
        JButton e10 = new JButton("[_]");
        if(seatingGrid[4][9]){e10.setLabel("[X]");};
        e10.putClientProperty("seatSelected", "E10");
        gridList.add(e10);

        // seat numbers
        JButton blank = new JButton("");
        blank = setButtonColorDull(blank);
        gridList.add(blank);
        JButton one = new JButton("1");
        one = setButtonColorDull(one);
        gridList.add(one);
        JButton two = new JButton("2");
        two = setButtonColorDull(two);
        gridList.add(two);
        JButton three = new JButton("3");
        three = setButtonColorDull(three);
        gridList.add(three);
        JButton four = new JButton("4");
        four = setButtonColorDull(four);
        gridList.add(four);
        JButton five = new JButton("5");
        five = setButtonColorDull(five);
        gridList.add(five);
        JButton six = new JButton("6");
        six = setButtonColorDull(six);
        gridList.add(six);
        JButton seven = new JButton("7");
        seven = setButtonColorDull(seven);
        gridList.add(seven);
        JButton eight = new JButton("8");
        eight = setButtonColorDull(eight);
        gridList.add(eight);
        JButton nine = new JButton("9");
        nine = setButtonColorDull(nine);
        gridList.add(nine);
        JButton ten = new JButton("10");
        ten = setButtonColorDull(ten);
        gridList.add(ten);
        
        int i = 0;
        for(JButton button : gridList)
        {
            if(i>10&&i<66)
                {
                    //button is a seat-button. Add the correct ActionListener
                    if(ticketManager.equals("booker"))
                    {
                        button.addActionListener(addBookingActionListener);
                    }
                    else
                    {
                        button.addActionListener(bookingsMoveTicketActionListener);
                    }
                }
            panel.add(button);
            i++;
        }
        return panel;
    }
}
