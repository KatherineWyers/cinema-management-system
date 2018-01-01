import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

/**
 * GuiHelper Class
 * Utility class for the GUI
 *
 * @author Katherine Wyers
 * @version DEC-2017
 */
public class GuiHelper
{
    /**
     * Constructor for objects of class GuiNavMaker
     */
    public GuiHelper()
    {}
    
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
     * @param button JButton
     * @return JButton
     */
    public static JButton setButtonColorActive(JButton button)
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
     * @param button JButton
     * @return JButton
     */
    public static JButton setButtonColorAccent(JButton button)
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
     * @param button JButton
     * @return JButton
     */
    public static JButton setButtonColorDull(JButton button)
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
    * @param button JButton 
    * @return JButton
    */
    public static JButton setButtonColorWarning(JButton button)
    {
        button.setBackground(Color.orange);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }
    
    /**
    * setButtonColorSelected
    * 
    * Set the background color of the button
    * so show successfully select
    * 
    * Adapted from code at 
    * https://stackoverflow.com/questions/1065691
    * /how-to-set-the-background-color-of-a-jbutton-on-the-mac-os
    * 
    * Author: codethulu
    * Accessed 26-DEC-2017
    * 
    * @param button JButton 
    * @return JButton
    */
    public static JButton setButtonColorSelected(JButton button)
    {
        button.setBackground(Color.green);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }
   
    /**
     * getSeatingGridPanel
     * 
     * Create a JPanel version of the seatingGrid
     * 
     * @param seatingGrid boolean[][] 
     * @return JPanel
     */
    public static JPanel getSeatingGridPanel(boolean[][] seatingGrid)
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
            rowLetter = MathHelper.convertToRowLetter(i+1);
            
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
     * getSeatingGridButtons
     * 
     * Get a List of seatingGrid button 
     * 
     * @param seatingGrid boolean[][] 
     * @return List
     */
   public static List getSeatingGridButtons(boolean[][] seatingGrid)
   {
        List<JButton> gridList = new ArrayList<JButton>(); 
        
        JButton screen0 = new JButton("");
        screen0 = GuiHelper.setButtonColorAccent(screen0);
        gridList.add(screen0);
        JButton screen1 = new JButton("[");
        screen1 = GuiHelper.setButtonColorAccent(screen1);
        gridList.add(screen1);
        JButton screen2 = new JButton("[");
        screen2 = GuiHelper.setButtonColorAccent(screen2);
        gridList.add(screen2);
        JButton screen3 = new JButton("S");
        screen3 = GuiHelper.setButtonColorAccent(screen3);
        gridList.add(screen3);
        JButton screen4 = new JButton("C");
        screen4 = GuiHelper.setButtonColorAccent(screen4);
        gridList.add(screen4);
        JButton screen5 = new JButton("R");
        screen5 = GuiHelper.setButtonColorAccent(screen5);
        gridList.add(screen5);
        JButton screen6 = new JButton("E");
        screen6 = GuiHelper.setButtonColorAccent(screen6);
        gridList.add(screen6);
        JButton screen7 = new JButton("E");
        screen7 = GuiHelper.setButtonColorAccent(screen7);
        gridList.add(screen7);
        JButton screen8 = new JButton("N");
        screen8 = GuiHelper.setButtonColorAccent(screen8);
        gridList.add(screen8);
        JButton screen9 = new JButton("]");
        screen9 = GuiHelper.setButtonColorAccent(screen9);
        gridList.add(screen9);
        JButton screen10 = new JButton("]");
        screen10 = GuiHelper.setButtonColorAccent(screen10);
        gridList.add(screen10);
        
        // row A
        JButton a = new JButton("A");
        a = GuiHelper.setButtonColorDull(a);
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
        b = GuiHelper.setButtonColorDull(b);
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
        c = GuiHelper.setButtonColorDull(c);
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
        d = GuiHelper.setButtonColorDull(d);
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
        e = GuiHelper.setButtonColorDull(e);
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
        seatNums0 = GuiHelper.setButtonColorDull(seatNums0);
        gridList.add(seatNums0);
        JButton seatNums1 = new JButton("1");
        seatNums1 = GuiHelper.setButtonColorDull(seatNums1);
        gridList.add(seatNums1);
        JButton seatNums2 = new JButton("2");
        seatNums2 = GuiHelper.setButtonColorDull(seatNums2);
        gridList.add(seatNums2);
        JButton seatNums3 = new JButton("3");
        seatNums3 = GuiHelper.setButtonColorDull(seatNums3);
        gridList.add(seatNums3);
        JButton seatNums4 = new JButton("4");
        seatNums4 = GuiHelper.setButtonColorDull(seatNums4);
        gridList.add(seatNums4);
        JButton seatNums5 = new JButton("5");
        seatNums5 = GuiHelper.setButtonColorDull(seatNums5);
        gridList.add(seatNums5);
        JButton seatNums6 = new JButton("6");
        seatNums6 = GuiHelper.setButtonColorDull(seatNums6);
        gridList.add(seatNums6);
        JButton seatNums7 = new JButton("7");
        seatNums7 = GuiHelper.setButtonColorDull(seatNums7);
        gridList.add(seatNums7);
        JButton seatNums8 = new JButton("8");
        seatNums8 = GuiHelper.setButtonColorDull(seatNums8);
        gridList.add(seatNums8);
        JButton seatNums9 = new JButton("9");
        seatNums9 = GuiHelper.setButtonColorDull(seatNums9);
        gridList.add(seatNums9);
        JButton seatNums10 = new JButton("10");
        seatNums10 = GuiHelper.setButtonColorDull(seatNums10);
        gridList.add(seatNums10);
        
        
        return gridList;
   }
}
