package view;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import util.resources.ResourceManager;

/**
 * 
 * @author Lisa Gutermuth
 */
@SuppressWarnings("serial")
public class EndView extends Canvas
{
    public EndView(String endCondition,int score,Canvas canvas)
    {
        myCanvas = canvas;
        myScore = score;
        myCanvas.setActive(this);
        
        icon = new ImageIcon(ResourceManager.getString(endCondition+ ".background"));
        
        myGameModel.setGameOver(true);
    }
    
    public void paintComponent(Graphics pen)
    {
        pen.drawImage(icon.getImage(), 0, 0, mySize.width, mySize.height, null);
    }
}
