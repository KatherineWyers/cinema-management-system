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
 * @version DEC-2017
 * 
 */
public class Gui extends UserInterface
{
    private JFrame frame;
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
     * run
     * 
     * Run the application
     */
    public void run()
    {
        this.displaySplashScreen();// resizes the frame
        this.addDelayInSeconds(5);
        this.resizeFrame();//return the frame to the correct size
        this.displayFilmsIndexPage();
        this.frame.pack();
        this.frame.setVisible(true); 
        // Application will quit when user clicks 'X' in the application interface
    }
    
    /**
     * setupFrame
     * 
     * Setup the Frame and the four panels (northPanel, centerPanel, 
     * eastPanel and soutPanel) that will display the content
     * 
     * Set the frame size and disable resizing of the frame
     */
    private void setupFrame()
    {
        this.indexList = new JList<Object>();
        this.listModel = new DefaultListModel<Object>();
        
        this.northPanel = new JPanel(new BorderLayout());
        this.centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.centerPanel.setBackground(Color.white);
        this.eastPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.eastPanel.setMinimumSize(new Dimension(512,0));
        this.eastPanel.setPreferredSize(new Dimension(512,0));
        this.eastPanel.setBackground(Color.white);
        this.southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.frame = new JFrame("Odeon Cinema System");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * oneColumnLayout
     * 
     * Create layout with Centre panel as full width
     */
    private void oneColumnLayout()
    {
        this.centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.centerPanel.setMinimumSize(new Dimension(1023,0));
        this.centerPanel.setPreferredSize(new Dimension(1023,0));
        this.eastPanel.setMinimumSize(new Dimension(1,0));
        this.eastPanel.setPreferredSize(new Dimension(1,0));
    }
    
    /**
     * twoColumnLayout
     * 
     * Create layout with 1/2 centerPanel and 1/2 eastPanel
     */
    private void twoColumnLayout()
    {
        this.centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.centerPanel.setMinimumSize(new Dimension(512,0));
        this.centerPanel.setPreferredSize(new Dimension(512,0));
        this.eastPanel.setMinimumSize(new Dimension(512,0));
        this.eastPanel.setPreferredSize(new Dimension(512,0));
    }
    
    
    /**
     * displaySplashScreen
     * 
     * Create an instance of GuiSplashScreen
     * and add it to the centerPanel
     */
    private void displaySplashScreen()
    {
        this.frame.setPreferredSize(new Dimension(1024,560));
        this.frame.setMinimumSize(new Dimension(1024,560));
        
        GuiSplashScreen splash = new GuiSplashScreen();
        this.clearPanels();
        this.oneColumnLayout();
        this.centerPanel.add(splash.getSplashPanel());
        this.showPanels();
    }
    
    /**
     * resizeFrame
     * 
     * Set the frame to its default size
     */
    private void resizeFrame()
    {
        this.frame.setPreferredSize(new Dimension(1024,768));
        this.frame.setMinimumSize(new Dimension(1024,768));   
    }
    
    /**
     * displayFilmsIndexPage()
     * 
     * Display the index page for films. 
     * When an item is clicked in the list, call 
     * updateEastPanelFilmsIndex
     */
    private void displayFilmsIndexPage()
    {    
        this.clearPanels();
        this.twoColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("films"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("films", 1), BorderLayout.SOUTH);
        this.refreshList(this.cinema.getFilmList());// Load the index list and display it
        this.indexList = new JList<Object>(listModel);
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(GuiNavMaker.getHeaderPanel("FILM INDEX: Select Film"), BorderLayout.NORTH);
        JPanel listContainer = new JPanel();
        listContainer.add(this.indexList);
        this.centerPanel.add(this.addScrollbars(listContainer), BorderLayout.CENTER);
        this.indexList.addMouseListener(filmsIndexListMouseListener);// Event listener. When the list is clicked, it updates the eastPanel
        this.updateEastPanelFilmsIndex(this.cinema.getFilmList().get(0));// Display the first film in the list by default
        this.showPanels();
    }
    
    /**
     * addScrollbars
     * 
     * Convert JPanel to JScrollPane with scrollbars.
     * Add scrollbars to the the JPanel. This is 
     * applied throughout the application if the 
     * size of the JPanel can expand beyond the size 
     * of the JFrame
     * 
     * @param JPanel
     * @return JScrollPane
     */
    private JScrollPane addScrollbars(JPanel panel)
    {
       JScrollPane scrollPane = new JScrollPane(panel);
       scrollPane.setPreferredSize(new Dimension(390,460));
       return scrollPane;
    }
    
      
    /**
     * updateEastPanelFilmsIndex
     * 
     * Display the details about the film 
     * in the eastPanel
     * 
     * @param film Film 
     */
    private void updateEastPanelFilmsIndex(Film film)
    {   
        this.clearEastPanel();
        JPanel panel = new JPanel(); 
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(new GridLayout(0,2,20,20));
        panel.add(new JLabel("Title"));
        panel.add(new JLabel(film.getTitle()));
        panel.add(new JLabel("Director"));
        panel.add(new JLabel(film.getDirector()));
        panel.add(new JLabel("Year"));
        panel.add(new JLabel(Integer.toString(film.getYear())));
        panel.add(new JLabel("Language"));
        panel.add(new JLabel(film.getLanguage()));
        panel.add(new JLabel("Subtitles"));
        panel.add(new JLabel(film.getSubtitles()));  
        this.eastPanel.add(panel); 
        this.refreshEastPanel();
    }
    
    
    /**
     * displayAddFilmPage()
     * 
     * Create the navigation and call 
     * addAddFilmFormToCenterPanel()
     */
    private void displayAddFilmPage()
    {    
        this.clearPanels();
        this.oneColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("films"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("films", 2), BorderLayout.SOUTH);
        this.addAddFilmFormToCenterPanel();
        this.showPanels();
    }
    
    /**
     * addAddFilmFormToCenterPanel()
     * 
     * Display a form and prompt the user 
     * to enter details about the new film
     */
    private void addAddFilmFormToCenterPanel()
    {
        JPanel panel = new JPanel();   
        panel.setLayout(new GridLayout(10,2,5,5));
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
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(GuiNavMaker.getHeaderPanel("Add New Film"), BorderLayout.NORTH);
        this.centerPanel.add(panel, BorderLayout.CENTER);
    }
    
    /**
     * displayShowsIndexPage()
     * 
     * Display a list of all shows. 
     * When a list-item is clicked, call 
     * updateEastPanelShowsIndex()
     */
    private void displayShowsIndexPage()
    {    
        this.clearPanels();
        this.twoColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("shows"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("shows", 1), BorderLayout.SOUTH);
        this.refreshList(this.cinema.getShowList());// Load the index list and display it
        this.indexList = new JList<Object>(listModel);
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(GuiNavMaker.getHeaderPanel("SHOWS INDEX: Select Show"), BorderLayout.NORTH);
        JPanel listContainer = new JPanel();
        listContainer.add(this.indexList);
        this.centerPanel.add(this.addScrollbars(listContainer), BorderLayout.CENTER);
        this.indexList.addMouseListener(showsIndexListMouseListener);// Event listener. When the list is clicked, it updates the eastPanel
        this.updateEastPanelShowsIndex(this.cinema.getShowList().get(0));// preselect the first show
        this.showPanels();
    }
    
    /**
     * updateEastPanelShowsIndex
     * 
     * Display full details about the show in the 
     * eastPanel
     * 
     * @param show Show 
     */
    private void updateEastPanelShowsIndex(Show show)
    {   
        this.clearEastPanel();
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        JPanel topPanel = new JPanel(new GridLayout(3,2,20,20));
        topPanel.add(new JLabel("Film"));
        topPanel.add(new JLabel(show.getFilm().getTitle()));
        topPanel.add(new JLabel("DateTime"));
        topPanel.add(new JLabel(show.getDateTime()));
        topPanel.add(new JLabel("Screen"));
        topPanel.add(new JLabel(show.getScreen().getTitle()));
        JPanel midPanel = GuiNavMaker.getHeaderPanel("SEATING GRID");
        JPanel bottomPanel = GuiHelper.getSeatingGridPanel(this.cinema.getSeatingGrid(show));
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(midPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        eastPanel.add(mainPanel);     
        this.refreshEastPanel();  
    }
    
    /**
     * displayAddShowPage()
     * 
     * Reset the formData Map to blank and 
     * call displayAddShowSelectFilm
     */
    private void displayAddShowPage()
    {
        this.formData = new HashMap<String, Object>();
        this.displayAddShowSelectFilm();
    } 
    
    /**
     * displayAddShowSelectFilm()
     * 
     * Display a list of films and prompt 
     * the user to select the film for the 
     * new show.
     * When a film is selected, call 
     * updateEastPanelAddShowSelectFilm()
     */
    private void displayAddShowSelectFilm()
    {    
        this.clearPanels();
        this.twoColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("shows"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("shows", 2), BorderLayout.SOUTH);
        this.refreshList(this.cinema.getFilmList());
        this.indexList = new JList<Object>(listModel);
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(GuiNavMaker.getHeaderPanel("ADD SHOW: Select Film"), BorderLayout.NORTH);
        JPanel listContainer = new JPanel();
        listContainer.add(this.indexList);
        this.centerPanel.add(this.addScrollbars(listContainer), BorderLayout.CENTER);
        this.indexList.addMouseListener(addShowSelectFilmListMouseListener);
        this.updateEastPanelAddShowSelectFilm(this.cinema.getFilmList().get(0));// preselect the first film
        this.showPanels();
    }
    
    /**
     * updateEastPanelAddShowSelectFilm
     * 
     * Display details about the selected 
     * film in the east panel and prompt the user 
     * to add the film to the new show.
     * The button [Select Film for Show],
     * uses addShowActionListener
     * 
     * @param film Film 
     */
    private void updateEastPanelAddShowSelectFilm(Film film)
    {   
        this.clearEastPanel();
        JPanel panel = new JPanel(new GridLayout(3,2,20,20));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(new JLabel("Title"));
        panel.add(new JLabel(film.getTitle()));
        panel.add(new JLabel("Director"));
        panel.add(new JLabel(film.getDirector()));
        panel.add(new JLabel("Year"));
        panel.add(new JLabel(Integer.toString(film.getYear())));
        this.formData.put("tempFilm", film);// place film in temporary HashMap location, until 'Select Film for Show' has been clicked.
        JButton addFilmButton = new JButton("Select Film for Show");
        addFilmButton.addActionListener(addShowActionListener);
        this.eastPanel.add(panel);
        this.eastPanel.add(addFilmButton);
        this.refreshEastPanel();
    }
    
    /**
     * displayAddShowSelectScreen()
     * 
     * Display a list of screen and prompt 
     * the user to select the screen for the 
     * new show.
     * When a screen is selected, call 
     * updateEastPanelAddShowSelectScreen()
     */
    private void displayAddShowSelectScreen()
    {    
        this.clearPanels();
        this.twoColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("shows"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("shows", 2), BorderLayout.SOUTH);
        this.refreshList(this.cinema.getScreenList());
        this.indexList = new JList<Object>(listModel);
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(GuiNavMaker.getHeaderPanel("ADD SHOW: Select Screen"), BorderLayout.NORTH);
        JPanel listContainer = new JPanel();
        listContainer.add(this.indexList);
        this.centerPanel.add(this.addScrollbars(listContainer), BorderLayout.CENTER);
        this.indexList.addMouseListener(addShowSelectScreenListMouseListener);
        this.updateEastPanelAddShowSelectScreen(this.cinema.getScreenList().get(0));// preselect the first film
        this.showPanels();
    }
    
    /**
     * updateEastPanelAddShowSelectScreen
     * 
     * Display details about the selected 
     * screen in the east panel and prompt the user 
     * to add the screen to the new show.
     * The button [Select Screen for Show],
     * uses addShowActionListener
     * 
     * @param screen Screen 
     */  
    private void updateEastPanelAddShowSelectScreen(Screen screen)
    {   
        this.clearEastPanel();
        JPanel panel = new JPanel(new GridLayout(3,1,20,20));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(new JLabel("SCREEN SELECTED"));
        panel.add(new JLabel(screen.getTitle()));
        formData.put("tempScreen", screen);// place screen in temporary HashMap location, until 'Select Screen for Show' has been clicked. 
        JButton addScreenButton = new JButton("Select Screen for Show");
        addScreenButton.addActionListener(addShowActionListener);
        panel.add(addScreenButton);
        this.eastPanel.add(panel);
        this.refreshEastPanel();
    }
    
    
    /**
     * displayAddShowEnterDetails()
     * 
     * Create the navigation and call 
     * addAddShowEnterDetailsFormToCenterPanel()
     */
    private void displayAddShowEnterDetails()
    {
        this.clearPanels();
        this.oneColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("shows"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("shows", 2), BorderLayout.SOUTH);
        this.addAddShowEnterDetailsFormToCenterPanel();
        this.showPanels();
    }  
    
    /**
     * addAddShowEnterDetailsFormToCenterPanel()
     * 
     * Display a form and prompt the user 
     * to enter details about the new show
     */
    private void addAddShowEnterDetailsFormToCenterPanel()
    {
        JPanel panel = new JPanel();   
        panel.setLayout(new GridLayout(10,2,5,5));
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
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(GuiNavMaker.getHeaderPanel("Enter Details For New Show"), BorderLayout.NORTH);
        this.centerPanel.add(panel, BorderLayout.CENTER);
    }
    
    
    /**
     * displayCustomersIndexPage()
     * 
     * Display a list of all customers. 
     * When a list-item is clicked, call 
     * updateEastPanelCustomersIndex()
     */
    private void displayCustomersIndexPage()
    {    
        this.clearPanels();
        this.twoColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("customers"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("customers", 1), BorderLayout.SOUTH);
        this.refreshList(this.cinema.getCustomerList());// Load the index list and display it
        this.indexList = new JList<Object>(listModel);
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(GuiNavMaker.getHeaderPanel("CUSTOMERS INDEX: Select Customer"), BorderLayout.NORTH);
        JPanel listContainer = new JPanel();
        listContainer.add(this.indexList);
        this.centerPanel.add(this.addScrollbars(listContainer), BorderLayout.CENTER);
        this.indexList.addMouseListener(customersIndexListMouseListener);// Event listener. When the list is clicked, it updates the eastPanel
        this.updateEastPanelCustomersIndex(this.cinema.getCustomerList().get(0));// Display the first film in the list by default
        this.showPanels();
    }

    /**
     * updateEastPanelCustomersIndex
     * 
     * Display full details about the customer in the 
     * eastPanel
     * 
     * @param customer Customer 
     */
    private void updateEastPanelCustomersIndex(Customer customer)
    {   
        this.clearEastPanel();
        JPanel panel = new JPanel(new GridLayout(2,2,20,20));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(new JLabel("CustomerName"));
        panel.add(new JLabel(customer.getName()));
        panel.add(new JLabel("CustomerId"));
        panel.add(new JLabel(Integer.toString(customer.getId())));
        eastPanel.add(panel);  
        this.refreshEastPanel();
    }
    
    /**
     * displayBookingsIndexPage()
     * 
     * Display a list of all bookings. 
     * When a list-item is clicked, call 
     * updateEastPanelBookingsIndex()
     */
    private void displayBookingsIndexPage()
    {    
        this.clearPanels();
        this.twoColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 1), BorderLayout.SOUTH);
        this.refreshList(this.cinema.getBookingList());// Load the index list and display it
        this.indexList = new JList<Object>(listModel);
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(GuiNavMaker.getHeaderPanel("BOOKINGS INDEX: Select Booking"), BorderLayout.NORTH);
        JPanel listContainer = new JPanel();
        listContainer.add(this.indexList);
        this.centerPanel.add(this.addScrollbars(listContainer), BorderLayout.CENTER);
        this.indexList.addMouseListener(bookingsIndexListMouseListener);// Event listener. When the list is clicked, it updates the eastPanel
        this.updateEastPanelBookingsIndex(this.cinema.getBookingList().get(0));// Display the first film in the list by default
        this.showPanels();
    }
      
