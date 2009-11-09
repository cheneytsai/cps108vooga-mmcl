import java.awt.Graphics;
import javax.swing.ImageIcon;
import util.resources.ResourceManager;

@SuppressWarnings("serial")
public class LevelViewer extends Canvas
{
    private String myGameName;
    
    public LevelViewer(String gameName, String levelName, Canvas canvas)
    {
        myCanvas = canvas;
        myGameName = gameName;
        myCanvas.removeAll();
        myCanvas.setActive(this);

        if (myCanvas.getMouseListeners().length > 0)
        {
            myCanvas.removeMouseListener(myCanvas.getMouseListeners()[0]);
        }
        icon = new ImageIcon(ResourceManager.getString(levelName+ ".background"));

        myCanvas.repaint();
    }


    public void paintComponent(Graphics pen)
    {
        pen.drawImage(icon.getImage(), 0, 0, mySize.width, mySize.height, null);
    }
    
    public String getGameName()
    {
        return myGameName;
    }
}
