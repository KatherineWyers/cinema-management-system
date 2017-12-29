import java.awt.*;
import java.awt.event.*;
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

    private void displaySplashScreen() {
        JFrame frame = new JFrame("Image Example");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        contentPane = new ImagePanel();

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }    
    
    private class ImagePanel extends JPanel
    {
        private BufferedImage image;
        /**
         * Constructor for objects of class GuiSplashScreen
         */
        public ImagePanel()
        {
            try {
                    image = ImageIO.read(ImagePanel.class.getResource("/resources/odeon-logo.png"));
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
        }
    
        @Override
        public Dimension getPreferredSize() {
            return image == null ? new Dimension(400, 300): new Dimension(image.getWidth(), image.getHeight());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }
    
    /**
     * getSplashPanel
     * @return JPanel
     */
    public JPanel getSplashPanel()
    {
        JPanel contentPane = new ImagePanel();
        return contentPane;
    }
    
    
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