    /**
     * updateEastPanelBookingsIndex
     * 
     * Display full details about the booking in the 
     * eastPanel
     * 
     * @param booking Booking 
     */
    private void updateEastPanelBookingsIndex(Booking booking)
    {   
        this.clearEastPanel();
        JPanel panel = new JPanel(new GridLayout(0,2,20,20));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(new JLabel("Customer Name"));
        panel.add(new JLabel(booking.getCustomer().getName()));
        panel.add(new JLabel("CustomerId"));
        panel.add(new JLabel(Integer.toString(booking.getCustomer().getId())));
       for(Ticket ticket : this.cinema.getTicketList(booking))
       {
           panel.add(new JLabel("-----"));
           panel.add(new JLabel("-----"));
           panel.add(new JLabel("Film"));
           panel.add(new JLabel(ticket.getShow().getFilm().getTitle()));
           panel.add(new JLabel("DateTime"));
           panel.add(new JLabel(ticket.getShow().getDateTime()));
           panel.add(new JLabel("TicketId"));
           panel.add(new JLabel(Integer.toString(ticket.getId())));
           panel.add(new JLabel("Seat"));
           panel.add(new JLabel(ticket.getSeatName()));
           if(this.cinema.getReviews().get(ticket.getId())!=null)
           {
               panel.add(new JLabel("+++Customer Review"));
               panel.add(new JLabel(this.cinema.getReviews().get(ticket.getId()).getReview()));
               panel.add(new JLabel("+++Customer Rating (1-5)"));
               panel.add(new JLabel(Integer.toString(this.cinema.getReviews().get(ticket.getId()).getRating())));
           }
       }    
       panel.add(new JLabel("-----"));
       panel.add(new JLabel("-----"));
       JScrollPane scrollPane = new JScrollPane(panel);
       scrollPane.setPreferredSize(new Dimension(390,460));
       eastPanel.add(scrollPane);  
       this.refreshEastPanel();
    }
    
