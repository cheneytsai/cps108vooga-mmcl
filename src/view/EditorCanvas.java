package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import model.EditorModel;
import model.GameModel;
import util.reflection.Reflection;
import util.resources.ResourceManager;
import actions.Quit;
import actors.Actor;

public class EditorCanvas extends Canvas
{
    private String myGameName;

    private EditorModel myEditorModel;
    private List<Actor> myActors;
    private String myLastKeyPressed;
    private String myLevelName;

    
    public EditorCanvas(String gameName, String levelName, int score, Canvas canvas)
    {

        myCanvas = canvas;
        myGameName = gameName;
        myLevelName = levelName;
        myScore = score;
        myCanvas.removeAll();
        myCanvas.setActive(this);
        myCanvas.requestFocus();
        
        if (myCanvas.getMouseListeners().length > 0)
        {
            myCanvas.removeMouseListener(myCanvas.getMouseListeners()[0]);
        }

        myCanvas.addMouseListener(mouseListener());
        
        icon = new ImageIcon(ResourceManager.getString(levelName+ ".background"));

        myCanvas.repaint(); 
        
        myEditorModel = new EditorModel(this);
        myActors = myEditorModel.getActors();
        myEditorModel.update(myLastKeyPressed);

    }

    
    public void paintComponent(Graphics pen)
    {        
        pen.drawImage(icon.getImage(), 0, 0, mySize.width, mySize.height, null);
        pen.setFont(SCOREBOARD_FONT);
        pen.setColor(Color.WHITE);
        pen.drawString(ResourceManager.getString("Title").substring(0,10),0,20);
        pen.drawString(myGameName,0,40);
        pen.drawString(ResourceManager.getString("Score") + myScore, 800, 20);
        
        paint(pen);
    }
    
    public String getGameName()
    {
        return myGameName;
    }
    
    
    /**
     * Never called by you directly, instead called by Java runtime
     * when area of screen covered by this container needs to be 
     * displayed (i.e., creation, uncovering, change in status)
     * 
     * @param pen smart pen to draw on the canvas with
     */
    public void paint (Graphics pen)
    {
        myActors = myEditorModel.getActors();
        for (Actor current : myActors)
        {
            current.paint(pen);
        }
    }
    
    public MouseAdapter mouseListener()
    {
        MouseAdapter myMouseAdapter = new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                boolean used = false;
                for(Actor current : myActors)
                {
                    if(e.getPoint().equals(current.getCenter()))
                    {
                        used = true;
                        break;
                    }
                }
                
//              if nothing there, open popup
//              else,be able to update that object
                if(used == false)
                {
                    EditorCreate created = new EditorCreate(e.getX(),e.getY());
                    String[] actorStats = created.getStats();
                    
                    myEditorModel.add((Actor) Reflection.createInstance(
                            actorStats[0],
                            actorStats[1],
                            new Dimension(Integer.parseInt(actorStats[2]),Integer.parseInt(actorStats[3])),
                            new Point(Integer.parseInt(actorStats[4]), Integer.parseInt(actorStats[5])),
                            this));
                }
                 
            }
        };
        return myMouseAdapter;
    }
}
