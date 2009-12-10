package view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.MissingResourceException;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import actors.Actor;
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
    private List<Actor> myActors;
    private KeyEvent myLastKeyPressed;
    protected int myLevelNum;
    private Timer myTimer;
    private boolean isPaused;
    private final int PAUSE_KEY = KeyEvent.VK_P;
    // animate 25 times per second if possible
    public static final int DEFAULT_DELAY = 1000 / 25; // in milliseconds

    public LevelViewer(String gameName, int levelNum, Canvas canvas)
    {
        isPaused = false;
        myCanvas = canvas;
        myGameName = gameName;
        myLevelNum = levelNum;
        try
        {
            myCanvas.setActive(this);
            myCanvas.requestFocus();
            myModel = canvas.getGameModel();

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
                        if (myLastKeyPressed == null
                                || (myLastKeyPressed != null
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

            icon = new ImageIcon(ResourceManager.getString(myGameName
                    + "level.background"));

            myCanvas.repaint();

            myActors = myModel.getActors();
            myTimer = new Timer(DEFAULT_DELAY, this);
            myTimer.start();

            update();
        } catch (MissingResourceException e)
        {
            // TODO now doubletime doesn't happen. but you can only lose.
            // TODO the problem was that level4 still loaded in the
            // background...
            // TODO thats still a problem =/
            System.out.println("fail");
            myCanvas.loadEnd("Win");
        }
        if(myLevelNum == 1)
            myModel.clearScore();

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

        paint(pen);
    }

    public String getGameName()
    {
        return myGameName;
    }

    public int getLevelNum()
    {
        return myLevelNum;
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
        // let Java runtime know panel needs to be repainted
        myCanvas.repaint();
    }

    public void loadNextLevel()
    {
        myLevelNum++;
        Reflection.createInstance(myGameName.toLowerCase() + "."+ myGameName + "LevelViewer", myGameName, getLevelNum(), myCanvas);
    }

    public void loadBonusLevel(int level)
    {
        Reflection.createInstance(myGameName.toLowerCase() + "."+ myGameName + "LevelViewer", myGameName, level, myCanvas);
    }

    public void loadEnd(String endCondition)
    {
        new EndView(endCondition, myGameName, myCanvas);
    }

}
