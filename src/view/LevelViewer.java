package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.MissingResourceException;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import actions.AddPiece;
import actors.Actor;
import actors.Grid;
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
    protected KeyEvent myLastKeyPressed;
    protected int myLevelNum;
    private Timer myTimer;
    private boolean isPaused;
    // animate 25 times per second if possible
    public static final int DEFAULT_DELAY = 1000 / 25; // in milliseconds

    public LevelViewer(String gameName, int levelNum, int score, Canvas canvas)
    {
        Grid.resetGrid();
        isPaused = false;
        myCanvas = canvas;
        myGameName = gameName;
        myLevelNum = levelNum;
        myScore = score;
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

                    if (e.getKeyCode() == KeyEvent.VK_P)
                    {
                        if (myLastKeyPressed != null)
                        {
                            if (myLastKeyPressed.getKeyCode() != e.getKeyCode())
                            {
                                System.out.println(isPaused);
                                isPaused = !isPaused;
                                System.out.println(isPaused);
                                myLastKeyPressed = e;
                            }

                        } else
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

        if (myGameName.equals("Tetris"))
        {
            pen.setColor(Color.BLACK);
            pen.drawString(ResourceManager.getString("Title").substring(0, 10),
                    800, 25);
            pen.drawString(myGameName, 800, 50);
            pen.setColor(Color.WHITE);
            pen.fillRect(800, 160, 110, 50);
            pen.setColor(Color.BLACK);
            pen.drawRect(800, 160, 110, 50);
            pen.drawString(ResourceManager.getString("Score"), 800, 155);
            pen.drawString("" + myScore, 805, 190);
            pen.setColor(Color.WHITE);
            pen.fillRect(800, 260, 110, 50);
            pen.setColor(Color.BLACK);
            pen.drawRect(800, 260, 110, 50);
            pen.drawString("Level:", 800, 255);
            pen.drawString("" + Grid.getNumRowsCleared() / 10, 805, 290);
            pen.setColor(Color.WHITE);
            pen.fillRect(800, 360, 110, 50);
            pen.setColor(Color.BLACK);
            pen.drawRect(800, 360, 110, 50);
            pen.drawString("Lines:", 800, 355);
            pen.drawString("" + Grid.getNumRowsCleared(), 805, 390);
            pen.setColor(Color.WHITE);
            pen.fillRect(800, 460, 110, 110);
            pen.setColor(Color.BLACK);
            pen.drawRect(800, 460, 110, 110);
            pen.drawString("Next Piece:", 800, 455);
            pen.drawImage(AddPiece.nextImage(), 855 - AddPiece.nextImage()
                    .getWidth(null) / 2, 515 - AddPiece.nextImage().getHeight(
                    null) / 2, null);

        }

        else
        {
            pen.setColor(Color.WHITE);
            pen.drawString(ResourceManager.getString("Title").substring(0, 10),
                    0, 20);
            pen.drawString(myGameName, 0, 40);
            pen.drawString(ResourceManager.getString("Score") + myScore, 800,
                    20);

        }

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
        myActors = myModel.getActors();
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
        new LevelViewer(myGameName, myLevelNum, myScore, myCanvas);
    }

    public void loadBonusLevel(int level)
    {
        new LevelViewer(myGameName, level, myScore, myCanvas);
    }

    public void loadEnd(String endCondition)
    {
        new EndView(endCondition, myGameName, myScore, myCanvas);
    }
}
