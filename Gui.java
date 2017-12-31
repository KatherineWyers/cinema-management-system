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
     * @return void
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
     * 
     * @return void
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
     * 
     * @return void
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
     * 
     * @return void
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
     * 
     * @return void
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
     * 
     * @return void
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
     * 
     * @return void
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
        this.centerPanel.add(this.getHeaderPanel("FILM INDEX: Select Film"), BorderLayout.NORTH);
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
     * @param Film film
     * @return void
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
     * 
     * @return void
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
     * 
     * @return void
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
        this.centerPanel.add(this.getHeaderPanel("Add New Film"), BorderLayout.NORTH);
        this.centerPanel.add(panel, BorderLayout.CENTER);
    }
    
    /**
     * displayShowsIndexPage()
     * 
     * Display a list of all shows. 
     * When a list-item is clicked, call 
     * updateEastPanelShowsIndex()
     * 
     * @return void
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
        this.centerPanel.add(this.getHeaderPanel("SHOWS INDEX: Select Show"), BorderLayout.NORTH);
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
     * @param Show show
     * @return void
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
        JPanel midPanel = this.getHeaderPanel("SEATING GRID");
        JPanel bottomPanel = this.getSeatingGridPanel(this.cinema.getSeatingGrid(show));
        
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
     * 
     * @return void
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
     * 
     * @return void
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
        this.centerPanel.add(this.getHeaderPanel("ADD SHOW: Select Film"), BorderLayout.NORTH);
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
     * @param Film film
     * @return void
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
     * 
     * @return void
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
        this.centerPanel.add(this.getHeaderPanel("ADD SHOW: Select Screen"), BorderLayout.NORTH);
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
     * @param Screen screen
     * @return void
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
     * 
     * @return void
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
     * 
     * @return void
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
        this.centerPanel.add(this.getHeaderPanel("Enter Details For New Show"), BorderLayout.NORTH);
        this.centerPanel.add(panel, BorderLayout.CENTER);
    }
    
    
    /**
     * displayCustomersIndexPage()
     * 
     * Display a list of all customers. 
     * When a list-item is clicked, call 
     * updateEastPanelCustomersIndex()
     * 
     * @return void
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
        this.centerPanel.add(this.getHeaderPanel("CUSTOMERS INDEX: Select Customer"), BorderLayout.NORTH);
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
     * @param Customer customer
     * @return void
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
     * 
     * @return void
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
        this.centerPanel.add(this.getHeaderPanel("BOOKINGS INDEX: Select Booking"), BorderLayout.NORTH);
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
     * @param Booking booking
     * @return void
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
     * 
     * @return void
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
     * 
     * @return void
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
        this.centerPanel.add(this.getHeaderPanel("ADD BOOKING: Select Customer Type"), BorderLayout.NORTH);
        this.centerPanel.add(panel, BorderLayout.CENTER);
        
        this.showPanels();
    }
    
    /**
     * displayAddBookingEnterCustomerId()
     * 
     * Propmt the user to enter a customer id 
     * for the existing customer
     * 
     * @return void
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
        this.centerPanel.add(this.getHeaderPanel("ADD BOOKING: Enter Customer Id"), BorderLayout.NORTH);
        this.centerPanel.add(panel, BorderLayout.CENTER);
        
        this.showPanels();
    }   
    
    /**
     * displayAddBookingNewCustomer()
     * 
     * Display a cusomter form and prompt
     * the user to enter customer details 
     * for the new customer
     * 
     * @return void
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
        this.centerPanel.add(this.getHeaderPanel("ADD BOOKING: Enter Customer Details"), BorderLayout.NORTH);
        this.centerPanel.add(panel, BorderLayout.CENTER);
        
        this.showPanels();
    }   
    
    /**
     * displayAddBookingSelectShow()
     * 
     * Display a list of shows. When a show 
     * is selected, call updateEastPanelAddBookingSelectShow()
     * @return void
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
        this.centerPanel.add(this.getHeaderPanel("ADD BOOKING: Select Show"), BorderLayout.NORTH);
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
     * @param Show show
     * @return void
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
     * @param Booker booker
     * @return void
     */
    private void displayAddBookingSelectSeats(Booker booker)
    {    
        this.clearPanels();
        this.oneColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 2), BorderLayout.SOUTH);
        
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(this.getHeaderPanel("ADD BOOKING: Select Seats"), BorderLayout.NORTH);
        this.centerPanel.add(this.getSeatingGridButtons(booker.getSeatingGrid(), "booker"), BorderLayout.CENTER);
        
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
     * 
     * @return void
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
     * 
     * @return void
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
        this.centerPanel.add(this.getHeaderPanel("MOVE TICKET: Enter Ticket Id"), BorderLayout.NORTH);
        this.centerPanel.add(panel, BorderLayout.CENTER);
        this.showPanels();
    }   
    
    /**
     * displayBookingsMoveTicketSelectShow()
     * 
     * Display a list of shows. When selected, 
     * call updateEastPanelBookingsMoveTicketSelectShow
     * 
     * @return void
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
        this.centerPanel.add(this.getHeaderPanel("MOVE TICKET: Select New Show"), BorderLayout.NORTH);
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
     * @param Show show
     * @return void
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
     * @param Transferer transferer
     * @return void
     */
    private void displayBookingsMoveTicketSelectSeat(Transferer transferer)
    {    
        this.clearPanels();
        this.oneColumnLayout();
        this.northPanel.add(this.getPrimaryNavPanel("bookings"), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel("bookings", 3), BorderLayout.SOUTH);
        
        // centerPanel
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.add(this.getHeaderPanel("MOVE TICKET: Select Seat"), BorderLayout.NORTH);
        
        // if the current ticket is for the current show, pass the seatName to the grid to exclude it
        // Otherwise, display full seating grid for the show and pass in a NULL string for seatName
        String seatName;
        if(transferer.getTicket().getShow()==transferer.getShow())
        {
            this.centerPanel.add(this.getSeatingGridButtons(transferer.getSeatingGridIgnoreTicket(transferer.getTicket()), transferer.getTicket().getSeatName()));
        }
        else
        {
            this.centerPanel.add(this.getSeatingGridButtons(transferer.getSeatingGrid(), "NULL"));
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
     * 
     * @return void
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
     * 
     * @return void
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
        this.centerPanel.add(this.getHeaderPanel("BOOKINGS: Enter Review and Rating"), BorderLayout.NORTH);
        this.centerPanel.add(panel, BorderLayout.CENTER);
        this.showPanels();
    }   
    
    /**
     * displayReportsPage
     * 
     * Display the default reports page
     * 
     * @return void
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
     * 
     * @return void
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
     * @param int month// zero-based [0: Jan, 1: Feb, 11: Dec]
     * @param int year
     * @return void
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
        this.centerPanel.add(this.getHeaderPanel("REPORTS: Tickets And Average Ratings"), BorderLayout.NORTH);
        this.centerPanel.add(panel, BorderLayout.CENTER);
        this.showPanels();
    }
    
    /**
     * getTicketsAndRatingsReportPanel
     * 
     * Get the panel for the tickets and review report
     * 
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
     * 
     * @return void
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
     * @param int month// zero-based [0: Jan, 1: Feb, 11: Dec]
     * @param int year
     * @return void
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
        this.centerPanel.add(this.getHeaderPanel("REPORTS: Income Generated Per Film"), BorderLayout.NORTH);
        this.centerPanel.add(panel, BorderLayout.CENTER);
        this.showPanels();
    }
    
    /**
     * geIncomeReportPanel
     * 
     * Return a JPanel with the requested income report
     * 
     * @int month // zero-based [0: Jan, 1: Feb, 11: Dec]
     * @int year
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
     * @param int month// currently selected month , zero-based [0: Jan, 1: Feb, 11: Dec]
     * @param int year// currently selected year
     * @param String report
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
        panel.add(this.setButtonColorDull(blank7));
        panel.add(this.setButtonColorDull(blank8));
        panel.add(this.setButtonColorDull(blank9));
        
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
     * 
     * Add a textfield and label to the 
     * panel, with default text set in the 
     * textfield
     * 
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
     * 
     * Call removeAll on all panels
     * 
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
     * 
     * Call removeAll on eastPanel
     * 
     * @return void
     */
    private void clearEastPanel()
    {
        this.eastPanel.removeAll();
    }
    
    /**
     * clearSouthPanel
     * 
     * Call removeAll on southPanel
     * 
     * @return void
     */
    private void clearSouthPanel()
    {
        this.southPanel.removeAll();
    }
    
    /**
     * refreshCenterPanel
     * 
     * Call revalidate and repaint on eastPanel
     * 
     * @return void
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
     * 
     * @return void
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
     * 
     * @return void
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
     * 
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
     * 
     * Refresh the list model
     * refreshes the list with data from the system.
     * RuntimeException is caught as it is thrown if the List Model is too small
     * 
     * Adapted from: CCS course materials
     * Date: 26-DEC-2017
     * 
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
     * 
     * The primary navigation menu
     * Each button uses NavButtonActionListener 
     * and passes page as a client property
     * 
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
     * 
     * The secondary navigation menu
     * This changes depending on the 
     * category.
     * Each button uses NavButtonActionListener
     * and passes page as a client property
     * 
     * @param String category
     * @param int subPointer
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
     * 
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
     * @param JButton
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
     * so show that it is emphasized
     * 
     * Adapted from code at 
     * https://stackoverflow.com/questions/1065691
     * /how-to-set-the-background-color-of-a-jbutton-on-the-mac-os
     * 
     * Author: codethulu
     * Accessed 26-DEC-2017
     * 
     * @param JButton
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
     * setButtonColorDull
     * Set the background color of the button
     * so show that it is subtly emphasized
     * 
     * Adapted from code at 
     * https://stackoverflow.com/questions/1065691
     * /how-to-set-the-background-color-of-a-jbutton-on-the-mac-os
     * 
     * Author: codethulu
     * Accessed 26-DEC-2017
     * 
     * @param JButton
     * @return JButton
     */
    private JButton setButtonColorDull(JButton button)
   {
        button.setBackground(Color.lightGray);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }
    
    /**
     * setButtonColorWarning
     * 
     * Set the background color of the button
     * so show a warning
     * 
     * Adapted from code at 
     * https://stackoverflow.com/questions/1065691
     * /how-to-set-the-background-color-of-a-jbutton-on-the-mac-os
     * 
     * Author: codethulu
     * Accessed 26-DEC-2017
     * 
     * @param JButton 
     * @return JButton
     */
    private JButton setButtonColorWarning(JButton button)
   {
        button.setBackground(Color.orange);
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
            
            if(!isLengthInRange(title.getText(),3,20))
            {
                JOptionPane.showMessageDialog(popupFrame, "Film Title must be between 3 and 20 characters in length");
                return;
            }
            
            if(!isLengthInRange(director.getText(),3,20))
            {
                JOptionPane.showMessageDialog(popupFrame, "Director name must be between 3 and 20 characters in length");
                return;
            }
            
            if(!isLengthInRange(language.getText(),3,20))
            {
                JOptionPane.showMessageDialog(popupFrame, "Language must be between 3 and 20 characters in length");
                return;
            }
            
            if(!isLengthInRange(subtitles.getText(),0,4))
            {
                JOptionPane.showMessageDialog(popupFrame, "Subtitles must be an acronym between 0 and 4 characters in length");
                return;
            }

            try
            {
                yearInt = Integer.parseInt(yearTextField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(popupFrame, "Year must be a number");
                return;
            }
            if(yearInt<1900||yearInt>2050)
            {
                JOptionPane.showMessageDialog(popupFrame, "Year must be between 1900 and 2050");
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
            
            JFrame popupFrame = new JFrame("Add Show");
            
            if(formData.get("detailsEntered")!=null)
            {
                try
                {
                    JTextField yearTextField = (JTextField)formData.get("year");
                    yearInt = Integer.parseInt(yearTextField.getText());
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(popupFrame, "Year is not a number");
                    return;
                }
                
                try
                {
                    JTextField monthTextField = (JTextField)formData.get("month");
                    monthInt = Integer.parseInt(monthTextField.getText());
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(popupFrame, "Month is not a number. Month must be a number between 1 and 12");
                    return;
                }
                
                try
                {
                    JTextField dateTextField = (JTextField)formData.get("date");
                    dateInt = Integer.parseInt(dateTextField.getText());
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(popupFrame, "Date is not a number");
                    return;
                }
                
                try
                {
                    JTextField hourTextField = (JTextField)formData.get("hour");
                    hourInt = Integer.parseInt(hourTextField.getText());
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(popupFrame, "Hour is not a number");
                    return;
                }
                
                try
                {
                    JTextField minuteTextField = (JTextField)formData.get("minute");
                    minuteInt = Integer.parseInt(minuteTextField.getText());                    
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(popupFrame, "Minute is not a number");
                    return;
                }
                
                try
                {
                    JTextField priceRegularTextField = (JTextField)formData.get("priceRegular");
                    priceRegularFloat = Float.parseFloat(priceRegularTextField.getText());                    
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(popupFrame, "PriceRegular is not a number");
                    return;
                }
                
                try
                {
                    JTextField priceVipTextField = (JTextField)formData.get("priceVip");
                    priceVipFloat = Float.parseFloat(priceVipTextField.getText());                      
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(popupFrame, "PriceVip is not a number");
                    return;
                }
                
                if(yearInt<2015||yearInt>2050)
                {
                    JOptionPane.showMessageDialog(popupFrame, "Year must be between 2015 and 2050");
                    return;
                }
                
                if(monthInt<1||monthInt>12)
                {
                    JOptionPane.showMessageDialog(popupFrame, "Month must be a number between 1 and 12");
                    return;
                }
                
                if(dateInt<1||dateInt>31)
                {
                    JOptionPane.showMessageDialog(popupFrame, "Date must be between 1 and 31");
                    return;
                }
                
                if(hourInt<0||hourInt>23)
                {
                    JOptionPane.showMessageDialog(popupFrame, "Hour must be between 0 and 23");
                    return;
                }
                
                if(minuteInt<0||minuteInt>59)
                {
                    JOptionPane.showMessageDialog(popupFrame, "Minute must be between 0 and 59");
                    return;
                }
                
                if(priceRegularFloat<0||priceRegularFloat>500)
                {
                    JOptionPane.showMessageDialog(popupFrame, "PriceRegular must be between 0.0 and 500.0");
                    return;
                }
                
                if(priceVipFloat<0||priceVipFloat>500)
                {
                    JOptionPane.showMessageDialog(popupFrame, "PriceVip must be between 0.0 and 500.0");
                    return;
                }
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
            int customerId;
            try
            {
                customerId = Integer.parseInt(customerIdTextField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(popupFrame, "CustomerId entered is not a number.");
                return;
            }
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
            
            JFrame popupFrame = new JFrame("Move Ticket");
            
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
            int rating;
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
            try
            {
                rating = Integer.parseInt(ratingTextField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(popupFrame, "Rating entered is not a number.");
                return;
            }
            
            if(rating<1||rating>5)
            {
                JOptionPane.showMessageDialog(popupFrame, "Rating must be between 1 and 5.");
                return;
            }
            
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
            int year = 0;
            int month = (Integer)((JButton)e.getSource()).getClientProperty("month");

            JTextField yearTextField = (JTextField)formData.get("year");
            try
            {
                year = Integer.parseInt(yearTextField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(popupFrame, "Year entered is not a number.");
                return;
            }
            
            if(year<2015||year>2050)
            {
                JOptionPane.showMessageDialog(popupFrame, "Year must be between 2015 and 2050.");
                return;
            } 
            
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
            int year = 0;
            int month = (Integer)((JButton)e.getSource()).getClientProperty("month");
            
            JTextField yearTextField = (JTextField)formData.get("year");
            try
            {
                year = Integer.parseInt(yearTextField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(popupFrame, "Year entered is not a number.");
                return;
            }
            
            if(year<2015||year>2050)
            {
                JOptionPane.showMessageDialog(popupFrame, "Year must be between 2015 and 2050.");
                return;
            } 
            displayReportsIncomeForSelectedMonth(month,year);
            return;
        }
    };
    
    /**
     * isLengthInRange
     * 
     * Utility method to check if the length 
     * of a string is within the specified range
     * 
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
     * 
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
     * 
     * @param boolean[][] seatingGrid
     * @param String ticketManager {booker, transferSeatName eg A1, B2, C1}
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
        
        // seat numbers.
        JButton seatNums0 = new JButton("");
        seatNums0 = setButtonColorDull(seatNums0);
        gridList.add(seatNums0);
        JButton seatNums1 = new JButton("1");
        seatNums1 = setButtonColorDull(seatNums1);
        gridList.add(seatNums1);
        JButton seatNums2 = new JButton("2");
        seatNums2 = setButtonColorDull(seatNums2);
        gridList.add(seatNums2);
        JButton seatNums3 = new JButton("3");
        seatNums3 = setButtonColorDull(seatNums3);
        gridList.add(seatNums3);
        JButton seatNums4 = new JButton("4");
        seatNums4 = setButtonColorDull(seatNums4);
        gridList.add(seatNums4);
        JButton seatNums5 = new JButton("5");
        seatNums5 = setButtonColorDull(seatNums5);
        gridList.add(seatNums5);
        JButton seatNums6 = new JButton("6");
        seatNums6 = setButtonColorDull(seatNums6);
        gridList.add(seatNums6);
        JButton seatNums7 = new JButton("7");
        seatNums7 = setButtonColorDull(seatNums7);
        gridList.add(seatNums7);
        JButton seatNums8 = new JButton("8");
        seatNums8 = setButtonColorDull(seatNums8);
        gridList.add(seatNums8);
        JButton seatNums9 = new JButton("9");
        seatNums9 = setButtonColorDull(seatNums9);
        gridList.add(seatNums9);
        JButton seatNums10 = new JButton("10");
        seatNums10 = setButtonColorDull(seatNums10);
        gridList.add(seatNums10);
        
        int i = 0;
        for(JButton button : gridList)
        {
            if((i>10&&i<66)&&(button.getLabel().equals("[_]")))// filter out the occupied seats
                {
                    if(!ticketManager.equals(button.getClientProperty("seatSelected")))// filter out the current ticket seatName
                    {
                        //button is an unoccupied seat-button that is not the current transferer ticket. Add the correct ActionListener
                        if(ticketManager.equals("booker"))
                        {
                            button.addActionListener(addBookingActionListener);
                        }
                        else
                        {
                            button.addActionListener(bookingsMoveTicketActionListener);
                        }
                    }
                    else
                    {
                        // button is current seatName
                        button = setButtonColorWarning(button);
                    }
                }
            panel.add(button);
            i++;
        }
        return panel;
    }
    
    
    /**
     * getHeaderPanel
     * 
     * Return a JPanel with the give string
     * set as the header
     * 
     * @param String header
     * @return JPanel panel
     */
    private JPanel getHeaderPanel(String header)
    {
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        header.toUpperCase();
        JLabel label = new JLabel(header);
        label.setFont(new Font("Serif", Font.BOLD, 20));
        panel.add(label);
        return panel;
    }
}
