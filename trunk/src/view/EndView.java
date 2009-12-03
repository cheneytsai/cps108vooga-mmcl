package view;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import util.resources.ResourceManager;

/**
 * @author Lisa Gutermuth
 */
@SuppressWarnings("serial")
public class EndView extends Canvas
{
    public EndView(String endCondition,String gameName,int score,Canvas canvas)
    {
        myCanvas = canvas;
        myScore = score;
        myCanvas.setActive(this);
        
        icon = new ImageIcon(ResourceManager.getString(endCondition+ ".background"));
        
        myGameModel.setGameOver(true);
        saveScores(gameName);
    }
    
    public void paintComponent(Graphics pen)
    {
        pen.drawImage(icon.getImage(), 0, 0, mySize.width, mySize.height, null);
    }
    
    public void saveScores(String gameName)
    {
        String name = (String) JOptionPane.showInputDialog(ResourceManager.getString("Name"),null);

        try
        {
            FileWriter output = new FileWriter(ResourceManager.getString(gameName+"Scores"),true);
            output.append(name +"\t\t"+myScore);
            output.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
