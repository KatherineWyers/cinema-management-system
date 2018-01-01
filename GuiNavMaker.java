import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Write a description of class GuiNavMaker here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GuiNavMaker
{

    /**
     * Constructor for objects of class GuiNavMaker
     */
    public GuiNavMaker()
    {}
    
    
    /**
     * getHeaderPanel
     * 
     * Return a JPanel with the give string
     * set as the header
     * 
     * @param header String 
     * @return JPanel
     */
    public static JPanel getHeaderPanel(String header)
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
