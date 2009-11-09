import java.awt.Graphics;
import javax.swing.ImageIcon;
import util.resources.ResourceManager;


@SuppressWarnings("serial")
public class LevelViewer extends Canvas
{
    public LevelViewer(String levelname,Canvas canvas) 
    {
        myCanvas = canvas;
        myCanvas.removeAll();
        myCanvas.setActive(this);
        
        icon = new ImageIcon(ResourceManager.getString(levelname+".background"));

        myCanvas.repaint();
    }

    public void paintComponent(Graphics pen) 
    {
        pen.drawImage(icon.getImage(), 0, 0, mySize.width, mySize.height, null);
    }
}
