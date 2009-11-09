package view;
import java.awt.Graphics;
import java.util.Collection;

import javax.swing.ImageIcon;

import actors.Actor;

import model.GameModel;
import util.resources.ResourceManager;


@SuppressWarnings("serial")
public class LevelViewer extends Canvas
{
    private String myGameName;
    private GameModel myGameModel;

    
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
        myGameModel = new GameModel(this);
        myCanvas.repaint();
    }


    public void paintComponent(Graphics pen, Collection<Actor> actors)
    {
        pen.drawImage(icon.getImage(), 0, 0, mySize.width, mySize.height, null);
        for (Actor a: actors)
        {
            a.paint(pen);
        }
    }
    
    public String getGameName()
    {
        return myGameName;
    }
}
