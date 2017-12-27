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
        frame.setSize(frameWidth,frameHeight);
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
        this.updateNorthPanel("films", 1);
        this.updateCenterPanel("films", "list");
        this.updateEastPanel(this.cinema.getFilmList().get(0));// id for the first film
        this.clearSouthPanel();
        this.addPanels();
        this.indexList.addMouseListener(listMouseListener);
    }  
    
    /**
     * displayAddFilmPage()
     * @return void
     */
    private void displayAddFilmPage()
    {    
        this.updateNorthPanel("films", 2);
        this.updateCenterPanel("films", "form");
        this.clearEastPanel();
        this.clearSouthPanel();
        this.addPanels();
    }  
    
    /**
     * displayShowsIndexPage()
     * @return void
     */
    private void displayShowsIndexPage()
    {    
        this.updateNorthPanel("shows", 1);
        this.updateCenterPanel("shows", "list");
        this.updateEastPanel(this.cinema.getShowList().get(0));// preselect the first show
        this.clearSouthPanel();
        this.addPanels();
        this.indexList.addMouseListener(listMouseListener);
    }  
    
    
    /**
     * displayAddShowPage()
     * Create the blank HashMap and call the first page
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
        this.updateNorthPanel("shows", 2);
        this.updateCenterPanelAddShowSelectFilm();
        this.updateEastPanelAddShow(this.cinema.getFilmList().get(0));// preselect the first film
        this.clearSouthPanel();
        this.addPanels();
        this.indexList.addMouseListener(addShowListMouseListener);
    }  
    
    /**
     * displayAddShowSelectScreen()
     * @return void
     */
    private void displayAddShowSelectScreen()
    {    
        this.updateNorthPanel("shows", 2);
        this.updateCenterPanelAddShowSelectScreen();
        this.updateEastPanelAddShow(this.cinema.getScreenList().get(0));// preselect the first screen
        this.clearSouthPanel();
        this.addPanels();
        this.indexList.addMouseListener(addShowListMouseListener);
    }  
    
    /**
     * displayAddShowDetails()
     * @return void
     */
    private void displayAddShowDetails()
    {
        this.updateNorthPanel("shows", 2);
        this.updateCenterPanel("shows", "form");
        this.clearEastPanel();
        this.clearSouthPanel();
        this.addPanels();
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
     * updateNorthPanel
     * @return void
     */
    private void updateNorthPanel(String category, int subPointer)
    {
        this.northPanel.removeAll();
        this.northPanel.add(this.getPrimaryNavPanel(category), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel(category, subPointer), BorderLayout.SOUTH);
        this.northPanel.revalidate();
        this.northPanel.repaint();
    }
    
    /**
     * updateCenterPanel
     * @param String category
     * @param String layout {"index", "add"}
     * @return void
     */
    private void updateCenterPanel(String category, String layout)
    {
        switch(layout)
        {
            case "list":
                this.updateCenterPanelList(category);
                break;
            case "form":
                this.updateCenterPanelForm(category);
                break;
        }
    }
    
    /**
     * updateCenterPanelIndex
     * @param String category
     * @return void
     */
    private void updateCenterPanelList(String category)
    {
        this.centerPanel.removeAll();
        switch(category)
        {
            case "films":
                this.refreshList(this.cinema.getFilmList());
                break;
            case "shows":
                this.refreshList(this.cinema.getShowList());
                break;
        }
        JPanel panel = new JPanel();
        this.indexList = new JList<Object>(listModel);
        this.centerPanel.add(this.indexList);
        this.centerPanel.revalidate();
        this.centerPanel.repaint();
    }
    
    /**
     * updateCenterPanelForm
     * @return void
     */
    private void updateCenterPanelForm(String category)
    {
        
        this.centerPanel.removeAll();
        
        JPanel panel = new JPanel();
        
        JTextField textField1;
        JTextField textField2;
        JTextField textField3;
        JTextField textField4;
        JTextField textField5;
        JTextField textField6;
        JTextField textField7;
        JButton submit;
        switch(category)
        {
            case "films":
                panel.setLayout(new GridLayout(2,6,5,5));
                panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                textField1 = new JTextField(20);
                this.formData.put("title", textField1);
                textField2 = new JTextField(20);
                this.formData.put("director", textField2);
                textField3 = new JTextField(4);
                this.formData.put("year", textField3);
                textField4 = new JTextField(20);
                this.formData.put("language", textField4);
                textField5 = new JTextField(2);
                this.formData.put("subtitles", textField5);
                submit = new JButton("Submit");
                submit.addActionListener(addFilmActionListener);
                
                panel.add(new JLabel("Title:"));
                panel.add(textField1);
                panel.add(new JLabel("Director:"));
                panel.add(textField2);
                panel.add(new JLabel("Year:"));
                panel.add(textField3);
                panel.add(new JLabel("Language:"));
                panel.add(textField4);
                panel.add(new JLabel("Subtitles:"));
                panel.add(textField5);
                panel.add(new JLabel(""));
                panel.add(submit);
                break;
            case "shows":
                panel.setLayout(new GridLayout(2,7,5,5));
                panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                textField1 = new JTextField(4);
                this.formData.put("year", textField1);
                textField2 = new JTextField(2);
                this.formData.put("month", textField2);
                textField3 = new JTextField(2);//
                this.formData.put("date", textField3);
                textField4 = new JTextField(2);
                this.formData.put("hour", textField4);
                textField5 = new JTextField(2);
                this.formData.put("minute", textField5);
                textField6 = new JTextField(20);
                this.formData.put("priceRegular", textField5);
                textField7 = new JTextField(20);
                this.formData.put("priceVip", textField5);
                this.formData.put("detailsEntered", "true");
                submit = new JButton("Submit");
                submit.addActionListener(addShowActionListener);
                
                panel.add(new JLabel("Year (eg: 2017)"));
                panel.add(textField1);
                panel.add(new JLabel("MonthNo. (eg: 6)"));
                panel.add(textField2);
                panel.add(new JLabel("Date (eg 25)"));
                panel.add(textField3);
                panel.add(new JLabel("Hour (0-23)"));
                panel.add(textField4);
                panel.add(new JLabel("Minute (0-59)"));
                panel.add(textField5);
                panel.add(new JLabel("Price Regular (eg 8.50)"));
                panel.add(textField6);
                panel.add(new JLabel("Price VIP (eg 10.75)"));
                panel.add(textField7);
                panel.add(submit);
                break;
            }
        this.centerPanel.add(panel);
        this.centerPanel.revalidate();
        this.centerPanel.repaint();
    }
    
    /**
     * updateCenterPanelIndex
     * @param String category
     * @return void
     */
    private void updateCenterPanelAddShowSelectFilm()
    {
        this.centerPanel.removeAll();
        this.refreshList(this.cinema.getFilmList());
        JPanel panel = new JPanel();
        this.indexList = new JList<Object>(listModel);
        this.centerPanel.add(this.indexList);
        this.centerPanel.revalidate();
        this.centerPanel.repaint();
    }
    
    /**
     * updateCenterPanelAddShowSelectScreen
     * @param String category
     * @return void
     */
    private void updateCenterPanelAddShowSelectScreen()
    {
        this.centerPanel.removeAll();
        this.refreshList(this.cinema.getScreenList());
        JPanel panel = new JPanel();
        this.indexList = new JList<Object>(listModel);
        this.centerPanel.add(this.indexList);
        this.centerPanel.revalidate();
        this.centerPanel.repaint();
    }
    
    /**
     * updateEastPanel
     * @param Object object
     * @return void
     */
    private void updateEastPanel(Object object)
    {   
        this.eastPanel.removeAll();
        if(object instanceof Film)
        {
            Film film = (Film)object;
            if(film==null)
            {
                throw new IllegalArgumentException("Film Id is not recognized");
            }
            eastPanel.add(new JLabel("<html>Title: " + film.getTitle() + "<br />Director: " + film.getDirector() + "<br />Year: " + film.getYear() + "<br /></html>"));
        }
        
        if(object instanceof Show)
        {
            Show show = (Show)object;
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
        }        
        this.eastPanel.revalidate();
        this.eastPanel.repaint();        
    }
    
        
    /**
     * clearEastPanel
     * Clear all content from the eastPanel
     * @return void
     */
    
    private void clearEastPanel()
    {
        this.eastPanel.removeAll();
        this.eastPanel.revalidate();
        this.eastPanel.repaint();        
    }
    
    /**
     * updateEastPanelAddShow
     * @param Map formData
     * @return void
     */
    private void updateEastPanelAddShow(Object object)
    {   
        this.eastPanel.removeAll();
        
        if(object instanceof Film)
        {
            Film film = (Film)object;
            if(film==null)
            {
                throw new IllegalArgumentException("Film Id is not recognized");
            }
            eastPanel.add(new JLabel("<html>Title: " + film.getTitle() + "<br />Director: " + film.getDirector() + "<br />Year: " + film.getYear() + "<br /></html>"));
            this.formData.put("tempFilm", film);// place film in temporary HashMap location, until 'Select Film for Show' has been clicked. 
            JButton addFilmButton = new JButton("Select Film for Show");
            addFilmButton.addActionListener(addShowActionListener);
            this.eastPanel.add(addFilmButton);
        }
        
        if(object instanceof Screen)
        {
            Screen screen = (Screen)object;
            if(screen==null)
            {
                throw new IllegalArgumentException("Screen Id is not recognized");
            }
            eastPanel.add(new JLabel("<html>Title: " + screen.getTitle()));
            formData.put("tempScreen", screen);// place screen in temporary HashMap location, until 'Select Screen for Show' has been clicked. 
            JButton addScreenButton = new JButton("Select Screen for Show");
            addScreenButton.addActionListener(addShowActionListener);
            this.eastPanel.add(addScreenButton);
        }
        
        this.eastPanel.revalidate();
        this.eastPanel.repaint();  
    }
    
    /**
     * updateSouthPanel
     * @return void
     */
    private void updateSouthPanel()
    {
        this.southPanel.removeAll();
        JLabel label = new JLabel("Please make a selection:");
        JTextField textField = new JTextField(20);
        JButton button = new JButton("Submit");
        southPanel.add(label);
        southPanel.add(textField);
        southPanel.add(button);
        this.southPanel.revalidate();
        this.southPanel.repaint();
    }
    
    
    /**
     * clearSouthPanel
     * Clear all content from the southPanel
     * @return void
     */
    private void clearSouthPanel()
    {
        this.southPanel.removeAll();
        this.southPanel.revalidate();
        this.southPanel.repaint();        
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
        JButton bookingsNavBtn = new JButton("Bookings");
        JButton reportsNavBtn = new JButton("Reports");
        
        filmsNavBtn.addActionListener(NavButtonActionListener);
        showsNavBtn.addActionListener(NavButtonActionListener);
        
        switch(category)
        {
            case "films":
                filmsNavBtn = this.setButtonColorActive(filmsNavBtn);
                break;
            case "shows":
                showsNavBtn = this.setButtonColorActive(showsNavBtn);
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
    
    MouseListener listMouseListener = new MouseAdapter()
    {
        /**
         * Method mouseClicked on JList selection
         * If the mouse is clicked once at an item in the JList, the eastPanel is 
         * updated with the details for the list
         * @param e object holding mouse event information
         */
        public void mouseClicked(MouseEvent e)
        {            
            if (e.getClickCount() == 1) 
            {
                int index = indexList.locationToIndex(e.getPoint());
                Object object = (Object) listModel.elementAt(index);
                updateEastPanel(object);
            }
        }
    };
    
    ActionListener NavButtonActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {      
            // Use of the getClientProperty to identify the button where the event originated
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
            }
        }
    };
    
    
        
    ActionListener addFilmActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {      
            // Use of the getClientProperty to identify the button where the event originated
            // Code adapted from:
            // https://stackoverflow.com/questions/11037622/pass-variables-to-actionlistener-in-java
            // Author: Robin
            // Accessed: 26-DEC-2017
            
            JTextField title = (JTextField)formData.get("title");
            JTextField director = (JTextField)formData.get("director");
            JTextField language = (JTextField)formData.get("language");
            JTextField subtitles = (JTextField)formData.get("subtitles");
            JTextField yearTextField= (JTextField)formData.get("year");
            int yearInt = 0;
            
            if(title.getText().length()>20||title.getText().length()<3)
            {
                return;
            }
            else if(director.getText().length()>20||director.getText().length()<3)
            {
                return;
            }
            else if(language.getText().length()>20||language.getText().length()<3)
            {
                return;
            }
            else if(subtitles.getText().length()>2||subtitles.getText().length()<1)
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
    
    MouseListener addShowListMouseListener = new MouseAdapter()
    {
        /**
         * Method mouseClicked on JList selection
         * If the mouse is clicked once at an item in the JList, the eastPanel is 
         * updated with the details for the list
         * @param e object holding mouse event information
         */
        public void mouseClicked(MouseEvent e)
        {   
            if (e.getClickCount() == 1) 
            {
                int index = indexList.locationToIndex(e.getPoint());
                Object object = (Object) listModel.elementAt(index);
                updateEastPanelAddShow(object);
            }
        }
    };
        
    ActionListener addShowActionListener = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {      
            // Use of the getClientProperty to identify the button where the event originated
            // Code adapted from:
            // https://stackoverflow.com/questions/11037622/pass-variables-to-actionlistener-in-java
            // Author: Robin
            // Accessed: 26-DEC-2017
            
            Film film;
            Screen screen;
            if(formData.get("tempFilm")!=null)
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
                displayAddShowDetails();
                return;
            }
            
            date = new GregorianCalendar(yearInt, monthInt, dateInt, hourInt, minuteInt);
            
            cinema.addShow(date, screen, film, (float)12.50, (float)15.00);
            formData = null;// reset the data object
            System.out.println("completed the show maker");
            displayShowsIndexPage();
            return;
        }
    };
    
    /**
     * getSeatingGridPanel
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
     * addPanels
     * Add the north, centre, east and south panels 
     * to the layout
     * @return void
     */
    public void addPanels()
    {
        this.frame.getContentPane().add(northPanel, BorderLayout.NORTH);
        this.frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        this.frame.getContentPane().add(eastPanel, BorderLayout.EAST);
        this.frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
    }
}
