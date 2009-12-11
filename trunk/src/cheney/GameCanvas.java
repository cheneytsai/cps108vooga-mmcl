package cheney;

import java.awt.Graphics;
import java.awt.event.*;
import java.util.List;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

import actors.*;
import util.resources.VoogaResources;
import view.Canvas;

/**
 * GameCanvas - Canvas for anything specific to a Game
 * 
 * @author Cheney
 * 
 */
@SuppressWarnings("serial")
public class GameCanvas extends Canvas implements ActionListener
{
    private String myGameName;
    protected List<Actor> myActors;
    protected KeyEvent myLastKeyPressed;
    protected MouseEvent myMouseEvent;
    protected GameEngine myEngine;
    private Timer myTimer;

    public static final int DEFAULT_DELAY = (int) VoogaResources
            .getConstant("DefaultDelay");

    public GameCanvas(String gameName, Canvas canvas)
    {
//        super(gameName, canvas);
//        myEngine = myCanvas.getGameEngine();

        // Listener for Keyboard and Mouse
//        myCanvas.resetMouseListener();
        addKeyListener();

        myCanvas.repaint();

        myEngine.initialize(gameName);

        myActors = myEngine.getActors();

        // Set Framerate
        myTimer = new Timer(DEFAULT_DELAY, this);
        myTimer.start();

        update();

    }

    /**
     * Goto next state in Game
     */
    public void update()
    {
        myEngine.update(myLastKeyPressed, myMouseEvent);
        myMouseEvent = null;
    }

    /**
     * Gets Actors and Paints all actors
     * 
     * @param pen
     *            smart pen to draw on the canvas with
     */
    public void paint(Graphics pen)
    {
        myActors = myEngine.getActors();
        for (Actor current : myActors)
        {
            current.paint(pen);
        }

    }

    /**
     * PaintComponents + call paint method
     */
    public void paintComponent(Graphics pen)
    {

        paint(pen);
    }

    /**
     * Stops timer to establish framerate
     */
    public void stopTimer()
    {
        myTimer.stop();
    }

    /**
     * Get name of game currently displayed
     */
    public String getGameName()
    {
        return myGameName;
    }

    /**
     * Force update and repaint
     */
    public void actionPerformed(ActionEvent arg0)
    {
        update();
        myCanvas.repaint();
    }

    /**
     * Listens for key strokes
     */
    private void addKeyListener()
    {
        myCanvas.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {

                myLastKeyPressed = e;
            }

            public void keyReleased(KeyEvent e)
            {

                myLastKeyPressed = null;
            }
        });
    }

    /**
     * Listens for mouse clicks
     */
    public MouseInputAdapter mouseListener()
    {
        MouseInputAdapter myMouseAdapter = new MouseInputAdapter()
        {
            public void mouseMoved(MouseEvent e)
            {

                myMouseEvent = e;

            }

            public void mouseDragged(MouseEvent e)
            {
                myMouseEvent = e;
            }

            public void mouseClicked(MouseEvent e)
            {

                myMouseEvent = e;
            }

            public void mouseReleased(MouseEvent e)
            {
                myMouseEvent = e;
            }
        };
        return myMouseAdapter;
    }

}
