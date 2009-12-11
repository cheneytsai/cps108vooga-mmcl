package view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import actors.Actor;
import gameengine.GameModel;
import util.reflection.Reflection;
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
    protected List<Actor> myActors;
    private KeyEvent myLastKeyPressed;
    protected String myLevelName;
    protected Timer myTimer;
    protected boolean isPaused;
    private final int PAUSE_KEY = KeyEvent.VK_P;
    // animate 25 times per second if possible
    public static final int DEFAULT_DELAY = 1000 / 25; // in milliseconds

    public LevelViewer(String gameName, String levelName, Canvas canvas, GameModel model)
    {
        isPaused = false;
        myCanvas = canvas;
        myGameName = gameName;
        myModel = model;
        myLevelName = levelName;

        myCanvas.setActive(this);
        myCanvas.requestFocus();

        if (myCanvas.getMouseListeners().length > 0)
        {
            myCanvas.removeMouseListener(myCanvas.getMouseListeners()[0]);
        }

        myCanvas.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {

                if (e.getKeyCode() == PAUSE_KEY)
                {
                    if (myLastKeyPressed == null || (myLastKeyPressed != null
                                    && myLastKeyPressed.equals(e)))
                    {
                        isPaused = !isPaused;
                        myLastKeyPressed = e;
                    }

                } else
                    myLastKeyPressed = e;
            }

            public void keyReleased(KeyEvent e)
            {
                myLastKeyPressed = null;
            }
        });

        myTimer = new Timer(DEFAULT_DELAY, this);
        icon = new ImageIcon(ResourceManager.getString(myGameName+ "level.background"));

        myCanvas.repaint();       
    }
    
    public void startGame()
    {
        myActors = myModel.getActors();
        myTimer.start();

        update();
    }
    
    public void stopTimer()
    {
        myTimer.stop();
    }

    public void update()
    {
        if (!isPaused)
        {       
            myModel.update(myLastKeyPressed);
        }      
    }

    public void paintComponent(Graphics pen)
    {
        pen.drawImage(icon.getImage(), 0, 0, mySize.width, mySize.height, null);
        pen.setFont(SCOREBOARD_FONT);

        if(myActors != null)
        {
            paint(pen);
        }
    }

    public String getGameName()
    {
        return myGameName;
    }

    public String getLevelName()
    {
        return myLevelName;
    }

    /**
     * Never called by you directly, instead called by Java runtime when area of
     * screen covered by this container needs to be displayed (i.e., creation,
     * uncovering, change in status)
     * 
     * @param pen
     *            smart pen to draw on the canvas with
     */
    public void paint(Graphics pen)
    {
        for (Actor current : myActors)
        {
            current.paint(pen);
        }

    }

    public void actionPerformed(ActionEvent arg0)
    {
        update();
        myCanvas.repaint();
    }

    public void setLevelName(String levelName)
    {
        myLevelName = levelName;
    }

    public void loadBonusLevel(int level)
    {
        Reflection.createInstance(myGameName.toLowerCase() + "."+ myGameName + "LevelViewer", myGameName, level, myCanvas);
    }

    public void loadEnd(String endCondition)
    {
        new EndView(endCondition, myGameName, myCanvas,myModel);
    }
    
    public void saveState()
    {
        System.out.println("save");
        myModel.saveState();
    }
}
