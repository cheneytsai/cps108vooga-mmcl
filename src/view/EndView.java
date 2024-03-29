package view;

import gameengine.GameModel;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import util.resources.ResourceManager;

/**
 * 
 * 
 * @author Lisa Gutermuth
 */
@SuppressWarnings("serial")
public class EndView extends Canvas
{
    public EndView(String endCondition, String gameName, Canvas canvas,
            GameModel model)
    {
        myCanvas = canvas;
        myCanvas.setActive(this);
        myModel = model;
        myGameName = gameName;
        myCanvas.requestFocus();

        icon = new ImageIcon(ResourceManager.getString(endCondition
                + "Background"));

        saveScores(gameName);
        myCanvas.repaint();

    }

    public void paintComponent(Graphics pen)
    {
        pen.drawImage(icon.getImage(), 0, 0, mySize.width, mySize.height, null);
    }

    public void saveScores(String gameName)
    {
        GregorianCalendar time = new GregorianCalendar();

        String name = (String) JOptionPane.showInputDialog(ResourceManager
                .getString("Name"), null);
        if (name == null || name.length() == 0)
        {
            name = ResourceManager.getString("NoName");
        }

        try
        {
            FileWriter output = new FileWriter(ResourceManager
                    .getString(gameName + "Scores"), true);
            output.append("\n" + name + "\t\t" + myModel.getScore() + "\t\t"
                    + time.getTime());
            output.close();
        } catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public String getGameName()
    {
        return myGameName;
    }
}
