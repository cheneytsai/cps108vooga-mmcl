package view;
import java.awt.Color;
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
    private int myScore;
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
        pen.setFont(SCOREBOARD_FONT);
        pen.setColor(Color.WHITE);
        pen.drawString(ResourceManager.getString("Title").substring(0,10),0,20);
        pen.drawString(myGameName,0,40);
        pen.drawString(ResourceManager.getString("Score") + myScore, 800, 20);
        
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
