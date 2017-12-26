import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Gui interface for the application
 *
 * @author Katherine Wyers
 * @version 1.0
 * 
 * Adapted from: H. Gan 2015
 */
public class Gui
{
    private Cinema cinema;
    /**
     * Constructor for objects of class Gui
     */
    public Gui()
    {
        this.setupFrame();
    }
    
    /**
     * setupFrame
     * Control the parameters of the frame
     * 
     */
    private void setupFrame()
    {
        System.out.println("Set up frame");
        final int horizMargin = 10;
        final int vertMargin = 10;
        
        final int floorRows = 2;
        final int floorColumns = 1;
        
        final int frameWidth = 320;
        final int frameHeight = 400;
        
        // create the frame and set it to show 2 parts one on top of the other
        JFrame frame = new JFrame("Customer Complaint System"); // create window frame
        Container contentPane = frame.getContentPane();  // get the background window pane/floor
        contentPane.setLayout(new GridLayout(floorRows,floorColumns));      // divide the floor into 2 sections using rows, col

        // create panels for the top and bottom half of the frame and set them to grid with a nice spacing
        JPanel topHalf = new JPanel();

        topHalf.setLayout(new BorderLayout(horizMargin,vertMargin)); // Put gaps horizontally and vertically
        JPanel bottomHalf = new JPanel();
        bottomHalf.setLayout(new BorderLayout(horizMargin,vertMargin)); // Put the same gaps as top half

        this.createTopHalf(topHalf); //set up the top half components
        this.createBottomHalf(bottomHalf); //set up the bottom half components

        contentPane.add(topHalf);
        contentPane.add(bottomHalf);
        
        frame.setSize(frameWidth,frameHeight);
        //frame.pack(); //adjust window to correct size
        frame.setVisible(true);
    }
    
    /**
     * set up the top half of the frame
     * @param panel -the main carpet
     */
    private void createTopHalf(JPanel panel)
    {
        System.out.println("Running top half");
    }

    /**
     * set up the bottom half of the frame
     * @param panel -the main carpet
     */
    private void createBottomHalf(JPanel panel)
    {
        System.out.println("Running top half");
    }// method
    
    /**
     * runMainApplication
     * Take pageId as input and navigate to the new page
     * @param pageId
     * @return void
     */
    public void run()
    {

        System.out.println("Application Quit");            
    }
}
