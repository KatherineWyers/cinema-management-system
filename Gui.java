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
     * updateEastPanelFilmsIndex
     * @param Film film
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
        //reportsNavBtn.putClientProperty("page", "reportsIndex");
        
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
                panel.add(button1);
                button1.addActionListener(NavButtonActionListener);
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
        button.setBackground(Color.red);
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
            } catch (Exception ex) {
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
}
