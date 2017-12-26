import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.awt.event.MouseListener;

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
    private final int frameWidth = 1024;
    private final int frameHeight = 768;
    private JList<Object> indexList = new JList<Object>();
    private DefaultListModel<Object> listModel = new DefaultListModel<Object>();
    
    private JPanel northPanel = new JPanel(new BorderLayout());
    private JPanel centerPanel = new JPanel();
    private JPanel eastPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    /**
     * Constructor for objects of class Gui
     */
    public Gui()
    {
        this.frame = new JFrame("Odeon Cinema System");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth,frameHeight);
    }
    
    /**
     * runMainApplication
     * Take pageId as input and navigate to the new page
     * @param pageId
     * @return void
     */
    public void run()
    {
        this.displayFilmsIndexPage();
        // Application will quit when user clicks 'X' in the application interface
    }
    
    /**
     * displayFilmsIndexPage()
     * @return int
     */
    private void displayFilmsIndexPage()
    {    
        this.updateNorthPanel("films", 1);
        this.updateCenterPanel("films");
        this.updateEastPanel(this.cinema.getFilmList().get(0).getId());// id for the first film
        frame.getContentPane().add(northPanel, BorderLayout.NORTH);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(eastPanel, BorderLayout.EAST);
        this.indexList.addMouseListener(listMouseListener);
        this.updateWindow();
    }  
    
    /**
     * updateCenterPanel
     * @return JPanel
     */
    private void updateCenterPanel(String category)
    {
        switch(category)
        {
            case "films":
                this.refreshList(this.cinema.getFilmList());
        }
        JPanel panel = new JPanel();
        this.indexList = new JList<Object>(listModel);
        this.centerPanel.add(this.indexList);
        this.updateWindow();
    }
    
    /*
     * Action Performed
     * 
     */
    MouseListener listMouseListener = new MouseAdapter()
    {

        /**
         * Method mouseClicked on JList selection
         * If the mouse is clicked twice at an item in the JList, the item information is printed
         * @param e object holding mouse event information
         */
        public void mouseClicked(MouseEvent e)
        {            
            if (e.getClickCount() == 2) 
            {
                int index = indexList.locationToIndex(e.getPoint());
                Object object = (Object) listModel.elementAt(index);
                updateEastPanel(object);
            }
        }
    };
    
    /**
     * updateEastPanel
     * @param Object object
     * @return void
     */
    private void updateEastPanel(Object object)
    {   
        eastPanel.removeAll();

        if(object instanceof Film)
        {
            Film film = (Film)object;
            if(film==null)
            {
                throw new IllegalArgumentException("Film Id is not recognized");
            }
            eastPanel.add(new JLabel("<html>Title: " + film.getTitle() + "<br />Director: " + film.getDirector() + "<br />Year: " + film.getYear() + "<br /></html>", SwingConstants.LEFT));
        }
        
        eastPanel.revalidate();
        eastPanel.repaint();
    }

    /**
     * refreshList: Refresh the list model
     * refreshes the list with data from the system.
     * RuntimeException is caught as it is thrown if the List Model is too small
     * 
     * Adapted from: CCS course materials
     * Date: 26-DEC-2017
     * @param DefaultListModel listModel
     * @param List list
     * @return DefaultListModel listModel
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
     * getFilmsIndexMainPanel
     * @return JPanel
     */
    // private JPanel getFilmsShowPanel()
    // {
        // DefaultListModel<Film> listModel = new DefaultListModel<Film>();
        // JList<Film> films = new JList<Film>(listModel);
        
        // JPanel panel = new JPanel();
        // panel.add(films);
        // panel.add(new JLabel("random label2"));
        // return panel;
    // }
    
    /**
     * getUserInputPanel
     * @return JPanel
     */
    private JPanel getUserInputPanel()
    {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("User Input Panel");
        JTextField textField = new JTextField(20);
        JButton button = new JButton("This is a button");
        panel.add(label);
        panel.add(textField);
        panel.add(button);
        return panel;
    }
    
    /**
     * getNavPanel
     * @return JPanel
     */
    private void updateNorthPanel(String category, int subPointer)
    {
        // Primary Nav Panel
        this.northPanel.add(this.getPrimaryNavPanel(category), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel(category, subPointer), BorderLayout.SOUTH);
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
        JButton showsNavBtn = new JButton("Shows");
        JButton customersNavBtn = new JButton("Customers");
        JButton bookingsNavBtn = new JButton("Bookings");
        JButton reportsNavBtn = new JButton("Reports");
        
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
                button2.setText("Add");
                panel.add(button1);
                panel.add(button2);
                break;
            case "shows":
                button1.setText("Index");
                button2.setText("Add");
                panel.add(button1);
                panel.add(button2);
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
    
    /**
     * updateWindow
     * Refresh the window with the new layout and content
     * @return void
     */
    private void updateWindow()
    {
        this.frame.setVisible(true); 
    }
}
