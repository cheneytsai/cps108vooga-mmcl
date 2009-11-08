import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import util.resources.ResourceManager;

/*
 * stuff that actually chooses the game.
 */
@SuppressWarnings("serial")
public class GameChooser extends Canvas
{
    private Dimension mySize = new Dimension(960,720);
    private static final Font DEFAULT_TITLE_FONT =
        new Font("TAHOMA", Font.BOLD, 75);
    private static final Font DEFAULT_OPTION_FONT = 
        new Font("TAHOMA", Font.BOLD, 50);
    
    public GameChooser()
    {
        icon = new ImageIcon(ResourceManager.getString("StartingBackground"));
    }
    
    public void paintComponent(Graphics pen)
    {
        //Set Background
        pen.drawImage(icon.getImage(), 0, 0, mySize.width, mySize.height, null);
        
        //Set Pen
        Graphics2D myPen = (Graphics2D) pen;
        myPen.setColor(Color.BLUE);
        
        //Draw Title
        myPen.setFont(DEFAULT_TITLE_FONT);
        myPen.drawString(ResourceManager.getString("Title"), 100, 100);
        
        //Draw Options
        myPen.setFont(DEFAULT_OPTION_FONT);
        String[] menuOptions = ResourceManager.getString("GameChooser").split(" ");
        int counter = 0;
        for(String option : menuOptions)
        {
            myPen.drawString(option, 375, 250 + 100*counter);
            counter++;
        }
        myPen.drawString(ResourceManager.getString("Quit"), 375, 250 + 100*counter);
        
//        MouseListener?  To tell when a button is clicked within the right area
        
        
        setOpaque(false);
    }
}