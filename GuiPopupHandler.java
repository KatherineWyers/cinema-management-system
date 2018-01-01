import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * GuiPopupHandler
 * Manage the dialog box messages
 * for the GUI
 *
 * @author Katherine Wyers
 * @version DEC-2017
 */
public class GuiPopupHandler
{
    /**
     * Constructor for objects of class GuiExceptionHandler
     */
    public GuiPopupHandler()
    {}

    /**
     * ifIntNotInRangeShowDialog
     * 
     * If the integer value give is in the 
     * range minValue and maxValue, return int. 
     * Else, display a 
     * dialog popup box with the message
     * and return -1
     * 
     *
     * @param value int
     * @param minValue int
     * @param maxValue int
     * @param dialogMessage String
     * @param int
     */
    public static int ifIntNotInRangeShowDialog(int value, int minValue, int maxValue, String dialogMessage)
    {
        JFrame popupFrame = new JFrame("Invalid Input");
        
        if(value<minValue||value>maxValue)
        {
            JOptionPane.showMessageDialog(popupFrame, dialogMessage);
            return -1;
        }  
        return value;
    }

    /**
     * ifFloatNotInRangeShowDialog
     * 
     * If the float value give is not in the 
     * range minValue and maxValue, display a 
     * dialog popup box with the message
     *
     * @param value float
     * @param minValue float
     * @param maxValue float
     * @param dialogMessage String
     * @return float
     */
    public static float ifFloatNotInRangeShowDialog(float value, float minValue, float maxValue, String dialogMessage)
    {
        JFrame popupFrame = new JFrame("Invalid Input");
        
        if(value<minValue||value>maxValue)
        {
            JOptionPane.showMessageDialog(popupFrame, dialogMessage);
            return (float)-1.0;
        }  
        return value;
    }
    
    /**
     * parseToIntOrShowDialog
     * 
     * If the string cannot be parsed to an 
     * integer, display a dialog popup box 
     * with the message and return -1. Else, parse to 
     * int and return int
     *
     * @param string String
     * @param dialogMessage String
     * @return int
     */
    public static int parseToIntOrShowDialog(String string, String dialogMessage)
    {
        JFrame popupFrame = new JFrame("Invalid Input");
        int result;
        try
        {
            result = Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(popupFrame, dialogMessage);
            return -1;
        }
        return result;
    }
    
    /**
     * parseToFloatOrShowDialog
     * 
     * If the string cannot be parsed to an 
     * float, display a dialog popup box 
     * with the message and return -1.0. Else, parse to 
     * float and return float
     *
     * @param string String
     * @param dialogMessage String
     * @return float
     */
    public static float parseToFloatOrShowDialog(String string, String dialogMessage)
    {
        JFrame popupFrame = new JFrame("Invalid Input");
        float result;
        try
        {
            result = Float.parseFloat(string);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(popupFrame, dialogMessage);
            return (float)-1.0;
        }
        return result;
    }
}
