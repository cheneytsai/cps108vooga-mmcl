package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import actors.Actor;
import actors.Ball;
import model.GameModel;
import util.resources.ResourceManager;

/**
 * 
 * 
 * @author Lisa Gutermuth
 */
@SuppressWarnings("serial")
public class LevelViewer extends Canvas implements ActionListener
{
    private String myGameName;

    private GameModel myGameModel;
    private List<Actor> myActors;
    private String myLastKeyPressed;
    // animate 25 times per second if possible
    public static final int DEFAULT_DELAY = 1000 / 25;  // in milliseconds

    
    public LevelViewer(String gameName, String levelName, int score, Canvas canvas)
    {
        myCanvas = canvas;
        myGameName = gameName;
        myScore = score;
        myCanvas.removeAll();
        myCanvas.setActive(this);
        myCanvas.requestFocus();
        
        if (myCanvas.getMouseListeners().length > 0)
        {
            myCanvas.removeMouseListener(myCanvas.getMouseListeners()[0]);
        }

        myCanvas.addKeyListener(new KeyAdapter()
            {
                public void keyPressed (KeyEvent e)
                {

                    myLastKeyPressed = KeyEvent.getKeyText(e.getKeyCode());
                }
                public void keyReleased (KeyEvent e)
                {

                    myLastKeyPressed = "";
                }
            });
        
        icon = new ImageIcon(ResourceManager.getString(levelName+ ".background"));
        myCanvas.repaint(); 
        
        myGameModel = new GameModel(this);
        myActors = myGameModel.getActors();
        Timer timer = new Timer(DEFAULT_DELAY, this);
        timer.start();
        myGameModel.update(myLastKeyPressed);

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
        myActors = myGameModel.getActors();
        for (Actor current : myActors)
        {
            current.paint(pen);
        }
    }

    public void actionPerformed(ActionEvent arg0)
    {
        myGameModel.update(myLastKeyPressed);
        // let Java runtime know panel needs to be repainted        
        myCanvas.repaint();
    }
}