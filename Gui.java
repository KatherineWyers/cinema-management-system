import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
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
    private int frameWidth;
    private int frameHeight;
    private JList<Object> indexList;
    private DefaultListModel<Object> listModel;
    
    private JPanel northPanel;
    private JPanel centerPanel;
    private JPanel eastPanel;
    private JPanel southPanel;
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
     * runMainApplication
     * Take pageId as input and navigate to the new page
     * @param pageId
     * @return void
     */
    public void run()
    {
        this.displayShowsIndexPage();
        this.frame.pack();
        this.frame.setVisible(true); 
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
        this.updateEastPanel(this.cinema.getFilmList().get(0));// id for the first film
        frame.getContentPane().add(northPanel, BorderLayout.NORTH);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(eastPanel, BorderLayout.EAST);
        this.indexList.addMouseListener(listMouseListener);
    }  
    
    /**
     * displayShowsIndexPage()
     * @return int
     */
    private void displayShowsIndexPage()
    {    
        this.updateNorthPanel("shows", 1);
        this.updateCenterPanel("shows");
        this.updateEastPanel(this.cinema.getShowList().get(0));// id for the first film
        frame.getContentPane().add(northPanel, BorderLayout.NORTH);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(eastPanel, BorderLayout.EAST);
        this.indexList.addMouseListener(listMouseListener);
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
        this.northPanel.removeAll();
        this.northPanel.add(this.getPrimaryNavPanel(category), BorderLayout.NORTH);
        this.northPanel.add(this.getSecondaryNavPanel(category, subPointer), BorderLayout.SOUTH);
        this.northPanel.revalidate();
        this.northPanel.repaint();
    }
    
    /**
     * updateCenterPanel
     * @return JPanel
     */
    private void updateCenterPanel(String category)
    {
        this.centerPanel.removeAll();
        switch(category)
        {
            case "films":
                this.refreshList(this.cinema.getFilmList());
            case "shows":
                this.refreshList(this.cinema.getShowList());
        }
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
            List<JComponent> componentList = new ArrayList<JComponent>();
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
    
    MouseListener listMouseListener = new MouseAdapter()
    {

        /**
         * Method mouseClicked on JList selection
         * If the mouse is clicked twice at an item in the JList, the item information is printed     
         * Adapted from: CCS course materials
         * Date: 26-DEC-2017
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
}