    /**
     * displayAddBookingPage()
     * 
     * Reset the formData Map to blank and 
     * call displayAddBookingSelectCustomerType
     */
    private void displayAddBookingPage()
    {
        this.formData = new HashMap<String, Object>();// reset the formData Map
        this.displayAddBookingSelectCustomerType();
    } 
    
    /**
     * displayAddShowSelectCustomerType()
     * 
     * Prompt the user to select the whether 
     * the customer is new or and existing 
     * customer. The two buttons use 
     * addBookingSelectCustomerTypeActionListener 
     * and pass customerType as a ClientProperty
     */
    private void displayAddBookingSelectCustomerType()
    {    
        this.clearPanels();
        this.oneColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 2), BorderLayout.SOUTH);
        
        JPanel panel = new JPanel();
        JButton newCustomerBtn = new JButton("New Customer");
        newCustomerBtn.putClientProperty("customerType", "newCustomer");
        newCustomerBtn.addActionListener(addBookingSelectCustomerTypeActionListener);
        JButton existingCustomerBtn = new JButton("Existing Customer");
        existingCustomerBtn.putClientProperty("customerType", "existingCustomer");
        existingCustomerBtn.addActionListener(addBookingSelectCustomerTypeActionListener);
        panel.add(newCustomerBtn);
        panel.add(existingCustomerBtn);
        
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(GuiNavMaker.getHeaderPanel("ADD BOOKING: Select Customer Type"), BorderLayout.NORTH);
        this.centerPanel.add(panel, BorderLayout.CENTER);
        
        this.showPanels();
    }
    
    /**
     * displayAddBookingEnterCustomerId()
     * 
     * Propmt the user to enter a customer id 
     * for the existing customer
     */
    private void displayAddBookingEnterCustomerId()
    {
        this.clearPanels();
        this.oneColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 2), BorderLayout.SOUTH);
        JPanel panel = new JPanel();   
        panel.setLayout(new GridLayout(10,2,5,5));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel = this.addTextFieldToPanel(20, "customerId", "Customer Id", panel);
        panel.add(new JLabel(""));//blank label so submit button moves to second column
        JButton submit = new JButton("Submit");
        submit.addActionListener(addBookingSetExistingCustomerActionListener);
        panel.add(submit);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(GuiNavMaker.getHeaderPanel("ADD BOOKING: Enter Customer Id"), BorderLayout.NORTH);
        this.centerPanel.add(panel, BorderLayout.CENTER);
        
        this.showPanels();
    }   
    
    /**
     * displayAddBookingNewCustomer()
     * 
     * Display a cusomter form and prompt
     * the user to enter customer details 
     * for the new customer
     */
    private void displayAddBookingNewCustomer()
    {
        this.clearPanels();
        this.oneColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 2), BorderLayout.SOUTH);
        JPanel panel = new JPanel();   
        panel.setLayout(new GridLayout(10,2,5,5));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel = this.addTextFieldToPanel(20, "customerName", "Customer Name", panel);
        panel.add(new JLabel(""));//blank label so submit button moves to second column
        JButton submit = new JButton("Submit");
        submit.addActionListener(addBookingSetNewCustomerActionListener);
        panel.add(submit);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(GuiNavMaker.getHeaderPanel("ADD BOOKING: Enter Customer Details"), BorderLayout.NORTH);
        this.centerPanel.add(panel, BorderLayout.CENTER);
        
        this.showPanels();
    }   
    
    /**
     * displayAddBookingSelectShow()
     * 
     * Display a list of shows. When a show 
     * is selected, call updateEastPanelAddBookingSelectShow()
     */
    private void displayAddBookingSelectShow()
    {    
        this.clearPanels();
        this.twoColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 2), BorderLayout.SOUTH);
        this.refreshList(this.cinema.getShowList());
        this.indexList = new JList<Object>(listModel);
        
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(GuiNavMaker.getHeaderPanel("ADD BOOKING: Select Show"), BorderLayout.NORTH);
        JPanel listContainer = new JPanel();
        listContainer.add(this.indexList);
        this.centerPanel.add(this.addScrollbars(listContainer), BorderLayout.CENTER);
        
        this.indexList.addMouseListener(addBookingSelectShowListMouseListener);
        this.updateEastPanelAddBookingSelectShow(this.cinema.getShowList().get(0));// preselect the first film
        this.showPanels();
    }  
    
    /**
     * updateEastPanelAddBookingSelectShow
     * 
     * Display the show details in 
     * the eastPanel
     * 
     * @param show Show 
     */
    private void updateEastPanelAddBookingSelectShow(Show show)
    {   
        this.clearEastPanel();
        JPanel panel = new JPanel(new GridLayout(3,2,20,20));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(new JLabel("Film"));
        panel.add(new JLabel(show.getFilm().getTitle()));
        panel.add(new JLabel("Screen"));
        panel.add(new JLabel(show.getScreen().getTitle()));
        panel.add(new JLabel("DateTime"));
        panel.add(new JLabel(show.getDateTime()));
        formData.put("tempShow", show);// place screen in temporary HashMap location, until 'Select Screen for Show' has been clicked. 
        JButton addShowButton = new JButton("Select Show for Booking");
        addShowButton.addActionListener(addBookingActionListener);
        this.eastPanel.add(panel);
        this.eastPanel.add(addShowButton);
        this.refreshEastPanel();
    }
    
    /**
     * displayAddBookingSelectSeats()
     * 
     * Display the seating grid where the 
     * user can select and deselect temporary 
     * seat reservations. 
     * 
     * @param booker Booker 
     */
    private void displayAddBookingSelectSeats(Booker booker)
    {    
        this.clearPanels();
        this.oneColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 2), BorderLayout.SOUTH);
        
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(GuiNavMaker.getHeaderPanel("ADD BOOKING: Select Seats"), BorderLayout.NORTH);
        this.centerPanel.add(this.getSeatingGridButtons(booker.getSeatingGrid(), "booker", booker), BorderLayout.CENTER);
        
        this.updateSouthPanelAddBookingSelectSeats();
        this.showPanels();
    }
    
    /**
     * updateSouthPanelAddBookingSelectSeats
     * 
     * Display the list of temporary 
     * seat reseravations in the southPanel. Display 
     * the current price of the booking, and buttons 
     * to proceed with cash payment or card payment.
     */
    private void updateSouthPanelAddBookingSelectSeats()
    {   
        this.clearSouthPanel();
        Booker booker = (Booker)this.formData.get("booker");
        if(booker!=null)
        {
            for(Reservation reservation : booker.getReservations())
            {
                this.southPanel.add(new JLabel(cinema.convertToRowLetter(reservation.getRow()) + Integer.toString(reservation.getNum())));
                this.southPanel.add(new JLabel(", "));
            }  
            this.southPanel.add(new JLabel("TOTAL PRICE: $" + booker.getTotalPrice()));
        }
        
        JButton cashPaymentBtn = new JButton("Cash Payment");
        cashPaymentBtn.putClientProperty("paymentType", "cash");
        cashPaymentBtn.addActionListener(addBookingSelectPaymentTypeActionListener);
        JButton cardPaymentBtn = new JButton("Card Payment");
        cardPaymentBtn.putClientProperty("paymentType", "card");
        cardPaymentBtn.addActionListener(addBookingSelectPaymentTypeActionListener);
        this.southPanel.add(cashPaymentBtn);
        this.southPanel.add(cardPaymentBtn);
        this.refreshSouthPanel();
    }
    
    /**
     * displayBookingsMoveTicketPage()
     * 
     * Prompt the user to enter a ticket id
     */
    private void displayBookingsMoveTicketPage()
    {
        this.formData = new HashMap<String, Object>();// reset the formData Map
        this.clearPanels();
        this.oneColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 3), BorderLayout.SOUTH);
        JPanel panel = new JPanel();   
        panel.setLayout(new GridLayout(10,2,5,5));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel = this.addTextFieldToPanel(20, "ticketId", "Ticket Id", panel);
        panel.add(new JLabel(""));//blank label so submit button moves to second column
        JButton submit = new JButton("Submit");
        submit.addActionListener(bookingsMoveTicketActionListener);
        panel.add(submit);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(GuiNavMaker.getHeaderPanel("MOVE TICKET: Enter Ticket Id"), BorderLayout.NORTH);
        this.centerPanel.add(panel, BorderLayout.CENTER);
        this.showPanels();
    }   
    
    /**
     * displayBookingsMoveTicketSelectShow()
     * 
     * Display a list of shows. When selected, 
     * call updateEastPanelBookingsMoveTicketSelectShow
     */
    private void displayBookingsMoveTicketSelectShow()
    {    
        this.clearPanels();
        this.twoColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 3), BorderLayout.SOUTH);
        this.refreshList(this.cinema.getShowList());
        this.indexList = new JList<Object>(listModel);
        
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(GuiNavMaker.getHeaderPanel("MOVE TICKET: Select New Show"), BorderLayout.NORTH);
        JPanel listContainer = new JPanel();
        listContainer.add(this.indexList);
        this.centerPanel.add(this.addScrollbars(listContainer), BorderLayout.CENTER);
        this.indexList.addMouseListener(bookingsMoveTicketSelectShowListMouseListener);
        this.updateEastPanelBookingsMoveTicketSelectShow(this.cinema.getShowList().get(0));// preselect the first film
        this.showPanels();
    }  
    
    /**
     * updateEastPanelBookingsMoveTicketSelectShow
     * 
     * Display details about the show in the eastPanel.
     * Prompt the user to click [Select Show for Transfer].
     * The button uses the bookingsMoveTicketActionListener
     * 
     * @param show Show 
     */
    private void updateEastPanelBookingsMoveTicketSelectShow(Show show)
    {   
        this.clearEastPanel();
        JPanel panel = new JPanel(new GridLayout(3,2,20,20));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(new JLabel("Film"));
        panel.add(new JLabel(show.getFilm().getTitle()));
        panel.add(new JLabel("Screen"));
        panel.add(new JLabel(show.getScreen().getTitle()));
        panel.add(new JLabel("DateTime"));
        panel.add(new JLabel(show.getDateTime()));
        formData.put("tempShow", show);// place screen in temporary HashMap location, until 'Select Screen for Show' has been clicked. 
        JButton addShowButton = new JButton("Select Show for Transfer");
        addShowButton.addActionListener(bookingsMoveTicketActionListener);
        this.eastPanel.add(panel);
        this.eastPanel.add(addShowButton);
        this.refreshEastPanel();
    }
    
    /**
     * displayBookingsMoveTicketSelectSeat()
     * 
     * Display the seating grid and prompt the 
     * user to select a new seat. 
     * When a seat is selected, call 
     * updateSouthPanelBookingMoveTicketSelectSeat
     * 
     * @param transferer Transferer 
     */
    private void displayBookingsMoveTicketSelectSeat(Transferer transferer)
    {    
        this.clearPanels();
        this.oneColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 3), BorderLayout.SOUTH);
        
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(GuiNavMaker.getHeaderPanel("MOVE TICKET: Select Seat"), BorderLayout.NORTH);
        
        // if the current ticket is for the current show, pass the seatName to the grid to exclude it
        // Otherwise, display full seating grid for the show and pass in a NULL string for seatName
        String seatName;
        if(transferer.getTicket().getShow()==transferer.getShow())
        {
            this.centerPanel.add(this.getSeatingGridButtons(transferer.getSeatingGridIgnoreTicket(transferer.getTicket()), transferer.getTicket().getSeatName(), transferer));
        }
        else
        {
            this.centerPanel.add(this.getSeatingGridButtons(transferer.getSeatingGrid(), "NULL", transferer));
        }
        
        this.updateSouthPanelBookingMoveTicketSelectSeat();
        this.showPanels();
    }
    
    /**
     * updateEastPanelBookingMoveTicketSelectSeat
     * 
     * Display the current seat reservation, and the price of the ticket 
     * transfer. Display  a [Process Transfer] button which 
     * uses bookingsMoveTicketSelectPaymentTypeActionListener
     */
    private void updateSouthPanelBookingMoveTicketSelectSeat()
    {   
        this.clearSouthPanel();
        Transferer transferer = (Transferer)this.formData.get("transferer");
        if(transferer!=null)
        {
            if(transferer.getReservation()!=null)
            {
                this.southPanel.add(new JLabel("SEAT: " + cinema.convertToRowLetter(transferer.getReservation().getRow()) + Integer.toString(transferer.getReservation().getNum())));
                this.southPanel.add(new JLabel("TRANSFER PRICE: $" + transferer.getSurcharge()));
            }    
        }
        
        JButton processTransferBtn = new JButton("Process Transfer");
        processTransferBtn.addActionListener(bookingsMoveTicketSelectPaymentTypeActionListener);
        this.southPanel.add(processTransferBtn);
        this.refreshSouthPanel();
    }
    
    /**
     * displayBookingsReviewAndRatePage()
     * 
     * Display a form where the user can enter 
     * ticketId, review and rating
     */
    private void displayBookingsReviewAndRatePage()
    {
        this.formData = new HashMap<String, Object>();// reset the formData Map
        this.clearPanels();
        this.oneColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 4), BorderLayout.SOUTH);
        JPanel panel = new JPanel();   
        panel.setLayout(new GridLayout(10,2,5,5));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel = this.addTextFieldToPanel(20, "ticketId", "Ticket Id", panel);
        panel = this.addTextFieldToPanel(20, "review", "Review (max 20 char.)", panel);
        panel = this.addTextFieldToPanel(1, "rating", "Rating", panel);
        panel.add(new JLabel(""));//blank label so submit button moves to second column
        JButton submit = new JButton("Submit");
        submit.addActionListener(bookingsReviewAndRateActionListener);
        panel.add(submit);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(GuiNavMaker.getHeaderPanel("BOOKINGS: Enter Review and Rating"), BorderLayout.NORTH);
        this.centerPanel.add(panel, BorderLayout.CENTER);
        this.showPanels();
    }   
    
    /**
     * displayReportsPage
     * 
     * Display the default reports page
     */
    private void displayReportsPage()
    {
        this.displayReportsTicketsAndRatingsPage();
    }
    
    /**
     * displayReportsTicketsAndReviewPage()
     * 
     * Set the default values to the current year and month
     * and call the displayReportsTicketsAndReviewForSelectedMonth()
     */
    private void displayReportsTicketsAndRatingsPage()
    {
        Calendar calendar = Calendar.getInstance();
        this.displayReportsTicketsAndRatingsForSelectedMonth(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));// default variables to the current month and year
    }
    
    /**
     * displayReportsTicketsAndReviewForSelectedMonth
     * 
     * Display the tickets and review report for the 
     * selected month and year
     * 
     * @param month int // zero-based [0: Jan, 1: Feb, 11: Dec]
     * @param year int 
     */
    private void displayReportsTicketsAndRatingsForSelectedMonth(int month, int year)
    {    
        this.clearPanels();
        this.oneColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("reports"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("reports", 1), BorderLayout.SOUTH);
        // Reports gets added to centerPanel here
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(this.getMonthYearMenuPanel(month, year, "ticketsAndRatings"), BorderLayout.NORTH);

        panel.add(this.getTicketsAndRatingsReportPanel(month, year), BorderLayout.CENTER);
        
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(GuiNavMaker.getHeaderPanel("REPORTS: Tickets And Average Ratings"), BorderLayout.NORTH);
        this.centerPanel.add(panel, BorderLayout.CENTER);
        this.showPanels();
    }
    
    /**
     * getTicketsAndRatingsReportPanel
     * 
     * Get the panel for the tickets and review report
     * 
     * @param month int // zero-based [0: Jan, 1: Feb, 11: Dec]
     * @param year int
     * @return JPanel
     */
    private JPanel getTicketsAndRatingsReportPanel(int month, int year)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1,5,5));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        Reporter reporter = new Reporter(this.cinema);
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
     * this.displayReportsIncomePage()
     * 
     * Set the default values to the current year and month
     * and call the displayReportsIncomeForSelectedMonth()
     */
    private void displayReportsIncomePage()
    {
        Calendar calendar = Calendar.getInstance();
        this.displayReportsIncomeForSelectedMonth(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));// default variables to the current month and year
    }
    
    /**
     * displayReportsIncomeForSelectedMonth()
     * 
     * Set the navigation for the income report page
     * 
     * @param month int // zero-based [0: Jan, 1: Feb, 11: Dec]
     * @param year int 
     */
    private void displayReportsIncomeForSelectedMonth(int month, int year)
    {    
        this.clearPanels();
        this.oneColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("reports"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("reports", 2), BorderLayout.SOUTH);
        // Reports gets added to centerPanel here
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(this.getMonthYearMenuPanel(month, year, "income"), BorderLayout.NORTH);
        panel.add(this.getIncomeReportPanel(month, year), BorderLayout.CENTER);
        
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(GuiNavMaker.getHeaderPanel("REPORTS: Income Generated Per Film"), BorderLayout.NORTH);
        this.centerPanel.add(panel, BorderLayout.CENTER);
        this.showPanels();
    }
    
    /**
     * geIncomeReportPanel
     * 
     * Return a JPanel with the requested income report
     * 
     * @param month int  // zero-based [0: Jan, 1: Feb, 11: Dec]
     * @param year int 
     * @return JPanel
     */
    private JPanel getIncomeReportPanel(int month, int year)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1,5,5));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        Reporter reporter = new Reporter(this.cinema);
        List<IncomeReport> incomeReportList = reporter.getIncomeReportList(month, year);
        panel.add(new Label("REPORT - Income"));// heading
        panel.add(new Label("YEAR: " + year + ", MONTH: " + ((int)month+1)));// heading
        panel.add(new Label(""));// add a margin
        if(incomeReportList.size()==0)
        {
            panel.add(new Label("No Tickets Sold"));
        }
        else
        {
            for(IncomeReport incomeReport : incomeReportList)
            {
                panel.add(new Label(incomeReport.toString()));
            }   
        }
        return panel;
    }
    
    /**
     * getMonthYearMenuPanel
     * 
     * get a JPanel with JTextField for year and 12 buttons for the months 
     * The value for year is passed to the ActionListener using the formData map
     * The value for month is passed to the ActionListener using the getClientProperty
     * 
     * @param month int // currently selected month , zero-based [0: Jan, 1: Feb, 11: Dec]
     * @param year int // currently selected year
     * @param report String 
     * @return JPanel
     */
    private JPanel getMonthYearMenuPanel(int month, int year, String report)
    {
        JPanel panel = new JPanel();   
        List<JButton> buttonList = new ArrayList<JButton>();
        panel.setLayout(new GridLayout(6,3,5,5));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        panel = this.addTextFieldToPanelWithDefaultText(4, "year", "Enter Year (eg 2017)", Integer.toString(year), panel);
        JButton updateYearBtn = new JButton("Update Year");
        updateYearBtn.putClientProperty("month", month);
        //button is a seat-button. Add the correct ActionListener
        if(report.equals("ticketsAndRatings"))
        {
            updateYearBtn.addActionListener(reportsTicketsAndRatingsSetYearMonthActionListener);
        }
        else
        {
            updateYearBtn.addActionListener(reportsIncomeSetYearMonthActionListener);
        }
        panel.add(updateYearBtn);
        
        // create middle gray line
        JButton blank7 = new JButton("");
        JButton blank8 = new JButton("");
        JButton blank9 = new JButton("");
        panel.add(GuiHelper.setButtonColorDull(blank7));
        panel.add(GuiHelper.setButtonColorDull(blank8));
        panel.add(GuiHelper.setButtonColorDull(blank9));
        
        // add buttons to month array
        JButton janBtn = new JButton("Jan");
        janBtn.putClientProperty("month", (int)0);
        buttonList.add(janBtn);
        JButton febBtn = new JButton("Feb");
        febBtn.putClientProperty("month", (int)1);
        buttonList.add(febBtn);
        JButton marBtn = new JButton("Mar");
        marBtn.putClientProperty("month", (int)2);
        buttonList.add(marBtn);
        JButton aprBtn = new JButton("Apr");
        aprBtn.putClientProperty("month", (int)3);
        buttonList.add(aprBtn);
        JButton mayBtn = new JButton("May");
        mayBtn.putClientProperty("month", (int)4);
        buttonList.add(mayBtn);
        JButton junBtn = new JButton("Jun");
        junBtn.putClientProperty("month", (int)5);
        buttonList.add(junBtn);
        JButton julBtn = new JButton("Jul");
        julBtn.putClientProperty("month", (int)6);
        buttonList.add(julBtn);
        JButton augBtn = new JButton("Aug");
        augBtn.putClientProperty("month", (int)7);
        buttonList.add(augBtn);
        JButton sepBtn = new JButton("Sep");
        sepBtn.putClientProperty("month", (int)8);
        buttonList.add(sepBtn);
        JButton octBtn = new JButton("Oct");
        octBtn.putClientProperty("month", (int)9);
        buttonList.add(octBtn);
        JButton novBtn = new JButton("Nov");
        novBtn.putClientProperty("month", (int)10);
        buttonList.add(novBtn);
        JButton decBtn = new JButton("Dec");
        decBtn.putClientProperty("month", (int)11);
        buttonList.add(decBtn);
        
        switch(month)
        {
            case 0:
                janBtn = GuiHelper.setButtonColorActive(janBtn);
                break;
            case 1:
                febBtn = GuiHelper.setButtonColorActive(febBtn);
                break;
            case 2:
                marBtn = GuiHelper.setButtonColorActive(marBtn);
                break;
            case 3:
                aprBtn = GuiHelper.setButtonColorActive(aprBtn);
                break;
            case 4:
                mayBtn = GuiHelper.setButtonColorActive(mayBtn);
                break;
            case 5:
                junBtn = GuiHelper.setButtonColorActive(junBtn);
                break;
            case 6:
                julBtn = GuiHelper.setButtonColorActive(julBtn);
                break;
            case 7:
                augBtn = GuiHelper.setButtonColorActive(augBtn);
                break;
            case 8:
                sepBtn = GuiHelper.setButtonColorActive(sepBtn);
                break;
            case 9:
                octBtn = GuiHelper.setButtonColorActive(octBtn);
                break;
            case 10:
                novBtn = GuiHelper.setButtonColorActive(novBtn);
                break;
            case 11:
                decBtn = GuiHelper.setButtonColorActive(decBtn);
                break;
        }
               
        for(JButton button : buttonList)
        {
            //button is a seat-button. Add the correct ActionListener
            if(report.equals("ticketsAndRatings"))
            {
                button.addActionListener(reportsTicketsAndRatingsSetYearMonthActionListener);
            }
            else
            {
                button.addActionListener(reportsIncomeSetYearMonthActionListener);
            }
            panel.add(button);
        }
        
        return panel;
    }
    
    /**
     * addTextFieldToPanel
     * 
     * Add a label and a textfield 
     * to the panel
     * 
     * @param maxLength int 
     * @param title String 
     * @param label String 
     * @return JPanel
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
     * 
     * Add a textfield and label to the 
     * panel, with default text set in the 
     * textfield
     * 
     * @param maxLength int 
     * @param title String 
     * @param label String 
     * @param defaultText String 
     * @return JPanel
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
     * 
     * Call removeAll on all panels
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
     * 
     * Call removeAll on eastPanel
     */
    private void clearEastPanel()
    {
        this.eastPanel.removeAll();
    }
    
    /**
     * clearSouthPanel
     * 
     * Call removeAll on southPanel
     */
    private void clearSouthPanel()
    {
        this.southPanel.removeAll();
    }
    
    /**
     * refreshCenterPanel
     * 
     * Call revalidate and repaint on eastPanel
     */
    private void refreshCenterPanel()
    {
        this.centerPanel.revalidate();
        this.centerPanel.repaint();
    }
    
    /**
     * refreshEastPanel
     * 
     * Call revalidate and repaint on eastPanel
     */
    private void refreshEastPanel()
    {
        this.eastPanel.revalidate();
        this.eastPanel.repaint();
    }
    
    /**
     * refreshSouthPanel
     * 
     * Call revalidate and repaint on southPanel
     */
    private void refreshSouthPanel()
    {
        this.southPanel.revalidate();
        this.southPanel.repaint();
    }
    
    /**
     * showPanels
     * 
     * Call revalidate and repaint on all panels
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
     * 
     * Refresh the list model
     * refreshes the list with data from the system.
     * RuntimeException is caught as it is thrown if the List Model is too small
     * 
     * Adapted from: CCS course materials
     * Date: 26-DEC-2017
     * 
     * @param list List 
     */
    private void refreshList(List list) 
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
     * 
     * The primary navigation menu
     * Each button uses NavButtonActionListener 
     * and passes page as a client property
     * 
     * @param category String
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
                filmsNavBtn = GuiHelper.setButtonColorActive(filmsNavBtn);
                break;
            case "shows":
                showsNavBtn = GuiHelper.setButtonColorActive(showsNavBtn);
                break;
            case "customers":
                customersNavBtn = GuiHelper.setButtonColorActive(customersNavBtn);
                break;
            case "bookings":
                bookingsNavBtn = GuiHelper.setButtonColorActive(bookingsNavBtn);
                break;
            case "reports":
                reportsNavBtn = GuiHelper.setButtonColorActive(reportsNavBtn);
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
     * 
     * The secondary navigation menu
     * This changes depending on the 
     * category.
     * Each button uses NavButtonActionListener
     * and passes page as a client property
     * 
     * @param category String 
     * @param subPointer int 
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
                button1 = GuiHelper.setButtonColorActive(button1);
                break;
            case 2:
                button2 = GuiHelper.setButtonColorActive(button2);
                break;
            case 3:
                button3 = GuiHelper.setButtonColorActive(button3);
                break;
            case 4:
                button4 = GuiHelper.setButtonColorActive(button4);
                break;
        }
        return panel;
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
                case "reportsIncome":
                    displayReportsIncomePage();
                    break;
            }
        }
    };
    
    ActionListener addFilmActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {      
            JFrame popupFrame = new JFrame("Add Film");
            
            JTextField title = (JTextField)formData.get("title");
            JTextField director = (JTextField)formData.get("director");
            JTextField language = (JTextField)formData.get("language");
            JTextField subtitles = (JTextField)formData.get("subtitles");
            JTextField yearTextField= (JTextField)formData.get("year");
            int yearInt = 0;
            
            if(!MathHelper.isLengthInRange(title.getText(),3,20))
            {
                JOptionPane.showMessageDialog(popupFrame, "Film Title must be between 3 and 20 characters in length");
                return;
            }
            
            if(!MathHelper.isLengthInRange(director.getText(),3,20))
            {
                JOptionPane.showMessageDialog(popupFrame, "Director name must be between 3 and 20 characters in length");
                return;
            }
            
            if(!MathHelper.isLengthInRange(language.getText(),3,20))
            {
                JOptionPane.showMessageDialog(popupFrame, "Language must be between 3 and 20 characters in length");
                return;
            }
            
            if(!MathHelper.isLengthInRange(subtitles.getText(),0,4))
            {
                JOptionPane.showMessageDialog(popupFrame, "Subtitles must be an acronym between 0 and 4 characters in length");
                return;
            }
            
            JTextField ratingTextField = (JTextField)formData.get("rating");
            int rating = GuiPopupHandler.parseToIntOrShowDialog(ratingTextField.getText(), "Rating entered is not a number.");
            if(rating == -1) {return;};     

            yearInt = GuiPopupHandler.ifIntNotInRangeShowDialog(yearInt, 1, 5, "Year must be between 1900 and 2050");  
            if(yearInt == -1) {return;}; 

            try
            {
                yearInt = Integer.parseInt(yearTextField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(popupFrame, "Year must be a number");
                return;
            }
            
            yearInt = GuiPopupHandler.parseToIntOrShowDialog(yearTextField.getText(), "Year must be a number");
            if(yearInt == -1) {return;}; 
            yearInt = GuiPopupHandler.ifIntNotInRangeShowDialog(yearInt, 1900, 2050, "Year must be between 1900 and 2050");  
            if(yearInt == -1) {return;}; 
            
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
            
            JFrame popupFrame = new JFrame("Add Show");
            
            if(formData.get("detailsEntered")!=null)
            {
                JTextField yearTextField = (JTextField)formData.get("year");
                yearInt = GuiPopupHandler.parseToIntOrShowDialog(yearTextField.getText(), "Year entered is not a number.");
                if(yearInt == -1) {return;};  

                JTextField monthTextField = (JTextField)formData.get("month");
                monthInt = GuiPopupHandler.parseToIntOrShowDialog(monthTextField.getText(), "Month entered is not a number. Month must be a number between 1 and 12");
                if(monthInt == -1) {return;};  

                JTextField dateTextField = (JTextField)formData.get("date");
                dateInt = GuiPopupHandler.parseToIntOrShowDialog(dateTextField.getText(), "Date entered is not a number.");
                if(dateInt == -1) {return;};  
                
                JTextField hourTextField = (JTextField)formData.get("hour");
                hourInt = GuiPopupHandler.parseToIntOrShowDialog(hourTextField.getText(), "Hour entered is not a number.");
                if(hourInt == -1) {return;};  
                
                JTextField minuteTextField = (JTextField)formData.get("minute");
                minuteInt = GuiPopupHandler.parseToIntOrShowDialog(minuteTextField.getText(), "Minute entered is not a number.");
                if(minuteInt == -1) {return;};  
                
                JTextField priceRegularTextField = (JTextField)formData.get("priceRegular");
                priceRegularFloat = GuiPopupHandler.parseToFloatOrShowDialog(priceRegularTextField.getText(), "PriceRegular entered is not a number.");
                if(priceRegularFloat == (float)-1.0) {return;};   
                
                JTextField priceVipTextField = (JTextField)formData.get("priceVip");
                priceVipFloat = GuiPopupHandler.parseToFloatOrShowDialog(priceVipTextField.getText(), "PriceVip entered is not a number.");
                if(priceVipFloat == (float)-1.0) {return;};                    
                
                
                yearInt = GuiPopupHandler.ifIntNotInRangeShowDialog(yearInt, 2015, 2050, "Year must be between 2015 and 2050");  
                if(yearInt == -1){ return;};
                monthInt = GuiPopupHandler.ifIntNotInRangeShowDialog(monthInt, 1, 12, "Month must be a number between 1 and 12");  
                if(monthInt == -1){ return;};
                dateInt = GuiPopupHandler.ifIntNotInRangeShowDialog(dateInt, 1, 31, "Date must be between 1 and 31");  
                if(dateInt == -1){ return;};
                hourInt = GuiPopupHandler.ifIntNotInRangeShowDialog(hourInt, 0, 23, "Hour must be between 0 and 23");  
                if(hourInt == -1){ return;};
                minuteInt = GuiPopupHandler.ifIntNotInRangeShowDialog(minuteInt, 0, 59, "Minute must be between 0 and 59");  
                if(minuteInt == -1){ return;};   
                priceRegularFloat = GuiPopupHandler.ifFloatNotInRangeShowDialog(priceRegularFloat, (float)0.0, (float)500.0, "PriceRegular must be between 0.0 and 500.0");  
                if(priceRegularFloat == (float)-1.0){ return;};                
                priceVipFloat = GuiPopupHandler.ifFloatNotInRangeShowDialog(priceVipFloat, (float)0.0, (float)500.0, "PriceVip must be between 0.0 and 500.0");  
                if(priceVipFloat == (float)-1.0){ return;};
            
                screen = (Screen)formData.get("screen");
            }
            else
            {
                displayAddShowEnterDetails();
                return;
            }
            
            Calendar calendar = Calendar.getInstance();
            calendar.setLenient(false);  
            calendar.set(yearInt, (monthInt-1), dateInt, hourInt, minuteInt);  
            try {  
                calendar.getTime(); 
            } catch (Exception ex) { 
                JOptionPane.showMessageDialog(popupFrame, "Date is not valid");
                return;
            }  
            
            Calendar now = Calendar.getInstance();
            if(calendar.before(now))
            {
                JOptionPane.showMessageDialog(popupFrame, "Cannot create a show with a date in the past");
                return;
            }
        
            cinema.addShow(calendar, screen, film, (float)priceRegularFloat, (float)priceVipFloat);// add the new show to the database
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
            JFrame popupFrame = new JFrame("Add Booking");
            if(formData.get("customerName")==null)
            {
                displayAddBookingSelectCustomerType();
                return;
            }
            JTextField customerNameTextField = (JTextField)formData.get("customerName");
            if(customerNameTextField.getText().length()<3||customerNameTextField.getText().length()>20)
            {
                JOptionPane.showMessageDialog(popupFrame, "Customer Name must be between 3 and 20 characters in length");
                return;
            }
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
            JFrame popupFrame = new JFrame("Add Booking");
            if(formData.get("customerId")==null)
            {
                displayAddBookingSelectCustomerType();
                return;
            }
            JTextField customerIdTextField = (JTextField)formData.get("customerId");            
            int customerId = GuiPopupHandler.parseToIntOrShowDialog(customerIdTextField.getText(), "CustomerId entered is not a number.");
            if(customerId == -1) {return;};      
            
            Customer customer;
            try
            {
                customer = cinema.getCustomer(customerId);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(popupFrame, ex.getMessage());
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
            JFrame popupFrame = new JFrame("Add Booking");
            if(formData.get("booker")==null)
            {
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
                case "card":
                    reply = JOptionPane.showConfirmDialog(null, "Proceed with Card Payment?", "Checkout", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        formData.put("seatSelectionComplete", "true");
                        String refNum = JOptionPane.showInputDialog(popupFrame, "Enter Card-payment Reference Number");
                        if(refNum.length()>8)
                        {
                            JOptionPane.showMessageDialog(popupFrame, "Payment Cancelled.\nCash Payment Reference Number must be 1 to 8 characters in length");
                            return;
                        }
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
            JFrame popupFrame = new JFrame("Add Booking");
            
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
                
                // Only allow booking to be created for shows in the future
                Show tempShow = (Show)formData.get("tempShow");
                Calendar now = Calendar.getInstance();
                if(tempShow.getDate().before(now))
                {
                    JOptionPane.showMessageDialog(popupFrame, "Cannot create a booking for a show with a date in the past");
                    return;
                }
                
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
                    row = MathHelper.convertToRowNum(seatSelected.substring(0,1)); 
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
            
            JFrame popupFrame = new JFrame("Move Ticket");
            
            if(formData.get("ticketId")==null)
            {
                displayBookingsMoveTicketPage();
                return;
            }
            JTextField ticketIdTextField = (JTextField)formData.get("ticketId");
            int ticketId = GuiPopupHandler.parseToIntOrShowDialog(ticketIdTextField.getText(), "TicketId entered is not a number.");
            if(ticketId == -1) {return;};          
            
            
            try
            {
                ticket = cinema.getTicket(ticketId);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(popupFrame, ex.getMessage());
                return;
            }

            Calendar now = Calendar.getInstance();
            if(ticket.getShow().getDate().before(now))
            {
                JOptionPane.showMessageDialog(popupFrame, "Ticket Expired: Cannot transfer a ticket that was purchased for a show in the past");
                return;
            }            
            
            formData.put("ticket", ticket);
            
            if(formData.get("tempShow")!=null)//formData stores a temporary show when eastPanel loads. Move it to "show" key to confirm
            {
                formData.put("show", formData.get("tempShow"));
                
                // Only allow booking to be created for shows in the future
                Show tempShow = (Show)formData.get("tempShow");
                now = Calendar.getInstance();
                if(tempShow.getDate().before(now))
                {
                    JOptionPane.showMessageDialog(popupFrame, "Cannot move the ticket to a show with a date in the past");
                    return;
                }                
                
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
                    row = MathHelper.convertToRowNum(seatSelected.substring(0,1));
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
            JFrame popupFrame = new JFrame("Move Ticket");
            
            if(formData.get("transferer")==null)
            {
                displayBookingsIndexPage();
                return;
            }
            Transferer transferer = (Transferer)formData.get("transferer");
            String paymentType = "";
            if(transferer.getSurcharge()>0)
            {
                Object[] options = {"Cash", "Card"};
                int reply = JOptionPane.showOptionDialog(frame,"Select Payment Method","Checkout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
                
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
                    if(refNum.length()>8)
                    {
                        JOptionPane.showMessageDialog(popupFrame, "Payment Cancelled.\nCash Payment Reference Number must be 1 to 8 characters in length");
                        return;
                    }
                    transferer.finalizeCardPayment(refNum);
                    JOptionPane.showMessageDialog(popupFrame, "Cash Payment Accepted.\nTransfer Process Completed Successfully."); 
                    displayBookingsIndexPage();
                    return;
                }
            }
            else
            {
                transferer.finalizeNoChargeTransfer();
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
            Ticket ticket;
            int ticketId;
            JFrame popupFrame = new JFrame("Review And Rate");
            
            if(formData.get("ticketId")==null||formData.get("review")==null||formData.get("rating")==null)
            {
                JOptionPane.showMessageDialog(popupFrame, "All fields must be completed.");
                return;
            }
            JTextField ticketIdTextField = (JTextField)formData.get("ticketId");
            
            try
            {
                ticketId = Integer.parseInt(ticketIdTextField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(popupFrame, "TicketId entered is not a number.");
                return;
            }
            
            try
            {
                ticket = cinema.getTicket(ticketId);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(popupFrame, ex.getMessage());
                return;
            }

            Calendar now = Calendar.getInstance();
            if(ticket.getShow().getDate().after(now))
            {
                JOptionPane.showMessageDialog(popupFrame, "Cannot log a review for a ticket if the show is in the future");
                return;
            } 
            
            JTextField reviewTextField = (JTextField)formData.get("review");
            if(reviewTextField.getText().length()>20)
            {
                JOptionPane.showMessageDialog(popupFrame, "Review must be less than 20 characters.");
                return;
            }
            review = reviewTextField.getText();
            
            JTextField ratingTextField = (JTextField)formData.get("rating");
            int rating = GuiPopupHandler.parseToIntOrShowDialog(ratingTextField.getText(), "Rating entered is not a number.");
            if(rating == -1) {return;};     

            rating = GuiPopupHandler.ifIntNotInRangeShowDialog(rating, 1, 5, "Rating must be between 1 and 5.");  
            if(rating == -1) {return;}; 

            cinema.addReview(ticket, review, rating);
            JOptionPane.showMessageDialog(popupFrame, "Review and Rating added successfully.");
            displayBookingsReviewAndRatePage();
            return;
        }
    };
    
    ActionListener reportsTicketsAndRatingsSetYearMonthActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            JFrame popupFrame = new JFrame("Tickets And Ratings Report");
            
            if(formData.get("year")==null)
            {
                displayReportsTicketsAndRatingsPage();
                return;
            }
            if(((JButton)e.getSource()).getClientProperty("month")==null)
            {
                displayReportsTicketsAndRatingsPage();
                return;
            }
            
            int month = (Integer)((JButton)e.getSource()).getClientProperty("month");

            JTextField yearTextField = (JTextField)formData.get("year");
            int year = GuiPopupHandler.parseToIntOrShowDialog(yearTextField.getText(), "Year entered is not a number.");
            if(year == -1) {return;};
            
            year = GuiPopupHandler.ifIntNotInRangeShowDialog(year, 2015, 2050,"Year must be between 2015 and 2050.");            
            if(year == -1) {return;};
            
            displayReportsTicketsAndRatingsForSelectedMonth(month,year);
            return;
        }
    };
    
    ActionListener reportsIncomeSetYearMonthActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            JFrame popupFrame = new JFrame("Income Report");
            
            if(formData.get("year")==null)
            {
                displayReportsIncomePage();
                return;
            }
            
            if(((JButton)e.getSource()).getClientProperty("month")==null)
            {
                displayReportsIncomePage();
                return;
            }
            int month = (Integer)((JButton)e.getSource()).getClientProperty("month");
            
            JTextField yearTextField = (JTextField)formData.get("year");
            int year = GuiPopupHandler.parseToIntOrShowDialog(yearTextField.getText(), "Year entered is not a number.");
            if(year == -1) {return;};
            
            year = GuiPopupHandler.ifIntNotInRangeShowDialog(year, 2015, 2050,"Year must be between 2015 and 2050.");
            if(year == -1) {return;};

            displayReportsIncomeForSelectedMonth(month,year);
            return;
        }
    };
   
    /**
     * getSeatingGridPanel
     * 
     * Create a JPanel version of the seatingGrid
     * Accepts a String param booker or transferer so it 
     * can be used form both
     * 
     * @param seatingGrid boolean[][] 
     * @param string String {NULL or transferSeatName eg A1, B2, C1}
     * @param TicketManager ticketManager
     * @return JPanel
     */
    private JPanel getSeatingGridButtons(boolean[][] seatingGrid, String ignoreSeat, TicketManager ticketManager)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,11,5,5));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        List<JButton> gridList = GuiHelper.getSeatingGridButtons(seatingGrid);
        
        int i = 0;
        for(JButton button : gridList)
        {
            if(i>10&&i<66)// only include buttons if they can be seats
            {
                String seatSelected = (String)button.getClientProperty("seatSelected");
                boolean addListener = false;// default to false
                
                // if seat is occupied, and ticketManager is a Booker, 
                // check if the seat is a Reservation or a Ticket
                if(button.getLabel().equals("[X]"))
                {
                    // only apply the action listener if the ticketManager is a Booker
                    // Transferer deselects when a different seat is selected
                    if(MultiReservationable.class.isAssignableFrom(ticketManager.getClass()))
                    {
                        Booker booker = (Booker)ticketManager;
                        int row = MathHelper.convertToRowNum(seatSelected.substring(0,1));
                        int num = Integer.parseInt(seatSelected.substring(1));
                        if(booker.isExistReservation(row, num))
                        {
                            addListener = true;
                            // set the button color to success if this is a Booker and the 
                            // seat is in getReservations() 
                            button = GuiHelper.setButtonColorSelected(button);
                        }
                    }
                    else
                    {
                        Transferer transferer = (Transferer)ticketManager;
                        
                        if(transferer.getReservation()!=null)
                        {
                            int row = MathHelper.convertToRowNum(seatSelected.substring(0,1));
                            int num = Integer.parseInt(seatSelected.substring(1));
                            if(transferer.getReservation().getSeatName().equals(seatSelected))
                            {
                                // set the button color to success if this is a Transferer and the 
                                // seat is the selected seat 
                                button = GuiHelper.setButtonColorSelected(button);
                            }      
                        }
                    }
                }
                // if seat is unoccupied, check if the seat is the ignoreSeat for the Transferer
                else if (!ignoreSeat.equals(button.getClientProperty("seatSelected")))// filter out the current ticket seatName
                {
                    addListener = true;
                }

                if(seatSelected!=null&&ignoreSeat!=null)
                {
                    if(seatSelected.equals(ignoreSeat))
                    {
                        // set the button color to warning if this is a Transferer and the 
                        // seat is the original ticket's seat
                        button = GuiHelper.setButtonColorWarning(button);
                        button.setText("[X]");
                    }
                }

                // attach the ActionListener to the button if addListener has been updated to true
                if(addListener)
                {
                    if(ticketManager instanceof Booker)
                    {
                        button.addActionListener(addBookingActionListener);
                    }
                    else
                    {
                        button.addActionListener(bookingsMoveTicketActionListener);
                    }
                }
            }

            panel.add(button);
            i++;
        }
        return panel;
    }
}
