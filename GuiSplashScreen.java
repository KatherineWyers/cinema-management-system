import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * GuiSplashScreen
 * Display an image when the application loads
 * 
 * Adapted from:
 * https://stackoverflow.com/questions/9864267/
 * loading-image-resource/9866659#9866659
 * 
 * @author nIcE cOw
 * @version 10-MAY-2013
 */
public class GuiSplashScreen extends JPanel
{

    private ImagePanel contentPane;

    /**
     * displaySplashScreen
     * 
     * Create a new frame for the splash screen 
     * and create an instance of ImagePanel
     */
    private void displaySplashScreen() {
        JFrame frame = new JFrame("Cinema Booking System");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        contentPane = new ImagePanel();

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }    
    
    /**
     * ImagePanel Inner class
     * 
     * Inner class for the splash screen
     */
    private class ImagePanel extends JPanel
    {
        private BufferedImage image;
        /**
         * Constructor for objects of class GuiSplashScreen
         */
        public ImagePanel()
        {
            try {
                    image = ImageIO.read(ImagePanel.class.getResource("resources/logo.PNG"));
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
        }
    
        /**
         * Dimension class
         * 
         * getPreferredSize
         * 
         * Set the image panel size to the size of the image
         */
        @Override
        public Dimension getPreferredSize() {
            return image == null ? new Dimension(400, 300): new Dimension(image.getWidth(), image.getHeight());
        }
    
        /**
         * paintComponent
         * 
         * Draw the image onto the JPanel
         */

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }
    
    /**
     * getSplashPanel
     * 
     * Get the splash panel
     * 
     * @return JPanel
     */
    public JPanel getSplashPanel()
    {
        JPanel contentPane = new ImagePanel();
        return contentPane;
    }
    
    /**
     * main Method
     * 
     * @param args String[]
     */
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                new GuiSplashScreen().displaySplashScreen();
            }
        };
        EventQueue.invokeLater(runnable);
    }    
        
        
}
